package new_investigation.masters.dao;

import java.util.List;





import new_investigation.vo.TestMandRefMasterVO;
import hisglobal.vo.UserVO;

public interface TestMandRefMstDAOi
{


	public String checkDuplicateTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public void createTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List<TestMandRefMasterVO> fetchCheckListTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List<TestMandRefMasterVO> fetchdisplaydataTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public String checkDuplicateModifyTestMandRef(TestMandRefMasterVO testmandref_VO,UserVO _UserVO);
	public void updateTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List getTestCombo(UserVO _UserVO);
	public List getTestMandRef(UserVO _UserVO);
	public List fetchParameterCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public String fetchCriteriaCode(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchLabCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchSampleCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchLabComboFilter(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchSampleComboFilter(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchLabSampleCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchMandCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchOld(String testCode, String parameterCode, UserVO _UserVO);
	public void deleteTestMandRef(String str1, String testCode, String parameterCode,UserVO _UserVO);
	public List<TestMandRefMasterVO> fetchCheckListLocalTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);

}
