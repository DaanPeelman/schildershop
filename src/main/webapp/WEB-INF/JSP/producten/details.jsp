<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang='nl'>
<head>
<title>${product.titel}</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/normalize.css' />
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,700,400italic,700italic,900%7CPlay:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/layout.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/lightbox.css" />
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/javascript/lightbox.min.js"></script>
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
				<jsp:include page="../menu.jsp" />
			</nav>
			<h1>${product.titel}</h1>
		</header>
		<div id="wrapper">
			<c:url value="/mandje" var="url" />
			<form:form commandName="bestelProductForm" action="${url}"
				method="post">
				<c:if test='${heeftFoto}'>
					<c:url value='/img/${product.productId}.jpg' var='fotoURL' />
					<a
						href="${pageContext.servletContext.contextPath}/img/${product.productId}.jpg"
						title="${product.titel}" rel="lightbox"><img
						alt="foto van het schilderij" class="extraMarginBottom"
						src="${pageContext.servletContext.contextPath}/img/${product.productId}.jpg"></a>
				</c:if>
				<div class="extraMarginBottom">
					<section>
						Schilder: ${product.schilder.naam}<br> Stijl:
						${product.stijl}<br> Jaar: ${product.jaartal}<br> Prijs:
						&euro;${product.prijs}
					</section>
					<form:input path="productId" type="hidden" />
					<form:label path="aantal">
						<span>Aantal: </span>
						<form:input path="aantal" type="text" />
						<form:errors path="aantal" />
					</form:label>
					<input type="submit" value="Bestel" />
				</div>
			</form:form>
			<security:authorize url='/producten/toevoegen'>
				<spring:url value='/producten/{id}/wijzigen' var='wijzigURL'>
					<spring:param name="id" value="${product.productId}" />
				</spring:url>
				<form action='${wijzigURL}' method='get'>
					<input type='submit' value='Wijzigen'>
				</form>
			</security:authorize>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer class="wrapper">
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>
