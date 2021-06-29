package opd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.UserVO;

public class ConsentRequestDATA extends ControllerDATA 
{
	public static Map getEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate= new OpdEssentialDelegate();
		return opdEssentialDelegate.getEssentials(consentRequestVO,_userVO);
	}
	
	public static void updateStatus(List consentRequestVO,UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate= new OpdPatientDelegate();
		opdPatientDelegate.updateStatus(consentRequestVO,_userVO);
		
	}
}
