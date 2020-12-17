<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%

InitialContext context=new InitialContext();
DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/mysql");
out.print(ds.getConnection());

%>