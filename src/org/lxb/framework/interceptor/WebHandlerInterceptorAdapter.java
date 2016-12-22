/**
 * 
 */
package org.lxb.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxb.framework.annotation.WebLogin;
import org.lxb.framework.entity.system.User;
import org.lxb.framework.util.Const;
import org.lxb.framework.util.Jurisdiction;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * web界面访问过滤
 * 
 * @author linxinbin 2016年7月11日 下午7:39:40
 */
public class WebHandlerInterceptorAdapter extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (handler instanceof HandlerMethod)
		{
			HandlerMethod method = (HandlerMethod) handler;
			WebLogin appAuthentication = method.getMethodAnnotation(WebLogin.class);
			User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER_FRONT);
			if (null == appAuthentication)
			{
				return true;
			}
			else
			{
				// 需要登录，判断是否已经登录
				if (null == user)
				{
					// 登陆过滤
					response.sendRedirect(request.getContextPath() + Const.FRONT_LOGIN);
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		else
		{
			return false;
		}
	}

}