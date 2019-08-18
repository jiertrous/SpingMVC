package com.jyy.bookstore.servlet.ProductServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.model.Product;
import com.jyy.bookstore.service.AddProductService;

@WebServlet("/deletebook")
public class DeleteBookByIdServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.获取id
		String id = request.getParameter("id");
		
		//2.通过id查找书
		AddProductService productService = new AddProductService();
		productService.deleteProductById(id);
		
		//3.放在请求对象
		request.setAttribute("products", productService.findAllBooks());  //重新返回查找商品
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}
}
