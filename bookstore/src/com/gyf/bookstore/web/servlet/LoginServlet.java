package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gyf.bookstore.exception.UserException;
import com.gyf.bookstore.model.User;
import com.gyf.bookstore.service.UserService;
import com.mchange.v2.beans.BeansUtils;
import com.sun.net.httpserver.HttpServer;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
		
		//1.获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//2.调用service
		UserService us = new UserService();
		try {
			User user = us.login(username, password);
			//登陆成功就回到首页
			//保存user到session中
			request.getSession().setAttribute("user", user);
			//简单权限管理
			if ("管理员".equals(user.getRole())) {
				response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
				//进如后台
			}else {  
				//普通进入index页面
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}

			//request.getRequestDispatcher("/index.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/index.jsp");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//登录失败，回到登陆特免
			request.setAttribute("login_msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
