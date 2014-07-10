<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang='nl'>
	<head>
		<title>${product.titel}</title>
		<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css' />
	</head>
	<body>
		<header>
			<nav>
				<jsp:include page="/WEB-INF/JSP/menu.jsp"/>
			</nav>
			<h1>${product.titel}</h1>
		</header>
		<div>
			<section>
				Schilder: ${product.schilder.naam}<br>
				Stijl: ${product.stijl}<br>
				Jaar: ${product.jaartal}<br>
				Prijs: €${product.prijs}
			</section>
			<c:if test='${heeftFoto}'>
			<dt>Foto</dt>
				<dd>
					<c:url value='/img/${product.productId}.jpg' var='fotoURL'/>
					<img alt="foto van het schilderij" src="${pageContext.servletContext.contextPath}/img/1.jpg">
				</dd>
			</c:if>
		</div>
		<footer>
			<jsp:include page="/WEB-INF/JSP/footer.jsp"/>
		</footer>
	</body>
</html>
