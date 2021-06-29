package dutyroster.transaction.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.DutyRoleDetailVO;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.RosterReliverDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.BlockwiseRosterDtlVO;

public interface DutyRosterBOi {

	// Function for Form EmployeeDuty  Roster  and Table HDRT_ROSTER_DTL
	
	public void saveEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO);
	
	public void saveAndModifyEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode);
		
	public Map getEmployeesWithRoster(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId, String _areaId, UserVO _userVO);

	
	
	// Function for Form LocationWise Roster  and Table HDRT_BLOCKWISE_ROSTER_DTL
	
	public void checkDateRange(BlockwiseRosterDtlVO[] _blockWiseRosterVO,UserVO _userVO,String modifyStatus,String startDateTimeOld,String endDateTimeOld,String fromDateCheck,String toDateCheck);
	
	
	public void saveLocationDutyRoster(BlockwiseRosterDtlVO[] _blockWiseRosterVO,UserVO _userVO,JDBCTransactionContext tx);
	
	
	public Map fetchLocationRosterAreaWise(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);

	
	public void generateLocationWiseRoster(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	
	//Function for Date wise Emp Roster
	
	public void saveEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO);
	
	public void saveAndModifyEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO);	
	
	public Map getDateWiseEmployeesRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);

	public Map getDateWiseEmployeesRosterModify(String _generatedRosterId,String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	
	//Function for generating the Monthwise Employee Roster Report
	
	public Map getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat);
	
	public Map getMonthWiseDutyRosterReportBasedOnDutyAreaAndDesignation(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat);
	
	
	public void generateEmpDutyRoster(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO);
	
	public void dropEmpDutyRoster(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String generatedRosterId);
	
	
	public Map getAreaWiseReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String _rosterCatg);


	public void saveEmployeeRoleDetail(DutyRoleDetailVO[] _dutyRoleDetailVO,RosterDtlVO[] _insertRosterDtlVO,RosterDtlVO[] _updateRosterDtlVO,UserVO _userVO,String mode);


	
	
///////////For reliver duty assignment/////////////////
	
	public RosterWiseReliversDtlVO[] RosterShiftWiseReliversVO(String _rosterId,String _shiftId,String _selectedDate,UserVO _userVO);
	
	
	public void saveReliverOfDuty(RosterDtlVO[] _reliverEmpInsertNewVO,RosterDtlVO[] _reliverEmpCancelOldVO,RosterDtlVO[] _requestedEmpCancelOldVO,RosterDtlVO _reliverEmpModifyDayOffOldVO,RosterReliverDtlVO[] _rosterReliverDtlVO,UserVO _userVO);
	

	///////////For Emp Duty Roster Date Range Wise/////////////////
	
	public void saveEmpDutyRosterDateRangeWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO);
	

	public void generateEmpDutyRosterDateWise(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO);
	
	public Map getMonthlyEmpRosterReportDateWise(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat,String _fromDate,String _toDate);


	public void saveExchangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _reliverEmpCancelOldVO,RosterDtlVO _exchangeEmpCancelOldVO,RosterDtlVO _reliverEmpInsertNewVO,RosterDtlVO _exchangeEmpInsertNewVO,UserVO _userVO);
	
	
	public void saveChangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _requestedEmpCancelOldVO,RosterDtlVO _changeEmpInsertNewVO,UserVO _userVO);
	
	public List getRosterDetail(String rosterCatId,String dutyAreaCode,UserVO _userVO);
	
	
	//Functions for Emp Daily Work Report
	
	public Map getEmpDailyWorkReport(String _rosterId,String _areaTypeCode,String _areaCode,String _empId,String _workingDate,UserVO _userVO);
	
	//Function to save Reliver Detail
	
	public void saveEmpReliverDtl(UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO);
}

