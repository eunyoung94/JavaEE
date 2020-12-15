<%@page import="board.model.News"%>
<%@page import="board.model.Comments"%>
<%@page import="java.util.List"%>
<%@page import="board.model.CommentsDAO"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String news_id=request.getParameter("news_id");
	NewsDAO dao=new NewsDAO();
	News news = dao.select(Integer.parseInt(news_id));//데이터 한건 가져오기
	
	//이 게시물에 딸려있는 모든 코멘트 게시물 가져오기
	 CommentsDAO cdao=new CommentsDAO();
	List<Comments>list = cdao.selectAll(Integer.parseInt(news_id));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta>
<title>뉴스등록폼</title>
<style>
table{
	width:70%;
}
td{
	border:2px solid #cccccc;
}
td input,textarea{
	width:97%;
	font-size:1.2em;
}
textarea{
	height:150px;
}
input[name='msg']{
	width:70%;
}
input[name='author']{
	width:20%;
}
p{
	display:inline-block;
	background: pink;
}
.msg{
	width:70%;
}
.author{
	width:10%;
}
.cdate{
	width:18%;
}
</style>
<script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$($("button")[0]).click(function () {
	//글수정
});
$($("button")[1]).click(function () {//삭제하기 
	if(confirm("삭제하시겠습니까?")){
		del();
	}
});
$($("button")[2]).click(function () {//목록으로가기
	location.href="list.jsp";
});
$($("button")[3]).click(function () {//댓글등록
	reply();
});

function del(){
	// 삭제 버튼을 누를때 자식 코멘트가 존재한다면, 업데이트하자
	<%if(list.size()>0){%>
	$("form").attr({
		method:"post",
		action:"replace.jsp"
	});
	<%}else{%>
	//자식 코멘트가 없다면 그냥 삭제하기 
	$("form").attr({ 
		method:"post",
		action:"delete.jsp"
	});
	<%}%>
	$("form").submit();
}

function  regist() {
	$("form").attr({ 
		method:"post",
		action:"regist.jsp"
	});
	$("form").submit();	
}

function reply() {
	$("form").attr({ 
		method:"post",
		action:"reply.jsp"
	});
	$("form").submit();
}
</script>
</head>
<body>
	<form>
		<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
		<table align="center">
			<tr>
				<td><input type="text" name="writer" value="<%=news.getWriter()%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" value="<%=news.getTitle()%>"></td>
			</tr>
			<tr>
				<td><textarea name="content"><%=news.getContent() %></textarea></td>
			</tr>
			<tr>
				<td>
					<button type="button">수정</button>
					<button type="button">삭제</button>
					<button type="button">목록</button>
				</td>
			</tr>
			<tr>
				<td>
					<div style="background:yellow">
						<input type="text" placeholder="댓글을 입력하세요" name="msg">
						<input type="text" placeholder="작성자입력" name="author">
						<button>등록</button>
				</td>
			</tr>
			
			<!--댓글리스트영역 -->
			<tr>
				<td>
					<%for(Comments comments:list){%>
					<div>
						<p class="msg"><%=comments.getMsg()%></p>
						<p class="author"><%=comments.getAuthor() %></p>
						<p class="cdate"><%=comments.getCdate()%></p>
					</div>
					<%} %>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>