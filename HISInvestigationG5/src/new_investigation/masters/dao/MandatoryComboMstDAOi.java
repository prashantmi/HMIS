package new_investigation.masters.dao;

import java.util.List;




import new_investigation.vo.MandatoryComboMasterVO;
import hisglobal.vo.UserVO;

public interface MandatoryComboMstDAOi
{


	public String checkDuplicateMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public void createMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public void fetchCheckListMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public List<MandatoryComboMasterVO> fetchdisplaydataMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public String checkDuplicateModifyMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO,UserVO _UserVO);
	public void updateMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public List getMandCombo(UserVO _UserVO);
}
