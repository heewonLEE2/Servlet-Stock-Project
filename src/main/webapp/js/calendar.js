/**
 * calendar.js
 */

const month = document.getElementById("month");
const calendar = document.getElementById("calendar");

let table = "";

const date = new Date();
const currentYear = date.getFullYear();
const currentMonth = date.getMonth();
const currentDay = date.getDate();

const theDate = new Date(currentYear, currentMonth, 1);
const theDay = theDate.getDay();
console.log('theDay' + theDay);

const last = [31,28,31,30,31,30,31,31,30,31,30,31];

if (currentYear % 4 == 0 && currentYear % 100 !=0 || currentYear % 400 == 0) lastDate=last[1]=29;

const lastDate = last[currentMonth];
console.log('lastDate' + lastDate);


const row = Math.ceil((theDay+lastDate)/7);
console.log(row);


table += "<tr>";
table += "<th>월</th>";
table += "<th>화</th>";
table += "<th>수</th>";
table += "<th>목</th>";
table += "<th>금</th>";
table += "<th>토</th>";
table += "<th>일</th>";
table += "</tr>";

//1일부터 기록
let dNum = 1;
//이중 for문을 이용해 달력 테이블 생성
for (let i = 1; i <= row; i++) {
  table += "<tr>"; //행 생성

  for (let k = 1; k <= 7; k++) {//열 생성 (td 태그 생성)        
    //빈 날짜는 빈칸으로 표기
    if (i == 1 && k < theDay || dNum > lastDate) {
      table += "<td> &nbsp; </td>";
    }
    else {
      //오늘 날짜
      if (dNum === currentDay) {
        table += "<td id='today'>" + dNum + "</td>";
      }
      //오늘이 아닌 날짜
      else {
        table += "<td>" + dNum + "</td>";
      }
      //일 증가
      dNum++;
    }
  }
  table += "</tr>";
}    

// table.innerHTML = `
//     <thead>
//       <th>
//         <tr>${currentYear}</tr>
//         <tr>${currentMonth}</tr>
//         <tr>${currentDay}</tr>
//       </th>s
//     </thead>
// `;




month.innerHTML = `<table>${table}</table>`;