<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="../css/style.css" />
  </head>
  <body>
    <%@include file="header.jsp" %>
    <main id="mainContainer">
      <div id="mainDiv">
        <div>
          <input type="text" name="companyName" placeholder="원하는 회사를 입력하세요!" />
          <button>클릭</button>
        </div>
        <div id="month"></div>
        <div id="calendar"></div>
      </div>
    </main>
    <script src="../js/calendar.js" defer></script>
    <%@include file="footer.jsp" %>
  </body>
</html>
    