package new_investigation.masters.controller.data;


import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabCannedMasterVO;


public class GlobalLabCannedMstDATA
{
	public static void saveGlobalLabCanned(LabCannedMasterVO[] insert_labcannedmaster_VO,LabCannedMasterVO[] delete_labcannedmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveGlobalLabCanned(insert_labcannedmaster_VO,delete_labcannedmaster_VO, _UserVO);
	}


	public static Map fetchGlobalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchGlobalLabCanned(labcannedmaster_VO, _UserVO);
	}


	public static Map getGlobalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getGlobalLabCanned(labcannedmaster_VO, _UserVO);
	}
	
}
