<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
	function send() {
		var form = document.querySelector("form");
		form.action = "/movie.do";
		form.method = "get";
		form.submit();//전송
	}
</script>

</head>

<body>
	<form>
		<select name="movie">
			<option>영화를 선택하세요</option>
			<option value="토르">토르</option>
			<option value="킹덤">킹덤</option>
			<option value="아바타">아바타</option>
			<option value="러브미">러브미</option>
		</select>
		<button type="button" onClick="send()">분석보기</button>
	</form>
</body>
</html>