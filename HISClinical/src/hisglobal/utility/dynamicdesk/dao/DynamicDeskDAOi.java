package hisglobal.utility.dynamicdesk.dao;

import java.util.List;

import hisglobal.utility.Entry;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

public interface DynamicDeskDAOi
{
	// Getting Dynamic Desk Essentials 
	public String getDynamicDeskEssentials(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO);

	// Getting Dynamic Desk ID 
	public String getDynamicDeskID(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO);

	//  Getting Dynamic Desk Menus
	public DeskDetailVO[] getDynamicDeskMenus(DeskDetailVO _deskDtlVO, UserVO _userVO);
	
	//  Getting Dynamic Desk All Menus
	public DeskDetailVO[] getDynamicDeskAllMenus(String _deskId, UserVO _userVO);

	// Getting Nursing Desk ID
	public String getIpdNursingDesk(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO);

	//  Getting Desk Profile Based Menus
	public List<DeskMenuMasterVO> getDeskProfileBasedMenus(String profileType,String _deskId, UserVO _userVO);

	// Getting Desk User List
	public List<UserVO> getUserList(String wardCode, UserVO _uservo);
}
