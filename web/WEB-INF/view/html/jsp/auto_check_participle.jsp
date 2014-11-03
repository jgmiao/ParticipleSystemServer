<div id="autoAppendDiv">
	<input type="hidden" id="callBackName" value="${checkName}"></input>
	<button type="button" class="btn btn-info"><b>${checkName}</b></button>
	<button type="button" class="btn btn-info btn-xs">total->${esBean.total}</button>
	<button type="button" class="btn btn-info btn-xs">maxScore->${esBean.maxScore}</button>
	<button type="button" class="btn btn-danger btn-xs callback">反馈</button>
	<table class="table" style="margin-bottom: 50px;">
		<thead>
			<tr>
				<th>[ApkName]</th>
				<th>[Score]</th>
				<th>[Package : Install]</th>
			<tr>
		</thead>
		<tbody>
			<c:forEach var="hit" items="${esBean.hits}">
				<tr>
					<td><button type="button" class="btn btn-warning btn-xs">[${hit.name}][${hit.install}]</button></td>
					<td><button type="button" class="btn btn-success btn-xs">${hit.score}</button></td>
					<td><c:forEach var="map" items="${hit.source}">
							<button type="button" class="btn btn-warning btn-xs">[${map.key}]</button>
							<button type="button" class="btn btn-success btn-xs">[${map.value}]</button>&nbsp&nbsp
						</c:forEach></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>