/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.ot.data;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import opd.OpdConfig;
import ehr.EHRConfig;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import ehr.ot.vo.EHRSection_OTDetailsVO;
import ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO;
public class EHRSection_OTDetailsDAO extends DataAccessObject 
{

	public EHRSection_OTDetailsDAO(TransactionContext _tx)
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
		finally{
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
		 finally{
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
		
		 finally{
				if (hisDAO_p != null) {
					hisDAO_p.free();
				}
				hisDAO_p = null;
			}

	}

	public List getCompletePatOperationsList(UserVO userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.OT.TYPE.HOTT_OPERATIONS_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No OT Type Found");
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException();
		}
		return alRecord;
	}
	
	public List getSurgeonList(String deptCode, UserVO userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			//Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_PEON_LIST);
			//Added by Dheeraj on 25-Sept-2018
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_EMP_LIST);
			strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
			//strProc.addInParameter(2, Types.VARCHAR, MrdConfig.DECEASED_ACCEPTANCE_BROUGHT_BY_PEON);
			
			// Added by Dheeraj on 25-Sept-2018 to get MRD_Record_Reported by List
			strProc.addInParameter(2, Types.VARCHAR, MrdConfig.PROCESS_ID_SINGLE_PAGE_OPERATING_SURGEONS);
			strProc.addInParameter(3, Types.VARCHAR, deptCode);
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		// log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		List alRecord = new ArrayList();

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}
	
	public static void saveOperationDetails(EHRSection_OTDetailsVO _voEHROTDetails, UserVO _userVO)
	{

		int i=0;
		String strErr = "";
		HisDAO daoObj = null;
		int nProcIndex2 = 0;
		String strProcName2="";
		strProcName2 = "{call pkg_ehr_dml.proc_save_ehrt_pat_operation_dtl(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying)}";
		try
		{
			daoObj = new HisDAO("EHR","EHRSection_OTDetailsDAO");
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk", _voEHROTDetails.getPatCrNo(),1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_episode_code", _voEHROTDetails.getEpisodeCode(),2);
			daoObj.setProcInValue(nProcIndex2, "p_hipnum_admno",  _voEHROTDetails.getAdmissionNo(),3);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_visit_no", (_voEHROTDetails.getEpisodeVisitNo()==null) ? "": _voEHROTDetails.getEpisodeVisitNo().trim(),4);
			daoObj.setProcInValue(nProcIndex2, "p_hotnum_operation_code", _voEHROTDetails.getSelOperationCode(),5);
			daoObj.setProcInValue(nProcIndex2, "p_hotdt_operation_date", _voEHROTDetails.getSelOperationDate(),6);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_seat_id", _userVO.getSeatId(),7);
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_surgeons", _voEHROTDetails.getSelSurgeonName(),8);
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_surgeon_empids",_voEHROTDetails.getSelSergeonCode(),9);
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_operation_remarks",_voEHROTDetails.getSelOperativeFindings(),10);
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_operation_name",_voEHROTDetails.getSelOperationName(),11);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code",_userVO.getHospitalCode(),12);
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_operation_complications",_voEHROTDetails.getSelOperatonComplications(),13);
			
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_operation_implant",_voEHROTDetails.getSelOperatonImplant(),14);
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_operation_PreOp",_voEHROTDetails.getSelOperatonPreOp(),15);
			daoObj.setProcInValue(nProcIndex2, "p_hotstr_operation_PostOp",_voEHROTDetails.getSelOperatonPostOp(),16);
			
			daoObj.setProcOutValue(nProcIndex2, "err", 1,17);

			
			daoObj.executeProcedureByPosition(nProcIndex2);
			//daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
		}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		 finally{
				if (daoObj != null) {
					daoObj.free();
				}
				daoObj = null;
			}
	}
	
	public List<EHRSection_OTDetailsVO> getPreviousOTDetails(EHRSection_OTDetailsVO _voEHROTDetails, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_OT_DETAILS.EHRT_PAT_OPERATION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _voEHROTDetails.getPatCrNo());
		populateMAP.put(sq.next(), _voEHROTDetails.getEpisodeCode());
		populateMAP.put(sq.next(), _voEHROTDetails.getAdmissionNo());
		populateMAP.put(sq.next(), _voEHROTDetails.getEpisodeVisitNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		List<EHRSection_OTDetailsVO> _previousOTVO = new ArrayList<EHRSection_OTDetailsVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(EHRSection_OTDetailsVO.class, rs);			
				for (ValueObject v : vo)
					_previousOTVO.add((EHRSection_OTDetailsVO)v);
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
		return _previousOTVO;
	}

	//Added by Vasu on 30.July.2019
	public void deleteOperationDetails(EHRSection_OTDetailsVO eHROTDetailsVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "DELETE_EHRT_PAT_OPERATION_DTL";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			
			populateMAP.put(sq.next(), eHROTDetailsVO.getPatCrNo());
			populateMAP.put(sq.next(), eHROTDetailsVO.getEpisodeCode());
			populateMAP.put(sq.next(), eHROTDetailsVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), eHROTDetailsVO.getSlno());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMenuMacroMasterDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
}