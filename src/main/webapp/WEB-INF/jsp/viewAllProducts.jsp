<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>

<c:if test="${addProductSuccess}">
    <div class="text-center">Product successfully added with title: ${savedProduct.title}</div>
</c:if>

<c:forEach items="${productList}" var="product">
    <div class="row" style="width: 23%; float: left; margin: 10px">
        <div class="col" style="padding: 0;">
            <div class="card" style="">
                <img src="${product.imgPath}" alt="${product.imgPath}" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.description}</p>
                    <p class="card-text text-end">${product.price}</p>
                    <p class="card-text">В наличии: ${product.amount}</p>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<c:import url="footer.jsp"/>