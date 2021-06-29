package inpatient.masters.dao;

import hisglobal.vo.AbortionMethodMasterVO;
import hisglobal.vo.UserVO;

public interface AbortionMethodMasterDAOi 
{
	//Inserting Record 
	public void create(AbortionMethodMasterVO abortionMethodMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateAbortionMethodName(AbortionMethodMasterVO abortionMethodMasterVO,UserVO userVO);
	
	public AbortionMethodMasterVO getDataForModify(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO);
	
	public void updateAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO,UserVO _UserVO);
	
	public void modifySaveAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(AbortionMethodMasterVO abortionMethodMasterVO,UserVO userVO);

	
	public String getAbortionTypeName(String typeID,UserVO userVO);
	
}
