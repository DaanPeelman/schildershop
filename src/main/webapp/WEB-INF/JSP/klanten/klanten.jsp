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
	<h2>Ik heb al een account</h2>
	<h2>Ik heb nog geen account</h2>
	<form:form commandName="klant">
		<div><form:label path="naam">Voornaam: <form:input path="naam" /></form:label></div>
		<div><form:label path="familienaam">Familienaam: <form:input path="familienaam" /></form:label></div>
		<div><form:label path="adres.straat">Straat: <form:input path="adres.straat" /></form:label></div>
		<div><form:label path="adres.nummer">Huisnr.: <form:input path="adres.nummer" /></form:label></div>
		<div><form:label path="adres.gemeente">Gemeente: <form:input path="adres.gemeente" /></form:label></div>
		<div><form:label path="adres.postcode">Postcode: <form:input path="adres.postcode" /></form:label></div>
		<div><form:label path="wachtwoord">Wachtwoord <form:input path="wachtwoord" /></form:label></div>
		<div><form:label path="bevestigingWachtwoord">Bevestig wachtwoord: <form:input path="bevestigingWachtwoord" /></form:label></div>
		<div><input type="submit" value="Maak aan" /></div>
	</form:form>
</body>
</html>