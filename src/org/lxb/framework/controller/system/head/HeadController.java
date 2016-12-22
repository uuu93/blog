package org.lxb.framework.controller.system.head;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.lxb.framework.controller.base.BaseController;
import org.lxb.framework.service.system.user.IUserService;
import org.lxb.framework.util.ApiUtil;
import org.lxb.framework.util.Const;
import org.lxb.framework.util.Jurisdiction;
import org.lxb.framework.util.PageData;
import org.lxb.framework.util.Tools;
import org.lxb.framework.util.Watermark;
import org.lxb.framework.util.mail.SimpleMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类名称：HeadController
 * 
 * @version
 */
@Controller
@RequestMapping(value = "/head")
public class HeadController extends BaseController
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7275497539763185534L;
	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * 获取头部信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getList")
	@ResponseBody
	public Object getList()
	{
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			Session session = Jurisdiction.getSession();
			PageData pds = new PageData();
			pds = (PageData) session.getAttribute(Const.SESSION_userpds);
			if (null == pds)
			{
				pd.put("USERNAME", Jurisdiction.getUsername());// 当前登录者用户名
				pds = userService.findByUsername(pd);
				session.setAttribute(Const.SESSION_userpds, pds);
			}
			pdList.add(pds);
			map.put("list", pdList);
		}
		catch (Exception e)
		{
			logger.error(e.toString(), e);
		}
		finally
		{
			logAfter(logger);
		}
		return ApiUtil.returnObject(pd, map);
	}

	/**
	 * 去发送邮箱页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editEmail")
	public ModelAndView editEmail() throws Exception
	{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/edit_email");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 去发送电子邮件页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goSendEmail")
	public ModelAndView goSendEmail() throws Exception
	{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/send_email");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 发送电子邮件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sendEmail")
	@ResponseBody
	public Object sendEmail()
	{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "ok"; // 发送状态
		int count = 0; // 统计发送成功条数
		int zcount = 0; // 理论条数
		String strEMAIL = Tools.readTxtFile(Const.EMAIL); // 读取邮件配置
		List<PageData> pdList = new ArrayList<PageData>();
		String toEMAIL = pd.getString("EMAIL"); // 对方邮箱
		String TITLE = pd.getString("TITLE"); // 标题
		String CONTENT = pd.getString("CONTENT"); // 内容
		String TYPE = pd.getString("TYPE"); // 类型
		String isAll = pd.getString("isAll"); // 是否发送给全体成员 yes or no
		if (null != strEMAIL && !"".equals(strEMAIL))
		{
			String strEM[] = strEMAIL.split(",fh,");
			if (strEM.length == 4)
			{
				if ("yes".endsWith(isAll))
				{
					try
					{
						List<PageData> userList = new ArrayList<PageData>();
						userList = userService.listAllUser(pd);
						zcount = userList.size();
						try
						{
							for (int i = 0; i < userList.size(); i++)
							{
								if (Tools.checkEmail(userList.get(i).getString("EMAIL")))
								{ // 邮箱格式不对就跳过
									SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3],
											userList.get(i).getString("EMAIL"), TITLE, CONTENT, TYPE, true);// 调用发送邮件函数
									count++;
								}
								else
								{
									continue;
								}
							}
							msg = "ok";
						}
						catch (Exception e)
						{
							msg = "error";
						}
					}
					catch (Exception e)
					{
						msg = "error";
					}
				}
				else
				{
					toEMAIL = toEMAIL.replaceAll("；", ";");
					toEMAIL = toEMAIL.replaceAll(" ", "");
					String[] arrTITLE = toEMAIL.split(";");
					zcount = arrTITLE.length;
					try
					{
						for (int i = 0; i < arrTITLE.length; i++)
						{
							if (Tools.checkEmail(arrTITLE[i]))
							{ // 邮箱格式不对就跳过
								SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], arrTITLE[i], TITLE, CONTENT, TYPE,
										true);// 调用发送邮件函数
								count++;
							}
							else
							{
								continue;
							}
						}
						msg = "ok";
					}
					catch (Exception e)
					{
						e.printStackTrace();
						msg = "error";
					}
				}
			}
			else
			{
				msg = "error";
			}
		}
		else
		{
			msg = "error";
		}
		pd.put("msg", msg);
		pd.put("count", count); // 成功数
		pd.put("ecount", zcount - count); // 失败数
		pdList.add(pd);
		map.put("list", pdList);
		return ApiUtil.returnObject(pd, map);
	}

	/**
	 * 去系统设置页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goSystem")
	public ModelAndView goEditEmail() throws Exception
	{
		if (!"admin".equals(Jurisdiction.getUsername()))
		{
			return null;
		} // 非admin用户不能修改
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("YSYNAME", Tools.readTxtFile(Const.SYSNAME)); // 读取系统名称
		pd.put("COUNTPAGE", Tools.readTxtFile(Const.PAGE)); // 读取每页条数
		String strEMAIL = Tools.readTxtFile(Const.EMAIL); // 读取邮件配置
		String strFWATERM = Tools.readTxtFile(Const.FWATERM); // 读取文字水印配置
		String strIWATERM = Tools.readTxtFile(Const.IWATERM); // 读取图片水印配置
		if (null != strEMAIL && !"".equals(strEMAIL))
		{
			String strEM[] = strEMAIL.split(",fh,");
			if (strEM.length == 4)
			{
				pd.put("SMTP", strEM[0]);
				pd.put("PORT", strEM[1]);
				pd.put("EMAIL", strEM[2]);
				pd.put("PAW", strEM[3]);
			}
		}
		if (null != strFWATERM && !"".equals(strFWATERM))
		{
			String strFW[] = strFWATERM.split(",fh,");
			if (strFW.length == 5)
			{
				pd.put("isCheck1", strFW[0]);
				pd.put("fcontent", strFW[1]);
				pd.put("fontSize", strFW[2]);
				pd.put("fontX", strFW[3]);
				pd.put("fontY", strFW[4]);
			}
		}
		if (null != strIWATERM && !"".equals(strIWATERM))
		{
			String strIW[] = strIWATERM.split(",fh,");
			if (strIW.length == 4)
			{
				pd.put("isCheck2", strIW[0]);
				pd.put("imgUrl", strIW[1]);
				pd.put("imgX", strIW[2]);
				pd.put("imgY", strIW[3]);
			}
		}
		mv.setViewName("system/head/sys_edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 保存系统设置1
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveSys")
	public ModelAndView saveSys() throws Exception
	{
		if (!"admin".equals(Jurisdiction.getUsername()))
		{
			return null;
		} // 非admin用户不能修改
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Tools.writeFile(Const.SYSNAME, pd.getString("YSYNAME")); // 写入系统名称
		Tools.writeFile(Const.PAGE, pd.getString("COUNTPAGE")); // 写入每页条数
		Tools.writeFile(Const.EMAIL, pd.getString("SMTP") + ",fh," + pd.getString("PORT") + ",fh," + pd.getString("EMAIL")
				+ ",fh," + pd.getString("PAW")); // 写入邮件服务器配置
		mv.addObject("msg", "OK");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 保存系统设置2
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveSys2")
	public ModelAndView saveSys2() throws Exception
	{
		if (!"admin".equals(Jurisdiction.getUsername()))
		{
			return null;
		} // 非admin用户不能修改
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Tools.writeFile(
				Const.FWATERM,
				pd.getString("isCheck1") + ",fh," + pd.getString("fcontent") + ",fh," + pd.getString("fontSize") + ",fh,"
						+ pd.getString("fontX") + ",fh," + pd.getString("fontY")); // 文字水印配置
		Tools.writeFile(
				Const.IWATERM,
				pd.getString("isCheck2") + ",fh," + pd.getString("imgUrl") + ",fh," + pd.getString("imgX") + ",fh,"
						+ pd.getString("imgY")); // 图片水印配置
		Watermark.fushValue();
		mv.addObject("msg", "OK");
		mv.setViewName("save_result");
		return mv;
	}

}
