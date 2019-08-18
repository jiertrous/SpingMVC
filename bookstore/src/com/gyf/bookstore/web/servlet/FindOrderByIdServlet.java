package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Order;
import com.gyf.bookstore.model.User;
import com.gyf.bookstore.service.OrderService;

/**
 * 通过用户id查询订单
 * @author 党
 *
 *1.dao
 *2.service
 *3.servlet 调用service
 */

@WebServlet("/findOrderById")
public class FindOrderByIdServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		//1.获取用户id
		//从session中获取用户id
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.getWriter().write("非法访问：");
			return;
		}
		
		//2.调用service
		OrderService os = new OrderService();
		List<Order> orders = os.findOrderByUserId(user.getId() + "");  //加“” 字符串变SString形
		
		//3.存数据到request
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
		 

		
	}
}
 