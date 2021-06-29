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


public class GlobalEssentialDelegate extends Delegate
{
	public GlobalEssentialDelegate()
	{
		super(new GlobalEssentialBO());
	}

	public String[] getSysDate(UserVO _userVO)
	{
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSysDate(_userVO));
	}

	public Date getSysDate(Date _dt)
	{
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return serviceBO.getSysDate(_dt);
	}

	public String isRegistrationAllowed(String _categoryCode, UserVO _userVO)
	{
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return serviceBO.isRegistrationAllowed(_categoryCode, _userVO);
	}

	public List getSystemDateAndFormat(UserVO _userVO)
	{
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSystemDateAndFormat(_userVO));
	}


	public HospitalMstVO getHospitalDetail(String hospitalCode) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getHospitalDetail(hospitalCode));
	}


	public List getSoftwareList(InstallSoftwareVO softwareVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSoftwareList(softwareVO));
		
	}

	public List getModuleList(UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getModuleList(userVO));
		
	}
	
	public List getMasterListByModuleId(String moduleId,UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMasterListByModuleId(moduleId, userVO));
		
	}

	public List getMasterDataList(String mainQuery, String[] masterColumnArray,
			String isActive, UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMasterDataList(mainQuery, masterColumnArray,isActive,userVO));
	}

	public List getCriteriaData(String criteriaQuery, UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getCriteriaData(criteriaQuery,userVO));
	}
	


	public List getAllMasterList(String moduleId, UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllMasterList(moduleId, userVO));
	}

	public List getMasterColumnList(String masterName, UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMasterColumnList(masterName, userVO));
	}

	public void saveMasterVerification(
			MasterVerificationVO masterVerificationVO, UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		serviceBO.saveMasterVerification(masterVerificationVO, userVO);
	}

	public MasterVerificationVO getMasterVerification(
			MasterVerificationVO masterVerificationVO, UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return serviceBO.getMasterVerification(masterVerificationVO, userVO);
		
	}

	public void modify(MasterVerificationVO masterVerificationVO, UserVO userVO) {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		serviceBO.modifyMasterVerification(masterVerificationVO, userVO);
	}

	public List getHospitalCombo() {
		GlobalEssentialBOi serviceBO = (GlobalEssentialBOi) super.getServiceProvider();
		return serviceBO.getHospitalCombo();
	}
	

}
