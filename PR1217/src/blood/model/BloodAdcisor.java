package blood.model;

public class BloodAdcisor {

	public String getAdvice(String blood) {
		String msg=null;
		if(blood.equals("A")) {
			msg="꼼꼼하고 세심하다. 때론 소심하다";
		}else if(blood.equals("B")) {
			msg="b형";
		}else if(blood.equals("O")) {
			msg="c형";
		}else if(blood.equals("AB")) {
			msg="ab형";
		}
		return msg;
	}
}
