package com.secneo.participle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.secneo.participle.support.JDBC;
import com.secneo.participle.util.WriteFile;

public class InitAppName {
	
	private static String PATH;
	
	public static void getAppNames(){
		System.out.println("getAppNames beging!");
		int flag = 0;
		Connection conn = JDBC.getConnection();
		ResultSet res = null;
		PreparedStatement stmt = null;
		StringBuffer appNames = new StringBuffer();
		String getAppNamesSql = "SELECT apk_name FROM installed_apks WHERE apk_name IS NOT NULL AND apk_name != '' ORDER BY apk_name";
		System.out.println("sql: " + getAppNamesSql);
		try {
			stmt = conn.prepareStatement(getAppNamesSql);
			res = stmt.executeQuery();
			while (res.next()) {
				flag ++;
				String name = res.getString("apk_name").trim();
				while (name.startsWith(" ")) {	//trim只能去半角空格 此去掉全角空格
					name = name.substring(1, name.length()).trim();
				}
				while (name.endsWith(" ")) {
					name = name.substring(0, name.length() - 1).trim();
				}
				appNames.append(name).append("\r\n");
				if (flag > 10000) { // 缓存10000个写一次
					WriteFile.write(appNames.toString(), PATH);
					flag = 0;
					System.out.println("执行一次。。。");
					appNames.delete(0, appNames.length()); //清空
				}
			}
			if (flag != 0) {
				WriteFile.write(appNames.toString(), PATH);
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
	}
	
	public static void main(String[] args) {
		PATH = args[0];
		getAppNames();
		System.out.println("初始化apk_name完成！");
	}
}