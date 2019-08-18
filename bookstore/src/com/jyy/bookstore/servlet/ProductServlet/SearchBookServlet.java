package com.jyy.bookstore.servlet.ProductServlet;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Product;
import com.jyy.bookstore.service.AddProductService;

@WebServlet("/searchbook")
public class SearchBookServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//1.接收参数，不放在bean区间，模型无区间价格
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String category = request.getParameter("category");
	String minprice = request.getParameter("minprice");
	String maxprice = request.getParameter("maxprice");
	
	//2.调用service，  根据条件查询多个条件
	AddProductService findbook = new AddProductService();
	List<Product> products = findbook.findProduct(id,name,category,minprice,maxprice);
	
	//3.返回结果到list.jsp页面
	request.setAttribute("products",products);
	request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	
	}
}
