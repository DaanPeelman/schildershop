<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<title>Geen toegang</title>
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
				<jsp:include page="ingelogdMenu.jsp" />
			</security:authorize>
			<jsp:include page="menu.jsp" />
		</nav>
	</header>
	<div id="wrapper" >
		U hebt geen toegang tot dit onderdeel.
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>