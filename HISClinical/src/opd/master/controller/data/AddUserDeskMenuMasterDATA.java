package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;
import java.util.Map;

import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.OpdEssentialBO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class AddUserDeskMenuMasterDATA 
{
	//* Getting Essentials for Adding into User Desk Menu Master
	public static Map getEssentials(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		Map mp=essentialDelegate.getAddUserDeskMenuMasterEssentials(_UserVO);
		return mp;		
	}

	//* Getting Seats not assigned to given Department Units
	//public static List getSeatsByNotUnits(String[] _UnitsList,String deskType,UserVO _UserVO, String groupCode)
	// Change By Chetan According to Global Mapping Concept
	public static List getSeatsByNotUnits(String deskType,UserVO _UserVO, String groupCode)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		//List lst=essentialDelegate.getAddUserDeskMenuMasterSeatsByNotUnits(_UnitsList,deskType,_UserVO,groupCode);
		List lst=essentialDelegate.getAddUserDeskMenuMasterSeatsByNotUnits(deskType,_UserVO,groupCode);
		return lst;		
	}

	//* Getting All Desk By given Desk Type
	public static List getDeskListByType(String _MenuType,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getAddUserDeskMenuMasterDeskByType(_MenuType,_UserVO);
		return lst;		
	}

	//* Save User Desk Menu Record 
	public static void AddDesktoSeatsUnitWise(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUserDeskMenu(_UserDeskVO, _UserVO);
	}
	
	
	public static void AddDesktoSeatsUnitWise1(UserDeskMenuMasterVO[] _UserDeskVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUserDeskMenu1(_UserDeskVO, _UserVO);
	}
	
	//* Getting Desk type List
	public static List getDeskType(UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskType(_userVO);
	}
	
	public static List getUnitExceptAssignedByDeskType(String _deskType,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getUnitExceptAssignedByDeskType(_deskType,userVO); 
	}
	
	public static List getAllGroupList(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllGroupList(userVO); 
	}
	
	public static Map getDetailsToMap(UserDeskMenuMasterVO _voDeskMapping, UserVO _voUser) 
	{
		OpdEssentialBO boEssential = new OpdEssentialBO();
		Map mp = boEssential.getAddUserDeskMenuMasterDetailsToMap(_voDeskMapping, _voUser);
		return mp;		
	}
	public static List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO userVO,String deptCode)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(_deskType,userVO,deptCode); 
	}
	
	
}
