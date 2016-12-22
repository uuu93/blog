package org.lxb.framework.controller.common.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lxb.business.common.ParamConst;
import org.lxb.framework.controller.base.BaseController;
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
 * 统一文件上传控制器
 * 
 * @author linxinbin 2016年6月23日 下午12:18:29
 */
@Controller
@RequestMapping(value = "/common/upload")
public class UploadController extends BaseController
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5264170532745797413L;

	/**
	 * 保存上传文件
	 * 
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	@ResponseBody
	public Object save(@RequestParam(required = true, value = "files") MultipartFile[] files) throws Exception
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
		return results;
	}

}
