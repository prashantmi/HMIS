package medicalboard.masters.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.MbCertificateBoardMstVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MbReferMappingMstVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.RoleMasterVO;
import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;
import medicalboard.masters.bo.MedicalBoardMasterBO;
import medicalboard.masters.bo.MedicalBoardMasterBOi;


public class MbMasterDelegate extends Delegate{
	
	public MbMasterDelegate(){
		super(new MedicalBoardMasterBO()); ///<<Setting the service provider
	}
	public void saveCertificateTypeDtl(MbCertificateTypeMstVO mTypeMstVO,String[] districtId,UserVO userVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveCertificateTypeDtl(mTypeMstVO,districtId,userVO);
	}
	
	public Map getDataCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return(serviceBO.getDataCertificateType(mTypeMstVO, _UserVO));
	}
	public boolean saveModCertificateType(MbCertificateTypeMstVO mTypeMstVO,String[] districtId,UserVO _UserVO)
	{
		boolean flag=false;
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		flag= serviceBO.saveModCertificateType(mTypeMstVO,districtId,_UserVO);
		return flag;
	}

	//// for Organization Master//
	
	public void saveOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveOrganizationDtl(mbOrganizationMstVO,userVO);
	}
	
	public MbOrganizationMstVO getOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return(serviceBO.getOrganizationDtl(mbOrganizationMstVO, _UserVO));
	}
	public boolean saveModOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO _UserVO)
	{
		boolean flag=false;
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		flag= serviceBO.saveModOrganizationDtl(mbOrganizationMstVO,_UserVO);
		return flag;
	}

	// Checklist master
	public List getIsCompulsoryOptions(UserVO userVO) {
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return serviceBO.getIsCompulsoryOptions(userVO);
	}

	public boolean saveChecklistMst(MedicalBoardChecklistVO checklistVO,
			UserVO userVO) {
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return  serviceBO.saveChecklistMst(checklistVO,userVO);
	}

	public MedicalBoardChecklistVO getChecklistById(
			MedicalBoardChecklistVO checklistVO, UserVO userVO) {
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return  serviceBO.getChecklistById(checklistVO,userVO);
	}

	public boolean modifySaveChecklist(MedicalBoardChecklistVO checklistVO,
			UserVO userVO) {
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return  serviceBO.modifySaveChecklist(checklistVO,userVO);
	}

	public Map getEssentialsForCertificateChecklist(String certificateTypeID, UserVO userVO) {
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return  serviceBO.getEssentialsForCertificateChecklist(certificateTypeID,userVO);
	}

	public void saveCerificateChecklistMst(List certificateChecklist,
			UserVO userVO) {
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveCerificateChecklistMst(certificateChecklist,userVO);
	}

	public void modifySaveCertificateChecklist(List certificateChecklist,
			List certificateChecklistUpdateVOList, UserVO userVO) {
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveCertificateChecklistMst(certificateChecklist,certificateChecklistUpdateVOList,userVO);
	}
	
	/// board type Master //
	
	public Map fetchBoardDetail(MedicalBoardMasterVO medicalBoardMasterVO, UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return(serviceBO.fetchBoardDetail(medicalBoardMasterVO, _UserVO));
	}
	
	public void saveBoardInfo(MedicalBoardMasterVO _MedicalBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO _userVO){
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveBoardInfo(_MedicalBoardMasterVO,empId,escortedBy,roleID,_userVO);
	}
	
	public void saveModBoardInfo(MedicalBoardMasterVO _MedicalBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO _userVO){
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveModBoardInfo(_MedicalBoardMasterVO,empId,escortedBy,roleID,_userVO);
	}
	
	
	// Certificate Board Mapping //
	

	public Map getBoard(MbCertificateBoardMstVO mBoardMstVO, UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return(serviceBO.getBoard(mBoardMstVO, _UserVO));
	}
	
	public void saveModCertificateBoardInfo(MbCertificateBoardMstVO mBoardMstVO,String[] boardTypeIdArray,UserVO _userVO){
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveModCertificateBoardInfo(mBoardMstVO,boardTypeIdArray,_userVO);
	}
	
	
//// for role Master//
	
	
	public void saveRoleDtl(RoleMasterVO roleMasterVO,UserVO userVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveRoleDtl(roleMasterVO,userVO);
	}
	public RoleMasterVO getRoleDtl(RoleMasterVO roleMasterVO, UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return(serviceBO.getRoleDtl(roleMasterVO, _UserVO));
	}
	public boolean saveModRoleDtl(RoleMasterVO roleMasterVO,UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return( serviceBO.saveModRoleDtl(roleMasterVO,_UserVO));
	}

	
	/// refer Type Mapping //
	
	public void saveReferMappingData(List selectedDeptOrUnit,UserVO _userVO){
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveReferMappingData(selectedDeptOrUnit,_userVO);
	}
	
	public Map getReferMappingDetail(MbReferMappingMstVO mappingMstVO, UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		return(serviceBO.getReferMappingDetail(mappingMstVO, _UserVO));
	}
	
	public void saveModReferMappingData(List selectedDeptOrUnit,UserVO _userVO){
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveModReferMappingData(selectedDeptOrUnit,_userVO);
	}
	
	public void saveInvestigationMappintDtl(List selInvastMapVOList,UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.saveInvestigationMappintDtl(selInvastMapVOList,_UserVO);
	}
	
	public void updateInvestigationMappintDtl(List selInvastMapVOList,UserVO _UserVO)
	{
		MedicalBoardMasterBOi serviceBO = (MedicalBoardMasterBOi)super.getServiceProvider();
		serviceBO.updateInvestigationMappintDtl(selInvastMapVOList,_UserVO);
	}
	
}
