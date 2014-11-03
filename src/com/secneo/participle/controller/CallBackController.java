package com.secneo.participle.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secneo.participle.bean.CallBack;
import com.secneo.participle.controller.base.ControllerSupport;
import com.secneo.participle.domain.Cell;
import com.secneo.participle.split.SplitWordIndexTool;
import com.secneo.participle.util.MyUtils;

@Controller("CallBackController")
@RequestMapping("/callBack")
public class CallBackController extends ControllerSupport {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public void callBack(HttpServletRequest req, HttpServletResponse res) {
		res.setCharacterEncoding("UTF-8");
		CallBack callBack = new CallBack();
		HashSet<Cell> subs = null;
		String callBackInfor = req.getParameter("callBackInfor");
		String callBackName = req.getParameter("callBackName");
		String callBackSubSet = "";
		HashMap<String, HashSet<Cell>> resultData = (HashMap<String, HashSet<Cell>>) req
				.getSession().getAttribute("resultIndexMap");
		String reply = "";
		if(null != resultData && resultData.size() > 0) {
			subs = resultData.get(callBackName);
		}
		if (null != subs && subs.size() > 0) {
			callBack.setBackInfor(callBackInfor);
			callBack.setApkName(callBackName);
			for (Cell s : subs) {
				callBackSubSet += s.getName() + " ";
			}
			callBack.setSubs(callBackSubSet);
			callBack.save();
			reply = "{\"status\":\"0\",\"message\":\"保存成功!\"}";
		} else {
			reply = "{\"status\":\"1\",\"message\":\"保存失败! 可能停留时间过长，请刷新。\"}";
		}
		try {
			res.getWriter().write(reply);
		} catch (IOException e) {
			MyUtils.log.error("HttpServletResponse回写失败！ " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String callBackList(HttpServletRequest req, HttpServletResponse res) {
		CallBack callBack = new CallBack();
		List<CallBack> callBacks = callBack.list();
		if(null != callBacks && callBacks.size() > 0) {
			req.setAttribute("callBackList", callBacks);
		}
		return render(res, "call_back");
	}
	
	@RequestMapping(value = "/autoCheck", method = RequestMethod.POST)
	public void callBackAutoCheck(HttpServletRequest req, HttpServletResponse res) {
		String reply = "";
		String cbName = req.getParameter("callBackName");
		CallBack callBack = new CallBack();
		int i = callBack.saveAuto(cbName);
		if (i > 0) {
			reply = "{\"status\":\"0\",\"message\":\"反馈信息成功\"}";
		} else {
			reply = "{\"status\":\"1\",\"message\":\"很抱歉反馈信息失败\"}";
		}
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(reply);
		} catch (IOException e) {
			MyUtils.log.error("HttpServletResponse回写失败！ " + e.getMessage());
		}
	}

	@RequestMapping(value = "/again", method = RequestMethod.POST)
	public void callBackAgain(HttpServletRequest req, HttpServletResponse res) {
		String reply = "";
		String cbName = req.getParameter("callBackName");
		SplitWordIndexTool tool = new SplitWordIndexTool();
		List<Cell> cellList = tool.splitWordPort(cbName);
		if(null != cellList && cellList.size() > 0) {
			String resultNameList = "";
			for(Cell hCell : cellList) {
				resultNameList += hCell.getName() + " ";
			}
			reply = "{\"status\":\"0\",\"message\":\"分词成功！\",\"result\":\""+ resultNameList +"\"}";
		} else {
			reply = "{\"status\":\"1\",\"message\":\"很抱歉分词失败，已收集，稍后会做调整！\"}";
		}
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(reply);
		} catch (IOException e) {
			MyUtils.log.error("HttpServletResponse回写失败！ " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public void remove(HttpServletRequest req, HttpServletResponse res) {
		try {
			String reply = "";
			String callBackName = req.getParameter("callBackName");
			CallBack cb = new CallBack(callBackName);
			int i = cb.remove();
			if (i > 0) {
				reply = "{\"status\":\"1\",\"message\":\"删除成功!\"}";
			} else {
				reply = "{\"status\":\"0\",\"message\":\"删除失败!\"}";
			}
		res.setCharacterEncoding("UTF-8");
			res.getWriter().write(reply);
		} catch (UnsupportedEncodingException e) {
			MyUtils.log.error("UnsupportedEncodingException error in CallBackController->remove " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			MyUtils.log.error("HttpServletResponse回写失败！ " + e.getMessage());
		} 
	}
}