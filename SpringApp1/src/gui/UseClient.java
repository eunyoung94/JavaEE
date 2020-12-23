package gui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseClient {
	public static void main(String[] args) {
		//ChatClient�� has a ����� ����ϰ� �ִ� ��ü���� ���� new�������� , 
		//�������� ApplicationContext�� �̿��Ͽ� �ν��Ͻ��� ����(=injection)����! 
		ClassPathXmlApplicationContext context=null; //�������� xml ���������� �о �ۼ��� ��ü�� �ν��Ͻ��� ���� �� ��������
		context=new ClassPathXmlApplicationContext("spring/config/gui-context.xml");
		//xml�� �̹� ������ �����̹Ƿ�, �޸𸮿��� �ν��Ͻ����� �����Ѵ�. �� �� ����� ���������� getBean �޼���ΰ�������ȴ�.
		
		ChatClient chatClient= (ChatClient)context.getBean("chatClient");
		chatClient.init();
	}
}
