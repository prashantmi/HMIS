package usermgmt.bo;

import java.util.List;

import vo.hissso.LoginVO;
import vo.usermgmt.MenuMasterVO;
import vo.usermgmt.UserLoginLogVO;
import vo.usermgmt.UserMasterVO;

public interface UserMgmtBOi
{
	public UserMasterVO getValidUserDetail(LoginVO voLoginUser_p);
	
	public List<MenuMasterVO> getUserAuthorizationDetail(UserMasterVO voUser);

	public boolean logUserLoginDetail(UserLoginLogVO voUserLog);
	
	public boolean logUserLogoutDetail(UserLoginLogVO voUserLog);
}
