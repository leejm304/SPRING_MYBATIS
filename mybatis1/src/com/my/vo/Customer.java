package com.my.vo;

public class Customer {
	private String id;
	private String pwd;
	private String name;
	//private String buildingno; 
	//class관계(객체와 객체와의 관계)도 table관계처럼 표현  ex.1:1, 1:다
	private Post post;
	private String addr2;
	
	//생성자 : 매개변수 없는 생성자, 모든 인스턴스변수 초기화하는 생성자
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//setter, getter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getAddr() {
		return addr2;
	}
	public void setAddr(String addr2) {
		this.addr2 = addr2;
	}

}
