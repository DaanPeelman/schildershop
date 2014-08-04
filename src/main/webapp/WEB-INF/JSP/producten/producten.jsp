<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
		<a href='<c:url value="/" />' id="logo"> Hier komt het logo </a>
		<nav>
			<security:authorize access="isAuthenticated()">
				<jsp:include page="../ingelogdMenu.jsp" />
			</security:authorize>
			<ul>
				<jsp:include page="../menuZonderLogin.jsp" />
			</ul>
		</nav>
		<h1>Producten ${filter}</h1>
	</header>
	<div id="wrapper">
		<aside class='sidebar'>
			<c:url var="url" value="/producten" />
			<form:form commandName="titelForm" action="${url}" method="get">
				<div>
					<form:label path="titel">Titel: 
						<form:input path="titel" autofocus='autofocus' />
						<form:errors path="titel" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<input type="submit" value="Zoek" />
					<form:errors cssClass='fout' element='div' />
				</div>
			</form:form>
			<form:form commandName="schilderForm" action="${url}" method="get">
				<div>
					<form:label path="schilderNaam">Schilder: 
						<form:input path="schilderNaam" />
						<form:errors path="schilderNaam" cssClass="fout" />
					</form:label>
				</div>
				<div>
					<input type="submit" value="Zoek" />
					<form:errors cssClass='fout' element='div' />
				</div>
			</form:form>
			<form:form commandName="stijlForm" action="${url}" method="get">
				<div>
					<form:label path="stijl">Stijl: 
						<form:select path="stijl" items="${stijlen}" />
						<form:errors path="stijl" cssClass="fout" />
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
									<th><a href="${productURL}">${schilderij.titel}</a></th>
									<th>${schilderij.schilder.naam}</th>
									<th>${schilderij.stijl}</th>
									<th>${schilderij.jaartal}</th>
									<th>${schilderij.prijs}</th>
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
	</div>
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>
