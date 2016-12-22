package org.lxb.framework.service.system.dictionaries;

import java.util.List;
import java.util.Map;

import org.lxb.framework.entity.Page;
import org.lxb.framework.entity.system.Dictionaries;
import org.lxb.framework.util.PageData;

/**
 * 说明： 数据字典接口类
 */
public interface IDictionariesService
{

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception;

	/**
	 * 删除
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd) throws Exception;

	/**
	 * 修改
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd) throws Exception;

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 产品结构管理列表
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getProductConstructionlistPage(Page page) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception;

	/**
	 * 通过编码获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBianma(PageData pd) throws Exception;

	/**
	 * 通过ID获取其子级列表
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listSubDictByParentId(String parentId) throws Exception;

	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * 
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listAllDict(String parentId, String url) throws Exception;

	/**
	 * 排查表检查是否被占用
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findFromTbs(PageData pd) throws Exception;

	/**
	 * 根据编码查询其子级节点
	 * 
	 * @param page
	 * @return
	 */
	public List<PageData> findChildByBianma(Page page) throws Exception;

	/**
	 * 根据编码查询子级节点
	 */
	public List<PageData> findChildByBianmalistPage(Page page) throws Exception;

	/**
	 * 根据编码查询子级节点
	 */
	public List<PageData> findChildsByBianma(String bianma) throws Exception;

	/**
	 * 获取子级列表，状态条件
	 * 
	 * @param parentId
	 * @param state
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listAllDict(String parentId, String state, String url) throws Exception;

	/**
	 * 查询对应数据字典结构下的所有子级数据，目前只支持四级
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findAllChildById(String id) throws Exception;

	/**
	 * 通过ID和状态获取其子级列表
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listSubDictByParentId(String parentId, String state) throws Exception;

	/**
	 * 通过编码查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> findByBianMa(Page p) throws Exception;

	/**
	 * 通过ID查询以下子类 返回字典Map值,仅支持四级
	 * 
	 * @param bianma
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> allProductString(String ID) throws Exception;

}
