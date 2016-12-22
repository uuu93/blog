package org.lxb.framework.service.system.buttonrights.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lxb.framework.dao.DaoSupport;
import org.lxb.framework.service.system.buttonrights.IButtonrightsService;
import org.lxb.framework.util.PageData;
import org.springframework.stereotype.Service;

/** 
 * 说明： 按钮权限
 * 创建人：FH Q313596790
 * 创建时间：2016-01-16
 * @version
 */
@Service("buttonrightsService")
public class ButtonrightsService implements IButtonrightsService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ButtonrightsMapper.save", pd);
	}
	
	/**通过(角色ID和按钮ID)获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ButtonrightsMapper.findById", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ButtonrightsMapper.delete", pd);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ButtonrightsMapper.listAll", pd);
	}
	
	/**列表(全部)左连接按钮表,查出安全权限标识
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAllBrAndQxname(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ButtonrightsMapper.listAllBrAndQxname", pd);
	}

	/**通过(按钮ID)获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findButtonById(PageData pd) throws Exception
	{
		return (List<PageData>)dao.findForList("ButtonrightsMapper.findButtonById", pd);
	}

}

