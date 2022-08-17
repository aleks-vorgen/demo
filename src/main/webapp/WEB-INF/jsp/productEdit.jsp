<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="header.jsp"/>

<form:form action="/admin/editProduct" modelAttribute="product" cssStyle="width: 40%">
    <div class="mb-3">
        <form:input path="id" type="hidden" value="${product.id}"/>
        <form:label path="categoryId" cssClass="form-label">Категория</form:label>
        <form:select cssClass="form-select" path="categoryId">
            <c:forEach  items="${categoryList}" var="category">
                <form:option value="${category.id}">${category.title}</form:option>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3">
        <form:label path="title" cssClass="form-label">Название</form:label>
        <form:input type="text" cssClass="form-control" path="title"
            required="true" maxlength="50"/>
    </div>
    <div class="mb-3">
        <form:label path="price" cssClass="form-label">Цена</form:label>
        <form:input type="number" cssClass="form-control" path="price"
            required="true" />
    </div>
    <div class="mb-3">
        <form:label path="amount" cssClass="form-label">Количество</form:label>
        <form:input type="number" cssClass="form-control" path="amount"
            required="true" />
    </div>
    <div class="mb-3">
        <form:label path="description" cssClass="form-label">Описание</form:label>
        <form:input type="text" cssClass="form-control" path="description"
            required="true" minlength="10" maxlength="255"/>
    </div>
    <div class="mb-3">
        <form:label path="imgPath" cssClass="form-label">Ссылка на изображение</form:label>
        <form:input path="imgPath" cssClass="form-control" type="text"
            required="true" maxlength="255"/>
    </div>
    <a href="${pageContext.request.contextPath}/admin/viewAdminPanel" type="button"
       class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</a>
    <button  type="submit" class="btn btn-primary">Сохранить</button>
</form:form>

<c:import url="footer.jsp"/>