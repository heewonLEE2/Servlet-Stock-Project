/**
 * 주식 배당락일 가져오는 js
 */

const stockInput = document.querySelector("#search-input");

const getStockDivied = async () => {
  try {
    const response 
    	= await fetch(`http://localhost:8888/stockCalendar.stockwave?itmsName=${stockInput.value}`);
    const data = await response.json();
    
    const p = document.createElement("p");
	
	if(data&&data.length>0){
		p.innerHTML = "";
				
	    const firstDate = data[0];
        const year = Number(firstDate.substring(0, 4));
        const month = Number(firstDate.substring(4, 6)) - 1;
        const days = data.map(d => Number(d.substring(6, 8)));
        
        drawCalendar(year, month, days);
		
		const stockDiviedListSorted = data.sort().reverse();
		p.innerHTML = `
		  ${data[0].substring(0, 4)}년 
		  ${data[0].substring(4, 6)}월`;
		document.querySelector(".date").append(p);
		
		let stockYearAllList = [];
		for(let date of stockDiviedListSorted){
		  stockYearAllList.push(date.substring(0, 4));
		}
		
		let stockYearList = [...new Set(stockYearAllList)];
		for(let year of stockYearList){
		  const option = document.createElement("option");
		  option.innerHTML = `${year}`;
		  document.querySelector("#yearSelect").append(option);
		}
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



