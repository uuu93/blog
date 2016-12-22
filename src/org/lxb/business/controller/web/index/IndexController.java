/**
 * 
 */
package org.lxb.business.controller.web.index;

import org.lxb.framework.controller.base.BaseController;
import org.lxb.framework.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前端主页控制器
 * 
 * @author linxinbin 2016年12月21日 下午6:18:36
 */
@Controller
@RequestMapping(value = "/web/index")
public class IndexController extends BaseController
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3690592538355104084L;

	/**
	 * 获取首页信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	@ResponseBody
	public ModelAndView getList()
	{
		PageData pd = new PageData();
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("web/index");
		return mv;
	}

}
