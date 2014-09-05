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
	href='http://fonts.googleapis.com/css?family=Merriweather:400,700,400italic,700italic,900%7CPlay:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/layout.css" />
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
</head>
<body>
	<div class="sticky_wrapper">
		<header>
			<a href='<c:url value="/" />' id="logo"> <img
				src="${pageContext.servletContext.contextPath}/img/logoSS.png"
				alt="logo">
			</a>
			<nav>
				<jsp:include page="../menu.jsp" />
			</nav>
		</header>
		<div id="wrapper">
			<c:url value="/gebruiker" var="url" />
			<form:form commandName="adresForm" action="${url}" method="put">
				<h2>Uw gegevens wijzigen</h2>
				<div>
					<form:label path="straat">
						<span>Straat: </span>
						<form:input path="straat" />
						<form:errors path="straat" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<form:label path="nummer">
						<span>Nummer: </span>
						<form:input path="nummer" />
						<form:errors path="nummer" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<form:label path="gemeente">
						<span>Gemeente: </span>
						<form:input path="gemeente" />
						<form:errors path="gemeente" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<form:label path="postcode">
						<span>Postcode: </span>
						<form:input path="postcode" />
						<form:errors path="postcode" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<input type="submit" value="Opslaan" />
				</div>
			</form:form>
			<div class="lagereDiv">
				<c:url value="/gebruiker/wachtwoord" var="url" />
				<form:form commandName="wijzigWachtwoordForm" action="${url}"
					method="put">
					<h2>Uw wachtwoord wijzigen</h2>
					<div>
						<form:label path="oudWachtwoord">
							<span>Huidig wachtwoord: </span>
							<form:input path="oudWachtwoord" type="password" />
							<form:errors path="oudWachtwoord" cssClass="fout" />
						</form:label>
					</div>
					<div>
						<form:label path="nieuwWachtwoord"><span>Nieuw wachtwoord: </span><form:input
								path="nieuwWachtwoord" type="password" />
							<form:errors path="nieuwWachtwoord" cssClass="fout" />
						</form:label>
					</div>
					<div>
						<form:label path="bevestigWachtwoord"><span>Bevestig wachtwoord: </span><form:input
								path="bevestigWachtwoord" type="password" />
							<form:errors path="bevestigWachtwoord" cssClass="fout" />
						</form:label>
					</div>
					<div>
						<input type="submit" value="Opslaan" />
					</div>
				</form:form>
			</div>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>