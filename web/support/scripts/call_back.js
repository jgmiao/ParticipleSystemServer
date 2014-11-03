$(function() {
	$(".tryAgain").click(function() {
		var _this = $(this);
		var callBackName = _this.parents(".callBackTr").find(".callBackName").text();
		$.ajax({
			type : "POST",
			url : "callBack/again",
			data : {"callBackName":callBackName},
			async : false,
			success : function(data){
				var obj = jQuery.parseJSON(data);
				if (obj.status == 0) {	//反馈信息分词成功
					alert(obj.result);
					window.location.reload();
				} else{
					alert(obj.message);
				}
			}
		});
		return false;
	});
	
	$(".remove").click(function(){
		var _this = $(this);
		var callBackTr = _this.parents(".callBackTr");
		var callBackName = callBackTr.find(".callBackName").text();
		$.ajax({
			type : "POST",
			data : {"callBackName":callBackName},
			async : false,
			url : "callBack/remove",
			success : function(data){
				var obj = jQuery.parseJSON(data);
				if (obj.status == 1) {	//删除回馈信息成功
					callBackTr.remove();
				} else{
					alert(obj.message);
				}
			}
		});
		return false;
	});
});