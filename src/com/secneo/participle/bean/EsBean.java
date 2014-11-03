package com.secneo.participle.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.secneo.participle.util.MyUtils;

public class EsBean {

	private int total;
	private double maxScore;
	private List<HitBean> hits;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}

	public List<HitBean> getHits() {
		return hits;
	}

	public void setHits(List<HitBean> hits) {
		this.hits = hits;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void show() {
		Class clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields) {
			String fieldName = f.getName();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
				Object value = getMethod.invoke(this, new Object[]{});
				if(value instanceof List<?>) {
					List<?> tmpList = (List<?>) value;
					for (Object obj : tmpList) {
						System.out.println(fieldName + " : " + obj.toString());
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
	}//show
	
	public static void main(String[] args) {
		EsBean es = new EsBean();
		HitBean hit = new HitBean();
		List<HitBean> hitList = new ArrayList<HitBean>();
		HashMap<String, Object> sourceMap = new HashMap<String, Object>();
		es.setTotal(123);
		es.setMaxScore(456);
		hit.setIndex("index");
		hit.setName("hit");
		hit.setScore(123);
		hit.setType("type");
		sourceMap.put("int", 1);
		sourceMap.put("String", "string");
		sourceMap.put("float", 1.0);
		hit.setSource(sourceMap);
		hitList.add(hit);
		es.setHits(hitList);
		es.show();
	}
}