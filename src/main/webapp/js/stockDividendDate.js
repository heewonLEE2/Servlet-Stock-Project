/**
 * 주식 배당락일 가져오는 js
 */

const stockInput = document.querySelector("#search-input");

const getStockDivied = async () => {
  try {
    const response 
    	= await fetch(`http://localhost:8888/stockCalendar.stockwave?itmsName=${stockInput.value}`);
    const data = await response.json();
    
    document.querySelector(".date").innerHTML = "";
	
	if(data&&data.length>0){
		// document.querySelector("#p-date").innerHTML = "";
		
		const stockDiviedListSorted = data.sort().reverse();
	    const firstDate = data[0];
        const year = Number(firstDate.substring(0, 4));
        const month = Number(firstDate.substring(4, 6)) - 1;
        const days = Number(firstDate.substring(6, 8));
        
        drawCalendar(year, month, days);
		
		createCalendarTitle(year, month+1);
		
		// year과 년도가 같은 배당락일 모두 리스트에 표시
		getDiviList(year, stockDiviedListSorted);
		// 년도 필터링 연도 선택 시 해당 년도의 배당락일 리스트에 표시
		filterStockYear(stockDiviedListSorted);
		// 리스트 선택 시 해당 날짜로 캘린더 이동
		stockMoveCalendar();
		
	}
	
  } catch(error) {
    console.error('배당락일 데이터 가져오는 중 오류 발생: ', error);
  }
}

stockInput.addEventListener("keydown", e => {
	if(e.key === "Enter"){
		getStockDivied();
	}
});



