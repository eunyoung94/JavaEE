package blood.model;

public class BloodAdcisor {

	public String getAdvice(String blood) {
		String msg=null;
		if(blood.equals("A")) {
			msg="�Ĳ��ϰ� �����ϴ�. ���� �ҽ��ϴ�";
		}else if(blood.equals("B")) {
			msg="b��";
		}else if(blood.equals("O")) {
			msg="c��";
		}else if(blood.equals("AB")) {
			msg="ab��";
		}
		return msg;
	}
}
