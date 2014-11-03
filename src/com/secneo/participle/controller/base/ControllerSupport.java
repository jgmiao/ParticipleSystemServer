package com.secneo.participle.controller.base;

import javax.servlet.http.HttpServletResponse;

import com.secneo.participle.util.StringUtils;

public abstract class ControllerSupport {

	protected String render(HttpServletResponse res, String view) {
		if (!StringUtils.isEmpty(res.getContentType())) {
			String suffix = "";
			if (res.getContentType().contains("text/xml"))
				suffix = ".xml";
			else if (res.getContentType().contains("text/json"))
				suffix = ".json";
			view = view + suffix;
		}
		return view;
	}
}