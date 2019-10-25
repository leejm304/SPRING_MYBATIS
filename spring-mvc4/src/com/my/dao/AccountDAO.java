package com.my.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDAO {
	@Autowired
	private SqlSession sqlSession;	
	@Transactional
	public void account(){
		HashMap<String, Object> map = new HashMap<>();
		String no1 ="101";
		map.put("no", no1);
		map.put("amount", 10);
		int rowCnt1 = 
			sqlSession.update("com.my.vo.Account.withdraw",map);
		if(rowCnt1 == 0) {
			throw new RuntimeException(no1+"계좌가 없어서 출금오류");
		}		
		map = new HashMap<>();
		//map.put("no", "102"); //
		
		String no2 = "999";
		map.put("no", no2); //
		map.put("amount", 10);

		//내부에서 uncheckedexception발생 - 자동롤백되어야한다.
		int rowCnt2 =sqlSession.update("com.my.vo.Account.deposit",	map);
		if(rowCnt2 == 0) {
			throw new RuntimeException(no1+"계좌가 없어서 입금오류");
		}
	}
}