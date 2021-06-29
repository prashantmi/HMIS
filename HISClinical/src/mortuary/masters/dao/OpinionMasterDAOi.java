package mortuary.masters.dao;

import hisglobal.vo.OpinionMasterVO;
import hisglobal.vo.UserVO;

public interface OpinionMasterDAOi 
{
	//Inserting Record 
	public void create(OpinionMasterVO opinionMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateOpinionName(OpinionMasterVO opinionMasterVO,UserVO userVO);
	
	public OpinionMasterVO getDataForModify(OpinionMasterVO opinionMasterVO, UserVO _UserVO);
	
	public void updateOpinionMaster(OpinionMasterVO opinionMasterVO,UserVO _UserVO);
	
	public void modifySaveOpinionMaster(OpinionMasterVO opinionMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(OpinionMasterVO opinionMasterVO,UserVO userVO);

	
}
