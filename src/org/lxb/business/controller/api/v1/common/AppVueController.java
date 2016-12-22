/**
 * 
 */
package org.lxb.business.controller.api.v1.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.lxb.framework.controller.base.BaseController;
import org.lxb.framework.entity.Page;
import org.lxb.framework.service.system.user.IUserService;
import org.lxb.framework.service.system.user.impl.UserService;
import org.lxb.framework.util.ApiUtil;
import org.lxb.framework.util.AppConstants;
import org.lxb.framework.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linxinbin 2016年12月9日 下午12:15:25
 */
@Controller
@RequestMapping(value = "/api/v1/vue")
public class AppVueController extends BaseController
{

	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3393369266445252116L;
	static List<PageData> productlist = new ArrayList<PageData>();
	static
	{
		PageData p1 = new PageData();
		p1.put("id", "111");
		p1.put("name", "Jack");
		p1.put("age", 22);
		p1.put("sex", "M");
		PageData p2 = new PageData();
		p2.put("id", "222");
		p2.put("name", "Tom");
		p2.put("age", 23);
		p2.put("sex", "M");
		PageData p3 = new PageData();
		p3.put("id", "333");
		p3.put("name", "Jane");
		p3.put("age", 21);
		p3.put("sex", "F");

		productlist.add(p1);
		productlist.add(p2);
		productlist.add(p3);
	}

	/**
	 * 查询用户列表
	 */
	@RequestMapping(value = "/list/{currentPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(@PathVariable("currentPage") int currentPage) throws Exception
	{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPd(pd);
		List<PageData> users = userService.listUsers(page);
		json.put("page", page.tidyPage());
		json.put("users", users);
		json.put(AppConstants.code, AppConstants.success);
		json.put(AppConstants.msg, "获取成功");
		return JSONObject.fromObject(json, ApiUtil.getJsonConfig());
	}

	/**
	 * 查询用户详情
	 */
	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	@ResponseBody
	public Object info() throws Exception
	{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		PageData user = userService.findById(pd);
		json.put("info", user);
		json.put(AppConstants.code, AppConstants.success);
		json.put(AppConstants.msg, "获取成功");
		return JSONObject.fromObject(json, ApiUtil.getJsonConfig());
	}

	/**
	 * 编辑用户信息
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.PUT })
	@ResponseBody
	public Object edit(@RequestBody String reqdata) throws Exception
	{
		JSONObject data = JSONObject.fromObject(reqdata);
		PageData pd = ApiUtil.toHashMap(data.getJSONObject("data"));
		JSONObject json = new JSONObject();
		// PageData pd = this.getPageData();
		userService.editU(pd);
		json.put(AppConstants.code, AppConstants.success);
		json.put(AppConstants.msg, "修改成功");
		return JSONObject.fromObject(json, ApiUtil.getJsonConfig());
	}

	/**
	 * 新增用户信息
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object add(@RequestBody String reqdata) throws Exception
	{
		JSONObject data = JSONObject.fromObject(reqdata);
		JSONObject json = new JSONObject();
		PageData pd = ApiUtil.toHashMap(data.getJSONObject("data"));
		// PageData pd = this.getPageData();
		pd.put("USER_ID", this.get32UUID());
		pd.put("LAST_LOGIN", ""); // 最后登录时间
		pd.put("IP", ""); // IP
		pd.put("STATUS", "0"); // 状态
		pd.put("SKIN", "default");
		pd.put("RIGHTS", "");
		pd.put("ROLE_ID", "3264c8e83d0248bb9e3ea6195b4c0216");
		pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), "111111").toString()); // 密码加密
		userService.saveU(pd);
		json.put(AppConstants.code, AppConstants.success);
		json.put(AppConstants.msg, "添加成功");
		return JSONObject.fromObject(json, ApiUtil.getJsonConfig());
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.DELETE })
	@ResponseBody
	public Object delete() throws Exception
	{
		JSONObject json = new JSONObject();
		PageData pd = this.getPageData();
		userService.deleteU(pd);
		json.put(AppConstants.code, AppConstants.success);
		json.put(AppConstants.msg, "删除成功");
		return JSONObject.fromObject(json, ApiUtil.getJsonConfig());
	}

	public static void main(String[] args)
	{
		String s = "原图.jpg";
		String target = s.substring(0, s.lastIndexOf(".")) + "_200" + s.substring(s.lastIndexOf("."), s.length() - 1);
		System.out.println(target);
	}
}
