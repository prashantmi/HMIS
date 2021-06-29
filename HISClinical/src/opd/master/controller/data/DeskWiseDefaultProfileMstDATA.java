package opd.master.controller.data;

import hisglobal.vo.DeskWiseDefaultProfileMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

import opd.bo.delegate.OpdMasterDelegate;

public class DeskWiseDefaultProfileMstDATA
{
	public static List getDeskType(UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getDeskType(_UserVO));
	}
	
	public static List getDeskName(String deskTypeID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getDeskName(deskTypeID,_UserVO));
	}
	
	public static List getMenuName(String deskID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getMenuName(deskID,_UserVO));
	}
	public static List getAllMenuName(String deskID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getAllMenuName(deskID,_UserVO));
	}
	
	public static List getNonDefaultMenuName(String deskID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getNonDefaultMenuName(deskID,_UserVO));
	}
	
	public static List getDefaultMenuName(String deskID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getDefaultMenuName(deskID,_UserVO));
	}
	
	public static boolean saveDefaultProfileDetails(DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveDefaultProfileDetails(_DeskWiseDefaultProfileMstVO, _UserVO);
		return hasFlag;
	}
/*
	public static DrugRouteMstVO fetchDrugRouteInfo(DrugRouteMstVO _macroMstVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.fetchDrugRouteInfo(_macroMstVO, _UserVO));
	}


	public static void saveModDrugRouteInfo(DrugRouteMstVO _macroMstVo, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveModDrugRouteInfo(_macroMstVo, _UserVO);
	}*/
}
