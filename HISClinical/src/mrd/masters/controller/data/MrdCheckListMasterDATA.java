package mrd.masters.controller.data;

import hisglobal.vo.MrdCheckListVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;

public class MrdCheckListMasterDATA 
{
	public static boolean saveMrdCheckListMstDetail(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveMrdCheckListMstDetail(mrdCheckListMstVO, _UserVO);
		return hasFlag;
	}
	
	public static Map getDataForModifyMrdCheckListMst(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return(masterDelegate.getDataForModifyMrdCheckListMst(mrdCheckListMstVO, _UserVO));
	}
	
	public static boolean saveModMrdCheckListMaster(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveModMrdCheckListMaster(mrdCheckListMstVO, _UserVO);
		return hasFlag;
	}
}
