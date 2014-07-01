<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang='nl'>
	<head>
		<title>Zoeken</title>
	<body>
		<a href="<c:url value='/'/>">Home</a>
		<h1>Zoeken</h1>
		<c:url var="url" value="/producten" />
		<form:form commandName="zoekForm" action="${url}" method="get">
			<div>
				<form:label path="titel">Titel: 
				<form:input path="titel" autofocus='autofocus'/>
				<form:errors path="titel" cssClass="fout"/>
				</form:label>
			</div>
			<div>
				<form:label path="schilderNaam">Schilder: 
				<form:input path="schilderNaam" />
				<form:errors path="schilderNaam" cssClass="fout"/>
				</form:label>
			</div>
			<div>
				<form:label path="stijl">Stijl: 
				<form:input path="stijl" />
				<form:errors path="stijl" cssClass="fout"/>
				</form:label>
			</div>
			<div>
				<form:label path="vanPrijs">Prijs vanaf 
				<form:input path="vanPrijs" type='number'/>
				<form:errors path="vanPrijs" cssClass="fout"/>
				</form:label>
				<form:label path="totPrijs"> tot 
				<form:input path="totPrijs" type='number'/>
				<form:errors path="totPrijs" cssClass="fout"/>
				</form:label>
			</div>
			<div>
				<form:label path="vanJaartal">Gemaakt tussen 
				<form:input path="vanJaartal" type='number'/>
				<form:errors path="vanJaartal" cssClass="fout"/>
				</form:label>
				<form:label path="totJaartal"> en 
				<form:input path="totJaartal" type='number'/>
				<form:errors path="totJaartal" cssClass="fout"/>
				</form:label>
			</div>
			<div>
				<input type="submit" value="Zoek" />
				<form:errors cssClass='fout' element='div'/>
			</div>
		</form:form>
		<c:if test="${not empty resultaat}">
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
					<c:forEach items="${resultaat}" var="schilderij">
						<tr>
							<th>${schilderij.titel}</th>
							<th>${schilderij.schilder.naam}</th>
							<th>${schilderij.stijl}</th>
							<th>${schilderij.jaartal}</th>
							<th>${schilderij.prijs}</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>