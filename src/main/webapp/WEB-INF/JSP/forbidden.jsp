<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<title>Geen toegang</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/normalize.css' />
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,700,400italic,700italic,900%7CPlay:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/layout.css" />
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="sticky_wrapper">
		<header>
			<a href='<c:url value="/" />' id="logo"> <img
				src="${pageContext.servletContext.contextPath}/img/logoSS.png"
				alt="logo">
			</a>
			<nav>
				<jsp:include page="menu.jsp" />
			</nav>
		</header>
		<div id="wrapper">
			<p>U hebt geen toegang tot dit onderdeel.</p>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer class="wrapper">
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>