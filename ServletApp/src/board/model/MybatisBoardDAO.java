/*���� �������� jdbc������� �ۼ��ߴ� dao�� CRUD(create read update delete)�޼��带 mybatis
 �����ӿ��� �̿��ؼ� �ڵ带 ����ȭ ���Ѻ���
 
 
* RDB(Relational Database)��?
    ������ ����Ÿ �𵨿� ���ʸ� �� ����Ÿ���̽��Դϴ�.
    ������ ����Ÿ ���̶� ����Ÿ�� �����ϴµ� �ʿ��� ��� �� �ϳ��� ��� ����Ÿ�� 2������ ���̺� ���·� ǥ�����ݴϴ�.
    ������ ����Ÿ ���� ������ ǥ�� ��ü�� �ܺΰ��� ���ʸ� ������ ������, ����Ÿ ���� ������迡�� ��ü���� ���踦 ǥ���� ���̶�� �� �� �ֽ��ϴ�.

  sqlsession�̶�? 
  SqlSession�̶� RDB�� ������ ��ģ ������ ���� ���¸� ���ϴ� ���̴�
  
mybatis.config -> config.xml���� typeAliases ����Ͽ��� �̸��� ����ȭ ���ѳ���.
	<typeAliases>
		<typeAlias type="board.model.Board" alias="Board"/>
	</typeAliases>	
		
 */

package board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.MybatisConfigManager;

public class MybatisBoardDAO {
	MybatisConfigManager configManager=MybatisConfigManager.getInstance();
	
	public int insert(Board board){
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();

		result=sqlSession.insert("Board.insert",board);//vo�� ��ٴ�!
		sqlSession.commit();//DML(Data Manipulation Language: ���̺� �ȿ� �����͸� �ϳ��ϳ� �߰��ϰų�, ���� , insert,delete,update )�� ��� ������ 
		configManager.close(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list=null;//list�� ����ְ�
		SqlSession sqlSession = configManager.getSqlSession();//�������ఴü
		list=sqlSession.selectList("Board.selectAll");
		configManager.close(sqlSession);
		return list;
	}
	
	public Board select(int board_id) {
		Board board=null;
		SqlSession sqlSession = configManager.getSqlSession();
		sqlSession.selectOne("Board.select",board_id);
		configManager.close(sqlSession);
		return board;
	}
	
	public int update(Board board) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.update("Board.update",board);
		sqlSession.commit();//DML�̴ϱ� 
		configManager.close(sqlSession);
		return result;
	}
	
	public int delete(int board_id) {
		int result=0;
		SqlSession sqlSession=configManager.getSqlSession();
		result=sqlSession.delete("Board.delete",board_id);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	
	
}