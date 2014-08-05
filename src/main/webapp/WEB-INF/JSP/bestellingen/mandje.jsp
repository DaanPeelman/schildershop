<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
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
					</tr>
				</thead>
				<tbody>
		<c:forEach items="${mandje.bestelbonlijnen}" var="bestelbonlijn">
		<tr>
			<td>${bestelbonlijn.product.titel}</td>
			<td>${bestelbonlijn.aantal}</td>
			<td>${bestelbonlijn.product.prijs}</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
		<h2>Afleveradres</h2>
		<c:url var="url" value="/bestellingen" />
		<form:form commandName="adresForm" action="${url}" method="post">
			<div><form:label path="straat">Straat <form:input path="straat"/><form:errors path="straat" cssClass="fout"/></form:label></div>
			<div><form:label path="nummer">Nummer: <form:input path="nummer" /><form:errors path="nummer" cssClass="fout"/></form:label></div>
			<div><form:label path="gemeente">Gemeente: <form:input path="gemeente"/><form:errors path="gemeente" cssClass="fout"/></form:label></div>
			<div><form:label path="postcode">Postcode: <form:input path="postcode"/><form:errors path="postcode" cssClass="fout"/></form:label></div>
			<input type="submit" value="Uitchecken" />
		</form:form>
	</c:if>
	<c:if test="${empty mandje.bestelbonlijnen}">
		<p>Er zijn geen producten in uw mandje</p>
	</c:if>
	<div class="push"></div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>