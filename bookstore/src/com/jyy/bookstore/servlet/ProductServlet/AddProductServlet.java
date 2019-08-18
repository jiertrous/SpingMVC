package com.jyy.bookstore.servlet.ProductServlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gyf.bookstore.model.Product;
import com.jyy.bookstore.service.AddProductService;
import com.sun.swing.internal.plaf.basic.resources.basic;

@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
			//1。把表单的数据封装成模型
		Product product = new Product();
		try {
			BeanUtils.populate(product, request.getParameterMap());
			System.out.println(product);
			
			//2.调用service
			AddProductService bookService = new AddProductService();
			bookService.addProduct(product);
			
			//3.返回List页面
			//重新获取product数据
			List<Product> products = bookService.findAllBooks();
			request.setAttribute("products", products);
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
}
