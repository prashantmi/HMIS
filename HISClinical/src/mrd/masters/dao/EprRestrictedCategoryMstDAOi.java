package mrd.masters.dao;

import hisglobal.vo.EprRestrictedCategoryVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface EprRestrictedCategoryMstDAOi {

	public List getAddedPatientCategoryList(UserVO userVO);
	
	public List getNotAddedPatCatList(UserVO userVO);
	
	public void create(EprRestrictedCategoryVO eprRestrictedCatVO,UserVO userVO);
	
	public void updateIsValid(UserVO userVO);
	
}
