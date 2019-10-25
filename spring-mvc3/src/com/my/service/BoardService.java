package com.my.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.my.dao.BoardDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Board;
import com.my.vo.PageBean;

public class BoardService {

	private BoardDAO dao;
	public BoardService() {
	      dao = new BoardDAO();
	}

//	public List<Board> boardList() throws NotFoundException{
//		//return dao.select(1, 10);
//		return boardList(1); // 매개변수없이 호출되면 무조건 1페이지 보여준다.
//	}
	
//	public List<Board> boardList(int currentPage) throws NotFoundException{
//		//int cntPerPage = 10; //한페이지당 보여줄 목록수
//		int cntPerPage = 3; //한페이지별 보여줄 목록수
//		//currentPage startRow endRow
//		//     1         1       10
//		//     2        11       20
//		//     3        21       30
//		int startRow = (currentPage-1)*cntPerPage+1;
//		int endRow = currentPage*cntPerPage;
//		return dao.select(startRow, endRow);
//	}	
//	
//		
//   public int maxPage() throws NotFoundException {
//	    int cntPerPage=3;
//	    int totalCnt = dao.count();
//	    return (int)(Math.ceil((float)totalCnt/cntPerPage));
//   }
	
	public com.my.vo.PageBean boardList(int currentPage)
	throws NotFoundException{
		int cntPerPage = 3;//한페이지별 보여줄 목록수
		int cntPageGroup = 4;//페이지 그룹에서 보여줄 페이지수 
		int startRow = (currentPage-1)*cntPerPage+1;
		int endRow = currentPage*cntPerPage;
		List<Board> list = dao.select(startRow, endRow);
		int totalCnt = dao.count();
		int maxPage = (int)(Math.ceil((float)totalCnt/cntPerPage));
		
		//페이지그룹의 시작페이지, 끝페이지 계산
		//현재페이지 시작페이지 끝페이지
		//   1       1      4
		//   2       1      4  
		//   3       1      4
		//   4       1      4
		//   5       4      8
		//   6       4      8
		//   7       4      8
		//   8       4      8
		int startPage;
		int endPage;
		startPage = ((currentPage-1)/cntPageGroup)*cntPageGroup+1;
		endPage = startPage + cntPageGroup - 1 ;
		if(endPage>maxPage){
			endPage = maxPage;
		}
		PageBean<Board> pb = new PageBean<>();
		pb.setCurrentPage(currentPage);//현재페이지
		pb.setCntPerPage(cntPerPage);//페이지별 목록수
		pb.setList(list);
		pb.setTotalCnt(totalCnt);
		pb.setMaxPage(maxPage);
		pb.setStartPage(startPage);
		pb.setEndPage(endPage);
		return pb;
	}
	
	public Board boardDetail(int no) throws NotFoundException{
		Board board = dao.selectByBoardNo(no);
		return board;
	  }
	
	public String boardWrite(Board b){
		try{
			dao.insert(b);
			return "등록 성공";
		} catch(AddException e){
			return "등록 실패";
		}
	}
	
	public String boardReply(Board b){
		int status = -1;
		String msg = "답글쓰기 실패";
		try{
			dao.insert(b);
			status = 1;
		} catch(AddException e){
			msg += e.getMessage();
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("msg", msg);
		String str = jsonObj.toString();
		return str;
	}
	
	public String boardPwdChk(int board_no, String iptPwd) {
		int status = -1;
		String board_pwd = "";
		try {
			Board board = dao.selectByBoardNo(board_no);
			board_pwd = board.getBoard_pwd();
			System.out.println(board_pwd);
			if(iptPwd.equals(board_pwd)){
				status = 1;
			}
		} catch(NotFoundException e){
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
	}

//	  public static void main(String[] args) throws NotFoundException{ 
//		  //1,4 1*cntPageGroup-(cntPageGroup-1), 1*cntPageGroup         
//		  //5,8 2*cntPageGroup-(cntPageGroup-1), 2*cntPageGroup
//		    int startPage; 
//			int endPage;
//			int cntPageGroup = 4;
//			int totalCnt = 10;
//			for(int currentPage = 1; currentPage<=10; currentPage++) {
//			startPage = (int)Math.floor((float)(currentPage-1)/cntPageGroup)*cntPageGroup+1;	
//			endPage = startPage + cntPageGroup - 1 ;
//			if(endPage>totalCnt){
//				endPage = totalCnt;
//			}
//			System.out.println(currentPage+", "+startPage+", "+endPage);		  
//			}
//	  }
	 
}
