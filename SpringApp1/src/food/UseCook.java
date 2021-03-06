package food;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {
	public static void main(String[] args) {
		/*
		// 팬을 올리자
		FriPan pan = new FriPan();
		Cook cook = new Cook();
		// 펜을 요리사에게 주입
		cook.setPan(pan);
		cook.makeFood();
	*/
		
	//스프링을 이용해서 객체를 주입시켜본다
	//xml에 원하는 객체를 명시하면 객체가 작성된 xml을 파악하여 객체들의 인스턴스를 생성 및 관리를 해준다. 
	//이런한 역할을 수행하는 객체를 가리켜 .Spring Context객체가 된다.
		ClassPathXmlApplicationContext context=null; //스프링xml설정파일을 읽어서 작성된 객체의 인스턴스를 생성 및 관리해준다.
		context = new ClassPathXmlApplicationContext("spring/config/context.xml");
		
	//xml이 이미 읽혀진 상태이므로, 메모리에는 인스턴스들이 존재할 것이고, 그 중 어떤 인스턴스를 가져올지는 getBean메서드로 가져오면된다. 
		Cook cook=(Cook)context.getBean("cook");
		cook.makeFood();
	}
}