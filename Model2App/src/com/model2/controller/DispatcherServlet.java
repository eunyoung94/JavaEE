/*������ ��� Ŭ���̾�Ʈ�� ��û�� �ް�, ������ �����ϴ� ��Ʈ�ѷ� 
 * 
 */
package com.model2.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DispatcherServlet extends HttpServlet {
	FileReader fis;// ��Ʈ�ѷ� ���� ���������� �б����� ��Ʈ��
	JSONObject controllerMap; //��Ʈ�ѷ��� �������� ����ִ¸� 
	JSONObject viewMap;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);

		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			// �Ľ�
			JSONObject json = (JSONObject) jsonParser.parse(fis);

			// �����Ϳ� ����
			controllerMap = (JSONObject) json.get("controller");
			viewMap=(JSONObject)json.get("view");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//1�ܰ� doxxx�� �޼��� �����Ͽ�  ��û�� ���� 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 2�ܰ� ��û�� �м��Ѵ�
		String uri = request.getRequestURI();// Ŭ���̾�Ʈ�� ��û�� �����.uri��ü�� ��û�� ���а����� ���� �� �ִ�.
		// if���� ����� ����ȭ�� �����͸� ��������(xml,json,propertice) -->������ ����ȭ ��ǰ��:
		// xml,json,properties
		String controllerName = (String) controllerMap.get(uri);
		System.out.println("���� ���� ��û�� ó���� ��Ʈ�ѷ� Ŭ������" + controllerName);
		
		//���� ���� ��Ʈ�ѷ��� �̸��� �˾�����, �ν��Ͻ��� �����, execute(),getResultViewȣ�� ()
		Class controllerClass=null;
		Controller controller=null;		
		try {
			controllerClass=Class.forName(controllerName);//String, �� ���ڿ��� ������ Ŭ������ ���� ���� Ŭ������ ����
			controller=(Controller)controllerClass.newInstance();//�����¾ ������Ʈ�ѷ��� �ν��Ͻ������� 
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
		controller.execute(request, response);//3�ܰ� ���� 
		//���� ��Ʈ�ѷ��κ��� �Ѱܹ��� �信���� ������ �̿��Ͽ� Ŭ���̾�Ʈ���� �˸´� �並 �����ش�. 
		
		String resultKey=controller.getResultView();
		System.out.println("�����ѱ� Ű��"+resultKey);
		//���� ��Ʈ�ѷ��κ��� �Ѱܹ��� Ű���� �̿��ؼ� ���� �������� �˻��ϰ� , �� ����� �̿��ؼ� Ŭ���̾�Ʈ�� ���Ե� �������� ��������
		String viewPage=(String)viewMap.get(resultKey);//jsp��Ī ��ȯ
		//����� sendRedirect�� ó���ؾ��� ��찡 �ְ�, ���ۼ� �� ����Ʈ, ���� �ٸ� �������� �������� �õ��ϰ��Ҷ� ..
		//response.sendRedirect(viewPage);//�ϰ��δ�...
		if(controller.isForward()) {//Ŭ���̾�Ʈ�� �Ͽ��� ���Ӱ� ������ �õ��ϰ� �� ��� 
			RequestDispatcher dis=request.getRequestDispatcher(viewPage);
			dis.forward(request, response);//������̼������� �Ǵٸ� �ڿ����� ��û�� ����! 			
		}else {
			response.sendRedirect(viewPage);
		}
	}

	@Override
	public void destroy() {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
