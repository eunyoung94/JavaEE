/*
 삭제요청을 처리하는 컨트롤러의 정의
 */
package com.webApp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webApp1216.board.model.NoticeDAO;

public class DeleteServlet extends HttpServlet{
	NoticeDAO noticeDAO=new NoticeDAO();
	//삭제요청은? get,post? 둘다 가능하지만, 파라미터가notice_id밖에 없으므로, get으로 처리 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id=request.getParameter("notice_id");
		int result=noticeDAO.delete(Integer.parseInt(notice_id));
		//컨트롤러가 클라이언트가 보게될 메세지를 처리해야한다? 아니다?
		//alert()은 디자인..
		HttpSession session=request.getSession();
		
		if(result==0) {
			//실패페이지를 보여준다. 결국 에러에대한 안내를 보여주는 페이지를 별도로 두고, 그 페이지를 보여주는것 
			session.setAttribute("msg", "삭제실페 (에러코드 3003)");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/list");
		}
		
	}
}
