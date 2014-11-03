<h1>
	<small>Participle Test Platform</small>
</h1>
<div style="position: fixed; margin: 0px; top: 10px; right: 10px;">
	<a href="home"><button type="button" class="btn btn-default">主页</button></a>
	<a href="checkPlatform"><button type="button" class="btn btn-default">抽取一组</button></a>
	<a href="selfCheck"><button type="button" class="btn btn-default">自主检测</button></a>
	<a href="autoCheck"><button type="button" class="btn btn-default autoCheck">懒人检测</button></a>
	<a href="callBack"><button type="button" class="btn btn-default">反馈结果</button></a>
</div>
<table class="table">
	<thead>
		<tr>
			<th>#</th>
			<th>待分词</th>
			<th>索引分词</th>
			<th>严格分词</th>
			<th>
				<button type="button" class="btn btn-info btn-xs">全库词</button>
				<button type="button" class="btn btn-success btn-xs">确定词</button>
				<button type="button" class="btn btn-primary btn-xs">[角色]修饰词</button>
				<button type="button" class="btn btn-warning btn-xs">游戏修饰词</button>
				<button type="button" class="btn btn-danger btn-xs">噪声词</button>
			</th>
		<tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${resultIndexMap}" varStatus="var">
			<tr class="callBackTr">
				<td>${var.index}</td>
				<td>${item.key}</td>
				<c:set var="key" value="${item.key}"></c:set>
				<td><c:forEach var="subCell" items="${item.value}">
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
					</c:forEach>
				</td>
				<td><c:forEach var="subCell" items="${resultStrictMap[key]}">
						<button type="button" class="btn btn-warning btn-xs">${subCell.name}[${subCell.countWhole}]</button>
					</c:forEach>
					<button type="button" class="btn btn-default btn-xs callBack">反馈</button></td>
				<td>
					<div data-url="callBack" class="callBackDiv"
						style="height: 35px; width: 350px; visibility: hidden;">
						<input type="hidden" class="callBackName" value="${key}" />
						<div style="float: left; width: 230px;">
							<input type="text" class="form-control callBackInfor"
								value="分词不合理">
						</div>
						<div style="float: left; margin-left: 5px;">
							<button class="submit">提交</button>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>