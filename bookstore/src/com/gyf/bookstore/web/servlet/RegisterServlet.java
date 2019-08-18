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

@WebServlet("/register")
public class RegisterServlet  extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
		
		//教研验证码
		//获取表单的验证码
		String checkcode_client = request.getParameter("checkcode");
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		//System.out.println("checkcode_client" + checkcode_client);
		//System.out.println("checkcode_session" + checkcode_session);
		if (!checkcode_client.equals(checkcode_session)) {
			//客户端提交的验证码与服务器不一样,跳回注册页面
			request.setAttribute("checkcode_err", "验证码不一致");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		
		//1.吧参数转成bean，model
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			System.out.println(user);
			
			//给无数据的属性赋值
			user.setActiveCode(UUID.randomUUID().toString());//激活码
			user.setRole("普通用户 "); //角色
			user.setRegistTime(new Date());
			System.out.println(user);

			//2.注册
			UserService us = new UserService();
			us.register(user);
			
			//3.返回结果
			//3.1注册成功 进入成功页面
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
		
		
		}catch (UserException e) {
			e.printStackTrace(); 
			//3.2注册失败，进入注册页面
			request.setAttribute("register_err", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		catch (Exception e) {
			System.out.println("转模型失败");
			e.printStackTrace();
		}
	}

	private Object getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
