package com.my.dao;

import java.util.List;
import java.util.Map;

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
		List<Product> list;
		SqlSession sqlSession = sqlSessionFactory.openSession();

		list = sqlSession.selectList("com.my.vo.Product.selectAll");
		sqlSession.close();
		
		if (list == null) {
			throw new NotFoundException("검색 결과가 없습니다");
		}
		return list;
	}

	public Product selctByNo(String prod_no) throws NotFoundException {
		Product product;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		product = sqlSession.selectOne("com.my.vo.Product.selectByNo",prod_no);
		sqlSession.close();
		System.out.println("확인"+product.getProd_detail());
		if (product == null) {
			throw new NotFoundException("검색 결과가 없습니다");
		}
		return product;
	}
}
