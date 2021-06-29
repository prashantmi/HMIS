package inpatient.transaction.delegate;

import hisglobal.business.Delegate;
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
import inpatient.transaction.bo.InPatientEssentialBO;
import inpatient.transaction.bo.InPatientEssentialBOi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;




public class InPatientEssentialDelegate extends Delegate  
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public InPatientEssentialDelegate()
	{
		super(new InPatientEssentialBO()); //// Setting the service provider
	}
	
	/** Getting the List of Unit
	 * @param userVO
	 * @return
	 */
	public List getDeptUnitList(UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getDeptUnitList(userVO);
	}
	
	public Map getEssentialsForUnitWise(UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getDeptUnitListForUnitWise(userVO);
	}
	
	public String getDrugAdminData(PatientDetailVO voPat, UserVO voUser) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return serviceBO.getDrugAdminData(voPat,voUser);
	}
	
	public String getProgressNotesData(PatientDetailVO voPat, UserVO voUser) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return serviceBO.getProgressNotesData(voPat,voUser);
	}
	
	
	public Map getEssentialsForUnitWiseForModify(UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getDeptUnitListForUnitWiseForModify(userVO);
	}
	
	public Map getEssentialsForWardWise(UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getDeptUnitListForWardWise(userVO);
	}
		
	public List getParameter(UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getParameter(userVO);
	}
	
	public List getParameterForModify(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getParameterForModify(unitInvParaMapVO,userVO);
	}
	
	public List getParameterForWardWise(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getParameterForWardWise(unitInvParaMapVO,userVO);
	}
	
	/** Getting the List of Ward on the basis of unitCode 
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getWardOnBasisOfUnitCode(unitCode,userVO);
	}
	
	/** Getting the List of Ward on the basis of role 
	 * @param userVO
	 * @return
	 */
	public List getWardListBasedOnRole(UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getWardListBasedOnRole(userVO);
	}

	public List getRoomOnBasisOfWardCode(String unitCode,String wardCode,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getRoomOnBasisOfWardCode(unitCode,wardCode,userVO);
	}
	
	/** Getting the List of Admitted Patient on the basis of unitCode & wardCode
	 * @param unitCode
	 * @param wardCode
	 * @param userVO
	 * @return
	 */
	public PatientDetailVO[] getAdmittedPatientList(String roomCode,String unitCode,String wardCode,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getAdmittedPatientList(roomCode,unitCode,wardCode,userVO);
	}
	
	public UnitInvParaMappingVO getData(UnitInvParaMappingVO _UnitInvParaMStVO, UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getData(_UnitInvParaMStVO, _UserVO));
	}
	
	public UnitInvParaMappingVO fetchParameter(String _slno, String _unitId, String _wardCode, UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.fetchParameter(_slno,_unitId,_wardCode, _UserVO));
	}
	
	public List getWardsForModify(String _unitId, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardsForModify(_unitId, _userVO));
	}
	
	public UnitInvParaMappingVO getWardName(UnitInvParaMappingVO _unitInvParaMapVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardName(_unitInvParaMapVO, _userVO));
	}
	
	/*public List getWardList(UnitInvParaMappingVO voUDMT, UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardList(voUDMT, _UserVO));
	}*/
	public UnitInvParaMappingVO getParameterName(String _paraCode,UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getParameterName(_paraCode,_UserVO);
	}
	public List getParaListForModify(String _slno,String _unitId,String _wardCode,UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getParaListForModify(_slno,_unitId,_wardCode,_UserVO);
	}
	public List getParaListForWardWise(String _slno,String _unitId,String _wardCode,UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getParaListForWardWise(_slno,_unitId,_wardCode,_UserVO);
	}


	/** Getting the List of Employee Unit Wise
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getEmployeeListUnitWise(String unitCode,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getEmployeeListUnitWise(unitCode,userVO);
	}
	
	/** Getting the List of Out Parameter List
	 * @param userVO
	 * @return
	 */
	public List getOutParameterList(String type,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getOutParameterList(type,userVO);
	}
	
	public MonitoringModeMstVO[] getMonitorMode(UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getMonitorMode(userVO);
	}

	/**
	 * Getting Vital Recording Essentials
	 * @param _patClinDtlVO
	 * @param _patClinicalDtlVO
	 * @param _lstTemplates
	 * @param _userVO
	 * @return Map of essentials
	 */
	public Map getVitalsRecordingEssentials(PatientMonitoringMstVO _patMoniDtl, PatientClinicalDetailVO _patClinicalDtlVO, 
			List<Entry> _lstTemplates, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getVitalsRecordingEssentials(_patMoniDtl, _patClinicalDtlVO, _lstTemplates, _userVO);
	}
	
	/** Getting Total No of Admitted Patient
	 * @param userVO
	 * @param unitCode
	 * @param wardeCode
	 * @return
	 */
	public String getTotalAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getTotalAdmittedPatient(userVO,unitCode,wardCode,roomCode);
	}
	
	/** Getting Total No of Today Admitted Patient
	 * @param userVO
	 * @param unitCode
	 * @param wardeCode
	 * @return
	 */
	public String getTodayAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getTodayAdmittedPatient(userVO,unitCode,wardCode,roomCode);
	}
	
	/** Getting Total No of Today Discharged Patient
	 * @param userVO
	 * @param unitCode
	 * @param wardeCode
	 * @return
	 */
	public String getTodayDischargedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getTodayDischargedPatient(userVO,unitCode,wardCode,roomCode);
	}
	

	public List getProgressNotes(String processId,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getProgressNotes(processId,userVO);
	}
	public List getCallRemarksNNotes(String processId,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getCallRemarksNNotes(processId,userVO);
	}
	
	public List getRouteList(String type,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getRouteList(type,userVO);
	}

	public Map getTreatAdministrationEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getTreatAdministrationEssential(patientDetailVO, _userVO));

	}
	
	public Map getExtAdministrationEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getExtAdministrationEssential(patientDetailVO, _userVO));

	}
	

	
	 

	public List getConsultantDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		List consultantLst=new ArrayList();
		consultantLst= serviceBO.getConsultantDetails(unitCode,_UserVO);
		return consultantLst;
	}

	public List getConsultantPhone(String empNo,UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		List phoneLst=new ArrayList();
		phoneLst= serviceBO.getConsultantPhone(empNo,_UserVO);
		return phoneLst;
	}

	
	public List getPeonDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		List peonLst=new ArrayList();
		peonLst= serviceBO.getPeonDetails(unitCode,_UserVO);
		return peonLst;
	}
	

	public List getAllConsultantDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		List consultantLst=new ArrayList();
		consultantLst= serviceBO.getAllConsultantDetails(unitCode,_UserVO);
		return consultantLst;
	}
	

	public List getDateWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getDateWiseTreatInfo(drugAdminDtlVO, _userVO));
	}
	
	public List getDrugWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getDrugWiseTreatInfo(drugAdminDtlVO, _userVO));
	}
	
	public Map getPatDrugReactionEssential(PatientDetailVO patientDetailVO,DrugAdminDtlVO drugAdminDtlVO ,UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getPatDrugReactionEssential(patientDetailVO,drugAdminDtlVO, _userVO));
	}
	
	public Map getStockEntryOfBloodEssential(PatientDetailVO patientDetailVO,UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getStockEntryOfBloodEssential(patientDetailVO, _userVO));
	}

	
	public List getRoundByEssentials(String unitCode,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return serviceBO.getRoundByEssentials(unitCode,userVO);
	}


	
	public Map getBloodTransfusionEssential(PatientDetailVO patientDetailVO,UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getBloodTransfusionEssential(patientDetailVO, _userVO));
	}
	
	public List<BloodTransfusionDtlVO> getPreviousBloodTransDtl(String patCrNo,List<BloodTransfusionDtlVO> lstBldTransDtlVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getPreviousBloodTransDtl(patCrNo,lstBldTransDtlVO, _userVO));
	}
	public Map getBloodTransReactionEssential(PatientDetailVO patientDetailVO,UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO=(InPatientEssentialBOi)super.getServiceProvider();
		return (serviceBO.getBloodTransReactionEssential(patientDetailVO, _userVO));
	}
	
	/**
	 * Getting Essentials for ANC Detail
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCDetailEssentials(UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getANCDetailEssentials(_userVO));
	}

	/**
	 * Getting ANC Detail Macros
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getANCMacroDetail(String _processId, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getANCMacroDetail(_processId, _userVO));
	}
 
	/**
	 * Getting Essentials for ANC Delivery Detail
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCDeliveryDetailEssentials(UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getANCDeliveryDetailEssentials(_userVO));
	}

	/**
	 * Getting Essentials for ANC Neonatal Detail
	 * @param _ancNeoNatVO ANC Neo Natal Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCNeonatalDetailEssentials(ANCNeonatalDetailVO _ancNeoNatVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getANCNeonatalDetailEssentials(_ancNeoNatVO, _userVO));
	}

	public Map getPendingTasks(String deptUnitCode, String wardCode,
			String roomCode, UserVO _uservo) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPendingTasks(deptUnitCode,wardCode,roomCode,_uservo));
	}


	/*public List getPendingTreatmentList(String admissionNo, UserVO userVO) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPendingConsentList(admissionNo,userVO));
	}*/
	
	public Map getPatientChronicNAllergy(String crNo,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatientChronicNAllergy(crNo,userVO));
	}
	
	public Map getDischargeStatusListNProfileStatus(PatientDetailVO patientVO,UserVO userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDischargeStatusListNProfileStatus(patientVO,userVO));
	}
	
	public List<Entry> getReactionSummaryDetail(String _processId, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getReactionSummaryDetail(_processId, _userVO));
	}
	
	public Map getANCTeamDtlEssential(UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getANCTeamDtlEssential( _userVO));
	}
	
	/**
	 * Getting Essentials for ANC Child Handover
	 * @param _ancChildHandoverVO ANC Child Handover VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCChildHandoverEssentials(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getANCChildHandoverEssentials(_ancChildHandoverVO, _userVO));
	}

	/**
	 * Getting Essentials for ANC Trimester CheckList Entry
	 * @param _ancDtlVO ANC Detail VO
	 * @param _sysdate System Date Object
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCTrimesterChklistEssentials(ANCDetailVO _ancDtlVO, Date _sysdate, UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getANCTrimesterChklistEssentials(_ancDtlVO, _sysdate, _userVO));
	}

	// Getting ICD Code Essentials
	public Map getICDAllEssentials(UserVO _userVO)
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getICDAllEssentials(_userVO));
	}
	
	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,
			 String _deptUnitCode, UserVO _userVO){
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO));
	}

	public Map getRegAndPatCategory(UserVO _userVO) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return serviceBO.getRegAndPatCategory(_userVO);
	}
	
	/**
	 * Getting Hospital Combo List
	 * 
	 * @param _uservo
	 * @return
	 * 
	 */
	public Map getHospitalEssentialCombo(UserVO _uservo) 
	{
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return (serviceBO.getHospitalEssentialCombo(_uservo));
	}
	
	public Map getAllGlobalDepartment(UserVO _userVO) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllGlobalDepartment(_userVO);
	}
	
	public Map getDischargeStatus(UserVO _userVO) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		return serviceBO.getDischargeStatus(_userVO);
	}

	public List getUnitPhone(String unitCode, UserVO userVO) {
		InPatientEssentialBOi serviceBO = (InPatientEssentialBOi) super.getServiceProvider();
		List phoneLst=new ArrayList();
		phoneLst= serviceBO.getUnitPhone(unitCode,userVO);
		return phoneLst;
		
	}
	
	
}
