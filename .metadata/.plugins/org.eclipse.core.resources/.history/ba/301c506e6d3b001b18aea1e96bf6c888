package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

/*DAO란? Data Access Object를 의미하는 어플리케이션 설계분야용어이다 
 -Data Access란? 데이터베이스와의 C(=insert)R(=select)U(update)D(delete)작업을 전담하는 의미
 */

public class NoticeDAO {
	DBManager dbManager = new DBManager(); //db 메니저 접속하기! 접속클래스 
	//재사용성을 고려하지 않은 swing만의 로직작성
	//insert는 글 한건! 하나의 VO로 가자 
		public int regist(Notice notice) {
			Connection con=null;
			PreparedStatement pstmt=null;
			int result=0;//글 등록후 그 결과값 보관
			
			con=dbManager.getConnection();
			String sql="insert into notice(author,title,content) values(?,?,?)"; //작성하기위해 필요한 쿼리문 
			
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
			return result; //숫자값이반환되네?
		}	
		
		public int update(Notice notice) {
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="update notice set author=? , title=?, content=? where notice_id=?";
			int result=0;
			
			con=dbManager.getConnection();
			try {
				pstmt=con.prepareStatement(sql);//준비 
				pstmt.setString(1, notice.getAuthor());
				pstmt.setString(2, notice.getTitle());
				pstmt.setString(3, notice.getContent());
				pstmt.setInt(4, notice.getNotice_id());
				result=pstmt.executeUpdate();//쿼리수행			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt);
			}
			return result;
		}
		
		//모든레코드 가져오기! 
		public ArrayList selectAll() {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<Notice>list= new ArrayList<Notice>(); //rs를 대체할 녀석들 
			
			con=dbManager.getConnection();
			String sql="select * from notice order by notice_id desc";
			//notice_id기준으로 내림차순 해서 가져와라 
			try {
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Notice notice= new Notice(); //텅빈 empty상태의 vo를 생성하자 
					//notice에 rs정보를 모두 옮겨심자 
					notice.setNotice_id(rs.getInt("notice_id")); //rs에서 얻어와서 노치에 넣어주기
					notice.setAuthor(rs.getString("author"));
					notice.setContent(rs.getString("content"));
					notice.setRegdate(rs.getString("regdate"));
					notice.setHit(rs.getInt("hit"));
					//notice변수가 사라지기전에 얼른 list에 담자
					list.add(notice);
				}
				
				//rs는 레코드가 복수개이므로, VO또한 여러개가 필요하고 , 이 VO를 한꺼번에 모아서 반환해야하므로,
				//집합형 자료형이 필요하다. 객체를 모아놓은 집합을 지원하는 프레임웍은 CollectionFramework이므로, 이중 하나의 api를 이용해보자
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt, rs);
			}
			return list; //rs가 아닌 ArrayList를 반환하자
		}
		
		
		//게시문 1건 가져오기(상세보기)
		public Notice select(int notice_id) {
			PreparedStatement pstmt=null;
			Connection con=null;
			ResultSet rs =null;
			Notice notice=null;//rs대신 데이터 1건을 담는 객체
			
			String sql="select * from notice where notice_id=?";		
			con=dbManager.getConnection();//접속객체얻기
			try {
				pstmt=con.prepareStatement(sql);//쿼리준비
				pstmt.setInt(1, notice_id);//바인드 변수값 지정
				rs=pstmt.executeQuery();
				//지금 탄생한 rs는 곧 죽는다. 따라서 rs를 대체할 객체가 필요하다. 
				//rs는 레코드 한건을 담는 객체이므로, 레코드 1건을 담아 전달용으로 사용되는 VO를 이용하자
			if(rs.next()) { //레코드가 존재할때 VO를 올리자
				notice= new Notice(); //텅빈 empty상태의 vo를 생성하자 
				//notice에 rs정보를 모두 옮겨심자 
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			
			//조회수 증가 
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
