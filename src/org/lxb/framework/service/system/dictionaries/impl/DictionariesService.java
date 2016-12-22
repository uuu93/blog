package org.lxb.framework.service.system.dictionaries.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.lxb.framework.dao.DaoSupport;
import org.lxb.framework.entity.Page;
import org.lxb.framework.entity.system.Dictionaries;
import org.lxb.framework.service.system.dictionaries.IDictionariesService;
import org.lxb.framework.util.PageData;
import org.springframework.stereotype.Service;

/**
 * 说明： 数据字典
 * 
 * @version
 */
@Service("dictionariesService")
public class DictionariesService implements IDictionariesService
{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void save(PageData pd) throws Exception
	{
		dao.save("DictionariesMapper.save", pd);
	}

	/**
	 * 删除
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void delete(PageData pd) throws Exception
	{
		dao.delete("DictionariesMapper.delete", pd);
	}

	/**
	 * 修改
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void edit(PageData pd) throws Exception
	{
		dao.update("DictionariesMapper.edit", pd);
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception
	{
		return (List<PageData>) dao.findForList("DictionariesMapper.datalistPage", page);
	}

	
	/**
	 * 产品结构管理列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> getProductConstructionlistPage(Page page) throws Exception{
		return (List<PageData>) dao.findForList("DictionariesMapper.getProductConstructionlistPage", page);
	}
	
	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findById(PageData pd) throws Exception
	{
		return (PageData) dao.findForObject("DictionariesMapper.findById", pd);
	}

	/**
	 * 通过编码获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findByBianma(PageData pd) throws Exception
	{
		return (PageData) dao.findForObject("DictionariesMapper.findByBianma", pd);
	}

	/**
	 * 通过ID获取其子级列表
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Dictionaries> listSubDictByParentId(String parentId) throws Exception
	{
		return (List<Dictionaries>) dao.findForList("DictionariesMapper.listSubDictByParentId", parentId);
	}

	/**
	 * 根据状态和ID查询子级列表
	 * 
	 * @param parentId
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Dictionaries> listSubDictByParentId(String parentId, String state) throws Exception
	{
		PageData pd = new PageData();
		pd.put("parentId", parentId);
		pd.put("state", state);
		return (List<Dictionaries>) dao.findForList("DictionariesMapper.listSubDictByState", pd);
	}

	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * 
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Dictionaries> listAllDict(String parentId, String url) throws Exception
	{
		List<Dictionaries> dictList = this.listSubDictByParentId(parentId);
		for (Dictionaries dict : dictList)
		{
			dict.setTreeurl(url + "?DICTIONARIES_ID=" + dict.getDICTIONARIES_ID());
			dict.setSubDict(this.listAllDict(dict.getDICTIONARIES_ID(), url));
			dict.setTarget("treeFrame");
		}
		return dictList;
	}

	/**
	 * 获取子级列表，状态条件
	 * 
	 * @param parentId
	 * @param state
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Dictionaries> listAllDict(String parentId, String state, String url) throws Exception
	{
		List<Dictionaries> dictList = this.listSubDictByParentId(parentId, state);
		for (Dictionaries dict : dictList)
		{
			dict.setTreeurl(url + "?DICTIONARIES_ID=" + dict.getDICTIONARIES_ID());
			dict.setSubDict(this.listAllDict(dict.getDICTIONARIES_ID(), url));
			dict.setTarget("treeFrame");
		}
		return dictList;
	}

	/**
	 * 排查表检查是否被占用
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findFromTbs(PageData pd) throws Exception
	{
		return (PageData) dao.findForObject("DictionariesMapper.findFromTbs", pd);
	}

	/**
	 * 根据编码查询子级节点
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findChildByBianma(Page page) throws Exception
	{
		return (List<PageData>) dao.findForList("DictionariesMapper.findChildByBianma", page);
	}

	/**
	 * 根据编码查询子级节点
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findChildsByBianma(String bianma) throws Exception
	{
		return (List<PageData>) dao.findForList("DictionariesMapper.findChildsByBianma", bianma);
	}

	/**
	 * 根据编码查询子级节点
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findChildByBianmalistPage(Page page) throws Exception
	{
		return (List<PageData>) dao.findForList("DictionariesMapper.findChildByBianmalistPage", page);
	}
	
	/**
	 * 查询对应数据字典结构下的所有子级数据，目前只支持四级
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findAllChildById(String id) throws Exception
	{
		return (List<PageData>) dao.findForList("DictionariesMapper.findAllChildById", id);
	}

	@Override
	public Map<String, String> findByBianMa(Page p) throws Exception
	{
		Map<String,String> orderstatus = new HashMap<String,String>();
		List<PageData> pagelist = findChildByBianma(p);
		for(PageData pa:pagelist){
			orderstatus.put(pa.getString("BIANMA"), pa.getString("NAME"));
		}
		return orderstatus;
	}
	
	/**
	 * 通过ID查询以下子类
	 * 返回字典Map值,仅支持四级
	 * @param bianma
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,String> allProductString(String ID) throws Exception{
		Map<String,String> resultMap = new HashMap<String, String>();
		List<PageData> listdata = findAllChildById(ID);
		for(PageData p:listdata){
			resultMap.put(p.getString("bianma"), p.getString("name"));
		}
		return resultMap;
	}

}
