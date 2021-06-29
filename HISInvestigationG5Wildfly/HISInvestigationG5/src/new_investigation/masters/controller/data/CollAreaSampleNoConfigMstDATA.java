package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.CollAreaSampleNoConfigMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class CollAreaSampleNoConfigMstDATA
{
	public static void saveCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig__VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveCollAreaSampleNoConfig(collareasamplenoconfig__VO, _UserVO);
	}

	public static Map fetchCheckListCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig__VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListCollAreaSampleNoConfig(collareasamplenoconfig__VO, _UserVO);
	}


	public static Map fetchCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig__VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCollAreaSampleNoConfig(collareasamplenoconfig__VO, _UserVO);
	}

	public static void savemodifyCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig__VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyCollAreaSampleNoConfig(collareasamplenoconfig__VO, _UserVO);
	}
	
	public static Map getArea(CollAreaSampleNoConfigMasterVO collareasamplenoconfig__VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getArea(collareasamplenoconfig__VO, _UserVO);
	}
	
	public static Map getAreaWiseLab(CollAreaSampleNoConfigMasterVO collareasamplenoconfig__VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getAreaWiseLab(collareasamplenoconfig__VO, _UserVO);
	}
}
