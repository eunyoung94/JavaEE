package common;
/*
 파일과 관련된 유용한 기능을 모아놓는 클래스
 */
public class FileManager {
	public static String getExtend(String path) {
		int lastIndex = path.lastIndexOf("."); // lastIndexOf는 숫자값을 반환한다
		String ext =path.substring(lastIndex+1, path.length());
		//System.out.println(lastIndex);
		return ext; //.이후의 값을 스트링형으로 반환해줌 
	}
}
