package new_investigation.masters.controller.data;


import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabMacroMapMasterVO;


public class LabMacroGlobalMapMstDATA
{
	public static void saveLabMacroGlobalMap(LabMacroMapMasterVO[] insert_labmacromap_VO,LabMacroMapMasterVO[] delete_labmacromap_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveLabMacroGlobalMap(insert_labmacromap_VO,delete_labmacromap_VO, _UserVO);
	}


	public static Map fetchLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabMacroGlobalMap(labmacromap_VO, _UserVO);
	}


	public static Map getGlobalMacro(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getGlobalMacro(labmacromap_VO, _UserVO);
	}
	
}
