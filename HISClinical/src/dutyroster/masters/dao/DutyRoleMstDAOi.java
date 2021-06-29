package dutyroster.masters.dao;

import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.UserVO;

public interface DutyRoleMstDAOi { 

	public void saveDutyRole(DutyRoleMstVO _dutyRoleMstVO, UserVO _UserVO);

	public DutyRoleMstVO getDutyRole(DutyRoleMstVO _dutyRoleMstVO, UserVO _UserVO);

	public void modifyDutyRole(DutyRoleMstVO _dutyRoleMstVO, UserVO _UserVO);

	public void modifyInsertDutyRole(DutyRoleMstVO _dutyRoleMstVO, UserVO _UserVO);	
	
	public void checkDuplicateBeforeInsert(String dutyRoleDesc, UserVO _UserVO);

	public void checkDuplicateBeforeModify(DutyRoleMstVO _dutyRoleMstVO, UserVO _UserVO);	
	
	public void checkDuplicateShortNameBeforeInsert(String roleShortName, UserVO _UserVO);

	public void checkDuplicateShortNameBeforeModify(DutyRoleMstVO _dutyRoleMstVO, UserVO _UserVO);	
}
