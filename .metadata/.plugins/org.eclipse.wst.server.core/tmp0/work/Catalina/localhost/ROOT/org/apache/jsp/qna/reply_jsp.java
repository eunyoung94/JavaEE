/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.61
 * Generated at: 2020-12-13 12:11:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.qna;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import board.model.QnA;
import board.model.QnADAO;

public final class reply_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {



//앞으로 재사용성이 높은 jsp 코드를 이 파일에 메서드로 모아놓겠다
public String getMsgBack(String msg){ //욕하고, 이전페이지 보여주는것 
	StringBuilder sb = new StringBuilder();
	sb.append("<script>");
	sb.append("alert('"+msg+"');");
	sb.append("history.back();");
	sb.append("</script>");
	return sb.toString();
}
///board/detail.jsp?notice_id="+notice_id+" 매개변수로 빼야함..
public String getMsgURL(String msg ,String url){//욕하고, 원하는 페이지 요청 
	StringBuilder sb = new StringBuilder();
	sb.append("<script>");
	sb.append("alert('"+msg+"');");
	sb.append("location.href='"+url+"';");
	sb.append("</script>");
	return sb.toString();
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/inc/lib.jsp", Long.valueOf(1606970342000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("board.model.QnADAO");
    _jspx_imports_classes.add("board.model.QnA");
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
      response.setContentType("text/html;charset=utf-8");
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
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');

//넘겨받은 파라미터 값을 이용하여 답글달기! 
//qna테이블의 rank는 모두 업데이트 되라, 단 내가본글 team내에서, 내본글의 rank보다 값이 컸던애들만 +1
// insert qna(~~team,rank,depth) values(내본team,내본rank+1,내본depth+1)

//넘겨받은것들, 
request.setCharacterEncoding("utf-8");
String writer = request.getParameter("writer");
String title = request.getParameter("title");
String content = request.getParameter("content");
String team=request.getParameter("team"); //내본글 team
String rank=request.getParameter("rank"); //내본글 rank
String depth=request.getParameter("depth"); //내본글 depth

//넘겨받은걸 vo에 담기 
QnA qna = new QnA();

qna.setWriter(writer);
qna.setTitle(title);
qna.setContent(content);
qna.setTeam(Integer.parseInt(team));
qna.setRank(Integer.parseInt(rank));
qna.setDepth(Integer.parseInt(depth));

//DAO에서 수행할거지만, 일단 이해를 위해!
//1단계 : 후발로 등록된 글이 들어갈 자리확보(기존 글을 밀어내는효과)
/*
String sql="update qna set rank = rank+1 where team="+team+"and rank >" +rank;
out.print(sql);

//2단계 : 내가 본 글의 바로 아래쪽에 답변 insert 
sql="insert into qna(team,rank,depth) vales("+team+","+(rank+1)+","+(depth+1)+")";
out.print(sql);
*/

QnADAO dao =new QnADAO();
int result = dao.reply(qna);

if(result==0){
	out.print(getMsgBack("답변등록실패"));
}else{
	out.print(getMsgURL("답변등록성공", "/qna/list.jsp"));
}


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
