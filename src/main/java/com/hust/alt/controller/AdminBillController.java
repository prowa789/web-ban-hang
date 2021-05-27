package com.hust.alt.controller;

import com.hust.alt.dao.AccountDao;
import com.hust.alt.dao.BillDao;
import com.hust.alt.dao.CategoryDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.dao_impl.AccountDaoImpl;
import com.hust.alt.dao_impl.BillDaoImpl;
import com.hust.alt.dao_impl.CategoryDaoImpl;
import com.hust.alt.dao_impl.ProductDaoImpl;
import com.hust.alt.model.Bill;
import com.hust.alt.model.Category;
import com.hust.alt.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminBillController",value = "/admin/bill")
public class AdminBillController extends HttpServlet {
    BillDao billDao = new BillDaoImpl();
    AccountDao accountDao = new AccountDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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

            // lấy tổng số lượng product vào biến count
            int count = billDao.getTotalBill();
            int endPage= count/3; // lấy số trang(3 sp/1 trang)
            if (count % 3 != 0) endPage++;
            //lấy danh sách sản phẩm của từng trang
            List<Bill> listBill = billDao.pagingBill(index);
            ArrayList<String> listStatus = new ArrayList<>();


            for (int i = 0; i < listBill.size(); i++) {
                switch (listBill.get(i).getStatus()){
                    case 1 : {
                        listStatus.add("Chờ xử lý");
                        break;
                    }
                    case 2 : {
                        listStatus.add("Đã xác nhận");
                        break;
                    }
                }
            }

            request.setAttribute("listBill",listBill);
            request.setAttribute("tag", index);
            request.setAttribute("listStatus",listStatus);
            request.setAttribute("endP", endPage);//
            request.setAttribute("count", count);


            request.getRequestDispatcher("/BillManager.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
