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
  </head>
  <body>
    <%@include file="header.jsp" %>
    <main id="mainContainer">
      <div id="mainDiv">
        <div>
          <input type="text" name="companyName" placeholder="원하는 회사를 입력하세요!" />
          <button>클릭</button>
        </div>
        <div id="resultContainer">
	        <div id="calendar"></div>
	        <div id="dividendList"></div>
        </div>
      </div>
    </main>
    <%@include file="footer.jsp" %>
  </body>
</html>
    