<table class="table">
	<thead>
		<tr>
			<th>#</th>
			<th>待分词</th>
			<th>索引分词</th>
			<th>严格分词</th>
		<tr>
	</thead>
	<tbody>
		<tr>
			<td><button type="button" class="btn btn-warning btn-xs">#</button></td>
			<td><button type="button" class="btn btn-warning btn-xs">${checkName}</button></td>
			<td><c:forEach var="subCell" items="${indexCellSet}">
					<c:choose>
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
					</c:choose>
				</c:forEach></td>
			<td><c:forEach var="subCell" items="${strictCellSet}">
					<button type="button" class="btn btn-warning btn-xs">${subCell.name}[${subCell.countWhole}]</button>	
				</c:forEach>
		</tr>
	</tbody>
</table>