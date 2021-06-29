package dutyroster.masters.dao;

import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;

public interface EssentialDAOi {
	
	//--------LIST---OF---AREA--TYPE--LIST--NOT---INCLUDING---BLOCK--AND--ESTATE--BLOCK
	
	public List getDutyAreaTypesList(UserVO _userVO);
	
	
	//--------LIST---OF---AREA--TYPE--LIST----INCLUDING---BLOCK--AND--ESTATE--BLOCK	
	
	public List getAllDutyAreaTypesList(UserVO _userVO);

	public List getDutyRoleList(UserVO _userVO);
	
	public List getDutyAreaBasedOnDutyAreaType(String areaTypeCode, UserVO _userVO);
	
	public List getDutyAreaBasedOnRosterType(String _rosterId, UserVO _userVO);
	
	public List getEmpDetailsUnselected(String _empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO);
	
	public List getEmpDetailsSelected(String _empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO);

	public List getDutyBlockList(UserVO userVO);

	public List getRosterIdList(UserVO _userVO);
	
	public List getShiftTypesList( UserVO _UserVO);
	
	public List getDayOffShiftTypeList( UserVO _UserVO);

	public String getShiftTypeCodeAndTime(String _hCode,String _sTypeCode); 
	
	public List getDutyRosterCategory(UserVO _userVO);
	
	public List getRosterAndAreaTypeList(UserVO _userVO);
	
	public List getEmployeesBasedOnDutyAreaAndDesignation(String _desigId,String _areaTypeCode,String _areaCode,UserVO _userVO);
	
	public List getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO); 
	
	public List getShiftListBasedOnRosterType(String _rosterId,UserVO _userVO);
	
	public List getRosterType(UserVO _userVO);
	
	public List getRosterAndAreaTypeListHavingRosterModeLocation(UserVO _userVO);
	
	public List getAreaBasedOnBlockId(String _areaCode,UserVO _userVO);
	
	public List getDesignationBasedOnRosterType(String _rosterId,UserVO _userVO); 
		
	public List getShiftTypesRosterWise(String _rosterId,UserVO _UserVO);
	
	
////////List of Rosters on the Basis of Roster Category////////////////
	
	public List getDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO);
	
////////List of Monthwise Rosters on the Basis of Roster Category////////////////
	
	public List getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO);
	
	
////////List of DateWise Rosters on the Basis of Roster Category////////////////
	
	
	public List getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO);
	

	//list of roster main category
	public List getListOfRosterMainCategory(UserVO _userVO);
	
	//list of Empwise roster category	
	public List getEmpWiseRosterCategory(UserVO _userVO);
	
	//list of Locationwise roster category
	public List getLocationWiseRosterCategory(UserVO _userVO);

	//Map of monthly gazetted holidays
	public Map getMonthlyGazettedHolidays(String _year,String _month,UserVO _userVO);
	
	//MAP OF SHIFT TIMINGS
	public Map getShiftTimingsOfShift(String _shiftId,UserVO _userVO);
	
	
	//for fetching of leave of the month for all the mapped employees  
	public RosterDtlVO[] fetchEmpLeaveDetails(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	

	public List getEmployeesMappedWithAllMappedAreasWithRosterType(String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO);


	public List getEmpListBasedOnDesignation(String desigId, UserVO _userVO);
	
	
	public List getDutyAreaBasedOnEmployee(String _empId, UserVO _userVO);
	
	
	public List getEmpListofSupervisior(UserVO _userVO);
	
	
	public List getRosterListBasedonAreaEmp(String _empId,String _areaCode, UserVO _userVO);
	
	
	public List getDutyAreasBasedOnRosterCategory(String rosterCategory,UserVO _UserVO);
	
	
	public List getRostersBasedOnDutyAreaAndRosterCatg(String rosterCategory,String _areaTypeCode,String _areaCode,UserVO _userVO);
	

	public List getDutyAreaBasedOnRosterAndDutyAreaType(String rosterId,String areaTypeCode, UserVO _userVO);

	
	public RosterAreaCapacityMstVO getRosterShiftWiseCapacity(String _rosterId,String _areaTypeCode,String _areaCode, UserVO _userVO);


	public List getEmpListToBeExchanged(String _rosterCatgId,UserVO _userVO);

	
	public RosterDtlVO[] getEmpDutyListForExchange(String _year,String _month,String _rosterCatgId,String empId,UserVO _userVO);
	
	
	public RosterDtlVO[] getEmpDutyListForChange(String _year,String _month,String _day,String _rosterCatgId,String empId,UserVO _userVO); 

	
	public List getDateRangeDutyRoleAssignmentList(String rosterTypeId,String areaCode,UserVO _userVO);
	

	public List getAreaListBasedOnRosterCategory(String _rosterCategoryId,UserVO _userVO);
	
	
	public List getEmpListBasedOnRosterCategory(String _year,String _month,String _rosterCategoryId,UserVO _userVO);
	
	
	public List getReliverEmpListBasedOnRosterCategory(String _year,String _month,String _rosterCategoryId,UserVO _userVO);
	
	
	public List getShiftListBasedOnRosterCategory(String _year,String _month,String _areaCode,String _areaTypeCode,String _rosterCategoryId,UserVO _userVO);	
}
