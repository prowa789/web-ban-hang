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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DetailController",value = "/detail")
public class DetailController extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();

    CategoryDao categoryDao = new CategoryDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // lấy dữ liệu
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = productDao.findById(productId);
            Product lastProduct = productDao.getLastProduct();
            List<Category> listCategory = categoryDao.findAll();
            // đổ lên view
            request.setAttribute("product",product);
            request.setAttribute("lastProduct",lastProduct);
            request.setAttribute("listCategory",listCategory);
            request.getRequestDispatcher("Detail.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
