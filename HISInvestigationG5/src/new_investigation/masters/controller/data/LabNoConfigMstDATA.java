package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.LabNoConfigMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class LabNoConfigMstDATA
{
	public static void saveLabNoConfig(LabNoConfigMasterVO collectioncrea_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveLabNoConfig(collectioncrea_VO, _UserVO);
	}

	public static Map fetchCheckListLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListLabNoConfig(labnoconfig_VO, _UserVO);
	}


	public static Map fetchLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabNoConfig(labnoconfig_VO, _UserVO);
	}

	public static void savemodifyLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyLabNoConfig(labnoconfig_VO, _UserVO);
	}
	
	public static Map getTest(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTest(labnoconfig_VO, _UserVO);
	}
}
