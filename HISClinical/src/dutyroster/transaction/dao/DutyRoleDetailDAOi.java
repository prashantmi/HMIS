package dutyroster.transaction.dao;

import hisglobal.vo.DutyRoleDetailVO;
import hisglobal.vo.UserVO;

public interface DutyRoleDetailDAOi {

	public void create(DutyRoleDetailVO _dutyRoleDetailVO,UserVO _userVO);
	
	public void update(DutyRoleDetailVO _dutyRoleDetailVO,UserVO _userVO);
	
}
