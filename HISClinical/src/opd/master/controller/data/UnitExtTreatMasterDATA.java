package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UserVO;

public class UnitExtTreatMasterDATA extends ControllerDATA
{
	public static Map getExtTreatMasterEssential(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getExtTreatMasterEssential(userVO);
	}
	
	public static void saveUnitExtTreatDetail(List unitExtTreatLst, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUnitExtTreatDetail(unitExtTreatLst,userVO);
	}

	public static Map getUnitExtTreatForModify(UnitExtTreatMstVO vo,
			UserVO userVO) {
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getUnitExtTreatForModify(vo,userVO);
		
	}

	public static void modifySaveUnitExtTreat(List unitExtTreatVOLst,
			UserVO userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveUnitExtTreat(unitExtTreatVOLst,userVO);
		
	}
}
