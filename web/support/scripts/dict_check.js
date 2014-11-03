$(function() {
	$(".dictType").live('click', function() {
		var url = "dictCheck";
		var dictType = $(this).val();
		$.ajax({
			type : "POST",
			data : {
				"dictType" : dictType
			},
			url : url,
			async : false,
			success : function(data) {
				if (data.status == 1) {
					alert(data.message);
				}
				if (null != data) {
					$("#showDataDiv").html(data);
					return false;
				}
			}
		});
	});
});