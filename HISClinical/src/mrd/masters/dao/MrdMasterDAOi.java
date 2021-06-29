package mrd.masters.dao;

import hisglobal.vo.MrdMasterVO;
import hisglobal.vo.UserVO;

public interface MrdMasterDAOi 
{
	public void creat(MrdMasterVO mrdMasterVO ,UserVO userVO) ;
	
	public String checkMainMrdExistence(UserVO _UserVO);
	
	public MrdMasterVO getDataForModify(MrdMasterVO mrdMasterVO, UserVO _UserVO);
	
	public void saveModMrdMaster(MrdMasterVO mrdMasterVO ,UserVO userVO);
	
	public void updateMrdMaster(MrdMasterVO mrdMasterVO,UserVO _UserVO);
	
	public String checkDuplicateShortNameForModify(MrdMasterVO mrdMasterVO,UserVO userVO);
	
	public String checkDuplicateNameForModify(MrdMasterVO mrdMasterVO,UserVO userVO);
	
	public String checkDuplicateShortName(MrdMasterVO mrdMasterVO,UserVO userVO);
	
	public String checkDuplicateName(MrdMasterVO mrdMasterVO,UserVO userVO);
}
