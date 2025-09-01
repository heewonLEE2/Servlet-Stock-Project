/**
 * api, 이벤트리스너
 */

initCalendar();

document.querySelector("#leftArrow").addEventListener("click", () => moveCalendar(-1));
document.querySelector("#rightArrow").addEventListener("click", () => moveCalendar(1));
  
const stockInput = document.querySelector("#search-input");

stockInput.addEventListener("input", e => {
	exdateCalendarState.stock.keyword = e.target.value;
});

stockInput.addEventListener("keyup", e => {
	
	const keyword = e.target.value.trim();
	document.querySelector("#autocompleteContainer").innerHTML = "";
	
	if(keyword.length > 0){
		createInputSearchAutocompleteWindow(exdateCalendarState, keyword);
	}
	
   //if (e.key === "Enter") {getStockDivied();}
   }
); 
  
/**
 * 배당락일 API 호출
 */
const getStockDivied = async (name) => {
  try {
    exdateCalendarState.ui.loading = true;
    const response = await fetch(
      `http://localhost:8888/stockCalendar.stockwave?itmsName=${name}`
    );
    const data = await response.json();

    if (data && data.length > 0) {
      exdateCalendarState.calendar.stockDiviedListSorted = data.sort().reverse().map(d => ({
        year: Number(d.substring(0, 4)),
        month: Number(d.substring(4, 6)) - 1,
        day: Number(d.substring(6, 8))
      }));

      const firstDate = exdateCalendarState.calendar.stockDiviedListSorted[0];
      exdateCalendarState.calendar.currentYear = firstDate.year;
      exdateCalendarState.calendar.currentMonth = firstDate.month;
      exdateCalendarState.calendar.today = firstDate;

      // 같은 연도 배당락일만 리스트에 표시
      exdateCalendarState.stock.diviList = exdateCalendarState.calendar.stockDiviedListSorted.filter(
        d => d.year === firstDate.year
      );

      // 선택 가능한 년도 Set
      exdateCalendarState.stock.years = [...new Set(exdateCalendarState.calendar.stockDiviedListSorted.map(d => d.year))];

      // 렌더링
      renderCalendar();
      renderStockList();
      renderYearFilter();
    }

  } catch (err) {
    console.error(err);
  } finally {
    exdateCalendarState.ui.loading = false;
  }
}; 

const getStockJsonApi = async () => {
	try{
		const response = await fetch(
     		`http://localhost:8888/stockList.stockwave`
    	);
    	const data = await response.json();
    	
    	if(data&&data.length>0){
			data.forEach(stock => {
				exdateCalendarState.stock.names.push(stock.name);
			})
		}
		
	} catch(err){
		console.error(err);
	}
}

getStockJsonApi();