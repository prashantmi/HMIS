package medicalboard.masters.bo;

import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;
import hisglobal.vo.MbCertificateBoardMstVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MbReferMappingMstVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.RoleMasterVO;
import hisglobal.vo.MedicalBoardChecklistVO;

public interface MedicalBoardMasterBOi {


	public void saveCertificateTypeDtl(MbCertificateTypeMstVO mTypeMstVO,String[] districtId,UserVO userVO);
	public Map getDataCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO);
	public boolean saveModCertificateType(MbCertificateTypeMstVO mTypeMstVO,String[] districtId,UserVO _UserVO);
	
	// organization Master//
	
	public void saveOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO);
	public MbOrganizationMstVO getOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO);
	public boolean saveModOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO _UserVO);

	
	// BoardType Master//
	
	public void saveBoardInfo(MedicalBoardMasterVO _MedicalBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO userVO);
	public Map fetchBoardDetail(MedicalBoardMasterVO medicalBoardMasterVO, UserVO _UserVO);
	public void saveModBoardInfo(MedicalBoardMasterVO mBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO userVO);
	
	// Certificate Board Mapping Master //
	
	public Map getBoard(MbCertificateBoardMstVO mBoardMstVO, UserVO _UserVO);
	public boolean saveModCertificateBoardInfo(MbCertificateBoardMstVO mBoardMstVO,String[] boardTypeIdArray,UserVO _UserVO);
	


	/* ****************** Checklist master ************************/
	
	public List getIsCompulsoryOptions(UserVO userVO);
	public boolean saveChecklistMst(MedicalBoardChecklistVO checklistVO,
			UserVO userVO);
	public MedicalBoardChecklistVO getChecklistById(
			MedicalBoardChecklistVO checklistVO, UserVO userVO);
	public boolean modifySaveChecklist(MedicalBoardChecklistVO checklistVO,UserVO userVO);

	
	// role Master //
	public void saveRoleDtl(RoleMasterVO roleMasterVO,UserVO userVO);
	public RoleMasterVO getRoleDtl(RoleMasterVO roleMasterVO, UserVO _UserVO);
	public boolean saveModRoleDtl(RoleMasterVO roleMasterVO,UserVO _UserVO);
	
	
	// refer Mapping //
	public void saveReferMappingData(List selectedDeptOrUnit,UserVO userVO);
	public Map getReferMappingDetail(MbReferMappingMstVO mappingMstVO, UserVO _UserVO);
	public void saveModReferMappingData(List selectedDeptOrUnit,UserVO userVO);
	
	//certificate Checklist Master
	public Map getEssentialsForCertificateChecklist(String certificateTypeID,UserVO userVO);
	public void saveCerificateChecklistMst(List certificateChecklist,UserVO userVO);
	public void saveCertificateChecklistMst(List certificateChecklist,List certificateChecklistUpdateVOList, UserVO userVO);

	//investigationMapping master
	
	public void saveInvestigationMappintDtl(List selInvastMapVOList,UserVO _UserVO);
	public void updateInvestigationMappintDtl(List selInvastMapVOList,UserVO userVO);
	
	
}
