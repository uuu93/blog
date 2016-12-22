package org.lxb.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Api接口工具类
 * 
 * @author linxinbin 2016年7月4日 下午5:08:24
 */
public class ApiUtil
{
	private ApiUtil()
	{
	}

	private static ObjectMapper om = new ObjectMapper();

	/**
	 * null值处理
	 * 
	 * @param pd
	 * @param map
	 * @return
	 */
	public static Object returnObject(PageData pd, Map<?, ?> map)
	{
		if (pd.containsKey("callback"))
		{
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}
		else
		{
			return map;
		}
	}

	/**
	 * 返回带字符串null值处理的JsonConfig
	 */
	public static JsonConfig getJsonConfig()
	{
		JsonConfig jc = new JsonConfig();
		jc.registerJsonValueProcessor(String.class, new MyStringJsonValueProcessor());
		jc.registerJsonValueProcessor(JSONNull.class, new MyStringJsonValueProcessor());
		return jc;
	}

	/**
	 * 返回带字符串null值处理和过滤属性的JsonConfig
	 */
	public static JsonConfig getJsonConfig(String[] excludes)
	{
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(excludes);
		jc.registerJsonValueProcessor(String.class, new MyStringJsonValueProcessor());
		jc.registerJsonValueProcessor(JSONNull.class, new MyStringJsonValueProcessor());
		return jc;
	}

	/**
	 * 将json数组字符串转为List<T> 的对象
	 */
	/*
	 * public static <T> List<T> fromJsonList(String json, Class<T> clazz) throws IOException { return
	 * om.readValue(json, om.getTypeFactory().constructCollectionType(List.class, clazz)); }
	 *//**
	 * 将一个输入流转为List<T>的对象
	 */
	/*
	 * public static <T> List<T> fromJsonList(InputStream json, Class<T> clazz) throws IOException {
	 * return om.readValue(json, om.getTypeFactory().constructCollectionType(List.class, clazz)); }
	 */

	/**
	 * 将一个输入流转为Class<T>的对象
	 */
	public static <T> T fromJsonClass(InputStream json, Class<T> clazz) throws IOException
	{
		return om.readValue(json, clazz);
	}

	/**
	 * 将json对象字符串转为Class<T>的对象
	 * 
	 */
	public static <T> T fromJsonClass(String json, Class<T> clazz) throws IOException
	{
		return om.readValue(json, clazz);
	}

	/**
	 * 将json对象解析成Map对象(PageData)
	 */
	@SuppressWarnings("rawtypes")
	public static/* HashMap<String, String> */PageData toHashMap(JSONObject json)
	{
		// HashMap<String, String> data = new HashMap<String, String>();
		PageData data = new PageData();
		Iterator it = json.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext())
		{
			String key = String.valueOf(it.next());
			Object value = json.get(key);
			data.put(key, value);
		}
		return data;
	}

	/**
	 * 将json数组解析成List<Map>对象(PageData)
	 */
	public static/* HashMap<String, String> */List<PageData> toListHashMap(JSONArray jsonarr)
	{
		// HashMap<String, String> data = new HashMap<String, String>();
		List<PageData> datas = new ArrayList<PageData>();
		for (int i = 0; i < jsonarr.size(); i++)
		{
			JSONObject json = jsonarr.getJSONObject(i);
			PageData pd = toHashMap(json);
			datas.add(pd);
		}
		return datas;
	}
}

/**
 * String null 转 ""
 * 
 * @author linxinbin 2016年7月4日 下午5:33:24
 */
class MyStringJsonValueProcessor implements JsonValueProcessor
{

	@Override
	public Object processArrayValue(Object key, JsonConfig jsonConfig)
	{
		return key;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig)
	{
		// System.out.println(key+"===="+value);
		if (value == null || value == JSONNull.getInstance())
		{
			return "";
		}
		return value;
	}
}