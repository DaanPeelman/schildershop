<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Bestelling</title>
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
		<a href='<c:url value="/" />' id="logo" >
	        Hier komt het logo
	    </a>
		<nav>
			<security:authorize access="isAuthenticated()">
				<jsp:include page="../ingelogdMenu.jsp"/>
			</security:authorize>
			<ul>
				<jsp:include page="../menuZonderLogin.jsp" />
			</ul>
		</nav>
		<h1>
			Overzicht bestelling van
			<fmt:formatDate value="${bestelbon.datum}" dateStyle="long" />
		</h1>
	</header>
	<div id="wrapper">
		<table>
			<thead>
				<tr>
					<td>Naam</td>
					<td>Aantal</td>
					<td>Prijs per eenheid</td>
					<td>Totale prijs</td>
				</tr>
			</thead>
			<tbody>
				<c:set var="totalePrijs" value="0" />
				<c:forEach var="bestellijn" items="${bestelbon.bestelbonlijnen}">
					<spring:url value="/producten/{productId}" var="url">
						<spring:param name="productId"
							value="${bestellijn.product.productId}" />
					</spring:url>
					<tr>
						<td><a href="${url}"><c:out
									value="${bestellijn.product.titel}" /></a></td>
						<td>${bestellijn.aantal}</td>
						<td>&euro;<fmt:formatNumber value="${bestellijn.prijs}"
								minFractionDigits="2" maxFractionDigits="2" /></td>
						<c:set var="totaleRijPrijs"
							value="${bestellijn.aantal * bestellijn.prijs}" />
						<td>&euro;<fmt:formatNumber value="${totaleRijPrijs}"
								minFractionDigits="2" maxFractionDigits="2" /></td>
					</tr>
					<c:set var="totalePrijs" value="${totalePrijs + totaleRijPrijs}" />
				</c:forEach>
			</tbody>
		</table>
		<div>
			Totaal: &euro;
			<fmt:formatNumber value="${totalePrijs}" minFractionDigits="2"
				maxFractionDigits="2" />
		</div>
		<div class="push"></div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>