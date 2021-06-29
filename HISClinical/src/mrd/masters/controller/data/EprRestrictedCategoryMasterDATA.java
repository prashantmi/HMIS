package mrd.masters.controller.data;

import java.util.Map;

import mrd.masters.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EprRestrictedCategoryVO;
import hisglobal.vo.UserVO;

public class EprRestrictedCategoryMasterDATA extends ControllerDATA  
{
	public static Map getEprRestrictedCategoryEssentials(UserVO _userVO){			
		MrdEssentialDelegate masterDelegate = new MrdEssentialDelegate();
		return masterDelegate.getEprRestrictedCategoryEssentials(_userVO);		
	}

	public static void saveEprPatRestrictedCategory(
			EprRestrictedCategoryVO[] eprRestrictedCatMstVO, UserVO userVO) {
		MrdEssentialDelegate masterDelegate = new MrdEssentialDelegate();
		masterDelegate.saveEprPatRestrictedCategory(eprRestrictedCatMstVO,userVO);		
		
	}
	
	
}
