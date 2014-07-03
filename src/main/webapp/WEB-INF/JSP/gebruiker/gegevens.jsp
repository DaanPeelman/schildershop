<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css' />
<title>Uw gegevens</title>
</head>
<body>
	<jsp:include page="../menu.jsp" />
	<h1>Uw gegevens</h1>
	<div><b>Naam: </b> ${gebruiker.naam}&nbsp;${gebruiker.familienaam}</div>
	<div><b>Emailadres: </b> ${gebruiker.emailadres}</div>
	<div><b>Straat: </b> ${gebruiker.adres.straat}</div>
	<div><b>Huisnummer: </b> ${gebruiker.adres.nummer}</div>
	<div><b>Gemeente: </b> ${gebruiker.adres.gemeente}</div>
	<div><b>Postcode: </b> ${gebruiker.adres.postcode}</div>
	<div><a href="<c:url value='/gebruiker/wijzig' />">Wijzig gegevens</a></div>
</body>
</html>