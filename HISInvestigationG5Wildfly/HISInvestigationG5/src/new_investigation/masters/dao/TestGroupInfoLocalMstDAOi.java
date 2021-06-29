package new_investigation.masters.dao;

import java.util.List;



import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import hisglobal.vo.UserVO;

public interface TestGroupInfoLocalMstDAOi
{


	public void updateValidTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO);
	public void createTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO);
	public void fetchCheckListTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO);
	public String checkPrimaryKeyTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO,UserVO _UserVO);
	public void deleteTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO);
	public List getgroupCombo(UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List getPrintingTemplateCombo(UserVO _UserVO);
	public List gettestComboLeft(TestGroupInfoMasterVO testgroupinfolocal_VO,UserVO _UserVO);
	public List getselectedtestComboRight(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO,List<TestGroupInfoMasterVO> lstTestSeqNo);
	public void fetchParameter(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO);
	public void modifyTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public void modifyPrintingTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public List getPrintingTemplateMappedCombo(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO);
	public void insertPrintingTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO);
	public String checkUserGroupCodeTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO,UserVO _UserVO);

}
