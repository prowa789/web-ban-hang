<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.hust.alt.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hust.alt.dao_impl.ProductDaoImpl" %>
<%@ page import="com.hust.alt.dao.ProductDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="shortcut icon" href="https://set.hust.edu.vn/storage/logo/set-logo.png"
              type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Product</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Quantity</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Delete</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                ProductDao productDAO = new ProductDaoImpl();
                                                NumberFormat nf = NumberFormat.getInstance();
                                                nf.setMinimumIntegerDigits(0);
                                                double total = 0;
                                                ArrayList<Cart> cart=null;
                                                if(session.getAttribute("cart")!=null){
                                                    cart = (ArrayList<Cart>) session
                                                            .getAttribute("cart");}
                                            %>
                                            <%
                                                if (cart != null) {
                                                    for (Cart c : cart) {
                                                        total = total
                                                                + (c.getQuantity() * productDAO.findById(
                                                                c.getProduct().getId()).getPrice());
                                            %>
                                                <tr>
                                                    <td scope="row">
                                                        <div class="p-2">
                                                            <img src="<%=productDAO.findById(c.getProduct().getId())
							.getImage()%>" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block"><%=productDAO.findById(c.getProduct().getId())
                                                                        .getName()%></a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td class="align-middle"><strong><%=nf.format(productDAO.findById(
                                                            c.getProduct().getId()).getPrice())%></strong></td>
                                                    <td class="align-middle">
                                                        <a href="cart?command=deleteCart&id=<%=c.getProduct().getId()%>"><button class="btnSub">-</button></a>
                                                        <strong><%=c.getQuantity()%></strong>
                                                        <a href="cart?command=addCart&id=<%=c.getProduct().getId()%>"><button class="btnAdd">+</button></a>
                                                    </td>
                                                    <td class="align-middle"><a href="cart?command=removeCart&id=<%=c.getProduct().getId()%>" class="text-dark">
                                                            <button type="button" class="btn btn-danger">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->

                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                                    <ul class="list-unstyled mb-4" style="width: 100%">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Transport fee</strong><strong>Free ship</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>0 $</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Payment</strong>
                                            <h5 class="font-weight-bold"><%=nf.format(total)%> $</h5>
                                        </li>
                                    </ul>
                        </div>
<%--                        --%>

<%--                        --%>
                            <a href="checkout" class="btn btn-block btn-primary" style="height: 50px;font-size: 20px">Check Out</a>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
</html>
