package inpatient.masters.dao;

import hisglobal.vo.AbortionTypeMasterVO;
import hisglobal.vo.UserVO;

public interface AbortionTypeMstDAOi 
{
	//Inserting Record 
	public void create(AbortionTypeMasterVO abortionTypeMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateAbortionTypeName(AbortionTypeMasterVO abortionTypeMasterVO,UserVO userVO);
	
	public AbortionTypeMasterVO getDataForModify(AbortionTypeMasterVO abortionTypeMasterVO, UserVO _UserVO);
	
	public void updateAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO,UserVO _UserVO);
	
	public void modifySaveAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(AbortionTypeMasterVO abortionTypeMasterVO,UserVO userVO);

	
}
