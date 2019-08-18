package com.gyf.bookstore.model;

import java.util.Date;
import java.util.List;

public class Order {
	
	private String id;
	private double money;    //总价
	private String receiverAddress;  //收件人地址
	private String receiverName;   //收件人姓名
	private String receiverPhone;   //收件人电话
	private String paystate;   //0未支付  1已支付
	private Date ordertime;  //订单时间
	//private int user_id;
	
	private List<OrderItem> items ;  //订单下所有的商品
	
	/**
	 * 如果灬是一个阿我见关系，一般都是设置成对象
	 */
	private User user;     //显示订单是谁的，方便拿到订单的主人

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getPaystate() {
		return paystate;
	}

	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", receiverAddress=" + receiverAddress + ", receiverName="
				+ receiverName + ", receiverPhone=" + receiverPhone + ", paystate=" + paystate + ", ordertime="
				+ ordertime + ", user=" + user + "]";
	}
}
