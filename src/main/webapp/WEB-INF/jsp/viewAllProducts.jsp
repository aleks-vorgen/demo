<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>

<c:if test="${addToBasketSuccess}">
    <div class="position-fixed bottom-0 right-0 p-3" style="z-index: 5; right: 0; bottom: 0;">
        <div id="liveToast" class="toast show hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="4000">
            <div class="toast-header">
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                Продукт добавлен в корзину
            </div>
        </div>
    </div>
</c:if>

<c:if test="${productList.size() == 0}">
    <div class="flex-content">
        <h3>По вашему запросу ничего не найдено</h3>
        <h6>Попробуйте повторить запрос с большой буквы</h6>
    </div>
</c:if>

<c:forEach items="${productList}" var="product">
    <div class="float-start" style="width: 30%; margin: 10px">
        <div class="card" style="height: 400px">
            <img src="${product.imgPath}" alt="${product.title}" class="card-img-top">
            <div class="card-body">
                <h5 class="card-title">${product.title}</h5>
                <p class="card-text">${product.description}</p>
                <p class="card-text text-end">${product.price}$</p>
                <a href="/products/${product.id}" class="card-link">Детали</a>
                <a href="/orders/addToBasket/${product.id}" class="card-link">В корзину</a>
            </div>
        </div>
    </div>
</c:forEach>

<div class="float-start search_data" style="width: 30%; margin: 10px"></div>

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