package new_investigation.masters.controller.data;


import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabCannedMasterVO;



public class LocalLabCannedMstDATA
{
	public static void saveLocalLabCanned(LabCannedMasterVO[] insert_labcannedmaster_VO,LabCannedMasterVO[] delete_labcannedmaster_VO,UserVO _UserVO,LabCannedMasterVO[] modify_labcannedmaster_VO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveLocalLabCanned(insert_labcannedmaster_VO,delete_labcannedmaster_VO, _UserVO,modify_labcannedmaster_VO);
	}

	public static Map fetchLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLocalLabCanned(labcannedmaster_VO, _UserVO);
	}

	public static Map getLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getLocalLabCanned(labcannedmaster_VO, _UserVO);
	}
	/*public void fetchdisplaydataLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchdisplaydataLocalLabCanned(labcannedmaster_VO, _UserVO);
	}*/
}
