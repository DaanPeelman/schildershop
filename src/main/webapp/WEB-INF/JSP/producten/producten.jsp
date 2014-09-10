<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script>
	$(function() {
		var iMaxPrijs = parseInt("${maxPrijs}");

		var sMinPrijs = parseInt("${sMinPrijs}");
		var sMaxPrijs = parseInt("${sMaxPrijs}");

		if (isNaN(sMinPrijs)) {
			sMinPrijs = 0;
		}
		if (isNaN(sMaxPrijs)) {
			sMaxPrijs = iMaxPrijs;
		}

		$("#slider-range-prijs").slider({
			range : true,
			min : 0,
			max : iMaxPrijs,
			values : [ sMinPrijs, sMaxPrijs ],
			slide : function(event, ui) {
				$("#prijsSpan").html("€" + ui.values[0] + " - €" + ui.values[1]);
				$("#vanPrijs").val(ui.values[0]);
				$("#totPrijs").val(ui.values[1]);
			}
		});
		$("#prijsSpan").html(
				"€" + $("#slider-range-prijs").slider("values", 0) + " - €"
						+ $("#slider-range-prijs").slider("values", 1));
		$("#vanPrijs").val($("#slider-range-prijs").slider("values", 0));
		$("#totPrijs").val($("#slider-range-prijs").slider("values", 1));

	});

	var iMinJaar = parseInt("${minDatum}");
	var iMaxJaar = parseInt("${maxDatum}");

	var sMinJaar = parseInt("${sMinJaar}");
	var sMaxJaar = parseInt("${sMaxJaar}");

	if (isNaN(sMinJaar)) {
		sMinJaar = iMinJaar;
	}
	if (isNaN(sMaxJaar)) {
		sMaxJaar = iMaxJaar;
	}
	$(function() {

		$("#slider-range-jaartal").slider({
			range : true,
			min : iMinJaar,
			max : iMaxJaar,
			values : [ sMinJaar, sMaxJaar ],
			slide : function(event, ui) {
				$("#periodeSpan").html(ui.values[0] + " - " + ui.values[1]);
				$("#vanJaartal").val(ui.values[0]);
				$("#totJaartal").val(ui.values[1]);
			}
		});
		$("#periodeSpan").html(
				$("#slider-range-jaartal").slider("values", 0) + " - "
						+ $("#slider-range-jaartal").slider("values", 1));
		$("#vanJaartal").val($("#slider-range-jaartal").slider("values", 0));
		$("#totJaartal").val($("#slider-range-jaartal").slider("values", 1));
	});
</script>
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
			<h1>Producten ${filter}</h1>
		</header>
		<div id="wrapper productWrapper">
			<aside class='sidebar'>
				<c:url var="url" value="/producten" />
				<form:form commandName="zoekTermForm" action="${url}" method="get">
					<div>
						<form:label path="zoekterm">
							<span>Zoekterm: </span>
							<form:input path="zoekterm" autofocus='autofocus' />
							<form:errors path="zoekterm" cssClass="fout" />
						</form:label>
					</div>
					<div class="slider">
						<label for="amount"><span class="wijder">Prijs: <span id="prijsSpan"></span></span></label>
						<div id="slider-range-prijs"></div>
						<form:input type="hidden" path="vanPrijs" id="vanPrijs" />
						<form:input type="hidden" path="totPrijs" id="totPrijs" />
					</div>
					<div class="slider">
						<label for="periode"><span class="wijder">Periode: <span id="periodeSpan"></span></span></label>
						<div id="slider-range-jaartal"></div>
						<form:input type="hidden" path="vanJaartal" id="vanJaartal" />
						<form:input type="hidden" path="totJaartal" id="totJaartal" />
					</div>
					<div>
						<input type="submit" value="Zoek" />
						<form:errors cssClass='fout' element='div' />
					</div>
				</form:form>
			</aside>
			<div class='lagereDiv productLijst'>
				<c:choose>
					<c:when test="${not empty producten}">
						<ul id="gallery">
							<c:forEach items="${producten}" var="product">
								<spring:url value="/producten/{productId}" var="productURL">
									<spring:param name="productId" value="${product.key.productId}" />
								</spring:url>
								<li class="nieuwProduct"><c:if test="${product.value}">
										<span class="nieuwProductImg"
											style="background-image: url('${pageContext.servletContext.contextPath}/img/${product.key.productId}.jpg');"><img
											src="${pageContext.servletContext.contextPath}/img/${product.key.productId}.jpg" /></span>
									</c:if>
									<div class="nieuwProductInfo">
										<h2>
											<a href="${productURL}">${product.key.titel}</a>
										</h2>
										<span class="enkel">${product.key.schilder.naam}</span> <span>${product.key.stijl}</span><span
											class="rechts">${product.key.jaartal}</span><span
											class="enkel"> &euro;<fmt:formatNumber
												value="${product.key.prijs}" minFractionDigits="2"
												maxFractionDigits="2" />
										</span>
									</div></li>
							</c:forEach>
						</ul>
						<p id="paginering">
							<c:if test="${hasLess}">
								<c:url value="${huidigUrl}" var="vorigeUrl">
									<c:param name="page" value="${huidigePagina - 1}" />
								</c:url>
								<a href="${vorigeUrl}">&lt; Vorige</a>
							</c:if>
							&nbsp;&nbsp;${huidigePagina}&nbsp;&nbsp;
							<c:if test="${hasMore}">
								<c:url value="${huidigUrl}" var="volgendeUrl">
									<c:param name="page" value="${huidigePagina + 1}" />
								</c:url>
								<a href="${volgendeUrl}">Volgende &gt;</a>
							</c:if>
						</p>
					</c:when>
					<c:otherwise>
					Er werden geen schilderijen gevonden.
				</c:otherwise>
				</c:choose>
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
