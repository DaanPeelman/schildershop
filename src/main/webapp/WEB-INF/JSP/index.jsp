<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Index</title>
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
			<div>
				<a href='<c:url value="/" />' id="logo"> <img
					src="${pageContext.servletContext.contextPath}/img/logoSS.png"
					alt="logo">
				</a>
			</div>
			<nav>
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
							<span class="nieuwProductImg"><img
								src="${pageContext.servletContext.contextPath}/img/${product.key.productId}.jpg" /></span>
						</c:if>
						<div class="nieuwProductInfo">
							<h2>
								<a href="${url}"><c:out value="${product.key.titel}" /></a>
							</h2>
							<p>
								&euro;
								<fmt:formatNumber value="${product.key.prijs}"
									minFractionDigits="2" maxFractionDigits="2" />
							</p>
							<c:url value="/mandje" var="url" />
							<form:form commandName="bestelProductForm" action="${url}"
								method="put">
								<form:input path="productId" value="${product.key.productId}"
									type="hidden" />
								<form:label path="aantal">Aantal:</form:label>
								<form:input path="aantal" />
								<input type="submit" value="Koop" />
							</form:form>
						</div>
					</div>
				</c:forEach>
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