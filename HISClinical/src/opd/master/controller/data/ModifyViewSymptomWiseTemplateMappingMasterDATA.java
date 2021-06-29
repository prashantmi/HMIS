package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;
import java.util.Map;

import hisglobal.vo.AllergyWiseSymptomMasterVO;
import hisglobal.vo.SymptomWiseTemplateMappingMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdMasterDelegate;

public class ModifyViewSymptomWiseTemplateMappingMasterDATA 
{
	
	/*
	public static Map getDetails(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		Map mp=masterDelegate.getDetails(allergyWiseSymptomMasterVO,_UserVO);
		return mp;		
	}
	
	
	public static List getDetail(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getDetail(allergyWiseSymptomMasterVO,_UserVO);
				
	}
	
	public static void saveModifyDetail(AllergyWiseSymptomMasterVO[] allergyWiseSymptomMasterVO,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveAlergySymMasterModifyDetail(allergyWiseSymptomMasterVO,_UserVO);
				
	}
*/

	public static Map getDetails(SymptomWiseTemplateMappingMasterVO symptomWiseTemplateMappingMasterVO,UserVO _userVO) 
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		Map mp=masterDelegate.getDetails(symptomWiseTemplateMappingMasterVO,_userVO);
		return mp;		
	}

	public static void saveModifyDetail(SymptomWiseTemplateMappingMasterVO[] _symptomWiseTemplateMappingMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveModifyDetail(_symptomWiseTemplateMappingMasterVO,_userVO);
		
	}
	
}
