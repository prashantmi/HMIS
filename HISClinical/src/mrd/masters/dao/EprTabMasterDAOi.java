package mrd.masters.dao;

import hisglobal.vo.UserVO;

import java.util.List;

public interface EprTabMasterDAOi 
{
	/**
	 * get the list of the tab which are default and 
	 * the list of the tab based on the user seat id
	 */
	public List selectTab(String tabType,UserVO userVO) ;
	
}
