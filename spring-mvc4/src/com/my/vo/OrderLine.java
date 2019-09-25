package com.my.vo;

public class OrderLine {
	private int order_no;
//	private OrderInfo orderInfo; //many has a one 표현
//	private String order_prod_no;
	private Product product;
	private int order_quantity;

	public OrderLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderLine(int order_no, Product product, int order_quantity) {
		super();
		this.order_no = order_no;
		this.product = product;
		this.order_quantity = order_quantity;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	

}
