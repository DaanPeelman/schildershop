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
		<c:if test="${not empty laatsteProducten}">
			<c:forEach var="product" items="${laatsteProducten}">
				<c:url var="url" value="/producten/${product.key.productId}" />
				<div class="nieuwProduct">
					<c:if test="${product.value}">
						<img src="${pageContext.servletContext.contextPath}/img/${product.key.productId}.jpg" />
					</c:if>
					<h2><a href="${url}" ><c:out value="${product.key.titel}" /></a></h2>
					<p>&euro;<fmt:formatNumber value="${product.key.prijs}" minFractionDigits="2" maxFractionDigits="2" /></p>
				</div>
			</c:forEach>
		</c:if>
		<div class="push"></div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>