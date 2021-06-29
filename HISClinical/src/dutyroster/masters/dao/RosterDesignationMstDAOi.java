package dutyroster.masters.dao;

import hisglobal.vo.RosterDesignationMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface RosterDesignationMstDAOi {
	
	public List getNotAssignedDesignation(String rosterTypeId,UserVO _userVO);
	
	public List getAssignedDesignation(String rosterTypeId,UserVO _userVO);
	
	public void modifyRosterDesignation(RosterDesignationMstVO rosterDesignationVo, UserVO userVO);

	public void modifyInsertRosterDesignation(RosterDesignationMstVO rosterDesignationVo,UserVO userVO);
	
}
