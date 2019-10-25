package com.my.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.CustomerDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerDAO dao;

	public Map<String, Object> login(String id, String pwd) {
		int status = -1; // 로그인 실패
		try {
			Customer c = dao.selectById(id);
			if (c.getPwd().equals(pwd)) // 비밀번호 일치확인
				status = 1;// 로그인 성공
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		map.put("id", id);
		return map;
	}
	
	
//	public String login(String id, String pwd) {
//		int status = -1; // 로그인 실패
//
//		try {
//			Customer c = dao.selectById(id);
//			if (c.getPwd().equals(pwd)) // 비밀번호 일치확인
//				status = 1;// 로그인 성공
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		JSONObject json = new JSONObject();
//		json.put("status",status);
//		json.put("id",id);
//		String str = json.toString();
//		return str;
//	}

	public Map<String, Object> dupchk(String id) {
		int status = -1; // 중복:1, 중복안됨:-1
		Map<String, Object> map = new HashMap<>();
		try {
			dao.selectById(id);
			status = 1; // id가 이미 존재함.
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		map.put("status", status);
		return map;
	}

	public Map<String, Object> join(Customer c) {
		int status = 0; 
		Map<String, Object> map = new HashMap<>();
		try {
			status = dao.insertCustomer(c);
			map.put("status", status);
		} catch (AddException e) {
			map.put("status", -1);
			e.printStackTrace();
		}
		return map;
	}
}
