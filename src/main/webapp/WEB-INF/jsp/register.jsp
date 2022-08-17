<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="header.jsp"/>


<form:form action="/users/register" modelAttribute="command" style="width: 40%">
    <c:if test='<%=session.getAttribute("regError") != null
            && Boolean.parseBoolean(session.getAttribute("regError").toString()) %>'>
        <div class="alert-danger p-2 mb-3 text-center">Пользователь с такой почтой уже существует</div>
    </c:if>
    <div class="mb-3 has-validation">
        <form:label path="username" cssClass="form-label">Логин</form:label>
        <form:input type="text" cssClass="form-control" path="username"
            required="true" minlength="5" maxlength="20" />
    </div>
    <div class="mb-3 has-validation">
        <form:label path="email" cssClass="form-label">Почта</form:label>
        <form:input type="email" cssClass="form-control" path="email"
            required="true" maxlength="50"/>
    </div>
    <div class="mb-3 has-validation">
        <form:label path="password" cssClass="form-label">Пароль</form:label>
        <form:password cssClass="form-control" path="password"
            required="true" minlength="6" maxlength="255"/>
    </div>
    <div class="justify-content-between" style="display: flex">
        <button type="submit" class="btn btn-primary" style="width: 200px">Зарегистрироваться</button>
        <a href="${pageContext.request.contextPath}/users/viewLogin" class="link-primary">Войти</a>
    </div>
</form:form>

<c:import url="footer.jsp"/>