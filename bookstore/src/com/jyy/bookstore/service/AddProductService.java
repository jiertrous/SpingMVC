package com.jyy.bookstore.service;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

import com.gyf.bookstore.model.Product;
import com.jyy.bookstore.dao.AddProductDao;
import com.jyy.bookstore.dao.FindProductDao;

public class AddProductService {
	FindProductDao findDao = new FindProductDao();
	AddProductDao productDao = new AddProductDao();
	/**
	 * 添加书
	 * @param product
	 */
	public void addProduct(Product product) {
		try {
			productDao.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 显示书列表
	 * @return
	 */
	public List<Product> findAllBooks(){
		//1.创建dao对象

					
		//2.调用findAllBook的方法
		try {
			return  productDao.findAllBooks();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		}
	
	
	/**
	 * 查找书的id
	 * @param id
	 * @return
	 */
	public Product findProductById(String id)  {

		try {
			return findDao.findProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void updateProduct(Product product) {
		try {
			productDao.updateProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteProductById(String id) {
		try {
			productDao.deleteProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Product> findProduct(String id, String name, String category, String minprice, String maxprice) {
		try {
			return productDao.findProduct( id,  name,  category,  minprice,  maxprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
