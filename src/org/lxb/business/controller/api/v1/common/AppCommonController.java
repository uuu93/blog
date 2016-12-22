/**
 * 
 */
package org.lxb.business.controller.api.v1.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.lxb.business.common.ParamConst;
import org.lxb.framework.controller.base.BaseController;
import org.lxb.framework.util.AppConstants;
import org.lxb.framework.util.ContextUtil;
import org.lxb.framework.util.DateUtil;
import org.lxb.framework.util.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共接口v1
 * 
 * @author linxinbin 2016年6月30日 下午7:13:03
 */
@Controller
@RequestMapping(value = "/api/v1/common")
public class AppCommonController extends BaseController
{

	private static final long serialVersionUID = -7068704339440681455L;

	/**
	 * 上传文件
	 */
	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	@ResponseBody
	public Object upload(@RequestParam(required = true, value = "files") MultipartFile[] files) throws Exception
	{
		String ffile = DateUtil.getDays(), fileName = "";
		String filePath = ContextUtil.getParam(ParamConst.RES_DIR).getString("VAL") + ffile; // 文件上传路径
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		for (MultipartFile file : files)
		{
			Map<String, String> result = new HashMap<String, String>();
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传
			result.put("filename", fileName);
			result.put("path", ffile + "/" + fileName);
			results.add(result);
		}
		JSONObject json = new JSONObject();
		json.put(AppConstants.code, AppConstants.success);
		json.put(AppConstants.msg, "上传成功。共" + results.size() + "个文件");
		json.put("results", results);
		return json;
	}
	
	/**
	 * 
	 */
	
}