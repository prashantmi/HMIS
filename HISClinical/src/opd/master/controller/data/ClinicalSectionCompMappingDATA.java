package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.OpdEssentialBO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class ClinicalSectionCompMappingDATA extends ControllerDATA
{
	// All Desk Type List
	public static Map getCompositionType(String hospitalCode, UserVO _UserVO)
	{
		OpdEssentialBO essentialDelegate=new OpdEssentialBO();
		return essentialDelegate.getCompositionType(hospitalCode, _UserVO);
	}

	// To get list of desks during DeskWise mode
	public static Map getAllDesk(String deskTypeId,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllDeskBasedOnDeskType(deskTypeId,_UserVO);
	}

	// Getting Template Attached List for Selected Desk in Addition Mode Desk-Wise  
	public static UserDeskMenuTemplateMasterVO[] getTemplateByDeskType(String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getTemplateByDeskType(deskId, userVO);
	}

	// Getting Template Attached Unit List for Selected Desk in Addition Mode Unit-Wise
	public static Map getMappedUnits(String deskId,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getMappedUnits(deskId,_UserVO);
	}

	// Getting Template Attached List for Selected Desk & Unit in Addition Mode Unit-Wise  
	public static UserDeskMenuTemplateMasterVO[] getTemplateByDeskNUnit(String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getTemplateByDeskNUnit(unitCode,deskId,userVO);
	}
	
	// Getting Template Attached Unit List for Selected Desk in Addition Mode Unit Seat-Wise
	public static Map getMappedUnitsForUnitSeat(String deskId,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getMappedUnitsForUnitSeat(deskId,_UserVO);
	}

	// Getting Template Attached Seat List for Selected Desk & Unit in Addition Mode Unit Seat-Wise
	public static List getSeatListByUnit(String _deskId, String unitCode, UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getSeatListByUnit(_deskId, unitCode,userVO);
	}

	// Getting Template Attached List for Selected Desk, Unit & Seat in Addition Mode Unit Seat-Wise  
	public static UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNSeat(String seatId,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getTemplateByDeskUnitNSeat(seatId,unitCode,deskId,userVO);
	}

	// Getting Template Attached Unit List for Selected Desk in Addition Mode Unit Ward-Wise
	public static Map getMappedUnitsForWard(String deskId,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getMappedUnitsForWard(deskId,_UserVO);
	}

	// Getting Template Attached Ward List for Selected Desk & Unit in Addition Mode Unit Ward-Wise
	public static List getWardListByUnit(String _deskId, String unitCode, UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getWardListByUnit(_deskId, unitCode, userVO);
	}
	
	// Getting Template Attached List for Selected Desk, Unit & Ward in Addition Mode Unit Ward-Wise  
	public static UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWard(String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getTemplateByDeskUnitNWard(wardCode,unitCode,deskId,userVO);
	}
	
	// Getting Template Attached Unit List for Selected Desk in Addition Mode Unit Ward Seat-Wise
	public static Map getMappedUnitsForUnitSeatWard(String deskId,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getMappedUnitsForUnitSeatWard(deskId,_UserVO);
	}

	// Getting Template Attached Ward List for Selected Desk & Unit in Addition Mode Unit Ward Seat-Wise
	public static List getWardListByUnitForUWS(String _deskId, String unitCode, UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getWardListByUnitForUWS(_deskId, unitCode, userVO);
	}

	// Getting Template Attached Seat List for Selected Desk, Unit & Wrad in Addition Mode Unit Ward Seat-Wise
	public static List getSeatListByUnitNWard(String _deskId, String wardCode, String unitCode, UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getSeatListByUnitNWard(_deskId, wardCode, unitCode, userVO);
	}
	
	// Getting Template Attached List for Selected Desk, Unit, Ward & Seat in Addition Mode Unit Ward Seat-Wise  
	public static UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWardNSeat(String seatId,String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getTemplateByDeskUnitNWardNSeat(seatId,wardCode,unitCode,deskId,userVO);
	}

	// Getting All Template VO List  
	public static List<TemplateMasterVO> getAllTemplatesVO(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllTemplatesVO(userVO);
	}
	
	// Getting Template-Based Desk Menus for the Selected Desk 
	public static DeskMenuMasterVO[] getMenuNameBasedOnDeskId(String deskId,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getMenuNameBasedOnDeskId(deskId,userVO);
	}
	
	// Saving Template Desk Menu Mapping Records
	public static void saveForDeskId(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveForDeskId(userDeskMenuDeskVO,userVO);
	}

	// Deleting Records for Desk-Wise Mode
	public static void deleteForDeskId(String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteForDeskId(deskId,userVO);
	}

	// Deleting Records for Unit-Wise Mode
	public static void deleteForDeskNUnit(String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteForDeskNUnit(unitCode,deskId,userVO);
	}

	// Deleting Records for Unit Seat-Wise Mode
	public static void deleteForDeskUnitNSeat(String seatId,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteForDeskUnitNSeat(seatId,unitCode,deskId,userVO);
	}
	
	// Deleting Records for Unit Ward-Wise Mode
	public static void deleteForDeskUnitNWard(String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteForDeskUnitNWard(wardCode,unitCode,deskId,userVO);
	}
	
	// Deleting Records for Unit Ward Seat-Wise Mode
	public static void deleteForDeskUnitNWardNSeat(String seatId,String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteForDeskUnitNWardNSeat(seatId,wardCode,unitCode,deskId,userVO);
	}
	
	// Getting All Clinical Department & Unit List Mode Wise
	/*public static Map getDeptNUnitModeWise(String _mode, String _deskId, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeptNUnitModeWise(_mode, _deskId, _userVO);
	}*/
	public static Map getDeptNUnitModeWise(String _mappingType, String _deskId, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeptNUnitModeWise(_mappingType, _deskId, _userVO);
	}

	// Getting All Group & Seat List Mode Wise
	public static Map getGroupNSeatModeWise(String _mode, String _deskId, List<Entry> _units, List<Entry> _wards, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getGroupNSeatModeWise(_mode, _deskId, _units, _wards, _userVO); 
	}

	// Getting Ward Non Added in Unit Ward-Wise Mode
	public static List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO userVO,String UnitId)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getWardExceptAssignedByDeskType(_deskType,userVO,UnitId); 
	}
	
	// Getting Ward Non Added in Unit Ward Seat-Wise Mode
	public static List getWardExceptAssignedByDeskTypeForUnitWardSeat(String _deskType,UserVO userVO,String UnitId)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getWardExceptAssignedByDeskTypeForUnitWardSeat(_deskType,userVO,UnitId); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List getAllGroupList(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllGroupList(userVO); 
	}

	public static Map getDeptAndUnit(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeptAndUnit(_UserVO);
	}
	
	//To get list of desks during DeskWise mode
	public static Map getAllUnit(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllUnitForMapping(_UserVO);
	}
	
	//* Getting Desk Menu Template Master ADD Essentials
	public static Map getEssentials(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUserDeskMenuTemplateEssentialsUnitWise(_UserVO);
	}
	/**
	 * @param deskId
	 * @param userVO
	 * @return
	 */
	//* Getting Desk Menu that are Template-Based by Desk Id
	public static List getAllTemplateBasedDeskMenusByDeskId(String _deskId,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllTemplateBasedDeskMenusByDeskId(_deskId,_UserVO);
	}

	//* Getting Templates List
	public static List getAllTemplates(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getEntryTemplateAllTempList(_UserVO);
	}
	
	
	public static List<TemplateMasterVO> getAllTemplatesNotAdded(String deskId,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllTemplatesNotAdded(deskId,userVO);
	}
	
	public static List getDeskListByUnit(String unitCode,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskListByUnit(unitCode,userVO);
	}
	
	public static void saveForUnit(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveForUnit(userDeskMenuDeskVO,userVO);
	}
	
	
	public static Map getUnitSeatWise(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getUnitSeatWise(userVO);
	}
	
	public static List getDeskListByUnitNSeat(String seatId,String unitCode,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskListByUnitNSeat(seatId,unitCode,userVO);
	}
	
	public static Map getUnitForWardWise(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getUnitForWardWise(userVO);
	}
	
	public static Map getUnitForWardSeatWise(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getUnitForWardSeatWise(userVO);
	}
	
	public static List getDeskListByUnitNWard(String wardCode,String unitCode,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskListByUnitNWard(wardCode,unitCode,userVO);
	}
	
	public static List getDeskListByUnitNWardNSeat(String seatId,String wardCode,String unitCode,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskListByUnitNWardNSeat(seatId,wardCode,unitCode,userVO);
	}
	
	public static List getSeatsByNotUnits(String[] _WardsList,UserVO _UserVO, String groupCode)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getAddUserDeskMenuMasterSeatsByNotUnits(_WardsList,_UserVO,groupCode);
		return lst;		
	}
	
	
	
}
