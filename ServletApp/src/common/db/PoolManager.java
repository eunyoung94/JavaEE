
/*
 * Ŀ�ؼ� Ǯ ����� ���� ���ϰ� ó���ؾ�  DAO �鿡�� ������Ѵ�!!
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
	InitialContext context;// JNDI�˻��� ����ϴ� ��ü
	DataSource ds; // Ŀ�ؼ�Ǯ
	private static PoolManager instance;

	// �̽������ʹ� �ƹ���new�� �� ����
	private PoolManager() {
		try {
			context = new InitialContext();// �˻� ��ü ����
/*
 ����� �ƴ�����.. �ƴ°͸� �亯�帮�ڸ� java:comp/env�� ���ҽ� �̸��տ� �ٴ� ���λ������� �����Ͻø� �˴ϴ�. web.xml���Ͽ� <resource-ref>������Ʈ�� ����������. JDBC�� ������� 
<resource-ref> 
<description>DB Connection</description> 
<res-ref-name>jdbc/myhome</res-ref-name> 
<res-type>javax.sql.DataSource</res-type> 
<res-auth>Container</res-auth> 
</resource-ref> 
�� �̷����� ���ٵ� ���� jdbc/myhome��� ���ҽ��̸��� lookup���� ã���� ���ҽ� �̸��տ� ���̴� ���λ� ��� �����Ͻø� �˴ϴ�.
 */
			ds = (DataSource) context.lookup("java:comp/env/jdbc/myoracle");// ã�� ���� and Ǯ��ȯ
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// ������ Ŭ������ ����� ��������Ƿ�, �ν��Ͻ��� ������ ��ȸ�� ���� Ŭ�������� �δ�����
	public static PoolManager getInstance() {
		if (instance == null) {
			instance = new PoolManager(); // ���� ���� new�ϱ�
		}
		return instance;
	}

	// Ŀ�ؼ��� �ʿ��� �ڿ��� Connection�� ��ȯ���ִ� (�뿩) �޼���
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();// �뿩!!!
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// �ݳ�
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
