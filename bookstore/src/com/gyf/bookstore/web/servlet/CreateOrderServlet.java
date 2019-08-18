package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gyf.bookstore.model.Order;
import com.gyf.bookstore.model.OrderItem;
import com.gyf.bookstore.model.Product;
import com.gyf.bookstore.model.User;
import com.gyf.bookstore.service.OrderService;
import com.mchange.v2.beans.BeansUtils;


/**
 * 生成订单
 * @author 党
 *
 */
@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
	  response.setCharacterEncoding("utf-8");
	  response.setContentType("application/octet-stream;");  
	  request.setCharacterEncoding("UTF-8");
	  response.setContentType("textml;charset=utf-8"); 
	  response.setHeader("content-type","textml;charset=utf-8");
	  response.setCharacterEncoding("UTF-8");
	  
	//获取session的user
	 User user = (User) request.getSession().getAttribute("user");
	 if (user == null) {
		response.getWriter().write("非法访问");
		return;
	}
	 
		//取购物车	
	 Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
	 if (cart == null || cart.size() == 0) {
		response.getWriter().write("购物车暂无商品");
	}
	//1.把数据封装
	//创建订单
	Order order = new Order();
	try {
		//1.1把请求参数封装成Order
		BeanUtils.populate(order, request.getParameterMap());
		
		//1.2补全order数据
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setUser(user);
		
		//1.3封装订单详情OrderItem【订单有n个详情】
		List<OrderItem> items = new ArrayList<OrderItem>();
		//取购物车	
		double totalPrice = 0;
		
			for(Entry<Product, Integer> entry : cart.entrySet()){
				OrderItem item = new OrderItem();
				//设置商品数量
				item.setBuynum(entry.getValue());
				//设置商品
				item.setProduct(entry.getKey());
				//设置订单
				item.setOrder(order);
				
				totalPrice += entry.getKey().getPrice() * entry.getValue();
				
				items.add(item);
			}
			
			//设置order中items
			order.setItems(items);

			//设置总价格
			order.setMoney(totalPrice);
			//打印订单
			System.out.println("-------------");
			System.out.println("订单：");			
			System.out.println(order);
			System.out.println("订单中的商品：");
			for(OrderItem item : items) {
				System.out.println("商品名称" + item.getProduct().getName() + "商品数量" + item.getBuynum());
			}
			
			//2.插入数据库
			OrderService os = new OrderService();
			os.createOrder(order);
			
			//2.订单成功，移除购物车数据
			request.getSession().removeAttribute("cart");
			
			//4.响应
			
			response.getWriter().write("下单成功");
			
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	}
}
