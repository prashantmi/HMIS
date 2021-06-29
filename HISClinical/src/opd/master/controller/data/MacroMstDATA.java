package opd.master.controller.data;

import hisglobal.vo.MacroMasterVO;
import hisglobal.vo.UserVO;

import opd.bo.delegate.OpdMasterDelegate;

public class MacroMstDATA
{
	public static MacroMasterVO getProcessName(String processID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		MacroMasterVO _macroMstVO = masterDelegate.getProcessName(processID,_UserVO);
		return _macroMstVO;
	}
	
	public static boolean saveMacroInfo(MacroMasterVO _macroMasterVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveMacroInfo(_macroMasterVO, _UserVO);
		return hasFlag;
	}

	public static MacroMasterVO fetchMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.fetchMacroInfo(_macroMstVO, _UserVO));
	}


	public static void saveModMacroInfo(MacroMasterVO _macroMstVo, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveModMacroInfo(_macroMstVo, _UserVO);
	}
}
