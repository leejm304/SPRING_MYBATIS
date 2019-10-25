package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;
import com.my.vo.Post;

@Repository
public class CustomerDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public Customer selectById(String id) throws NotFoundException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Customer c = sqlSession.selectOne("com.my.vo.Customer.selectById",id);
		if(c==null) {
			throw new NotFoundException("아이디가 존재하지 않습니다.");
		}
		sqlSession.close();
		return c;
	}

	public int insertCustomer(Customer c) throws AddException {
		Connection conn = null;
		int rs = 0;
		PreparedStatement stmt = null;
		
		String SQL = "insert into customer values(?,?,?,?,?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "password");

			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, c.getId());
			stmt.setString(2, c.getPwd());
			stmt.setString(3, c.getName());
			stmt.setString(4, c.getPost().getBuildingno());
			stmt.setString(5, c.getAddr());

			rs = stmt.executeUpdate();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			throw new AddException(e.getMessage());
		} catch (SQLException e) {
			throw new AddException(e.getMessage());
		} finally {
			try {
				stmt.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (rs); // 1이면 변경 완료

	}

}
