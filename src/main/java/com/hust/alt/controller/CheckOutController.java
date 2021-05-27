package com.hust.alt.controller;

import com.hust.alt.common.BillStatus;
import com.hust.alt.dao.BillDao;
import com.hust.alt.dao.BillDetailDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.dao_impl.BillDaoImpl;
import com.hust.alt.dao_impl.BillDetailDaoImpl;
import com.hust.alt.dao_impl.ProductDaoImpl;
import com.hust.alt.model.Bill;
import com.hust.alt.model.BillDetail;
import com.hust.alt.model.Cart;
import com.hust.alt.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CheckOutController", value = "/checkout")
public class CheckOutController extends HttpServlet {

    BillDao billDao = new BillDaoImpl();
    BillDetailDao billDetailDao = new BillDetailDaoImpl();
    ProductDao productDao = new ProductDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            Integer status = BillStatus.WAITING_FOR_PROCESSING;

            Bill bill = new Bill(name,phoneNumber,address,status);

            bill = billDao.insert(bill);

            System.out.println(bill);

            ArrayList<Cart> carts = (ArrayList<Cart>) request.getSession().getAttribute("cart");

            BillDetail billDetail = null;
            Product product = null;
            for (Cart item:carts) {
                product = productDao.findById(item.getProduct().getId());
                billDetail = new BillDetail(item.getProduct().getId(),bill.getId(),product.getPrice(),item.getQuantity());
                billDetailDao.insert(billDetail);
            }

            carts.clear();
//            response.sendRedirect("ordersuccess");
            request.getRequestDispatcher("OrderSuccess.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("Checkout.jsp").forward(request,response);
    }
}
