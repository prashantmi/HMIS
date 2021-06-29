package usermgmt.bo;

import hisglobal.security.SecureHashAlgorithm;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import usermgmt.dao.UserMgmtDAO;
import vo.hissso.LoginVO;
import vo.usermgmt.MenuMasterVO;
import vo.usermgmt.UserLoginLogVO;
import vo.usermgmt.UserMasterVO;

public class UserMgmtBO implements UserMgmtBOi
{

	@Override
	public UserMasterVO getValidUserDetail(LoginVO voLoginUser_p)
	{
		UserMasterVO voUser = null;
		List<UserMasterVO> lstUserMasterVO = null;
		List<MenuMasterVO> lstMenuMasterVO =null;
		UserMgmtDAO umgmtDAO = null;
		try
		{
			voUser = new UserMasterVO();
			BeanUtils.copyProperties(voUser, voLoginUser_p);

			umgmtDAO = new UserMgmtDAO();

			lstUserMasterVO = (List<UserMasterVO>) umgmtDAO.getUserDtl(voUser);
			if (lstUserMasterVO == null) return null;

			voUser = null;
			for (UserMasterVO voUserTmp : lstUserMasterVO)
			{
				//String strHashedPassword = SecureHashAlgorithm.SHA1(voUserTmp.getVarPassword() + voLoginUser_p.getVarLoginSessionSalt());
				String strHashedPassword = SecureHashAlgorithm.SHA1(SecureHashAlgorithm.SHA1(voUserTmp.getVarUserName() +voUserTmp.getVarUserName()) + voLoginUser_p.getVarLoginSessionSalt());
				if (voLoginUser_p.getVarPassword().equals(strHashedPassword))
				{
					voUser = voUserTmp;
					break;
				}
			}
			if (voUser == null) return null;
			
			voUser.setVarPassword(null);
			voUser.getVoHospital().setVarPassword(null);
			
			//lstMenuMasterVO = umgmtDAO.getMenuMstDtl(voUser, "1");

			// Calling DAO for setting UserHospitalDtl
			// Not Required Already coming with User VO
			//voUser.setVoHospital(umgmtDAO.getUserHospitalDetail(voUser));

			// Calling DAO for setting Last User Log Detail ----
			//voUser.setVoLastUserLog(umgmtDAO.getLastUserLogDetail(voUser, "1"));

			// Calling DAO for setting Last Unlogged User Log Detail ----
			//voUser.setLstUnloggedUserLoginDtl(umgmtDAO.getUserLogDetailList(voUser, "2"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			voUser = null;
		}
		finally
		{
		}
		return voUser;
	}

	@Override
	public List<MenuMasterVO> getUserAuthorizationDetail(UserMasterVO voUser)
	{
		UserMgmtDAO umgmtDAO = null;
		List<MenuMasterVO> lstMenuMasterVO =null;
		try{
			lstMenuMasterVO = umgmtDAO.getMenuMstDtl(voUser, "1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return lstMenuMasterVO;
	}

	@Override
	public boolean logUserLoginDetail(UserLoginLogVO voUserLog_p)
	{
		return new UserMgmtDAO().logUserDetail(voUserLog_p);
	}

	@Override
	public boolean logUserLogoutDetail(UserLoginLogVO voUserLog_p)
	{
		return new UserMgmtDAO().logUserDetail(voUserLog_p);
	}
}
