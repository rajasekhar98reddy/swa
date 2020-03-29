package com.so.beans;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	public static Cache instance;
	private Map<String,Object> dataMap;
	public Cache() {
		dataMap=new HashMap<String, Object>();
	}
	public static Cache getInstance() {
		if(instance==null) {
			instance=new Cache();
		}
		return instance;
	}
	public void put(String key,Object value) {
		dataMap.put(key, value);
	}
	public Object get(String key) {
		return dataMap.get(key);
	}
	public boolean containsKey(String key) {
		return dataMap.containsKey(key);
	}
	public void clear() {
		dataMap.clear();
	}
}
