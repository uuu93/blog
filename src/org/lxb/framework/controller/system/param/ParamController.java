package org.lxb.framework.controller.system.param;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.lxb.framework.controller.base.BaseController;
import org.lxb.framework.entity.Page;
import org.lxb.framework.service.system.param.IParamService;
import org.lxb.framework.util.ApiUtil;
import org.lxb.framework.util.Jurisdiction;
import org.lxb.framework.util.ObjectExcelView;
import org.lxb.framework.util.PageData;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 参数项
 * 
 * @author linxinbin 2016年6月23日 下午7:35:09
 */
@Controller
@RequestMapping(value = "/param")
public class ParamController extends BaseController
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 119718769729303430L;
	String menuUrl = "param/list.do"; // 菜单地址(权限用)
	@Resource(name = "paramService")
	private IParamService paramService;

	/**
	 * 保存
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception
	{
		logBefore(logger, Jurisdiction.getUsername() + "新增Param");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
		{
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PARAM_ID", this.get32UUID()); // 主键
		paramService.save(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) throws Exception
	{
		logBefore(logger, Jurisdiction.getUsername() + "删除Param");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del"))
		{
			return;
		} // 校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		paramService.delete(pd);
		out.write("success");
		out.close();
	}

	/**
	 * 修改
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception
	{
		logBefore(logger, Jurisdiction.getUsername() + "修改Param");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
		{
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		paramService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception
	{
		logBefore(logger, Jurisdiction.getUsername() + "列表Param");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords))
		{
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = paramService.list(page); // 列出Param列表
		mv.setViewName("system/param/param_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 去新增页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() throws Exception
	{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/param/param_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 去修改页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() throws Exception
	{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = paramService.findById(pd); // 根据ID读取
		mv.setViewName("system/param/param_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 批量删除
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception
	{
		logBefore(logger, Jurisdiction.getUsername() + "批量删除Param");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del"))
		{
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS))
		{
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			paramService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}
		else
		{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return ApiUtil.returnObject(pd, map);
	}

	/**
	 * 导出到excel
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() throws Exception
	{
		logBefore(logger, Jurisdiction.getUsername() + "导出Param到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha"))
		{
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("id"); // 1
		titles.add("参数名"); // 2
		titles.add("参数值"); // 3
		titles.add("备注"); // 5
		dataMap.put("titles", titles);
		List<PageData> varOList = paramService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for (int i = 0; i < varOList.size(); i++)
		{
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PARAM_ID")); // 1
			vpd.put("var2", varOList.get(i).getString("NAME")); // 2
			vpd.put("var3", varOList.get(i).getString("VAL")); // 3
			vpd.put("var5", varOList.get(i).getString("BZ")); // 5
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	/**
	 * 判断键名是否存在
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hasName")
	@ResponseBody
	public Object hasName()
	{
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try
		{
			pd = this.getPageData();
			if (paramService.findByName(pd) != null)
			{
				errInfo = "error";
			}
		}
		catch (Exception e)
		{
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return ApiUtil.returnObject(new PageData(), map);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
}
