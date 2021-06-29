package new_investigation.masters.dao;

import java.util.List;




import new_investigation.vo.TestParaComboMasterVO;
import hisglobal.vo.UserVO;

public interface TestParaComboMstDAOi
{


	public String checkDuplicateTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public void createTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public void removeDefaultTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public void fetchCheckListTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public List<TestParaComboMasterVO> fetchdisplaydataTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public String checkDuplicateModifyTestParaCombo(TestParaComboMasterVO testparacombo_VO,UserVO _UserVO);
	public void updateTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public List getTestCombo(UserVO _UserVO);
	public List getTestParaCombo(UserVO _UserVO);
	public List fetchParameterCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);

}
