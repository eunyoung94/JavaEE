/*
MVC�������� �����ϴٺ��� �þ�� ��Ʈ�ѷ��� ���� ������ ���ΰ����� �����ؾ��Ѵ�. 
�̶� �ʹ� ���� ������ �����ϱⰡ ��ٷӴ�. ���� ������������ ��������. 
���ǰ� �����ϰ�, ���ø����̼ǿ����� ���� ���� ó���� Ŭ���̾�Ʈ�� ��û�� ��ٷ� �ش� ��Ʈ�ѷ�����  ó������ �ʰ��ϰ� 
�߰��� ���� ��Ʈ�ѷ��� �ΰ� ��� ��û�� �� ���� ��Ʈ�ѷ����� ó���Ͽ� ������ ���� ��Ʈ�ѿ��� �����Ű�� ����� �̿��Ѵ�.
�� ��Ʈ�ѷ��� �� ���ø����̼��� ��� ��û�� ���������� �޴´�.  
*/
package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

	FileInputStream fis;
	Properties props; // ������Ƽ�� --->key�� value�� ���� ������ �� �ִ�.

	// init�� ������ �����ֱ⿡�� ���� �����ڿ� ���� ������ �ν��Ͻ��� �����ϸ�, �̿� ���ÿ� �ʱ�ȭ �޼���μ� ȣ��ȴ�.
	// throws ServletException --> ����ó���� �����ϴ°�
	// ServletConfig�� ServletContext�� web.xml ������ �����Ͽ� ������ ������ �����ϱ� ���� �뵵�� ���Ǵ�
	// �������̽� �Դϴ�

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();// ���ø����̼��� ��Ī
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		System.out.println(savePath);

		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // �Ķ���Ϳ� ���� ���ڵ�
		System.out.println("��û�� �޾ҽ��ϴ�");
		// Ŭ���̾�Ʈ�� ��ȭ�� ���ϸ� -->��ȭ��� ��Ʈ�ѷ����� �����ϰ�
		// Ŭ���̾�Ʈ�� �������� ���ϸ� --> ��������� ��Ʈ�ѷ����� ����

		// 2�ܰ� ��û�� �м��Ͽ� �˸´� ��Ʈ�ѷ����� ��û�� ����
		// �ش��� Ŭ���̾�Ʈ ��û ��ü�� �ִ�. ��û�� ���� uri�� �� ��û ���а��̴�.
		String uri = request.getRequestURI();
		System.out.println("���� ���� ��û��" + uri);
		Controller controller;
		String className = null;

		className = props.getProperty(uri);

		try {
			Class controllerClass = Class.forName(className);
			// �ν��Ͻ� ����
			controller = (Controller) controllerClass.newInstance();
			controller.execute(request, response);// ���������� ����ȴ�
			response.sendRedirect(controller.getViewPage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ������ �����ֱ� �޼��� �� ������ �Ҹ� �� �� ȣ��Ǵ� �޼����� destory()�� ��Ʈ������ �ڿ��� �ݴ� ó���� ����
	@Override
	public void destroy() {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
