package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserVO;

public class UnitMacroMasterDATA extends ControllerDATA
{
	public static Map getUnitMacroMasterEssential(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getUnitMacroMasterEssential(userVO);
	}
	
	public static void saveUnitMacroDetail(List unitMacroTreatLst, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUnitMacroDetail(unitMacroTreatLst,userVO);
	}

	public static Map getUnitMacroForModify(UnitWiseMacroMstVO vo, UserVO userVO) {
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getUnitExtTreatForModify(vo,userVO);
	}

	public static void modifySaveUnitMacro(List unitMacroVOLst,
			UserVO userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveUnitMacro(unitMacroVOLst,userVO);
		
	}

	
}
