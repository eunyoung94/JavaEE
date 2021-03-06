<%@page import="board.model.BoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="board.model.MybatisBoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%

//넘겨받은 board_id를 이용해서 글 한건을 삭제하자 
String board_id =request.getParameter("board_id");
String filename = request.getParameter("filename");
//MybatisBoardDAO dao = new MybatisBoardDAO();
BoardDAO dao = new BoardDAO();

//이미지를 삭제하고 db레코드를 지우기 
String path=application.getRealPath("/data");

if(FileManager.deleteFile(path+"/"+filename)){
	int result=dao.delete(Integer.parseInt(board_id));
	if(result==0){
		out.print(getMsgBack("게시물 삭제실패"));
	}else{
		out.print(getMsgURL("삭제성공","list.jsp"));
	}
}else{
	out.print(getMsgBack("파일삭제불가"));
}
%>

