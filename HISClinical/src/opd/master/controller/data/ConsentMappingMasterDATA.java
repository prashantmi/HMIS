package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;

import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.UserVO;

public class ConsentMappingMasterDATA extends ControllerDATA
{
	public static List getEssentails(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		List lst=opdEssDelegate.getServiceTypeList(_userVO);
		return lst;
	}
	
	public static Map getDetail(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		Map mp=opdDelegate.getDetail(consentMappingMasterVO,_userVO);
		return mp;
	}
	public static void saveDetail(ConsentMappingMasterVO[] consentMappingMasterVO,String[] serviceId,List lstServices,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveDetail(consentMappingMasterVO,serviceId,lstServices,_userVO);
		
	}
	public static Map getModifyDetail(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		return opdDelegate.getModifyDetail(consentMappingMasterVO,_userVO);
		
	}
	public static void saveModifyDetail(ConsentMappingMasterVO[] consentMappingMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveModifyDetail(consentMappingMasterVO,_userVO);
		
	}
	

}
