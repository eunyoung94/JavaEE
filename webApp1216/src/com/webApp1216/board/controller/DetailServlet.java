/*
 �� �Ѱ��� ��ûó���ϴ� ��Ʈ�ѷ�  
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
		HttpSession session=request.getSession();//��û�� ���õ� ������ ��� 
		session.setAttribute("notice", notice); //("�����������̸�",��) ���ǿ� ��Ƶα�
		//Ŭ���̾�Ʈ�� �������� �ص�, ���ǿ� ��Ƴ����� notice�� ���ؼ� ������ �����ϴ�
		
		response.sendRedirect("/board/detail.jsp");
	}
}
