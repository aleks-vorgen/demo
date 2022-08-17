<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>

<div class="row" style="width: 100%">
    <div class="col-10 mb-3">
        <h1>${product.title}</h1>
    </div>

    <div class="col-2 mb-3">
        <a href="${pageContext.request.contextPath}/products/viewAllProducts" class="btn btn-outline-primary">К каталогу</a>
    </div>
    <div class="col-6 text-center">
        <img src="${product.imgPath}" class="img-fluid" alt="${product.title}">
    </div>

    <div class="col-6">
        <div class="card mb-3">
            <div class="card-body">
                <h5 style="display: inline-block; min-width: 20%">${product.price}$</h5>
                <a href="#" class="btn btn-outline-success" style="min-width: 15%">Купить</a>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-body">
                В наличии: ${product.amount}
            </div>
        </div>
    </div>
</div>
<c:if test="${commentList != null}">
<div class="row" style="width: 100%">
    <div class="col-6">
        <h2>Описание</h2>
        <p>${product.description}</p>
    </div>
        <div class="col-6">
            <h2>Комментарии</h2>
            <c:forEach items="${commentList}" var="comment">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5>${comment.title}</h5>
                        <p>${comment.comment}</p>
                        <p class="text-end">${comment.user.username}, ${comment.rating}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
</div>
</c:if>

<script>
    $(document).ready(function () {
        $(".img-fluid").each(function (key, item) {
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