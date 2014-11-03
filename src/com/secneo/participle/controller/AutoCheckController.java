package com.secneo.participle.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secneo.participle.controller.base.ControllerSupport;
import com.secneo.participle.dao.RandomNameDao;
import com.secneo.participle.es.EsCore;
import com.secneo.participle.util.MyUtils;

@Controller("AutoCheckController")
@RequestMapping("/")
public class AutoCheckController extends ControllerSupport{

	@RequestMapping(value = "autoCheck", method = RequestMethod.GET)
	public String autoCheck(HttpServletRequest req, HttpServletResponse res) {
		return render(res, "auto_check");
	}
	
	@RequestMapping(value = "autoCheck", method = RequestMethod.POST)
	public String autoCheckParticiple(HttpServletRequest req, HttpServletResponse res) {
		res.setCharacterEncoding("UTF-8");
		try {
			String checkName = RandomNameDao.getRandomNames(1).get(0);
			EsCore esSupport = new EsCore();
			String reply =  "{\"status\":\"0\",\"message\":\"successful!\"}";
			req.setAttribute("checkName", checkName);
			req.setAttribute("esBean", esSupport.getEsBean(checkName));
			res.getWriter().write(reply);
		} catch (IOException e) {
			MyUtils.log.error("Response回写失败！ " + e.getMessage());
		}
		return render(res, "auto_check_participle");
	}
}