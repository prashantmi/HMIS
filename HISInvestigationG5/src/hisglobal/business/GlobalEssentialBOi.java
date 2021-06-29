package hisglobal.business;

import hisglobal.utility.installSoftware.InstallSoftwareVO;
import hisglobal.utility.masterVerification.MasterVerificationVO;
import hisglobal.vo.UserVO;

import java.util.Date;
import java.util.List;
import java.util.Map;


import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientAuditLogMstVO;
import hisglobal.vo.UserVO;



public interface GlobalEssentialBOi
{
	public String[] getSysDate(UserVO _userVO);
	public Date getSysDate(Date dt);
	public String isRegistrationAllowed(String _categoryCode,UserVO _userVO);
	public List getSystemDateAndFormat(UserVO _userVO);

	public HospitalMstVO getHospitalDetail(String hospitalCode);

	public List getSoftwareList(InstallSoftwareVO softwareVO);

	public List getModuleList(UserVO userVO);
	
	public List getMasterListByModuleId(String moduleId,UserVO userVO);
	
	public List getMasterDataList(String mainQuery, String[] masterColumnArray,
			String isActive, UserVO userVO);
	
	public List getCriteriaData(String criteriaQuery, UserVO userVO);
	
	
	public List getAllMasterList(String moduleId, UserVO userVO);
	
	public List getMasterColumnList(String masterName, UserVO userVO);
	
	public void saveMasterVerification(MasterVerificationVO masterVerficationVO,UserVO userVO);
	
	public MasterVerificationVO getMasterVerification(
			MasterVerificationVO masterVerificationVO, UserVO userVO);
	public void modifyMasterVerification(
			MasterVerificationVO masterVerificationVO, UserVO userVO);
	
	public List getHospitalCombo();
}
