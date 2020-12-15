<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
//세션에서 데이터 꺼내기 
out.print("당신이 사용하고있는 세션객체는"+session); //웹창에 드고
Admin admin=(Admin)session.getAttribute("ad");//오브젝트로 들어가기때문에 어드민으로 형변환하자

//만일 admin이라는 VO가 null이면 --> 인증을 거치지 않거나, 세션이 만료된 상황이다. 현재 페이지에 대한 접근자체를 막아야한다. 
if(admin!=null){
	%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@ include file="/admin/inc/head.jsp" %>
	</head>
	<body>
	<div>
	<%=admin.getMid()%>님 로그인중
	<a href="/admin/logout.jsp">로그아웃</a>
	</div>
	<%@ include file="/admin/inc/topnavi.jsp" %>
	<div style="padding-left:16px">
	  <h2>Top Navigation Example</h2>
	  <p>Some content..</p>
	</div>
	
	</body>
	</html>
<%}else{%>
	<script>
	alert("올바르지않은 접근입니다.");
	history.back;
	</script>
<%}%>