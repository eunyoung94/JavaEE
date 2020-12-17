<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
//service메서드 영역 고양이가 d 이 jsp로부터 생성한 서블릿 클래스에는 각종 예외가 throws가 되어있다. 
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
당신이 선택한 영화에 대한 분석결과 <p>
<%=session.getAttribute("msg") %>
<a href="movie_form.jsp">선택폼으로가기</a>
</body>
</html>