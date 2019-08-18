package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Product;
import com.gyf.bookstore.service.ProductService;

/**
 * 更新购物车数量
 * @author 党
 *
 */
@WebServlet("/changeNum")
public class ChangeNumServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
	//1.获取参数
	String id = request.getParameter("id");   //商品id
	String num = request.getParameter("num");  //购物数量
	
	//2.更新书购车数据
	//2.1通过id查找商品
	ProductService ps = new ProductService();
	Product p = ps.findBook(id);
	
	//判断购物车的数据与库存的数据是否相同
	//http://localhost:8080/bookstore/changeNum?num=-10&&id=1    修改数据
	
	//2.2通过id更新ession数据
	@SuppressWarnings("unchecked")   //忽略警告 不检查map数据
	Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
	
	//替换
	if (cart.containsKey(p)) { //判断是否有这个商品，安全
		if ("0".equals(num)) {  //移除商品
			cart.remove(p);
		}else {
			cart.put(p, Integer.parseInt(num));
		}
	}
		//更新保存到session
		request.getSession().setAttribute("cart", cart);
		
		//回到购物车页面
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}
}
