package com.jyy.bookstore.servlet.ProductServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Product;
import com.jyy.bookstore.service.AddProductService;

@WebServlet("/productlist")
public class ProductListServlet extends  HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//调用业务方法
		AddProductService productService = new AddProductService();
		List<Product> products = productService.findAllBooks();
		
		//2.把数据放在请求对象中
		request.setAttribute("products",products);
		
		//3.进入admin/products/list.jsp
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		
	}
}
