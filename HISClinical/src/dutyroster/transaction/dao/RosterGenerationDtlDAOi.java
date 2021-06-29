package dutyroster.transaction.dao;

import java.util.List;

import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.UserVO;

public interface RosterGenerationDtlDAOi {

	public void create(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO);

	public int checkDateRange(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO);
	
	public RosterGenerationDtlVO[] fetch(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);

	public RosterGenerationDtlVO[] fetchForAllMappedAreas(String _year,String _month,String _areaTypeCode,String _rosterId,UserVO _userVO);
	
	public RosterGenerationDtlVO[] fetchMonthlyForAllRosters(String _year,String _month,UserVO _userVO);
	
	public RosterGenerationDtlVO[] fetchForAllRostersOfaCategoryAreaWise(String _year,String _month,String _areaCode,String _areaTypeCode,String _rosterCatg,UserVO _userVO);
		
	public void update(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);


	
}
