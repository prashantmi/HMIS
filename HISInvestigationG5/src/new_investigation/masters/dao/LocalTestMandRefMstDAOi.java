package new_investigation.masters.dao;

import java.util.List;





import new_investigation.vo.LocalTestMandRefMasterVO;
import hisglobal.vo.UserVO;

public interface LocalTestMandRefMstDAOi
{


	public String checkDuplicateLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public void createLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List<LocalTestMandRefMasterVO> fetchCheckListLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List<LocalTestMandRefMasterVO> fetchdisplaydataLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public String checkDuplicateModifyLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO,UserVO _UserVO);
	public void updateLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List getTestCombo(UserVO _UserVO);
	public List getLocalTestMandRef(UserVO _UserVO);
	public List fetchParameterCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public String fetchCriteriaCode(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchLabCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchSampleCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchLabComboFilter(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchSampleComboFilter(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchLabSampleCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchMandCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
	public List fetchOld(String testCode, String parameterCode, UserVO _UserVO);
	public void deleteLocalTestMandRef(String str1, String testCode, String parameterCode,UserVO _UserVO);

}
