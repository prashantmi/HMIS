package mrd.masters.dao;

import java.util.List;

import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.UserVO;

public interface EprTabAccessUserWiseDAOi {

	public void create(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO);
	
	public void update(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO);
	
	public List selectUsers(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO);
}
