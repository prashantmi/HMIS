package medicalboard.masters.dao;

import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.UserVO;
import java.util.List;

public interface MbMasterEssentialDAOi {

	public List getCertificateCategory(UserVO userVO);
	public List getBoardTypeList(UserVO userVO);
	public List getDepartmentUnitList(UserVO userVO);
	public List getDistrictList(UserVO userVO);
	public List getRollList(UserVO userVO);
	public List getEmpEscortList(UserVO _userVO);
	public List getEmpDoctorList(UserVO _userVO);
    public MedicalBoardMasterVO[] getBoardDetail(UserVO _UserVO);
	public MbCertificateTypeMstVO[] getCertificateDetail(UserVO _UserVO);

	//Checklist master
	public List getIsCompulsoryList(UserVO userVO);
	
	//certificate checklist master
	public List getCertificateTypeList(UserVO userVO);
	
	public List getChecklistNotAddedToCertificate(String certificateTypeID,UserVO userVO);
	
	public List getAddedChecklistToCertificate(String certificateTypeID,UserVO userVO);

	public List getAllDepartmentList(UserVO userVO);
	public List getAllDeptUnitList(UserVO userVO);
	public List getSpecialDeptUnitList(UserVO userVO);
	
	public String getCertificateNameByID(String certificateTypeID,UserVO userVO);
	
	 public List getlabTestList(String certificateTypeId,UserVO userVO);
	 
	 public List getSelLabTestList(String certificateTypeId,UserVO userVO);
	 
	 public List getTemplateList(UserVO userVO);
 

 }
