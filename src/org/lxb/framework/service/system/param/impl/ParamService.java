package org.lxb.framework.service.system.param.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lxb.framework.dao.DaoSupport;
import org.lxb.framework.entity.Page;
import org.lxb.framework.service.system.param.IParamService;
import org.lxb.framework.util.ContextUtil;
import org.lxb.framework.util.PageData;
import org.springframework.stereotype.Service;

/**
 * 参数项
 * 
 * @author linxinbin 2016年6月24日 上午11:11:36
 */
@Service("paramService")
public class ParamService implements IParamService
{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception
	{
		dao.save("ParamMapper.save", pd);
		ContextUtil.addParam(pd.getString("NAME"), pd);
	}

	/**
	 * 删除
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd) throws Exception
	{
		dao.delete("ParamMapper.delete", pd);
		ContextUtil.removeParam(pd.getString("NAME"));
	}

	/**
	 * 修改
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd) throws Exception
	{
		dao.update("ParamMapper.edit", pd);
		ContextUtil.addParam(pd.getString("NAME"), pd);
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception
	{
		return (List<PageData>) dao.findForList("ParamMapper.datalistPage", page);
	}

	/**
	 * 列表(全部)
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd) throws Exception
	{
		return (List<PageData>) dao.findForList("ParamMapper.listAll", pd);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception
	{
		return (PageData) dao.findForObject("ParamMapper.findById", pd);
	}

	/**
	 * 批量删除
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception
	{
		dao.delete("ParamMapper.deleteAll", ArrayDATA_IDS);
		ContextUtil.initParam(listAll(new PageData()));
	}

	/**
	 * 根据NAME查询
	 */
	@Override
	public PageData findByName(PageData pd) throws Exception
	{
		return (PageData) dao.findForObject("ParamMapper.findByName", pd);
	}

}
