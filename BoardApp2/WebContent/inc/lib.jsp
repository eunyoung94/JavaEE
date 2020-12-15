<%@ page contentType="text/html;charset=utf-8"%>
<%!

//앞으로 재사용성이 높은 jsp 코드를 이 파일에 메서드로 모아놓겠다
public String getMsgBack(String msg){ //욕하고, 이전페이지 보여주는것 
	StringBuilder sb = new StringBuilder();
	sb.append("<script>");
	sb.append("alert('"+msg+"');");
	sb.append("history.back();");
	sb.append("</script>");
	return sb.toString();
}
///board/detail.jsp?notice_id="+notice_id+" 매개변수로 빼야함..
public String getMsgURL(String msg ,String url){//욕하고, 원하는 페이지 요청 
	StringBuilder sb = new StringBuilder();
	sb.append("<script>");
	sb.append("alert('"+msg+"');");
	sb.append("location.href='"+url+"';");
	sb.append("</script>");
	return sb.toString();
}
%>