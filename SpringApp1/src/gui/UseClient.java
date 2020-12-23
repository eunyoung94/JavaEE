package gui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseClient {
	public static void main(String[] args) {
		//ChatClient가 has a 관계로 사용하고 있는 객체들을 직접 new하지말고 , 
		//스프링의 ApplicationContext를 이용하여 인스턴스를 주입(=injection)하자! 
		ClassPathXmlApplicationContext context=null; //스프링의 xml 설정파일을 읽어서 작성된 객체의 인스턴스를 생성 및 관리해줌
		context=new ClassPathXmlApplicationContext("spring/config/gui-context.xml");
		//xml이 이미 읽혀진 상태이므로, 메모리에는 인스턴스들이 존재한다. 그 중 어떤것을 가져올지는 getBean 메서드로가져오면된다.
		
		ChatClient chatClient= (ChatClient)context.getBean("chatClient");
		chatClient.init();
	}
}
