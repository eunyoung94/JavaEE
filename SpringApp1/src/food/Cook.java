/*�丮�縦 ����*/
package food;

public class Cook {
	Pan pan; //��Ȯ�� �ڷ������� has a ���踦 ǥ����������..
	//�����ڷ����� �����ǰų�, ��ȭ�� ������� ���� Ŭ������ has a ���迡 �ִ� Ŭ������ �������� ��ȭ��Ű�� ������ 
	//���� �������� ���ϼ� �ִ�.
	//
	public void setPan(Pan pan) {
		this.pan=pan;
	}
	public void makeFood() {
		pan.boil();
	}
}
