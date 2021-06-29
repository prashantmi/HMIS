package registration.dao;

import hisglobal.vo.MlcVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.UserVO;

public interface PoliceVerificationDAOi 
{
	public void create(PoliceVerificationDtlVO policeVerDtlVO,UserVO userVO);
	
	public PoliceVerificationDtlVO select(MlcVO mlcVO,UserVO userVO);
	
}
