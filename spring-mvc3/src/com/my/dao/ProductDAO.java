package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.NotFoundException;
import com.my.vo.Product;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<Product> selectAll() throws NotFoundException {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Product> list = sqlSession.selectList("com.my.vo.Product.selectAll");
		sqlSession.close();
		if (list == null) {
			throw new NotFoundException("상품목록이 없습니다.");
		}
		return list;
	}
	
	public Product selectByNo(String prod_no) throws NotFoundException {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Product p = new Product();
		p = sqlSession.selectOne("com.my.vo.Product.selectByNo", prod_no);
		if (p == null) {
			throw new NotFoundException("해당 상품 정보가 없습니다.");
		}
		return p;
	}
}
