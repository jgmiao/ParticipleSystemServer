package com.secneo.participle.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.secneo.participle.dict.ParticipleDict;

public class WebContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ParticipleDict.loadAllDict();
	}
}