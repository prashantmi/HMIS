package registration.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 19-Dec-2013
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */

import javax.servlet.http.HttpServletRequest;

import registration.bo.RegMasterBO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import vo.registration.VerificationDocVO;


public class VerificationDocMstDATA {


	public static boolean saveVerificationDocDtl(HttpServletRequest objHttpServletRequest ,VerificationDocVO verificationDocModel,	String strMode_p) 
	{

		VerificationDocVO objDocModel;
		boolean bExistStatus=false;

		try 
		{
			RegMasterBO bo = new RegMasterBO();
			objDocModel= new VerificationDocVO();			
			objDocModel=verificationDocModel;
			
			UserVO uservo = ControllerUTIL.getUserVO(objHttpServletRequest);			
			
			bExistStatus=bo.saveVerificationDocDtl(verificationDocModel, strMode_p, uservo);

			if (objDocModel.getStrMsgType()!=null && objDocModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objDocModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objDocModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objDocModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "VerificationDocMstDATA.saveVerificationDocDtl(vo) --> "
					+ e.getMessage();
			verificationDocModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);

		} finally 
		{
			objDocModel = null;
		}
		return bExistStatus;
	}
	

	
}
