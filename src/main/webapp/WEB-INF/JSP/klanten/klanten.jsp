<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aanmelden</title>
</head>
<body>
	<h1>Aanmelden</h1>
	<h2>Ik ben al geregistreerd</h2>
	<h2>Ik ben nieuw hier</h2>
	<c:url var="url" value="/klanten" />
	<form:form commandName="gebruiker" action="${url}" method="post">
		<div><form:label path="naam">Voornaam: <form:input path="naam" /><form:errors path="naam" cssClass="fout"/></form:label></div>
		<div><form:label path="familienaam">Familienaam: <form:input path="familienaam" /><form:errors path="familienaam" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.straat">Straat: <form:input path="adres.straat" /><form:errors path="adres.straat" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.nummer">Huisnr.: <form:input path="adres.nummer" /><form:errors path="adres.nummer" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.gemeente">Gemeente: <form:input path="adres.gemeente" /><form:errors path="adres.gemeente" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.postcode">Postcode: <form:input path="adres.postcode" /><form:errors path="adres.postcode" cssClass="fout"/></form:label></div>
		<div><form:label path="emailadres">Emailadres: <form:input path="emailadres" /><form:errors path="emailadres" cssClass="fout"/></form:label></div>
		<div><form:label path="wachtwoord">Wachtwoord <form:input path="wachtwoord" /><form:errors path="wachtwoord" cssClass="fout"/></form:label></div>
		<div><input type="submit" value="Maak aan" /></div>
	</form:form>
</body>
</html>