<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang='nl'>
<head>
<title>${product.titel}</title>
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
			<ul>
				<jsp:include page="../menuZonderLogin.jsp" />
			</ul>
		</nav>
		<h1>${product.titel}</h1>
	</header>
	<div id="wrapper">
		<c:url value="/mandje"  var="url"/>
		<form:form commandName="mandjeForm" action="${url}" method="put">
			<c:if test='${heeftFoto}'>
				<c:url value='/img/${product.productId}.jpg' var='fotoURL' />
				<img alt="foto van het schilderij" id="detailImg"
					src="${pageContext.servletContext.contextPath}/img/${product.productId}.jpg">
			</c:if>
			<section>
				Schilder: ${product.schilder.naam}<br> Stijl: ${product.stijl}<br>
				Jaar: ${product.jaartal}<br> Prijs: €${product.prijs}
			</section>
			<form:input path="productId" type="hidden" />
			<form:label path="aantal">
				Aantal: 
				<form:input path="aantal" type="text"/>
				<form:errors path="aantal" />
			</form:label>
			<input type="submit" value="Koop" />
		</form:form>
		<div class="push"></div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>
