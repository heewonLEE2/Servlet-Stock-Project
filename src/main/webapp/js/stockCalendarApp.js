/**
 * api, 이벤트리스너
 */

initCalendar();

// 왼쪽, 오른쪽 화살표 클릭 시 moveCalendar 함수 호출
document.querySelector("#leftArrow").addEventListener("click", () => moveCalendar(-1));
document.querySelector("#rightArrow").addEventListener("click", () => moveCalendar(1));
  
// 검색창
const stockInput = document.querySelector("#search-input");

stockInput.addEventListener("keyup", e => {
	
	const keyword = e.target.value.trim();
	document.querySelector("#autocompleteContainer").innerHTML = "";
	
	if(keyword.length > 0){
		// 입력창과 같은 주식 종목명 자동완성 리스트 만드는 함수
		createInputSearchAutocompleteWindow(exdateCalendarState, keyword);
	}
   }
); 
  
/**
 * 배당락일 API 호출
 */
const getStockDiviedAPI = async (name) => {
  try {
    const response = await fetch(
      `http://localhost:8888/stockCalendar.stockwave?itmsName=${name}`
    );
    const data = await response.json();

    if (data && data.length > 0) {
	  // 가장 가까운 날짜 순으로 정렬
      exdateCalendarState.calendar.stockDiviedListSorted 
      	= data.sort().reverse().map(d => ({
			// year, month, day로 객체 매핑
        	year: Number(d.substring(0, 4)),
        	month: Number(d.substring(4, 6)) - 1,
        	day: Number(d.substring(6, 8))
      	}));

	  // 최근 배당락일 하나만 가져와 firstDate에 담음
      const firstDate = exdateCalendarState.calendar.stockDiviedListSorted[0];
      // 상태 객체 year, month에 yyyy, mm를 담음
      exdateCalendarState.calendar.currentYear = firstDate.year;
      exdateCalendarState.calendar.currentMonth = firstDate.month;
      // today에는 최근 배당락일 yyyymmdd를 넣음
      exdateCalendarState.calendar.today = firstDate;

      // 같은 연도 배당락일만 상태 객체 diviList에 담음
      exdateCalendarState.stock.diviList 
      	= exdateCalendarState.calendar.stockDiviedListSorted.filter(
        	d => d.year === firstDate.year
      );

      // 선택 가능한 년도 Set(중복없앰)
      exdateCalendarState.stock.years = [...new Set(exdateCalendarState.calendar.stockDiviedListSorted.map(d => d.year))];

      // 렌더링
      renderCalendar();
      renderStockList();
      renderYearFilter();
    }

  } catch (err) {
    console.error(err);
  }
}; 

/* stock json */
const getStockJsonApi = async () => {
	try{
		const response = await fetch(
     		`http://localhost:8888/stockList.stockwave`
    	);
    	const data = await response.json();
    	
    	if(data&&data.length>0){
			data.forEach(stock => {
				// 주식 종목명만 names 상태객체 리스트에 넣음
				exdateCalendarState.stock.names.push(stock.name);
			})
		}
		
	} catch(err){
		console.error(err);
	}
}

getStockJsonApi();