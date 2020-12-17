package com.webApp1216.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webApp1216.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();// singleTon ��ü���

	public int insert(Notice notice) {
		int result = 0;
		// jdbc�� �ƴ� mybatis�� ������ ��������
		SqlSession sqlSession = manager.getsqSession();// ���� ��簴ü(xml�� ������ ȣ���� �� �ִ�)
		result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();// DML�� ��� commitó��
		manager.close(sqlSession);
		return result;
	}

	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getsqSession();
		list = sqlSession.selectList("Notice.selectAll");
		manager.close(sqlSession);
		return list;
	}

	public Notice select(int notice_id) {
		Notice notice = null;
		SqlSession sqlSession = manager.getsqSession();
		notice = sqlSession.selectOne("Notice.select", notice_id);
		manager.close(sqlSession);
		return notice;
	}

	public int update(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getsqSession();
		result = sqlSession.delete("Notice.update", notice);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}

	public int delete(int notice_id) {
		int result = 0;
		SqlSession sqlSession = manager.getsqSession();
		result = sqlSession.delete("Notice.delete", notice_id);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}

}
