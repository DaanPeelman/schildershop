<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<ul>
	<li><a href='<c:url value="/" />'>Home</a>
	<li><a href='<c:url value="/producten" />'>Producten</a></li>
	<security:authorize access="isAuthenticated()"><li><a href="<c:url value='/j_spring_security_logout'/>">Afmelden</a></li></security:authorize>
	<security:authorize access="isAnonymous()"><li>
		<form method='post' action='<c:url value="/j_spring_security_check"/>'
		id='aanmeldform'>
		<label>Gebruikersnaam: <input name='j_username' autofocus></label>
		<label>Paswoord: <input type='password' name='j_password'></label>
		<c:if test="${param.error }">
			<div class="fout">Verkeerde gebruikersnaam of paswoord</div>
		</c:if>
		<input type='submit' value='Aanmelden' id='aanmeldknop'>
		<p>U bent nieuw hier en zou u graag registreren? Klik <a href="<c:url value='/klanten/registreer' />"> hier </a></p>
	</form>
	</li></security:authorize>
</ul>