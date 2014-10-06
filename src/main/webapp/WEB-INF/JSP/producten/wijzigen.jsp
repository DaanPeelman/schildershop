<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<title>Product ${product.titel} wijzigen</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/normalize.css' />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
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
			<div>
				<a href='<c:url value="/" />' id="logo"> <img
					src="${pageContext.servletContext.contextPath}/img/logoSS.png"
					alt="logo">
				</a>
			</div>
			<nav>
				<jsp:include page="../menu.jsp" />
			</nav>
			<h1>Product ${product.titel} wijzigen</h1>
		</header>
		<div id="wrapper">
			<spring:url value='/producten/{id}' var='wijzigURL'>
				<spring:param name='id' value='${product.productId}' />
			</spring:url>
			<form:form action='${wijzigURL}' method='put' commandName='product'
				id='wijzigform'>
				<jsp:include page='productformfields.jsp' />
				<input type='submit' value='Wijzigen' id='wijzigknop'>
			</form:form>
			${succesProduct}
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
	<script>
		document.getElementById('wijzigform').onsubmit = function() {
			document.getElementById('wijzigknop').disabled = true;
		};
	</script>
</body>
</html>