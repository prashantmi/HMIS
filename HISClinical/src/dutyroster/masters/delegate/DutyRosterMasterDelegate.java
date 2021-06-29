package dutyroster.masters.delegate;

import hisglobal.business.Delegate;
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

import dutyroster.masters.bo.DutyRosterMasterBO;
import dutyroster.masters.bo.DutyRosterMasterBOi;

public class DutyRosterMasterDelegate extends Delegate {

	public DutyRosterMasterDelegate(){
		super(new DutyRosterMasterBO()); ///<<Setting the service provider
	  }
	
	// Function for Form Roster Category Master and Table HDRT_ROSTER_CAT_MST 
	
	public void saveRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO) 
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveRosterCategoryInfo(_rosterCatMstVO, _userVO);
	}

	public Map fetchRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.fetchRosterCategoryInfo(_rosterCatMstVO, _userVO);
	}

	public void updateRosterCategoryInfo(String sRosterId,String sRosterSlno,RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.updateRosterCategoryInfo(sRosterId,sRosterSlno,_rosterCatMstVO, _userVO);
	}

	
// Function for Form Shift Type Master and Table HDRT_SHIFT_MST 
	
	

		
	public void saveShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveShiftTypeInfo(_shiftMstVO, _userVO, _shiftTimingsMstVO);
	}

	public Map fetchShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.fetchShiftTypeInfo(_shiftMstVO, _userVO);
	}

	public void updateShiftTypeInfo(String shiftCode,String serialNo,DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.updateShiftTypeInfo(shiftCode,serialNo,_shiftMstVO, _userVO, _shiftTimingsMstVO);
	}

	
	
// Function for Form Duty Area Employee Master and Table HDRT_DUTY_BLOCK_AREA_MST
	
	
	public Map getRoleAndDesignation(UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getRoleAndDesignation( _userVO);
	}
	
	public List getLeftEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getLeftEmployeesBasedOnDesignationAndArea( empDesg, _dutyAreaEmpVO, _userVO);
	}
	
	public List getRightEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getRightEmployeesBasedOnDesignationAndArea( empDesg, _dutyAreaEmpVO, _userVO);
	}
	
	public void saveAndModifyDutyAreaEmpInfo(DutyRosterAreaEmployeeVO[] _addDutyAreaEmpVO,DutyRosterAreaEmployeeVO[] _deleteDutyAreaEmpVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveAndModifyDutyAreaEmpInfo(_addDutyAreaEmpVO,_deleteDutyAreaEmpVO, _userVO);
	}

	// function for duty role master
	
	public boolean saveDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.saveDutyRole(dutyRoleMstVO, _UserVO);
	}
	
	public DutyRoleMstVO getDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getDutyRole(dutyRoleMstVO, _UserVO);
	}
	
	public boolean updateDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.updateDutyRole(dutyRoleMstVO, _UserVO);
	}
	
	// function for roster type master
	
	public RosterTypeMstVO getRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getRosterType(_rosterTypeVO, _UserVO);
	}
	
	public void saveRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		 serviceBO.saveRosterType(_rosterTypeVO, _UserVO);
	}
	
	public void modifyRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		 serviceBO.modifyRosterType(_rosterTypeVO, _UserVO);
	}
	
	public Map getRosterTypeEssentials(UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getRosterTypeEssentials(_UserVO);
	}
	
	// function for roster shift master
	
	public List getRosterShiftEssentials(UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getRosterShiftEssentials(_UserVO);
	}
	public List getShiftsBasedOnRoster(String shiftId,String shiftType,UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getShiftsBasedOnRoster(shiftId,shiftType,_UserVO);
	}
	
		
	public void saveRosterShift(RosterShiftMstVO rosterShiftVO[],UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveRosterShift(rosterShiftVO,_UserVO);
	}

	public Map getRosterShift(RosterShiftMstVO rosterShiftVO,UserVO userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getRosterShift(rosterShiftVO,userVO);
		
	}

	public void modifyRosterShift(RosterShiftMstVO[] insertRosterShiftVO,RosterShiftMstVO[] updateRosterShiftVO, UserVO userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.modifyRosterShift(insertRosterShiftVO,updateRosterShiftVO,userVO);
		
	}
	
		
	/* ***************************function for Roster Role Master****************************/
	
	public List getDutyRoleNotIn(String rosterTypeId, UserVO _userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getDutyRoleNotIn(rosterTypeId, _userVO);
	}
	
	public List getDutyRole(String rosterTypeId, UserVO _userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getDutyRole(rosterTypeId, _userVO);
	}
	
	public void saveRosterRole(RosterRoleMstVO[] insertRosterRoleVO,
			RosterRoleMstVO[] updateRosterRoleVO, UserVO _userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveRosterRole(insertRosterRoleVO,updateRosterRoleVO,_userVO);
	}
		

// Function for Form Roster Area Capacity Master and Table HDRT_ROSTERAREA_CAPACITY_MST
	
	
				
	public void saveRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveRosterAreaCapacityInfo(_rosterAreaCapMstVO, _userVO);
	}	


	public Map fetchRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.fetchRosterAreaCapacityInfo(_rosterAreaCapMstVO, _userVO);
	}	
	
	public void updateRosterAreaCapacityInfo(String areaCode,RosterAreaCapacityMstVO _rosterAreaCapaMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.updateRosterAreaCapacityInfo( areaCode,_rosterAreaCapaMstVO, _userVO);
	}	
	
	/* ***************************function for Roster Designation Master****************************/
	
	public List getNotAssignedDesignation(String rosterTypeId, UserVO _userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getNotAssignedDesignation(rosterTypeId, _userVO);
	}
	
	public List getAssignedDesignation(String rosterTypeId, UserVO _userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getAssignedDesignation(rosterTypeId, _userVO);
	}
	
	public void saveRosterDesignation(RosterDesignationMstVO[] insertRosterDesignationVO,
			RosterDesignationMstVO[] updateRosterDesignationVO, UserVO _userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveRosterDesignation(insertRosterDesignationVO,updateRosterDesignationVO,_userVO);
	}
	




	// function for duty Block master
	
	public boolean saveDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.saveDutyBlock(dutyBlockMstVO, _UserVO);
	}
	
	public DutyBlockMstVO getDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getDutyBlock(dutyBlockMstVO, _UserVO);
	}
	
	public boolean updateDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO _UserVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.updateDutyBlock(dutyBlockMstVO, _UserVO);

	}
	
	// function for block area master
	
	public Map getEssentialForBlockArea(UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getEssentialForBlockArea(_userVO);
	}
	
	public List getAssignedAreaCode(BlockAreaMstVO blockAreaVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getAssignedAreaCode(blockAreaVO, _userVO);
	}

	public void saveBlockArea(BlockAreaMstVO[] insertBlockAreaVO,BlockAreaMstVO[] updateBlockAreaVO, UserVO _userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveBlockArea(insertBlockAreaVO, updateBlockAreaVO, _userVO);
		
	}
	
	public BlockAreaMstVO[] getBlockArea(String dutyBlockId, UserVO userVO){
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getBlockArea(dutyBlockId, userVO);
	}

	public List getAreaCode(String dutyBlockId, UserVO userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getAreaCode(dutyBlockId, userVO);
	}

	public void saveChangeWorkPreference(BlockAreaMstVO[] updateBlockAreaVOs,UserVO userVO) {
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.modifyWorkPreference(updateBlockAreaVOs, userVO);
	}
	
	// Function for Form Other Area Master and Table HDRT_OTHER_AREA_MST
	
	public void saveOtherAreaInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO) 
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveOtherAreaInfo(_otherAreaMstVO, _userVO);
	}

	public DutyRosterOtherAreaMstVO fetchOtherAreaInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.fetchOtherAreaInfo(_otherAreaMstVO, _userVO);
	}

	public void updateOtherAreaInfo(String sRosterId,String sRosterSlno,DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.updateOtherAreaInfo(sRosterId,sRosterSlno,_otherAreaMstVO, _userVO);
	}
	
	//Functions for Roster Print Master  
	
	public DutyRosterPrintPropertiesVO[] getRosterPrintDetailsBasedOnRosterType(String rosterType,UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		return serviceBO.getRosterPrintDetailsBasedOnRosterType(rosterType, _userVO);
	}
	
	public void saveRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveRosterPrintMstInfo(_rosterPrintVO, _userVO);
	}
	
	public void saveAndModifyRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.saveAndModifyRosterPrintMstInfo(_rosterPrintVO, _userVO);
	}
	
	public void changeDisplayOrder(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO)
	{
		DutyRosterMasterBOi serviceBO = (DutyRosterMasterBOi) super.getServiceProvider();
		serviceBO.changeDisplayOrder(_rosterPrintVO, _userVO);
	}
	
	
	
}
