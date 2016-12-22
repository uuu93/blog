package org.lxb.framework.listener;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.lxb.framework.util.ContextUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

/**
 * 初始化数据监听器
 * 
 * @author linxinbin 2016年6月29日 上午9:11:40
 */
public class InitDataListener implements InitializingBean, ServletContextAware
{

	private final static Logger log = Logger.getLogger(InitDataListener.class);

	@Override
	public void setServletContext(ServletContext sc)
	{
		try
		{
			// 初始化加载数据
			ContextUtil.init(sc);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{

	}

}