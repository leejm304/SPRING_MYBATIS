package com.my.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.ZipDAO;
import com.my.exception.NotFoundException;

@Service
public class ZipService {
	
	@Autowired
	public ZipDAO dao;

	public String searchZip(String doro) {
		JSONArray jsonArr = new JSONArray();
		try {
			List<Map<String, String>> list = dao.selectByDoro(doro);
			// List로 받아온걸 JSON형 string으로 만들어줌
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				JSONObject json = new JSONObject();
				json.put("buildingno", map.get("buildingno"));
				json.put("zipcode", map.get("zipcode"));
				json.put("doro", map.get("doromyoung"));
				json.put("zibun", map.get("zibun"));
				jsonArr.add(json);
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		String str = jsonArr.toString();
		return str;

	}
}
