package com.jyy.bookstore.servlet.ProductServlet;

import java.awt.print.Book;
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

@WebServlet("/updateproduct")
public class UpdateProductServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	
	Product product = new Product();
	try {
		BeanUtils.populate(product, request.getParameterMap());
		System.out.println(product);
		
		//2.更新数据
		AddProductService productService = new AddProductService();
		productService.updateProduct(product);
		
		//3.回到list页面
		List<Product> products = productService.findAllBooks();
		request.setAttribute("products", products);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
