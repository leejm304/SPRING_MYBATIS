package com.my.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;

@Repository("orderDAO")
public class OrderDAOOracle implements OrderDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSession sqlsession;

	public OrderDAOOracle() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * //Component 덕에 필요없어짐 //Spring 컨테이너가 Injection이 가능할 수 있도록 getter/setter 생성
	 * public SqlSessionFactory getSqlSessionFactory() { return sqlSessionFactory; }
	 * 
	 * 
	 * 
	 * public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	 * this.sqlSessionFactory = sqlSessionFactory; }
	 */

	/**
	 * order_info 테이블에 주문기본정보 추가 order_line 테이블에 주문상세정보 추가
	 * 
	 * @param orderInfo 주문기본정보와 주문상세정보들
	 * @throws AddException
	 */
	public void insert(OrderInfo orderInfo) throws AddException {
		// myBatis 설정파일 찾기
		// String resource = "mybatis-config.xml";
		// InputStream inputStream;
		// SqlSession session = null;
		// if (sqlSessionFactory != null) {
		// inputStream = Resources.getResourceAsStream(resource);
		// SqlSessionFactory sqlSessionFactory = new
		// SqlSessionFactoryBuilder().build(inputStream);
		// session = sqlSessionFactory.openSession();
		try {
			String id = orderInfo.getCustomer().getId();
			System.out.println("id:" + id);
			sqlsession.insert("com.my.vo.Order.insertOrderInfo", id);

			List<OrderLine> lines = orderInfo.getOrderLines();
			for (OrderLine line : lines) {
				sqlsession.insert("com.my.vo.Order.insertOrderLine", line);
			}
		} catch (Exception e) {
			throw new AddException("추가 실패");
		}
		// session.commit();
		// } else {
		// throw new AddException("주문 실패");
		// }
		// session.close(); // session 닫아주기 --> leak 방지
	}

	/**
	 * 아이디에 해당하는 주문정보[주문상세들포함]들을 반환한다 최근 주문정보부터 저장된다. - 내림차순정렬
	 * 
	 * @param id 주문자ID
	 * @return
	 * @throws NotFoundException 해당주문정보가 없다면 NotFoundException이 발생한다.
	 */
	public List<OrderInfo> selectById(String id) throws NotFoundException {
		SqlSession session = null;
		if (sqlSessionFactory != null) {
			session = sqlSessionFactory.openSession();
			List<OrderInfo> list = session.selectList("com.my.vo.Order.selectById", id);
			if (list.size() == 0) {
				throw new NotFoundException("아이디에 해당 주문정보가 없습니다.");
			}
			session.close();
			return list;
		} else {
			throw new NotFoundException("검색 실패");
		}
	}

	/**
	 * 날짜구간에 해당하는 주문정보들을 반환한다
	 * 
	 * @param start 검색할 시작날짜(yy/MM/dd/포맷 사용)
	 * @param end   검색할 끝날짜(yy/MM/dd/포맷 사용)
	 * @return
	 * @throws NotFoundException
	 */
	public List<OrderInfo> selectByDate(String start, String end) throws NotFoundException {

		return null;
	}

	public List<OrderInfo> selectAll() {

		return null;
	}
}