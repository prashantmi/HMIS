package new_investigation.masters.controller.data;

 
import java.util.Map;

import new_investigation.vo.TestParameterMasterVO;
import new_investigation.vo.TestNewMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class TestParameterMstDATA
{
	public static void saveTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveTestParameter(testParameterMasterVO, _UserVO);
	}

	public static Map fetchTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestParameter(testParameterMasterVO, _UserVO);
	}
	
	 
	
	public static void savemodifyTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyTestParameter(testParameterMasterVO, _UserVO);
	}
	public static Map fetchTestParameterCombos(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchParameterCombo(testParameterMasterVO, _UserVO);
	}
	
	
	public static Map  TestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.TestParameter(testParameterMasterVO, _UserVO);
	}
	

	public static void saveTestParameterMasterForm(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveTestParameterMasterForm(testParameterMasterVO, _UserVO);
	}
	
	/*public static Map ajaxUrlCombo(TestParameterMasterVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
	return masterDelegate.ajaxUrlCombo(testNewMasterVO, _UserVO);
}*/

	public static Map fetchTestParameterCombosReqform( UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchParameterComboReqform( _UserVO);
	}
	
	public static void updateTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.updateTestParameter(testParameterMasterVO, _UserVO);
	}
	
}
