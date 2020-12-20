package blood.model;

public class BloodAdvisor {

	public String getAdvice(String blood) {
		String msg = null;
		if (blood.equals("A")) {
			msg = "꼼꼼하고 세심하다.때론소심하다";
		} else if (blood.equals("B")) {
			msg = "엉뚱매력 쿨하고 활발";
		} else if (blood.equals("O")) {
			msg = "사교성있고, 둥글둥글하다";
		} else if (blood.equals("AB")) {
			msg = "감정기복이 심하다";
		}
		return msg;
	}
}
