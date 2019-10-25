package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Board;

public class BoardDAO {

	public void insert(Board b) throws AddException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "password");
			
			if(b.getParent_no()==0) {
				String SQL = "insert into board values(board_seq.nextval ,null, ?, ?,? ,SYSDATE,?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, b.getBoard_subject());
			stmt.setString(2, b.getBoard_writer());
			stmt.setString(3, b.getBoard_content());
			stmt.setString(4, b.getBoard_pwd());
			
			}else if(b.getParent_no()!=0) {
				String SQL = "insert into board values(board_seq.nextval ,?, ?, ?,? ,SYSDATE,?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, b.getParent_no());
			stmt.setString(2, b.getBoard_subject());
			stmt.setString(3, b.getBoard_writer());
			stmt.setString(4, b.getBoard_content());
			stmt.setString(5, b.getBoard_pwd());
			}

			int state = stmt.executeUpdate();
			if(state==0) throw new AddException();
			
		} catch (ClassNotFoundException e) {
			throw new AddException(e.getMessage());
		} catch (SQLException e) {
			throw new AddException(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Board selectByBoardNo(int no) throws NotFoundException{
		Board board = null;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "password");
			String SQL = "select level, board.* from board " + 
					"where board_no = ? " + 
					"start with parent_no is null " + 
					"connect by prior board_no = parent_no";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, no);

			rs = stmt.executeQuery();

			if (rs.next()) {
				int board_no = no;
				int parent_no = rs.getInt("parent_no");
				int level = rs.getInt("level");
				String board_subject = rs.getString("board_subject");
				String board_content = rs.getString("board_content");
				String board_writer = rs.getString("board_writer");
				Date board_time = rs.getDate("board_time");
				String board_pwd = rs.getString("board_pwd");
				board = new Board(board_no, parent_no, level, board_subject, board_content, board_writer,
						board_time, board_pwd);
			}
			else{
				throw new NotFoundException("게시물이 없습니다.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return board;
	}
	
	public List<Board> select(int startRow, int endRow) throws NotFoundException {
		List<Board> list = new ArrayList<Board>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "password");
			String SQL = "select a.* " + "from " + "(select rownum rn, level, board.* from board "
					+ "start with parent_no is null " + "connect by prior board_no = parent_no "
					+ "order SIBLINGS by board_no desc) a " + "where a.rn between ? and ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, startRow);
			stmt.setInt(2, endRow);

			rs = stmt.executeQuery();

			while (rs.next()) {
				int board_no = rs.getInt("board_no");
				int parent_no = rs.getInt("parent_no");
				int level = rs.getInt("level");
				String board_subject = rs.getString("board_subject");
				String board_content = rs.getString("board_content");
				String board_writer = rs.getString("board_writer");
				Date board_time = rs.getDate("board_time");
				String board_pwd = rs.getString("board_pwd");

				Board board = new Board(board_no, parent_no, level, board_subject, board_content, board_writer,
						board_time, board_pwd);
				list.add(board);
			}
			if (list.size() == 0) {
				throw new NotFoundException("게시목록이 없습니다.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int count() throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora_user", "password");
			String SQL = "select count(*) from board";
			stmt = conn.prepareStatement(SQL);

			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1); //index로 가져오기

		} catch (ClassNotFoundException | SQLException e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
