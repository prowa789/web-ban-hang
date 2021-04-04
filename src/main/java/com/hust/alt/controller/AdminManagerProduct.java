package com.hust.alt.controller;

import com.hust.alt.dao.CategoryDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.dao_impl.CategoryDaoImpl;
import com.hust.alt.dao_impl.ProductDaoImpl;
import com.hust.alt.model.Category;
import com.hust.alt.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminManagerProduct",urlPatterns = "/admin/manager-product")
public class AdminManagerProduct extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            List<Product>  listProduct = productDao.findAll();
            List<Category>  listCategory = categoryDao.findAll();
            request.setAttribute("listProduct",listProduct);
            request.setAttribute("listCategory",listCategory);
            RequestDispatcher rd = request.getRequestDispatcher("/ManagerProduct.jsp");
            rd.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
