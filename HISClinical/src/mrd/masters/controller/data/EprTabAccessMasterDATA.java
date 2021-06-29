package mrd.masters.controller.data;

import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;

public class EprTabAccessMasterDATA {
	
	public static Map getEssentialForTabAccessMst(UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialForTabAccessMst(userVO);
		
	}

	public static Map getTabAccessPolicy(
			EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getTabAccessPolicy(eprTabAccessDtlVO,userVO);
	}

	public static void saveTabAccessPolicy(
			List<EprTabAccessDtlVO> insertTabAccessDtlVOList, List<EprTabAccessDtlVO> updateTabAccessDtlVOList, Map userTabIdMap, EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		masterDelegate.saveTabAccessPolicy(insertTabAccessDtlVOList,updateTabAccessDtlVOList,userTabIdMap,eprTabAccessDtlVO,userVO);
	}

	public static Map getUsersForTabAccess(UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getUsersForTabAccess(userVO);
	}

}
