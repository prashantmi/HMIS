package inpatient.transaction.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import hisglobal.utility.Entry;
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.MonitoringModeMstVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;

public interface InPatientEssentialBOi 
{
	/** Getting the List of Unit
	 * @param userVO
	 * @return
	 */
	public List getDeptUnitList(UserVO userVO);
	
	public Map getDeptUnitListForUnitWise(UserVO userVO);
	public String getDrugAdminData(PatientDetailVO voPat, UserVO voUser); 
	public String getProgressNotesData(PatientDetailVO voPat, UserVO voUser);
	
	public Map getDeptUnitListForUnitWiseForModify(UserVO userVO);
	
	public Map getDeptUnitListForWardWise(UserVO userVO);
	
	public List getParameter(UserVO userVO);
	
	public List getParameterForModify(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO);
	
	public List getParameterForWardWise(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO);
	
	/** Getting the List of Ward on the basis of Unit
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO);
	
	/** Getting the List of Ward on the basis of role 
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getWardListBasedOnRole(UserVO userVO);
	
	public List getRoomOnBasisOfWardCode(String unitCode,String wardCode,UserVO userVO);
	
	/** Getting the List of Admitted Patient on the basis of unitCode & wardCode
	 * @param unitCode
	 * @param wardCode
	 * @param userVO
	 * @return
	 */
	public PatientDetailVO[] getAdmittedPatientList(String roomCode,String unitCode,String wardCode,UserVO userVO);
	
	public UnitInvParaMappingVO getData(UnitInvParaMappingVO _UnitInvParaMstVO, UserVO _UserVO);
	
	public UnitInvParaMappingVO fetchParameter(String _slno,String _unitId,String _wardCode,UserVO _UserVO);
	
	public List getWardsForModify(String UnitId, UserVO _userVO);
	
	public UnitInvParaMappingVO getWardName(UnitInvParaMappingVO UnitInvParaMapVO, UserVO _userVO);
	
	//public List getWardList(UnitInvParaMappingVO voUDMT, UserVO _UserVO);
	
		public UnitInvParaMappingVO getParameterName(String _paraCode,UserVO _UserVO);
		
	public List getParaListForModify(String _slno,String _unitId,String _wardCode,UserVO _UserVO);
	
	public List getParaListForWardWise(String _slno,String _unitId,String _wardCode,UserVO _UserVO);
	
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
	
	/**
	 * Getting Vital Recording Essentials
	 * @param _patClinDtlVO
	 * @param _patClinicalDtlVO
	 * @param _lstTemplates
	 * @param _userVO
	 * @return Map of essentials
	 */
	public Map getVitalsRecordingEssentials(PatientMonitoringMstVO _patMoniDtl, PatientClinicalDetailVO _patClinicalDtlVO, 
			List<Entry> _lstTemplates, UserVO _userVO);
	
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
	
	public Map getTreatAdministrationEssential(PatientDetailVO patientDetailVO, UserVO _userVO);

	
	public List getConsultantDetails(String unitCode,UserVO _userVO);
	
	public List getConsultantPhone(String empNo,UserVO _userVO);
	
	public List getPeonDetails(String unitCode,UserVO _userVO);
	
	public List getAllConsultantDetails(String unitCode,UserVO _userVO);

	
	public List getDateWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO);
	
	public List getDrugWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO);
	
	public Map getPatDrugReactionEssential(PatientDetailVO patientDetailVO,DrugAdminDtlVO drugAdminDtlVO ,UserVO _userVO);
	public List getProgressNotes(String processId,UserVO userVO);
	
	public List getCallRemarksNNotes(String processId,UserVO userVO);
	
	public List getRouteList(String type,UserVO userVO);
	
	public Map getStockEntryOfBloodEssential(PatientDetailVO patientDetailVO,UserVO _userVO);
	
	public List getRoundByEssentials(String unitCode,UserVO userVO);

	public Map getBloodTransfusionEssential(PatientDetailVO patientDetailVO,UserVO _userVO);
	
	public List<BloodTransfusionDtlVO> getPreviousBloodTransDtl(String patCrNo,List<BloodTransfusionDtlVO> lstBldTransDtlVO, UserVO _userVO);
	
	public Map getBloodTransReactionEssential(PatientDetailVO patientDetailVO,UserVO _userVO);

	/**
	 * Getting Essentials for ANC Detail
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCDetailEssentials(UserVO _userVO);

	/**
	 * Getting ANC Detail Macros
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getANCMacroDetail(String _processId, UserVO _userVO);

	/**
	 * Getting Essentials for ANC Delivery Detail
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCDeliveryDetailEssentials(UserVO _userVO);

	/**
	 * Getting Essentials for ANC Neonatal Detail
	 * @param _ancNeoNatVO ANC Neo Natal Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCNeonatalDetailEssentials(ANCNeonatalDetailVO _ancNeoNatVO, UserVO _userVO);

	//public List getPendingConsentList(String deptUnitCode, String wardCode,

	public Map getPendingTasks(String deptUnitCode, String wardCode,
			String roomCode, UserVO _uservo);

	//public List getPendingConsentList(String admissionNo, UserVO userVO);
	
	public Map getPatientChronicNAllergy(String crNo,UserVO userVO);
	
	public Map getDischargeStatusListNProfileStatus(PatientDetailVO patientVO,UserVO userVO);
	
	public Map getExtAdministrationEssential(PatientDetailVO patientDetailVO, UserVO _userVO);
	
	public List<Entry> getReactionSummaryDetail(String _processId, UserVO _userVO);
	
	public Map getANCTeamDtlEssential(UserVO _userVO);

	/**
	 * Getting Essentials for ANC Child Handover
	 * @param _ancChildHandoverVO ANC Child Handover VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCChildHandoverEssentials(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO);

	/**
	 * Getting Essentials for ANC Trimester CheckList Entry
	 * @param _ancDtlVO ANC Detail VO
	 * @param _sysdate System Date Object
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCTrimesterChklistEssentials(ANCDetailVO _ancDtlVO, Date _sysdate, UserVO _userVO);

	// Getting ICD Code Essentials
	public Map getICDAllEssentials(UserVO _userVO);
	
	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode, UserVO _userVO);
	
	public Map getRegAndPatCategory(UserVO _userVO);
	
	public Map getHospitalEssentialCombo(UserVO _uservo);
	
	public Map getAllGlobalDepartment(UserVO _userVO);

	public Map getDischargeStatus(UserVO _uservo);

	public List getUnitPhone(String unitCode, UserVO userVO);
}
