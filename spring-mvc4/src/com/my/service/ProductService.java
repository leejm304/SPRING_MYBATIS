package com.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dao.ProductDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Product;

@Service
public class ProductService {
	@Autowired
	public ProductDAO dao;

	public List<Product> productList() {

		List<Product> list = null;
		try {
			list = dao.selectAll();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public String productDetail(String prod_no) {
		Map<String, Object>map = new HashMap<String, Object>();
		try {
			Product p = dao.selctByNo(prod_no);
			map.put("status", 1);
			map.put("product",p);
		} catch (NotFoundException e) {
			map.put("status",-1);
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return(mapper.writeValueAsString(map));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
