/*
 JavaEE 개발 패턴 중 mvc패턴을 적용한 개발방법을 가리켜 model2방식이라고한다 .
 특히 jsp가 디자인에 사용되고 있으므로, 웹상의 요청을 받고 응답이 가능한 서블릿이  컨트롤러로서 역할을 수행하게 된다. 
 서블릿(servlet)?
정의: 오직 웹서버에서만 해석 및 실행될 수 있는 javaEE기반의 클래스 
생명주기:init(),service(),destory()
init():생성자 호출 후, 서블릿의 초기화 작업때문에 톰켓에 의해 호출된다. 
service():동시에 많은 클라이언트의 요청을처리하는 메서드이고, 동시에 호출되려면
쓰레드에 의해 호출된다.
destory():서블릿 소멸시점에 호출 , 주로 서블릿이 보유했던 자원 등을 반납하는 용도에 사용한다.

-서블릿의 계보 
:HttpServlet(추상클래스)->GenericServlet(추상클래스) ->Servlet(인터페이스)

 */
package com.webApp1216.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webApp1216.board.model.NoticeDAO;

//클라이언트의 목록요청을 처리하는 서블릿 정의 
public class ListServlet extends HttpServlet{
	NoticeDAO noticeDAO=new NoticeDAO();// 멤버변수로 먼저 선언해준다 dao가 실행할때마다 발생하면 메모리를 많이 잡아먹어버리니..
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list=noticeDAO.selectAll();
		//뭔가 저장할 방법?
		//세션? 클라이언트가 브라우저 프로세스를 닫지 않거나 일정 시작내에 재접속했을때에 서버측 메모리에 담겨진 정보를 사용할 수 있는 기술 (새로운 접속인 경우 세션객체를 새로 생성되고, 세션아이디가 새롭게 발견된다.)
		//jsp에서의 session내장객체는 자료형이 HttpSession이다 
		
		HttpSession session=request.getSession(); //이 요청과 관련한 세션을 얻는다 
		session.setAttribute("noticeList", list);//세션객체에 보관하자  ("내가 정한이름", 담을것)
		
		//결과 페이지 선택
		response.sendRedirect("/board/list.jsp");
	}
	
}
