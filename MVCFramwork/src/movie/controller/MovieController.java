/*
 ������ jsp�� ����ϰ� �־��� ��Ʈ�ѷ��μ��� ������ ���� Ŭ������ �и���Ű�� 
 �׷��� jsp�� ������ �������� �Ǳ⶧���� ���������� ��ü���� �����ϴ� 
 
 */

package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;
import movie.model.MovieAdvisor;
//MovieController�� Controller..
public class MovieController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���͹ޱ� 
		String movie=request.getParameter("movie");
		//3�ܰ� _ �˸´� ���� ��ü���� �Ͻ�Ų��
		MovieAdvisor advisor = new MovieAdvisor();
		String msg=advisor.getAdvice(movie);
		//4�ܰ� : Ŭ���̾�Ʈ���� ������ ����� �����س��´�. 
		HttpSession session=request.getSession();
		session.setAttribute("msg", msg);
}
		//����� ����� �������� View�� ����ϹǷ�, �� �������� ó���ϸ� �ȵȴ�. 
		//��� jsp���� �޼����� �����ַ���, ������ �޸𸮿� �ӽ������� �����س��� �ʿ䰡 �ִ� 
		//����μ��� ���ǿ� ���� 
		public String getViewPage() {
			return "/movie/movie_result.jsp";
		}
	
}
