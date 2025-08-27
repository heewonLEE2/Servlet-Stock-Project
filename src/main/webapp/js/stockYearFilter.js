/**
 * 년도 필터링해서 배당락일 리스트에 보여주는 js
 */

function filterStockYear(stockDiviedListSorted){
	const select = document.getElementById("yearSelect");
	select.innerHTML = "";
	
	let stockYearAllList = [];
	for(let date of stockDiviedListSorted){
  		stockYearAllList.push(date.substring(0, 4));
	}
		
	let stockYearList = [...new Set(stockYearAllList)];
	for(let year of stockYearList){
	  	const option = document.createElement("option");
	  	option.className = "selectYear";
	  	option.innerHTML = `${year}`;
	  	document.querySelector("#yearSelect").append(option);
	}
	
	select.addEventListener("change", (e) => {
		getDiviList(e.target.value, stockDiviedListSorted);
		stockMoveCalendar();
	});
}


 