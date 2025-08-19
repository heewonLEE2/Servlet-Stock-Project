// API 데이터를 내부 형식으로 변환하는 함수
function convertApiDataToDisplayFormat(apiData, stockName) {
	//console.log(apiData);
	//console.log(apiData.clpr);
	const currentPrice = parseInt(apiData.clpr);
	// 전일 종가가 없으면 시가 사용
	const previousClose = parseInt(apiData.mkp);
	const change = currentPrice - previousClose;
	const changePercent =
		previousClose !== 0 ? (change / previousClose) * 100 : 0;

	return {
		name: stockName,
		code: apiData.isinCd,
		currentPrice: currentPrice,
		change: change,
		changePercent: parseFloat(changePercent.toFixed(2)),
		marketCap: formatMarketCap(apiData.mrktTotAmt),
		openPrice: parseInt(apiData.mkp),
		closePrice: parseInt(apiData.clpr),
		highPrice: parseInt(apiData.hipr),
		lowPrice: parseInt(apiData.lopr),
	};
}

// 시가총액 포맷팅 함수 (원 단위를 조/억 단위로 변환)
function formatMarketCap(mrktTotAmt) {
	const amount = parseInt(mrktTotAmt);
	const trillion = Math.floor(amount / 1000000000000);
	const billion = Math.floor((amount % 1000000000000) / 100000000);

	if (trillion > 0) {
		if (billion > 0) {
			return `${trillion}조 ${billion}억원`;
		} else {
			return `${trillion}조원`;
		}
	} else if (billion > 0) {
		return `${billion}억원`;
	} else {
		return `${Math.floor(amount / 100000000)}억원`;
	}
}
// API에서 받은 종목 데이터 저장 배열
const apiStockData = [];
// 검색 기능
const searchInput = document.getElementById("stockSearch");
const searchSuggestions = document.getElementById("searchSuggestions");

searchInput.addEventListener("input", function() {
	const query = this.value.trim().toLowerCase();

	if (query.length === 0) {
		hideSuggestions();
		return;
	}

	const results = apiStockData.filter((stock) =>
		stock.name.toLowerCase().includes(query)
	);

	showSuggestions(results);
});

// 검색창 포커스 아웃시 suggestions 숨기기
searchInput.addEventListener("blur", function() {
	// 약간의 지연을 주어 클릭 이벤트가 먼저 실행되도록 함
	setTimeout(() => {
		hideSuggestions();
	}, 200);
});

// 검색 결과 표시
function showSuggestions(results) {
	if (results.length === 0) {
		searchSuggestions.innerHTML =
			'<div class="no-results">검색 결과가 없습니다.</div>';
	} else {
		searchSuggestions.innerHTML = results
			.map(
				(stock) => `
                    <div class="suggestion-item" onclick="selectStock('${stock.name}')">
                        <span class="suggestion-name">${stock.name}</span>
                    </div>
                `
			)
			.join("");
	}
	searchSuggestions.style.display = "block";
}

// 검색 결과 숨기기
function hideSuggestions() {
	searchSuggestions.style.display = "none";
}

// 종목 선택
function selectStock(stockName) {
	const stock = apiStockData.find((s) => s.name === stockName);
	if (stock) {
		// 검색 input 창에 선택한 종목명 입력
		searchInput.value = stockName;
		fetchStockInfo(stockName);
		hideSuggestions();
	}
}

// Enter 키로 첫 번째 결과 선택
searchInput.addEventListener("keydown", function(event) {
	if (event.key === "Enter") {
		const firstSuggestion = searchSuggestions.querySelector(".suggestion-item");
		if (firstSuggestion) {
			const stockName =
				firstSuggestion.querySelector(".suggestion-name").textContent;
			selectStock(stockName);
		}
	}
});

// 실시간 시간 업데이트
function updateCurrentTime() {
	const now = new Date();
	const timeString = now.toLocaleTimeString("ko-KR", {
		hour12: false,
		hour: "2-digit",
		minute: "2-digit",
		second: "2-digit",
	});

	document.getElementById("currentTime").textContent = timeString;

	const dateTimeString = now.toLocaleString("ko-KR", {
		year: "numeric",
		month: "long",
		day: "numeric",
		hour: "2-digit",
		minute: "2-digit",
		second: "2-digit",
		hour12: false,
	});

	document.getElementById("lastUpdate").textContent = dateTimeString;
}

// 1초마다 시간 업데이트
setInterval(updateCurrentTime, 1000);
updateCurrentTime(); // 초기 실행

// API 데이터로 정보 업데이트하는 함수 (개선됨)
function updateStockInfo(stockData) {
	// 종목 기본 정보
	document.getElementById("stockName").textContent = stockData.name;
	document.querySelector(".stock-code").textContent = stockData.code;
	document.getElementById("currentPrice").textContent = formatPrice(
		stockData.currentPrice
	);

	// 변동률 표시
	const change = stockData.change;
	const changePercent = stockData.changePercent;
	const priceChangeElement = document.getElementById("priceChange");

	const changeText = `${change > 0 ? "+" : ""}${formatNumber(change)} (${change > 0 ? "+" : ""
		}${changePercent}%)`;
	priceChangeElement.textContent = changeText;

	// 변동률에 따른 색상 변경
	priceChangeElement.className = `price-change ${getChangeClass(change)}`;

	// 상세 정보
	document.getElementById("marketCap").textContent = stockData.marketCap;
	document.getElementById("openPrice").textContent = formatPrice(
		stockData.openPrice
	);
	document.getElementById("closePrice").textContent = formatPrice(
		stockData.closePrice
	);
	document.getElementById("highPrice").textContent = formatPrice(
		stockData.highPrice
	);
	document.getElementById("lowPrice").textContent = formatPrice(
		stockData.lowPrice
	);
}

// API에서 받은 실제 데이터로 업데이트하는 함수
function updateStockInfoFromApi(apiData, stockName) {
	const displayData = convertApiDataToDisplayFormat(apiData, stockName);
	console.log(displayData);
	updateStockInfo(displayData);
}

// 초기 데이터 로드 (삼성전자)
document.addEventListener('DOMContentLoaded', function() {
	// URL에서 name 파라미터 가져오기
	const urlParams = new URLSearchParams(window.location.search);
	const stockName = urlParams.get('name');

	// 파라미터가 있으면 해당 주식 정보, 없으면 삼성전자 정보 로드
	if (stockName) {
		fetchStockInfo(stockName);
	} else {
		fetchStockInfo('삼성전자');
	}

	// fetch API 데이터
	console.log("API 호출 시작...");
	fetch("http://localhost:8888/JsonServlet")
		.then((response) => {
			// console.log("API 응답 받음:", response.status, response.ok);
			if (response.ok) {
				return response.json();
			}
			throw new Error(
				`Network response was not ok. Status: ${response.status}`
			);
		})
		.then((data) => {
			console.log("받은 JSON 데이터:", data);
			// JSON 데이터에서 모든 기업명을 추출해서 apiStockData에 추가
			if (Array.isArray(data)) {
				// console.log("stock 배열 길이:", data.length);
				data.forEach((stock) => {
					apiStockData.push({ name: stock.name });
				});
				// console.log("API에서 받은 기업명 데이터:", apiStockData);
			}
			// updateStockInfoFromApi(data);
		})
		.catch((error) => {
			console.error("There was a problem with the fetch operation:", error);
			console.error("에러 상세:", error.message);
		});
});

// 가격 포맷팅 함수
function formatPrice(price) {
	return price.toLocaleString() + "원";
}

// 숫자 포맷팅 함수
function formatNumber(num) {
	return num.toLocaleString();
}

// 변동률 클래스 결정
function getChangeClass(change) {
	if (change > 0) return "change-positive";
	if (change < 0) return "change-negative";
	return "change-neutral";
}

// API 호출
async function fetchStockInfo(stockName) {
	try {
		const response = await fetch(
			`http://localhost:8888/StockInfoServlet?name=${stockName}`
		);
		const data = await response.json();
		// console.log(data)
		// [{}] 이런식으로 데이터가 넘어와서 data[0] 번 인덱스로 넘겨야 잘 적용됨
		updateStockInfoFromApi(data[0], stockName);
	} catch (error) {
		console.error("주식 정보 가져오기 실패:", error);
	}
}

