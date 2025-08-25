/**
 * 년도 필터링해서 배당락일 리스트에 보여주는 js
 */

function filterStockYear(stockDiviedListSorted){
	
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
	
	const options = document.querySelectorAll(".selectYear");
	options.forEach(y => {
		y.addEventListener("click", () => {
			console.log(y);
			
			getStockDivied(y.value, stockDiviedListSorted);
		})
	})
	
}
 