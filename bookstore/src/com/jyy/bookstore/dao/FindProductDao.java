package com.jyy.bookstore.dao;

import java.awt.print.Book;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.gyf.bookstore.model.Product;
import com.gyf.bookstore.utils.C3P0Utils;

public class FindProductDao {
	
	/**
	 * 通过id找到书
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Product findProductById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from products where id =?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),id);
	}
 }
