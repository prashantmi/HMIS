package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class TestGroupInfoMstDATA
{
	public static void saveTestGroupInfo(TestGroupInfoMasterVO[] insert_testgroupinfo_VO,TestGroupInfoMasterVO[] delete_testgroupinfo_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveTestGroupInfo(insert_testgroupinfo_VO,delete_testgroupinfo_VO, _UserVO);
	}




	public static Map fetchTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestGroupInfo(testgroupinfo_VO, _UserVO);
	}


	
	public static Map getTest(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTest(testgroupinfo_VO, _UserVO);
	}
	
	public static void modifyTemplateValue(TestGroupInfoMasterVO testgroupinfo_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.modifyTemplateValue(testgroupinfo_VO, _UserVO);
	}
	
	
	
	
}
