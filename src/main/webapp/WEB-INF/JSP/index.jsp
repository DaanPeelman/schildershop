<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css' />
<title>Index</title>
</head>
<body>
	<header>
		<nav class="adminNav">
	<security:authorize access="isAuthenticated()">
		<jsp:include page="ingelogdMenu.jsp" />
	</security:authorize>
	<jsp:include page="menu.jsp" />
	</header>
	<h1>index</h1>
</body>
</html>