/*요리사를 정의*/
package food;

public class Cook {
	Pan pan; //정확한 자료형으로 has a 관계를 표기하지말자..
	//하위자료형이 삭제되거나, 변화가 생기더라도 현재 클래스와 has a 관계에 있는 클래스간 의족성을 약화시키기 때문에 
	//유지 보수성을 높일수 있다.
	//
	public void setPan(Pan pan) {
		this.pan=pan;
	}
	public void makeFood() {
		pan.boil();
	}
}
