package com.secneo.participle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.secneo.participle.support.JDBC;
import com.secneo.participle.util.StringUtils;

public class RandomNameDao {
	private final static int ALL_APKNAME_NUM = 7265;

	private static String getAppNameById(String id) {
		String apkName = "";
		Connection conn = JDBC.getConnection();
		ResultSet res = null;
		PreparedStatement stmt = null;
		String getAppNamesSql = "SELECT apk_name FROM apk_info WHERE id = ? LIMIT 1";

		try {
			stmt = conn.prepareStatement(getAppNamesSql);
			stmt.setString(1, id);
			res = stmt.executeQuery();
			while (res.next()) {
				apkName = res.getString("apk_name").trim();
				while (apkName.startsWith(" ")) { // trim只能去半角空格 此去掉全角空格
					apkName = apkName.substring(1, apkName.length()).trim();
				}
				while (apkName.endsWith(" ")) {
					apkName = apkName.substring(0, apkName.length() - 1).trim();
				}
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
		return apkName;
	}

	public static ArrayList<String> getRandomNames(final int apkNum) {
		int i = 0;
		Random rand = new Random();
		ArrayList<String> list = new ArrayList<String>();
		while (i < apkNum) {
			String id = String.valueOf(rand.nextInt(ALL_APKNAME_NUM));
			String name = getAppNameById(id);
			if (!StringUtils.isEmpty(name)) {
				list.add(name);
				i++;
			}
		}
		return list;
	}
}