package org.lxb.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 说明：参数封装Map
 * 
 * @version
 */
@SuppressWarnings(value = "rawtypes")
public class PageData extends HashMap implements Map
{

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	public PageData()
	{
		map = new HashMap();
	}

	/**
	 * 简单对象，采用Map对象，不能封装复杂属性数据
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request)
	{
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		while (entries.hasNext())
		{
			String name = "";
			String value = "";
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj)
			{
				value = "";
			}
			else if (valueObj instanceof String[])
			{
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++)
				{
					value += values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			}
			else
			{
				value = valueObj.toString();
			}
			try
			{
				value = URLDecoder.decode(value, ToolString.encoding).trim();
				// value = new String(value.getBytes("ISO-8859-1"),"utf-8");
			}
			catch (IllegalArgumentException e)
			{
			}
			catch (UnsupportedEncodingException e)
			{
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	@Override
	public Object get(Object key)
	{
		Object obj = null;
		if (map.get(key) instanceof Object[])
		{
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		}
		else
		{
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key)
	{
		return (String) get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value)
	{
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key)
	{
		return map.remove(key);
	}

	public void clear()
	{
		map.clear();
	}

	public boolean containsKey(Object key)
	{
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value)
	{
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet()
	{
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet()
	{
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t)
	{
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size()
	{
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values()
	{
		// TODO Auto-generated method stub
		return map.values();
	}

}
