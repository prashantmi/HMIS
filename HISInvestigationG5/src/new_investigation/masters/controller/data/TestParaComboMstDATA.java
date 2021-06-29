package new_investigation.masters.controller.data;


import java.util.List;
import java.util.Map;

import new_investigation.vo.TestParaComboMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class TestParaComboMstDATA
{
	public static void saveTestParaCombo(List<TestParaComboMasterVO> lstTestParaComboVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveTestParaCombo(lstTestParaComboVO, _UserVO);
	}

	public static Map fetchCheckListTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListTestParaCombo(testparacombo_VO, _UserVO);
	}


	public static Map fetchTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestParaCombo(testparacombo_VO, _UserVO);
	}

	public static void savemodifyTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyTestParaCombo(testparacombo_VO, _UserVO);
	}

	public static List<TestParaComboMasterVO> fetchdisplaydataTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchdisplaydataTestParaCombo(testparacombo_VO, _UserVO);
	}
	
	public static Map fetchParameterCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchParameterCombo(testparacombo_VO, _UserVO);
	}



}
