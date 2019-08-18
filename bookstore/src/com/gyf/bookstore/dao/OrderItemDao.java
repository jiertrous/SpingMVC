package com.gyf.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.gyf.bookstore.model.OrderItem;
import com.gyf.bookstore.utils.C3P0Utils;

public class OrderItemDao {
	
	
	/**
	 * 添加订单详情
	 * @param items
	 * @throws SQLException 
	 */
	public void addOrderItems(List<OrderItem> items) throws SQLException {
		String sql = "insert into orderitem (order_id,product_id,buynum) values(?,?,?)";
		
		//参数 遍历
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		for(OrderItem item : items) {
			qr.update(sql,item.getOrder().getId(),item.getProduct().getId(),item.getBuynum());
		}
		
		
	}
}
