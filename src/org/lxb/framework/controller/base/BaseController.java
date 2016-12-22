package org.lxb.framework.controller.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lxb.framework.entity.Page;
import org.lxb.framework.util.Const;
import org.lxb.framework.util.ContextUtil;
import org.lxb.framework.util.Logger;
import org.lxb.framework.util.PageData;
import org.lxb.framework.util.UuidUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public class BaseController implements Serializable
{

	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(HttpServletRequest req)
	{
		return req.getParameter("p");
	}

	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public PageData getPageData()
	{
		return new PageData(this.getRequest());
	}

	/**
	 * 得到ModelAndView
	 * 
	 * @return
	 */
	public ModelAndView getModelAndView()
	{
		ModelAndView mv = new ModelAndView();
		HttpServletRequest request = this.getRequest();
		// 配合nginx使用，获取静态图片资源不取上下文，直接用/res/
		String resBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		mv.addObject("res_path", resBasePath + Const.RES_ACCESS);// 静态资源访问路径
		// mv.addObject("res_access", Const.RES_ACCESS);// 静态资源访问路径
		mv.addObject("CU", ContextUtil.getInstance());// ContextUtil实例访问
		return mv;
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到当前session对象
	 * 
	 * @return
	 */
	public HttpSession getSession()
	{
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getSession();
	}

	public void setSessionAttribute(String key, Object value)
	{
		HttpSession session = getSession();
		if (session != null)
		{
			session.setAttribute(key, value);
		}
	}

	public Object getSessionAttribute(String key)
	{
		HttpSession session = getSession();
		return session == null ? null : session.getAttribute(key);
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID()
	{
		return UuidUtil.get32UUID();
	}

	/**
	 * 得到分页列表的信息
	 * 
	 * @return
	 */
	public Page getPage()
	{
		return new Page();
	}

	public static void logBefore(Logger logger, String interfaceName)
	{
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger)
	{
		logger.info("end");
		logger.info("");
	}

	/**
	 * 获取前台传递过来的json字符串 wangyue 2016年8月12日
	 */
	@SuppressWarnings("rawtypes")
	public List<PageData> getPageDataList(String preName)
	{
		List<PageData> list = new ArrayList<PageData>();
		Map<String, String[]> properties = getRequest().getParameterMap();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry entry;
		String value = "";
		Map<String, PageData> map = new HashMap<String, PageData>();
		while (entries.hasNext())
		{
			entry = (Entry) entries.next();
			String key = (String) entry.getKey();
			if (key.startsWith(preName))
			{
				String[] arrStrings = key.split("\\]");
				if (arrStrings.length >= 2)
				{
					String keyString = arrStrings[0] + "]";
					PageData pageData = map.get(keyString);
					if (pageData == null)
					{
						pageData = new PageData();
						map.put(keyString, pageData);
					}
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
							value = values[i] + ",";
						}
						value = value.substring(0, value.length() - 1);
					}
					else
					{
						value = valueObj.toString();
					}
					pageData.put(arrStrings[1].substring(1, arrStrings[1].length()), value);
				}
			}
		}
		for (Map.Entry e : map.entrySet())
		{
			list.add((PageData) e.getValue());
		}
		return list;
	}

}
