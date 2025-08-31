/**
 * 상태 객체, 함수
 */

const exdateCalendarState = {
  calendar: {
    currentYear: null,
    currentMonth: null,
    today: null, // 실제 오늘 or 최근 배당락일
    stockDiviedListSorted: []
  },
  stock: {
    keyword: null, // 검색한 종목명
    diviList: [], // 특정 연도 배당락일 리스트
    years: [], // 선택가능한 연도 목록
    names: [] // 종목명 목록
  },
  ui: {
    loading: false, // API 로딩 상태
  }
};

/**
 * 달력 이동
 */
const moveCalendar = (offset) => {
  const newDate = new Date(
    exdateCalendarState.calendar.currentYear,
    exdateCalendarState.calendar.currentMonth + offset,
    1
  );

  exdateCalendarState.calendar.currentYear = newDate.getFullYear();
  exdateCalendarState.calendar.currentMonth = newDate.getMonth();

  renderCalendar();
};


/**
 * 달력 그리기
 */
const drawCalendar = (year, month, stockDiviedListSorted = [], today = null) => {
  const calendar = document.getElementById("calendar");
  calendar.innerHTML = "";

  const table = document.createElement("table");

  const date = new Date();
  const currentDay = today ? today.day : date.getDate();

  const theDate = new Date(year, month, 1);
  const theDay = theDate.getDay();

  const last = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
  if (year % 4 === 0 && year % 100 !== 0 || year % 400 === 0) last[1] = 29;
  const lastDay = last[month];

  const row = Math.ceil((theDay + lastDay) / 7);

  table.innerHTML = `
    <tr>
      <th>일</th><th>월</th><th>화</th>
      <th>수</th><th>목</th><th>금</th><th>토</th>
    </tr>
  `;

  let dNum = 1;

  for (let i = 1; i <= row; i++) {
    const tr = document.createElement("tr");
    table.appendChild(tr);

    for (let k = 0; k < 7; k++) {
      let td = document.createElement("td");

      if ((i === 1 && k < theDay) || dNum > lastDay) {
        td.innerHTML = "&nbsp;";
      } else {
        td.innerHTML = dNum;

        // 오늘 표시
        if (today && dNum === today.day && month === today.month && year === today.year) {
          td.id = "today";
        }

        // 배당락일 표시
        for (let date of stockDiviedListSorted) {
          if (dNum === date.day && month === date.month && year === date.year) {
            td.style.backgroundColor = "yellow";
          }
        }

        dNum++;
      }

      tr.appendChild(td);
    }
  }

  calendar.appendChild(table);
};

/**
 * 렌더링 함수
 */
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

const renderStockList = () => {
  getDiviList(exdateCalendarState.calendar.currentYear, exdateCalendarState.calendar.stockDiviedListSorted);
};

const renderYearFilter = () => {
  filterStockYear(exdateCalendarState.calendar.stockDiviedListSorted);
};

/**
 * 배당락일 리스트 렌더링
 */
function getDiviList(year, stockDiviedListSorted) {
  const container = document.querySelector("#dividendList-list");
  container.innerHTML = "";

  const ul = document.createElement("ul");
  ul.id = "stockDiviUl";

  stockDiviedListSorted.forEach(date => {
    if (date.year === year) {
      const li = document.createElement("li");
      li.className = "stockDiviLi";
      li.textContent = `${date.year}-${String(date.month + 1).padStart(2, "0")}-${String(date.day).padStart(2, "0")}`;
      ul.appendChild(li);
    }
  });

  container.appendChild(ul);

  stockMoveCalendar(); 
}

/**
 * 캘린더 타이틀 생성
 */
function createCalendarTitle(year, month) {
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

  const stockYearList = [...new Set(stockDiviedListSorted.map(d => d.year))];

  stockYearList.forEach(year => {
    const option = document.createElement("option");
    option.value = year;
    option.textContent = year;
    select.appendChild(option);
  });

  select.addEventListener("change", e => {
    const year = Number(e.target.value);
    getDiviList(year, stockDiviedListSorted);
  });
}

/**
 * 배당락일 클릭 시 해당 날짜로 이동
 */
function stockMoveCalendar() {
  document.querySelectorAll(".stockDiviLi").forEach(li => {
    li.addEventListener("click", () => {
      const [year, month, day] = li.textContent.split("-").map(Number);
      const dateObj = { year, month: month - 1, day };

      exdateCalendarState.calendar.currentYear = year;
      exdateCalendarState.calendar.currentMonth = month - 1;
      exdateCalendarState.calendar.today = dateObj;

      renderCalendar();
    });
  });
}

/**
 * 초기화
 */
function initCalendar() {
  const today = new Date();
  exdateCalendarState.calendar.currentYear = today.getFullYear();
  exdateCalendarState.calendar.currentMonth = today.getMonth();
  exdateCalendarState.calendar.today = {
    year: today.getFullYear(),
    month: today.getMonth(),
    day: today.getDate()
  };

  renderCalendar();

}






