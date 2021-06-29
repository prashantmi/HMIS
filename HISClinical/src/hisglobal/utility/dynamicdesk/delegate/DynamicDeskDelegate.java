package hisglobal.utility.dynamicdesk.delegate;

import java.util.List;
import java.util.Map;

import hisglobal.business.Delegate;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.bo.DynamicDeskBO;
import hisglobal.utility.dynamicdesk.bo.DynamicDeskBOi;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

public class DynamicDeskDelegate extends Delegate
{
	public DynamicDeskDelegate()
	{
		super(new DynamicDeskBO());
	}
	
	//  Getting Dynamic Desk Essentials
	public Map getDynamicDeskEssentials(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO)
	{
		DynamicDeskBOi serviceBO = (DynamicDeskBOi) super.getServiceProvider();
		return (serviceBO.getDynamicDeskEssentials(_userDeskMstVO, _userVO));
	}

	//  Getting Dynamic Desk Menus
	public DeskDetailVO[] getDynamicDeskMenus(DeskDetailVO _deskDtlVO, UserVO _userVO)
	{
		DynamicDeskBOi serviceBO = (DynamicDeskBOi) super.getServiceProvider();
		return (serviceBO.getDynamicDeskMenus(_deskDtlVO, _userVO));
	}

	//  Getting Desk Profile Based Menus
	public List<DeskMenuMasterVO> getDeskProfileBasedMenus(String profileType,String _deskId, UserVO _userVO)
	{
		DynamicDeskBOi serviceBO = (DynamicDeskBOi) super.getServiceProvider();
		return (serviceBO.getDeskProfileBasedMenus(profileType,_deskId, _userVO));
	}

	// Getting Desk User List
	public List<UserVO> getUserList(UserVO _uservo, String _wardCode)
	{
		DynamicDeskBOi serviceBO=(DynamicDeskBOi) super.getServiceProvider();
		return serviceBO.getUserList(_uservo,_wardCode);
	}
}
