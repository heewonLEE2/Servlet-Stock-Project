<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>로고 - 주식 정보</title>
    <link rel="stylesheet" href="../css/stockList.css" />
    <link rel="stylesheet" href="../css/headerFooter.css">
    <script src="../js/stockList.js" defer></script>
    <script src="../js/header.js" defer></script>
  </head>
  <body>
  	<%@ include file="header.jsp" %>
    <div class="container">
      <div class="table-container">
        <div class="table-wrapper">
          <table id="stockTable">
            <thead>
              <tr>
                <th class="sortable">종목명</th>
                <th class="sortable">업종</th>
                <th class="sortable">상장주식수(주)</th>
                <th class="sortable">즐겨찾기 여부</th>
                <th class="sortable">보유 주식수</th>
              </tr>
            </thead>
            <tbody id="stockTableBody">
              <!-- 데이터가 여기에 삽입 -->
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <%@ include file="footer.jsp" %>
  </body>
</html>