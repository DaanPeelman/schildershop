<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<div>
	<form:label path="titel"><span>Titel: </span>
						<form:input path="titel" autofocus='autofocus' />
		<form:errors path="titel" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="schilder"><span>Schilder: </span>
						<form:select path="schilder" items="${schilders}"/>
		<form:errors path="schilder" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="stijl"><span>Stijl: </span>
						<form:input path="stijl"/>
		<form:errors path="stijl" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="prijs"><span>Prijs: </span>
						<form:input path="prijs" type='text' />
		<form:errors path="prijs" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="jaartal"><span>Gemaakt in: </span>
						<form:input path="jaartal" type='number' />
		<form:errors path="jaartal" cssClass="fout" />
	</form:label>
</div>