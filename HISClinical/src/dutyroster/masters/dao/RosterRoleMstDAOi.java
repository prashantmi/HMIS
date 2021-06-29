package dutyroster.masters.dao;

import hisglobal.vo.RosterRoleMstVO;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface RosterRoleMstDAOi {
	
	public List getDutyRoleNotIn(String rosterTypeId,UserVO _userVO);
	
	public List getAllDutyRole(String rosterTypeId,UserVO _userVO);
	
	public void modifyRosterRole(RosterRoleMstVO rosterRoleVo, UserVO userVO);

	public void modifyInsertRosterRole(RosterRoleMstVO rosterRoleVo,UserVO userVO);
	
}
