package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.TestGroupInfoMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class TestGroupInfoLocalMstDATA
{
	public static void saveTestGroupInfoLocal(TestGroupInfoMasterVO[] insert_testgroupinfolocal_VO,TestGroupInfoMasterVO[] delete_testgroupinfolocal_VO,UserVO _UserVO,TestGroupInfoMasterVO[] modify_testgroupinfolocal_VO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveTestGroupInfoLocal(insert_testgroupinfolocal_VO,delete_testgroupinfolocal_VO, _UserVO,modify_testgroupinfolocal_VO);
	}

	
	public static Map fetchTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestGroupInfoLocal(testgroupinfolocal_VO, _UserVO);
	}

	
	public static Map getTestLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTestLocal(testgroupinfolocal_VO, _UserVO);
	}
	
	/*public static Map getTemplateLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTemplateLocal(testgroupinfolocal_VO, _UserVO);
	}*/
	public static void modifyTemplateValueLocal(TestGroupInfoMasterVO testgroupinfolocal_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.modifyTemplateValueLocal(testgroupinfolocal_VO, _UserVO);
	}
}
