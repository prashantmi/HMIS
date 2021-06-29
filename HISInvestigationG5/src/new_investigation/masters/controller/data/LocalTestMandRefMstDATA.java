package new_investigation.masters.controller.data;


import java.util.List;
import java.util.Map;

import new_investigation.vo.LocalTestMandRefMasterVO;
import new_investigation.vo.TestMandRefMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class LocalTestMandRefMstDATA
{
	public static void saveLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveLocalTestMandRef(lstLocalTestMandRefVO, _UserVO);
	}

	public static Map fetchCheckListLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
	}


	public static Map fetchLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLocalTestMandRef(testmandref_VO, _UserVO);
	}

	public static void savemodifyLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyLocalTestMandRef(lstLocalTestMandRefVO, _UserVO);
	}

	public static List<LocalTestMandRefMasterVO> fetchdisplaydataLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchdisplaydataLocalTestMandRef(testmandref_VO, _UserVO);
	}
	
	public static Map fetchParameterCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchParameterCombo(testmandref_VO, _UserVO);
	}
	
	public static Map fetchCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCombo(testmandref_VO, _UserVO);
	}
	public static List fetchOldLocal(String testCode, String parameterCode, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchOldLocal(testCode,parameterCode, _UserVO);
	}
	public static void deleteLocalTestMandRef(List deleteList, String testCode, String parameterCode,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.deleteLocalTestMandRef(deleteList,testCode,parameterCode, _UserVO);
	}

	public static List getLabSample(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getLabSample(testmandref_VO, _UserVO);
	}




}
