package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import movie.model.MovieAdcisor;

public class MovieController implements Controller{

	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//�Ķ���͹ޱ�
		String movie= request.getParameter("movie");
		MovieAdcisor advisor=new MovieAdcisor();
		String msg=advisor.getAdvice(movie);
		//4�ܰ� :Ŭ���̾�Ʈ���� ������ ����� �������� 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
	}
		//����� ����� �������� view�� ����ϹǷ�, �� �������� ó���ϸ� �ȵȴ�. 
		//��� jsp�޼����� �����ַ���, ������ �޸𸮿� �ӽ������� ������ ���� �ʿ䰡 �ִ�. 
		//����μ��� ���ǿ� ���� 
		public String getViewPage() {
			return "/movie/movie_result.jsp";
		}
	}
