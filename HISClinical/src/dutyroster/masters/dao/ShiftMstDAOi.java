package dutyroster.masters.dao;

import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface ShiftMstDAOi {

	public void create(DutyRosterShiftMasterVO _rosterCatMstVO, UserVO _UserVO);

	public DutyRosterShiftMasterVO fetch(DutyRosterShiftMasterVO _rosterCatMstVO, UserVO _UserVO);

	public void update(String sCode,String sSlno, UserVO _UserVO);

	public void createwhileUpdate(String sSlno,DutyRosterShiftMasterVO _rosterCatMstVO, UserVO _UserVO);	
	
	public int duplicateCheckForShiftDescriptionWhileInsert(DutyRosterShiftMasterVO _rosterCatMstVO, UserVO _UserVO);

	public int duplicateCheckForShiftDescriptionWhileModify(DutyRosterShiftMasterVO _rosterCatMstVO, UserVO _UserVO);	
	
	public int duplicateCheckForShiftShortNameWhileInsert(DutyRosterShiftMasterVO _rosterCatMstVO, UserVO _UserVO);

	public int duplicateCheckForShiftShortNameWhileModify(DutyRosterShiftMasterVO _rosterCatMstVO, UserVO _UserVO);	



}
