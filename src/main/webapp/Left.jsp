

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${listCategory}" var="o">
                <li class="list-group-item text-white ${tag == o.id ? "active":""}"><a href="category?id=${o.id}">${o.name}</a></li>
            </c:forEach>

        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Last product</div>
        <div class="card-body">
            <img class="img-fluid" src="${lastProduct.image}" />
            <h5 class="card-title">${lastProduct.name}</h5>
            <p class="card-text">${lastProduct.introduction}</p>
            <p class="bloc_left_price">${lastProduct.price} $</p>
        </div>
    </div>
</div>