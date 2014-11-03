package com.secneo.participle.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import com.secneo.participle.util.MyUtils;

public class HitBean {

	private String index;
	private String type;
	private String name;
	private double score;
	private int install;
	private HashMap<String, Object> source;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getInstall() {
		return install;
	}

	public void setInstall(int install) {
		this.install = install;
	}

	public HashMap<String, Object> getSource() {
		return source;
	}

	public void setSource(HashMap<String, Object> source) {
		this.source = source;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String toString() {
		Class clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			String fieldName = f.getName();
			String getMethodName = "get"
					+ fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			try {
				Method getMethod = clazz.getMethod(getMethodName,
						new Class[] {});
				Object value = getMethod.invoke(this, new Object[] {});
				if (value instanceof HashMap) {
					HashMap tmpMap = (HashMap) value;
					Iterator<String> itor = tmpMap.keySet().iterator();
					while (itor.hasNext()) {
						String tmpKey = itor.next();
						System.out.println(fieldName + "[key, value] : [" + tmpKey + ", " + tmpMap.get(tmpKey) + "]");
					}
				} else {
					System.out.println(fieldName + " : " + value.toString());
				}
			} catch (NoSuchMethodException e) {
				MyUtils.log.error(getMethodName + " can`t find in " + clazz.getName() + "!  " + e.getMessage());
			} catch (SecurityException e) {
				MyUtils.log.error("has security problem in " + clazz.getName() + "!  " + e.getMessage());
			} catch (IllegalAccessException e) {
				MyUtils.log.error(e.getMessage());
			} catch (IllegalArgumentException e) {
				MyUtils.log.error(e.getMessage());
			} catch (InvocationTargetException e) {
				MyUtils.log.error(e.getMessage());
			}
		}
		return this.getClass().getName();
	}
}