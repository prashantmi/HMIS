package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.SampleNoConfigMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class SampleNoConfigMstDATA
{
	public static void saveSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveSampleNoConfig(samplenoconfig_VO, _UserVO);
	}

	public static Map fetchCheckListSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListSampleNoConfig(samplenoconfig_VO, _UserVO);
	}


	public static Map fetchSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchSampleNoConfig(samplenoconfig_VO, _UserVO);
	}

	public static void savemodifySampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifySampleNoConfig(samplenoconfig_VO, _UserVO);
	}
	
	public static Map getTest(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTest(samplenoconfig_VO, _UserVO);
	}
}
