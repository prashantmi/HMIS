package dutyroster.masters.dao;

import hisglobal.vo.DutyRosterOtherAreaMstVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.UserVO;

public interface DutyRosterOtherAreaMstDAOi { 

	public void create(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO);

	public DutyRosterOtherAreaMstVO fetch(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO);

	public void update(String sRosterId,String sRosterSlno, UserVO _UserVO);

	public void createwhileUpdate(String sRosterSlno,DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO);	
	
	public int duplicateCheckInsert(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO);

	public int duplicateCheckModify(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO);	
}
