package mortuary.masters.dao;

import hisglobal.vo.MortuaryMasterVO;
import hisglobal.vo.UserVO;

public interface MortuaryMasterDAOi 
{
	//Inserting Record in Mortuary Master
	public void create(MortuaryMasterVO mortuaryMstVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateName(MortuaryMasterVO mortuaryMstVO,UserVO userVO);
	
	//Checking For Duplicate Short Name
	public String checkDuplicateShortName(MortuaryMasterVO mortuaryMstVO,UserVO userVO);
	
	public MortuaryMasterVO getDataForModify(MortuaryMasterVO _MortuaryMasterVO, UserVO _UserVO);
	
	public void updateMortuaryMaster(MortuaryMasterVO _MortuaryMasterVO,UserVO _UserVO);
	
	public void modifySaveMortuaryMaster(MortuaryMasterVO _MortuaryMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(MortuaryMasterVO mortuaryMstVO,UserVO userVO);

	public String checkDuplicateShortNameForModify(MortuaryMasterVO mortuaryMstVO,UserVO userVO);
}
