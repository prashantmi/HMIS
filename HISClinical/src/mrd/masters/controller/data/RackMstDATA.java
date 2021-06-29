package mrd.masters.controller.data;

import hisglobal.vo.RackMstVO;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;


public class RackMstDATA 

{
	
	public static List getBuilding(UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getBuilding(userVO);
	}
	
	public static List getItemList(UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getItemList(userVO);
	}
	
	public static List getRackType(UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getRackType(userVO);
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
	
	public static boolean saveRackDetails(RackMstVO _RackMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveRackDetails(_RackMstVO, _UserVO);
		return hasFlag;
	}
	
	public static Map fetchRackDetails(RackMstVO _RackMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return (masterDelegate.fetchRackDetails(_RackMstVO, _UserVO));
	}
	
	public static String getRackTypeName(String rackTypeId,UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		String rackTypeName  = masterDelegate.getRackTypeName(rackTypeId,_UserVO);
		return rackTypeName;
	}
	
	public static boolean modifyRackDetails(RackMstVO _RackMstVO,UserVO userVO) 
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.modifyRackDetails(_RackMstVO,userVO);
		return hasFlag;
		
	}

	public static Map getEssentialForRackMst(UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialForRackMst(userVO);
	}
	
}
