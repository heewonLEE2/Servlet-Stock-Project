/*
 * 배당락일 리스트에서 날짜 클릭 시 해당 날짜로 캘린더 이동하는 js
*/

function stockMoveCalendar(){
	document.querySelectorAll(".stockDiviLi").forEach(li => {
		li.addEventListener("click", () => {
			const dateArr = li.textContent.split("-");
			drawCalendar(dateArr[0], Number(dateArr[1].substring(1))-1, dateArr[2]);
			createCalendarTitle(dateArr[0], Number(dateArr[1].substring(1)));
		})
	})
}
