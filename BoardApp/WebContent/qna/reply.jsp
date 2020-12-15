<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
//넘겨받은 파라미터 값을 이용하여 답글달기! 
//qna테이블의 rank는 모두 업데이트 되라, 단 내가본글 team내에서, 내본글의 rank보다 값이 컸던애들만 +1
// insert qna(~~team,rank,depth) values(내본team,내본rank+1,내본depth+1)

//넘겨받은것들, 
request.setCharacterEncoding("utf-8");
String writer = request.getParameter("writer");
String title = request.getParameter("title");
String content = request.getParameter("content");
String team=request.getParameter("team"); //내본글 team
String rank=request.getParameter("rank"); //내본글 rank
String depth=request.getParameter("depth"); //내본글 depth

//넘겨받은걸 vo에 담기 
QnA qna = new QnA();

qna.setWriter(writer);
qna.setTitle(title);
qna.setContent(content);
qna.setTeam(Integer.parseInt(team));
qna.setRank(Integer.parseInt(rank));
qna.setDepth(Integer.parseInt(depth));

//DAO에서 수행할거지만, 일단 이해를 위해!
//1단계 : 후발로 등록된 글이 들어갈 자리확보(기존 글을 밀어내는효과)
/*
String sql="update qna set rank = rank+1 where team="+team+"and rank >" +rank;
out.print(sql);

//2단계 : 내가 본 글의 바로 아래쪽에 답변 insert 
sql="insert into qna(team,rank,depth) vales("+team+","+(rank+1)+","+(depth+1)+")";
out.print(sql);
*/

QnADAO dao =new QnADAO();
int result = dao.reply(qna);

if(result==0){
	out.print(getMsgBack("답변등록실패"));
}else{
	out.print(getMsgURL("답변등록성공", "/qna/list.jsp"));
}

%>