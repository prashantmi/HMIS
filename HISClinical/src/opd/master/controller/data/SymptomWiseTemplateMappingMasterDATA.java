package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.Map;

import hisglobal.vo.AllergyWiseSymptomMasterVO;
import hisglobal.vo.SymptomWiseTemplateMappingMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdMasterDelegate;

public class SymptomWiseTemplateMappingMasterDATA 
{
	/* Getting Allergy Type
	public static Map getAllergyTypeNotInAllergyWiseSymptom(UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		Map mp=masterDelegate.getAllergyTypeNotInAllergyWiseSymptom(_UserVO);
		return mp;		
	}
	
	public static void addAllergyTypeAgainstSymptom(AllergyWiseSymptomMasterVO[] _voAllergyWiseSymptom, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.addAllergyTypeAgainstSymptom(_voAllergyWiseSymptom, _UserVO);
	}
*/
	public static Map getTemplateListForSymptom(UserVO _UserVO) 
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		Map mp=masterDelegate.getTemplateListForSymptom(_UserVO);
		return mp;	
	}

	public static void addSymptomWiseTemplateList(SymptomWiseTemplateMappingMasterVO[] voSymptomWiseTemplate,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.addSymptomWiseTemplateList(voSymptomWiseTemplate, _UserVO);
	}
	
	
}
