<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	request.setCharacterEncoding("utf-8");
	Object obj1 = request.getAttribute("dividendCalc");
	Object obj2 = request.getAttribute("dividendForThisYear");

%>

예상 배당금: <%=obj1 %><br><br>
--배당금 지급 현황--<br><%=obj2 %>

</body>
</html>