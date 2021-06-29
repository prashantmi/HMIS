package hisglobal.persistence;

import hisglobal.utility.installSoftware.InstallSoftwareVO;
import hisglobal.vo.UserVO;

import java.util.Date;
import java.util.List;


import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;



public interface GlobalEssentialDAOi
{
	public String[] getSystemDate(UserVO _userVO);

	public Date getSystemDate(UserVO _userVO,Date _dt);

	public List getSystemDateAndFormat(UserVO _userVO);

	public String isRegistrationAllowed(String _categoryCode, UserVO _userVO);


	public HospitalMstVO getHospitalDetail(String hospitalCode);

	
	public List getSoftwareList(InstallSoftwareVO softwareVO);

	public List getModuleList(UserVO _userVO);
	
	public List getMasterListByModuleId(String moduleId,UserVO userVO);

	public List getMasterDataList(String mainQuery, String[] masterColumnArray,String isActive,
			UserVO userVO);

	public List getMasterCriteriaData(String criteriaQuery, UserVO userVO);

	public boolean isCrNoExists(String patCrNo,UserVO userVO);
	
	public List getAllMasterList(String moduleId,UserVO userVO);

	public List getMasterColumnList(String masterName, UserVO userVO);
	
	public List getHospitalCombo();
}
