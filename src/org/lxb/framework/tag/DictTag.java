package org.lxb.framework.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.lxb.framework.entity.Page;
import org.lxb.framework.service.system.dictionaries.impl.DictionariesService;
import org.lxb.framework.util.Logger;
import org.lxb.framework.util.PageData;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DictTag extends TagSupport {

	private static final long serialVersionUID = 3957049283315026224L;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private PageContext pageContext;
	/**
	 * 字典表中的编码
	 */
	private String dictid;
	/**
	 * select选项卡的id
	 */
	private String _id;
	//
	/**
	 * 给节点赋予class
	 */
	private String _class;
	/**
	 * 给节点赋予name
	 */
	private String _name;
	/**
	 * 原始的html代码
	 */
	private String _html;
	/**
	 * select选项卡的第一项的值
	 */
	private String firstValue;
	/**
	 * select选项卡的第一项的option的文本
	 */
	private String firstText;
	/**
	 * 初始值
	 */
	private String defaultValue;
	/**
	 * 节点类型
	 */
	private String domType;
	/**
	 * 生成节点值的表的字段名
	 */
	private String valueColumn;
	/**
	 * 生成节点值的表的字段名
	 */
	private String textColumn;
	/**
	 * 生成checkbox时的生成id的字段名
	 */
	private String idColumn;
	/**
	 * 获取子节点时条件过滤的字段名
	 */
	private String escapeColumn;
	/**
	 * 获取子节点时条件过滤的字段值
	 */
	private String escapeValue;

	@Override
	public int doStartTag() throws JspException {
		try {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			DictionariesService dictionariesService = (DictionariesService) webApplicationContext.getBean(DictionariesService.class);
			Page page = new Page();
			PageData pageData = new PageData();
			pageData.put("BIANMA", dictid);
			page.setPd(pageData);
			
			StringBuilder html = new StringBuilder();
			if("select".equalsIgnoreCase(domType)){
				if (!StringUtils.isEmpty(escapeColumn))
				{
					pageData.put(escapeColumn, escapeValue);
				}
				List<PageData> dicList = dictionariesService.findChildByBianma(page);
				html = createSelectDom(dicList);
			}else if("checkbox".equalsIgnoreCase(domType)){
				if (!StringUtils.isEmpty(escapeColumn))
				{
					pageData.put(escapeColumn, escapeValue);
				}
				List<PageData> dicList = dictionariesService.findChildByBianma(page);
				html = createCheckboxDom(dicList);
			}else if("writeText".equalsIgnoreCase(domType)){
				PageData dict = dictionariesService.findByBianma(pageData);
				if (dict != null && !dict.isEmpty())
				{
					html.append(dict.getString(textColumn));
				}
			}
			pageContext.getOut().write(html.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}

	/**
	 * 生成checkbox复选框节点
	 * wangyue 2016年7月7日
	 */
	private StringBuilder createCheckboxDom(List<PageData> dicList) {
		StringBuilder html = new StringBuilder();
		for(PageData pageData : dicList){
			if("1".equals(pageData.getString("state"))){
				logger.info("字典处于隐藏状态"+pageData.getString("BIANMA"));
				continue;
			}
			html.append(" <input type='checkbox'");
			if(!StringUtils.isEmpty(valueColumn)){
				html.append(" value='").append(pageData.getString(valueColumn)).append("'");
			}
			if(!StringUtils.isEmpty(idColumn)){
				html.append(" id='").append(pageData.getString(idColumn)).append("'");
			}
			if(!StringUtils.isEmpty(_class)){
				html.append(" class='").append(_class).append("'");
			}
			if(!StringUtils.isEmpty(_name)){
				html.append(" name='").append(_name).append("'");
			}
			if(!StringUtils.isEmpty(_html)){
				html.append(_html);
			}
			html.append("/>");
			html.append(pageData.getString(textColumn));
		}
		return html;
	}

	/**
	 * 生成select选择框节点
	 * wangyue 2016年7月6日
	 */
	private StringBuilder createSelectDom(List<PageData> dicList){
		StringBuilder html = new StringBuilder();
		html.append("<select");
		if(!StringUtils.isEmpty(_id)){
			html.append(" id='").append(_id).append("'");
		}
		if(!StringUtils.isEmpty(_class)){
			html.append(" class='").append(_class).append("'");
		}
		if(!StringUtils.isEmpty(_name)){
			html.append(" name='").append(_name).append("'");
		}
		if(!StringUtils.isEmpty(_html)){
			html.append(" ").append(_html);
		}
		html.append(" >");
		if(!StringUtils.isEmpty(firstText)){
			html.append(" <option");
			html.append(" value='").append(firstValue).append("'");
			html.append(">").append(firstText).append("</option>");
		}
		for(PageData pageData : dicList){
			if("1".equals(pageData.getString("state"))){
				logger.info("字典处于隐藏状态"+pageData.getString("BIANMA"));
				continue;
			}
			html.append("<option value='").append(pageData.getString(valueColumn)).append("'");
			if(!StringUtils.isEmpty(defaultValue)){
				if(defaultValue.equalsIgnoreCase(pageData.getString(valueColumn))){
					html.append(" selected='selected'");
				}
			}
			html.append(">").append(pageData.get(textColumn)).append("</option>");
		}
		html.append("</select>");
		return html;
	}
	public PageContext getPageContext() {
		return pageContext;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_html() {
		return _html;
	}

	public void set_html(String _html) {
		this._html = _html;
	}

	public String getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(String firstValue) {
		this.firstValue = firstValue;
	}

	public String getFirstText() {
		return firstText;
	}

	public void setFirstText(String firstText) {
		this.firstText = firstText;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDomType() {
		return domType;
	}

	public void setDomType(String domType) {
		this.domType = domType;
	}

	public String getValueColumn() {
		return valueColumn;
	}

	public void setValueColumn(String valueColumn) {
		this.valueColumn = valueColumn;
	}

	public String getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	public String getTextColumn() {
		return textColumn;
	}

	public void setTextColumn(String textColumn) {
		this.textColumn = textColumn;
	}

	public String getEscapeColumn()
	{
		return escapeColumn;
	}

	public void setEscapeColumn(String escapeColumn)
	{
		this.escapeColumn = escapeColumn;
	}

	public String getEscapeValue()
	{
		return escapeValue;
	}

	public void setEscapeValue(String escapeValue)
	{
		this.escapeValue = escapeValue;
	}

}
