package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

/*DAO��? Data Access Object�� �ǹ��ϴ� ���ø����̼� ����о߿���̴� 
 -Data Access��? �����ͺ��̽����� C(=insert)R(=select)U(update)D(delete)�۾��� �����ϴ� �ǹ�
 */

public class NoticeDAO {
	DBManager dbManager = new DBManager(); //db �޴��� �����ϱ�! ����Ŭ���� 
	//���뼺�� �������� ���� swing���� �����ۼ�
	//insert�� �� �Ѱ�! �ϳ��� VO�� ���� 
		public int regist(Notice notice) {
			Connection con=null;
			PreparedStatement pstmt=null;
			int result=0;//�� ����� �� ����� ����
			
			con=dbManager.getConnection();
			String sql="insert into notice(author,title,content) values(?,?,?)"; //�ۼ��ϱ����� �ʿ��� ������ 
			
			try {
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, notice.getAuthor());
				pstmt.setString(2, notice.getTitle());
				pstmt.setString(3, notice.getContent());
			
				result=pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con,pstmt);
			}
			return result; //���ڰ��̹�ȯ�ǳ�?
		}	
		
		public int update(Notice notice) {
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="update notice set author=? , title=?, content=? where notice_id=?";
			int result=0;
			
			con=dbManager.getConnection();
			try {
				pstmt=con.prepareStatement(sql);//�غ� 
				pstmt.setString(1, notice.getAuthor());
				pstmt.setString(2, notice.getTitle());
				pstmt.setString(3, notice.getContent());
				pstmt.setInt(4, notice.getNotice_id());
				result=pstmt.executeUpdate();//��������			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt);
			}
			return result;
		}
		
		//��緹�ڵ� ��������! 
		public ArrayList selectAll() {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<Notice>list= new ArrayList<Notice>(); //rs�� ��ü�� �༮�� 
			
			con=dbManager.getConnection();
			String sql="select * from notice order by notice_id desc";
			//notice_id�������� �������� �ؼ� �����Ͷ� 
			try {
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Notice notice= new Notice(); //�ֺ� empty������ vo�� �������� 
					//notice�� rs������ ��� �Űܽ��� 
					notice.setNotice_id(rs.getInt("notice_id")); //rs���� ���ͼ� ��ġ�� �־��ֱ�
					notice.setAuthor(rs.getString("author"));
					notice.setContent(rs.getString("content"));
					notice.setRegdate(rs.getString("regdate"));
					notice.setHit(rs.getInt("hit"));
					//notice������ ����������� �� list�� ����
					list.add(notice);
				}
				
				//rs�� ���ڵ尡 �������̹Ƿ�, VO���� �������� �ʿ��ϰ� , �� VO�� �Ѳ����� ��Ƽ� ��ȯ�ؾ��ϹǷ�,
				//������ �ڷ����� �ʿ��ϴ�. ��ü�� ��Ƴ��� ������ �����ϴ� �����ӿ��� CollectionFramework�̹Ƿ�, ���� �ϳ��� api�� �̿��غ���
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt, rs);
			}
			return list; //rs�� �ƴ� ArrayList�� ��ȯ����
		}
		
		
		//�Խù� 1�� ��������(�󼼺���)
		public Notice select(int notice_id) {
			PreparedStatement pstmt=null;
			Connection con=null;
			ResultSet rs =null;
			Notice notice=null;//rs��� ������ 1���� ��� ��ü
			
			String sql="select * from notice where notice_id=?";		
			con=dbManager.getConnection();//���Ӱ�ü���
			try {
				pstmt=con.prepareStatement(sql);//�����غ�
				pstmt.setInt(1, notice_id);//���ε� ������ ����
				rs=pstmt.executeQuery();
				//���� ź���� rs�� �� �״´�. ���� rs�� ��ü�� ��ü�� �ʿ��ϴ�. 
				//rs�� ���ڵ� �Ѱ��� ��� ��ü�̹Ƿ�, ���ڵ� 1���� ��� ���޿����� ���Ǵ� VO�� �̿�����
			if(rs.next()) { //���ڵ尡 �����Ҷ� VO�� �ø���
				notice= new Notice(); //�ֺ� empty������ vo�� �������� 
				//notice�� rs������ ��� �Űܽ��� 
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			
			//��ȸ�� ���� 
			sql="update notice set hit=hit+1 where notice_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			pstmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt, rs);
			}
			return notice;
		}
		
		public int delete(int notice_id) {
			Connection con=null;
			PreparedStatement pstmt =null;
			String sql="delete from notice where notice_id=?";
			int result=0;
			con=dbManager.getConnection();
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, notice_id);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt);
			}
			return result;
		}
}