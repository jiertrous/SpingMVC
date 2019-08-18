package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.User;

/**
 * 结账的功能
 * @author 党
 *
 */
@WebServlet("/settleAccount")
public class SettleAccountServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("application/octet-stream;");  
	request.setCharacterEncoding("UTF-8");
	response.setContentType("textml;charset=utf-8"); 
	response.setHeader("content-type","textml;charset=utf-8");
	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
//	判断当前浏览器是否有登陆
	User user = (User) request.getSession().getAttribute("user");
//	如果有登陆，进行订单页面
	if (user != null) {
		response.sendRedirect(request.getContextPath() + "/order.jsp");
	}else {
//		如果没有登陆，先登陆
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
}

}
