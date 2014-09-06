<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uw mandje</title>
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
			<h1>Overzicht van uw mandje</h1>
		</header>
		<div id="wrapper">
			<c:if test="${not empty productenInMandje.bestelbonlijnen}">
				<table>
					<thead>
						<tr>
							<th>Titel</th>
							<th>Aantal</th>
							<th>Prijs</th>
							<th>Totaal</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="totaalPrijs" value="0" />
						<c:forEach items="${productenInMandje.bestelbonlijnen}"
							var="bestelbonlijn" varStatus="i">
							<tr>
								<td>${bestelbonlijn.product.titel}</td>
								<td>${bestelbonlijn.aantal}</td>
								<td>&euro;<fmt:formatNumber minFractionDigits="2"
										maxFractionDigits="2" value="${bestelbonlijn.prijs}" /></td>
								<td>&euro;<fmt:formatNumber minFractionDigits="2"
										maxFractionDigits="2"
										value="${bestelbonlijn.aantal * bestelbonlijn.product.prijs}" /></td>
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
				<div class="lagereDiv">
					<h2>Leveradres</h2>
					<p class="adres">${productenInMandje.leverAdres.straat}&nbsp;${productenInMandje.leverAdres.nummer},
						${productenInMandje.leverAdres.postcode}&nbsp;${productenInMandje.leverAdres.gemeente}</p>
					<c:url var="url" value="/bestellingen" />
					<form action="${url}" method="post">
						<input type="submit" value="Bestel" />
					</form>
				</div>
			</c:if>
			<c:if test="${empty productenInMandje.bestelbonlijnen}">
				<p>Er zijn geen producten in uw mandje.</p>
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