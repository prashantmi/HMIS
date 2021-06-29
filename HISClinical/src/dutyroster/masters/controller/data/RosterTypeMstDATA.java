package dutyroster.masters.controller.data;

import java.util.Map;

import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class RosterTypeMstDATA  
{
	
	public static Map getRosterTypeEssentials(UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getRosterTypeEssentials(_UserVO);
	}
	
	public static void saveRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		 masterDelegate.saveRosterType(_rosterTypeVO, _UserVO);
	}
	
	public static RosterTypeMstVO getRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getRosterType(_rosterTypeVO, _UserVO);
	}
	
	
	
	public static void modifyRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		 masterDelegate.modifyRosterType(_rosterTypeVO, _UserVO);
	}
	
	
	
	
		
}
