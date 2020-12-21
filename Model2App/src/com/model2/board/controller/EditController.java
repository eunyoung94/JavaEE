package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class EditController implements Controller{
	BoardDAO boardDAO=new BoardDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//board_id,title,writer,content
		String board_id=request.getParameter("board_id");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Board board = new Board();
		board.setBoard_id(Integer.parseInt(board_id));
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//수정처리 
		int result=boardDAO.update(board);
		//4단계 수정후 상세페이지에서 수정된 내용을 봐야하기때문에 저장할 것이있다.
		request.setAttribute("result",result);
		request.setAttribute("board",board);
	}


	public String getResultView() {
		return "/view/board/edit";
	}


	public boolean isForward() {
		return true;//요청을 유지하기 위해서 true로 둔다..
	}

}
