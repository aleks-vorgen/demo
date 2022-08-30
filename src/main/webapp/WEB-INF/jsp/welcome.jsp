<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>


<h1>Welcome, ${username == null ? "guest" : username}</h1>

<c:import url="footer.jsp"/>