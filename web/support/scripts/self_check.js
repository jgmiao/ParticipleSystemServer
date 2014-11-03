$(function() {
	
	$(".selfSubmitParticiple, .selfSubmitSimilar").click(function() {
		var _this = $(this);
		var url = _this.attr("data-url");
		var selfDiv = _this.parents(".selfDiv");
		var selfInfor = selfDiv.find(".selfInfor").val();
		
		if($.trim(selfInfor).length <= 0){
			alert("您输入的信息为空。");
			return false;
		}
		$.ajax({
			type : "POST",
			url : url,
			data : {"selfInfor":selfInfor},
			async : false,
			success : function(data) {
				if (null != data) {
					$("#showDataDiv").html(data);
					return false;
				}
			}
		});
	});
});