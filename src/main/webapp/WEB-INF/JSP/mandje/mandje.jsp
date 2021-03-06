<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uw mandje</title>
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
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						if (($('#straat').val() == "${adresGebruiker.straat}")
								&& ($('#nummer').val() == "${adresGebruiker.nummer}")
								&& ($('#gemeente').val() == "${adresGebruiker.gemeente}")
								&& ($('#postcode').val() == "${adresGebruiker.postcode}")) {
							$('#cbxThuisAdres').prop('checked', true);
						}

						$('#cbxThuisAdres').change(
								function() {
									if ($('#cbxThuisAdres').is(':checked')) {
										$('#straat').val(
												"${adresGebruiker.straat}");
										$('#nummer').val(
												"${adresGebruiker.nummer}");
										$('#gemeente').val(
												"${adresGebruiker.gemeente}");
										$('#postcode').val(
												"${adresGebruiker.postcode}");
									} else {
										$('#straat').val("");
										$('#nummer').val("");
										$('#gemeente').val("");
										$('#postcode').val("");
									}
								});
						
						$('table input').change(function() {
							if(!isNaN($(this).val())) {
								var nAantal = parseInt($(this).val());
								if(nAantal > 0) {									
									var aVelden = $('table input:not([type="hidden"]').size();
									var nTotaal = 0;
									var bKomma = false;
									for(var i = 0; i < aVelden; i++) {
										var sNaamAantal = "#lijnen" + i;
										var nAantal = parseInt($(sNaamAantal).val());

										var sNaamPrijs = "#lijnen" + i + "Prijs";
										var sPrijs = $(sNaamPrijs).html();
										
										if(sPrijs.indexOf(',') > 0) {
											bKomma = true;
											sPrijs = sPrijs.replace(',', '.');
										}
										
										var nPrijs = parseFloat(sPrijs);
										
										var sNaamTotaal = "#lijnen" + i + "Totaal";
										var sSubTotaal = (nAantal * nPrijs).toFixed(2) + "";
										
										if(bKomma) {
											sSubTotaal = sSubTotaal.replace('.', ',');
										}
										
										$(sNaamTotaal).html(sSubTotaal);
										
										nTotaal += (nAantal * nPrijs);
									}
									
									sTotaal = nTotaal.toFixed(2) + "";
									
									if(bKomma) {
										sTotaal = sTotaal.replace('.', ',');
									}
									$('#totaal').html(sTotaal);
								}
							}
							
						});
					});
</script>
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
			<h1>Uw mandje</h1>
		</header>
		<div id="wrapper">
			<c:if test="${not empty productenInMandje.bestelbonlijnen}">
				<c:url var="url" value="/mandje" />
				<form:form commandName="mandjeForm" action="${url}" method="put">
					<table>
						<thead>
							<tr>
								<th>Titel</th>
								<th>Aantal</th>
								<th>Prijs</th>
								<th>Totaal</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="totaalPrijs" value="0" />
							<c:forEach items="${productenInMandje.bestelbonlijnen}"
								var="bestelbonlijn" varStatus="i">
								<spring:url var="verwijderUrl" value="/mandje/{id}/verwijder">
									<spring:param name="id"
										value="${bestelbonlijn.product.productId}" />
								</spring:url>
								<tr>
									<td>${bestelbonlijn.product.titel}</td>
									<form:input path="lijnen[${i.index}].id" type="hidden"
										value="${bestelbonlijn.product.productId}" />
									<td class="center"><form:input path="lijnen[${i.index}].aantal"
											type="text" title="voer het aantal in dat u wil bestellen"
											value="${bestelbonlijn.aantal}" id="lijnen${i.index}"/> <form:errors
											path="lijnen[${i.index}].aantal" cssClass="fout" /></td>
									<td>&euro;<span id="lijnen${i.index}Prijs"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${bestelbonlijn.product.prijs}"/></span></td>
									<td>&euro;<span id="lijnen${i.index}Totaal"><fmt:formatNumber minFractionDigits="2"
											maxFractionDigits="2"
											value="${bestelbonlijn.aantal * bestelbonlijn.product.prijs}" /></span></td>
									<td><a href="${verwijderUrl}"
										title="verwijder dit product uit uw mandje">X</a></td>
								</tr>
								<c:set var="totaalPrijs"
									value="${totaalPrijs + (bestelbonlijn.product.prijs * bestelbonlijn.aantal)}" />
							</c:forEach>
						</tbody>
					</table>
					<p>
						Totaal: &euro;
						<span id="totaal"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2">${totaalPrijs}</fmt:formatNumber></span>
					</p>
					<div class="lagereDiv">
						<h2>Afleveradres</h2>
						<c:if test="${not empty adresGebruiker}">
							<label class="naCheckbox"><input id="cbxThuisAdres"
								type="checkbox" />Gebruik mijn thuisadres</label>
						</c:if>
						<div>
							<form:label path="adres.straat">
								<span>Straat </span>
								<form:input id="straat" path="adres.straat" />
								<form:errors path="adres.straat" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="adres.nummer">
								<span>Nummer: </span>
								<form:input id="nummer" path="adres.nummer" />
								<form:errors path="adres.nummer" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="adres.gemeente">
								<span>Gemeente: </span>
								<form:input id="gemeente" path="adres.gemeente" />
								<form:errors path="adres.gemeente" cssClass="fout" />
							</form:label>
						</div>
						<div>
							<form:label path="adres.postcode">
								<span>Postcode: </span>
								<form:input id="postcode" path="adres.postcode" />
								<form:errors path="adres.postcode" cssClass="fout" />
							</form:label>
						</div>
						<input type="submit" value="Uitchecken" />
					</div>
				</form:form>
			</c:if>
			<c:if test="${empty productenInMandje.bestelbonlijnen}">
				<p>Er zijn geen producten in uw mandje</p>
			</c:if>
		</div>
		<div class="push"></div>
	</div>
	<!--  END sticky_wrapper -->
	<footer>
		<jsp:include page="/WEB-INF/JSP/footer.jsp" />
	</footer>
</body>
</html>