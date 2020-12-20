<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
function send(){
	var form=document.querySelector("form");
	form.action="/blood.do";
	form.method="post";
	form.submit();//전송
}
</script>

</head>

<body>
	<form>
		<select name="blood">
			<option>혈액형을 선택하세요</option>
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="O">O</option>
			<option value="AB">AB</option>			
		</select>
	<button type="button" onClick="send()">분석보기</button>
	</form>
</body>
</html>