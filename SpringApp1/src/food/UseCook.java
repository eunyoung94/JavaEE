package food;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {
	public static void main(String[] args) {
		/*
		// ���� �ø���
		FriPan pan = new FriPan();
		Cook cook = new Cook();
		// ���� �丮�翡�� ����
		cook.setPan(pan);
		cook.makeFood();
	*/
		
	//�������� �̿��ؼ� ��ü�� ���Խ��Ѻ���
	//xml�� ���ϴ� ��ü�� ����ϸ� ��ü�� �ۼ��� xml�� �ľ��Ͽ� ��ü���� �ν��Ͻ��� ���� �� ������ ���ش�. 
	//�̷��� ������ �����ϴ� ��ü�� ������ .Spring Context��ü�� �ȴ�.
		ClassPathXmlApplicationContext context=null; //������xml���������� �о �ۼ��� ��ü�� �ν��Ͻ��� ���� �� �������ش�.
		context = new ClassPathXmlApplicationContext("spring/config/context.xml");
		
	//xml�� �̹� ������ �����̹Ƿ�, �޸𸮿��� �ν��Ͻ����� ������ ���̰�, �� �� � �ν��Ͻ��� ���������� getBean�޼���� ��������ȴ�. 
		Cook cook=(Cook)context.getBean("cook");
		cook.makeFood();
	}
}