package dutyroster.masters.dao;

import hisglobal.vo.DutyRosterShiftTimingsMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface ShiftTimingsMstDAOi {

	public void create(DutyRosterShiftTimingsMasterVO _rosterCatMstVO, UserVO _UserVO);

	public DutyRosterShiftTimingsMasterVO[] fetch(String _shiftCode, UserVO _userVO);

	public void update(String sCode,String sSlno, UserVO _UserVO);

	public void createwhileUpdate(String sSlno,DutyRosterShiftTimingsMasterVO _rosterCatMstVO, UserVO _UserVO);	
	
	

}
