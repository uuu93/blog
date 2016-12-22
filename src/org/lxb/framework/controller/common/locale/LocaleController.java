/**
 * 
 */
package org.lxb.framework.controller.common.locale;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxb.framework.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 国际化控制器
 * 
 * @author linxinbin 2016年6月22日 上午11:11:13
 */
@Controller
@RequestMapping(value = "/common/locale")
public class LocaleController extends BaseController
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1031687060002332069L;

	@Resource
	private LocaleResolver localeResolver;

	static public final Locale MY_LOCAL = new Locale("ms", "MY");

	@RequestMapping("/changeLocale")
	public void changeLocale(@RequestParam(defaultValue = "zh_CN", required = true, value = "locale") String locale,
			HttpServletRequest request, HttpServletResponse response)
	{
		if ("zh_CN".equals(locale))
		{
			localeResolver.setLocale(request, response, Locale.SIMPLIFIED_CHINESE);
		}
		else if ("zh_TW".equals(locale))
		{
			localeResolver.setLocale(request, response, Locale.TRADITIONAL_CHINESE);
		}
		else if ("en_US".equals(locale))
		{
			localeResolver.setLocale(request, response, Locale.US);
		}
		else if ("ja_JP".equals(locale))
		{
			localeResolver.setLocale(request, response, Locale.JAPAN);
		}
		else if ("ms_MY".equals(locale))
		{
			localeResolver.setLocale(request, response, MY_LOCAL);
		}
		else
		{
			localeResolver.setLocale(request, response, Locale.SIMPLIFIED_CHINESE);
		}
	}
}
