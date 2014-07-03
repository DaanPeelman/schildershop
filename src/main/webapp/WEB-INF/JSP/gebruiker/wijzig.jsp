<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css' />
<title>Uw gegevens</title>
</head>
<body>
	<jsp:include page="../menu.jsp" />
	<c:url value="/gebruiker" var="url" />
	<form:form commandName="gebruiker" action="${url}" method="put">
		<div><form:input path="gebruikerId" type="hidden"/></div>
		<div><form:label path="naam">Voornaam: <form:input path="naam" /><form:errors path="naam" /></form:label></div>
		<div><form:label path="familienaam">Familienaam: <form:input path="familienaam" /><form:errors path="familienaam" /></form:label></div>
		<div><form:label path="adres.straat">Straat: <form:input path="adres.straat" /><form:errors path="adres.straat" /></form:label></div>
		<div><form:label path="adres.nummer">Nummer: <form:input path="adres.nummer" /><form:errors path="adres.nummer" /></form:label></div>
		<div><form:label path="adres.gemeente">Gemeente: <form:input path="adres.gemeente" /><form:errors path="adres.gemeente" /></form:label></div>
		<div><form:label path="adres.postcode">Postcode: <form:input path="adres.postcode" /><form:errors path="adres.postcode" /></form:label></div>
		<div><form:input path="emailadres" type="hidden" /></div>
		<div><form:input path="wachtwoord" type="hidden" /></div>
		<div><input type="submit" value="Opslaan" /></div>
	</form:form>
</body>
</html>