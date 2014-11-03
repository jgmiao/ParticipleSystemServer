package com.secneo.participle.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secneo.participle.dao.AutoCallBackDao;
import com.secneo.participle.dao.CallBackDao;

public class CallBack {
	private String id;
	private String apkName;
	private String subs;
	private String backInfor;

	public CallBack(){
	}
	
	public CallBack(String name) {
		this.apkName = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public String getSubs() {
		return subs;
	}

	public void setSubs(String subs) {
		this.subs = subs;
	}

	public String getBackInfor() {
		return backInfor;
	}

	public void setBackInfor(String backInfor) {
		this.backInfor = backInfor;
	}

	public void save() {
		CallBackDao dao = new CallBackDao();
		int i = 0;
		i = dao.create(this);
		if (i <= 0) {
			System.out.println(this.apkName + "存入call_back失败");
		} else {
			System.out.println(this.apkName + "存入call_back成功");
		}
	}

	public List<CallBack> list() {
		CallBackDao dao = new CallBackDao();
		List<CallBack> list = new ArrayList<CallBack>();
		try {
			list = dao.list();
		} catch (SQLException e) {
			System.out.println("查询CallBack出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 	删除反馈信息
	 */
	public int remove() {
		int i = 0;
		CallBackDao dao = new CallBackDao();
		i = dao.delete(this);
		return i;
	}
	
	/**
	 *	保存自动检测的反馈信息 
	 */
	public int saveAuto(String name) {
		int i = 0;
		AutoCallBackDao dao = new AutoCallBackDao();
		i = dao.create(name);
		return i;
	}
}