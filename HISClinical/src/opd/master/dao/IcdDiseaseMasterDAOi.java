package opd.master.dao;

import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.UserVO;

public interface IcdDiseaseMasterDAOi
{
	// Saving ICD Include Exclude Record 
	public void createIncludeExclude(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Checking Duplicate ICD Include Exclude
	public boolean checkIncludeExcludeDuplicate(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Getting Detail of ICD Include Exclude Record
	public IcdDiseaseMasterVO getIncludeExclude(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Updating the ICD Include Exclude
	public void update(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Checking Duplicate ICD Include Exclude for Modification
	public boolean checkDuplicateforIncludeExcludeModify(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Get Disease Name In Special Short Format
	public String getName(String _groupCode, UserVO _userVO);
}
