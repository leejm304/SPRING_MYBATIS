package com.my.vo;

import java.util.Date;

public class Board {
	/*
	 * board_no number CONSTRAINT board_pk PRIMARY KEY, parent_no number CONSTRAINT
	 * board_fk REFERENCES board(board_no), board_subject varchar2(30) NOT NULL,
	 * board_writer varchar2(30) NOT NULL, board_content varchar2(300) NOT NULL,
	 * board_time date NOT NULL, board_pwd varchar2(10) NOT NULL
	 */
	private int board_no;
	private int  parent_no;
	private int level;
	private String board_subject;
	private String board_content;	
	private String board_writer;
	private Date board_time;
	private String board_pwd;
	
	public Board() {
		super();
	}
	public Board(String board_subject, String board_writer, String board_pwd, String board_content) {
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_writer = board_writer;
		this.board_pwd = board_pwd;
	}


	public Board(int board_no, int parent_no, int level, String board_subject, String board_content,
			String board_writer, Date board_time, String board_pwd) {
		super();
		this.board_no = board_no;
		this.parent_no = parent_no;
		this.level = level;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_writer = board_writer;
		this.board_time = board_time;
		this.board_pwd = board_pwd;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public Date getBoard_time() {
		return board_time;
	}

	public void setBoard_time(Date board_time) {
		this.board_time = board_time;
	}

	public String getBoard_pwd() {
		return board_pwd;
	}

	public void setBoard_pwd(String board_pwd) {
		this.board_pwd = board_pwd;
	}
	
	
}