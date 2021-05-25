<%--
  Created by IntelliJ IDEA.
  User: NinhND's PC
  Date: 5/23/2021
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="order" method="POST">
    <div class="container bg-default" style="background-color: #F8F9FA;color: black;">
        <h4 class="my-4">
            Customer information
        </h4>

        <div class="col-md-6 form-group">
            <label >Fullname</label>
            <input type="text" class="form-control"  name="name" placeholder="Tien Bip"
                   required="">
            <div class="invalid-feedback">
                Please enter your name.
            </div>
        </div>

        <div class="form-group col-md-6">
            <label>Email</label>
            <input type="email" class="form-control" name="email" placeholder="you@example.com"
                   required="">
            <div class="invalid-feedback">
                Please enter your email.
            </div>
        </div>

        <div class="form-group col-md-6">
            <label>Address</label>
            <input type="text" class="form-control" name="address"
                   placeholder="Hai Bà Trưng,Hà Nội" required="">
            <div class="invalid-feedback">
                Please enter your shipping address.
            </div>
        </div>
        <div class="form-group col-md-6">
            <label>Phone Number</label>
            <input type="text" class="form-control" name="phoneNumber" placeholder="0967855944" required="">
            <div class="invalid-feedback">
                Please enter your phone.
            </div>
        </div>
        <button class="btn btn-primary bt-lg btn-block" style="height:50px;background-color: #343a40;"
                type="submit">Order</button>

    </div>
</form>

</body>
</html>
