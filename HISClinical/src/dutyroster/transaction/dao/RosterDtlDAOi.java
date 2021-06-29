package dutyroster.transaction.dao;

import java.util.List;

import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;

public interface RosterDtlDAOi {

	public void create(RosterDtlVO _rosterDtlVO,UserVO _userVO) ;

	public List fetch(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public List fetchAllMappedEmp(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public void update(String _rosterId,String _areaTypeCode,String _areaCode,String empList,String daysList,String _year,String _month,UserVO _userVO);

	public RosterDtlVO[] fetchDesignationWiseRoster(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchDesignationWiseRosterReport(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchAllMappedDesignationWiseRoster(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchAllMappedDesignationWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);

	public List fetchDesignationWiseDateWiseRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public List fetchAllMappedDesignationDateWiseRoster(String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchVOofDesignationWiseDateWiseRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchVOofAllMappedDesignationDateWiseRoster(String _generatedRosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchVOofAllMappeDEmpsForOtherRosterOftheMonth(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchAllMappedAreaWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchAreaWiseEmpWiseRosterReport(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,String _empId,UserVO _userVO);

	public RosterDtlVO[] fetchEmpWiseRosterReport(String _year,String _month,String empId,UserVO _userVO) ;

	public RosterDtlVO[] fetchSeperateAllMappedAreaWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO); 
	
	public RosterDtlVO[] fetchSeperateAllMappedDesignationWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public RosterDtlVO[] fetchAllRostersCategAreaWiseReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterCatg,UserVO _userVO);

	public String getGeneratedRosterId(UserVO _userVO);
	
	public int checkDuplicateDateWiseRoster(String _generatedRosterid,RosterDtlVO _rosterDtlVO,UserVO _userVO);

	public RosterDtlVO[] fetchMonthlyEmpRosterReportDateWise(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,String _fromDate,String _toDate,UserVO _userVO);
	
	public List getRosterDetail(String rosterCatId,String dutyAreaCode,UserVO _userVO);
	
	public void updateforExchange(RosterDtlVO _rosterDtlVO,UserVO _userVO);
	

}
