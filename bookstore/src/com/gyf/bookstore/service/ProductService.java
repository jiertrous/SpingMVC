package com.gyf.bookstore.service;

import java.sql.SQLException;
import java.util.List;


import com.gyf.bookstore.dao.ProductDao;
import com.gyf.bookstore.model.PageResult;
import com.gyf.bookstore.model.Product;

public class ProductService {

	ProductDao productDao = new ProductDao();
	ProductDao dao = new ProductDao();
	public PageResult<Product> findBooks(String category,int page) {
	 try {
		//创建模型
		 PageResult<Product> pr = new PageResult<Product>();
		 //2.设置总记录数		
		 long totalCount = productDao.count(category);
		 pr.setTotalCount(totalCount);
		 //3.总页数
		 //向上取整  2.5 = 3
		 int totalPage = (int) Math.ceil(totalCount * 1.0  / pr.getPageSize());
		 pr.setTotalPage(totalPage);
	
		 //4.设置当前页数
		 pr.setCurrentPage(page);
		 //5.设施数据list
		 	
		 List<Product> list = productDao.findBooks(category, page, pr.getPageSize()); 
		 pr.setList(list);
		 
		long totalProduct = dao.count(category);
		 return pr;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public Product findBook(String id) {
		try {
			return productDao.findBook(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
