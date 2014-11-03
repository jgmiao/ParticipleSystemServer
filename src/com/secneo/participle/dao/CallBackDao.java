package com.secneo.participle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.secneo.participle.bean.CallBack;
import com.secneo.participle.support.JDBC;

public class CallBackDao {
	
	public int create(CallBack c){
		int changeRole = 0;
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = formate.format(new Date());
		Connection conn = JDBC.getConnection();
		try {
			String saveSql = "INSERT INTO call_back (apk_name, subs, back_infor, modify_date) VALUES ('"
				+ c.getApkName() + "', '"
				+ c.getSubs() + "', '"
				+ c.getBackInfor() + "', '"
				+ date + "')";
			Statement stat = conn.createStatement();
			changeRole = stat.executeUpdate(saveSql);
			stat.close();
		} catch (SQLException ex) {
			System.out.println("反馈信息保存出错。");
			System.out.println(ex.getMessage());
		} finally {
			JDBC.closeConnection(conn);
		}
		return changeRole;
	}

	public List<CallBack> list() throws SQLException{
		Connection conn = JDBC.getConnection();
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<CallBack> list = new ArrayList<CallBack>();
		String sql = "SELECT * FROM call_back";
		try {
			stmt = conn.prepareStatement(sql);
			res = stmt.executeQuery();
			while(res.next()) {
				CallBack callBack = new CallBack();
				callBack.setId(res.getString("id"));
				callBack.setApkName(res.getString("apk_name"));
				callBack.setSubs(res.getString("subs"));
				callBack.setBackInfor(res.getString("back_infor"));
				list.add(callBack);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (res != null) {
				try {
					res.close();
					res = null;
				} catch (SQLException sqlEx) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException sqlEx) {
				}
			}
			JDBC.closeConnection(conn);
		}
		return list;
	}

	public int delete(CallBack c) {
		int changeRole = 0;
		Connection conn = JDBC.getConnection();
		String delSql = "DELETE FROM call_back WHERE apk_name = '" + c.getApkName() + "'"; 
		try {
			Statement stat = conn.createStatement();
			changeRole = stat.executeUpdate(delSql);
			stat.close();
		} catch (SQLException e) {
			System.out.println("删除CallBack失败。");
			e.printStackTrace();
		} finally {
			JDBC.closeConnection(conn);
		}
		return changeRole;
	}
}