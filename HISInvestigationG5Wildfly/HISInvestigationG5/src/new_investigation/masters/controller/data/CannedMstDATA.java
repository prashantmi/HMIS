package new_investigation.masters.controller.data;

 

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.CannedMasterVO;

import java.util.Map;



public class CannedMstDATA
{
	public static void saveCanned(CannedMasterVO cannedmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		  masterDelegate.saveCanned(cannedmaster_VO, _UserVO);
	}

	public static void modifyCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.modifyCanned(cannedmaster_VO, _UserVO);
	}
	
	public static void fetchCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchCanned(cannedmaster_VO, _UserVO);
	}
	
	public static void updateCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.updateCanned(cannedmaster_VO, _UserVO);
	}

}