<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<title>Schilderijen</title>
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
		<div>
			<a href='<c:url value="/" />' id="logo"> <img
				src="${pageContext.servletContext.contextPath}/img/logoSS.png"
				alt="logo">
			</a>
		</div>
		<nav>
			<security:authorize access="isAuthenticated()">
				<jsp:include page="../ingelogdMenu.jsp" />
			</security:authorize>
			<jsp:include page="../menu.jsp" />
		</nav>
		<h1>Producten ${filter}</h1>
	</header>
	<div id="wrapper">
		<aside class='sidebar'>
			<c:url var="url" value="/producten" />
			<form:form commandName="zoekTermForm" action="${url}" method="get">
				<div>
					<form:label path="zoekterm">
						<form:input path="zoekterm" autofocus='autofocus' />
						<form:errors path="zoekterm" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<input type="submit" value="Zoek" />
					<form:errors cssClass='fout' element='div' />
				</div>
			</form:form>
			<form:form commandName="vanTotPrijsForm" action="${url}" method="get">
				<div>
					<form:label path="vanPrijs">Prijs vanaf 
						<form:input path="vanPrijs" type='number' />
						<form:errors path="vanPrijs" cssClass="fout" />
					</form:label>
					<form:label path="totPrijs"> tot 
						<form:input path="totPrijs" type='number' />
						<form:errors path="totPrijs" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<input type="submit" value="Zoek" />
					<form:errors cssClass='fout' element='div' />
				</div>
			</form:form>
			<form:form commandName="vanTotJaartalForm" action="${url}"
				method="get">
				<div>
					<form:label path="vanJaartal">Gemaakt tussen 
						<form:input path="vanJaartal" type='number' />
						<form:errors path="vanJaartal" cssClass="fout" />
					</form:label>
					<form:label path="totJaartal"> en 
						<form:input path="totJaartal" type='number' />
						<form:errors path="totJaartal" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<input type="submit" value="Zoek" />
					<form:errors cssClass='fout' element='div' />
				</div>
			</form:form>
		</aside>
		<section class='content'>
			<c:choose>
				<c:when test="${not empty schilderijen}">
					<table>
						<thead>
							<tr>
								<th>Titel</th>
								<th>Schilder</th>
								<th>Stijl</th>
								<th>Jaartal</th>
								<th>Prijs</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${schilderijen}" var="schilderij">
								<tr>
									<spring:url value="/producten/{productId}" var="productURL">
										<spring:param name="productId" value="${schilderij.productId}" />
									</spring:url>
									<td><a href="${productURL}">${schilderij.titel}</a></td>
									<td>${schilderij.schilder.naam}</td>
									<td>${schilderij.stijl}</td>
									<td>${schilderij.jaartal}</td>
									<td>${schilderij.prijs}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
						Er werden geen schilderijen gevonden.
					</c:otherwise>
			</c:choose>
		</section>
		<div class="push"></div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>
