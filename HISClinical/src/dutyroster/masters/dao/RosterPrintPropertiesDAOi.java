package dutyroster.masters.dao;

import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.UserVO;


public interface RosterPrintPropertiesDAOi { 
	

	public void create(DutyRosterPrintPropertiesVO _rosterPrintMstVO, UserVO _UserVO);

	public DutyRosterPrintPropertiesVO[] fetch(String rosterType, UserVO _UserVO);

	public void update(String rosterType,UserVO _UserVO);
	
	public void changeDisplayOrder(DutyRosterPrintPropertiesVO _rosterPrintMstVO,UserVO _userVO);

}
