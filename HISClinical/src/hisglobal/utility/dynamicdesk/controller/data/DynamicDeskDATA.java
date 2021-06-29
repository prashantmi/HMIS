package hisglobal.utility.dynamicdesk.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.dynamicdesk.bo.DynamicDeskBO;
import hisglobal.utility.dynamicdesk.delegate.DynamicDeskDelegate;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;

public class DynamicDeskDATA extends ControllerDATA
{
	//  Getting Dynamic Desk Essentials
	public static Map getDynamicDeskEssentials(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO)
	{
		DynamicDeskDelegate delegate = new DynamicDeskDelegate();
		return delegate.getDynamicDeskEssentials(_userDeskMstVO, _userVO);
	}

	//  Getting Dynamic Desk Menus
	public static DeskDetailVO[] getDynamicDeskMenus(DeskDetailVO _deskDtlVO, UserVO _userVO)
	{
		DynamicDeskDelegate delegate = new DynamicDeskDelegate();
		return delegate.getDynamicDeskMenus(_deskDtlVO, _userVO);
	}
	
	//  Getting New Dynamic Desk Essentials
	public static Map<String,Object> getNewDeskEssentials(DeskDetailVO deskDetailVO,UserVO userVO)
	{
		DynamicDeskBO serviceBO = new DynamicDeskBO();
		return serviceBO.getNewDeskEssentials(deskDetailVO, userVO);
	}

	// Get Room List Unit Wise
	public static List getRoomsByUnitCode(UserVO _userVO, String unitCode)
	{
		//OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		//List list = opdEssentialDelegate.getRoomsByUnitCode(_userVO, unitCode);
		DynamicDeskBO serviceBO = new DynamicDeskBO();
		List list = serviceBO.getRoomsByUnitCode(_userVO, unitCode);
		return list;
	}
}
