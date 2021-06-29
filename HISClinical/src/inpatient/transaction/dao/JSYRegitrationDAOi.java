package inpatient.transaction.dao;

import hisglobal.vo.HealthWorkerDetailVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.UserVO;

public interface JSYRegitrationDAOi {

	 public void create(JSYRegitrationVO jRegitrationVO, UserVO _userVO);
	  public HealthWorkerDetailVO getHealthworkerDetailForJSYRegistration(String patCrNo, UserVO _userVO);
}
