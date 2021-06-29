package opd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class CsultyDeskLoginDATA extends ControllerDATA
{
	// Getting Casulaity Desk Essentials
	public static List getCsultyDeskEssentials(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List list = opdEssentialDelegate.csultyDeskEssentials(_userVO);
		return list;
	}

	// Getting Rooms By Unit Code
	public static List getRoomsByUnitCode(UserVO _userVO, String unitCode)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List list = opdEssentialDelegate.getRoomsByUnitCode(_userVO, unitCode);
		return list;
	}

	// Getting data for ICD diagnosis
	public static Map getICDAllEssentials(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getICDAllEssentials(_userVO); 
	}
}
