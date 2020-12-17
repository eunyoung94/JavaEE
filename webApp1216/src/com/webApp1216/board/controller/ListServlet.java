/*
 JavaEE ���� ���� �� mvc������ ������ ���߹���� ������ model2����̶���Ѵ� .
 Ư�� jsp�� �����ο� ���ǰ� �����Ƿ�, ������ ��û�� �ް� ������ ������ ������  ��Ʈ�ѷ��μ� ������ �����ϰ� �ȴ�. 
 ����(servlet)?
����: ���� ������������ �ؼ� �� ����� �� �ִ� javaEE����� Ŭ���� 
�����ֱ�:init(),service(),destory()
init():������ ȣ�� ��, ������ �ʱ�ȭ �۾������� ���Ͽ� ���� ȣ��ȴ�. 
service():���ÿ� ���� Ŭ���̾�Ʈ�� ��û��ó���ϴ� �޼����̰�, ���ÿ� ȣ��Ƿ���
�����忡 ���� ȣ��ȴ�.
destory():���� �Ҹ������ ȣ�� , �ַ� ������ �����ߴ� �ڿ� ���� �ݳ��ϴ� �뵵�� ����Ѵ�.

-������ �躸 
:HttpServlet(�߻�Ŭ����)->GenericServlet(�߻�Ŭ����) ->Servlet(�������̽�)

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

//Ŭ���̾�Ʈ�� ��Ͽ�û�� ó���ϴ� ���� ���� 
public class ListServlet extends HttpServlet{
	NoticeDAO noticeDAO=new NoticeDAO();// ��������� ���� �������ش� dao�� �����Ҷ����� �߻��ϸ� �޸𸮸� ���� ��ƸԾ������..
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list=noticeDAO.selectAll();
		//���� ������ ���?
		//����? Ŭ���̾�Ʈ�� ������ ���μ����� ���� �ʰų� ���� ���۳��� �������������� ������ �޸𸮿� ����� ������ ����� �� �ִ� ��� (���ο� ������ ��� ���ǰ�ü�� ���� �����ǰ�, ���Ǿ��̵� ���Ӱ� �߰ߵȴ�.)
		//jsp������ session���尴ü�� �ڷ����� HttpSession�̴� 
		
		HttpSession session=request.getSession(); //�� ��û�� ������ ������ ��´� 
		session.setAttribute("noticeList", list);//���ǰ�ü�� ��������  ("���� �����̸�", ������)
		
		//��� ������ ����
		response.sendRedirect("/board/list.jsp");
	}
	
}
