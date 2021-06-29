package hisglobal.utility.dynamicdesk.bo;

import java.util.List;
import java.util.Map;

import hisglobal.utility.Entry;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

public interface DynamicDeskBOi
{
	// Getting Dynamic Desk Essentials
	public Map getDynamicDeskEssentials(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO);

	//  Getting Dynamic Desk Menus
	public DeskDetailVO[] getDynamicDeskMenus(DeskDetailVO _deskDtlVO, UserVO _userVO);

	//  Getting Desk Profile Based Menus
	public List<DeskMenuMasterVO> getDeskProfileBasedMenus(String profileType,String _deskId, UserVO _userVO);

	// Getting Desk User List
	public List<UserVO> getUserList(UserVO _uservo, String _wardCode);
}
