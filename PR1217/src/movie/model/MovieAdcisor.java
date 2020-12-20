package movie.model;

public class MovieAdcisor {
	public String getAdvice(String movie) {
		String msg=null;
		
		if(movie.equals("토르")) {
			msg="토르는 멋지다";
		}else if(movie.equals("킹덤")) {
			msg="킹덤은 재밌다";
		}else if(movie.equals("아바타")) {
			msg="아바타는 신기하다";
		}else if(movie.equals("러브미")) {
			msg="내가지어낸영화다";
		}
		return msg;
	}
}
