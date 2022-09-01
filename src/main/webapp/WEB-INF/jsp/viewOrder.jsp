<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="header.jsp"/>

<c:if test="${basket.size() == 0 || basket == null}">
    <h3>Вы ничего не выбрали</h3>
</c:if>

<c:if test="${basket.size() > 0}">
    <form:form action="/orders/addOrder" modelAttribute="newOrder">
        <c:forEach items="${basket}" var="order">
            <form:input type="hidden" value="${order.id}" path="productId"/>
            <div class="card mb-3" style="width: 100%">
                <div class="card-body row">
                    <img class="card-img-top col-sm" src="${order.imgPath}" alt="">
                    <div class="col-8">
                        <h5>${order.title}</h5>
                        <p>${order.price}</p>
                    </div>
                    <a href="/orders/deleteFromBasket/${order.id}" class="btn btn-close col-1"></a>
                </div>
            </div>
        </c:forEach>
        <a href="${pageContext.request.contextPath}/orders/saveOrder" class="btn btn-success" type="submit">Подтвердить заказ</a>
    </form:form>
</c:if>

<c:import url="footer.jsp"/>