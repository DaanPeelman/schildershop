<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	        Hier komt het logo
	    </a>
		<nav>
			<security:authorize access="isAuthenticated()">
				<jsp:include page="../ingelogdMenu.jsp" />
			</security:authorize>
			<ul>
				<jsp:include page="../menuZonderLogin.jsp" />
			</ul>
		</nav>
		<h1>Uw mandje</h1>
		</header>
	<div id="wrapper">
				<c:if test="${not empty mandje.bestelbonlijnen}">
				<table>
				<thead>
					<td>Titel</td>
					<td>Aantal</td>
					<td>Prijs</td>
				</thead>
		<c:forEach items="${mandje.bestelbonlijnen}" var="bestelbonlijn">
		<tr>
			<td>${bestelbonlijn.product.titel}</td>
			<td>${bestelbonlijn.aantal}</td>
			<td>${bestelbonlijn.product.prijs}</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty mandje.bestelbonlijnen}">
		<p>Er zijn geen producten in uw mandje</p>
	</c:if>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>