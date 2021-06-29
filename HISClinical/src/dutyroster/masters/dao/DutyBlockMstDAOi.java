package dutyroster.masters.dao;

import hisglobal.vo.DutyBlockMstVO;
import hisglobal.vo.UserVO;

public interface DutyBlockMstDAOi { 

	public void saveDutyBlock(DutyBlockMstVO _dutyBlockMstVO, UserVO _UserVO);

	public DutyBlockMstVO getDutyBlock(DutyBlockMstVO _dutyBlockMstVO, UserVO _UserVO);

	public void modifyDutyBlock(DutyBlockMstVO _dutyBlockMstVO, UserVO _UserVO);

	public void modifyInsertDutyBlock(DutyBlockMstVO _dutyBlockMstVO, UserVO _UserVO);	
	
	public String checkDuplicateBeforeInsert(String dutyBlockName, UserVO _UserVO);

	public String checkDuplicateBeforeModify(DutyBlockMstVO _dutyBlockMstVO, UserVO _UserVO);	
}
