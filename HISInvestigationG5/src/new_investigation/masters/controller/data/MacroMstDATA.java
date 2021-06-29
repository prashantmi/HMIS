package new_investigation.masters.controller.data;

 
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.MacroMasterVO;

import java.util.Map;



public class MacroMstDATA
{
	public static void saveMacro(MacroMasterVO macromaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		  masterDelegate.saveMacro(macromaster_VO, _UserVO);
	}

	public static void fetchCheckListMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchCheckListMacro(macromaster_VO, _UserVO);
	}
	
	public static void fetchMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchMacro(macromaster_VO, _UserVO);
	}
	
	public static void savemodifyMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyMacro(macromaster_VO, _UserVO);
	}
}