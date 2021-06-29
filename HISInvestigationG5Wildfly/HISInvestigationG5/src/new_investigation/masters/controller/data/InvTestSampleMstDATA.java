package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.InvTestSampleMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class InvTestSampleMstDATA
{
	public static void saveInvTestSample(InvTestSampleMasterVO testsample_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveInvTestSample(testsample_VO, _UserVO);
	}

	public static Map fetchCheckListInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListInvTestSample(testsample_VO, _UserVO);
	}


	public static Map fetchInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchInvTestSample(testsample_VO, _UserVO);
	}

	public static void savemodifyInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyInvTestSample(testsample_VO, _UserVO);
	}

	
}
