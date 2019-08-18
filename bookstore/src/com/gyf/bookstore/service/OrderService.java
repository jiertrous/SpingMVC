package com.gyf.bookstore.service;

import java.sql.SQLException;


import java.util.List;

import com.gyf.bookstore.dao.OrderDao;
import com.gyf.bookstore.dao.OrderItemDao;
import com.gyf.bookstore.dao.ProductDao;
import com.gyf.bookstore.model.Order;
import com.gyf.bookstore.model.OrderItem;
import com.sun.org.glassfish.gmbal.ManagedAttribute;

public class OrderService {
	/**
	 * 添加订单业务方法
	 * @param order
	 */
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	private ProductDao productDao = new ProductDao();
	 
	public void createOrder(Order order) {
		try { 
			//创建订单需要添加事物，一旦某个sql语句失败，数据库存储失败
			//开启事物
		//	ManagerThreadLocal.beginTransaction();
			
			//1.插入订单表
			orderDao.add(order);
			
			//2.插入订单详情表
			orderItemDao.addOrderItems(order.getItems());
			
			//3.订单成功，减少库存
			for(OrderItem item : order.getItems()) {
				productDao.updatePnum(item.getProduct().getId(), item.getBuynum());
			}
			
		//结束事物
		//ManagerThreadLocal.commitTransaction();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//事物回滚
		//	ManagerThreadLocal.rollbackTransaction();
			
		}
	}
	
	public List<Order> findOrderByUserId(String userid){
		try {
			return orderDao.findOrderByUserId(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过订单id找Order
	 * @param userid
	 * @return
	 */
	public Order findOrderByOrder(String orderId){
		try {
			return orderDao.findOrderByOrderId(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
