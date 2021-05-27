package com.hust.alt.controller;

import com.hust.alt.dao.BillDao;
import com.hust.alt.dao.BillDetailDao;
import com.hust.alt.dao_impl.BillDaoImpl;
import com.hust.alt.dao_impl.BillDetailDaoImpl;
import com.hust.alt.model.Bill;
import com.hust.alt.model.BillDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminDeleteBillController",value = "/admin/bill/delete")
public class AdminDeleteBillController extends HttpServlet {

    BillDetailDao billDetailDao= new BillDetailDaoImpl();
    BillDao billDao = new BillDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            billDetailDao.delete(id);
            billDao.delete(id);
            response.sendRedirect("/web_ban_hang_war/admin/bill");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
