package inpatient.transaction.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.ANCVisitDetailVO;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DoctorWardRoundDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.bo.InpatientBO;
import inpatient.transaction.bo.InpatientBOi;

import java.util.List;
import java.util.Map;

public class InpatientDelegate extends Delegate
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public InpatientDelegate()
	{
		super(new InpatientBO()); //// Setting the service provider
	}
	
	
	/** Getting The Patient Detail By CR No For Desk
	 * @param deskType
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public PatientDetailVO getInpatientDetailByCrNoNAdmNo(String deskType,PatientDetailVO patDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getInpatientDetailByCrNoNAdmNo(deskType,patDtlVO,userVO);
	}
	
	/** Getting The MLC No of The Patient For Desk
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public MlcVO getMlcNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getMlcNo(patDtlVO,userVO);
	}
	
	/** Saving Nurse Progress Notes
	 * @param nurseRoundDtlVO
	 * @param userVO
	 */
	public void saveNurseProgNotes(NurseRoundDtlVO nurseRoundDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveNurseProgNotes(nurseRoundDtlVO,userVO);
	}
	
	/** Saving Doctor Visit Notes By Nurse
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void saveDoctorVisitNotes(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveDoctorVisitNotes(docRoundDtlVO,userVO);
	}
	
	/** Getting Doctor Instruction on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getDoctorInstruction(String patAdmNo,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getDoctorInstruction(patAdmNo,userVO);
	}
	
	/** Getting the Data Entered by Nurse and Not Verified
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getUnverifiedEntryByNurse(String patAdmNo,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getUnverifiedEntryByNurse(patAdmNo,userVO);
	}
	
	/** Getting the Details o record entered by the Nurse
	 * @param unverifiedRecordVO
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO getRecordDetail(DoctorRoundDtlVO unverifiedRecordVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getRecordDetail(unverifiedRecordVO,userVO);
	}
	
	/** Updating Nurse Visit Notes & Saving Doctor Instruction,Progress Notes 
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void saveNVerifyNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveNVerifyNotesByDoctor(docRoundDtlVO,userVO);
	}
	
	/** Saving Notes Entered By Doctor
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void saveNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveNotesByDoctor(docRoundDtlVO,userVO);
	}
	
	/** Saving the Out Take
	 * @param patIntakeOutDtlVO
	 * @param userVO
	 */
	public void saveOutParameter(List<PatIntakeOutDtlVO> listPatIntakeOutTake,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveOutParameter(listPatIntakeOutTake,userVO);
	}
	
	/**  Getting the Details of out Take Parameter of the Patient
	 * @param dailyPatVO
	 * @param userVO
	 * @return
	 */
	public PatIntakeOutDtlVO[] getOutParaDetail(String mode,PatientDetailVO dailyPatVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getOutParaDetail(mode,dailyPatVO,userVO);
	}
	
	public Map getBulletinDetails(PatientDetailVO patDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getBulletinDetails(patDtlVO,userVO);
	}
	
	public void saveAndUpdatePatientBulletinDetails(PatientBulletinDetailVO _oldPatientBulletinVO,PatientBulletinDetailVO _newPatientBulletinVO,UserVO userVO,String revoke)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
	 serviceBO.saveAndUpdatePatientBulletinDetails(_oldPatientBulletinVO,_newPatientBulletinVO,userVO,revoke);
	}
	
	
	/** Saving the Vital Details of the Patient
	 * @param patMonitringMstVO
	 * @param modPatMonitringMstVO
	 * @param userVO
	 */
	public  void saveVitalsDetail(PatientMonitoringMstVO[] patMonitringMstVO,PatientMonitoringMstVO[] modPatMonitringMstVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		 serviceBO.saveVitalsDetail(patMonitringMstVO,modPatMonitringMstVO,userVO);
	}
	
	/** Getting the Vital Details of the Patient
	 * @param patMonitringMstVO
	 * @param userVO
	 * @return
	 */
	public PatientMonitoringMstVO[] getVitalDetail(PatientMonitoringMstVO patMonitringMstVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getVitalDetail(patMonitringMstVO,userVO);
	}
	
	/**
	 * Getting Essentials for Monitoring Vitals
	 * @param _deskType Desk Type
	 * @param _userDeskMenuTempVO User Desk Menu Detail
	 * @param _patClinicalVO Patient Clinical Data VO
	 * @param patMonitringMstVO Pat Monitoring VO
	 * @param _userVO User VO
	 * @return Map of Essentials
	 */
	public Map<String, Object> getEssentailsForMonitoringVital(String _deskType, UserDeskMenuTemplateMasterVO _userDeskMenuTempVO, 
			PatientClinicalDetailVO _patClinicalVO, PatientMonitoringMstVO _patMonitringMstVO, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getEssentailsForMonitoringVital(_deskType,_userDeskMenuTempVO,_patClinicalVO,_patMonitringMstVO,_userVO);
	}

	public NurseRoundDtlVO[] getPreviousProgressNotes(String patAdmNo,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getPreviousProgressNotes(patAdmNo,userVO);
	}


	/** Getting Doctor Prev Round Dtl on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getDoctorPrevRoundDetail(String patAdmNo, UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getDoctorPrevRoundDetail(patAdmNo,userVO);
	}
	
	/**  Revoking the Vitals of the Patient
	 * @param paraId
	 * @param patMonitoringVitalVO
	 * @param userVO
	 */
	public void revokeVitals(String paraId,PatientMonitoringMstVO patMonitoringVitalVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.revokeVitals(paraId,patMonitoringVitalVO,userVO);
	}
	
	public void saveDischargeRequest(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveDischargeRequest(patStatus,patDischargeReqVO,userVO);
	}
	
	public boolean getPatientStatus(String admNo,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getPatientStatus(admNo,userVO);
	}
	
	public PatDischargeReqDtlVO getDischargeRemarks(String admNo,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getDischargeRemarks(admNo,userVO);
	}

	public void saveDrugTreatmentExe(List drugAdminDtlVOList,List ivFluidDrugAdminVOList,List sosDrugList,List patIntakeOutDtlVOList,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveDrugTreatmentExe(drugAdminDtlVOList,ivFluidDrugAdminVOList,sosDrugList,patIntakeOutDtlVOList, _userVO);
	}
	
	public void saveExtTreatmentExe(List actvityList,List extAdminVoList ,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveExtTreatmentExe(actvityList,extAdminVoList, _userVO);
	}
	
	public void saveDrugReactionDtl(DrugReactionVO drugReactionVO,List templateParaList ,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveDrugReactionDtl(drugReactionVO,templateParaList, _userVO);
	}

	
	public PatIntakeOutDtlVO[] getIntakeSummary(String patCrNo,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getIntakeSummary(patCrNo,userVO);
	}
	
	public PatIntakeOutDtlVO[] getOuttakeSummary(String patCrNo,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getOuttakeSummary(patCrNo,userVO);
	}


	public PatientBulletinDetailVO[] getAllAdmittedPatientListBulletin(UserVO userVO) {
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getAllAdmittedPatientListBulletin(userVO);
	}

	public void saveExternalBloodStockDtl(PatBloodStockDtlVO[] patBloodStockDtlVO,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveExternalBloodStockDtl(patBloodStockDtlVO,_userVO);
	}
	

	public boolean saveDoctorCallDetails(DoctorCallBookVO callBookVO,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveDoctorCallDetails(callBookVO,_userVO);
		return hasFlag;
	}
	
	
	public boolean ModifyDoctorCallDetails(DoctorCallBookVO callBookVO,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.ModifyDoctorCallDetails(callBookVO,_userVO);
		return hasFlag;
	}
	
	public List getDeptUnitList(UserVO userVO)
	{
		InpatientBOi serviceBO=(InpatientBOi) super.getServiceProvider();
		return serviceBO.getDeptUnitList(userVO);
	}
	
	public DoctorCallBookVO[] getCallBookDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientBOi serviceBO=(InpatientBOi) super.getServiceProvider();
		return serviceBO.getCallBookDetails(doctorCallBookVO,_userVO);
	}
	

	public DoctorCallBookVO[] getAllCalls(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientBOi serviceBO=(InpatientBOi) super.getServiceProvider();
		return serviceBO.getAllCalls(doctorCallBookVO,_userVO);
	}
	
	public DoctorCallBookVO getCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientBOi serviceBO=(InpatientBOi) super.getServiceProvider();
		return serviceBO.getCallDetails(doctorCallBookVO,_userVO);
	}
	
	public DoctorCallBookVO[] getCallAcknowledgeDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientBOi serviceBO=(InpatientBOi) super.getServiceProvider();
		return serviceBO.getCallAcknowledgeDetails(doctorCallBookVO,_userVO);
	}
	
	public DoctorCallBookVO[] getAllCallsAcknowledge(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientBOi serviceBO=(InpatientBOi) super.getServiceProvider();
		return serviceBO.getAllCallsAcknowledge(doctorCallBookVO,_userVO);
	}
	
	public boolean saveDoctorCallAcknowledgeDetails(DoctorCallBookVO callBookVO,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveDoctorCallAcknowledgeDetails(callBookVO,_userVO);
		return hasFlag;
	}

	
	public void saveDoctorWardRoundDtl(DoctorWardRoundDtlVO _doctorWardRoundDtlVO,List callBookList, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveDoctorWardRoundDtl(_doctorWardRoundDtlVO,callBookList, _userVO);
	}


	
	public void saveBloodTransfusionDtl(Map<String, TransfusionReactionDtlVO> mapTrasReaction,Map<String, List<TransfusionReactionParaDtlVO>> mapTrasReactionPara,List bloodTrasVOList,List patIntakeOutDtlVOList,String[] selectedBag,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveBloodTransfusionDtl(mapTrasReaction,mapTrasReactionPara,bloodTrasVOList,patIntakeOutDtlVOList,selectedBag,_userVO);
	}
	
	public void saveBloodTransReactionDtl(TransfusionReactionDtlVO vo,List bloodTrasReactionParaVOList,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveBloodTransReactionDtl(vo,bloodTrasReactionParaVOList,_userVO);
	}
	
	
	public Map getExtPatTransReaction(UserVO _userVO){
		InpatientBOi serviceBO = (InpatientBOi) super.getServiceProvider();
		return (serviceBO.getExtPatTransReaction(_userVO));
	}
	
	
	
	public void saveExtBloodTransReactionDtl(TransfusionReactionDtlVO transReactionDtlVO,List bloodTrasReactionParaVOList,UserVO _userVO){
		InpatientBOi serviceBO = (InpatientBOi) super.getServiceProvider();
		serviceBO.saveExtBloodTransReactionDtl(transReactionDtlVO,bloodTrasReactionParaVOList,_userVO);
	}
	
	/**
	 * Getting ANC Detail 
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map getANCDetails(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getANCDetails(_ancDetailVO, _userVO);
	}
	
	/**
	 * Getting ANC History Detail 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return List of ANC History
	 */
	public List<ANCHistoryDetailVO> getANCHistoryDetail(String _patCrNo, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getANCHistoryDetail(_patCrNo, _userVO);
	}
	
	/**
	 * Saving Complete ANC Detail
	 * @param _ancDtlFlag ANC Detail Flag
	 * @param _patDtlVO Patient Other Demographic Detail
	 * @param _ancDetailVO ANC Detail
	 * @param _ancVisitDtlVO ANC Visit Detail
	 * @param _lstHistVOs ANC History Detail
	 * @param _lstHistChildVOs ANC History Delivery Detail
	 * @param _userVO User Detail
	 */
	public void saveCompleteANCDetail(String _ancDtlFlag, PatientDetailVO _patDtlVO, ANCDetailVO _ancDetailVO,
			ANCVisitDetailVO _ancVisitDtlVO, List<ANCHistoryDetailVO> _lstHistVOs, List<ANCHistoryDeliveryDetailVO> _lstHistChildVOs, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveCompleteANCDetail(_ancDtlFlag, _patDtlVO, _ancDetailVO, _ancVisitDtlVO, _lstHistVOs, _lstHistChildVOs, _userVO);
	}

	/**
	 * Getting ANC Detail for Delivery
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map getANCDetailsForDelivery(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getANCDetailsForDelivery(_ancDetailVO, _userVO);
	}

	/**
	 * Saving ANC Delivery Detail
	 * @param _ancDeliveryDetailVO
	 * @param _userVO User Detail
	 */
	public void saveANCDeliveryDetail(ANCDeliveryDetailVO _ancDeliveryDetailVO, ANCDetailVO _ancDetailVO, ANCHistoryDetailVO _ancHistVO,
			List<ANCNeonatalDetailVO> _lstANCChildren, List<ANCHistoryDeliveryDetailVO> _lstANCHistChildren, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveANCDeliveryDetail(_ancDeliveryDetailVO, _ancDetailVO, _ancHistVO, _lstANCChildren, _lstANCHistChildren, _userVO);
	}

	/**
	 * Saving Complete ANC New Natal Detail & Apgar Detail
	 * @param _ancNewNatalVO ANc New Natal Detail VO
	 * @param _lstApgars List of Apgar Details
	 * @param _userVO User Detail
	 */
	public void saveANCNewNatalDetail(ANCNeonatalDetailVO _ancNewNatalVO, List<ANCNeonatalApgarVO> _lstApgars, 
			UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveANCNewNatalDetail(_ancNewNatalVO, _lstApgars, _userVO);
	}

	
	/**
	 * Getting Patient Detail and Patient ANC Detail 
	 * @param patCrNo Patient CrNo
	 * @param _userVO User Detail
	 * @return
	 */
	public Map getPatDetailForJSYRegistration(String patCrNo, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getPatDetailForJSYRegistration(patCrNo, _userVO);
	}
	
	// save Jsy Detail
	
	public void saveJSYDetail(JSYRegitrationVO jRegitrationVO,PatientDetailVO pDetailVO,String jsyCategoryCode,SecondaryCategoryChangeVO sChangeVO,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveJSYDetail(jRegitrationVO,pDetailVO,jsyCategoryCode,sChangeVO,_userVO);
	}
	
	
 

	
	public PatIntakeOutDtlVO[] getViewSummaryDetail(String admNo,String fromDate,String toDate,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getViewSummaryDetail(admNo,fromDate,toDate, userVO);
		
	}
	
	public DoctorCallBookVO[] getOnCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientBOi serviceBO=(InpatientBOi) super.getServiceProvider();
		return serviceBO.getOnCallDetails(doctorCallBookVO,_userVO);
	}
	
	public List getDrugReactionDtl(DrugReactionVO drugReactionVO,UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		return serviceBO.getDrugReactionDtl(drugReactionVO, _userVO);
	}
	
	/**
	 * Saving ANC Child Handover Detail
	 * @param _ancChildHandoverVO Child Handover Detail
	 * @param _userVO User Detail
	 */
	public void saveANCChildHandoverDetail(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveANCChildHandoverDetail(_ancChildHandoverVO, _userVO);
	}

	/**
	 * Saving ANC Trimester Checklist Detail
	 * @param _lstDrugChkLst List of Drug CheckList
	 * @param _lstInvstChkLst List of Invest CheckList
	 * @param _userVO User Detail
	 */
	public void saveANCTrimesterChecklistDetail(List<ANCChecklistDetailVO> _lstDrugChkLst, List<ANCChecklistDetailVO> _lstInvstChkLst, UserVO _userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.saveANCTrimesterChecklistDetail(_lstDrugChkLst, _lstInvstChkLst, _userVO);
	}


	//addedBy:NehaRajgariya Date:1sept2016
	public String getSmsFlagDetails(DoctorCallBookVO _callBookVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		String smsFlag=serviceBO.getSmsFlagDetails(_callBookVO,_userVO);
		return smsFlag;
	}


	public void getSnomedIdTerm(PatIntakeOutDtlVO patIntakeOutDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.getSnomedIdTerm(patIntakeOutDtlVO,userVO);
		
	}
	
	//Added by Vasu on 26.Sept.2018 to update doctor Notes
	public void upateNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientBOi serviceBO = (InpatientBOi)super.getServiceProvider();
		serviceBO.updateNotesByDoctor(docRoundDtlVO,userVO);
	}
}

