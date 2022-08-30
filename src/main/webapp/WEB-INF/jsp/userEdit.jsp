<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="header.jsp"/>

<form:form action="/admin/editUser" modelAttribute="user" cssStyle="width: 40%">
    <div class="mb-3">
        <form:input path="id" type="hidden" value="${user.id}"/>
        <form:label path="username" cssClass="form-label">Логин</form:label>
        <form:input type="text" path="username" cssClass="form-control"
                    required="true" minlength="5" maxlength="20"/>
    </div>
    <div class="mb-3">
        <form:label path="email" cssClass="form-label">Почта</form:label>
        <form:input type="email" cssClass="form-control" path="email"
                    required="true" maxlength="50"/>
    </div>
    <div class="mb-3">
        <form:label path="password" cssClass="form-label">Пароль</form:label>
        <form:input type="password" cssClass="form-control" path="password"
                    required="true" minlength="6" maxlength="255"/>
    </div>
    <div class="mb-3">
        <form:label path="permissions" cssClass="form-label">Роли</form:label>
        <form:select cssClass="form-select" path="permissions">
            <form:option value="ROLE_ADMIN">Администратор</form:option>
            <form:option value="ROLE_USER">Пользователь</form:option>
        </form:select>
    </div>
    <a href="${pageContext.request.contextPath}/admin/viewAdminPanel" type="button"
       class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</a>
    <button  type="submit" class="btn btn-primary">Сохранить</button>
</form:form>

<c:import url="footer.jsp"/>