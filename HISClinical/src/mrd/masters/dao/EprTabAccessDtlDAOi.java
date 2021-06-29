package mrd.masters.dao;

import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface EprTabAccessDtlDAOi {

	public List selectTabAccessDetail(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO);
	
	public List getTabAccessDtlByUnitCodePolicyType(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO);
	
	public void create(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO);

	public void updateIsValid(EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO);
	
}
