package dutyroster.masters.dao;

import hisglobal.vo.DutyRosterAreaEmployeeVO;

import hisglobal.vo.UserVO;



public interface DutyAreaEmployeeMstDAOi {
	
	
	
	public void create(DutyRosterAreaEmployeeVO _dutyAreaMstVO, UserVO _UserVO);

	public void update(DutyRosterAreaEmployeeVO _dutyAreaMstVO, UserVO _UserVO);

	public DutyRosterAreaEmployeeVO[] fetchVOofAllAreaEmpMapping(String _rosterId,UserVO _userVO) ;
	
}
