package com.gyf.bookstore.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/active")
public class ActiveCode extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("content-type", "text/html;charset-utf-8");
		//1.获取参数
		String activecode = request.getParameter("activeCode");
		
		//2.记过
		UserService us = new UserService();
		try {
			us.activeUser(activecode);
			response.getWriter().write("激活成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
}
