<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>
<%@ include file="/inc/lib.jsp"%>

<%
	request.setCharacterEncoding("utf-8"); // 받기전에 처리하자!
	
	String notice_id = request.getParameter("notice_id");
	NoticeDAO noticeDAO = new NoticeDAO();
	int result = noticeDAO.delete(Integer.parseInt(notice_id));
	if(result==0){
		out.print(getMsgBack("삭제실패"));
	}else{
		out.print(getMsgURL("삭제성공","/board/list.jsp"));
	}	
	
%>