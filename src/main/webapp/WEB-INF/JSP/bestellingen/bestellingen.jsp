<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alle bestellingen</title>
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
	        <img
			src="${pageContext.servletContext.contextPath}/img/logoSS.png"
			alt="logo">
	    </a>
		<nav>
			<security:authorize access="isAuthenticated()">
				<jsp:include page="../ingelogdMenu.jsp" />
			</security:authorize>
			<jsp:include page="../menu.jsp" />
		</nav>
		<h1>Al uw bestellingen</h1>
		</header>
	<div id="wrapper">
	<c:if test="${not empty bestellingen}">
		<table>
			<thead>
				<tr>
					<td>Datum</td>
					<td>Prijs</td>
					<td>Info</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bestelling" items="${bestellingen}" end="0">
					<c:set var="bestelDatum" value="${bestelling.datum}" />
				</c:forEach>
				<tr>
					<fmt:formatDate var="fmtDatum" value="${bestelDatum}" pattern="MMMM yyyy" />
					<td colspan="3"><b><c:out value="${fmtDatum}" /></b></td>
				</tr>
				
				<c:forEach var="bestelling" items="${bestellingen}">
				
				<c:if test="${(bestelDatum.year != bestelling.datum.year) || (bestelDatum.month != bestelling.datum.month)}">
					<c:set var="bestelDatum" value="${bestelling.datum}" />
					<tr>
						<fmt:formatDate var="fmtDatum" value="${bestelDatum}" pattern="MMMM yyyy" />
						<td colspan="3"><b><c:out value="${fmtDatum}" /></b></td>
					</tr>
				</c:if>
				
					<spring:url var="url" value="/bestellingen/{bestelbonId}">
						<spring:param name="bestelbonId" value="${bestelling.bestelbonId}" />
					</spring:url>
					<tr>
						<td><fmt:formatDate value="${bestelling.datum}" pattern="dd/MM/yyyy" /></td>
						<c:set var="prijs" value="0" />
						<c:forEach var="bestellijn" items="${bestelling.bestelbonlijnen}">
							<c:set var="prijs"
								value="${prijs + bestellijn.prijs * bestellijn.aantal}" />
						</c:forEach>
						<td>&euro;<fmt:formatNumber value="${prijs}" minFractionDigits="2" maxFractionDigits="2" />
						<td><a href="${url}">Info</a></td>
					</tr>
					
					
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty bestellingen}">
		<p>U hebt nog geen bestellingen</p>
	</c:if>
	<div class="push"></div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>