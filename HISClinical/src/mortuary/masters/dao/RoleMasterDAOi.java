package mortuary.masters.dao;

import hisglobal.vo.MortuaryRoleMasterVO;
import hisglobal.vo.UserVO;

public interface RoleMasterDAOi 
{
	
	public void create(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateRoleName(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO);
	
	public MortuaryRoleMasterVO getDataForModify(MortuaryRoleMasterVO mortuaryRoleMasterVO, UserVO _UserVO);
	
	public void updateRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO _UserVO);
	
	public void modifySaveRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO);

	
}
