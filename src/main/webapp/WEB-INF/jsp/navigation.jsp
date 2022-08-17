<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value="/"/>">ShopMe</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/products/viewAllProducts">Каталог</a>
        </li>
        <li class="nav-item">
          <c:if test="${user.permissions == true}">
            <a class="nav-link active" href="${pageContext.request.contextPath}/admin/viewAdminPanel" tabindex="-1" aria-disabled="true">Админ панель</a>
          </c:if>
        </li>
        <li class="nav-item">
          <c:if test="${user.username == null}">
            <a class="nav-link active" href="${pageContext.request.contextPath}/users/viewLogin" tabindex="-1" aria-disabled="true">Войти</a>
          </c:if>
          <c:if test="${user.username != null}">
            <a class="nav-link active" href="${pageContext.request.contextPath}/users/logout" tabindex="-1" aria-disabled="true">Выйти</a>
          </c:if>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Поиск" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Найти</button>
      </form>
    </div>
  </div>
</nav>