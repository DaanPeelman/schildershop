<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Index</title>
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
		<div><a href='<c:url value="/" />' id="logo" >
	        <img
			src="${pageContext.servletContext.contextPath}/img/logoSS.png"
			alt="logo">
	    </a>
	    </div>
		<nav>
			<security:authorize access="isAuthenticated()">
				<jsp:include page="ingelogdMenu.jsp" />
			</security:authorize>
			<jsp:include page="menu.jsp" />
		</nav>
		<h1>Onze nieuwste producten:</h1>
	</header>
	<div id="wrapper">
		Hier komt de banner.
		<c:if test="${not empty laatsteProducten}">
		<table>
			<thead>
				<tr>
					<td>Titel</td>
					<td>Schilder</td>
					<td>Jaartal</td>
					<td>Stijl</td>
					<td>Prijs</td>
				</tr>
			</thead>
			<c:forEach var="product" items="${laatsteProducten}">
			<c:url var="url" value="/producten/${product.productId}" />
				<tr>
					<td><a href="${url}">${product.titel}</a></td>
					<td>${product.schilder.naam}</td>
					<td>${product.jaartal}</td>
					<td>${product.stijl}</td>
					<td>&euro;<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${product.prijs}" /></td>
				<tr>
			</c:forEach>
		</table>
		</c:if>
		<div class="push"></div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>