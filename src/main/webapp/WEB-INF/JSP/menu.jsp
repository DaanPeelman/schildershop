<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<ul>
	<jsp:include page="menuZonderLogin.jsp"></jsp:include>
	<security:authorize access="isAnonymous()">
	<c:url value="/gebruiker/login" var="url" />
		<li><a href="${url}">Aanmelden</a></li>
	</security:authorize>
</ul>

<script>
	document.getElementById('aanmeldform').onsubmit = function() {
		document.getElementById('aanmeldknop').disabled = true;
	};
</script>