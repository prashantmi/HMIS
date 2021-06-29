package new_investigation.masters.dao;

import new_investigation.vo.MandatoryMasterVO;
import hisglobal.vo.UserVO;

public interface MandatoryMstDAOi
{
	public String checkDuplicateMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public void createMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public void fetchCheckListMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public String checkDuplicateModifyMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO);
	public void updateMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
}
