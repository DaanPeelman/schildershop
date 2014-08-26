<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Uw gegevens</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/normalize.css' />
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,700,400italic,700italic,900|Play:400,700'
	rel='stylesheet' type='text/css'>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
</head>
<body>
	<header>
		<a href='<c:url value="/" />' id="logo"> <img
			src="${pageContext.servletContext.contextPath}/img/logoSS.png"
			alt="logo">
		</a>
		<nav>
			<security:authorize access="isAuthenticated()">
				<jsp:include page="../ingelogdMenu.jsp" />
			</security:authorize>
			<ul>
				<jsp:include page="../menu.jsp" />
			</ul>
		</nav>
	</header>
	<div id="wrapper">
		<c:url value="/gebruiker" var="url" />
		<form:form commandName="adresForm" action="${url}" method="put">
			<h2>Uw gegevens wijzigen</h2>
			<div>
				<form:label path="straat">Straat: <form:input
						path="straat" />
					<form:errors path="straat" cssClass="fout" />
				</form:label>
			</div>
			<div>
				<form:label path="nummer">Nummer: <form:input
						path="nummer" />
					<form:errors path="nummer" cssClass="fout" />
				</form:label>
			</div>
			<div>
				<form:label path="gemeente">Gemeente: <form:input
						path="gemeente" />
					<form:errors path="gemeente" cssClass="fout" />
				</form:label>
			</div>
			<div>
				<form:label path="postcode">Postcode: <form:input
						path="postcode" />
					<form:errors path="postcode" cssClass="fout" />
				</form:label>
			</div>
			<div>
				<input type="submit" value="Opslaan" />
			</div>
		</form:form>
		<c:url value="/gebruiker/wachtwoord" var="url" />
		<form:form commandName="wijzigWachtwoordForm" action="${url}"
			method="put">
			<h2>Uw wachtwoord wijzigen</h2>
			<div>
				<form:label path="oudWachtwoord">Huidig wachtwoord: <form:input
						path="oudWachtwoord" type="password" />
					<form:errors path="oudWachtwoord" cssClass="fout" />
				</form:label>
			</div>
			<div>
				<form:label path="nieuwWachtwoord">Nieuw wachtwoord: <form:input
						path="nieuwWachtwoord" type="password" />
					<form:errors path="nieuwWachtwoord" cssClass="fout" />
				</form:label>
			</div>
			<div>
				<form:label path="bevestigWachtwoord">Bevestig wachtwoord: <form:input
						path="bevestigWachtwoord" type="password" />
					<form:errors path="bevestigWachtwoord" cssClass="fout" />
				</form:label>
			</div>
			<div>
				<input type="submit" value="Opslaan" />
			</div>
		</form:form>
		<footer>
			<jsp:include page="/WEB-INF/JSP/footer.jsp" />
		</footer>
	</div>
</body>
</html>