package common.file;

import java.io.File;

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
	
	//파일삭제 : 호출자는 삭제하고싶은 파일의 경로를 넘긴다.
	public static boolean deleteFile(String path) {
		File file= new File(path);
	//	file.delete();
		return file.delete();//삭제성공하면 true를 리턴
	}
	
	
}
