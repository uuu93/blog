package org.lxb.framework.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionResolver implements HandlerExceptionResolver
{

	private final static Logger log = Logger.getLogger(MyExceptionResolver.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex)
	{
		System.out.println("==============异常开始=============");
		ex.printStackTrace();
		log.error(ex.getMessage(), ex);
		System.out.println("==============异常结束=============");
		ModelAndView mv = new ModelAndView("404");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}
