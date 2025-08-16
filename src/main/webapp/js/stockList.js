// 이벤트 리스너 등록 페이지 로딩하면 
document.addEventListener('DOMContentLoaded', function () {
  // 초기 테이블 렌더링
  fetchStockData();
});

// 테이블 렌더링 함수
function renderTable(data) {
  const tbody = document.getElementById('stockTableBody');
  tbody.innerHTML = '';

  data.forEach((stock) => {
    const row = document.createElement('tr');
    row.dataset.id = stock.id;
    row.innerHTML = `
                    <td class="stock-name">${stock.name}</td>
                    <td class="category">${stock.category}</td>
                    <td class="stockNum">${stock.stockNum}</td>
                    <td>${stock.isLike ? '✅' : '❌'}</td>
                    <td>${stock.count}</td>
                `;
    tbody.appendChild(row);
  });
}

// API 호출
async function fetchStockData() {
  try {
    const response = await fetch('http://localhost:8888/JsonServlet');
    const data = await response.json();
    //console.log(data); // 데이터 확인
	// 받은 데이터를 렌더링 함수 data 파라미터에 보내기
    renderTable(data);
  } catch (error) {
    console.error('API 데이터 가져오기 실패:', error);
  }
}
