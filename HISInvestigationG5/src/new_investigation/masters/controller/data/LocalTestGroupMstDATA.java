package new_investigation.masters.controller.data;

 

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.TestGroupMasterVO;

import java.util.List;
import java.util.Map;



public class LocalTestGroupMstDATA
{
	public static void saveLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		  masterDelegate.saveLocalTestGroup(testgroupmaster_VO, _UserVO);
	}

	public static void modifyLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.modifyLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public static Map fetchCheckListLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public static Map fetchLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public static void updateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.updateLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public static void fetchdisplaydataLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchdisplaydataLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
}