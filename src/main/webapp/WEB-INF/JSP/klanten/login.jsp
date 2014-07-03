<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css' />
<title>Aanmelden</title>
</head>
<body>
	<jsp:include page="../menu.jsp" />
	<h1>Ik ben al geregistreerd</h1>
	<form method='post' action='<c:url value="/j_spring_security_check"/>' id='aanmeldform'>
		<label>Gebruikersnaam: <input name='j_username' autofocus></label>
		<label>Paswoord: <input type='password' name='j_password'></label>
		<c:if test="${param.error }">
			<div class="fout">Verkeerde gebruikersnaam of paswoord</div>
		</c:if>
		<input type='submit' value='Aanmelden' id='aanmeldknop'>
		<p>U bent nieuw hier en zou u graag registreren? Klik <a href="<c:url value='/klanten/registreer' />"> hier </a></p>
	</form>
	
	<h1>Ik ben nieuw hier</h1>
	<c:url var="url" value="/klanten" />
	<form:form commandName="gebruiker" action="${url}" method="post">
		<div><form:label path="naam">Voornaam: <form:input path="naam" /><form:errors path="naam" cssClass="fout"/></form:label></div>
		<div><form:label path="familienaam">Familienaam: <form:input path="familienaam" /><form:errors path="familienaam" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.straat">Straat: <form:input path="adres.straat" /><form:errors path="adres.straat" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.nummer">Huisnr.: <form:input path="adres.nummer" /><form:errors path="adres.nummer" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.gemeente">Gemeente: <form:input path="adres.gemeente" /><form:errors path="adres.gemeente" cssClass="fout"/></form:label></div>
		<div><form:label path="adres.postcode">Postcode: <form:input path="adres.postcode" /><form:errors path="adres.postcode" cssClass="fout"/></form:label></div>
		<div><form:label path="emailadres">Emailadres: <form:input path="emailadres" /><form:errors path="emailadres" cssClass="fout"/></form:label></div>
		<div><form:label path="wachtwoord">Wachtwoord <form:input path="wachtwoord" type="password"/><form:errors path="wachtwoord" cssClass="fout"/></form:label></div>
		<div><input type="submit" value="Maak aan" /></div>
	</form:form>
</body>
<script>
	document.getElementById('aanmeldform').onsubmit = function() {
		document.getElementById('aanmeldknop').disabled = true;
	};
</script>
</html>