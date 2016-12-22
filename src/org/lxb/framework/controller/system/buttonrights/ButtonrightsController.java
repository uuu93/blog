package org.lxb.framework.controller.system.buttonrights;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.lxb.framework.controller.base.BaseController;
import org.lxb.framework.entity.Page;
import org.lxb.framework.entity.system.Role;
import org.lxb.framework.service.system.buttonrights.IButtonrightsService;
import org.lxb.framework.service.system.fhbutton.IFhbuttonService;
import org.lxb.framework.service.system.role.IRoleService;
import org.lxb.framework.util.ApiUtil;
import org.lxb.framework.util.Jurisdiction;
import org.lxb.framework.util.PageData;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 说明：按钮权限
 */
@Controller
@RequestMapping(value = "/buttonrights")
public class ButtonrightsController extends BaseController
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5723865636936361958L;
	String menuUrl = "buttonrights/list.do"; // 菜单地址(权限用)
	@Resource(name = "buttonrightsService")
	private IButtonrightsService buttonrightsService;
	@Resource(name = "roleService")
	private IRoleService roleService;
	@Resource(name = "fhbuttonService")
	private IFhbuttonService fhbuttonService;

	/**
	 * 列表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception
	{
		logBefore(logger, Jurisdiction.getUsername() + "列表Buttonrights");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords))
		{
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = fhbuttonService.list(page); // 列出Fhbutton列表
		mv.setViewName("system/buttonrights/button_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

//	 public ModelAndView list() throws Exception
//	 {
//	 logBefore(logger, Jurisdiction.getUsername() + "列表Buttonrights");
//	 ModelAndView mv = this.getModelAndView();
//	 PageData pd = new PageData();
//	 pd = this.getPageData();
//	 if (pd.getString("ROLE_ID") == null || "".equals(pd.getString("ROLE_ID").trim()))
//	 {
//	 pd.put("ROLE_ID", "1"); // 默认列出第一组角色(初始设计系统用户和会员组不能删除)
//	 }
//	 PageData fpd = new PageData();
//	 fpd.put("ROLE_ID", "0");
//	 List<Role> roleList = roleService.listAllRolesByPId(fpd); // 列出组(页面横向排列的一级组)
//	 List<Role> roleList_z = roleService.listAllRolesByPId(pd); // 列出此组下架角色
//	 List<PageData> buttonlist = fhbuttonService.listAll(pd); // 列出所有按钮
//	 List<PageData> roleFhbuttonlist = buttonrightsService.listAll(pd); // 列出所有角色按钮关联数据
//	 pd = roleService.findObjectById(pd); // 取得点击的角色组(横排的)
//	 mv.addObject("pd", pd);
//	 mv.addObject("roleList", roleList);
//	 mv.addObject("roleList_z", roleList_z);
//	 mv.addObject("buttonlist", buttonlist);
//	 mv.addObject("roleFhbuttonlist", roleFhbuttonlist);
//	 mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
//	 mv.setViewName("system/buttonrights/buttonrights_list");
//	 return mv;
//	 }

	/**
	 * 编辑，准备授权
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
		 if (pd.getString("ROLE_ID") == null || "".equals(pd.getString("ROLE_ID").trim()))
		 {
		 pd.put("ROLE_ID", "1"); // 默认列出第一组角色(初始设计系统用户和会员组不能删除)
		 }
		 
		 PageData fpd = new PageData();
		 fpd.put("ROLE_ID", "0");
		 List<Role> roleList = roleService.listAllRolesByPId(fpd); // 列出组(页面横向排列的一级组)
		 List<Role> roleList_z = roleService.listAllRolesByPId(pd); // 列出此组下所有角色
//		 List<PageData> buttonlist = fhbuttonService.listAll(pd); // 列出所有按钮
		 PageData buttonObj = fhbuttonService.findById(pd);//查询出按钮对象
		 List<PageData> roleFhbuttonlist = buttonrightsService.findButtonById(pd); // 列出所有角色按钮关联数据
		 pd = roleService.findObjectById(pd); // 取得点击的角色组(横排的)
		 mv.addObject("pd", pd);
		 mv.addObject("roleList", roleList);
		 mv.addObject("roleList_z", roleList_z);
		 mv.addObject("buttonObj", buttonObj);
		 mv.addObject("roleFhbuttonlist", roleFhbuttonlist);
		 mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		 mv.setViewName("system/buttonrights/buttonrights_list");
		return mv;
	}

	/**
	 * 点击按钮处理关联表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upRb")
	@ResponseBody
	public Object updateRolebuttonrightd() throws Exception
	{
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
		{
			return null;
		} // 校验权限
		logBefore(logger, Jurisdiction.getUsername() + "分配按钮权限");
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success";
		if (null != buttonrightsService.findById(pd))
		{ // 判断关联表是否有数据 是:删除/否:新增
			buttonrightsService.delete(pd); // 删除
		}
		else
		{
			pd.put("RB_ID", this.get32UUID()); // 主键
			buttonrightsService.save(pd); // 新增
		}
		map.put("result", errInfo);
		return ApiUtil.returnObject(new PageData(), map);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
}
