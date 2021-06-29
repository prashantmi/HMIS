package mrd.transaction.dao;
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	MRD
## Process/Database Object Name	:	Patient Detail Process [Desk, EMR, Profile]
## Purpose						:	To get all patient Centric DATA (like Alert Detail, Diagnosis Detail, Chronic Detail etc.......)
## Date of Creation				: 	12-Jan-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mrd.EMRConfig;
import mrd.vo.EMRPateintDataPolicyVO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EMRPatientDetailDAO
{
	//Logger log;

	/**
	 * Constructor for Setting Transaction Context
	 */
	
/*	public EMRPatientDetailDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}*/

	public List<EpisodeDiagnosisVO> getEpisodeDiagnosisDetail(HisDAO hisDAO_p,String strMode_p, EpisodeDiagnosisVO episodeDiagnosisVO, EMRPateintDataPolicyVO objEMRPateintDataPolicyVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EMRConfig.GET_PAT_EPISODE_DIAGNOSIS_DTL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", episodeDiagnosisVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_visit_no", episodeDiagnosisVO.getEpisodeVisitNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", episodeDiagnosisVO.getEpisodeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EMRPatientDetailDAO.getEpisodeDiagnosisDetail()::hisDAO_p.execute" + EMRConfig.GET_PAT_EPISODE_DIAGNOSIS_DTL 
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<EpisodeDiagnosisVO> lst = new ArrayList<EpisodeDiagnosisVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((EpisodeDiagnosisVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("EMRPatientDetailDAO.getEpisodeDiagnosisDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List<PatDrugTreatmentDetailVO> getEpisodeDrugDetail(HisDAO hisDAO_p,	String strMode_p, PatDrugTreatmentDetailVO patDrugTreatmentDetailVO,EMRPateintDataPolicyVO objEMRPateintDataPolicyVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EMRConfig.GET_PAT_EPISODE_DRUG_DETAIL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patDrugTreatmentDetailVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "sl_no", patDrugTreatmentDetailVO.getSerialNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", patDrugTreatmentDetailVO.getEpisodeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EMRPatientDetailDAO.getEpisodeDrugDetail()::hisDAO_p.execute" + EMRConfig.GET_PAT_EPISODE_DRUG_DETAIL 
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<PatDrugTreatmentDetailVO> lst = new ArrayList<PatDrugTreatmentDetailVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatDrugTreatmentDetailVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatDrugTreatmentDetailVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("EMRPatientDetailDAO.getEpisodeDrugDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List<PatAllergyDtlVO> getPatAllergiesDetails(HisDAO hisDAO_p, String strMode_p, PatAllergyDtlVO patAllergyDtlVO, EMRPateintDataPolicyVO objEMRPateintDataPolicyVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EMRConfig.GET_PAT_ALLERGIES_DETAILS);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patAllergyDtlVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "sl_no", " ",3);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EMRPatientDetailDAO.getPatAllergiesDetails()::hisDAO_p.execute" + EMRConfig.GET_PAT_ALLERGIES_DETAILS 
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<PatAllergyDtlVO> lst = new ArrayList<PatAllergyDtlVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatAllergyDtlVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatAllergyDtlVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("EMRPatientDetailDAO.getPatAllergiesDetails()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List<PatientAlertsDetailVO> getPatChronicDetails(HisDAO hisDAO_p, String strMode_p, PatientAlertsDetailVO patientAlertsDetailVO,	EMRPateintDataPolicyVO objEMRPateintDataPolicyVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EMRConfig.GET_PAT_ALERTS_DETAILS);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patientAlertsDetailVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EMRPatientDetailDAO.getPatChronicDetails()::hisDAO_p.execute" + EMRConfig.GET_PAT_ALERTS_DETAILS 
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<PatientAlertsDetailVO> lst = new ArrayList<PatientAlertsDetailVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientAlertsDetailVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatientAlertsDetailVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("EMRPatientDetailDAO.getPatChronicDetails()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public PatIntakeOutDtlVO[] getPatientOutTakeDetail(HisDAO hisDAO_p, String _crNo,PatientDetailVO voDP, String strMode_p, UserVO _uservo) 
	{
		PatIntakeOutDtlVO[] arrPatOuttakeDtlVO=null;
		ValueObject vo[]= null;
		int nProcedureIndex;
		String strDBErr = null;
		ResultSet rs;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EMRConfig.GET_PAT_ALERTS_DETAILS);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", _crNo,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", _uservo.getHospitalCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((strDBErr != null ? "" : strDBErr) + e);
		}		//List prevRecord = new ArrayList();

		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		 try
	     	{	     			 
	 	 	    if(!rs.next())
	 	 	    {
	 	 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
	 	 	    }
	 	 	    rs.beforeFirst();
	 	 	   
	 	 	    vo=HelperMethods.populateVOfrmRS(PatIntakeOutDtlVO.class, rs);
	 	 	  arrPatOuttakeDtlVO= new PatIntakeOutDtlVO[vo.length];
	 	 	    for(int i=0;i<vo.length;i++)
	 	 	    {
	 	 	    	arrPatOuttakeDtlVO[i]= (PatIntakeOutDtlVO) vo[i];
	 	 	    }
	 	 	}
	 		catch(Exception e)
	 		{
	 			if(e.getClass()==HisRecordNotFoundException.class)
	 			{
	 				throw new HisRecordNotFoundException(e.getMessage());	
	 			}
	 			else			 		
	 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
	 		}
	 		return arrPatOuttakeDtlVO;
		}
	

	public List<ConsultationDtlVO> getConsultation(HisDAO hisDAO_p,String strMode_p, EMRPateintDataPolicyVO voEMRPolicy, DeskDetailVO voDeskMenuDtl, PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet rs;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EMRConfig.GET_CONSULTATION_DETAILS);



			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "seat_id", userVO.getSeatId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EMRPatientDetailDAO.getConsultation()::hisDAO_p.execute" + EMRConfig.GET_CONSULTATION_DETAILS 
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<ConsultationDtlVO> lst = new ArrayList<ConsultationDtlVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(ConsultationDtlVO.class, rs);
				for (ValueObject obj : arrVOs)
					lst.add((ConsultationDtlVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("EMRPatientDetailDAO.getConsultation()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List<ConsentRequestVO> getConsent(HisDAO hisDAO_p, String strMode_p,EMRPateintDataPolicyVO voEMRPolicy, DeskDetailVO voDeskMenuDtl,PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet rs;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(EMRConfig.GET_CONSENT_DETAILS);



			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patientDetailVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", patientDetailVO.getEpisodeCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_visit_no", patientDetailVO.getEpisodeVisitNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "is_revoke", "1",5);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EMRPatientDetailDAO.getConsultation()::hisDAO_p.execute" + EMRConfig.GET_CONSULTATION_DETAILS 
					+ ") -> " + e.getMessage());
		}
		finally{
		if (hisDAO_p != null) {
			hisDAO_p.free();
			
		}
		hisDAO_p = null;
		}
		List<ConsentRequestVO> lst = new ArrayList<ConsentRequestVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(ConsentRequestVO.class, rs);
				for (ValueObject obj : arrVOs)
					lst.add((ConsentRequestVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("EMRPatientDetailDAO.getConsultation()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
}