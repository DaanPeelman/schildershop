<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
	<head>
		<title>Schilderijen</title>
	<body>
		<a href="<c:url value='/'/>">Home</a>
		<h1>Schilderijen</h1>
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
						<th>${schilderij.titel}</th>
						<th>${schilderij.schilder.naam}</th>
						<th>${schilderij.stijl}</th>
						<th>${schilderij.jaartal}</th>
						<th>${schilderij.prijs}</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>