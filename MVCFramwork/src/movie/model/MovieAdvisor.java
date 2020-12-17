package movie.model;

public class MovieAdvisor {

	public String getAdvice(String movie) {
		String msg = null;
		if (movie.equals("미션임파서블5")) {
			msg = "그남자 누구지 톰크루즈?나왔나?";
		} else if (movie.equals("스타워즈")) {
			msg = "et나오는 거였나?";
		} else if (movie.equals("존윅3")) {
			msg = "총소리 탕탕탕";
		} else if (movie.equals("분노의질주")) {
			msg = "안봤지만 재밌을듯?";
		}
		return msg;
	}
}
