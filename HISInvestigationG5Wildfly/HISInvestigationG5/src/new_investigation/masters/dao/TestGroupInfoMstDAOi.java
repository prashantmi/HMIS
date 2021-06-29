package new_investigation.masters.dao;

import java.util.List;



import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import hisglobal.vo.UserVO;

public interface TestGroupInfoMstDAOi
{


	public void updateValidTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public void createTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public void fetchCheckListTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public String checkPrimaryKeyTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO,UserVO _UserVO);
	public void deleteTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public List getgroupCombo(UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List gettestComboLeft(TestGroupInfoMasterVO testgroupinfo_VO,UserVO _UserVO);
	public List getselectedtestComboRight(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public List getPrintingTemplateCombo(UserVO _UserVO);
	public void fetchParameter(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public void modifyTemplateValue(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public void modifyPrintingTemplateValue(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	


}
