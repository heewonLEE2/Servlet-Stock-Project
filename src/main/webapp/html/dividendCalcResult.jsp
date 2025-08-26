<%@page import="model.vo.StockDividendInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>배당 수익금 예상</title>
<link rel="stylesheet" type="text/css"
	href="../css/dividendCalcResult.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" defer></script>
<script type="text/javascript" src="../js/dividendCalcResult.js" defer></script>
</head>
<body>

	<%@ include file="header.jsp"%>

	<!-- 보유 주식 입력 섹션 -->
	<div class="container">
		<div class="card input-section">
			<div class="card-header">
				<div class="card-icon">📝</div>
				<h2 class="card-title">보유 주식 입력</h2>
			</div>
			<form id="stockForm">
				<div class="form-row">
				<div class="search-container">
					<div class="form-field">
						<label>종목명</label> <input type="text" id="stNm"
							placeholder="예: 삼성전자">
						<div class="search-suggestions" id="searchSuggestions">
							<!-- 검색 결과가 여기에 표시됩니다 -->
						</div>
					</div>
					</div>
					<div class="form-field">
						<label>보유 수량</label> <input type="number" id="stCt"
							placeholder="주">
					</div>

				</div>
				<button type="button" class="btn btn-initialize">초기화</button>
				<button type="button" class="btn btn-calculate">💰 배당금 계산</button>
			</form>

		</div>
		<!-- 사용자 검색 결과 컨테이너 -->
		<div id="newCardContainer"></div>
	</div>


	<%@ include file="footer.jsp"%>

</body>

</html>

