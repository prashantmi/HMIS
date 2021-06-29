package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LoincMstVO;

public class LoincMstDATA {
	
	public static Map fetchLoincMst(LoincMstVO loincMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLoincMst(loincMstVO, _UserVO);
	}
	public static Map fetchTestPara(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestPara(loincMstVO, _UserVO);
	}
	/*public static Map fetchUOM(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestUOM(loincMstVO, _UserVO);
	}*/
	public static Map getLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getLoinc(loincMstVO, _UserVO);
	}
	public static Map getSearch(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getSearch(loincMstVO, _UserVO);
	}
	public static void saveLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLoinc(loincMstVO, _UserVO);
	}
	public static Map fetchModifyLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyLoinc(loincMstVO, _UserVO);
	}
	public static void saveModifyLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveModifyLoinc(loincMstVO, _UserVO);
	}
}

