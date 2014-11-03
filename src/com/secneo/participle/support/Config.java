package com.secneo.participle.support;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Config {
	
	public static final Config i = new Config();

	private Config() {
		PropertiesConfiguration datasource = null;
		PropertiesConfiguration apkname = null;
		try {
			datasource = new PropertiesConfiguration("datasource.conf");
			apkname = new PropertiesConfiguration("apkname.conf");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			System.exit(1);
		}
		this.dictFileDir = apkname.getString("dict_file_dir");
		this.esStep1Path = datasource.getString("es_step1_path");
		this.esStep2Path = datasource.getString("es_step2_path");
		this.host = datasource.getString("host");
		this.port = Integer.parseInt(datasource.getString("port"));
		this.driver = datasource.getString("driver");
		this.url_94 = datasource.getString("url_94");
		this.user_94 = datasource.getString("user_94");
		this.password_94 = datasource.getString("password_94");
	}
	
	/** es一阶段的访问路径*/
	public final String esStep1Path;
	
	/** es二阶段的访问路径*/
	public final String esStep2Path;
	
	/** 词库存放路径 */
	public final String dictFileDir;
	
	/** 连接主机名 */
	public final String host;

	/** 连接端口 */
	public final int port;

	/** 数据库连接驱动 */
	public final String driver;

	/** 连接url */
	public final String url_94;

	/** 连接用户 */
	public final String user_94;

	/** 连接密码 */
	public final String password_94;
}