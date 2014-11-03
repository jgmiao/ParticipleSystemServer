package com.secneo.participle.es;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.secneo.participle.bean.EsBean;
import com.secneo.participle.bean.HitBean;
import com.secneo.participle.support.Config;
import com.secneo.participle.util.MyUtils;
import com.secneo.participle.util.StringUtils;

public class EsCore {
	 
	private static final int BUFFER_SIZE = 512 * 1000;
	
	/**
	 * 跟ES接口通信
	 * @param esQueryPath ES接口的URL
	 * @param esQueryJson 作为数据body 以流的形式传入ES 
	 * @return JsonObject
	 * @throws IOException
	 */
	private JsonObject esQuery(String esQueryPath, String esQueryJson) throws IOException {
		String tmpStr = "";
		URL url = new URL(esQueryPath);
		System.out.println(esQueryPath);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestProperty("Authorization", "Basic " + Base64.encode("secstorm:bnmOsFEsAc7q7JPQ13ihf"));
		httpConnection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(httpConnection.getOutputStream());
		out.write(esQueryJson.toString());
		out.flush();
		out.close();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				httpConnection.getInputStream()));
		StringBuffer strBuffer = new StringBuffer(BUFFER_SIZE);
		while((tmpStr = reader.readLine()) != null) {
			System.out.println(tmpStr);
			strBuffer.append(tmpStr);
		}
		return new JsonParser().parse(strBuffer.toString()).getAsJsonObject(); 
	}

	/**
     *修改分值，排序情况
	 */
	public EsBean getEsBean(final String apkname) throws IOException {
		
		String name = apkname;
		if (StringUtils.isEngilsh(name)) {
			name = name.toLowerCase();
		}
		/**
		 * 获取ES查询返回数据
		 */
		String esQueryPath = Config.i.esStep2Path;
		String esQueryJson = QueryHandler.getJsonQuery(name, 0, 100);
		JsonObject esObj = esQuery(esQueryPath, esQueryJson); //获取JsonObject
		
		EsBean esBean = new EsBean();
		List<HitBean> hitList = new ArrayList<HitBean>();
		try { //捕获UnsupportedOperationException
			JsonObject hitObj = esObj.get("hits").getAsJsonObject();
			esBean.setTotal(hitObj.get("total").getAsInt());
			esBean.setMaxScore(hitObj.get("max_score").getAsDouble());
			JsonArray hitsJson = hitObj.get("hits").getAsJsonArray();
			for (JsonElement jo : hitsJson) {
				JsonObject tmpObj = jo.getAsJsonObject();
				HitBean hitBean = new HitBean();
				hitBean.setIndex(tmpObj.get("_index").getAsString());
				hitBean.setType(tmpObj.get("_type").getAsString());
				hitBean.setName(tmpObj.get("_id").getAsString());
				hitBean.setScore(tmpObj.get("_score").getAsDouble());
				JsonObject jObject = tmpObj.get("_source").getAsJsonObject();
				Set<Entry<String, JsonElement>> entrySet = jObject.entrySet();
				HashMap<String, Object> tmpHash = new HashMap<String, Object>();
				HashMap<String, Object> resHash = new HashMap<String, Object>();
	
				for (Entry<String, JsonElement> entry : entrySet){ //将Entry转入HashMap中
					tmpHash.put(entry.getKey(), entry.getValue());
				}
				Iterator<String> iter = tmpHash.keySet().iterator();
				while (iter.hasNext()) { //找出package:install  合并两个entry为一组
					String tmpPagKey = iter.next(); //以pkg开始的[K, V]
					if (null != tmpPagKey && tmpPagKey.startsWith("pkg")) {
						String tmpInstallKey = "install_" + tmpPagKey; //以install开始的[K, V]
						if (null != tmpHash.get(tmpInstallKey)) {
							resHash.put(tmpHash.get(tmpPagKey).toString(), tmpHash.get(tmpInstallKey));
						} else { //没有对应的install的[K, V]
							MyUtils.log.error("对" + name + "搜索时" + tmpPagKey + "包下没有获取install_pkg_*");
						}
					} else if (tmpPagKey.equals("install_num")){
						hitBean.setInstall(Integer.parseInt(tmpHash.get(tmpPagKey).toString()));
					}
				}//while
				hitBean.setSource(resHash);
				hitList.add(hitBean);
			}//for
		} catch (UnsupportedOperationException e) {
			MyUtils.log.error(name + "Gson格式转化错误" + e.getMessage());
			MyUtils.log.error("JsonObject -->" + esObj.toString());
		}
		esBean.setHits(hitList);
		return esBean;
	}//getEsBean
	
	public static void main(String[] args) {
		EsCore support = new EsCore();
		try {
			support.getEsBean("爱奇艺PPS").show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}