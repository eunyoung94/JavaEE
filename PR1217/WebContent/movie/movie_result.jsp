<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
//service메서드 영역 고양이가 이 jsp로부터 생성한 서블릿 클래스에는 각종 예외가 throw되어있다. 
	Connection con;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
</script>
</head>

<body>
당신이 선택한 영화에 대한 분석결과는?<p>
<%=session.getAttribute("msg") %>
<a href="movie_from.jsp">선택폼으로 다시돌아다기</a>
</body>
</html>