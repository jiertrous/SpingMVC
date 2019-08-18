package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Product;
import com.gyf.bookstore.service.ProductService;

@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
		//1.获取 id
		String id = request.getParameter("id");
		
		//2.通过id查询数据库对应商品dao、service
		ProductService ps = new ProductService();
		Product product = ps.findBook(id);
		
		//3.把商品存在request，跳转到product_info_.jsp进行数据展示
		request.setAttribute("product", product);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	
		
		}
}

	

