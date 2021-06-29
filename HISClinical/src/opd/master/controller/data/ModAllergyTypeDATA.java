package opd.master.controller.data;

import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AllergyTypeVO;
import hisglobal.vo.UserVO;

public class ModAllergyTypeDATA extends ControllerDATA
{
	public static AllergyTypeVO getAllergyType(String _allergyCode,UserVO _userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		return opdMasterDelegate.getAllergyType(_allergyCode,_userVO);
	}
	
	public static boolean updateAllergyType(AllergyTypeVO _allergyTypeVO,UserVO _userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		return opdMasterDelegate.updateAllergyType(_allergyTypeVO,_userVO); 
	}
	
	public static String getAllergySensitivity(String sensitivity,UserVO _userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		return opdMasterDelegate.getAllergySensitivitydesc(sensitivity,_userVO); 
	}
}
