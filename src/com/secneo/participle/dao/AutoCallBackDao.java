package com.secneo.participle.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.secneo.participle.support.JDBC;

public class AutoCallBackDao {
	
	public int create(String name){
		int i = 0;
		Connection conn = JDBC.getConnection();
		try {
			String saveSql = "INSERT INTO call_back_auto (name) VALUES ('"
				+ name + "')";
			Statement stat = conn.createStatement();
			i = stat.executeUpdate(saveSql);
			stat.close();
		} catch (SQLException ex) {
			System.out.println("反馈自动检测信息保存出错。");
			System.out.println(ex.getMessage());
		} finally {
			JDBC.closeConnection(conn);
		}
		return i;
	}
}