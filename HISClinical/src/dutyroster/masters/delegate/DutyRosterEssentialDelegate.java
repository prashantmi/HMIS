package dutyroster.masters.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import dutyroster.masters.bo.DutyRosterEssentialBO;
import dutyroster.masters.bo.DutyRosterEssentialBOi;


public class DutyRosterEssentialDelegate extends Delegate {
    
	public Map getHospitalEssentialCombo(UserVO _uservo) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return (serviceBO.getHospitalEssentialCombo(_uservo));
	}
	
	public DutyRosterEssentialDelegate(){
		super(new DutyRosterEssentialBO()); ///<<Setting the service provider
	  }
	
//////////////////////List of Roster Main Category/////////////////////////////////////
	public List getListOfRosterMainCategory(UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getListOfRosterMainCategory( _userVO);
	}
	
	
	//////////////////////List of Shift Types/////////////////////////////////////
	public List getShiftTypes(UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getShiftTypes( _userVO);
	}
	
	//--------LIST---OF---AREA--TYPE--LIST--NOT---INCLUDING---BLOCK--AND--ESTATE--BLOCK
	
	public List getDutyAreaTypeList(UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreaTypeList(_userVO);
	}

	//--------LIST---OF---AREA--TYPE--LIST----INCLUDING---BLOCK--AND--ESTATE--BLOCK		
	
	public List getAllDutyAreaTypeList(UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllDutyAreaTypeList(_userVO);
	}
	
	
	
	public List getDutyAreaBasedOnDutyAreaType(String areaTypeCode,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreaBasedOnDutyAreaType(areaTypeCode, _userVO);
	}
	
	public Map getDutyAreaAndShiftsBasedOnRosterType(String _rosterId,String areaTypeCode,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreaAndShiftsBasedOnRosterType(_rosterId,areaTypeCode, _userVO);
	}
	
	public Map getDutyAreaAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreaAndDesignationBasedOnRosterType(_rosterId, _userVO);
	}
	
	public Map getDutyAreaWithCapacityAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(_rosterId, _userVO);
	}
	
	public List getRosterIdList(UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterIdList(_userVO);
	}
	

	public List getDutyRosterCategory( UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyRosterCategory(_userVO);
	}
	
	public List getRosterAndAreaTypeList(UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterAndAreaTypeList(_userVO);
	}
	
	public Map getEmployeesBasedOnDutyAreaAndDesignation( String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmployeesBasedOnDutyAreaAndDesignation( _desigId, _areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	

	public List getRosterType(UserVO _userVO) {
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterType(_userVO);
	}

	
	public List getRosterAndAreaTypeListHavingRosterModeLocation( UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterAndAreaTypeListHavingRosterModeLocation(_userVO);
	}
	
	public Map getLocationWiseRosterEssentials( String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getLocationWiseRosterEssentials( _areaTypeCode, _areaCode, _rosterId, _userVO);
	}

////////List of Rosters on the Basis of Roster Category////////////////
	
	
	public List getDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}

////////List of EMPLOYEE TYPE MonthwiseWise Rosters on the Basis of Roster Category////////////////
	
	
	public List getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}
	
////////List of EMPLOYEE TYPE DateWise Rosters on the Basis of Roster Category////////////////
	
	
	public List getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}

	
	//list of Empwise roster category
	
	public List getEmpWiseRosterCategory( UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpWiseRosterCategory(_userVO);
	}
	
//list of Empwise roster category concatinating with the max off,max continous off etc.
	
	public List getRosterCategoryList( UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterCategoryList(_userVO);
	}
	
	//list of Locationwise roster category
	
	public List getLocationWiseRosterCategory( UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getLocationWiseRosterCategory(_userVO);
	}

//////////emp wsie report 
	
	
	public List getDesignationList( UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDesignationList(_userVO);
	}
	
	
	public List getEmpListBasedOnDesignation(String desigId,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpListBasedOnDesignation(desigId,_userVO);
	}
	
	public List getDutyAreaBasedOnEmployee(String _empId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreaBasedOnEmployee(_empId, _userVO);
	}
	public List getRosterListBasedOnAreaEmployee(String _empId,String _areaCode,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterListBasedOnAreaEmployee(_empId,_areaCode, _userVO);
	}
	
		public Map getEmpRosterWiseEmpReport(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,String _empId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpRosterWiseEmpReport(_year, _month, _rosterId, _areaTypeCode, _areaCode, _empId, _userVO);
	}
	
	public List getEmpListofSupervisior(UserVO _userVO)
	{
			DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
			return serviceBO.getEmpListofSupervisior(_userVO);
	}
		
	public Map getEmpWiseRosterReport(String _year,String _month,String empId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpWiseEmpReport(_year, _month,empId, _userVO);
	}
	
	
	public List getRosterCategoryBasedOnHospital(String hospitalCode,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterCategoryBasedOnHospital(hospitalCode,_userVO);
	}
	///////////////////////for area type report///////////////////
	
	public List getDutyAreasBasedOnRosterCategory(String rosterCategory,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreasBasedOnRosterCategory(rosterCategory,_userVO);
	}
	
	public List getRostersBasedOnDutyAreaAndRosterCatg(String rosterCategory,String _areaTypeCode,String _areaCode,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRostersBasedOnDutyAreaAndRosterCatg(rosterCategory,_areaTypeCode,_areaCode,_userVO);
	}

	public List getRosterForRoleAssignment(UserVO _userVO) {
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterForRoleAssignment(_userVO);
	}

	public Map getShiftAndAreaForRoster(String _rosterTypeID, UserVO _userVO) {
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getShiftAndAreaForRoster(_rosterTypeID,_userVO);
	}

	public Map getEmployeeMapAndRole(RosterDtlVO _rosterDtlVO,UserVO _userVO) {
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmployeeMapAndRole(_rosterDtlVO,_userVO);
	}
	
//////////////////////List of Shifts based on roster type/////////////////////////////////////
	
	public Map RosterWiseShiftsAndArea(String _rosterId,String areaTypeCode,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.RosterWiseShiftsAndArea(_rosterId,areaTypeCode, _userVO);
	}
	
	
	public List RosterWiseMappedAreas(String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.RosterWiseMappedAreas(_rosterId, _userVO);
	}
	
	public List getEmpListToBeExchanged(String _rosterCatgId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpListToBeExchanged(_rosterCatgId,_userVO);
	}
	

	public Map getNurseRoleDetail(UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getNurseRoleDetail(_userVO);
	}
	

	public List getRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getRosterCategoryBasedOnRosterMainCategory(_rosterMainCatg, _userVO);
	}
	
	public RosterDtlVO[] getEmpDutyListForExchange(String _year,String _month,String _rosterCatgId,String empId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpDutyListForExchange(_year, _month, _rosterCatgId, empId, _userVO);
	}

	public RosterDtlVO[] getEmpDutyListForChange(String _year,String _month,String _day,String _rosterCatgId,String empId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpDutyListForChange(_year, _month,_day,_rosterCatgId, empId, _userVO);
	}
	
//***********************************For Relivers************************************/////////////
	
	public List getAreaListBasedOnRosterCategory(String _rosterCatgId,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getAreaListBasedOnRosterCategory(_rosterCatgId,_userVO);
	}
	
	public Map getEmpAndShiftListBasedOnRosterCategory(String _year,String _month,String _rosterCatgId,String _areaCode,String _areaTypeCode,String _reason,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpAndShiftListBasedOnRosterCategory(_year, _month, _rosterCatgId, _areaCode,_areaTypeCode,_reason,_userVO);
	}

	//***********************************For Employee Daily Work Report************************************/////////////
	
	public List getDutyAreasBasedOnRosterType(String _rosterCategory,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDutyAreasBasedOnRosterType(_rosterCategory,_userVO);
	}
	
	
	public List getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(String _rosterId,String _areaCode,String _areaTypeCode,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaCode, _areaTypeCode, _userVO);
	}

	
//////////////////////List of Role Based  Roster Main Category/////////////////////////////////////

	public List getListOfRoleBasedRosterMainCategory(UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getListOfRoleBasedRosterMainCategory( _userVO);
	}

////////List of Role Based Roster  Category  on the Basis of Roster Main Category////////////////	
	
	public List getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg,UserVO _userVO)
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(_rosterMainCatg, _userVO);
	}
	
////////List of Role Based Rosters on the Basis of Roster Category////////////////
	
	
	public List getListOfRoleBasedRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getListOfRoleBasedRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}

//List Of All Mapped Employees Having  UserId	
	
	public List getListOfAllMappedEmployeesHavingUserId(String _rosterId,String _areaCode,String _areaTypeCode,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getListOfAllMappedEmployeesHavingUserId(_rosterId, _areaCode, _areaTypeCode, _userVO);
	}

	public List getDesignationBasedOnHospital(String hospitalCode,UserVO _userVO) 
	{
		DutyRosterEssentialBOi serviceBO = (DutyRosterEssentialBOi) super.getServiceProvider();
		return serviceBO.getDesignationBasedOnHospital(hospitalCode,_userVO);
	}
	
}


