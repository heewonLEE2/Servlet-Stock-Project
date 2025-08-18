// 이벤트 리스너 등록 페이지 로딩하면 
document.addEventListener('DOMContentLoaded', function() {
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
                    <td class="stockIsLike">${stock.isLike ? '✅' : '❌'}</td>
                    <td>${stock.count}</td>
                `;
		tbody.appendChild(row);
	});
	// 테이블 렌더링 후 이벤트 리스너 등록
	addStockIsLikeEventListeners();
};

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
};

// stockIsLike 클릭 이벤트 리스너 등록 함수
function addStockIsLikeEventListeners() {
	document.querySelectorAll('.stockIsLike').forEach((element) => {
		element.addEventListener('click', function() {
			const stockId = this.parentElement.dataset.id; // tr의 data-id 가져오기
			const bool = this.textContent === '✅'; // boolean 값
			console.log('Stock ID:', stockId, 'Is Liked:', bool);

			let userConfirmed;

			if (!bool) {
				userConfirmed = confirm('이 주식을 즐겨찾기 추가 하시겠습니까?');
			} else {
				userConfirmed = confirm('이 주식을 즐겨찾기 취소 하시겠습니까?');
			}
			// 사용자 확인 대화상자 표시
			if (userConfirmed) {
				// 확인을 눌렀을 때 실행할 함수
				updateStockLike(stockId, bool);
			}
			// 취소를 누르면 아무것도 하지 않음
		});
	});
}

// 주식 좋아요 상태 업데이트 함수
async function updateStockLike(stockId, newLikeStatus) {
	try {
		const response = await fetch(`http://localhost:8888/PutJsonServlet`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				stockId,
				isLike: newLikeStatus,
			}),
		});

		if (response.ok) {
			console.log('좋아요 상태가 업데이트되었습니다.');
			// 테이블 다시 렌더링
			fetchStockData();
		} else {
			console.error('좋아요 상태 업데이트 실패');
		}
	} catch (error) {
		console.error('API 호출 오류:', error);
	}
}




