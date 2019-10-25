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
import com.my.vo.Customer;
import com.my.vo.Post;

public class BoardDAO {

	public int count() throws NotFoundException {
		// select count(*) from board
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int pageNum = 0;

		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "ora_user";
			String DB_PW = "lee";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);

			String SQL = "select count(*) from board";
			stmt = conn.prepareStatement(SQL); // sql구문 송신
			rs = stmt.executeQuery(); // sql구문 실행
			/*
			 * if (rs.next()) { pageNum = rs.getInt("count(*)"); } count()함수는 값이 없으면
			 * exception 발생시키기 때문에 if 쓸 필요도 없어
			 */
			rs.next();
			return rs.getInt(1); // 1은 인덱스 위치
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	public List<Board> select(int startRow, int endRow) throws NotFoundException {
		List<Board> list = new ArrayList<Board>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "ora_user";
			String DB_PW = "lee";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);

			String SQL = "SELECT a.*\r\n" + "FROM\r\n" + "(SELECT rownum rn, level, board.* \r\n" + "FROM board \r\n"
					+ "START WITH parent_no is null\r\n" + "CONNECT BY PRIOR board_no = parent_no\r\n"
					+ "ORDER SIBLINGS BY board_no) a\r\n" + "WHERE a.rn between ? and ?";
			stmt = conn.prepareStatement(SQL); // sql구문 송신
			stmt.setInt(1, startRow);
			stmt.setInt(2, endRow);
			rs = stmt.executeQuery(); // sql구문 실행
			while (rs.next()) {
				int board_no = rs.getInt("board_no");
				int parent_no = rs.getInt("parent_no");
				int level = rs.getInt("level");
				String board_subject = rs.getString("board_subject");
				String board_writer = rs.getString("board_writer");
				String board_content = rs.getString("board_content");
				Date board_time = rs.getDate("board_time");
				String board_pwd = rs.getString("board_pwd");

				Board board = new Board(board_no, parent_no, level, board_subject, board_writer, board_content,
						board_time, board_pwd);
				list.add(board);
			}
			if (list.size() == 0) {
				throw new NotFoundException("게시물 목록이 없습니다.");
			}
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return list;
	}

	public Board selectByBoardNo(int no) throws NotFoundException {
		Board board = new Board();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "ora_user";
			String DB_PW = "lee";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);

			String SQL = "select board_no, parent_no, board_subject, board_writer, board_content, board_time, board_pwd from board where board_no = ?";
			stmt = conn.prepareStatement(SQL); // sql구문 송신
			stmt.setInt(1, no);
			rs = stmt.executeQuery(); // sql구문 실행
			if (rs.next()) {
				int board_no = rs.getInt("board_no");
				int parent_no = rs.getInt("parent_no");
				String board_subject = rs.getString("board_subject");
				String board_writer = rs.getString("board_writer");
				String board_content = rs.getString("board_content");
				Date board_time = rs.getDate("board_time");
				String board_pwd = rs.getString("board_pwd");
				board.setBoard_no(board_no);
				board.setParent_no(parent_no);
				board.setBoard_subject(board_subject);
				board.setBoard_writer(board_writer);
				board.setBoard_content(board_content);
				board.setBoard_time(board_time);
				board.setBoard_pwd(board_pwd);
			}

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return board;
	}

	public void insert(Board board) throws AddException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "ora_user";
			String DB_PW = "lee";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);

			if (board.getParent_no() == 0) {
				String SQL = "INSERT INTO board VALUES(board_seq.NEXTVAL, null, ?, ?, ?, SYSDATE, ?)";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, board.getBoard_subject());
				stmt.setString(2, board.getBoard_writer());
				stmt.setString(3, board.getBoard_content());
				stmt.setString(4, board.getBoard_pwd());
				stmt.executeUpdate();

			} else if (board.getParent_no() != 0) {
				String SQL = "INSERT INTO board VALUES(board_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?)";
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, board.getParent_no());
				stmt.setString(2, board.getBoard_subject());
				stmt.setString(3, board.getBoard_writer());
				stmt.setString(4, board.getBoard_content());
				stmt.setString(5, board.getBoard_pwd());
				stmt.executeUpdate();
			}

		} catch (Exception e) {
			throw new AddException(e.getMessage());

		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				if (rs != null && !rs.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * <단위test> public static void main(String[] args) { BoardDAO dao = new
	 * BoardDAO(); try { System.out.println(dao.count()); } catch (NotFoundException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

}
