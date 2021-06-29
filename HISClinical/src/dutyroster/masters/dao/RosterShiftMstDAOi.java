package dutyroster.masters.dao;

import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface RosterShiftMstDAOi { 

	public List getRosterType(UserVO userVO) ;	
	
	public List getShiftsBasedOnRoster(String rosterId,String shiftType,UserVO userVO);
	
	public void saveRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO) ;
	
	public List getRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO); 
	
	public List getShiftsBasedOnRosterModify(String rosterId,UserVO userVO) ;
	
	public void modifyRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO);
	
	public void modifyInsertRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO);
	
	public String getRosterTypeNameById(String rosterTypeId ,UserVO userVO);
		
}
