<%@ page contentType="text/html;charset=utf-8"%>

<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@page import="common.FileManager"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>

<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ include file="/inc/lib.jsp" %>

<%!
	String saveDir="D:/workspace/javaee_workspace/BoardApp/WebContent/data"; //받은 데이터를 저장할곳 
	
	int maxSize = 3*1024*1024; //3M byte
	ImageBoardDAO dao=new ImageBoardDAO();
%>
<%
//실습했던 예제보다 기능이 더 추가된다. db를 넣어주자 DAO를 이용하자 
//업로드 컴포넌트에 대한 설정을 하기위해서 FileItemFactory객체를 이용해야한다. 
DiskFileItemFactory itemFactory = new DiskFileItemFactory();
itemFactory.setRepository(new File(saveDir));//Repository:저장소 
itemFactory.setSizeThreshold(maxSize); //sizeThreshold -->사이즈 문지방을 넘지마라! 
itemFactory.setDefaultCharset("utf-8");

ServletFileUpload upload=new ServletFileUpload(itemFactory) ;

//업로드된 정보 분석하기! 
request.setCharacterEncoding("utf-8"); 
List<FileItem> items=upload.parseRequest(request);//업로드된 정보를 분석하자! 각각의 컴포넌트들을 FileItem단위로 쪼갠다. 

ImageBoard board= new ImageBoard(); //Empty상태에서 VO를 생성

for(FileItem item:items){// List에 담긴 items의 길이만큼 반복한다. 
	if(item.isFormField()){ //textfield라면 db에 넣자 item이 isFormField-->일반 파라미터면 true를 반환함../ 아이템이 파라미터면!
		if(item.getFieldName().equals("author")){ //필드명이 author이라면
			board.setAuthor(item.getString()); //보드vo에 새롭게 넣어주자! 
		}else if(item.getFieldName().equals("title")){ //필드명이 title이라면
			board.setTitle(item.getString());
		}else if(item.getFieldName().equals("content")){//필드명이content이라면
			board.setContent(item.getString());
		}
	}else{ //textfield가 아니라면 업로드처리하자
		String newName=System.currentTimeMillis()+"."+FileManager.getExtend(item.getName());// 아이템에 저장되어 있는 파일의 이름을 반환한여서 filemanager에 있는 getExtend의 메서드에 넣어줘서 조져주자.. 리턴값('.' 이후의 path==확장자명)을 받아야지  
		String destFile= saveDir+"/"+newName;
		File file = new File(destFile);
		item.write(file);///물리적 저장시점 
		out.print("업로드완료");
		board.setFilename(newName);//vo에 파일명 값을 담자
	}
}
//반복문을 지나친 이 시점에는 VO에 데이터가 이미 채워진 상태일것이다. 
int result = dao.insert(board); //이시점에는 채워진 VO를 원한다. 
if(result==0){
	out.print(getMsgBack("등록실패"));	
}else{
	out.print(getMsgURL("등록성공","/imageboard/list.jsp"));
}
%>