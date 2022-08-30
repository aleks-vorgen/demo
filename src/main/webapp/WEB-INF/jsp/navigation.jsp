<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">ShopMe</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/products/viewAllProducts">Каталог</a>
        </li>
        <li class="nav-item">
          <sec:authorize access="hasRole('ADMIN')">
            <a class="nav-link active" href="${pageContext.request.contextPath}/admin/viewAdminPanel">Админ панель</a>
          </sec:authorize>
        </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/orders/viewOrder" class="nav-link active">Корзина</a>
        </li>
        <li class="nav-item">
          <sec:authorize access="!isAuthenticated()">
            <a class="nav-link active" href="${pageContext.request.contextPath}/login">Войти</a>
          </sec:authorize>
          <sec:authorize access="isAuthenticated()">
            <a class="nav-link active" href="${pageContext.request.contextPath}/logout">Выйти</a>
          </sec:authorize>
        </li>
      </ul>
      <form class="d-flex" action="${pageContext.request.contextPath}/products/searchProduct">
        <input class="form-control me-2" type="text" placeholder="Поиск" name="input" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Найти</button>
      </form>
    </div>
  </div>
</nav>