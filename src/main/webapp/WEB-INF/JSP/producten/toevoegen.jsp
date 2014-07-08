<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
	<head>
		<title>Product toevoegen</title>
		<link rel='stylesheet'
			href='${pageContext.servletContext.contextPath}/styles/default.css'>
		</head>
	<body>
		<header>
			<nav>
				<jsp:include page="/WEB-INF/JSP/menu.jsp"/>
			</nav>
		</header>
		<div>
			<section class='content'>
				<h1>Product toevoegen</h1>
				<c:url value='/producten/toevoegen' var='producturl'/>
				<form:form action='${producturl}' method='post' commandName='product'
					id='toevoegform'>
					<jsp:include page='productformfields.jsp'/>
					<input type='submit' value='Toevoegen' id='toevoegknop'>
				</form:form>
				${succesProduct}
				<h2>Schilder toevoegen</h2>
				<c:url value='/producten/toevoegen' var='schilderurl'/>
				<form:form action='${schilderurl}' method='post' commandName='schilder'
					id='toevoegform'>
					<form:label path="naam">Schilder: 
						<form:input path="naam" />
						<form:errors path="naam" cssClass="fout" />
					</form:label>
					<input type='submit' value='Toevoegen' id='toevoegknop'>
				</form:form>
				${succesSchilder}
			</section>
		</div>
		<footer>
			<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
		</footer>
	</body>
</html>