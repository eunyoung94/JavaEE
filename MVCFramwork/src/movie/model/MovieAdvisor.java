package movie.model;

public class MovieAdvisor {

	public String getAdvice(String movie) {
		String msg = null;
		if (movie.equals("�̼����ļ���5")) {
			msg = "�׳��� ������ ��ũ����?���Գ�?";
		} else if (movie.equals("��Ÿ����")) {
			msg = "et������ �ſ���?";
		} else if (movie.equals("����3")) {
			msg = "�ѼҸ� ������";
		} else if (movie.equals("�г�������")) {
			msg = "�Ⱥ����� �������?";
		}
		return msg;
	}
}
