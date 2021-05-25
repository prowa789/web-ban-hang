package com.hust.alt.controller;

import com.hust.alt.dao.OrderDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.dao_impl.OrderDaoImpl;
import com.hust.alt.dao_impl.ProductDaoImpl;
import com.hust.alt.model.Cart;
import com.hust.alt.model.Order;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "OrderController", urlPatterns = "/order")
public class OrderController extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            double total = 0;
            ArrayList<Cart> carts = (ArrayList<Cart>) request.getSession().getAttribute("cart");
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumFractionDigits(0);
            //lay time gửi mail
            Date dNow = new Date( );
            SimpleDateFormat ft =
                    new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a");
            //xác nhận đơn đặt hàng qua mail
            final String username_mail = "nguyenduyninh99@gmail.com";
            final String password = "duyninhhust";
            String to = email;
            String subject = "Confirm cart";
            String text = "<strong>Đơn Hàng - "+ name +" - </strong><i> "+ ft.format(dNow) +"</i> <ul>";
            if (carts != null){
                for (Cart c : carts) {
                    total = total + (c.getQuantity() * productDao.findById(c.getProduct().getId()).getPrice());
                    text += "<li>"+ productDao.findById(c.getProduct().getId()).getName()+": "+ nf.format(productDao.findById(
                                c.getProduct().getId()).getPrice()) +" $ </li>";

                    Order o = new Order(name, phoneNumber, email, address, c.getQuantity(),
                            c.getQuantity() * productDao.findById(c.getProduct().getId()).getPrice(),c.getProduct().getId());
                    orderDao.insert(o);
                }
            }
            text += "Tổng thanh toán: <strong>"+ nf.format(total) +" $ </strong>";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session_mail = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username_mail, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session_mail);
                message.setHeader("Content-Type", "text/plain; charset=UTF-8");
                message.setFrom(new InternetAddress(username_mail));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                message.setSubject(subject);
                message.setContent(text,"text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            carts.clear();
            request.getSession().setAttribute("cart", carts);
            response.sendRedirect("Cart.jsp");


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/Cart.jsp").forward(request,response);

    }
}
