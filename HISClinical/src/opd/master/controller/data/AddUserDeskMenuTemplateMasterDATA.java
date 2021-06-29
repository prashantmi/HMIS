package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;
import java.util.Map;

import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class AddUserDeskMenuTemplateMasterDATA 
{
	//* Getting Desk Menu Template Master ADD Essentials
	public static Map getEssentials(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUserDeskMenuTemplateEssentialsUnitWise(_UserVO);
	}
	
	//To get list of desks during DeskWise mode
	public static Map getAllDesk(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllDeskEssentials(_UserVO);
	}

	//* Setting Source Seats List not already added a Template for Selected Units
	public static List getSeatsByInAllUnits(String[] _UnitsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempSeatsByInAllUnits(_UnitsList,_UserVO);
	}
	
	
	public static List getSeats(String[] _UnitsList,String[] _WardsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempSeats(_UnitsList,_WardsList,_UserVO);
	}
		
	//* Setting Source Seats List not already added a Template for Selected Units
	public static UserDeskMenuTemplateMasterVO getSeats(UserDeskMenuTemplateMasterVO VOUDMT,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempSeats(VOUDMT,_UserVO);
	}
	
	public static UserDeskMenuTemplateMasterVO getWards(UserDeskMenuTemplateMasterVO VOUDMT,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempWards(VOUDMT,_UserVO);
	}

	//* Getting Desk List By Seats and Units
	public static List getDesksByInAllUnitsnAllSeats(String[] _UnitsList,String[] _SeatsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempdeksInUnitsnSeats(_UnitsList,_SeatsList,_UserVO);
	}
	
	public static List getDeskListByUnitsnWards(String[] _UnitsList,String[] _WardsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempdeksInUnitsnWards(_UnitsList, _WardsList, _UserVO);
	}
	
	public static List getDeskListByUnitsnWardsSeat(String[] _UnitsList,String[] _WardsList,String[] _SeatsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempdeksInUnitsnWardsSeat(_UnitsList, _WardsList,_SeatsList, _UserVO);
	}
		
	//* Getting Desk List for which USERSEATID is null
	public static List getDesksByInAllUnits(String[] _UnitsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempdeksInUnits(_UnitsList,_UserVO);
	}

	public static List getWardsByInAllUnits(String[] _UnitsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempWardsInUnits(_UnitsList,_UserVO);
	}
	
	public static List getWardsByForWardSeatWise(String[] _UnitsList,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAddUsrDskMnuTempWardsForWardSeatWise(_UnitsList,_UserVO);
	}
	
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

	//* Getting Templates By Unit,Seat,Desk Id
	public static List getTemplatesByUnitSeatDesk(UserDeskMenuTemplateMasterVO voUDMT,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getTemplatesByUnitSeatDesk(voUDMT,_UserVO);
	}
	
	//* Getting Templates By Unit,Desk Id
	public static List getTemplatesByUnitDesk(UserDeskMenuTemplateMasterVO voUDMT,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getTemplatesByUnitDesk(voUDMT,_UserVO);
	}
	
	//* Deleting UserDeskMenuTemplate Master By Given Conditions 
	public static void deleteTemplateToDeskMenuTemplateMasterDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteTemplateToDeskMenuTemplateMasterDeskWise(_voUDMT, _UserVO);
	}
	
	public static void deleteTemplateToDeskMenuTemplateMasterUnitWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteTemplateToDeskMenuTemplateMasterUnitWardWise(_voUDMT, _UserVO);
	}
			
	//* Deleting UserDeskMenuTemplate Master By Given Conditions Unit Wise
	public static void deleteTemplateToDeskMenuTemplateMasterUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteTemplateToDeskMenuTemplateMasterUnitWise(_voUDMT, _UserVO);
	}
	
	//* Deleting UserDeskMenuTemplate Master By Given Conditions Unit Wise
	public static void deleteTemplateToDeskMenuTemplateMasterUnitSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteTemplateToDeskMenuTemplateMasterUnitSeatWise(_voUDMT, _UserVO);
	}
	
	//* Saving User Desk Menu Template Record (in modify mode)
	public static void SaveTemplateToDeskMenu(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveTemplateToDeskMenu(_voUDMT, _UserVO);
	}
	
	//* Saving User Desk Menu Template Record 
	public static void SaveTemplateToDeskMenuSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveTemplateToDeskMenuSeatWise(_voUDMT, _UserVO);
	}
	
	public static void SaveTemplateToDeskMenuWardSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveTemplateToDeskMenuWardSeatWise(_voUDMT, _UserVO);
	}
	
	public static void SaveTemplateToDeskMenuWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveTemplateToDeskMenuWardWise(_voUDMT, _UserVO);
	}
	
	//* Saving User Desk Menu Template Record Unit Wise 
	public static void SaveTemplateToDeskMenuUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveTemplateToDeskMenuUnitWise(_voUDMT, _UserVO);
	}
	
	//* Saving User Desk Menu Template Record Unit Wise 
	public static void SaveTemplateToDeskMenuDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveTemplateToDeskMenuDeskWise(_voUDMT, _UserVO);
	}
	
	//* Getting Records
	public static UserDeskMenuTemplateMasterVO getRecords(UserDeskMenuTemplateMasterVO _voUDMT,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return (masterDelegate.getRecords(_voUDMT,_UserVO));
	}
	
	public static UserDeskMenuTemplateMasterVO getRecordsForWard(UserDeskMenuTemplateMasterVO _voUDMT,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return (masterDelegate.getRecordsForWard(_voUDMT,_UserVO));
	}

	//* Getting Unit Name
	public static UserDeskMenuTemplateMasterVO getUnitName(String _deptUnitCode,UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return (essentialDelegate.getUnitName(_deptUnitCode,_UserVO));
	}

	//* Getting Desk Detail by Desk Id
	public static DeskMasterVO fetchDeskVOByDeskId(String _DeskId, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return (masterDelegate.fetchDeskVOByDeskId(_DeskId,_userVO));
	}
	
	public static List getUnitExceptTemplate(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getUnitExceptTemplate(userVO); 
	}
	
	
}
