package com.gyf.bookstore.model;

import java.util.List;

public class OrderItem {
	//封装
	
	private int buynum; 	//购买的数量
	private Order order; //订单
	private Product product;  //商品
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
