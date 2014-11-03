<h1>
	<small>Dictionary Check</small>
</h1>
<div style="position: fixed; margin: 0px; top: 10px; right: 10px;">
	<c:forEach var="type" items="${dictTypes}">
		<button type="button" class="btn btn-success btn-xs dictType" value="${type}">${type}</button>	
	</c:forEach>
</div>
<div id="showDataDiv"></div>