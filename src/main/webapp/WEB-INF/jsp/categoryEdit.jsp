<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="header.jsp"/>

<form:form action="/admin/editCategory" modelAttribute="category" cssStyle="width: 40%">
    <div class="mb-3">
        <form:input type="hidden" path="id" value="${category.id}"/>
        <form:label path="parentCategoryId" cssClass="form-label">Главная категория</form:label>
        <form:select cssClass="form-select" path="parentCategoryId">
            <form:option value="0">Главная категория</form:option>
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
    <a href="${pageContext.request.contextPath}/admin/viewAdminPanel" type="button"
       class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</a>
    <button  type="submit" class="btn btn-primary">Сохранить</button>
</form:form>

<c:import url="footer.jsp"/>