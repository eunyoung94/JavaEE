/*
 �ڹ��� �÷��� ������ ���� ��ü�� �����Ͱ�key-value�� �����εǾ��ִ� ������ �����Ͽ� ó���� �� �ִ� ��ü�� Properties��� �Ѵ�. 
 �� ������ ���������� ������ ���� �� ���� key-value�� �� �ָ��� �����Ѵ�.
 */
package study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropTest {
	public PropTest() {
		//�������� �ڹ� �ڵ忡�� Ư�����丮�� ����ִ� ���������� ���� �����ؾ��Ѵ�.
		FileInputStream fis=null;
		Properties props=new Properties();
		try {
			fis=new FileInputStream("D:/workspace/javaee_workspace/MVCFramwork/WebContent/WEB-INF/mapping/mapping.properties");
			props.load(fis); //������Ƽ�� ��ü�� ��Ʈ�� ���� 
			//���ݺ��ʹ� key������ �˻��� �����ϴ� 
			 String value=props.getProperty("zerg");
			 System.out.println(value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private void mian() {
			
	}
}
