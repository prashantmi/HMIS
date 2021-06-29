package new_investigation.masters.dao;

import new_investigation.vo.externalLabMasterVO;
import hisglobal.vo.UserVO;

public interface externalLabMstDAOi
{

	public String checkDuplicateParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO);
	public void createParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO);
	public void fetchParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO);
	public String checkDuplicateModifyParameter(externalLabMasterVO parametermaster_VO,UserVO _UserVO);
	public void updateParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO);

}
