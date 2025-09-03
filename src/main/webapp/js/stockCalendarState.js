/**
 * 상태 객체, 함수
 */

// 배당락일 캘린더 상태 객체
const exdateCalendarState = {
  calendar: {
    currentYear: null, // 현재 년도
    currentMonth: null, // 현재 월
    today: null, // 실제 오늘 or 최근 배당락일
    stockDiviedListSorted: [] // 배당락일 리스트
  },
  stock: {
    diviList: [], // 특정 연도 배당락일 리스트
    years: [], // 선택가능한 연도 목록
    names: [] // 종목명 목록
  }
};

/**
 * 달력 이동
 */
const moveCalendar = (offset) => {
  // 새로운 date 객체 생성
  // 상태 객체의 년도와 월을 가져와 월은 파라미터에 따라 값이 달라짐
  const newDate = new Date(
    exdateCalendarState.calendar.currentYear,
    exdateCalendarState.calendar.currentMonth + offset,
    1
  );

  // 상태 객체 year, month를 위에서 만든 date로 넣음
  exdateCalendarState.calendar.currentYear = newDate.getFullYear();
  exdateCalendarState.calendar.currentMonth = newDate.getMonth();

  // 달력 렌더링
  renderCalendar();
};


/**
 * 달력 그리기
 */
const drawCalendar = (year, month, stockDiviedListSorted = [], today = null) => {
  
  const calendar = document.getElementById("calendar");
  calendar.innerHTML = ""; // 초기화

  const table = document.createElement("table");

  // 파라미터 년도, 월, 1일의 date 객체 생성
  const theDate = new Date(year, month, 1);
  // 1일의 요일
  const theDay = theDate.getDay();

  // 월의 마지막 날짜
  const last = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
  
  // 윤달인 경우 마지막 날짜를 29로 함
  if (year % 4 === 0 && year % 100 !== 0 || year % 400 === 0) last[1] = 29;
  
  // 파라미터 월의 마지막 날짜
  const lastDay = last[month];
  
  // 캘린더 행 수 계산
  const row = Math.ceil((theDay + lastDay) / 7);

  table.innerHTML = `
    <tr>
      <th>일</th><th>월</th><th>화</th>
      <th>수</th><th>목</th><th>금</th><th>토</th>
    </tr>
  `;

  // 1일부터 시작
  let dNum = 1;

  for (let i = 1; i <= row; i++) { // 행만큼 tr 생성
    const tr = document.createElement("tr");
    table.appendChild(tr);
    
    for (let k = 0; k < 7; k++) { // 7일 td 생성
      let td = document.createElement("td");
      td.className = "calendar-td";
      
      // 첫째주이고, 1일의 요일이 k보다 작거나 
      // 날짜가 월의 마지막 날짜보다 클 경우
      if ((i === 1 && k < theDay) || dNum > lastDay) {
        td.innerHTML = "&nbsp;"; // td에 공백 추가
      } else {
        td.innerHTML = dNum; // 아니면 td에 날짜 추가

        // td id에 오늘 표시
        if (today && dNum === today.day 
        	&& month === today.month && year === today.year) {
          td.id = "today";
        }

        // 배당락일 표시
        for (let date of stockDiviedListSorted) {
			
			// 캘린더 년도, 월, api의 일의 date 객체 생성		  
			const exDate = new Date(year, month, date.day);
			// api의 날짜
			let exDateDay = date.day;
			// exDate의 요일
			const dayOfWeek = exDate.getDay();
			
			// 토요일이면 하루 뺌 -> 금요일
			if(dayOfWeek === 6) exDateDay -= 1;
			// 일요일이면 이틀 뺌 -> 금요일
			if(dayOfWeek === 0) exDateDay -= 2;
			
			// 캘린더의 날짜와 주말 제외한 날짜가 같고
			// 캘린더의 년도, 월이 api의 년, 월과 같으면
			if(dNum == exDateDay && date.year == year 
				&& date.month == month){
					
				// div, p 생성 후
				const div = document.createElement("div");
				div.id = "exdate";
				const p = document.createElement("p");
				
				// p에 캘린더 날짜 넣음
				p.innerHTML = dNum;
				div.appendChild(p);
				td.append(div);
			}
        }
        dNum++; // 일 증가
      }
      tr.appendChild(td);
    }
  }
  calendar.appendChild(table);
};

/**
 * 렌더링 함수
 */
// 캘린더 렌더링
const renderCalendar = () => {
  drawCalendar(
    exdateCalendarState.calendar.currentYear,
    exdateCalendarState.calendar.currentMonth,
    exdateCalendarState.calendar.stockDiviedListSorted,
    exdateCalendarState.calendar.today
  );

  createCalendarTitle(
    exdateCalendarState.calendar.currentYear,
    exdateCalendarState.calendar.currentMonth + 1
  );
};

// 배당락일 리스트 렌더링
const renderStockList = () => {
  getDiviList(exdateCalendarState.calendar.currentYear, exdateCalendarState.calendar.stockDiviedListSorted);
};

// 년도 필터 select 렌더링
const renderYearFilter = () => {
  filterStockYear(exdateCalendarState.calendar.stockDiviedListSorted);
};

/**
 * 배당락일 리스트 렌더링
 */
function getDiviList(year, stockDiviedListSorted) {
  const container = document.querySelector("#dividendList-list");
  container.innerHTML = ""; // 초기화

  const ul = document.createElement("ul");
  ul.id = "stockDiviUl";

  stockDiviedListSorted.forEach(date => {
	  
	  // 주말 제외 로직
	  const exDate = new Date(year, date.month, date.day);
	  let exDateDay = date.day;
	  const dayOfWeek = exDate.getDay();
			
	  if(dayOfWeek === 6) exDateDay -= 1;
	  if(dayOfWeek === 0) exDateDay -= 2;
	  
	// api의 배당락일의 년도와 캘린더의 년도가 같을 경우
	// li 추가
    if (date.year === year) { 
      const li = document.createElement("li");
      li.className = "stockDiviLi";
      li.textContent 
      	= `${date.year}-${String(date.month + 1).padStart(2, "0")}
      		-${String(exDateDay).padStart(2, "0")}`;
      ul.appendChild(li);
    }
  });

  container.appendChild(ul);

  // 배당락일 날짜 li 클릭 시 
  // 해당 캘린더로 이동하는 함수 호출
  stockMoveCalendar(); 
}

/**
 * 캘린더 타이틀 생성
 */
function createCalendarTitle(year, month) {
  // 캘린더의 타이틀인 년도와 월 추가
  const dateContainer = document.querySelector(".date");
  dateContainer.innerHTML = "";
  const p = document.createElement("p");
  p.id = "p-date";
  p.textContent = `${year}년 ${month}월`;
  dateContainer.appendChild(p);
}

/**
 * 연도 필터링
 */
function filterStockYear(stockDiviedListSorted) {
  const select = document.getElementById("yearSelect");
  select.innerHTML = "";

  // api에서 년도 중복 set으로 없앰
  const stockYearList = [...new Set(stockDiviedListSorted.map(d => d.year))];
  
  // select 자식요소로 option 태그들 생성 후 추가
  stockYearList.forEach(year => {
    const option = document.createElement("option");
    option.value = year;
    option.textContent = year;
    select.appendChild(option);
  });

  // select의 년도 클릭 시 아래 해당 년도의 배당락일 리스트 보여줌
  select.addEventListener("change", e => {
    const year = Number(e.target.value);
    getDiviList(year, stockDiviedListSorted);
  });
}

/**
 * 배당락일 클릭 시 해당 날짜로 이동
 */
function stockMoveCalendar() {
  // 배당락일의 li를 list로 불러와서 
  document.querySelectorAll(".stockDiviLi").forEach(li => {
	// li 클릭 시 
    li.addEventListener("click", () => {
	  // - 단위로 split한 후 숫자로 매핑 후 구조분해 할당함
      const [year, month, day] = li.textContent.split("-").map(Number);
      // 상태객체 today에 담기 위해 객체 생성
      const dateObj = { year, month: month - 1, day };

      exdateCalendarState.calendar.currentYear = year;
      exdateCalendarState.calendar.currentMonth = month - 1;
      exdateCalendarState.calendar.today = dateObj;
      
      // 캘린더 렌더링
      renderCalendar();
    });
  });
}

/**
 * input 입력 시 검색어 자동완성창 ul, li 생성 함수
 */

function createInputSearchAutocompleteWindow(exdateCalendarState, keyword){
	const ul = document.createElement("ul");
	ul.id = "autocomplete-ul";
	
	// 상태 객체의 name배열에서
	exdateCalendarState.stock.names
		// name에 input에 입력한 값이 포함되는 종목명만 걸러서
		.filter(name => name.includes(keyword))
		.forEach(name => {
			// li에 추가함
			const li = document.createElement("li");
			li.className = "autocomplete-li";
			li.innerHTML = `${name}`;
			
			// li 클릭하면 
			li.addEventListener("click", () => {
				// input의 value에 name이 들어가고
				document.querySelector("#search-input").value = name;
				// 자동완성 ul 안보임
				ul.style.display = "none";
				
				// 클릭한 종목명으로 배당락일 api 호출함
          		getStockDiviedAPI(name);
			})
			
			ul.append(li);
		})
	
	document.getElementById("autocompleteContainer").append(ul);
}

/**
 * 처음 로딩 시 나오는 캘린더
 */
function initCalendar() {
	
  // 오늘 날짜 객체 생성
  const today = new Date();
  
  // 상태 객체에 오늘 년도, 월, 일 넣음
  exdateCalendarState.calendar.currentYear = today.getFullYear();
  exdateCalendarState.calendar.currentMonth = today.getMonth();
  exdateCalendarState.calendar.today = {
    year: today.getFullYear(),
    month: today.getMonth(),
    day: today.getDate()
  };

  // 캘린더 렌더링
  renderCalendar();

}






