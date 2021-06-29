package inpatient.transaction.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.JsyRuleMasterVO;
import hisglobal.vo.MonitoringModeMstVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;

import java.util.List;

import javax.print.DocFlavor.STRING;

public interface InPatientEssentialDAOi 
{
	/** Getting the List of Unit
	 * @param userVO
	 * @return
	 */
	public List getDeptUnitList(UserVO userVO);
	
	public List getAllUnitList(UserVO userVO);
	
	public String getDrugAdminData(PatientDetailVO voPat, UserVO voUser);
	
	public String getProgressNotesData(PatientDetailVO voPat, UserVO voUser);
	
	public List getDeptUnitListForUnitWise(UserVO userVO);
	
	public List getDeptUnitListForUnitWiseForModify(UserVO userVO);
	
	public List getDeptUnitListForWardWise(UserVO userVO);
	
	public List getWardListForWardWise(UserVO userVO);
	
	public List getParameter(UserVO userVO);
	
	public List getParameterForModify(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO);
	
	public List getParameterForWardWise(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO);
	
	/** Getting the List of ward on the basis of unitCode
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO);
	
	/** Getting the List of ward on the basis of unitCode
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getWardListBasedOnRole(UserVO userVO);

	public List getRoomOnBasisOfWardCode(String unitCode,String wardCode,UserVO userVO);
	
	/**Getting the List of Admitted patient on the basis of unitCode & wardCode
	 * @param unitCode
	 * @param wardCode
	 * @param userVO
	 * @return
	 */
	public PatientDetailVO[] getAdmittedPatientList(String roomCode,String unitCode,String wardCode,UserVO userVO);

	public List getWardsForModify(String _unitId, UserVO _userVO);
	
	public UnitInvParaMappingVO getWardName(UnitInvParaMappingVO _unitInvParaMapVO, UserVO _userVO);
	
//	public List getwardList(UnitInvParaMappingVO voUDMT, UserVO _UserVO);

	
	/** Getting the List of Employee Unit Wise
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getEmployeeListUnitWise(String unitCode,UserVO userVO);
	
	/** Getting the List of Out Parameter List
	 * @param userVO
	 * @return
	 */
	public List getOutParameterList(String type,UserVO userVO);
	
	public MonitoringModeMstVO[] getMonitorMode(UserVO userVO);
	
	/** Getting Total No of Admitted Patient
	 * @param userVO
	 * @param unitCode
	 * @param wardeCode
	 * @return
	 */
	public String getTotalAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode);
	
	/** Getting Total No of Today Admitted Patient
	 * @param userVO
	 * @param unitCode
	 * @param wardeCode
	 * @return
	 */
	public String getTodayAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode);
	
	/** Getting Total No of Today Discharged Patient
	 * @param userVO
	 * @param unitCode
	 * @param wardeCode
	 * @return
	 */
	public String getTodayDischargedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode);

	
	public List getProgressNotes(String processId,UserVO userVO);
	
	/**
	 * Getting Macros By Process Id
	 * @param processId
	 * @param userVO
	 * @return
	 */
	public List getMacrosByProcessId(String processId, UserVO userVO);

	public List getCallRemarksNNotes(String processId,UserVO userVO);
	
	public List getRouteList(String type,UserVO userVO);

	
	/****************************** getting all bulletins ***************************************/
	public PatientBulletinDetailVO[] getAllAdmittedPatientListBulletin(UserVO userVO);

	
	public List<Entry> getDosageFrequecy(UserVO _userVO);
	
	public List<PatDrugTreatmentDetailVO> getPrevPatDrugDetail(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List getPrevDrugSchedule(String _patCrNo, String _episodeCode, UserVO _userVO);

	
	public List getConsultantDetails(String UnitCode,UserVO _userVO);
	
	public List getConsultantPhone(String empNo,UserVO _userVO);
	
	public List getPeonDetails(String UnitCode,UserVO _userVO);

	public List getAllConsultantDetails(String UnitCode,UserVO _userVO);
	
	/*public List getDrugList(UserVO _userVO);*/
	public List getDrugListDetail(HisDAO hisDAO_p, String mode, PatientDetailVO _patDetailVO, UserVO _userVO);
	
	public List getAllDrugRouteList(UserVO _userVO);
	
	public DrugFrequencyMstVO[] getDrugFrequencyVOList(UserVO userVO);
	
	public List getDrugAdminListByCRNo(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List getDrugWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO);
	
	public List getDateWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO);
	
	public List getAllDrugAdminListByAdminEndDate(PatientDetailVO patientDetailVO, UserVO _userVO);
	
	public List getExecDrugByCrNo(PatientDetailVO patientDetailVO,UserVO _userVO);
	
	public List getALLDrugAdminListByCRNo(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List getAdminDateLst(String crNo,String episodeCode,UserVO _userVO);
	
	public List getReqBloodComponentList(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List getBloodRH();
	
	public List getBloodABO(UserVO _userVO);
	
	public List getAllComponentListForCombo(UserVO userVO);
	
	public List getInStockBloodBagListByCrNo(String _patCrNo, UserVO _userVO);
	
	public List getCrossMatchList(BloodRequisitionComponentDtlVO bloodRequisitionComponentDtlVO, UserVO _userVO);
	

	public List getRoundByListUnitWise(String unitCode,UserVO userVO);
	

	/**
	 * Getting List of Education Status
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getEducationStatusList(UserVO _userVO);

	/**
	 * Getting List of Caste
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getCasteList(UserVO _userVO);

	/**
	 * Getting List of Menstrual Cycle Id
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getMenstrualCycleList(UserVO _userVO);

	/**
	 * Getting List of Delivery Place
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getDeliveryPlaceList(UserVO _userVO);

	/**
	 * Getting List of Birth Types
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getBirthTypeList(UserVO _userVO);

	/**
	 * Getting List of Delivery Type
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getDeliveryTypeList(UserVO _userVO);
	
	public List getBloodTransReactionEssential( String patCrNo, UserVO _userVO);
	
	public List getInStockNotTransfusedBloodBagListByCrNo(String _patCrNo, UserVO _userVO);
	
	public List<BloodTransfusionDtlVO> getPreviousBloodTransDtl(String patCrNo,List<BloodTransfusionDtlVO> lstBldTransDtlVO, UserVO _userVO);
	
	public JsyRuleMasterVO getJsyRule(UserVO _userVO);
	
	public List getAreaCategory();

	
	public String getMaxEntryDateFromDrugDetail(String _patCrNo,String _episodeCode,UserVO _userVO);
	

	public List getTodayPatDrugDetail(String _patCrNo, String _episodeCode,String _patAdmNo ,UserVO _userVO);
	

	//Pending Task List
	public List getPendingConsentList(String deptUnitCode,String wardCode,String roomCode,UserVO _userVO);
	

	/**
	 * Getting List of Methods
	 * @param _methodType Method Type
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getMethodList(String _methodType, UserVO _userVO);

	/**
	 * Getting List of Labor Rooms
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getLaborRoomList(UserVO _userVO);

	/**
	 * Getting List of Labor Room Areas
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getLaborRoomAreaList(UserVO _userVO);

	/**
	 * Getting List of Labor Room Areas of given Area Type
	 * @param _userVO User Detail
	 * @param _areaTypes Area Types
	 * @return
	 */
	public List<Entry> getLaborRoomAreaListByAreaType(UserVO _userVO, String[] _areaTypes);
	
	/**
	 * Getting List of Placenta Types
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getPlacentaTypeList(UserVO _userVO);

	/**
	 * Getting List of Complications
	 * @param _type Complications Type
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getComplicationsList(String _type, UserVO _userVO);
	
	/**
	 * Getting List of Genders
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getGenderList(UserVO _userVO);

	public List getPendingTreatmentList(UserVO _userVO);
	
	public List getPendingSampleCollectionList(String wardCode,String deptUnitCode,String roomCode,UserVO _userVO);
	
	public List getPendingInstructionList(UserVO _userVO);


	/**
	 * Getting List of APGAR Times
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getApgarTimeList(UserVO _userVO);

	public List getPendingMonitoringList(UserVO _userVO);
	
	public List<Entry> getPrevInstListForPat(String crNo,String episodeCode,UserVO _userVO);
	
	public List<Entry> getPrevActivityListForPat(String crNo,String episodeCode,UserVO _userVO);
	
	public List getDischargeStatusList(UserVO userVO); 
	public String getIcdDtls(UserVO userVO); 
	

	// public List<Entry> getPrevActivityListForPat(UserVO _userVO);
	
	/**
	 * Getting Array of PatientDetailVO
	 * @param _patCrNo 
	 * @param _userVO User Detail
	 * @return PatientDetailVO[]
	 */
	public PatientDetailVO[] getPatientAdmissionDetailsEMR(String _patCrNo,UserVO _userVO);

	public List getTodayExtDetailList(String _patCrNo, String _episodeCode,String _admissionNo ,UserVO _userVO);
	
	public List getExecutedExtTreatList(String _patCrNo, String _episodeCode,String _admissionNo ,UserVO _userVO);
	
	public List getExecutedActivityList(String _patCrNo, String _episodeCode,String _admissionNo ,UserVO _userVO);

	public List getAllRoleList(UserVO userVO);

	/**
	 * Getting List of Anomoly Types
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getAnomolyTypeList(UserVO _userVO);
	
	public String getPatientProfileStatus(PatientDetailVO patientVO,UserVO userVO);

	/** Getting the List of all Departments 
	 * @param _userVO User Detail
	 * @return
	 */	
	public List<Entry> getAllDepartmentList(UserVO _userVO);

	/******* For Blood Bank***********/
	public List getBloodGroup(UserVO _userVO);
	
	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode, UserVO _userVO);
	public List getPatientCategoryList(UserVO _userVO);
	public List getPrimaryCat(UserVO _userVO);
	public List getRegistrationCategory();

	public List getUnitPhone(String unitCode, UserVO _UserVO);
	
	
	public List getDeptUnitListForIPDNursingDesk(UserVO userVO); //Added by Vasu on 15.Sept.2018
	public List getWardOnBasisOfUnitCodeForIPDNursing(String unitCode,UserVO userVO); //Added by Vasu on 15.Sept.2018
	
}
