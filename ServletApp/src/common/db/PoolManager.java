
/*
 * 커넥션 풀 사용을 보다 편하게 처리해야  DAO 들에서 고생안한다!!
 * */
package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager {
	InitialContext context;// JNDI검색을 담당하는 객체
	DataSource ds; // 커넥션풀
	private static PoolManager instance;

	// 이시점부터는 아무도new할 수 없다
	private PoolManager() {
		try {
			context = new InitialContext();// 검색 객체 생성
/*
 고수는 아니지만.. 아는것만 답변드리자면 java:comp/env는 리소스 이름앞에 붙는 접두사정도로 생각하시면 됩니다. web.xml파일에 <resource-ref>엘리먼트를 설정해주죠. JDBC를 예를들면 
<resource-ref> 
<description>DB Connection</description> 
<res-ref-name>jdbc/myhome</res-ref-name> 
<res-type>javax.sql.DataSource</res-type> 
<res-auth>Container</res-auth> 
</resource-ref> 
뭐 이런식이 될텐데 위의 jdbc/myhome라는 리소스이름을 lookup으로 찾을때 리소스 이름앞에 붙이는 접두사 라고 생각하시면 됩니다.
 */
			ds = (DataSource) context.lookup("java:comp/env/jdbc/myoracle");// 찾기 성공 and 풀반환
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 하지만 클래스는 쓰라고 만들었으므로, 인스턴스를 가져갈 기회를 현재 클래스에서 부담하자
	public static PoolManager getInstance() {
		if (instance == null) {
			instance = new PoolManager(); // 오직 나만 new하기
		}
		return instance;
	}

	// 커넥션이 필요한 자에게 Connection을 반환해주는 (대여) 메서드
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();// 대여!!!
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 반납
	public void release(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void release(Connection con, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
