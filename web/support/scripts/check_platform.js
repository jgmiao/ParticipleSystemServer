$(function() {
	$(".callBack").click(function() {
		$(".callBackDiv").css("visibility","hidden");
		var td = $(this).parent();
		td.next().children(".callBackDiv").css("visibility", "visible");
	});
	
	$(".submit").click(function() {
		var _this = $(this);
		var callBackDiv = _this.parents(".callBackDiv");
		var callBackName = callBackDiv.find(".callBackName").val();
		var callBackInfor = callBackDiv.find(".callBackInfor").val();
		var url = callBackDiv.attr("data-url");
		
		if($.trim(callBackInfor).length <= 0){
			alert("您输入的信息为空。");
			return false;
		}
		$.ajax({
			type : "POST",
			url : url,
			data : {"callBackInfor":callBackInfor, "callBackName":callBackName},
			async : false,
			success : function(data){
				var obj = jQuery.parseJSON(data);
				if (obj.status == 1) {
					alert(obj.message);
				} else {
					_this.parents(".callBackTr").remove();
				}
			}
		});
		return false;
	});
});