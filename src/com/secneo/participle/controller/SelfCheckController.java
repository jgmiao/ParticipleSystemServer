package com.secneo.participle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secneo.participle.controller.base.ControllerSupport;
import com.secneo.participle.domain.Cell;
import com.secneo.participle.split.SplitWordIndexTool;
import com.secneo.participle.split.SplitWordStrictTool2;
import com.secneo.participle.util.MyUtils;

@Controller("SelfCheckController")
@RequestMapping("/")
public class SelfCheckController extends ControllerSupport {

	@RequestMapping(value = "selfCheck", method = RequestMethod.GET)
	public String selfCheck(HttpServletRequest req, HttpServletResponse res) {
		return render(res, "self_check");
	}
	
	@RequestMapping(value = "selfCheckParticiple", method = RequestMethod.POST)
	public String selfCheckParticiple(HttpServletRequest req, HttpServletResponse res) {
		String checkName = req.getParameter("selfInfor");
		SplitWordIndexTool indexTool = new SplitWordIndexTool();
		SplitWordStrictTool2 strictTool = new SplitWordStrictTool2();
		
		List<Cell> indexCellList = indexTool.splitWordPort(checkName);
		List<Cell> strictCellList = new ArrayList<Cell>();
		strictCellList.add(strictTool.getFirstWordPort(checkName));		
		
		if(null != indexCellList && indexCellList.size() > 0) {
			req.setAttribute("checkName", checkName);
			req.setAttribute("indexCellSet", indexCellList);
			req.setAttribute("strictCellSet", strictCellList);
		} else {
			MyUtils.log.error("SelfCheckController->selfCheckParticiple对" + checkName + "分词接口出错！");
		}
		return render(res, "self_check_participle");
	}
	
	@RequestMapping(value = "selfCheckSimilar", method = RequestMethod.POST)
	public String selfCheckStrict(HttpServletRequest req, HttpServletResponse res) {
		String checkName = req.getParameter("selfInfor");
		SplitWordStrictTool2 tool = new SplitWordStrictTool2();
		req.setAttribute("similarCellSet", tool.getSimilarWordPort(checkName));
		return render(res, "self_check_similar");
	}
}