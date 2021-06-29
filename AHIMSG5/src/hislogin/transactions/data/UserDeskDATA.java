/**********************************************************
 Project:	   AHIMS_G5	
 File:         UserDeskDATA.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hislogin.transactions.data;

import java.util.Map;

import usermgmt.bo.UserManagementBO;
import vo.usermgmt.UserMasterVO;
import hisglobal.vo.CommonAlertVO;


public class UserDeskDATA
{
	
	//To getAutomatic Alert Count Added by Singaravelan on 21-Nov-2014
	public CommonAlertVO[] getAllAutomaticAlertBySeatID(UserMasterVO userVO)
	{
		UserManagementBO objBusiness = new UserManagementBO();
		return objBusiness.getAllAutomaticAlertBySeatID(userVO);
	}
	
	//To getUserWiseCashCollection Anount Added by Singaravelan on 02-Jun-2015
	public Map<String, String> getUserWiseCashCollected(UserMasterVO userVO)
	{
		UserManagementBO objBusiness = new UserManagementBO();
		return objBusiness.getUserWiseCashCollected(userVO);
	}
	
	public String checkBackDateDayEnd(UserMasterVO userVO)
	{
		UserManagementBO objBusiness = new UserManagementBO();
		return objBusiness.checkBackDateDayEnd(userVO);
	}
}
