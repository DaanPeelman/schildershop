<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uw mandje</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/normalize.css' />
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,700,400italic,700italic,900|Play:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	rel="${pageContext.servletContext.contextPath}/styles/layout.css" />
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
			<h1>Uw mandje</h1>
		</header>
		<div id="wrapper">
			<c:if test="${not empty mandje.bestelbonlijnen}">
				<table>
					<thead>
						<tr>
							<td>Titel</td>
							<td>Aantal</td>
							<td>Prijs</td>
							<td>Totaal</td>
							<td>&nbsp;</td>
						</tr>
					</thead>
					<tbody>
						<c:url var="verwijderUrl" value="/mandje" />
						<c:set var="totaalPrijs" value="0" />
						<c:forEach items="${mandje.bestelbonlijnen}" var="bestelbonlijn">
							<tr>
								<td>${bestelbonlijn.product.titel}</td>
								<td>${bestelbonlijn.aantal}</td>
								<td>&euro;<fmt:formatNumber minFractionDigits="2"
										maxFractionDigits="2">${bestelbonlijn.product.prijs}</fmt:formatNumber></td>
								<td>&euro;<fmt:formatNumber minFractionDigits="2"
										maxFractionDigits="2">${bestelbonlijn.aantal * bestelbonlijn.product.prijs}</fmt:formatNumber></td>
								<td><form:form commandName="verwijderUitMandjeForm"
										action="${verwijderUrl}" method="delete">
										<form:input path="productId" type="hidden"
											value="${bestelbonlijn.product.productId}" />
										<input type="submit" value="Verwijder" />
									</form:form></td>
							</tr>
							<c:set var="totaalPrijs"
								value="${totaalPrijs + (bestelbonlijn.product.prijs * bestelbonlijn.aantal)}" />
						</c:forEach>
					</tbody>
				</table>
				<p>
					Totaal: &euro;
					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2">${totaalPrijs}</fmt:formatNumber>
				</p>
				<h2>Afleveradres</h2>
				<c:url var="url" value="/bestellingen" />
				<form:form commandName="adresForm" action="${url}" method="post">
					<div>
						<form:label path="straat">Straat <form:input
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
					<input type="submit" value="Uitchecken" />
				</form:form>
			</c:if>
			<c:if test="${empty mandje.bestelbonlijnen}">
				<p>Er zijn geen producten in uw mandje</p>
			</c:if>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>