package com.hust.alt.controller;

import com.hust.alt.dao.CategoryDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.dao_impl.CategoryDaoImpl;
import com.hust.alt.dao_impl.ProductDaoImpl;
import com.hust.alt.model.Category;
import com.hust.alt.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Inet4Address;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/category")
public class CategoryController extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // lấy dữ liệu
            int categoryId = Integer.parseInt(request.getParameter("id"));
            List<Product> listProduct = productDao.findByCategory(categoryId);
            List<Category> listCategory = categoryDao.findAll();
            Product lastProduct = productDao.getLastProduct();

            // đổ lên view
            request.setAttribute("listProduct",listProduct);
            request.setAttribute("listCategory",listCategory);
            request.setAttribute("lastProduct",lastProduct);
            request.setAttribute("tag",categoryId);
            request.getRequestDispatcher("Home.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
