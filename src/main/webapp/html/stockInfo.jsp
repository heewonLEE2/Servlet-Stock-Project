<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>로고 - 종목 정보</title>
    <link rel="stylesheet" href="../css/stockInfo.css" />
    <script src="../js/stockInfo.js" defer></script>
  </head>
  <body>
	<%@ include file="header.jsp" %>
    <div class="container">
      <!-- 종목 검색 섹션 -->
      <div class="search-section">
        <div class="search-container">
          <div class="search-icon">🔍</div>
          <input
            type="text"
            class="search-input"
            id="stockSearch"
            placeholder="종목명을 입력하세요 (예: 삼성전자, SK하이닉스)"
            autocomplete="off"
          />
          <div class="search-suggestions" id="searchSuggestions">
            <!-- 검색 결과가 여기에 표시됩니다 -->
          </div>
        </div>
      </div>

      <!-- 종목 기본 정보 헤더 -->
      <div class="stock-header">
        <div class="stock-name-section">
          <div class="stock-name" id="stockName">---</div>
          <div class="stock-code">---</div>
        </div>
        <div class="current-price-section">
          <div class="current-price" id="currentPrice">---</div>
          <div class="price-change change-positive" id="priceChange">
            ---
          </div>
        </div>
      </div>

      <!-- 정보 카드 그리드 -->
      <div class="info-grid">
        <div class="info-card market-cap">
          <div class="info-left">
            <div class="info-icon">💰</div>
            <div class="info-details">
              <h3>시가총액</h3>
              <p id="marketCap">---</p>
            </div>
          </div>
        </div>

        <div class="info-card open-price">
          <div class="info-left">
            <div class="info-icon">🌅</div>
            <div class="info-details">
              <h3>시가</h3>
              <p id="openPrice">---</p>
            </div>
          </div>
        </div>

        <div class="info-card close-price">
          <div class="info-left">
            <div class="info-icon">🌆</div>
            <div class="info-details">
              <h3>종가</h3>
              <p id="closePrice">---</p>
            </div>
          </div>
        </div>

        <div class="info-card high-price">
          <div class="info-left">
            <div class="info-icon">📈</div>
            <div class="info-details">
              <h3>고가</h3>
              <p id="highPrice">---</p>
            </div>
          </div>
        </div>

        <div class="info-card low-price">
          <div class="info-left">
            <div class="info-icon">📉</div>
            <div class="info-details">
              <h3>저가</h3>
              <p id="lowPrice">---</p>
            </div>
          </div>
        </div>

        <div class="info-card current-time">
          <div class="info-left">
            <div class="info-icon">⏰</div>
            <div class="info-details">
              <h3>현재 시간</h3>
              <p id="currentTime">---</p>
            </div>
          </div>
        </div>
      </div>

      <!-- 마지막 업데이트 시간 -->
      <div class="last-updated">
        마지막 업데이트: <span id="lastUpdate">---</span>
      </div>
    </div>

    <%@ include file="footer.jsp" %>
  </body>
</html>