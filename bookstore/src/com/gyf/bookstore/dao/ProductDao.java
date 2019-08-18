package com.gyf.bookstore.dao;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gyf.bookstore.model.Product;
import com.gyf.bookstore.utils.C3P0Utils;

public class ProductDao {

	/**
	 * @param category 如果是空，是表的所有记录数
	 * @return
	 * @throws SQLException
	 */
	public long count(String category) throws SQLException {
		// 1.定义记录数变量0
		long count = 0;
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

		// String sql = "select count(*) from products where category = ?";
		String sql = "select count(*) from products where 1=1";
		if (category != null && !"".equals(category)) {
			sql += " and category = ?";
			count = (long) qr.query(sql, new ScalarHandler(), category);
		} else {
			count = (long) qr.query(sql, new ScalarHandler());
		}

		return count;
	}

	/**
	 * @param category 类别
	 * @param page     当前页
	 * @param pageSize 每页显示的条数页
	 * @return
	 */
	public List<Product> findBooks(String category, int page, int pageSize) throws SQLException {
		// 拼接sql和参数
		String sql = "select * from products where 1=1";

		List<Object> params = new ArrayList<Object>();

		if (category != null && !"".equals(category)) {
			sql += " and category =  ?";
			params.add(category);
		}
		sql += " limit ?,?";
		int start = (page - 1) * pageSize;
		int length = pageSize;
		params.add(start);
		params.add(length);

		// 执行
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), params.toArray());
	}

	/*
		通过ID查找商品
	*/
	public Product findBook(String id) throws SQLException {

		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from products where id = ?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),id);
	}
	
	
	public static void main(String[] args) throws SQLException {
		//测试方法可否能用
		ProductDao dao = new ProductDao();
		String category = "文学";

		long count = dao.count(category);
		List<Product>books = dao.findBooks(category, 1, 4);
		  for(Product p : books) {
			 System.out.println(p);
		}
		System.out.println(count);
	}
	

	/**
	 * @param productId   商品的id
	 * @param num   减的数量
	 * @throws SQLException
	 */
	public void updatePnum(int productId,int num) throws SQLException {
		String sql = "update products set pnum = pnum - ? where id = ?";
//		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
//		qr.update(sql,num,productId);
		QueryRunner qr = new QueryRunner();
		
		//执行sql语句要加上事务
		qr.update(sql,num,productId);
		
	}
	
	/*
	 * //首页书名搜索 public Product findBookByName (String name) throws SQLException {
	 * QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource()); String sql =
	 * "select * from products where name like '%?%'"; return qr.query(sql, new
	 * BeanHandler<Product>(Product.class), name);
	 * 
	 * }
	 */
	
}
