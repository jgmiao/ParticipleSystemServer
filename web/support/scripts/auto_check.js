$(function() {
	if ($(".startAutoCheck").is(":visible") == true) {
		$(".stopAutoCheck").hide();
	}
	var intervalId = 0;
	var intervalCount = 0;
	
	function getEsResponse() {
		var url = "autoCheck";
		$.ajax({
			type : "POST",
			url : url,
			async : false,
			success : function(data) {
				if (null != data) {
					if (++intervalCount % 10 != 0) {
						$("#showDataDiv").append(data);
						$("html, body").animate({
							scrollTop: $("html").height()
						},1500);
					} else {
						$("#showDataDiv").html(data);
					}
					return false;
				}
			}
		});
	}
	
	function clearIntervel() {
		$(".startAutoCheck").hide();
		$(".stopAutoCheck").show();
		clearInterval(intervalId);
	}
	
	$(".startAutoCheck, .startAutoCheck5s").click(function() { //defule
		clearIntervel();
		intervalId = setInterval(getEsResponse, 5000);
	});
	
	$(".startAutoCheck3s").click(function() { //3s
		clearIntervel();
		intervalId = setInterval(getEsResponse, 3000);
	});
	
	$(".startAutoCheck10s").click(function() { //10s
		clearIntervel();
		intervalId = setInterval(getEsResponse, 10000);
	});
	
	$(".stopAutoCheck").click(function() {
		$(".stopAutoCheck").hide();
		$(".startAutoCheck").show();
		clearInterval(intervalId);
		alert("Auto check has stopped!");
		return false;
	});
	
	$(".callback").live('click',function() {
		var url = "callBack/autoCheck";
		var appendDiv = $(this).parents("#autoAppendDiv"); 
		var callBackName = appendDiv.find("#callBackName").attr("value");
		intervalCount--;
		$.ajax({
			type : "POST",
			data : {"callBackName":callBackName},
			url : url,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.status == 0) {
					appendDiv.remove();
					return false;
				}
				alert(obj.message);
			}
		});
	});
});