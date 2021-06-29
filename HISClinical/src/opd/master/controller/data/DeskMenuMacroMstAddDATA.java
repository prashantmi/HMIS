package opd.master.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.UserVO;


public class DeskMenuMacroMstAddDATA extends ControllerDATA
{
	public static List getAllUnit(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List listAllUnit=essentialDelegate.getAllUnit(_UserVO);
		return listAllUnit;
	}
	
	public static void saveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveMacroHead(_deskMenuMacroVO,_UserVO);
	}
	public static DeskMenuMacroMstVO getMacroHeadForModify(String _macroID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return (masterDelegate.getMacroHeadForModify(_macroID,_UserVO));
	}
	
	public static void modifySaveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveMacroHead(_deskMenuMacroVO,userVO);
	}
}
