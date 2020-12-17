/*
 글 한건을 요청처리하는 컨트롤러  
 */

package com.webApp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webApp1216.board.model.Notice;
import com.webApp1216.board.model.NoticeDAO;

public class DetailServlet extends HttpServlet{
	NoticeDAO noticeDAO=new NoticeDAO();
	//get! 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id=request.getParameter("notice_id");
		
		Notice notice=noticeDAO.select(Integer.parseInt(notice_id));
		HttpSession session=request.getSession();//요청과 관련된 세션을 얻고 
		session.setAttribute("notice", notice); //("내가지정한이름",값) 세션에 담아두기
		//클라이언트가 재접속을 해도, 세션에 담아놓으면 notice에 대해서 참조가 가능하다
		
		response.sendRedirect("/board/detail.jsp");
	}
}
