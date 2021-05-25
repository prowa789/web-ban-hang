package com.hust.alt.controller;

import com.hust.alt.dao.CommentDao;
import com.hust.alt.dao_impl.CommentDaoImpl;
import com.hust.alt.model.Comment;
import com.hust.alt.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CommentController", urlPatterns = "/comment")
public class CommentController extends HttpServlet {
    CommentDao commentDao = new CommentDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String sdt = request.getParameter("sdt");
            String comment = request.getParameter("comment");
            int idProduct = Integer.parseInt(request.getParameter("id"));

            Comment comment1 = commentDao.insert(new Comment(name,email,sdt,comment,idProduct));
            System.out.println(comment1);
//            PrintWriter writer = response.getWriter();
//
//            writer.println("<div class=\"comment\">\n" +
//                    "                        <strong>"+comment1.getName()+"</strong>\n" +
//                    "                        <div>"+comment1.getComment()+"</div>\n" +
//                    "                    </div>");
            response.sendRedirect("detail?id="+idProduct);



        }catch (Exception e) {
            e.printStackTrace();
        }


    }

}
