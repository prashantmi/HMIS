package mortuary.masters.dao;

import hisglobal.vo.IncisionTypeMasterVO;
import hisglobal.vo.UserVO;

public interface IncisionTypeMasterDAOi 
{
	//Inserting Record
	public void create(IncisionTypeMasterVO mortuaryMstVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateName(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO);
		
	public IncisionTypeMasterVO getDataForModify(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO);
	
	public void updateIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO,UserVO _UserVO);
	
	public void modifySaveIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO);


}
