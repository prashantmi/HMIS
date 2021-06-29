package dutyroster.masters.controller.data;

import hisglobal.vo.BlockAreaMstVO;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;

public class BlockAreaMstDATA  
{
	public static Map getBlockAreaEssentials(UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getEssentialForBlockArea(_UserVO);
	}
	
	public static List getAreaCodeNotSelected(String areaType,UserVO _userVO) {
		
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getDutyAreaBasedOnDutyAreaType(areaType, _userVO);
	}
	
	public static List getAssignedAreaCode(BlockAreaMstVO blockAreaVO, UserVO _userVO){
		
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getAssignedAreaCode(blockAreaVO, _userVO);
	}

	public static void saveBlockArea(BlockAreaMstVO insertBlockAreaVO[],BlockAreaMstVO updateBlockAreaVO[],UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveBlockArea(insertBlockAreaVO, updateBlockAreaVO, _UserVO);
	}
	
	public static BlockAreaMstVO[] getBlockArea(String dutyBlockId, UserVO userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getBlockArea(dutyBlockId, userVO);
	}

	public static List getAreaCode(String dutyBlockId, UserVO userVO) {
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getAreaCode(dutyBlockId, userVO);
		
	}

	public static void saveChangeWorkPreference(BlockAreaMstVO[] updateBlockAreaVOs, UserVO userVO) {
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveChangeWorkPreference(updateBlockAreaVOs, userVO);
		
	}
	
	
		
}
