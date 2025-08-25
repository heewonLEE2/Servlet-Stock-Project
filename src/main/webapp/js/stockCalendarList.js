/**
 * 배당락일 리스트 로딩하는 js
 */

function getDiviList(year, stockDiviedListSorted){
	
	// year과 년도가 같은 배당락일 모두 리스트에 표시
	const ul = document.createElement("ul");
	
	stockDiviedListSorted.forEach(date => {
		const apiYear = Number(date.substring(0, 4));
		if(year === apiYear){
			const li = document.createElement("li");
			li.className = "stockDiviLi";
			li.innerHTML 
				= `${year}-${date.substring(4, 6)}
					-${date.substring(6, 8)}`;
			ul.appendChild(li);
			document.querySelector("#dividendList-list").append(ul);
		}
	})
	
	
	
}