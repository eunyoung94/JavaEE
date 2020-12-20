/*
MVC패턴으로 개발하다보면 늘어나는 컨트롤러에 대해 일일이 매핑과정을 진행해야한다. 
이때 너무 많은 매핑은 관리하기가 까다롭다. 따라서 유지보수성이 떨어진다. 
현실과 유사하게, 어플리케이션에서도 대형 업무 처리시 클라이언트의 요청을 곧바로 해당 컨트롤러에게  처리하지 않게하고 
중간에 메인 컨트롤러를 두고서 모든 요청을 이 메인 컨트롤러에서 처리하여 적절한 하위 컨트롤에게 전담시키는 방식을 이용한다.
이 컨트롤러는 웹 어플리케이션의 모든 요청을 일차적으로 받는다.  
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
	Properties props; // 프로퍼티스 --->key와 value로 값을 가져올 수 있다.

	// init은 서블릿의 생명주기에서 최초 접속자에 의해 톰켓이 인스턴스를 생성하며, 이와 동시에 초기화 메서드로서 호출된다.
	// throws ServletException --> 예외처리를 전과하는것
	// ServletConfig와 ServletContext는 web.xml 문서를 조작하여 서블릿에 정보를 전달하기 위한 용도로 사용되는
	// 인터페이스 입니다

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();// 어플리케이션을 지칭
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
		request.setCharacterEncoding("utf-8"); // 파라미터에 대한 인코딩
		System.out.println("요청을 받았습니다");
		// 클라이언트가 영화를 원하면 -->영화담당 컨트롤러에게 전가하고
		// 클라이언트가 혈액형을 원하면 --> 혈액형담당 컨트롤러에게 전가

		// 2단계 요청을 분석하여 알맞는 컨트롤러에게 요청을 전달
		// 해답은 클라이어트 요청 자체에 있다. 요청시 사용된 uri가 곧 요청 구분값이다.
		String uri = request.getRequestURI();
		System.out.println("지금 들어온 요청은" + uri);
		Controller controller;
		String className = null;

		className = props.getProperty(uri);

		try {
			Class controllerClass = Class.forName(className);
			// 인스턴스 생성
			controller = (Controller) controllerClass.newInstance();
			controller.execute(request, response);// 다형적으로 실행된다
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

	// 서블릿의 생명주기 메서드 중 서블릿이 소멸 할 때 호출되는 메서드인 destory()에 스트림등의 자원을 닫는 처리를 하자
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
