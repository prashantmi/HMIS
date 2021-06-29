package new_investigation.masters.controller.data;


import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabMacroMapMasterVO;
import new_investigation.vo.TestGroupMasterVO;


public class LabMacroLocalMapMstDATA
{
	public static void saveLabMacroLocalMap(LabMacroMapMasterVO[] insert_labmacromap_VO,LabMacroMapMasterVO[] delete_labmacromap_VO,UserVO _UserVO,LabMacroMapMasterVO[] modify_labmacromap_VO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveLabMacroLocalMap(insert_labmacromap_VO,delete_labmacromap_VO, _UserVO,modify_labmacromap_VO);
	}

	public static Map fetchLabMacroLocalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabMacroLocalMap(labmacromap_VO, _UserVO);
	}

	public static Map getLocalMacro(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getLocalMacro(labmacromap_VO, _UserVO);
	}
	/*public void fetchdisplaydataMacroLocalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchdisplaydataMacroLocalMap(labmacromap_VO, _UserVO);
	}*/
}
