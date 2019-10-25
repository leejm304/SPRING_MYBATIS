package com.my.vo;

import java.sql.Timestamp;
import java.util.List;

public class OrderInfo {

	public OrderInfo() {
		super();
	}	
	
	public OrderInfo(int order_no, Customer customer, Timestamp order_time, List<OrderLine> orderLines) {
		super();
		this.order_no = order_no;
		this.customer = customer;
		this.order_time = order_time;
		this.orderLines = orderLines;
	}

	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	private int order_no;
	private Customer customer; // private String order_id; 
	// 어느 쪽(many입장에서 또는 1입장에서)에서 has a 관계를 가지것인가? output의 형태에 따라 선택
	private Timestamp order_time;
	private List<OrderLine> orderLines; // 1에서 many를 갖는 것으로 표현
}
