package com.hust.alt.controller;

import com.hust.alt.dao.CategoryDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.dao_impl.CategoryDaoImpl;
import com.hust.alt.dao_impl.ProductDaoImpl;
import com.hust.alt.model.Category;
import com.hust.alt.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminEditProduct",urlPatterns = "/admin/edit")
public class AdminEditProduct extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            String price = request.getParameter("price");
            String introduction = request.getParameter("introduction");
            String category = request.getParameter("category");


            Product product = new Product(Integer.parseInt(id),name,Double.parseDouble(price),image,introduction,false,Integer.parseInt(category));
            boolean rs = productDao.update(product);
            if(rs) {
                response.sendRedirect("manager-product");
            }
            else {
                System.out.println("lỗi");
            }




        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = productDao.findById(Integer.parseInt(request.getParameter("id")));
            List<Category> listCategory = categoryDao.findAll();
            request.setAttribute("listCategory",listCategory);
            request.setAttribute("detail",product);
            RequestDispatcher rd = request.getRequestDispatcher("/Edit.jsp");
            rd.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
