$(function() {
	$('.btn-initialize').on('click', e => {
		// 입력창들 초기화
		initInput();

		// 생성된 card 없애기
		const newCard = $('#newCardContainer');

		// newCard 초기화
		if (newCard.children()) {
			newCard.empty(); // 자식 요소 전부 삭제
		}
	});

	$('.btn-calculate').on('click', async e => {

		// 응답 데이터 렌더링 할 부분
		const newCard = $('#newCardContainer');

		// newCard 초기화
		if (newCard.children()) {
			newCard.empty(); // 자식 요소 전부 삭제
		}

		const calcBtn = $('.btn-calculate');

		calcBtn.prop("disabled", true);

		let responseData = await fetchData();

		try {

			if (responseData.errorMessage) {
				alert("해당 주식 정보가 없습니다.");

				let stockNameInputBox = $('#stNm');

				// alert 띄우고 다시 입력창이 렌더링 되었을 때 포커스
				if (stockNameInputBox) {
					// 입력창 초기화 & 주식 입력 박스 focus
					initInput();
					return;
				}

				// 버튼 활성화
				calcBtn.prop("disabled", false);

			} else {
				addNewCard(responseData);
				calcBtn.prop("disabled", false);
			};

		} catch (error) {
			console.log(error);
		};

	});
});

/* functions */

// 응답 데이터 렌더링 함수
function addNewCard(responseData) {

	// 계산 결과
	const calcResult = responseData.dividendCalc;
	// 올해 지급 현황 List	
	const dividendForThisYear = responseData.dividendForThisYear;

	const thisYear = dividendForThisYear[0];

	let cardContainer = $('#newCardContainer');

	const stockName = $('#stNm').val();

	// 동적으로 추가될 HTML
	const cardHTML = `
		<div class="card added-card" id="newCard" style="display:none;">
			<div class="card-header">
				<div class="card-icon">📊</div>
				<h2 class="card-title">${stockName}</h2>
			</div>
											                
			<div class="stock-info">
				<div class="info-item">
					<div class="info-label">예상 배당 수익</div>
					<div class="info-value calc">${calcResult}</div>
				</div>
				<div class="info-item">
					<div class="info-label">${thisYear} 배당금 지급 현황</div>
					<div class="info-value situation"></div>
				</div>
			</div>
		</div>
						`;

	cardContainer.append(cardHTML);

	// 지급 현황 보여주는 부분
	const infoDiv = $('.situation');

	if (infoDiv.length) {
		showCurrentSituation(dividendForThisYear);
		// 아래 방향으로 부드럽게 스크롤하여 
		// 렌더링 된 데이터를 보여준다.
		$('#newCard').fadeIn("slow");
		window.scroll({ top: 600, behavior: "smooth" });
		return;
	}

}

// 지급 현황 List 요소 렌더링 함수
function showCurrentSituation(list) {
	const length = list.length;
	let div = $('.situation');
	console.log(list);

	for (let i = 1; i < length; i++) {
		if (i != length - 1) {
			div.append(list[i] + ', ');
		} else {
			div.append(list[i] + ` (총 ${length-1} 회)`);
		}
	}

}

// fetch
async function fetchData() {
	try {

		const stNm = $('#stNm').val();
		const stCt = $('#stCt').val();

		const resp = await fetch(
			`http://localhost:8888/RetainedStockServlet?stockName=${stNm}&stockCount=${stCt}`
		);

		const data = await resp.json();
		console.log("데이터 로드 성공");
		return data;

	} catch (error) {
		console.log("데이터 불러오는 도중 오류 발생: " + error);
	};
};

// 입력창 초기화
function initInput() {
	let stockName = $('#stNm'); // 주식 이름
	let stockCount = $('#stCt'); // 보유 수

	// input 요소 초기화
	stockName.val("");
	stockCount.val("");

	// 주식 종목 입력 칸 focus
	stockName.focus();

	console.log("초기화 완료");
}
