/**********************************************************
 Project:	   AHIMS_G5	
 File:         LoginFeaturesDATA.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hislogin.transactions.data;

import hisglobal.exceptions.HISDataAccessException;
import hisglobal.utility.Entry;

import java.util.List;

import usermgmt.bo.UserManagementBO;
import vo.hissso.LoginVO;
import vo.usermgmt.UserLoginLogVO;
import vo.usermgmt.UserMasterVO;
import vo.usermgmt.MenuMasterVO;

public class LoginFeaturesDATA
{
	public static List<Entry> getQuestionList(UserMasterVO voUser) throws HISDataAccessException
	{
		List<Entry> lstDate = null;
		try
		{
			UserManagementBO objBusiness = new UserManagementBO();
			lstDate = objBusiness.getQuestionList(voUser);
		}
		catch(Exception e)
		{
			voUser = null;
			throw new HISDataAccessException(e.getMessage());
		}
		return lstDate;
	}

	public static UserMasterVO getValidForgotDetail(LoginVO voLogin) throws HISDataAccessException
	{
		UserMasterVO voUser = null;
		try
		{
			UserManagementBO objBusiness = new UserManagementBO();
			voUser = objBusiness.validateForgotPassword(voLogin);
		}
		catch(Exception e)
		{
			voUser = null;
			throw new HISDataAccessException(e.getMessage());
		}
		return voUser;
	}

	public static UserMasterVO changeUserPasswordDetail(UserMasterVO voUser) throws HISDataAccessException
	{
		try
		{
			UserManagementBO objBusiness = new UserManagementBO();
			voUser = objBusiness.changeUserPassword(voUser);
		}
		catch(Exception e)
		{
			voUser = null;
			throw new HISDataAccessException(e.getMessage());
		}
		return voUser;
	}

	public static UserMasterVO changeUserDetail(UserMasterVO voUser) throws HISDataAccessException
	{
		try
		{
			UserManagementBO objBusiness = new UserManagementBO();
			voUser = objBusiness.changeUserDetail(voUser);
		}
		catch(Exception e)
		{
			voUser = null;
			throw new HISDataAccessException(e.getMessage());
		}
		return voUser;
	}
	
	public static UserMasterVO changeLoginUserDetails(UserMasterVO voUser,List<MenuMasterVO> lstNewFav) throws HISDataAccessException
	{
		try
		{
			for(MenuMasterVO m : lstNewFav)
		{
			System.out.println("user id in data="+m.getVarUserId());
		}
			UserManagementBO objBusiness = new UserManagementBO();
			voUser = objBusiness.changeLoginUserDetails(voUser,lstNewFav);
		}
		catch(Exception e)
		{
			voUser = null;
			throw new HISDataAccessException(e.getMessage());
		}
		return voUser;
	}
	public static List<UserLoginLogVO> getLogList(UserMasterVO voUser,int n,String frDate,String toDate) throws HISDataAccessException
	{
		List<UserLoginLogVO> lstDate = null;
		try
		{
			UserManagementBO objBusiness = new UserManagementBO();
			lstDate = objBusiness.getLogList(voUser,n,frDate,toDate);
		}
		catch(Exception e)
		{
			voUser = null;
			throw new HISDataAccessException(e.getMessage());
		}
		return lstDate;
	}
	
}
