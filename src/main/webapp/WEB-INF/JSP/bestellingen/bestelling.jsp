<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bestelling</title>
</head>
<body>
	<security:authorize access="isAuthenticated()">
	<jsp:include page="../ingelogdMenu.jsp" />
	</security:authorize>
	<jsp:include page="../menu.jsp" />
	<h1>Overzicht bestelling van <fmt:formatDate value="${bestelbon.datum}" dateStyle="long"/></h1>
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
			<spring:url value="/producten/{productId}" var="url" >
				<spring:param name="productId" value="${bestellijn.product.productId}" />
			</spring:url>
				<tr>
					<td><a href="${url}"><c:out value="${bestellijn.product.titel}" /></a></td>
					<td>${bestellijn.aantal}</td>
					<td>&euro;<fmt:formatNumber value="${bestellijn.prijs}" minFractionDigits="2" maxFractionDigits="2"/></td>
					<c:set var="totaleRijPrijs" value="${bestellijn.aantal * bestellijn.prijs}"/>
					<td>&euro;<fmt:formatNumber value="${totaleRijPrijs}" minFractionDigits="2" maxFractionDigits="2"/></td>
				</tr>
				<c:set var="totalePrijs" value="${totalePrijs + totaleRijPrijs}"/>
			</c:forEach>
		</tbody>	
	</table>
	<div>Totaal: &euro;<fmt:formatNumber value="${totalePrijs}" minFractionDigits="2" maxFractionDigits="2" /></div>
</body>
</html>