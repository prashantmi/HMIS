package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.IcdSnomedMappingMasterVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;

public class IcdSnomedMappingMasterDATA extends ControllerDATA  
{
	public static Map getIcdSnomedEssentials(UserVO _userVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getIcdSnomedEssentials(_userVO);		
	}
	public static void saveIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO,
			UserVO _userVO) 
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveIcdSnomedMapping(_icdSnomedMappingMasterVO,_userVO);
	}
	
	public static Map fetchIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchIcdSnomedMapping(_icdSnomedMappingMasterVO);		
	}
	
	
	public static IcdSnomedMappingMasterVO fetchIcdSnomedModify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchIcdSnomedModify(_icdSnomedMappingMasterVO,_userVO);
	}
	
	
}