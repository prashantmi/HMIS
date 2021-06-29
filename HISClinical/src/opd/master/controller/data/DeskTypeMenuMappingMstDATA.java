package opd.master.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeskTypeMenuMappingVO;
import hisglobal.vo.UserVO;

public class DeskTypeMenuMappingMstDATA extends ControllerDATA
{
	public static Map getDeskTypeMenuMappingMstEssentails(DeskTypeMenuMappingVO deskTypeMenuMappingVO ,UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDeskTypeMenuMappingMstEssentails(deskTypeMenuMappingVO,_userVO);
		
	}
	
	public static void saveDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveDetail(_deskTypeMenuMappingVO,_userVO);
		
	}
	
	public static Map getModifyDetail(DeskTypeMenuMappingVO deskTypeMenuMappingVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		return opdDelegate.getModifyDetail(deskTypeMenuMappingVO,_userVO);
		
	}
	public static void saveModifyDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveModifyDetail(_deskTypeMenuMappingVO,_userVO);
		
	}
}
