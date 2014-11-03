<table class="table">
	<thead>
		<tr>
			<th>Apk Name</th>
			<th>Install</th>
			<th>Count</th>
			<th>Count Sub</th>
		<tr>
	</thead>
	<tbody>
		<c:forEach var="subCell" items="${similarCellSet}">
			<tr>
				<td><c:choose>
						<c:when test="${subCell.state == 0}">
							<!-- 全库中的词 -->
							<button type="button" class="btn btn-info btn-xs">${subCell.name}</button>
						</c:when>
						<c:when test="${subCell.state == 1}">
							<!-- 确定词 -->
							<button type="button" class="btn btn-success btn-xs">${subCell.name}</button>
						</c:when>
						<c:when test="${subCell.state == 2 || subCell.state == 4}">
							<!-- 修饰词|角色修饰词 -->
							<button type="button" class="btn btn-primary btn-xs">${subCell.name}</button>
						</c:when>
						<c:when test="${subCell.state == 3}">
							<!-- 游戏修饰词 -->
							<button type="button" class="btn btn-warning btn-xs">${subCell.name}</button>
						</c:when>
						<c:when test="${subCell.state == 5}">
							<!-- 噪声词 -->
							<button type="button" class="btn btn-danger btn-xs">${subCell.name}</button>
						</c:when>
					</c:choose></td>
				<td>${subCell.countWhole}</td>
				<td>${subCell.count}</td>
				<td>${subCell.countSub}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>