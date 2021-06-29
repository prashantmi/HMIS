package inpatient.masters.dao;

import hisglobal.vo.MethodMasterVO;
import hisglobal.vo.UserVO;

public interface MethodMasterDAOi 
{
	//Inserting Record 
	public void create(MethodMasterVO methodMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateMethodName(MethodMasterVO methodMasterVO,UserVO userVO);
	
	public MethodMasterVO getDataForModify(MethodMasterVO methodMasterVO, UserVO _UserVO);
	
	public void updateMethodMaster(MethodMasterVO methodMasterVO,UserVO _UserVO);
	
	public void modifySaveMethodMaster(MethodMasterVO methodMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(MethodMasterVO methodMasterVO,UserVO userVO);

	
}
