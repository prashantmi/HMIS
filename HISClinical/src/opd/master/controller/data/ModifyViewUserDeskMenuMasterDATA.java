package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;

import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class ModifyViewUserDeskMenuMasterDATA 
{
	//* Getting User Desk Menu Master Record 
	public static UserDeskMenuMasterVO fetchUserDeskMenuVO(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getModifyViewUserDeskMenuMstVO(_UserDeskVO ,_UserVO);
	}

	//* Getting All Desk By given Desk Type
	public static List getDeskListByType(String _DeskType,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getAddUserDeskMenuMasterDeskByType(_DeskType,_UserVO);
		return lst;		
	}

	//* Updating User Desk Menu Record 
	public static void UpdateUserDeskMenuVO(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.updateUserDeskMenu(_UserDeskVO, _UserVO);
	}
	
	public static UserDeskMenuMasterVO getSeats(UserDeskMenuMasterVO VOUDMT,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getSeats(VOUDMT,_UserVO);
	}
	
	public static UserDeskMenuMasterVO getUnitName(String _deptUnitCode,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return (masterDelegate.gettingUnitName(_deptUnitCode,_UserVO));
	}
}
