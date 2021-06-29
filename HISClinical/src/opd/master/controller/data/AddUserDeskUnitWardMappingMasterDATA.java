package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;
import java.util.Map;

import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class AddUserDeskUnitWardMappingMasterDATA 
{
	//* Getting Essentials for Adding into User Desk Unit Ward Mapping Master
	public static Map getEssentials(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		Map mp=essentialDelegate.getAddUserDeskUnitWardMappingMasterEssentials(_UserVO);
		return mp;		
	}
	
	//* Getting All Desk By given Desk Type
	public static List getDeskListByType(String _MenuType,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getAddUserDeskMenuMasterDeskByType(_MenuType,_UserVO);
		return lst;		
	}
	
	public static List getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(_deskType,userVO); 
	}
	
	public static List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO userVO,String UnitId)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(_deskType,userVO,UnitId); 
	}
	public static List getAllWardInUnitWardSeatMode(String _deskType,UserVO userVO,String UnitId)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllWardInUnitWardSeatMode(_deskType,userVO,UnitId); 
	}
	
	//* Getting Seats not assigned to given Wards
	public static List getSeatsByNotWards(String[] _WardsList,String deskType,UserVO _UserVO, String groupCode)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getSeatsByNotWards(_WardsList,deskType,_UserVO,groupCode);
		return lst;		
	}
	
	public static List getAllGroupList(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllGroupList(userVO); 
	}
	//* Saving Unit Wise 
	public static void AddDesktoUnitWise(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUserDeskUnitWardMappingMaster(_UserDeskUnitWardMappingVO, _UserVO);
	}
	
	//* Saving both type Unit Ward and Unit Ward Seat Wise
	public static void AddDesktoUnitWardWise(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUserDeskUnitWardMappingMasterUnitWardWise(_UserDeskUnitWardMappingVO, _UserVO);
	}
	
	//* Getting Desk type List
	public static List getDeskType(UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskType(_userVO);
	}
	
	
	}
