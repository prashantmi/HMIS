
package dutyroster.transaction.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.DutyRoleDetailVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.BlockwiseRosterDtlVO;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.RosterReliverDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import dutyroster.masters.bo.DutyRosterEssentialBOi;
import dutyroster.transaction.bo.DutyRosterBO;
import dutyroster.transaction.bo.DutyRosterBOi;

public class DutyRosterDelegate extends Delegate {

	public DutyRosterDelegate(){
		super(new DutyRosterBO()); ///<<Setting the service provider
	  }
	
	// Function for Form EmployeeDuty  Roster  and Table HDRT_ROSTER_DTL

	public void saveEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.saveEmpDutyRoster(_rosterDtlVO, _userVO);
	}
	
	public void saveAndModifyEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode)
	{ 
	
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.saveAndModifyEmpDutyRoster(_rosterDtlVO, _userVO,  empListToBeUpdated, daysListToBeUpdated,_year,_month,_rosterId,_areaTypeCode,_areaCode);
	}
	
	
	public Map getEmployeesWithRoster(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId, String _areaId, UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getEmployeesWithRoster(_desigId,_year,_month,_areaTypeCode, _areaCode, _rosterId,_areaId, _userVO);
	}
	
	// Function for Form LocationWise Roster  and Table HDRT_BLOCKWISE_ROSTER_DTL
	
	
	public void saveLocationDutyRoster(BlockwiseRosterDtlVO[] _blockWiseRosterVO,UserVO _userVO,String modifyStatus,String startDateTimeOld,String endDateTimeOld,String fromDateCheck,String toDateCheck) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.checkDateRange(_blockWiseRosterVO, _userVO,modifyStatus, startDateTimeOld, endDateTimeOld,fromDateCheck,toDateCheck);
	}

	public Map fetchLocationRosterAreaWise(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
    	return	serviceBO.fetchLocationRosterAreaWise(_startDate,_endDate,_areaTypeCode,_areaCode, _rosterId,_userVO);
	}

	
	public void generateLocationWiseRoster(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
    	serviceBO.generateLocationWiseRoster(_startDate,_endDate,_areaTypeCode,_areaCode, _rosterId,_userVO);
	}
	
	
//  Function for Datewise Employee Duty Roster
	
	public void saveEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.saveEmpDutyRosterDateWise(_rosterDtlVO, _userVO);
	}
	
	public void saveAndModifyEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.saveAndModifyEmpDutyRosterDateWise(_rosterDtlVO, _userVO);
	}
	
	public Map getDateWiseEmployeesRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getDateWiseEmployeesRoster(_desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
	public Map getDateWiseEmployeesRosterModify(String _generatedRosterId,String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getDateWiseEmployeesRosterModify(_generatedRosterId,_desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
//Functions for the Monthwise Employee Roster Report
	
	public Map getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(_desigId,_year,_month,_areaTypeCode, _areaCode, _rosterId, _userVO,printFormat);
	}
	public Map getMonthWiseDutyRosterReportBasedOnDutyAreaAndDesignation(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getMonthWiseDutyRosterReportBasedOnDutyAreaAndDesignation(_desigId,_year,_month,_areaTypeCode, _areaCode, _rosterId, _userVO,printFormat);
	}
	
//For Generating the  employee roster
	
	public void generateEmpDutyRoster(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO)
	{ 
	
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.generateEmpDutyRoster(_rosterGenerationDtlVO, _userVO,_reliverRosterVO);
	}

//For Dropping  the  employee roster
	
	public void dropEmpDutyRoster(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String generatedRosterId)
	{ 
	
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.dropEmpDutyRoster(_year,_month,_rosterId,_areaTypeCode,_areaCode,_userVO, empListToBeUpdated,daysListToBeUpdated,generatedRosterId);
	}
	
	
	public Map getAreaWiseReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String _rosterCatg) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getAreaWiseReport(_year,_month,_areaTypeCode, _areaCode, _rosterId, _userVO, _rosterCatg);
	}


	


				///////////For reliver duty assignment/////////////////
	
	
	public  RosterWiseReliversDtlVO[] RosterShiftWiseReliversVO(String _rosterId,String _shiftId,String _selectedDate,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.RosterShiftWiseReliversVO(_rosterId,_shiftId,_selectedDate,_userVO);
	}

	public  void saveReliverOfDuty(RosterDtlVO[] _reliverEmpInsertNewVO,RosterDtlVO[] _reliverEmpCancelOldVO,RosterDtlVO[] _requestedEmpCancelOldVO,RosterDtlVO _reliverEmpModifyDayOffOldVO,RosterReliverDtlVO[] _rosterReliverDtlVO,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		 serviceBO.saveReliverOfDuty(_reliverEmpInsertNewVO,_reliverEmpCancelOldVO,_requestedEmpCancelOldVO,_reliverEmpModifyDayOffOldVO,_rosterReliverDtlVO,_userVO);
	}
	


	public void saveEmployeeRoleDetail(DutyRoleDetailVO[] _dutyRoleDetailVO,RosterDtlVO[] _insertRosterDtlVO,RosterDtlVO[] _updateRosterDtlVO,UserVO _userVO,String mode) {
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.saveEmployeeRoleDetail(_dutyRoleDetailVO,_insertRosterDtlVO,_updateRosterDtlVO,_userVO,mode);
		
	}

				
	
				///////////For Emp Duty Roster Date Range Wise/////////////////
	
	
	public void saveEmpDutyRosterDateRangeWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.saveEmpDutyRosterDateRangeWise(_rosterDtlVO, _userVO);
	}
	
	
	public void generateEmpDutyRosterDateWise(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO)
	{ 
	
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.generateEmpDutyRosterDateWise(_rosterGenerationDtlVO, _userVO);
	}

	
	public Map getMonthlyEmpRosterReportDateWise(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat,String _fromDate,String _toDate) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getMonthlyEmpRosterReportDateWise(_desigId,_year,_month,_areaTypeCode, _areaCode, _rosterId, _userVO,printFormat,_fromDate,_toDate);
	}


	public  void saveExchangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _reliverEmpCancelOldVO,RosterDtlVO _exchangeEmpCancelOldVO,RosterDtlVO _reliverEmpInsertNewVO,RosterDtlVO _exchangeEmpInsertNewVO,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		 serviceBO.saveExchangeofDuty(_exchangeDtlVO,_reliverEmpCancelOldVO,_exchangeEmpCancelOldVO,_reliverEmpInsertNewVO,_exchangeEmpInsertNewVO,_userVO);
	}
	
	
	public  void saveChangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _requestedEmpCancelOldVO,RosterDtlVO _changeEmpInsertNewVO,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		 serviceBO.saveChangeofDuty(_exchangeDtlVO,_requestedEmpCancelOldVO,_changeEmpInsertNewVO,_userVO);
	}
	
	
	public  List getRosterDetail(String rosterCatId,String dutyAreaCode,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getRosterDetail(rosterCatId,dutyAreaCode,_userVO);
	}
	

		//Functions for Emp Daily Work Report
 	
	public Map getEmpDailyWorkReport(String _rosterId,String _areaTypeCode,String _areaCode,String _empId,String _workingDate,UserVO _userVO) 
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		return serviceBO.getEmpDailyWorkReport(_rosterId,_areaTypeCode, _areaCode,_empId,_workingDate, _userVO);
	}
	
	//Function to save Reliver Employee
	public void saveEmpReliverDtl(UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO)
	{
		DutyRosterBOi serviceBO = (DutyRosterBOi) super.getServiceProvider();
		serviceBO.saveEmpReliverDtl(_userVO, _reliverRosterVO);
	}
	
}
