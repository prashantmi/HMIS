package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UnitDrugMstVO;
import hisglobal.vo.UserVO;

public class UnitDrugMasterData extends ControllerDATA{
	public static Map getDrugMasterEssential(UserVO userVO)
{
	OpdEssentialDelegate delegate=new OpdEssentialDelegate();
	return delegate.getDrugMasterEssential(userVO);
}

public static void saveUnitDrugDetail(List unitDrugVOLst, UserVO userVO)
{
	OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
	masterDelegate.saveUnitDrugDetail(unitDrugVOLst,userVO);
}

public static Map getUnitDrugLisyForModify(UnitDrugMstVO vo,
		UserVO userVO) {
	OpdEssentialDelegate delegate=new OpdEssentialDelegate();
	return delegate.getUnitDrugLisyForModify(vo,userVO);
	
}


public static void modifySaveUnitDrugDetail(List unitDrugVOLst,
		UserVO userVO) {
	OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
	masterDelegate.modifySaveUnitDrugDetail(unitDrugVOLst,userVO);
	
}

}
