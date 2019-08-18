package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.exception.UserException;
import com.gyf.bookstore.model.User;
import com.gyf.bookstore.service.UserService;

@WebServlet("/findUserById")
public class FindUserByServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
		//1.获取参数
		String userid = request.getParameter("id");
		//2.从数据库查询
		UserService us = new UserService();
		try {
			User user = us.findUserId(userid);
			/*
			 * EL表達式取數據順序：page，request，session,application 
			*/
			//3.放在request中
			request.setAttribute("modifyuser", user);
			
			//4.回到modifyuserinfo.jsp显示数据
			
			request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
		

	}
}
