<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<div>
	<form:label path="titel">Titel: 
						<form:input path="titel" autofocus='autofocus' />
		<form:errors path="titel" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="schilder.naam">Schilder: 
						<form:select path="schilder.naam" items="${schilders}"/>
		<form:errors path="schilder.naam" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="stijl">Stijl: 
						<form:input path="stijl"/>
		<form:errors path="stijl" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="prijs">Prijs: 
						<form:input path="prijs" type='number' />
		<form:errors path="prijs" cssClass="fout" />
	</form:label>
</div>
<div>
	<form:label path="jaartal">Gemaakt in: 
						<form:input path="jaartal" type='number' />
		<form:errors path="jaartal" cssClass="fout" />
	</form:label>
</div>