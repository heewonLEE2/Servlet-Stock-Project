<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>배당락일 캘린더</title>
    <link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../css/headerFooter.css" />
    <link rel="stylesheet" href="../css/stockCalendar.css" />
    <script src="../js/stockCalendar.js" defer></script>
    <script src="../js/header.js" defer></script>
    <script src="../js/stockDividendDate.js" defer></script>
    <script src="../js/stockCalendarList.js" defer></script>
    <script src="../js/stockYearFilter.js" defer></script>
    <script src="../js/stockMoveCalendar.js" defer></script>
  </head>
  <body>
    <%@include file="header.jsp" %>
    <main id="mainContainer">
      <div id="mainDiv">
        <div id="wrap">
          <div id="searchContainer">
            <img id="search" src="/css/assets/search.png" alt="search">
            <input id="search-input" type="text" name="itmsName" placeholder="원하는 회사를 입력하세요! ex)삼성전자" />
          </div>
          <div id="resultContainer">
            <div id="calendar-container">
              <div id="calendar-title">
                <div class="arrow">
                  <img src="/css/assets/left-arrow.png" alt="arrow">
                </div>
                <div class="date"></div>
                <div class="arrow">
                  <img src="/css/assets/right-arrow.png" alt="arrow">
                </div>
              </div>
              <div id="calendar"></div>
            </div>
            <div id="dividendList">
              <div id="yearSelectContainer">
                <select name="yearSelect" id="yearSelect"></select>
              </div>
              <div id="dividendList-result-container">
                <div id="dividendList-title">
                  <img src="/css/assets/calendar.png" alt="calendar-icon"/>
                  <p>배당락일</p>
                </div>
                <div id="dividendList-list"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <%@include file="footer.jsp" %>
  </body>
</html>
    