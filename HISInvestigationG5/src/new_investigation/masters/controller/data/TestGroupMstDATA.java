package new_investigation.masters.controller.data;

 

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.TestGroupMasterVO;

import java.util.Map;



public class TestGroupMstDATA
{
	public static void saveTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		  masterDelegate.saveTestGroup(testgroupmaster_VO, _UserVO);
	}

	public static void modifyTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.modifyTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public static void fetchTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public static void updateTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.updateTestGroup(testgroupmaster_VO, _UserVO);
	}
}