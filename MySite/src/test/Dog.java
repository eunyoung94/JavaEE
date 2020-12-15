/*
 �������� �ν��Ͻ��� ���� 1���� ���鵵�� ��ġ�ϱ�
  SingleTon Pattern : ��ü�� �ν��Ͻ��� 1���� �����ϴ� ���� 
 */
package test;

public class Dog {
	String name="�ǹ�";
	private static Dog instance;
	//�����ڸ� ��������, �ν��Ͻ��� ������ �ǹ��� �����. 
	private Dog() {
		
	}
	public static Dog getInstance() {
		if(instance==null) {
			instance = new Dog();
		}
		return instance;
	}
}	
