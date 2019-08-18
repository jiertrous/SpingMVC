package com.gyf.bookstore.service;

import java.sql.SQLException;


import com.gyf.bookstore.dao.UserDao;
import com.gyf.bookstore.exception.UserException;
import com.gyf.bookstore.model.User;
import com.gyf.bookstore.utils.SendJMail;

public class UserService {
	
	
	//创建Dao
	UserDao userDao = new UserDao();
	public void register(User user) throws UserException {
		try {
			//往数据库添加用户
			userDao.addUser(user);
			String link = "http://localhost:8080/bookstore/active?activeCode=" + user.getActiveCode();
			
			String html = "<a href=\"" + link + "\">欢迎您注册网络书城账号，请点击这里激活</a>";
			System.out.println(html);
			//发送激活邮件
			SendJMail.sendMail(user.getEmail(), html);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("用户注册失败,账号已存在，请重新输入");
		}
	}
	
	//激活用户
	//不要在try里面写throw  否则在catch
	public void activeUser(String activeCode) throws UserException {
		User user = null;
		try {
			//1.查询激活码的用户是否存在
			user = userDao.findUserByActiveCode(activeCode);
		}
			catch (Exception e) {
				throw new UserException("激活失败");
			}
			
			if (user == null) {
				throw new UserException("非法的激活，用户不存在");
			}
			if (user != null && user.getState() == 1) {
				throw new UserException("用户已经激活！");
			}
			try {
				userDao.updateState(activeCode);
			} catch (Exception e) {
				throw new UserException("激活失败");
			}
		
		}
	
	public User login(String usernmae,String password) throws UserException {
		try 
			{//1.查询
			User user = userDao.findUserByUsernameAndPasswordUser(usernmae, password);
			
			//2.判断
			if (user == null) {
				throw new UserException("用户名或密码不正确");
			}
			if (user.getState() == 0) {
				throw new UserException("用户未激活，请先登陆邮箱激活账号");
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("登陆失败");
		}
	}
	
	public User findUserId(String id) throws UserException {
		try 
			{//1.查询
			User user = userDao.findUserById(id);
			
			//2.判断
			if (user == null) {
				throw new UserException("用户名不存在");
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("未知錯誤");
		}
	}
	
	public void modifyUserInfo(User user) throws UserException {
		try {
			userDao.updateUser(user);

		} catch (SQLException e) {

			
		}
	}
		
}

