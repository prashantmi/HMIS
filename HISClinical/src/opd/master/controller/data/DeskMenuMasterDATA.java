package opd.master.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserVO;


public class DeskMenuMasterDATA extends ControllerDATA
{
	public static Map getDeskMenuMasterEssentails(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDeskMenuMasterEssentails(_userVO);
		
	}
	
	public static void saveDetail(DeskMenuMasterVO deskMenuMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveDetail(deskMenuMasterVO,_userVO);
		
	}
	
	public static DeskMenuMasterVO getModifyDetail(DeskMenuMasterVO deskMenuMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		return opdDelegate.getModifyDetail(deskMenuMasterVO,_userVO);
		
	}
	
	public static void saveModifyDetail(DeskMenuMasterVO deskMenuMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveModifyDetail(deskMenuMasterVO,_userVO);
		
	}
	
	
}
