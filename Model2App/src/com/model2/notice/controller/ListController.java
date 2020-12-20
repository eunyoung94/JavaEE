/*
 �����Խ����� ��� ��û�� ó���ϴ� ������Ʈ�ѷ�
 */

package com.model2.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;
import com.model2.notice.model.NoticeDAO;

public class ListController implements Controller{
	NoticeDAO noticeDAO=new NoticeDAO();
	
	//3�ܰ�: �˸´� ���� ��ü���� ���� ��Ų��
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list=noticeDAO.selectAll();
		
		//4�ܰ� : Ŭ���̾�Ʈ�� ������ ����� �ִٸ� , ����� ����(�׷��� DispatcherServlet ��Ʈ�ѷ��� ����� �� �ִ�. )
		HttpSession session= request.getSession();
		request.setAttribute("noticeList", list);
	}

	public String getResultView() {
		return "/view/notice/list";
	}

	public boolean isForward() {
		return true;
	}

}