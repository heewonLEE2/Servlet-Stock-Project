/**
 * 배당락일 리스트 로딩하는 js
 */

function getDiviList(year, stockDiviedListSorted){
	document.querySelector("#dividendList-list").innerHTML = ""
	
	// year과 년도가 같은 배당락일 모두 리스트에 표시
	const ul = document.createElement("ul");
	ul.id = "stockDiviUl";
	
	stockDiviedListSorted.forEach(date => {
		const apiYear = Number(date.substring(0, 4));
		if(Number(year) === apiYear){
			const li = document.createElement("li");
			li.className = "stockDiviLi";
			li.innerHTML 
				= `${year}-${date.substring(4, 6)}
					-${date.substring(6, 8)}`;
			ul.appendChild(li);
			document.querySelector("#dividendList-list").append(ul);
		}
	})
} // getDiviList

function createCalendarTitle(year, month){
	if(document.querySelector(".date").textContent==""){
		const p = document.createElement("p");
		p.id = "p-date";
		p.innerHTML = `${year}년 ${month}월`;
		document.querySelector(".date").appendChild(p);
	} else {
		document.querySelector("#p-date").textContent==""; 
	}
}