<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<title>Over Schildershop</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/normalize.css' />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,700,400italic,700italic,900|Play:400,700'
	rel='stylesheet' type='text/css'>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
</head>
<body>
	<header>
		<div>
			<a href='<c:url value="/" />' id="logo"> <img
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
		<h1>About us</h1>
	</header>
	<div id="wrapper">
		<p>
			SchilderShop is het Java eindproject van <a
				href='https://www.linkedin.com/pub/daan-peelman/95/5b9/a78/nl'
				target="_blank">Daan Peelman</a> en <a
				href='https://www.linkedin.com/in/lisasteendam' target="_blank">Lisa
				Steendam</a>.
		</p>
		<div class="push"></div>
		<footer>
			<jsp:include page="/WEB-INF/JSP/footer.jsp" />
		</footer>
	</div>
</body>
</html>
