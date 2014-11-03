package com.secneo.participle.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secneo.participle.controller.base.ControllerSupport;
import com.secneo.participle.dict.ParticipleDict;
import com.secneo.participle.util.MyUtils;

@Controller("DictCheckController")
@RequestMapping("/")
public class DictCheckController extends ControllerSupport {

	private static final String DEFINITE = "确定词";
	private static final String MODIFY = "修饰词";
	private static final String NOISE = "噪声词";

	@RequestMapping(value = "dictCheck", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse res) {
		res.setCharacterEncoding("GBK");
		List<String> dictTypes = new ArrayList<String>();
		dictTypes.add(DEFINITE);
		dictTypes.add(MODIFY);
		dictTypes.add(NOISE);
		req.setAttribute("dictTypes", dictTypes);
		return render(res, "dict_check");
	}

	@RequestMapping(value = "dictCheck", method = RequestMethod.POST)
	public String show(HttpServletRequest req, HttpServletResponse res) {
		res.setCharacterEncoding("UTF-8");
		String reply = "{\"status\":\"0\",\"message\":\"successful!\"}";
		try {
			String dictType = req.getParameter("dictType").intern();
			System.out.println("type: " + dictType);
			
			if (dictType == DEFINITE) {
				req.setAttribute("dict", ParticipleDict.DEFINITE.dict);
			} else if (dictType == MODIFY) {
				req.setAttribute("dict", ParticipleDict.MODIFY.dict);
			} else if (dictType == NOISE) {
				req.setAttribute("dict", ParticipleDict.NOISE.dict);
			} else {
				reply = "{\"status\":\"1\",\"message\":\"未获取或不匹配词典类型！\"}";
			}
			
			res.getWriter().write(reply);
//			res.setHeader("Cache-Control", "max-age=1800, public");
		} catch (IOException e) {
			MyUtils.log.error("Response回写失败！ " + e.getMessage());
		}
		return render(res, "dict_check_list");
	}
}