<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<title>Product toevoegen</title>
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
				<jsp:include page="../menu.jsp" />
			</nav>
			<h1>Toevoegen</h1>
		</header>
		<div id="wrapper">
			<section class='content'>
				<h2>Product toevoegen</h2>
				<c:url value='/producten/toevoegen' var='producturl' />
				<form:form action='${producturl}' method='post'
					commandName='product' id='toevoegform'
					enctype="multipart/form-data">
					<jsp:include page='productformfields.jsp' />
					<label><span>Afbeelding: </span><input type="file"
						name="foto"></label>
					<br>
					<input type='submit' value='Toevoegen' id='toevoegknop'>
					<form:errors cssClass='fout' />
				</form:form>
				${succesProduct}
				<h2>Schilder toevoegen</h2>
				<c:url value='/producten/toevoegen' var='schilderurl' />
				<form:form action='${schilderurl}' method='post'
					commandName='schilder' id='toevoegform'>
					<form:label path="naam">
						<span>Schilder: </span>
						<form:input path="naam" />
						<form:errors path="naam" cssClass="fout" />
					</form:label>
					<input type='submit' value='Toevoegen' id='toevoegknop'>
				</form:form>
				${succesSchilder}
			</section>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>