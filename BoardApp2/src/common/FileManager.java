package common;
/*
 ���ϰ� ���õ� ������ ����� ��Ƴ��� Ŭ����
 */
public class FileManager {
	public static String getExtend(String path) {
		int lastIndex = path.lastIndexOf("."); // lastIndexOf�� ���ڰ��� ��ȯ�Ѵ�
		String ext =path.substring(lastIndex+1, path.length());
		//System.out.println(lastIndex);
		return ext; //.������ ���� ��Ʈ�������� ��ȯ���� 
	}
}