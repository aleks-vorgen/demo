<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="header.jsp"/>


<form:form action="/users/login" modelAttribute="command" style="width: 40%">
    <c:if test='<%=session.getAttribute("error") != null
            && Boolean.parseBoolean(session.getAttribute("error").toString()) %>'>
    <div class="alert-danger p-2 mb-3 text-center">Пользователь с такими данными не найден</div>
    </c:if>
    <div class="mb-3 has-validation">
        <form:label path="email" cssClass="form-label">E-mail</form:label>
        <form:input type="email" cssClass="form-control" path="email" />
    </div>
    <div class="mb-3 has-validation">
        <form:label path="password" cssClass="form-label">Password</form:label>
        <form:password cssClass="form-control" path="password" />
    </div>
    <div class="justify-content-between" style="display: flex">
        <button type="submit" class="btn btn-primary" style="width: 165px">Login</button>
        <a href="${pageContext.request.contextPath}/users/viewRegister" class="link-primary">I don't have an account</a>
    </div>
</form:form>

<c:import url="footer.jsp"/>