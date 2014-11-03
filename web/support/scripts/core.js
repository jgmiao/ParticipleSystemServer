(function($) {
	$.put = function(url, data, callback, type) {
		if (data != null && data != "") {
			if (data.trim().indexOf("{") == 0) {
				data = $.extend(data, {
					"_method_" : "PUT"
				});
			} else {
				data += "&_method_=PUT";
			}
		} else {
			data = "_method_=PUT";
		}
		$.post(url, data, callback, type);
	};

	$.del = function(url, callback, type) {
		var data = {
			"_method_" : "DELETE"
		};
		$.post(url, data, callback, type);
	};
	
	$.inflate = function(template, object) {
		var result = "";
		if ($.isArray(object)) {
			for ( var i = 0; i < object.length; i++) {
				object[i].index = i;
				result += template.inflate(object[i]);
			}
		} else {
			result = template.inflate(object);
		}
		return result;
	};
})(jQuery);