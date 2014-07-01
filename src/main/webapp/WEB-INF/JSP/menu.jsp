<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<ul>
	<li><a href='<c:url value="/" />'>Home</a>
	<security:authorize access="isAnonymous()"><li><a href='<c:url value="/login" />'>Aanmelden</a></li></security:authorize>
	<security:authorize access="isAuthenticated()"><li><a href="<c:url value='/j_spring_security_logout'/>">Afmelden</a></li></security:authorize>
</ul>