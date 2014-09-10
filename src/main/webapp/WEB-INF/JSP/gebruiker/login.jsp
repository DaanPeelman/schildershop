<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<title>Aanmelden</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/normalize.css' />
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,700,400italic,700italic,900%7CPlay:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/layout.css" />
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			<h1>Inloggen</h1>
		</header>
		<div id="wrapper">
			<security:authorize access="isAnonymous()">
				<div id="primary">
					<form method='post'
						action='<c:url value="/j_spring_security_check"/>'
						id='aanmeldform'>
						<h2>Ik ben al geregistreerd</h2>
						<div>
							<label><span>Gebruikersnaam: </span><input
								name='j_username' autofocus type="text"></label>
						</div>
						<div>
							<label><span>Paswoord: </span><input type='password'
								name='j_password'></label>
						</div>
						<c:if test="${param.error }">
							<div class="fout">Verkeerde gebruikersnaam of paswoord</div>
						</c:if>
						<div>
							<input type='submit' value='Aanmelden' id='aanmeldknop'>
						</div>
					</form>
				</div>
				<section id="secondary" class="lagereDiv">
					<h2>Ik ben nieuw hier</h2>
					<c:url var="url" value="/gebruiker" />
					<form:form commandName="gebruiker" action="${url}" method="post">
						<div>
							<form:label path="naam">
								<span>Voornaam: </span>
								<form:input path="naam" />
								<form:errors path="naam" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="familienaam">
								<span>Familienaam: </span>
								<form:input path="familienaam" />
								<form:errors path="familienaam" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="adres.straat">
								<span>Straat: </span>
								<form:input path="adres.straat" />
								<form:errors path="adres.straat" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="adres.nummer">
								<span>Huisnr.: </span>
								<form:input path="adres.nummer" />
								<form:errors path="adres.nummer" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="adres.gemeente">
								<span>Gemeente: </span>
								<form:input path="adres.gemeente" />
								<form:errors path="adres.gemeente" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="adres.postcode">
								<span>Postcode: </span>
								<form:input path="adres.postcode" />
								<form:errors path="adres.postcode" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="emailadres">
								<span>Emailadres: </span>
								<form:input path="emailadres" />
								<form:errors path="emailadres" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="wachtwoord">
								<span>Wachtwoord </span>
								<form:input path="wachtwoord" type="password" />
								<form:errors path="wachtwoord" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="bevestigWachtwoord">
								<span>Bevestig wachtwoord: </span>
								<form:input path="bevestigWachtwoord" type="password" />
								<form:errors path="bevestigWachtwoord" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<input type="submit" value="Maak aan" />
						</div>
					</form:form>
				</section>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<h1>U bent al aangemeld</h1>
			</security:authorize>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
<script>
	document.getElementById('aanmeldform').onsubmit = function() {
		document.getElementById('aanmeldknop').disabled = true;
	};
</script>
</html>