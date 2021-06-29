package dutyroster.masters.dao;

import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.UserVO;

public interface RosterCategoryMstDAOi { 

	public void create(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO);

	public RosterCategoryMstVO fetch(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO);

	public void update(String sRosterId,String sRosterSlno, UserVO _UserVO);

	public void createwhileUpdate(String sRosterSlno,RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO);	
	
	public int duplicateCheckInsert(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO);

	public int duplicateCheckModify(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO);	
}
