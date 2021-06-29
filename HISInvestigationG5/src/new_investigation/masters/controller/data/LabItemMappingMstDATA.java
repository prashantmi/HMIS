package new_investigation.masters.controller.data;


import java.util.ArrayList;
import java.util.Map;

import new_investigation.vo.LabItemMappingMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class LabItemMappingMstDATA
{
	

	
	public static Map getEssentials(LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getEssentialsForLabItemMapping(labItemMappingMasterVO, _UserVO);
	}

	
	public static Map getItemsList(LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getItemsList(labItemMappingMasterVO, _UserVO);
	}
	public static Map saveNewItemsList(LabItemMappingMasterVO labItemMappingMasterVO, ArrayList deleteList,ArrayList insertList,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.saveNewItemsList(labItemMappingMasterVO,deleteList,insertList, _UserVO);
	}
	
}
