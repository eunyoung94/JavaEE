<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="common.board.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
<%@include file="/inc/lib.jsp" %>

<%!String saveDir = "D:/workspace/javaee_workspace/BoardApp2/WebContent/data";//받은 데이터를 저장할 곳 !
	int maxSize = 3 * 1024 * 1024;//3m byte
	ImageBoardDAO dao = new ImageBoardDAO();%>
<%
//실습했던 예제보다 기능을 더 추가해서 db에 넣어줄수 있게 dao를 이용해보자 
//업로드 컴포넌트에 대한 설정을 하기 위해서 FileItemFactory객체를 이용해보자 
DiskFileItemFactory itemFactory = new DiskFileItemFactory();
itemFactory.setRepository(new File(saveDir)); //Repository:저장소, 저장소 경로를 설정해준다.
itemFactory.setSizeThreshold(maxSize);//sizeThreshold -->사이즈 문지방을 넘지말자 
itemFactory.setDefaultCharset("utf-8");

ServletFileUpload upload= new ServletFileUpload(itemFactory);
//업로드된 정보를 분석해보자 
request.setCharacterEncoding("utf-8");
List<FileItem> items=upload.parseRequest(request);//업로드된 정보를 분석하자 
//,각 컴포넌트들을 Fileitem 단위로 쪼개자 
ImageBoard board=new ImageBoard();//empty상태에서 vo를 생성

for(FileItem item:items){ //List에 담긴 items의 길이만큼 반복해보자 
	if(item.isFormField()){ //isFormField는 폼필드 항목인지 검사 --> 폼필드 항목이 맞으면 true값, item이 파라미터면(텍스트파일)! 
		if(item.getFieldName().equals("author")){//필드명이 author이면
			board.setAuthor(item.getString());//보드 vo에 새롭게 넣어주자
		}else if(item.getFieldName().equals("title")){ //필드명이 title이면
			board.setTitle(item.getString());
		}else if(item.getFieldName().equals("content")){
			board.setContent(item.getString());
		}

	}else{//textfield가 아니라면 업로드하자!
		String newName=System.currentTimeMillis()+"."+FileManager.getExtend(item.getName());
		//아이템에 저장되어 있는 파일의 이름을 반환하여서 filemanager에 있는 getExtend의 메서드에 넣어줘서 조져주자! 리턴값 ('.'이후의 path==확장자명)을 받아와야지
		String destFile=saveDir+"/"+newName;
		File file = new File(destFile);
		item.write(file);//물리적 저장시점 
		out.print("업로드완료");
		board.setFilename(newName);//vo에 파일명값을 담자
	}
}
//반복문을 지나친 이 시점에는 VO에 데이터가 이미 채워진 상태일것이다. 
int result=dao.insert(board);//이 시점에서는 채워진 VO를 원한다 
if(result==0){
	out.print(getMsgBack("등록실패"));
}else{
	out.print(getMsgURL("등록성공", "/imageboard/list.jsp"));
}
%>