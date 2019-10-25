package com.my.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;
/**
 * 직영점용 주문서비스
 * @author Administrator
 *
 */
@Service("orderService")
public class OrderServiceDirect implements OrderService {
	@Autowired
	private OrderDAO dao;
	@Override
	public String add(OrderInfo info) {
		int status = -1;
		try {
			dao.insert(info);
			status = 1;
		} catch (AddException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
	}

	@Override
	public List<OrderInfo> findById(String id) throws NotFoundException{
		// TODO Auto-generated method stub
		return null;
	}
}
