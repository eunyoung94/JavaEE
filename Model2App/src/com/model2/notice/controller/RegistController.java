package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class RegistController implements Controller{
	NoticeDAO noticeDAO=new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계:알맞는 로직 객체에게 일을 시킨다.
		//regist_form에서 내가 입력한 텍스트를 파라미터를 vo에 담기 
		Notice notice= new Notice();
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result=noticeDAO.insert(notice);
		//저장할 것이 있다면 저장, 그러나 지금으로서는 저장할 것이 없다. 판단의 편수이므로..! 생략
	}

	public String getResultView() {
		return "/view/notice/regiest";
	}
	
	public boolean isForward() {
		return false;
	}

}
