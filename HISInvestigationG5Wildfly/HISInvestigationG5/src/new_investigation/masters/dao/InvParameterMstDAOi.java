package new_investigation.masters.dao;

import new_investigation.vo.InvParameterMasterVO;
import hisglobal.vo.UserVO;

public interface InvParameterMstDAOi
{

	public String checkDuplicateParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public void createParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public void fetchParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public String checkDuplicateModifyParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO);
	public void updateParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);

}
