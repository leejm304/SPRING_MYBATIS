package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;

public interface OrderDAO {

	/**
	 * order_info테이블에 주문기본정보 추가
	 * order_line테이블에 주문상세정보 추가
	 * @param orderInfo 주문기본정보와 주문상세정보들
	 * @throws AddException
	 */
	public void insert(OrderInfo orderInfo) 
			throws AddException;
	
	/**
	 * 아이디에 해당하는 주문정보[주문상세들 포함]들을 반환
	 * 최근 주문정보부터 저장된다. - 내림차순 정렬 by 주문 번호
	 * @param id (주문자 id)
	 * @return
	 * @throws NotFoundException 해당주문정보가 없다면 NotFoundException이 발생
	 */
	public List<OrderInfo> selectById(String id) 
			throws NotFoundException;
	
	/**
	 * 날짜구간에 해당 주문정보를 반환
	 * @param start (검색할 시간 날짜) - format : yy/MM/dd
	 * @param end (검색할 끝 날짜) - format : yy/MM/dd
	 * @return
	 * @throws NotFoundException
	 */
	public List<OrderInfo> selectByDate(String start, String end) 
			throws NotFoundException;
	
	public List<OrderInfo> selectAll();
	
}
