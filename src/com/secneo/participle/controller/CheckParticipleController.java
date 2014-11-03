package com.secneo.participle.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secneo.participle.controller.base.ControllerSupport;
import com.secneo.participle.dao.RandomNameDao;
import com.secneo.participle.domain.Cell;
import com.secneo.participle.split.SplitWordIndexTool;
import com.secneo.participle.split.SplitWordStrictTool2;

@Controller("CheckParticipleController")
@RequestMapping("/")
public class CheckParticipleController extends ControllerSupport {
	
	private static final int APK_NUM = 100;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse res) {
		return render(res, "home");
	}
	
	@RequestMapping(value = "checkPlatform", method = RequestMethod.GET)
	public String checkList(HttpServletRequest req, HttpServletResponse res) {
		ArrayList<String> list = RandomNameDao.getRandomNames(APK_NUM);
		
		SplitWordIndexTool indexTool = new SplitWordIndexTool();
		SplitWordStrictTool2 strictTool = new SplitWordStrictTool2();
		
		HashMap<String, List<Cell>> resultIndexData = new HashMap<String, List<Cell>>();
		HashMap<String, List<Cell>> resultStrictData = new HashMap<String, List<Cell>>();
		
		for(String s : list) {
			System.out.println("apkName: " + s);
			resultIndexData.put(s, indexTool.splitWordPort(s));
			List<Cell> strictList = new ArrayList<Cell>();
			strictList.add(strictTool.getFirstWordPort(s));
			resultStrictData.put(s, strictList);
		}
		//关联到页面上的数据展示 以及 CallBack中从Session中取回数据
		req.getSession().setAttribute("resultIndexMap", resultIndexData); 
		req.getSession().setAttribute("resultStrictMap", resultStrictData);
		return render(res, "check_platform");
	}
}