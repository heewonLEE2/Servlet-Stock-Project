/**
 * calendar.js
 */

function drawCalendar(year, month, highlightDays = []) {

	const calendar = document.getElementById("calendar");
	calendar.innerHTML = "";
	
	let table = document.createElement("table");
	
	const date = new Date(); // 현재 날짜 객체 생성
	const currentYear = date.getFullYear(); // 현재 날짜의 년도
	const currentMonth = date.getMonth(); // 현재 날짜의 월
	const currentDay = date.getDate(); // 현재 날짜의 일
	
	// 현재 날짜의 1일의 새로운 날짜 객체 생성
	const theDate = new Date(year, month, 1); 
	// 현재 날짜 1일의 요일
	// ex) 5이면 금요일부터 1 시작됨
	const theDay = theDate.getDay();
	// console.log('theDay' + theDay);
	
	// 달마다 마지막 날짜 배열
	const last = [31,28,31,30,31,30,31,31,30,31,30,31];
	
	// 윤년
	if (year % 4 == 0 && year % 100 !=0 || year % 400 == 0) last[1]=29;
	
	// 현재 월의 마지막 날짜
	const lastDay = last[month];
	// console.log('lastDay' + lastDay);
	
	// 캘린더 행 계산
	// 1일이 시작되는 요일의 숫자와 마지막 날짜를 7로 나눈값
	const row = Math.ceil((theDay+lastDay)/7);
	// console.log(row);
	
	table.innerHTML = `
		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
	`;
	
	//1일부터 시작
	let dNum = 1;
	let tr, td;
	
	//이중 for문을 이용해 달력 테이블 생성
	for (let i = 1; i <= row; i++) {
		
		//행 생성
	 	tr = document.createElement("tr"); 
	 	table.appendChild(tr);
	 	
	  for (let k = 0; k < 7; k++) {//열 생성 (td 태그 생성)        
	    //빈 날짜는 빈칸으로 표기
	    if (i == 1 && k < theDay || dNum > lastDay) {
			
		  td = document.createElement("td");
		  td.innerHTML = "&nbsp;";
		  
	    } else {
			
	      //오늘 날짜
	      if (dNum === currentDay) {
			td = document.createElement("td");
			td.id = "today";
			td.innerHTML = dNum;
			
	      } else { 
			
			//오늘이 아닌 날짜
			td = document.createElement("td");
			td.innerHTML = dNum;
			
	      }
	      
	      if(dNum === highlightDays){
				td.style.backgroundColor = "yellow";
		  }
		
	      //일 증가
	      dNum++;
	    }
	    tr.appendChild(td);
	  }
	}    
	
	calendar.appendChild(table);
}

// 페이지 로드 시 기본 달력
const today = new Date();
drawCalendar(today.getFullYear(), today.getMonth(), [today.getDate()]);