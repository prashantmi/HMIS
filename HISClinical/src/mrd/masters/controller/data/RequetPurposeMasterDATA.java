package mrd.masters.controller.data;

import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;

public class RequetPurposeMasterDATA {
	
	public static Map getEssentialForReqPurposeMst(UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialForReqPurposeMst(userVO);
	}
	
	public static boolean saveReqPurposeMstDetail(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveReqPurposeMstDetail(reqPurposeMstVO, _UserVO);
		return hasFlag;
	}
	
	public static Map getDataForModifyReqPurposeMst(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return(masterDelegate.getDataForModifyReqPurposeMst(reqPurposeMstVO, _UserVO));
	}
	
	public static boolean saveModReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveModReqPurposeMaster(reqPurposeMstVO, _UserVO);
		return hasFlag;
	}

}
