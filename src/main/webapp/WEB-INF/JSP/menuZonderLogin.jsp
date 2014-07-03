<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

	<li><a href='<c:url value="/" />'>Home</a>
	<li><a href='<c:url value="/producten" />'>Producten</a></li>
	<li><a href='<c:url value="/producten/toevoegen" />'>Product/Schilder toevoegen</a></li>
	<security:authorize access="isAuthenticated()"><li><a href="<c:url value='/j_spring_security_logout'/>">Afmelden</a></li></security:authorize>