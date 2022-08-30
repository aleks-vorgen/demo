<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="header.jsp"/>

<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>

<form method="POST" action="${pageContext.request.contextPath}/login" style="width: 40%;">
    <div class="mb-3 has-validation">
        <label for="username" class="form-label">Логин</label>
        <input type="text" class="form-control" name="username" id="username" required maxlength="20">
    </div>
    <div class="mb-3 has-validation">
        <label for="password" class="form-label">Пароль</label>
        <input class="form-control" name="password" id="password" required minlength="6" maxlength="255"/>
    </div>
    <div class="justify-content-between" style="display: flex">
        <input type="submit" class="btn btn-primary" style="width: 165px" value="Войти">
        <a href="/registration" class="link-primary">Зарегистрироваться</a>
    </div>
</form>

<c:import url="footer.jsp"/>