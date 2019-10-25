package com.my.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			
			jsonObj.put("prod_no", prod_no);
			jsonObj.put("prod_name", prod_name);
			jsonObj.put("prod_price", prod_price);
			jsonObj.put("prod_detail", prod_detail);
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		str = jsonObj.toString();
		return str;
	}
}
