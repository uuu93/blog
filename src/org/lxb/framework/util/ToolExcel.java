package org.lxb.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class ToolExcel
{
	/**
	 * 通用excel方法导出
	 * @param heads 头部标题列
	 * @param keys    标题列的keys值
	 * @param list       数据集
	 * @param mv      
	 * @return
	 */
	public static ModelAndView exportExcel(String[] heads,String[] keys,List<PageData> list,ModelAndView mv){
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			for(int i=0;i<heads.length;i++){
				titles.add(heads[i]);
			}
			dataMap.put("titles", titles);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<list.size();i++){
				PageData vpd = new PageData();
				for(int j=0;j<keys.length;j++){
					if (list.get(i).get(keys[j]) != null)
					{
						vpd.put("var" + (j + 1), list.get(i).get(keys[j])); // 1
					}
					else
					{
						vpd.put("var" + (j + 1), ""); // 1
					}
				}
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();					//执行excel操作
			mv = new ModelAndView(erv,dataMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return mv;
	}

}
