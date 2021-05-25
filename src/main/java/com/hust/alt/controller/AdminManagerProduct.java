package com.hust.alt.controller;

import com.hust.alt.dao.AccountDao;
import com.hust.alt.dao.CategoryDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.dao_impl.AccountDaoImpl;
import com.hust.alt.dao_impl.CategoryDaoImpl;
import com.hust.alt.dao_impl.ProductDaoImpl;
import com.hust.alt.model.Account;
import com.hust.alt.model.Category;
import com.hust.alt.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminManagerProduct",urlPatterns = "/admin/manager-product")
public class AdminManagerProduct extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();
    AccountDao accountDao = new AccountDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //xử lí phân trang
        // lấy dữ liệu từ biến index ở trang ManagerProduct.jsp truyền về
        String indexPage = request.getParameter("index");
        if (indexPage == null){
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        //getName account
        try {
            Cookie[] cookies =  request.getCookies();
            if (cookies != null){
                for (Cookie c : cookies) {
                    if (c != null && c.getName().equals("userId")){
                        request.setAttribute("acc",accountDao.findById(Integer.parseInt(c.getValue())));
                    }
                }
            }
//            List<Product>  listProduct = productDao.findAll();
            List<Category>  listCategory = categoryDao.findAll();
            // lấy tổng số lượng product vào biến count
            int count = productDao.getTotalProduct();
            int endPage= count/3; // lấy số trang(3 sp/1 trang)
            if (count % 3 != 0) endPage++;
            //lấy danh sách sản phẩm của từng trang
            List<Product> list = productDao.pagingProduct(index);
            request.setAttribute("listA",list);
            request.setAttribute("tag", index);

            request.setAttribute("endP", endPage);//
            request.setAttribute("count", count);

//            request.setAttribute("listProduct",listProduct);
            request.setAttribute("listCategory",listCategory);
            RequestDispatcher rd = request.getRequestDispatcher("/ManagerProduct.jsp");
            rd.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
