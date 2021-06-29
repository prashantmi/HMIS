package dutyroster.masters.bo;

import hisglobal.vo.BlockAreaMstVO;
import hisglobal.vo.DutyBlockMstVO;
import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.DutyRosterOtherAreaMstVO;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.DutyRosterShiftTimingsMasterVO;
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.RosterDesignationMstVO;
import hisglobal.vo.RosterRoleMstVO;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface DutyRosterMasterBOi {

	// Functions for Form Roster Category Master and Table HDRT_ROSTER_CAT_MST
	
	public void saveRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO); 

	public Map fetchRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO);

	public void updateRosterCategoryInfo(String sRosterId,String sRosterSlno,RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO);


	// Function for Form Shift Type Master and Table HDRT_SHIFT_MST
	
	
	
	public void saveShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO);

	public Map fetchShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO);

	public void updateShiftTypeInfo(String shiftCode,String serialNo,DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO);

	// Function for Form Duty Area Employee Master and Table HDRT_DUTY_AREA_EMPLOYEE_MST
	
			
	public Map getRoleAndDesignation(UserVO _userVO);

	public List getLeftEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO);

	public List getRightEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO);
	
	public void saveAndModifyDutyAreaEmpInfo(DutyRosterAreaEmployeeVO[] _addDutyAreaEmpVO,DutyRosterAreaEmployeeVO[] _deleteDutyAreaEmpVO, UserVO _userVO);

	
	/* *** function for duty role master ***************** */
	
	public DutyRoleMstVO getDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO userVO);
	
	public boolean saveDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO userVO);
	
	public boolean updateDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO userVO);
	
	// function for roster type master

	public RosterTypeMstVO getRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);
	
	public void saveRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);
	
	public void modifyRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);
		
	public Map getRosterTypeEssentials(UserVO _UserVO);
	
	// function for roster shift master 
	
	public List getRosterShiftEssentials(UserVO userVO);

	public List getShiftsBasedOnRoster(String shiftId,String shiftType,UserVO _UserVO);
	
	
	public void saveRosterShift(RosterShiftMstVO rosterShiftVO[],UserVO _UserVO);
	
	
	public Map getRosterShift(RosterShiftMstVO rosterShiftVO,UserVO userVO);

	public void modifyRosterShift(RosterShiftMstVO[] insertRosterShiftVO,RosterShiftMstVO[] updateRosterShiftVO, UserVO userVO);



// Function for Form Roster Area Capacity Master and Table HDRT_ROSTERAREA_CAPACITY_MST
	
	public void saveRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO);

	public Map fetchRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO);

	public void updateRosterAreaCapacityInfo(String areaCode,RosterAreaCapacityMstVO _rosterAreaCapaMstVO, UserVO _userVO);

////////////////////////*****************************************
	

	
	// function for Roster Role Master
	
	public List getDutyRoleNotIn(String rosterTypeId,UserVO _userVO) ;
	
	public List getDutyRole(String rosterTypeId,UserVO _userVO) ;
	
	public void saveRosterRole(RosterRoleMstVO[] insertRosterRoleVO,RosterRoleMstVO[] updateRosterRoleVO, UserVO _uservo);
	
	
	// function for Roster Designation Master
	
	public List getNotAssignedDesignation(String rosterTypeId,UserVO _userVO) ;
	
	public List getAssignedDesignation(String rosterTypeId,UserVO _userVO) ;
	
	public void saveRosterDesignation(RosterDesignationMstVO[] insertRosterRoleVO,RosterDesignationMstVO[] updateRosterRoleVO, UserVO userVO);

	
	/* *** function for duty Block master ***************** */
	
	public DutyBlockMstVO getDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO userVO);
	
	public boolean saveDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO userVO);
	
	public boolean updateDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO userVO);
	
	/* ************************** function for Block Area master ***************** */

	public Map getEssentialForBlockArea(UserVO _userVO) ;
	
	public List getAssignedAreaCode(BlockAreaMstVO blockAreaVO, UserVO _userVO);

	public void saveBlockArea(BlockAreaMstVO[] insertBlockAreaVO,BlockAreaMstVO[] updateBlockAreaVO, UserVO _uservo);
	
	public BlockAreaMstVO[] getBlockArea(String dutyBlockId, UserVO userVO);

	public List getAreaCode(String dutyBlockId, UserVO userVO);

	public void modifyWorkPreference(BlockAreaMstVO [] updateBlockAreaVO, UserVO userVO);
	
	
	
// Functions for Form  Other Area Master and Table HDRT_OTHER_AREA_MST
	
	public void saveOtherAreaInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO); 

	public DutyRosterOtherAreaMstVO fetchOtherAreaInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO);

	public void updateOtherAreaInfo(String sRosterId,String sRosterSlno,DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO);

	
//Functions for Roster Print Master 
	
	
	//This function is used to fetch the last saved records for the corresponding roster type	
	public DutyRosterPrintPropertiesVO[] getRosterPrintDetailsBasedOnRosterType(String rosterType,UserVO _userVO);
	
	//This function is used to save the  records Only
	public void saveRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO);
	
	//This function is used to save new  records and modify old records 
	public void saveAndModifyRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO);
	
	
	//This function is used to changeDisplayOrder of records Only
	public void changeDisplayOrder(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO);
		
	
}
