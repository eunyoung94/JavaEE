/*
 jsp������ �����ߴ� ��Ŀ��� regist.jsp�� ����ϴ� ������ �������� �����غ��� 
 */

package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.model.Board;
import board.model.BoardDAO;
import common.file.FileManager;

public class RegistServlet extends HttpServlet{
	BoardDAO boardDAO=new BoardDAO();
	
	//Ŭ���̾�Ʈ�� get��û: goGet(), post��û doPost() //regist_form���� �Էµ� text�� �����°��̴� Post! 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		//��� ��Ʈ�� �̾Ƴ��� 
		response.setContentType("text/html;charset=utf-8");// <%@page contentType="text/html;charset=utf-8"%> �̶� ����
		PrintWriter out =response.getWriter();
		//Ŭ���̾�Ʈ�� ��û�� multipart/form-data����� ���Ե� ��� �ؽ�Ʈ�� �ƴ� ���̳ʸ� ���Ϸ� ���۵ȴ�. ���� ���ε� ������Ʈ�� ����ؾ��Ѵ�. 
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletContext context=request.getServletContext();
		//���ø����̼ǰ� ���õ� ������ ��� ��ü�� ServletContext�� ���� 
		//ServletContext�� jsp������ application���尴ü�� �����ؾ��Ѵ�. 
		String saveDir=context.getRealPath("/data");
		factory.setRepository(new File(saveDir));
		factory.setSizeThreshold(3*1024*1024);//ũ������
		factory.setDefaultCharset("utf-8");//���ڵ� 
		
		ServletFileUpload upload=new ServletFileUpload(factory);
		//���ε�� ���� �м�����!(�Ľ��ϱ�)
		//���ε�� ������Ʈ���� item����Ʈ�� �޾Ƴ���
		try {
			List<FileItem> items=upload.parseRequest(request);
			Board board = new Board();//����ִ� VO�����ϱ� 
			boolean flag = false;//���Ͼ��ε忡 ���� �������� 
			
			for(FileItem item:items) {
				if(item.isFormField()) { //�Ϲ� �ؽ�Ʈ ������Ʈ���, �ۼ���, ����, ����... ��Ÿ���
					//vo�� �˸°� ���� 
					if(item.getFieldName().equals("title")) {
						board.setTitle(item.getString());
					}else if(item.getFieldName().equals("writer")) {
						board.setWriter(item.getString());
					}else if(item.getFieldName().equals("content")) {
						board.setContent(item.getString());
					}
				}else{ //�Ѿ�°� ����������Ʈ���
					long time=System.currentTimeMillis();
					String newName =time+"."+FileManager.getExtend(item.getName());
					board.setFilename(newName);//���θ������ �̸��� vo�� ��ƾ�insert���� ����. 
					//������ ���������� ���� 
					item.write(new File(saveDir+"/"+newName));
					flag=true;
				}
			}
			//db�� insert 
			if(flag) {
				int result=boardDAO.insert(board);
				if(result==0) {
					out.print("<script>");
					out.print("alert('��Ͻ���');");
					out.print("history.back();");
					out.print("</script>");
				}else {
					out.print("<script>");
					out.print("alert('��ϼ���');");
					out.print("location.href='/board/list.jsp';");
					out.print("</script>");
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}