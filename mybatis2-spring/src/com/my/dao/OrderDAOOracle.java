package com.my.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;

public class OrderDAOOracle implements OrderDAO{
	private SqlSessionFactory sqlSessionFactory;
//	public OrderDAOOracle() {
//		String resource = "mybatis-config.xml";
//		InputStream inputStream;
//		try {
//			inputStream = Resources.getResourceAsStream(resource);
//			sqlSessionFactory = 
//				new SqlSessionFactoryBuilder().build(inputStream);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	// /**enter누르고 메서드에 대한 설명서 작성(document형 comment)
	/**
	 * order_info테이블에 주문기본정보 추가
	 * order_line테이블에 주문상세정보 추가
	 * @param orderInfo 주문기본정보와 주문상세정보들
	 * @throws AddException
	 */
	public void insert(OrderInfo orderInfo) 
			throws AddException{
		SqlSession session = null;
		if(sqlSessionFactory != null) {
			session = sqlSessionFactory.openSession(); // 설정파일보고 DB와의 연결
			String id = orderInfo.getCustomer().getId();
			List<OrderInfo> list = session.selectList("com.my.vo.Order.insertOrderInfo", id);
			
			List<OrderLine> lines = orderInfo.getOrderLines();
			for(OrderLine line : lines) {
				session.insert("com.my.vo.Order.insertOrderLine", line);
			}
			session.commit();
		}else {
			throw new AddException("추가 실패");
		}
		session.close();
	}
	
	/**
	 * 아이디에 해당하는 주문정보[주문상세들 포함]들을 반환
	 * 최근 주문정보부터 저장된다. - 내림차순 정렬 by 주문 번호
	 * @param id (주문자 id)
	 * @return
	 * @throws NotFoundException 해당주문정보가 없다면 NotFoundException이 발생
	 */
	public List<OrderInfo> selectById(String id) 
			throws NotFoundException{
		SqlSession session = null;
		if(sqlSessionFactory != null) {
			session = sqlSessionFactory.openSession(); // 설정파일보고 DB와의 연결
			List<OrderInfo> list = session.selectList("com.my.vo.Order.selectById", "id1"); // com.my.vo.Order : Mapper의 namespace명
			//System.out.println("검색건수 : "+list.size());
			if(list.size() == 0) {
				throw new NotFoundException("아이디에 해당하는 주문정보가 없습니다.");
			}
			session.close();
			return list;
		}else {
			throw new NotFoundException("검색 실패");
		}
	}
	
	/**
	 * 날짜구간에 해당 주문정보를 반환
	 * @param start (검색할 시간 날짜) - format : yy/MM/dd
	 * @param end (검색할 끝 날짜) - format : yy/MM/dd
	 * @return
	 * @throws NotFoundException
	 */
	public List<OrderInfo> selectByDate(String start, String end) 
			throws NotFoundException{
		return null;
	}

	public List<OrderInfo> selectAll(){
		return null;
	}
	
	
}
