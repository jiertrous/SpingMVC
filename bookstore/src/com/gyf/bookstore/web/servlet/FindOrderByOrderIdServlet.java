package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Order;
import com.gyf.bookstore.service.OrderService;

/**
 * 订单详情的Servlet
 * @author 党
 *
 */
@WebServlet("/findOrderByOrderId")
public class FindOrderByOrderIdServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("application/octet-stream;");  
	request.setCharacterEncoding("UTF-8");
	response.setContentType("textml;charset=utf-8"); 
	response.setHeader("content-type","textml;charset=utf-8");
	response.setCharacterEncoding("UTF-8");
	
	//1.获取Orderid
	String orderId = request.getParameter("orderId");
	//2.调用service
	OrderService os = new OrderService();
	Order order = os.findOrderByOrder(orderId);
	//3.转发到orderInfo.jsp【显示数据】
	request.setAttribute("order", order);
	request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
	}
}
