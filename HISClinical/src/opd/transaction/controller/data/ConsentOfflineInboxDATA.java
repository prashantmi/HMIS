package opd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;

public class ConsentOfflineInboxDATA extends ControllerDATA 
{
	public static Map getEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate= new OpdEssentialDelegate();
		return opdEssentialDelegate.getOfflineConsentEssentials(consentRequestVO,_userVO);
	}
	
	public static void updateStatus(ConsentRequestVO[] consentRequestVO,UserVO _userVO)
	{
		//OpdPatientDelegate opdPatientDelegate= new OpdPatientDelegate();
		//opdPatientDelegate.updateStatus(consentRequestVO,_userVO);
		
	}

}
