<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="db.DBManager"%>
<%@ include file ="/inc/lib.jsp" %>
<%
request.setCharacterEncoding("utf-8");
String qna_id=request.getParameter("qna_id");
QnADAO qnaDAO= new QnADAO();
int result = qnaDAO.delete(Integer.parseInt(qna_id));
if(result==0){
	out.print(getMsgBack("삭제실패"));
}else{
	out.print(getMsgURL("삭제성공","/qna/list.jsp"));
}
%>