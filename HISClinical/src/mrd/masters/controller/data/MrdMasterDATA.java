package mrd.masters.controller.data;

import hisglobal.vo.MrdMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;

public class MrdMasterDATA 
{
	public static Map getEssentialForMrdMst(UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialForMrdMst(userVO);
	}
	
	public static List getBlockList(String buildingCode,UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getBlockList(buildingCode,userVO);
	}
	
	public static List getFloorList(String blockId,UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getFloorList(blockId,userVO);
	}
	
	public static List getRoomList(String floorId,UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getRoomList(floorId,userVO);
	}
	
	public static List getEmployeeListBasedOnDept(String deptCode,UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEmployeeListBasedOnDept(deptCode,userVO);
	}
	
	public static boolean saveMrdDetails(MrdMasterVO mrdMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveMrdDetails(mrdMstVO, _UserVO);
		return hasFlag;
	}
	
	public static Map getDataForModifyMrdMst(MrdMasterVO mrdMasterVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return(masterDelegate.getDataForModifyMrdMst(mrdMasterVO, _UserVO));
	}
	
	public static boolean saveModMrdMaster(MrdMasterVO mrdMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveModMrdMaster(mrdMstVO, _UserVO);
		return hasFlag;
	}
	
}
