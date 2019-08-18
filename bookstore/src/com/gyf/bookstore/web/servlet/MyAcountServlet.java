package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.User;

@WebServlet("/myaccount")
public class MyAcountServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
		//如果登陆就进入myccount.jsp
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/myAccount.jsp");
		}else {
		//未登录进入登陆页面 login.jsp
			response.sendRedirect("login.jsp");
			
		}	
	}
}
