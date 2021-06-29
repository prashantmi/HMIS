package mortuary.masters.dao;

import hisglobal.vo.MortuaryAreaMasterVO;
import hisglobal.vo.UserVO;

public interface MortuaryAreaMasterDAOi 
{
	//Inserting Record
	public void create(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateName(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO);
		
	public MortuaryAreaMasterVO getDataForModify(MortuaryAreaMasterVO _MortuaryMasterVO, UserVO _UserVO);
	
	public void updateMortuaryAreaMaster(MortuaryAreaMasterVO _MortuaryMasterVO,UserVO _UserVO);
	
	public void modifySaveMortuaryAreaMaster(MortuaryAreaMasterVO _MortuaryMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO);


}
