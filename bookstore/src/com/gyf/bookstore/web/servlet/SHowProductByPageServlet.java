package com.gyf.bookstore.web.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.PageResult;
import com.gyf.bookstore.model.Product;
import com.gyf.bookstore.service.ProductService;

@WebServlet("/showProductByPage")
public class SHowProductByPageServlet extends HttpServlet{
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");//客户端网页控制为UTF-8
		//1.获取参数
		String category = request.getParameter("category");
		String pageStr = request.getParameter("page");
		int page = 1;
		if (pageStr != null && !"".equals(pageStr)) {
			//把字符串转成int
			page = Integer.parseInt(pageStr);
		}
		
		//2.调用service
		ProductService ps = new ProductService();
		PageResult<Product> pageResult = ps.findBooks(category,page);
		
		//3.存在request里
		request.setAttribute("pageResult", pageResult);
		request.setAttribute("category", category);
		
		//4.跳转页面
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}
}
