<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang='nl'>
<head>
<title>Over Schildershop</title>
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
				<jsp:include page="menu.jsp" />
			</nav>
			<h1>About us</h1>
		</header>
		<div id="wrapper">
			<h2>Wat</h2>
			<p>
				SchilderShop is het Java eindproject van <a class="contact_link"
					href='https://www.linkedin.com/pub/daan-peelman/95/5b9/a78/nl'
					target="_blank">Daan Peelman</a> en <a class="contact_link"
					href='https://www.linkedin.com/in/lisasteendam' target="_blank">Lisa
					Steendam</a>. Het is een webshop die replica's van bekende schilderijen
				verkoopt. Je kan als klant of administrator een account aanmaken.
				Klanten kunnen er hun aankoophistorie bekijken en administrators
				kunnen nieuwe schilders en schilderijen toevoegen.
			</p>
			<h2>Hoe</h2>
			<p>
				De database achter de site draait op MySQL, de code van de site zelf
				is Java-based en maakt gebruik van JPA, Hibernate en Spring. De code
				kan je <a class="contact_link"
					href='https://github.com/DaanPeelman/schildershop' target="_blank">hier</a>
				bekijken. Om dit project als team in goede banen te leiden
				gebruikten we Github en Scrum.
			</p>
			<p>Het design van de site maakt gebruik van de Responsive
				principes, bekijk de pagina's dus zeker ook eens in een ander
				schermformaat.</p>
			<h2>Wie</h2>
			<div>
				<img alt="portret Daan Peelman"
					src="${pageContext.servletContext.contextPath}/img/daan.jpg"
					class="portret">
				<h3>
					<a class="contact_link"
						href='https://www.linkedin.com/pub/daan-peelman/95/5b9/a78/nl'
						target="_blank">Daan Peelman</a>
				</h3>
				<p>Als beginnend Java Ontwikkelaar ben ik opzoek naar de kans op
					een eerste professionele werkervaring. Ik ben een vriendelijk en
					actief persoon die houdt van leren binnen de IT wereld.</p>
				<p>Vloeiend in Java, JSP, Servlets, Hibernate,...</p>
			</div>
			<br style="clear: both;" />
			<div>
				<img alt="portret Lisa Steendam"
					src="${pageContext.servletContext.contextPath}/img/lisa.jpg"
					class="portret">
				<h3>
					<a class="contact_link"
						href='https://www.linkedin.com/in/lisasteendam' target="_blank">Lisa
						Steendam</a>
				</h3>
				<p>Als gemotiveerde junior software ontwikkelaar, ben ik
					momenteel op zoek naar werkgelegenheid als onderdeel van een
					dynamisch software development team. De studentenjobs die ik de
					afgelopen jaren heb uitgeoefend verbeterden mijn klantgeoriënteerde
					vaardigheden en maakten van mij een echte team player.</p>
				<ul>
					<li>Grondig getraind in Java en de meeste gerelateerde
						frameworks</li>
					<li>Bereid om in het buitenland te werken</li>
					<li>Ook voor een IBO of stage mag u mij contacteren</li>
				</ul>
			</div>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>
