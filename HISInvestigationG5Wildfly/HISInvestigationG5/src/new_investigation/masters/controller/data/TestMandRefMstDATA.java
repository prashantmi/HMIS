package new_investigation.masters.controller.data;


import java.util.List;
import java.util.Map;

import new_investigation.vo.TestMandRefMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class TestMandRefMstDATA
{
	public static void saveTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveTestMandRef(lstTestMandRefVO, _UserVO);
	}

	public static Map fetchCheckListTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListTestMandRef(testmandref_VO, _UserVO);
	}


	public static Map fetchTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestMandRef(testmandref_VO, _UserVO);
	}

	public static void savemodifyTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyTestMandRef(lstTestMandRefVO, _UserVO);
	}

	public static List<TestMandRefMasterVO> fetchdisplaydataTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchdisplaydataTestMandRef(testmandref_VO, _UserVO);
	}
	
	public static Map fetchParameterCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchParameterCombo(testmandref_VO, _UserVO);
	}
	
	public static Map fetchCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCombo(testmandref_VO, _UserVO);
	}
	public static List fetchOld(String testCode, String parameterCode, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchOld(testCode,parameterCode, _UserVO);
	}
	public static void deleteTestMandRef(List deleteList, String testCode, String parameterCode,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.deleteTestMandRef(deleteList,testCode,parameterCode, _UserVO);
	}
	
	public static List getLabSample(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getLabSample(testmandref_VO, _UserVO);
	}
	
	public static Map fetchCheckListLocalTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
	}



}
