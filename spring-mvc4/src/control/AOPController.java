package control;

import java.util.HashMap;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dao.AccountDAO;

@Controller
public class AOPController {

	@Autowired
	private SqlSession sqlSession;	
	
	@Autowired
	private AccountDAO dao;
	
//	@Autowired
//	private DataSource dataSource;

	@GetMapping("/account")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED)
	public String account() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("no", "101");
		map.put("amount", 10);
		int rowCnt1 = sqlSession.update("com.my.vo.Account.withdraw", map);
		if(rowCnt1 == 0) {
			throw new RuntimeException("출금오류");
		}
		
		map = new HashMap<>();
		//map.put("no", "102");
		map.put("no", "999");
		map.put("amount", 10);
		int rowCnt2 = 
				sqlSession.update("com.my.vo.Account.deposit", map);
		if(rowCnt2 == 0) {
			throw new RuntimeException("출금오류");
		}

		return "계좌이체";
	}

// DataSource를 Autowired 했을 때 트랜젝션 사용 불가(세션 매번 생성)
//	public String account() {
//		//service.XX();
//		Connection con = null;
//		PreparedStatement pstmt1 = null;
//		PreparedStatement pstmt2 = null;
//		try {
//			con = dataSource.getConnection();
//			con.setAutoCommit(false);
//			String sql1 = "UPDATE account SET balance = balance-10 WHERE no = ?";
//			pstmt1 = con.prepareStatement(sql1);
//			pstmt1.setString(1, "101");
//
//			int rowCnt1 = pstmt1.executeUpdate(); // 성공
//
//			String sql2 = "UPDATE account SET balance = balance+10 WHERE no = ?";
//			pstmt2 = con.prepareStatement(sql2);
//			//pstmt2.setString(1, "102");
//			pstmt2.setString(1, "999");
//			int rowCnt2 = pstmt2.executeUpdate();
//
//			if (rowCnt1 == 1 && rowCnt2 == 1) {
//				con.commit();
//			} else {
//				con.rollback();
//			}
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//
//		return "계좌이체";
//	}
}
