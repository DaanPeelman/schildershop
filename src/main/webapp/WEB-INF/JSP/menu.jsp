<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<ul>
	<jsp:include page="menuZonderLogin.jsp"></jsp:include>
	<security:authorize access="isAnonymous()"><li>
		<form method='post' action='<c:url value="/j_spring_security_check"/>'
		id='aanmeldform'>
		<label>Gebruikersnaam: <input name='j_username' autofocus></label>
		<label>Paswoord: <input type='password' name='j_password'></label>
		<input type='submit' value='Aanmelden' id='aanmeldknop'>
		<p>U bent nieuw hier en zou u graag registreren? Klik <a href="<c:url value='/klanten/login' />"> hier </a></p>
	</form>
	</li></security:authorize>
</ul>

<script>
	document.getElementById('aanmeldform').onsubmit = function() {
		document.getElementById('aanmeldknop').disabled = true;
	};
</script>