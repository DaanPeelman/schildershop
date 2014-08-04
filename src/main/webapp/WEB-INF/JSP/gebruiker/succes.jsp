<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registratie succesvol</title>
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
			<jsp:include page="../menu.jsp" />
		</nav>
		<h1>succes</h1>
		</header>
	<div id="wrapper">
		<h2>Registratie succesvol</h2>
		<p>U bent succesvol geregistreerd. U kan u nu aanmelden met de gebruikersnaam ${gebruikersnaam} en bijhorend wachtwoord</p>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
	</footer>
</body>
</html>