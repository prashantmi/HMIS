package opd.master.controller.data;

import java.util.Map;

import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class DrugRouteMstDATA
{
	public static DrugRouteMstVO getItemName(String itemID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		DrugRouteMstVO _drugRouteMstVO = masterDelegate.getItemName(itemID,_UserVO);
		return _drugRouteMstVO;
	}
	
	
	public static boolean saveDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveDrugRouteInfo(_drugRouteMstVO, _UserVO);
		return hasFlag;
	}

	public static DrugRouteMstVO fetchDrugRouteInfo(DrugRouteMstVO _macroMstVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.fetchDrugRouteInfo(_macroMstVO, _UserVO));
	}
	
	public static DrugRouteMstVO getDrugRouteInfo(DrugRouteMstVO _macroMstVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getDrugRouteInfo(_macroMstVO, _UserVO));
	}
	


	public static void saveModDrugRouteInfo(DrugRouteMstVO _macroMstVo, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveModDrugRouteInfo(_macroMstVo, _UserVO);
	}
	public static Map getDrugRouteMasterEssentails(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDrugRouteMasterEssentails(_userVO);
		
	}

}
