package com.my.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.my.dao.BoardDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Board;
import com.my.vo.PageBean;
import com.sun.glass.ui.CommonDialogs.Type;

public class BoardService {
	BoardDAO dao;

	public BoardService() {
		dao = new BoardDAO();
	}

	public com.my.vo.PageBean<Board> boardlist(int currentPage) throws NotFoundException {
		int cntPerPage = 5;
		int cntPerPageGroup = 4;
		int startRow = ((currentPage - 1) * cntPerPage) + 1;
		int endRow = currentPage * cntPerPage;
		List<Board> list = dao.select(startRow, endRow);

		int totalCnt = dao.count();
		int maxPage = (int) (Math.ceil((float) totalCnt / cntPerPage));

		int startPage = ((int) (currentPage - 0.1) / cntPerPageGroup) * cntPerPageGroup + 1;
		int endPage = startPage + cntPerPageGroup - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageBean<Board> pb = new PageBean<>();
		pb.setCurrentPage(currentPage);// 현재페이지
		pb.setCntPerPage(cntPerPage);// 페이지별 목록수
		pb.setList(list);// 목록
		pb.setTotalCnt(totalCnt); // 총건수
		pb.setMaxPage(maxPage); // 최대페이지수
		pb.setStartPage(startPage);
		pb.setEndPage(endPage);
		return pb;
	}

	public Board boardDetail(int no) throws NotFoundException {
		Board board = dao.selectByBoardNo(no);
		return board;
	}

	public String boardwrite(Board b) {
		int status = -1;
		JSONObject json = new JSONObject();
		try {
			dao.insert(b); // Exception 없으면 성공
			json.put("msg", "성공입니다.");
			status = 1;
		} catch (AddException e) {
			json.put("msg", "실패입니다.");
		}
		json.put("status", status);
		String str = json.toString();
		return str;
	}

	public String boardreply(Board b) {// 답글쓰기
		int status = -1;
		JSONObject json = new JSONObject();
		try {
			dao.insert(b);
			status = 1;

		} catch (AddException e) {
			e.printStackTrace();
			json.put("msg", "실패입니다.");
		}
		json.put("status", status);
		String str = json.toString();
		return str;

	}

	public String boardPwdChk(int board_no, String board_pwd) {
		int status = 0;
		try {
			Board board = dao.selectByBoardNo(board_no);
			if (board.getBoard_pwd().equals(board_pwd)) {
				status = 1;
			}
		} catch (NotFoundException e) {
			status = -1;
			e.printStackTrace();
		}
		System.out.println("status="+status);
		JSONObject json = new JSONObject();
		json.put("status", status);
		String str = json.toString();
		return str;
	}
}
