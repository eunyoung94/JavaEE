package blood.model;

public class BloodAdvisor {

	public String getAdvice(String blood) {
		String msg = null;
		if (blood.equals("A")) {
			msg = "�Ĳ��ϰ� �����ϴ�.���мҽ��ϴ�";
		} else if (blood.equals("B")) {
			msg = "���׸ŷ� ���ϰ� Ȱ��";
		} else if (blood.equals("C")) {
			msg = "�米���ְ�, �ձ۵ձ��ϴ�";
		} else if (blood.equals("D")) {
			msg = "�����⺹�� ���ϴ�";
		}
		return msg;
	}
}
