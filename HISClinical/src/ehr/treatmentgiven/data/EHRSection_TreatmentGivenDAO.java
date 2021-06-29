/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentgiven.data;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import ehr.EHRConfig;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO;
public class EHRSection_TreatmentGivenDAO extends DataAccessObject 
{

	public EHRSection_TreatmentGivenDAO(TransactionContext _tx)
	{
		super(_tx);
	}
		
	public List<EHRSection_TreatmentGivenVO> getPatientTreatmentGivenDetail(String _pmode, EHR_AccessPermissionVO _voEHRAccess, EHR_PatientEncounterVO _voEHREncounter, EHRSection_TreatmentGivenVO _voEHRTreatmentGiven)
	{
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_TreatmentGivenDAO");
		String strDBErr;
		ResultSet objResSet;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_EHR_PATIENT_TREATMENTGIVEN);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", _pmode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", _voEHRTreatmentGiven.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", _voEHRTreatmentGiven.getEpisodeCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", _voEHREncounter.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor
	
			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
	
			// If Database Error Occurs, No further processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_DiagnosisDAO.getPatientDiagnosisDetail::hisDAO_p.execute" + EHRConfig.GET_PREV_EPISODE_DIAGNOSIS_DTL
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		ValueObject vo[] = null;
		List<EHRSection_TreatmentGivenVO> lst = new ArrayList<EHRSection_TreatmentGivenVO>(); 
		try
		{
			vo = HelperMethods.populateVOfrmRS(EHRSection_TreatmentGivenVO.class, objResSet);
			for(ValueObject v :vo)
				lst.add((EHRSection_TreatmentGivenVO)v);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return lst;
	}

	
	
	
	
	
	/*public List getDiagnosisTypeListByDiseaseCode(UserVO _userVO,String str1,String str2)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_DiagnosisDAO");
		String pmode="0";
		String strDBErr;
		ResultSet objResSet;
		
		
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_DIAGNOSIS_TYPE);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", _userVO.getHospitalCode(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE,3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_icd", str1,4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_icd2", str2,5);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); 
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_DiagnosisDAO.getDiagnosisTypeList::hisDAO_p.execute" +EHRConfig.GET_DIAGNOSIS_TYPE
					+ ") -> " + e.getMessage());
		}
		
		try
		{
			if (!objResSet.next())
			{
				throw new HisRecordNotFoundException("No Diagnosis Type Found");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException();
		}
		return alRecord;
	}
	
	public EHRSection_DiagnosisVO[] getPrevDiagnosisDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		EHRSection_DiagnosisVO[] _previousEpisodeDiagVO = null;
		ValueObject vo[] = null;
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_DiagnosisDAO");
		String pmode="0";
		String strDBErr;
		ResultSet objResSet;
		
		
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_PREV_EPISODE_DIAGNOSIS_DTL);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", _patCrNo,2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", _episodeCode,3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", _userVO.getHospitalCode(),4);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_DiagnosisDAO.getPReviousDiagnosis::hisDAO_p.execute" + EHRConfig.GET_PREV_EPISODE_DIAGNOSIS_DTL
					+ ") -> " + e.getMessage());
		}
		
		try
		{
			
			
			
			vo = HelperMethods.populateVOfrmRS(EHRSection_DiagnosisVO.class, objResSet);
			_previousEpisodeDiagVO = new EHRSection_DiagnosisVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_previousEpisodeDiagVO[i] = (EHRSection_DiagnosisVO) vo[i];
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return _previousEpisodeDiagVO;
	}
*/
	
	
	public static void create(EHRSection_TreatmentGivenVO _treatmentGivenVO, UserVO _userVO)
	{
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_TreatmentGivenDAO");
		String pmode="0";
		String strDBErr;
		ResultSet objResSet;
		
		String strEpisodeVisitNo= _treatmentGivenVO.getEpisodeVisitNo().replace("^","@");
		String strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		String source=_treatmentGivenVO.getAdmissionNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISOPD;
		else
			source=OpdConfig.SOURCE_ISIPD;
		if(_treatmentGivenVO.getTreatmentType().equals("Drug"))
		{
			_treatmentGivenVO.setMedicationType(EHRConfig.MEDICATION_TYPE_DRUG);
		}
		else if(_treatmentGivenVO.getTreatmentType().equals("Procedure"))
		{
			_treatmentGivenVO.setMedicationType(EHRConfig.MEDICATION_TYPE_SERVICE_PROCEDURE);
		}
		else
		{
			_treatmentGivenVO.setMedicationType(EHRConfig.MEDICATION_TYPE_INVESTIGATION);
		}
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_TREATMENT_GIVEN);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_puk", (_treatmentGivenVO.getPatCrNo()==null) ? "":_treatmentGivenVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (_treatmentGivenVO.getEpisodeCode()==null) ? "": _treatmentGivenVO.getEpisodeCode(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admissionno", (_treatmentGivenVO.getAdmissionNo()==null) ? "":_treatmentGivenVO.getAdmissionNo(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_medication_type", (_treatmentGivenVO.getMedicationType()==null) ? "":_treatmentGivenVO.getMedicationType(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no",  strConsentNo,6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_treatmentdate",(_treatmentGivenVO.getTreatmentDate()==null) ? "": _treatmentGivenVO.getTreatmentDate(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", _userVO.getSeatId(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_remarks",(_treatmentGivenVO.getRemarks()==null)?"":_treatmentGivenVO.getRemarks(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode",(_userVO.getHospitalCode()==null) ? "":  _userVO.getHospitalCode(),10);
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_empno",(_userVO.getUserEmpID()==null) ? "":  _userVO.getUserEmpID(),11);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedcttreatmentname",(_treatmentGivenVO.getSnomedCTTreatmentName()==null) ? "": _treatmentGivenVO.getSnomedCTTreatmentName(),12);

/*
		hisDAO_p.setProcInValue(nProcedureIndex, "p_treatmenttype",(_treatmentGivenVO.getTreatmentType()==null) ? "": _treatmentGivenVO.getTreatmentType(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedcttreatmentname",(_treatmentGivenVO.getSnomedCTTreatmentName()==null) ? "": _treatmentGivenVO.getSnomedCTTreatmentName(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedcttreatmentcode",(_treatmentGivenVO.getSnomedCTTreatmentCode()==null) ? "": _treatmentGivenVO.getSnomedCTTreatmentCode(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedptremarks",(_treatmentGivenVO.getSnomedPTRemarks()==null) ? "": _treatmentGivenVO.getSnomedPTRemarks(),10);
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedcidremarks",(_treatmentGivenVO.getSnomedCIdRemarks()==null) ? "": _treatmentGivenVO.getSnomedCIdRemarks(),11);*/
		/*hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 14);*/
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_episodedate", (_treatmentGivenVO.getEpisodeDate()==null) ? "": _episodediagVO.getEpisodeDate(),10);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,19);
		hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,13); // varchar
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_DiagnosisDAO_Create::hisDAO_p.execute" + EHRConfig.SAVE_DIAGNOSIS_DETAILS
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}	
	}
	


		
	public static void revokeTreatment(EHRSection_TreatmentGivenVO episodeTreatmentVO,UserVO userVO)
	{
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_TreatmentGiven");
		String pmode="0";
		String strDBErr;
		ResultSet objResSet;
		
		
	
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.REVOKE_TREATMENT_GIVEN);
	    
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crNo",(episodeTreatmentVO.getPatCrNo()==null) ? "":episodeTreatmentVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode",(userVO.getHospitalCode()==null) ? "":  userVO.getHospitalCode(),3);	
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no", (episodeTreatmentVO.getEpisodeVisitNo()==null) ? "":  episodeTreatmentVO.getEpisodeVisitNo(),4);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnosisCodeType",(episodeDiaVO.getDiagnosisCodeType()==null) ? "": episodeDiaVO.getDiagnosisCodeType() ,5);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnosticCode", (episodeDiaVO.getDiagnosticCode()==null) ? "":episodeDiaVO.getDiagnosticCode(),6);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_isRepeat", OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED,7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode",(episodeTreatmentVO.getEpisodeCode()==null) ? "": episodeTreatmentVO.getEpisodeCode(),8);
		hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,9); 
		
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
	

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_DiagnosisDAO.revokeDiagnosis::hisDAO_p.execute" + EHRConfig.REVOKE_DIAGNOSIS
					+ ") -> " + e.getMessage());
		}
		
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}

	}

	
	
}