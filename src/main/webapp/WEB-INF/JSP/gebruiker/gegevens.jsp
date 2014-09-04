<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			<h1>Gegevens</h1>
		</header>
		<div id="wrapper">
			<h2>Uw recente bestellingen</h2>
			<c:if test="${not empty gebruiker.bestellingen}">
				<table>
					<thead>
						<tr>
							<th>Datum</th>
							<th>Prijs</th>
							<th>Info</th>
						</tr>
					</thead>
					<c:forEach var="bestelling" items="${gebruiker.bestellingen}"
						begin='0' end='2'>
						<spring:url var="url" value="/bestellingen/{bestelbonId}">
							<spring:param name="bestelbonId"
								value="${bestelling.bestelbonId}" />
						</spring:url>
						<tr>
							<td><fmt:formatDate value="${bestelling.datum}"
									pattern="dd/MM/yyyy" /></td>
							<c:set var="prijs" value="0" />
							<c:forEach var="bestellijn" items="${bestelling.bestelbonlijnen}">
								<c:set var="prijs"
									value="${prijs + bestellijn.prijs * bestellijn.aantal}" />
							</c:forEach>
							<td>&euro;<fmt:formatNumber value="${prijs}"
									minFractionDigits="2" maxFractionDigits="2" />
							<td><a href="${url}">Info</a></td>
						</tr>
					</c:forEach>
				</table>
				<c:url var="bestellingenURL" value="/bestellingen" />
				<a href="${bestellingenURL}">Bekijk al mijn bestellingen</a>
			</c:if>
			<c:if test="${empty gebruiker.bestellingen}">
				<div>U hebt geen recente bestellingen</div>
			</c:if>
			<h2>Uw gegevens</h2>
			<div>
				<b>Naam: </b> ${gebruiker.naam}&nbsp;${gebruiker.familienaam}
			</div>
			<div>
				<b>Emailadres: </b> ${gebruiker.emailadres}
			</div>
			<div>
				<b>Straat: </b> ${gebruiker.adres.straat}
			</div>
			<div>
				<b>Huisnummer: </b> ${gebruiker.adres.nummer}
			</div>
			<div>
				<b>Gemeente: </b> ${gebruiker.adres.gemeente}
			</div>
			<div>
				<b>Postcode: </b> ${gebruiker.adres.postcode}
			</div>
			<div>
				<a href="<c:url value='/gebruiker/wijzig' />">Wijzig uw gegevens
					of wachtwoord</a>
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