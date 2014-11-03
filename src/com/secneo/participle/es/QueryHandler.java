package com.secneo.participle.es;

import javax.json.Json;
import javax.json.JsonObject;

public class QueryHandler {
	/**
	 {"query":{"bool":{"must":[{"term":{"nametree.name":"下载"}}]}},"from":0,"size":1000}
	 */
	public static String getJsonQuery(String apkName, int from, int size){
		JsonObject query = Json.createObjectBuilder()
			.add("query", Json.createObjectBuilder()
				.add("bool", Json.createObjectBuilder()
					.add("must", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
							.add("term", Json.createObjectBuilder()
								.add("nametree.name", apkName)
							)
						)
					)
				)
			)
			.add("from", from)
			.add("size", size).build();
		return query.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(QueryHandler.getJsonQuery("愤怒的小鸟", 0, 100));
	}
}