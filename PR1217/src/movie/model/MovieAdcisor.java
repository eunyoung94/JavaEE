package movie.model;

public class MovieAdcisor {
	public String getAdvice(String movie) {
		String msg=null;
		
		if(movie.equals("�丣")) {
			msg="�丣�� ������";
		}else if(movie.equals("ŷ��")) {
			msg="ŷ���� ��մ�";
		}else if(movie.equals("�ƹ�Ÿ")) {
			msg="�ƹ�Ÿ�� �ű��ϴ�";
		}else if(movie.equals("�����")) {
			msg="���������ȭ��";
		}
		return msg;
	}
}
