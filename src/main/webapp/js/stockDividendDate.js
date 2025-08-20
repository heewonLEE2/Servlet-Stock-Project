/**
 * 주식 배당락일 가져오는 js
 */


let stockDiviedList = [];

const getStockDivied = async () => {
  try {
	const itmsName = document.querySelector("search-input");
	  
    const response 
    	= await fetch(`http://localhost:8888/stockCalendarServlet?itmsName=${itmsName}`);
    const data = await response.json();
    stockDiviedList.push(data);
    console.log(stockDiviedList);
  } catch(error) {
    console.error('배당락일 데이터 가져오는 중 오류 발생: ', error);
  }
}

getStockDivied();

const p = document.createElement("p");
const stockDiviedListLastIdx = stockDiviedList.length - 1;
p.innerHTML = `
  ${stockDiviedList[stockDiviedListLastIdx].substring(0, 4)}년 
  ${stockDiviedList[stockDiviedListLastIdx].substring(4, 6)}월`;
document.querySelector(".date").append(p);

const stockDiviedListReverse = stockDiviedList.reverse();
let stockYearAllList = [];
for(let date of stockDiviedListReverse){
  stockYearAllList.push(date.substring(0, 4));
}

let stockYearList = [...new Set(stockYearAllList)];
for(let year of stockYearList){
  const option = document.createElement("option");
  option.innerHTML = `${year}`;
  document.querySelector("#yearSelect").append(option);
}


