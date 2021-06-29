package dutyroster.masters.bo;

import java.util.List;
import java.util.Map;

import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;


public interface DutyRosterEssentialBOi {

	public List getListOfRosterMainCategory(UserVO _userVO);	
	
	public List getShiftTypes(UserVO _userVO);	
	
	public List getDutyAreaTypeList(UserVO _userVO);
	
	public List getAllDutyAreaTypeList(UserVO _userVO);
	
	public List getDutyAreaBasedOnDutyAreaType(String areaTypeCode,UserVO _userVO);
	
	public Map getDutyAreaAndShiftsBasedOnRosterType(String _rosterId,String areaTypeCode,UserVO _userVO);
	
	public Map getDutyAreaAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO);
	
	public Map getDutyAreaWithCapacityAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO);
	
	public List getRosterIdList(UserVO _userVO);
	
	public List getRosterType(UserVO _userVO);
	
	public List getDutyRosterCategory(UserVO _userVO); 
	
	public List getRosterAndAreaTypeList(UserVO _userVO); 
	
	public Map getEmployeesBasedOnDutyAreaAndDesignation(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO); 
	
	public List getRosterAndAreaTypeListHavingRosterModeLocation(UserVO _userVO);
	
	public Map getLocationWiseRosterEssentials(String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	
////////List of Rosters on the Basis of Roster Category////////////////
	
	
	public List getDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO);

////////List of EMPLOYEE TYPE MonthwiseWise Rosters on the Basis of Roster Category////////////////
	
	
	public List getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO);
	
////////List of EMPLOYEE TYPE DateWise Rosters on the Basis of Roster Category////////////////
	
	
	public List getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO);
	
//list of Empwise roster category	
	
	public List getEmpWiseRosterCategory(UserVO _userVO);
	
	public List getRosterCategoryList(UserVO _userVO);
	
	public Map getHospitalEssentialCombo(UserVO _uservo);

//list of Locationwise roster category
	
	public List getLocationWiseRosterCategory(UserVO _userVO);


	//for generating the report of empwise emp roster report
	
	public List getDesignationList(UserVO _userVO); 
	
	public List getEmpListBasedOnDesignation(String desigId,UserVO _userVO); 
	
	public List getDutyAreaBasedOnEmployee(String _empId,UserVO _userVO);
	
	public List getRosterListBasedOnAreaEmployee(String _empId,String _areaCode,UserVO _userVO);
	
	public Map getEmpRosterWiseEmpReport(String _year,String month,String _rosterId,String _areaTypeCode,String _areaCode,String _empId,UserVO _userVO);
		
	public List getEmpListofSupervisior(UserVO _userVO);
	
	public Map getEmpWiseEmpReport(String _year,String _month,String empId,UserVO _userVO);

	public List getRosterCategoryBasedOnHospital(String hospitalCode,UserVO _userVO);
	
	public List getDutyAreasBasedOnRosterCategory(String rosterCategory,UserVO _userVO);
	
	
	public List getRostersBasedOnDutyAreaAndRosterCatg(String rosterCategory,String _areaTypeCode,String _areaCode,UserVO _userVO);

	public List getRosterForRoleAssignment(UserVO _userVO);

	public Map getShiftAndAreaForRoster(String _rosterTtypeID, UserVO _userVO);

	public Map getEmployeeMapAndRole(RosterDtlVO _rosterDtlVO,UserVO _userVO);
	

	public Map RosterWiseShiftsAndArea(String _rosterId,String areaTypeCode,UserVO _userVO);


	public List RosterWiseMappedAreas(String _rosterId,UserVO _userVO);
	
	public List getEmpListToBeExchanged(String _rosterCatgId,UserVO _userVO);
	
	public List getRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg,UserVO _userVO);
	
	public Map getNurseRoleDetail(UserVO _userVO);
	
	public RosterDtlVO[] getEmpDutyListForExchange(String _year,String _month,String _rosterCatgId,String empId,UserVO _userVO);
	
	public RosterDtlVO[] getEmpDutyListForChange(String _year,String _month,String _day,String _rosterCatgId,String empId,UserVO _userVO);

	
//********************************************FOR RELIVERS***************************************//
	
	public List getAreaListBasedOnRosterCategory(String _rosterCatgId,UserVO _userVO);
	
	
	public Map getEmpAndShiftListBasedOnRosterCategory(String _year,String _month,String _rosterCatgId,String _areaCode,String _areaTypeCode,String _reason,UserVO _userVO);
	
	
	//***********************************For Employee Daily Work Report************************************/////////////
	
	
	public List getDutyAreasBasedOnRosterType(String _rosterCategory,UserVO _userVO);
	
	public List getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(String _rosterId,String _areaCode,String _areaTypeCode,UserVO _userVO);
	
//////////////////////List of Role Based  Roster Main Category/////////////////////////////////////
	
	
	public List getListOfRoleBasedRosterMainCategory(UserVO _userVO);	

	
////////List of Role Based Roster  Category  on the Basis of Roster Main Category////////////////
	
	
	public List getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg,UserVO _userVO);


////////List of Role Based Rosters on the Basis of Roster Category////////////////
	
	
	public List getListOfRoleBasedRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO);
	
	
	public List getListOfAllMappedEmployeesHavingUserId(String _rosterId,String _areaCode,String _areaTypeCode,UserVO _userVO);	
	
	public List getDesignationBasedOnHospital(String _hospitalCode,UserVO _userVO);
}
