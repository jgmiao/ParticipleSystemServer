<h1><small>Check By Yourself</small></h1>
<div style="position: fixed; margin: 0px; top: 10px; right: 10px;">
	<a href="home"><button type="button" class="btn btn-default">主页</button></a>
	<a href="checkPlatform"><button type="button" class="btn btn-default">检测平台</button></a>
	<a href="autoCheck"><button type="button" class="btn btn-default autoCheck">懒人测试</button></a>
	<a href="callBack"><button type="button" class="btn btn-default">反馈结果</button></a>
</div>
<div class="col-lg-6">
	<div class="input-group selfDiv">
		<input type="text" class="form-control selfInfor" placeholder="请输入待检测字串"> 
		<span class="input-group-btn">
			<button data-url="selfCheckParticiple"
				class="btn btn-default selfSubmitParticiple" type="button">分词</button>
		</span>
		<span class="input-group-btn">
			<button data-url="selfCheckSimilar"
				class="btn btn-default selfSubmitSimilar" type="button">同类检索</button>
		</span>
	</div>
</div>
<div id="showDataDiv"></div>