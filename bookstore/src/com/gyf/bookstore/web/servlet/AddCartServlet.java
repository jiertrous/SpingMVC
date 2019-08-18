package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Product;
import com.gyf.bookstore.service.ProductService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import sun.text.normalizer.UTF16;

/**
 * 添加购物车
 *  难点：如何判断你当前购买的书在购物车已经存在？
 *  技巧：重写Product的equals方法。根据id判断就可以
 * @author 党
 *
 */
@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream;");  
		request.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=utf-8"); 
		response.setHeader("content-type","textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
	
		// 1.获取ID
		String id = request.getParameter("id");

		// 2.通过id查询数据库对应商品dao/service
		ProductService ps = new ProductService();
		Product p = ps.findBook(id);

		// 3.把购买的商品放在购物车Map
		// 3.1先从session获取购物车数据[cart]
		Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		// 3.2如果没有购物车数据，就创建一个map对象
		if (cart == null) {
			cart = new HashMap<Product, Integer>();
			cart.put(p, 1);
		} else {
			// 3.2判断map里面是否有当前想购买的商品
			// 没有直接为1，有p+1
			if (cart.containsKey(p)) {
				cart.put(p, cart.get(p) + 1);
			}else {
				cart.put(p, 1);
			}
		}
			//4.打印购物车的数据
		for(Entry<Product, Integer> entry : cart.entrySet() ) {
			System.out.println(entry.getKey() + "数量" + entry.getValue());
		}
		
			//5.存session
		request.getSession().setAttribute("cart", cart);
		
		//6.响应客户端
		//继续购物，查看购物车
		String a1 = "<a href=\"" + request.getContextPath() + "/showProductByPage\"> 继续购物</a>";
		String a2 = "&nbsp;&nbsp;<a href=\"" + request.getContextPath() + "/cart.jsp\"> 查看购物</a>";

		response.getWriter().write(a1);
		response.getWriter().write(a2);
		//request.getSession().setAttribute("cart", cart);
	}
	
	/*
	 * public static void main(String[] args) { //测试 Map<Product, Integer> cat = new
	 * HashMap<Product, Integer>(); ProductService ps = new ProductService();
	 * Product p1 = ps.findBook("2"); cat.put(p1, 1);
	 * 
	 * Product p2 = ps.findBook("2"); 默认情况下，key是根据地质来判断是否存在 所以要改比较的规则，重写equals方法
	 * 
	 * if (cat.containsKey(p2)) { System.out.println("p2已经在购物车中存在了"); }else {
	 * System.out.println("p2不存在"); } }
	 */
}
