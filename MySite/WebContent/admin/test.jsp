<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="charset=utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
</script>
</head>

<body>
<% 
/*
jsp의 내장객체 
request, response, out,
*/
String id= session.getId();
out.print(id);
%>
</body>
</html>