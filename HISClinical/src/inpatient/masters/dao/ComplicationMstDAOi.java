package inpatient.masters.dao;

import hisglobal.vo.ComplicationMasterVO;
import hisglobal.vo.UserVO;

public interface ComplicationMstDAOi 
{
	//Inserting Record 
	public void create(ComplicationMasterVO complicationMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateCompName(ComplicationMasterVO complicationMasterVO,UserVO userVO);
	
	public ComplicationMasterVO getDataForModify(ComplicationMasterVO complicationMasterVO, UserVO _UserVO);
	
	public void updateCompMaster(ComplicationMasterVO complicationMasterVO,UserVO _UserVO);
	
	public void modifySaveCompMaster(ComplicationMasterVO complicationMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(ComplicationMasterVO complicationMasterVO,UserVO userVO);
	
}
