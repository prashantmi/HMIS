package opd.master.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;

/**
 * @author  CDAC
 */


import java.util.Map;

import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class AddModifyMenuToDeskMasterDATA 
{
	//* Getting Desk Menu Master ADD Essentials
	public static Map getEssentials(String _deskType, UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		Map mp=essentialDelegate.getAddModifyMenuToDeskMasterEssentials(_deskType,_UserVO);
		return mp;		
	}
	
	//* Saving Data to Desk Master
	public static String SaveDesk(DeskMasterVO _DskMstVO,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.saveDesk(_DskMstVO,_UserVO);		
	}	
	
	//* Saving Data to Desk Detail
	public static void SaveDeskDetail(DeskDetailVO _DskDtlVO,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveDeskDetail(_DskDtlVO,_UserVO);		
	}
	
	//* Getting Desk VO By Desk Id
	public static DeskMasterVO fetchDeskVOByDeskId(String _DeskId, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.fetchDeskVOByDeskId(_DeskId,_UserVO);		
	}
		
	// Getting Desk Detail VO Array By Desk
	public static DeskDetailVO[] getMenuListByDeskId(DeskMasterVO _voDeskMst, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getMenuListByDeskId(_voDeskMst, _UserVO);		
	}

	//* Updating Data to Desk Master
	public static void UpdateDesk(DeskMasterVO _DskMstVO,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.updateDesk(_DskMstVO,_UserVO);		
	}	

	//* Deleting All Menus From Desk By Desk Id
	public static void DeleteAllDeskMenus(String _DeskId, UserVO _UserVo)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteDeskMenus(_DeskId,_UserVo);	
	}	
	
	//* Getting Desk Type List
	public static List getDeskType(UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskType(_userVO);
	}
	
	//* Getting Desk Type Description
	public static String getDeskTypeDesc(String _deskType, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getDeskTypeDesc(_deskType,_userVO);		
	}
	//* Getting IsDefault Status for OPD Desk Master
	public static boolean getisDefault(DeskMasterVO _deskMstVO,UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getisDefault(_deskMstVO,_userVO);
	}
	
	// Saving Desk Detail
	public static void saveCompleteDeskDetail(DeskMasterVO _deskVO,List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveCompleteDeskDetail(_deskVO, _lstDeskDtl, _userVO);		
	}

	// Updating Complete Desk Detail
	public static void updateCompleteDesk(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.updateCompleteDesk(_deskVO, _lstDeskDtl, _userVO);		
	}	
}
