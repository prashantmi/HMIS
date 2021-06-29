package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.LabNoConfigMasterVO;
import hisglobal.vo.UserVO;

public interface LabNoConfigMstDAOi
{


	public String checkDuplicateLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public void createLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public void fetchCheckListLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public void updateLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public List getTest(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List gettestCombo(UserVO _UserVO);
	public String checkModifyDuplicateLabNoConfigWithoutAreaWise(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public String checkModifyDuplicateLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
}
