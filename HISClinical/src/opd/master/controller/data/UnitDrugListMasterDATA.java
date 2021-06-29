package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UnitDrugListMasterVO;
import hisglobal.vo.UserVO;

public class UnitDrugListMasterDATA extends ControllerDATA
{
	public static Map getUnitDrugListMasterEssential(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getUnitDrugListMasterEssential(userVO);
	}

	public static void saveUnitDrugList(List unitDrugListVOLst, UserVO userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUnitDrugList(unitDrugListVOLst,userVO);
		
	}
	
	public static Map getUnitMacroForModify(UnitDrugListMasterVO vo, UserVO userVO) {
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getUnitMacroForModify(vo,userVO);
	}

	public static void modifySaveDrugList(List unitDrugListVOLst,
			UserVO userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveDrugList(unitDrugListVOLst,userVO);
		
	}

}
