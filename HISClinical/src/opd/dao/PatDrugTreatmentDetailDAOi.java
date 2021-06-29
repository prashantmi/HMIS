package opd.dao;

import java.util.List;

import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public interface PatDrugTreatmentDetailDAOi 
{
	/** 
	 * Saving Drug Trteatment Detail Data
	 * @param _patDrugDtlVO Detail
	 * @param _userVO User Detail
	 */
	public void savePatDrugTreatmentDetail(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO);
	public String getDrugAllergyAllerts(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	public String getConsentStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	public void generatConsent(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	public String getPrevDrugStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	public void updateRevoke(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO);
	public PatDrugTreatmentDetailVO[] getPatientTreatmentDetail(String _crNo, PatientDetailVO voDP, String profileGenerationMode,UserVO _userVO);
	public void saveRevokePatDrugTreatmentDetail(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO);
	public void updateLastTodayVisitRecord(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO);
	public PatDrugTreatmentDetailVO[] getIpdPatientTreatmentDetail(String _crNo,String _admissionNo,UserVO _userVO);
	public PatDrugTreatmentDetailVO[] getTreatmentAdviceDetailsEMR(String _crNo,String [] departmentUnitArray,String accessType,UserVO _userVO);
	public List getDrugSaftyAlertList(String itemId,UserVO _userVO);
	public List getDrugDoseIndicationList(String itemId,UserVO _userVO);
	
	// Added By Shweta for fetching External Treatment Detail on 15-May-2019
	public PatDrugTreatmentDetailVO[] getPateintExtTreatmentDetails(String _crNo,String [] departmentUnitArray,String accessType,UserVO _userVO);
//	public PatDrugTreatmentDetailVO[] getOfflineTreatmentAdviceDetailsEMR(String _crNo,String [] departmentUnitArray,String accessType,UserVO _userVO);
	
	public String getMaxSlNo(PatDrugTreatmentDetailVO _patDrugDtlVO,UserVO userVO);
	
	public void savePatDrugTreatmentDetailForPHRMS(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO);//Added by Vasu on 27.March.2019


}
