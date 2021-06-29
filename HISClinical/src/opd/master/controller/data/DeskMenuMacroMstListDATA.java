package opd.master.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.UserVO;

public class DeskMenuMacroMstListDATA extends ControllerDATA
{
	
	
	public static List getDeskType(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate= new OpdEssentialDelegate();
		 List deskTypeList=essentialDelegate.getDeskType(_UserVO);		
		return deskTypeList;
	}
	
	public static List getDeskMenuBasedOnDeskType(String _deskType,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lstDeskMenu=essentialDelegate.getDeskMenuBasedOnDeskType(_deskType,_UserVO);
		return lstDeskMenu;		
	}
	
	public static DeskMenuMacroMstVO[] getMacroHead(String _deskType,String _deskMenu,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		DeskMenuMacroMstVO[] arrDeskMenuMacroMstVO=essentialDelegate.getMacroHead(_deskType,_deskMenu,_UserVO);
		return arrDeskMenuMacroMstVO;
	}
	
	public static void deleteMacroHead(DeskMenuMacroMstVO _deskMenuMacroMstVO,UserVO _UserVO )
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		essentialDelegate.deleteMacroHead(_deskMenuMacroMstVO,_UserVO);
	}
}
