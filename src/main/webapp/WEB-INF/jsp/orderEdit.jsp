<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="header.jsp"/>

<form:form action="/admin/editOrder" modelAttribute="order">
    <div class="mb-3">
        <form:input path="id" type="hidden" value="${order.id}"/>
        <form:label path="userId" cssClass="form-label">Пользователь</form:label>
        <form:select path="userId" cssClass="form-select">
            <c:forEach  items="${userList}" var="user">
                <form:option value="${user.id}">${user.username} (${user.email})</form:option>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3">
        <form:label path="productId" cssClass="form-label">Продукт</form:label>
        <form:select path="productId" cssClass="form-select">
            <c:forEach  items="${productList}" var="product">
                <form:option value="${product.id}">${product.title} (${product.category.title})</form:option>
            </c:forEach>
        </form:select>
    </div>
    <a href="${pageContext.request.contextPath}/admin/viewAdminPanel" type="button"
       class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</a>
    <button  type="submit" class="btn btn-primary">Сохранить</button>
</form:form>

<c:import url="footer.jsp"/>