package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.CollAreaSampleNoConfigMasterVO;
import hisglobal.vo.UserVO;

public interface CollAreaSampleNoConfigMstDAOi
{


	public String checkDuplicateCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);
	public void createCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);
	public void fetchCheckListCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);
	public void updateCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);
	public List getArea(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List getareaCombo(UserVO _UserVO);
	public List getAreaWiseLab(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);


}
