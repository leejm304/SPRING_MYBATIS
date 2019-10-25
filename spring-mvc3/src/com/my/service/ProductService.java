package com.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	private ProductDAO dao;

	public List<Product> productList() throws NotFoundException{
		return dao.selectAll();
	}
	
	public String productDetail(String prod_no) {
		String str = "";	
		JSONObject jsonObj = new JSONObject();
		try {
			Product p = dao.selectByNo(prod_no);
			String prod_name = p.getProd_name();
			int prod_price = p.getProd_price();
			String prod_detail = p.getProd_detail();
			System.out.println("상품설명: "+prod_detail);
			jsonObj.put("prod_no", prod_no);
			jsonObj.put("prod_name", prod_name);
			jsonObj.put("prod_price", prod_price);
			jsonObj.put("prod_detail", prod_detail);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			str = jsonObj.toString();
			return str;	
			
		//simple JSON --> jackson으로 바꾸기
//		Map<String, Object> map = new HashMap<>();
//		
//		try {
//			Product p = dao.selectByNo(prod_no);
//			map.put("status", 1);
//			map.put("product", p);
//		}catch(NotFoundException e) {
//			map.put("status", -1);
//			e.printStackTrace();
//		} 
//		
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			return mapper.writeValueAsString(map);
//		}catch(JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
		
	}
}
