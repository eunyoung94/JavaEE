/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.61
 * Generated at: 2020-12-13 12:19:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.gallery2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import common.FileManager;
import org.apache.commons.fileupload.FileItem;
import java.util.List;
import java.io.File;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.DefaultFileItemFactory;

public final class upload_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("common.FileManager");
    _jspx_imports_classes.add("org.apache.commons.fileupload.FileItem");
    _jspx_imports_classes.add("org.apache.commons.fileupload.servlet.ServletFileUpload");
    _jspx_imports_classes.add("java.io.File");
    _jspx_imports_classes.add("org.apache.commons.fileupload.DefaultFileItemFactory");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	//파일 업로드 컴포넌트의 종류엔 여러가지가 있지만, 그 중 아파치의 공식 업로드 컴포넌트를 사용해본다
//업로드 객체를 생성해주는 팩토리 객체: 주로 설정을 담당한다.(서버의 저장경로, 파일의 용량제한)

String saveDir = "D:/workspace/javaee_workspace/BoardApp2/WebContent/data";
int maxSize = 2 * 1024 * 1024;//2M byte로 제한
DefaultFileItemFactory itemFactory = new DefaultFileItemFactory();
itemFactory.setRepository(new File(saveDir));//저장될 위치
itemFactory.setSizeThreshold(maxSize);//용량을 지정한 크기로제한 
//이 객체가 실제 업로드를 수행한다. 
ServletFileUpload upload = new ServletFileUpload(itemFactory);//설정정보를 생성자의 인수로 전달! 
// FileItem은 클라이언트의 전송정보 하나하나를 의미한다. 즉, html에서의 input 텍스트박스, file 컴포넌트들..
// 우리의 경우 input type ="file"이 FileItem에담기고
// 우리의 경우 input type ="text"이 FileItem에담기고
request.setCharacterEncoding("utf-8");

List<FileItem> items = upload.parseRequest(request);//업로드컴포넌트에게 클라이언트의 요청정보를 전달한다. 

for (FileItem item : items) {
	//반복문으로 처리되다보니, 파일만 따로 처리를 하려면 구분로직이 필요하다. 
	out.print(item.getFieldName()+"은텍스트박스여부"+item.isFormField()+"<br>");
	if (!item.isFormField()) {//텍스트박스가 아닌것만 업로드 처리
		//out.print(item.getFieldName() + "의값은" + item.getFieldName() + "<br>");
		//업로드 처리하자! 메모리상의 이미지 정보를 실제 물리적 파일로 저장하자!
		out.print("당신이 업로드한 파일의 원래이름은?"+item.getName());
		String ext = FileManager.getExtend(item.getName());//확장자구하기
		String filename= System.currentTimeMillis()+"."+ext;
		File file =new File(saveDir+"/"+filename);//비어있는 파일을 만들되, 이 파일에 경로를 만들어주자..
		item.write(file);//저장정보를 File클래스의 인스턴스로 전달! 
		
		out.print("보고서작성<br>");
		out.print("원래파일명"+item.getName()+"<br>");
		out.print("생성된 파일명"+filename+"<br>");
		out.print("저장된위치"+saveDir+"<br>");
		out.print("업로드 파일크기"+item.getSize()+" bytes <br>");
		
	}
}

      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
