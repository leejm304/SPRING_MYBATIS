package com.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.NotFoundException;

@Repository
public class ZipDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<Map<String, String>> selectByDoro(String doro) throws NotFoundException {
		List<Map<String, String>> list;
		SqlSession sqlSession = sqlSessionFactory.openSession();

		list = sqlSession.selectList("com.my.vo.Post.selectByDoro", doro);
		sqlSession.close();
		
		if (list == null) {
			throw new NotFoundException("검색 결과가 없습니다");
		}
		return list;
	}
}