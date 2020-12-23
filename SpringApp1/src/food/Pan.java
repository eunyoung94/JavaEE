package food;
//일반프라이펜이건 전기 프라이팬이건 증기 프라이팬이건 모두를 가리킬 수 있는 최상위 인터페이스(추상클래스도 가능하지만
// 다중상속의 우려가 있기때문에 사실상 인터페이스를 많이쓴다
public interface Pan {
	//구현강제할 메서드
	public void boil();
}
