/**
 * 
 */
package org.lxb.framework.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.lxb.business.common.ParamConst;
import org.lxb.framework.annotation.ApiAuthentication;
import org.lxb.framework.util.AppConstants;
import org.lxb.framework.util.ContextUtil;
import org.lxb.framework.util.DateUtil;
import org.lxb.framework.util.MD5;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * APP接口访问过滤
 * 
 * @author linxinbin 2016年6月28日 下午2:59:26
 */
public class ApiHandlerInterceptor extends HandlerInterceptorAdapter
{
	private final static Logger log = Logger.getLogger(ApiHandlerInterceptor.class);

	private static Map<String, String> usertime = new HashMap<String, String>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (handler instanceof HandlerMethod)
		{
			HandlerMethod method = (HandlerMethod) handler;
			ApiAuthentication appAuthentication = method.getMethodAnnotation(ApiAuthentication.class);
			if (null == appAuthentication)
			{
				return true;
			}
			else
			{
				// 需要进行鉴权，开始校验
				if (apiAuthorization(request))
				{
					return true;
				}
				else
				{
					response.setCharacterEncoding("utf-8");
					response.setContentType("application/json");
					response.getWriter()
							.print(
									"{\"" + AppConstants.code + "\":\"" + AppConstants.fail + "\",\"" + AppConstants.msg
											+ "\":\"操作过于频繁~~\"}");
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 校验api请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean apiAuthorization(HttpServletRequest request)
	{
		String token = request.getHeader("token");// token（使用用户ID）
		String timestamp = request.getHeader("timestamp");// 时间戳（客户端统一使用UTC时区，避免客户端与服务器不在同一时区，格式:yyyyMMddHHmmss）
		String secret = request.getHeader("secret");// 由token+空格+服务端密钥（登录成功后服务端会返回），MD5加密再base64编码而成
		StringBuffer url = request.getRequestURL();
		try
		{
			// 判断时间戳是否有效
			String localTimestamp = DateUtil.utcToLocal(timestamp, "yyyyMMddHHmmss", "yyyyMMddHHmmss");
			String localTime = DateUtil.format("yyyyMMddHHmmss", new Date());

			String ptime = usertime.get(token);
			if (null != ptime)
			{
				if ((localTime + url).equals(ptime))
				// if(DateUtil.getSecond(DateUtil.parse("yyyyMMddHHmmss", ptime),
				// DateUtil.parse("yyyyMMddHHmmss", localTime)) < 1)
				{
					// 同一秒内的重复请求
					System.out
							.println("========================================================================================同一秒内的重复请求");
					return false;
				}
			}
			usertime.put(token, localTime + url);
			// if (localTime.equals(localTimestamp))
			// {
			// // 同一秒内的重复请求
			// return false;
			// }
			// else if (DateUtil.getSecond(DateUtil.parse("yyyyMMddHHmmss", localTime),
			// DateUtil.parse("yyyyMMddHHmmss", localTimestamp)) > 60)
			// {
			// // 客户端发起请求时间与服务器接收到的时间，相差超过指定的时间范围 60s
			// return false;
			// }
			// 判断用户身份
			if (!new String(Base64.encodeBase64(MD5.md5(
					token + " " + ContextUtil.getParam(ParamConst.API_SECRET).getString("VAL")).getBytes())).equals(secret))
			{
				// 用户身份校验不通过
				return false;
			}

			return true;
		}
		catch (Exception e)
		{
			// 非法请求
			return false;
		}
	}

	public static void main(String[] args)
	{
		String s = "c719c8f5f28e41f7abc6e8973a90a471" + " " + "$t%rGvFb";// ContextUtil.getParam(ParamConst.API_SECRET);
		System.out.println(new String(Base64.encodeBase64(MD5.md5(s).getBytes())));
	}
}
