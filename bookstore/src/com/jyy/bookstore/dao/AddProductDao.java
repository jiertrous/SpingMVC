package com.jyy.bookstore.dao;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.gyf.bookstore.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import com.gyf.bookstore.model.Order;
import com.gyf.bookstore.model.OrderItem;
import com.gyf.bookstore.utils.C3P0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;
public class AddProductDao {
	
	public List<Product> findAllBooks() throws SQLException{
		//1.创建QueryRunner
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		return qr.query("select * from products ", new BeanListHandler<Product>(Product.class));
	}
	
	public void addProduct(Product product) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into products (name,price,category,pnum,description) values (?,?,?,?,?)";
		
		//定义一个数组参数
		Object[] params = new Object[5];

		params[0] = product.getName();
		params[1] = product.getPrice();
		params[2] = product.getCategory();
		params[3] = product.getPnum();
		params[4] = product.getDescription();
		
		qr.update(sql,params);
//		qr.update(sql,product.getName(),product.getPrice(),product.getCategory(),product.getPnum(),product.getDescription());
	}
	
	/**
	 * 更新书的数据
	 * @param product
	 * @throws SQLException 
	 */
	public void  updateProduct(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update products set name=?,price=?,pnum=?,category=?,description=? where id = ? ";
		
		//执行
		qr.update(sql,product.getName(),product.getPrice(),product.getPnum(),product.getCategory(),product.getDescription(),product.getId());
		
	}

	public void deleteProductById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "delete from products where id = ? ";
		
		//执行
		qr.update(sql,id);
	
		
	}

	public List<Product> findProduct(String id, String name, String category, String minprice, String maxprice) throws SQLException {
		//1.查询 简历连接        ? 不好操作，不确定值   where后不可加and  1=1永远成立
		String sql =  "select * from products where 1=1 ";
		if (!"".equals(id)) {
			sql += " and id = '" + id +"'";
		}
		if (!"".equals(name)) {
			sql += " and name like '%" + name + "%'";
		}
		if (!"".equals(category)) {
			sql += " and category = '" + category +"'";
		}
		if (!"".equals(minprice)) {
			sql += " and price >= " + minprice ;
		}
		if (!"".equals(maxprice)) {
			sql += " and price <= " + maxprice ;
		}
		
		System.out.println(sql);
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
		
		
	}
}
