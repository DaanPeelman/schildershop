<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<ul id="adminnav">
	<li><a href="<c:url value='/gebruiker' />">Gegevens</a></li>
	<security:authorize url='/producten/toevoegen'>
	<li><a href='<c:url value="/producten/toevoegen" />'>Product/Schilder toevoegen</a></li>
</security:authorize>
</ul>