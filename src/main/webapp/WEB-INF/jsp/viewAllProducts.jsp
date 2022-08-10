<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>

<c:forEach items="${productList}" var="product">
        <div class="float-start" style="width: 30%; margin: 10px">
            <div class="card" style="height: 400px">
                <img src="${product.imgPath}" alt="${product.title}" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.description}</p>
                    <p class="card-text text-end">${product.price}$</p>
                    <a href="/products/${product.id}" class="card-link">Детали</a>
                    <a href="#" class="card-link">Купить</a>
                </div>
            </div>
        </div>
</c:forEach>

<script>
    $(document).ready(function () {
        $(".card-img-top").each(function (key, item) {
            $(item).on("error", function () {
                showDefaultImage(this);
            }).attr('src', $(item).attr('src'));
        });
    });

    function showDefaultImage(img) {
        $(img).attr('src', '/images/no_img.png');
        $(img).off('error');
    }
</script>

<c:import url="footer.jsp"/>