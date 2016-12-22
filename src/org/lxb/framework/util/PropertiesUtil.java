package org.lxb.framework.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil
{

	/**
	 * 读取配置文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties getPprVue(String fileName)
	{
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
		Properties p = new Properties();
		BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
		try
		{
			p.load(bf);
			inputStream.close();
		}
		catch (IOException e)
		{
			// 读取配置文件出错
			e.printStackTrace();
		}
		return p;
	}

	public static String getValue(String fileName, String key)
	{
		Properties props = PropertiesUtil.getPprVue(fileName);
		try
		{
			String value = props.getProperty(key);
			return value;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改properties文件
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void setValue(String fileName, String key, String value)
	{
		Properties prop = PropertiesUtil.getPprVue(fileName);
		try
		{
			String filePath = PropertiesUtil.class.getClassLoader().getResource(fileName).getPath();
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(key, value);
			prop.store(fos, "");
			fos.close();
		}
		catch (IOException e)
		{
			System.err.println("Visit " + fileName + " for updating " + value + " value error");
		}
	}

	/**
	 * 修改properties文件
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void setValues(String fileName, Map<String, String> kvMap)
	{
		Properties prop = PropertiesUtil.getPprVue(fileName);
		try
		{
			String filePath = PropertiesUtil.class.getClassLoader().getResource(fileName).getPath();
			OutputStream fos = new FileOutputStream(filePath);
			for (String key : kvMap.keySet())
			{
				String value = kvMap.get(key);
				prop.setProperty(key, value);
			}
			prop.store(fos, "");
			fos.close();
		}
		catch (IOException e)
		{
			System.err.println("Visit " + fileName + " for updating error");
		}
	}

	public static void main(String[] args)
	{
		// System.out.println(PropertiesConfig.readData("com/xiewanzhi/property/config.properties",
		// "port"));
		// PropertiesConfig.writeData("com/xiewanzhi/property/config.properties", "port", "12345");
		System.out.println(PropertiesUtil.getValue("charges.properties", "10001"));
		System.out.println(PropertiesUtil.getValue("dbconfig.properties", "username"));
		// Map<String,String> map=new HashMap<String, String>();
		// map.put("remoteDB", "no");
		// map.put("xx", "no");
		// PropertiesUtil.setValues("dbfh.properties", map);
	}

}
