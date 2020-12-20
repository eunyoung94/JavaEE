/*웹상의 모든 클라이언트의 요청을 받고, 응답을 전담하는 컨트롤러 
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
	FileReader fis;// 컨트롤러 매핑 설정파일을 읽기위한 스트림
	JSONObject controllerMap; //컨트롤러의 정보들이 들어있는맵 
	JSONObject viewMap;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);

		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			// 파싱
			JSONObject json = (JSONObject) jsonParser.parse(fis);

			// 데이터에 접근
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
//1단계 doxxx형 메서드 정의하여  요청을 받자 

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
		// 2단계 요청을 분석한다
		String uri = request.getRequestURI();// 클라이언트가 요청시 사용한.uri자체가 요청의 구분값으로 사용될 수 있다.
		// if문을 대신할 구조화된 데이터를 선택하자(xml,json,propertice) -->데이터 구조화 제품명:
		// xml,json,properties
		String controllerName = (String) controllerMap.get(uri);
		System.out.println("지금 들어온 요청을 처리할 컨트롤러 클래스는" + controllerName);
		
		//동생 하위 컨트롤러의 이름을 알았으니, 인스턴스를 만들고, execute(),getResultView호출 ()
		Class controllerClass=null;
		Controller controller=null;		
		try {
			controllerClass=Class.forName(controllerName);//String, 즉 문자열로 지정한 클래스에 대한 실제 클래스를 반한
			controller=(Controller)controllerClass.newInstance();//동생태어남 하위컨트롤러의 인스턴스생성시 
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
		controller.execute(request, response);//3단계 업무 
		//하위 컨트롤러로부터 넘겨받은 뷰에대한 정보를 이용하여 클라이언트에게 알맞는 뷰를 보여준다. 
		
		String resultKey=controller.getResultView();
		System.out.println("동생넘긴 키값"+resultKey);
		//동생 컨트롤러로부터 넘겨받은 키값을 이용해서 실제 페이지를 검색하고 , 그 결과를 이용해서 클라이언트가 보게될 페이지를 보여주자
		String viewPage=(String)viewMap.get(resultKey);//jsp명칭 반환
		//응답시 sendRedirect로 처리해야할 경우가 있고, 글작성 후 리스트, 전혀 다른 페이지로 재접속을 시도하게할때 ..
		//response.sendRedirect(viewPage);//믿고까부는...
		if(controller.isForward()) {//클라이언트로 하여금 새롭게 접속을 시도하게 할 경우 
			RequestDispatcher dis=request.getRequestDispatcher(viewPage);
			dis.forward(request, response);//응답없이서버상의 또다른 자원으로 요청을 전달! 			
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
