<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js" defer></script>
<script src="../js/test.js" defer></script>
</head>
<body>
	<button id="mainBtn">servlet에 요청보내기</button>
	<button id="jsonBtn">json서버에 요청보내기</button>
	<button id="stockInfoBtn">stockInfoServlet 서버에 요청보내기</button>
	<form action="/PutJsonServlet" method="post">
		바꾸고싶은 주식 아이디<input type="text" name="name" />
		<select name="likeState">
			<option value="false">즐찾 추가</option>		
			<option value="true">즐찾 해제</option>		
		</select>
		<input type="submit" value="요청 전송">
	</form>
	<%@include file="footer.jsp" %>	
</body>
</html>