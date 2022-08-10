<%@ page import="com.example.demo.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>

<% request.getSession(); %>
<% User user = (User) request.getSession().getAttribute("user"); %>

<h1>Welcome, <%= user == null ? "guest" : user.getUsername() %></h1>

<c:import url="footer.jsp"/>