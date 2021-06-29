package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.SampleNoConfigMasterVO;
import hisglobal.vo.UserVO;

public interface SampleNoConfigMstDAOi
{


	public String checkDuplicateSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO);
	public void createSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO);
	public void fetchCheckListSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO);
	public void updateSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO);
	public List getTest(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List gettestCombo(UserVO _UserVO);

}
