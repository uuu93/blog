/**
 * 
 */
package org.lxb.framework.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.lxb.framework.service.system.param.IParamService;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author linxinbin 2016年6月24日 下午4:36:11
 */
public class ContextUtil
{

	private static ContextUtil cu = null;

	private static final Logger log = Logger.getLogger(ContextUtil.class);
	private static IParamService paramService;
	// 参数项
	private static Map<String, PageData> params = new HashMap<String, PageData>();

	private ContextUtil()
	{
	}

	public static ContextUtil getInstance()
	{
		if (null == cu)
		{
			cu = new ContextUtil();
		}
		return cu;
	}

	/**
	 * 初始化加载数据
	 */
	public static void init(ServletContext sc)
	{
		paramService = (IParamService) WebApplicationContextUtils.getWebApplicationContext(sc).getBean("paramService");
		// 加载参数项
		try
		{
			List<PageData> paramlist = paramService.listAll(new PageData());
			for (PageData param : paramlist)
			{
				params.put(param.getString("NAME"), param);
			}
			log.info("加载参数项完成。大小：" + params.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}

	}

	/**
	 * 根据NAME移除参数项
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, PageData> removeParam(String key)
	{
		params.remove(key);
		return params;
	}

	/**
	 * 新增/修改参数项数据
	 */
	public static Map<String, PageData> addParam(String key, PageData pd)
	{
		params.put(key, pd);
		return params;
	}

	/**
	 * 根据NAME获取参数项
	 */
	public static PageData getParam(String key)
	{
		return params.get(key);
	}

	/**
	 * 重新加载参数项
	 */
	public static void initParam(List<PageData> paramlist)
	{
		try
		{
			params.clear();
			for (PageData param : paramlist)
			{
				params.put(param.getString("NAME"), param);
			}
			log.info("加载参数项完成。大小：" + params.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	public static String getBasePath(HttpServletRequest request)
	{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		// + "/";
		return basePath;
	}

	public static String getNoPortBasePath(HttpServletRequest request)
	{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()/* + ":" + request.getServerPort()*/ + path;
		// + "/";
		return basePath;
	}

	public static String getNoPortFullPath(HttpServletRequest request)
	{
		String basePath = request.getScheme() + "://" + request.getServerName()/* + ":" + request.getServerPort()*/ +  request.getContextPath()      //项目名称  
        + request.getServletPath()      //请求页面或其他地址  
    + "?" + (request.getQueryString()); //参数;
		// + "/";
		return basePath;
	}

}