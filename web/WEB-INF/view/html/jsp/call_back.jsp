<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<h1><small>Call Back List</small></h1>
<div style="position: fixed; margin: 0px; top: 10px; right: 10px;">
	<a href="home"><button type="button" class="btn btn-default">主页</button></a>
	<a href="checkPlatform"><button type="button" class="btn btn-default">检测平台</button></a>
	<a href="selfCheck"><button type="button" class="btn btn-default">自主检测</button></a>
	<a href="autoCheck"><button type="button" class="btn btn-default autoCheck">懒人检测</button></a>
</div>
<table class="table">
	<thead>
		<tr>
			<th>#</th>
			<th>应用名</th>
			<th>索引分词</th>
			<th>严格分词</th>
			<th>反馈信息</th>
			<th></th>
		<tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${callBackList}">
			<tr class="callBackTr">
				<td class="callBackId">${item.id}</td>
				<td class="callBackName">${item.apkName}</td>
				<td class="callBackSubs">${item.subs}</td>
				<td class="callBackBackInfor">${item.backInfor}</td>
				<td><button type="button" class="btn btn-primary btn-xs tryAgain">Try again</button>
					<button type="button" class="btn btn-warning btn-xs remove">Remove</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>