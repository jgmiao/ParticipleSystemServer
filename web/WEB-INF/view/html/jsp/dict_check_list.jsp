<table class="table">
	<thead>
		<tr>
			<th style="width:200px;">name</th>
			<th>count</th>
			<th>countSub</th>
			<th>countWhole</th>
			<th>lMatch</th>
			<th>rMatch</th>
		<tr>
	</thead>
	<tbody>
		<c:forEach var="map" items="${dict}"> <!-- 获取长度为N的 HashMap<Cell> -->
			<c:forEach var="cell" items="${map.value}"> <!-- 获取对应的Cell -->
				<tr>
					<td style="width:200px;"><button type="button" class="btn btn-primary btn-xs">${cell.key}</button></td>
					<td><button type="button" class="btn btn-warning btn-xs">${cell.value.count}</button></td>
					<td><button type="button" class="btn btn-warning btn-xs">${cell.value.countSub}</button></td>
					<td><button type="button" class="btn btn-warning btn-xs">${cell.value.countWhole}</button></td>
					<!-- 遍历左匹配集 -->
					<td>
						<c:forEach var="lMatch" items="${cell.value.lMatch}"> 
							<button type="button" class="btn btn-info btn-xs">${lMatch}</button>
						</c:forEach>
					</td>
					<!-- 遍历右匹配集 -->
					<td>
						<c:forEach var="rMatch" items="${cell.value.rMatch}"> 
							<button type="button" class="btn btn-info btn-xs">${rMatch}</button>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>