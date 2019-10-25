package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;

public class OrderDAOMySQL implements OrderDAO {

	@Override
	public void insert(OrderInfo orderInfo) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderInfo> selectById(String id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> selectByDate(String start, String end) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
