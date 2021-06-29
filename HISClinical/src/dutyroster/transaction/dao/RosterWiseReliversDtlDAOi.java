package dutyroster.transaction.dao;

import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface RosterWiseReliversDtlDAOi {

	public void create(RosterWiseReliversDtlVO _reliverRosterVO,UserVO _userVO) ;

	public RosterWiseReliversDtlVO[] fetch(String _generatedRosterId,UserVO _userVO)  ;
	
	public void update(String _generatedRosterId,UserVO _userVO);

	public void duplicateCheckOfReliverOfEmp(RosterWiseReliversDtlVO _reliverRosterVO,UserVO _userVO)  ;

	public RosterWiseReliversDtlVO[] fetchReliverListForDutyAssignment(String _rosterId,String _shiftId,String _selectedDate,UserVO _userVO)  ;
	
}
