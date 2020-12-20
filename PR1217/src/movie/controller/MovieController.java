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
		//파라미터받기
		String movie= request.getParameter("movie");
		MovieAdcisor advisor=new MovieAdcisor();
		String msg=advisor.getAdvice(movie);
		//4단계 :클라이언트에게 전달할 결과를 저장하자 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
	}
		//결과의 출력은 디자인인 view가 담당하므로, 이 서블릿에서 처리하면 안된다. 
		//결과 jsp메세지를 보여주려면, 서버의 메모리에 임시적으로 저장해 놓을 필요가 있다. 
		//현재로서는 세션에 담자 
		public String getViewPage() {
			return "/movie/movie_result.jsp";
		}
	}
