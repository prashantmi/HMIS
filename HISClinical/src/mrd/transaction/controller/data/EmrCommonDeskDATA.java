package mrd.transaction.controller.data;

import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ServiceAreaPatientVO;
import hisglobal.vo.Service_Req_dtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.VisitReasonsVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.bo.delegate.OpdEssentialDelegate;

import com.google.gson.JsonElement;

import mrd.transaction.bo.MrdBO;
import mrd.transaction.delegate.MrdDelegate;

public class EmrCommonDeskDATA {

	public static Map getEmrEssentials(PatientVO _patientVO,UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getEprEssentials(_patientVO, _userVO);
	}
	
	public static PatientVO getPatientDetails(PatientVO _patientVO,UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getPatientDtlByCrNoEMR(_patientVO, _userVO);
	}
	
	public static Map getPersonalProfileDetails(PatientVO _patientVO,String departmentUnitCode, String tabType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getPersonalProfile(_patientVO,departmentUnitCode,tabType, _userVO);
	}
	
	public static EpisodeAllergiesVO[] getEpisodeAllergiesVisitWise(EpisodeAllergiesVO _episodeAllergiesVO,UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getEpisodeAllergiesVisitWise(_episodeAllergiesVO, _userVO);
	}
	
	public static PatientAlertsDetailVO[] getPatientAlertDetails(PatientAlertsDetailVO _patientAlertsDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getPatientAlertDetails(_patientAlertsDetailVO,departmentUnitArray,accessType, _userVO);
	}
	
	public static EpisodeDiagnosisVO[] getDiagnosisDetailsEpisodeAndVisitWise(EpisodeDiagnosisVO _episodeDiagnosisVO,UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getDiagnosisDetailsEpisodeVisitWise(_episodeDiagnosisVO, _userVO);
	}
	
	public static EpisodeDiagnosisVO[] getAllDiagnosisDetails(EpisodeDiagnosisVO _episodeDiagnosisVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getAllDiagnosisDetails(_episodeDiagnosisVO,departmentUnitArray,accessType, _userVO);
	}
	
	public static EpisodeAllergiesVO[] getEpisodeAllergiesAll(EpisodeAllergiesVO _episodeAllergiesVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getEpisodeAllergiesAll(_episodeAllergiesVO,departmentUnitArray,accessType, _userVO);
	}
	
	public static PatDrugTreatmentDetailVO[] getPateintDrugAdviceDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getPatientAllDrugAdviceDetails(_patDrugTreatmentDetailVO,departmentUnitArray,accessType, _userVO);
	}
	
	public static PatDrugTreatmentDetailVO[] getPateintDrugAdviceOfflineDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getPatientAllDrugAdviceOfflineDetails(_patDrugTreatmentDetailVO,departmentUnitArray,accessType, _userVO);
	}

	public static ProfileInvestigationVO[] getPatientInvestigationDetails(ProfileInvestigationVO _profileInvestigationVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getPatientInvestigationDetails(_profileInvestigationVO,departmentUnitArray,accessType, _userVO);
	}
	
	/** Getting the Details of out Take Parameter of the Patient  
	 * @param dailyPatVO
	 * @param userVO
	 * @return
	 */
	public static Map getOutParaDetail(PatientDetailVO dailyPatVO,String []departmentUnitArray,String accessType,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getOutParaDetailEMR(dailyPatVO, departmentUnitArray, accessType, userVO);
	}
	
	/** To get patient progress notes by crno
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param dailyPatVO
	 * @param userVO
	 * @return DoctorRoundDtlVO[]
	 */
	public static DoctorRoundDtlVO[] getPatientProgressNotesEMR(DoctorRoundDtlVO doctorRoundDtlVO,String[] departmentUnitArray, String accessType, UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientProgressNotesEMR(doctorRoundDtlVO,departmentUnitArray,accessType, userVO);
	}
	
	/** To get patient vital details 
	 * @param _patientClinicalDetailVO
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public static PatientClinicalDetailVO[] getPatientVitalDetails(PatientClinicalDetailVO _patientClinicalDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientVitalsDetailsEMR(_patientClinicalDetailVO,departmentUnitArray,accessType, _userVO);
	}
	
	/** To get patient external investigation details 
	 * @param _episodExtInvDtlVO
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return EpisodeExtInvDtlVO[]
	 */
	public static EpisodeExtInvDtlVO[] getPatientExternalInvestigationDetails(EpisodeExtInvDtlVO _episodExtInvDtlVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientExternalInvestigationDetailsEMR(_episodExtInvDtlVO,departmentUnitArray,accessType, _userVO);
	}
	
	/** To get patient Complaints detail
	 * @param _patientClinicalDetailVO
	 * @param userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public static PatientClinicalDetailVO[] getPatientComplaintDetails(PatientClinicalDetailVO _patientClinicalDetailVO,String _templateCategory,UserVO _userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientComplaintsDetailsEMR(_patientClinicalDetailVO,_templateCategory, _userVO);
	}
	
	/** To get patient profile detail
	 * @param _patCrNo
	 * @param _unitCode
	 * @param _userVO
	 * @return PatientProfileDetailVO[]
	 */
	//not in use
	public static List<PatientProfileDetailVO> getPatientProfilesForAll(String _patCrNo,String _unitCode,UserVO _userVO)
	{
		//MrdDelegate delegate=new MrdDelegate();
		//return delegate.getPatientProfilesForAll(_patCrNo, _unitCode, _userVO);
		return null;
	}
	
	/** To get patient diagnosis images
	 * @param _opdPatientImageDtlVO
	 * @param _userVO
	 * @return OpdPatientImageDtlVO[]
	 */
	public static OpdPatientImageDtlVO[] getPatientDiagnosisImages(OpdPatientImageDtlVO _opdPatientImageDtlVO,UserVO _userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientDiagnosisImages(_opdPatientImageDtlVO, _userVO);
	}
	
	public static List getPatientOperationList(String crNo,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientOperationList(crNo,departmentUnitArray,accessType, _userVO);
	}
	
	public static Map getPatientOperationTemplateDetails(String crNo,String deptCode,String reqNo, UserVO _userVO) 
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientOperationTemplateDetails(crNo, deptCode, reqNo, _userVO);
	}
	
	public static ANCDetailVO[] getPatientAncDetails(ANCDetailVO _ancDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getPatientAncDetails(_ancDetailVO,departmentUnitArray,accessType, _userVO);
	}

	public static List getDepartmentUnitListForEMR(UserVO userVO) {
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getDepartmentUnitListForEMR(userVO);
	}

	public static List getPatientProfilesForAll(String patCrNo,
			String[] departmentUnitArray, String accessType, UserVO userVO) {
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientProfilesForAll(patCrNo, departmentUnitArray, accessType, userVO);
	}

	public static Map getPatientVisitSummary(EpisodeVO episodeVO,
			String[] departmentUnitArray, String accessType, UserVO userVO) {
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientVisitSummaryForEMR(episodeVO, departmentUnitArray, accessType, userVO);
	}

	public static List getPatientCRNoMergeList(CrNoMergeDtlVO voCRMerge, String[] departmentUnitArray, String accessType, UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getPatientCRNoMergeList(voCRMerge, departmentUnitArray, accessType, userVO);
	}
	
	public static JsonElement getPatientEMR(PatientVO _patientVO,UserVO _userVO,HttpServletRequest _request)
	{
		MrdBO boMRD = new MrdBO();
		return boMRD.getPatientEMR(_patientVO, _userVO,_request);
	}
	
	public static VisitReasonsVO[] getAllVisitDetails(VisitReasonsVO _visitReasonVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBO boMRD = new MrdBO();
		return boMRD.getAllVisitDetails(_visitReasonVO,departmentUnitArray,accessType, _userVO);
	}
	
	public static Service_Req_dtlVO[] getAllServiceProcedures(Service_Req_dtlVO _serviceReqDtlVOs,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBO boMRD = new MrdBO();
		return boMRD.getAllServiceProcedures(_serviceReqDtlVOs,departmentUnitArray,accessType, _userVO);
	}
	
	public static DocumentUploadDtlVO[] getAllDocUploaded(DocumentUploadDtlVO _documentUploadDtlVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBO boMRD = new MrdBO();
		return boMRD.getAllDocUploaded(_documentUploadDtlVO,departmentUnitArray,accessType, _userVO);
	}
	
	//Added By Shweta on 09-MAY-2019
	public static List getDocumentArchivalEssentials(String patCrNo,String episodeCode,UserVO userVO){
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getDocumentArchivalEssentials(patCrNo,episodeCode,userVO);
		
	}
	
	//Added By Shweta for fetching External Treatment Detail on 15-May-2019
		public static PatDrugTreatmentDetailVO[] getPateintExtTreatmentDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
		{
			MrdDelegate mrdDelegate=new MrdDelegate();
			return mrdDelegate.getPateintExtTreatmentDetails(_patDrugTreatmentDetailVO,departmentUnitArray,accessType, _userVO);
		}
	
}
