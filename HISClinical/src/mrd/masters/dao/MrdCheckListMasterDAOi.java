package mrd.masters.dao;

import hisglobal.vo.MrdCheckListVO;
import hisglobal.vo.UserVO;

public interface MrdCheckListMasterDAOi 
{
	public void creat(MrdCheckListVO checkListVO ,UserVO userVO);
	public String checkDuplicateName(MrdCheckListVO checkListVO ,UserVO userVO);
	public MrdCheckListVO getDataForModify(MrdCheckListVO checkListVO, UserVO _UserVO);
	public String checkDuplicateNameForModify(MrdCheckListVO checkListVO,UserVO userVO);
	public void updateReqPurposeMaster(MrdCheckListVO checkListVO,UserVO _UserVO);
	public void saveModMrdCheckListMaster(MrdCheckListVO checkListVO ,UserVO userVO);
	
}
