/*기존 전통적인 jdbc방식으로 작성했던 dao를 CRUD(create read update delete)메서드를 mybatis
 프레임웍을 이용해서 코드를 간략화 시켜보자
 
 
* RDB(Relational Database)란?
    관계형 데이타 모델에 기초를 둔 데이타베이스입니다.
    관계형 데이타 모델이란 데이타를 구성하는데 필요한 방법 중 하나로 모든 데이타를 2차원의 테이블 형태로 표현해줍니다.
    관계형 데이타 모델의 개념은 표현 개체의 외부개념 관례를 적용한 것으로, 데이타 간의 상관관계에서 개체간의 관계를 표현한 것이라고 할 수 있습니다.

  sqlsession이란? 
  SqlSession이란 RDB에 인증을 거친 논리적인 연결 상태를 말하는 것이다
  
mybatis.config -> config.xml에서 typeAliases 사용하여서 이름을 간소화 시켜놨다.
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

		result=sqlSession.insert("Board.insert",board);//vo에 담겟다!
		sqlSession.commit();//DML(Data Manipulation Language: 테이블 안에 데이터를 하나하나 추가하거나, 삭제 , insert,delete,update )인 경우 해주자 
		configManager.close(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list=null;//list를 비워주고
		SqlSession sqlSession = configManager.getSqlSession();//쿼리수행객체
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
		sqlSession.commit();//DML이니까 
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