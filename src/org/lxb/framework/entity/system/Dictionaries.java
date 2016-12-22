package org.lxb.framework.entity.system;

import java.util.List;

/**
 * 
 * 类名称：数据字典 类描述：
 * 
 * @author FH QQ 313596790[青苔] 作者单位： 联系方式： 修改时间：2015年12月16日
 * @version 2.0
 */
public class Dictionaries
{

	private String NAME; // 简体中文
	private String NAME_HK; // 繁体中文
	private String NAME_EN; // 英文名称
	private String NAME_JA; // 日文名称
	private String NAME_MY; // 马来文名称
	private String BIANMA; // 编码
	private String ORDER_BY; // 排序
	private String PARENT_ID; // 上级ID
	private String BZ; // 备注
	private String ICON; // 图标
	private String TBSNAME; // 关联表
	private String DICTIONARIES_ID; // 主键
	private String STATE;//状态
	public String getSTATE()
	{
		return STATE;
	}

	public void setSTATE(String sTATE)
	{
		STATE = sTATE;
	}

	private String target;
	private Dictionaries dict;
	private List<Dictionaries> subDict;
	private boolean hasDict = false;
	private String treeurl;

	public String getNAME()
	{
		return NAME;
	}

	public void setNAME(String nAME)
	{
		NAME = nAME;
	}

	public String getNAME_EN()
	{
		return NAME_EN;
	}

	public void setNAME_EN(String nAME_EN)
	{
		NAME_EN = nAME_EN;
	}

	public String getBIANMA()
	{
		return BIANMA;
	}

	public void setBIANMA(String bIANMA)
	{
		BIANMA = bIANMA;
	}

	public String getORDER_BY()
	{
		return ORDER_BY;
	}

	public void setORDER_BY(String oRDER_BY)
	{
		ORDER_BY = oRDER_BY;
	}

	public String getPARENT_ID()
	{
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID)
	{
		PARENT_ID = pARENT_ID;
	}

	public String getBZ()
	{
		return BZ;
	}

	public void setBZ(String bZ)
	{
		BZ = bZ;
	}

	public String getTBSNAME()
	{
		return TBSNAME;
	}

	public void setTBSNAME(String tBSNAME)
	{
		TBSNAME = tBSNAME;
	}

	public String getDICTIONARIES_ID()
	{
		return DICTIONARIES_ID;
	}

	public void setDICTIONARIES_ID(String dICTIONARIES_ID)
	{
		DICTIONARIES_ID = dICTIONARIES_ID;
	}

	public String getNAME_HK()
	{
		return NAME_HK;
	}

	public void setNAME_HK(String nAME_HK)
	{
		NAME_HK = nAME_HK;
	}

	public String getNAME_JA()
	{
		return NAME_JA;
	}

	public void setNAME_JA(String nAME_JA)
	{
		NAME_JA = nAME_JA;
	}

	public String getNAME_MY()
	{
		return NAME_MY;
	}

	public void setNAME_MY(String nAME_MY)
	{
		NAME_MY = nAME_MY;
	}

	public String getICON()
	{
		return ICON;
	}

	public void setICON(String iCON)
	{
		ICON = iCON;
	}

	public String getTarget()
	{
		return target;
	}

	public void setTarget(String target)
	{
		this.target = target;
	}

	public Dictionaries getDict()
	{
		return dict;
	}

	public void setDict(Dictionaries dict)
	{
		this.dict = dict;
	}

	public List<Dictionaries> getSubDict()
	{
		return subDict;
	}

	public void setSubDict(List<Dictionaries> subDict)
	{
		this.subDict = subDict;
	}

	public boolean isHasDict()
	{
		return hasDict;
	}

	public void setHasDict(boolean hasDict)
	{
		this.hasDict = hasDict;
	}

	public String getTreeurl()
	{
		return treeurl;
	}

	public void setTreeurl(String treeurl)
	{
		this.treeurl = treeurl;
	}

}
