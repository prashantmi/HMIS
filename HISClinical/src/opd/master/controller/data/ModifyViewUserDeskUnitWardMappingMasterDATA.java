package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;

import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdEssentialDelegate;

public class ModifyViewUserDeskUnitWardMappingMasterDATA 
{
	//* Getting User Desk Unit Ward Mapping Master Record 
	public static UserDeskUnitWardMappingMasterVO fetchUserDeskMenuVO(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getModifyViewUserDeskUnitWardMappingMstVO(_UserDeskVO ,_UserVO);
	}

	//* Getting All Desk By given Desk Type
	public static List getDeskListByType(String _DeskType,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getAddUserDeskMenuMasterDeskByType(_DeskType,_UserVO);
		return lst;		
	}

	//* Updating User Desk Menu Record 
	public static void UpdateUserDeskMenuVO(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		essentialDelegate.updateDeskUnitWardMappingMaster(_UserDeskVO, _UserVO);
	}
	
	public static UserDeskUnitWardMappingMasterVO gettingWards(UserDeskUnitWardMappingMasterVO VOUDMT,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.gettingWards(VOUDMT,_UserVO);
	}
	
	public static UserDeskUnitWardMappingMasterVO gettingSeats(UserDeskUnitWardMappingMasterVO VOUDMT,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.gettingSeats(VOUDMT,_UserVO);
	}
}
