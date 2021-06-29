package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UserVO;

public class AddUnitImageDescMasterDATA extends ControllerDATA
{
	public static Map getUnitNotInTable(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate= new OpdEssentialDelegate();
		Map mp=essentialDelegate.getUnitNotInTable(userVO);
		return mp;
	}
	
	public static void saveUnitImageDesc(List unitImageDescVOLst,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		opdMasterDelegate.saveUnitImageDesc(unitImageDescVOLst,userVO);
	}
	
	public static Map getUnitImageDescForModify(UnitImageDescMasterVO vo,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate= new OpdEssentialDelegate();
		return essentialDelegate.getUnitImageDescForModify(vo,userVO);
	}
	
	public static void saveModUnitImageDesc(List unitImageDescVoLst,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		opdMasterDelegate.saveModUnitImageDesc(unitImageDescVoLst,userVO);
	}
	
}
