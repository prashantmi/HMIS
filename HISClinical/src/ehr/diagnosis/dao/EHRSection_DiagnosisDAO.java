/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ehr.EHRConfig;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import opd.OpdConfig;
import registration.RegistrationConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;


import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class EHRSection_DiagnosisDAO extends DataAccessObject 
{

	public EHRSection_DiagnosisDAO(TransactionContext _tx)
	{
		super(_tx);
	}
		
	public List getDiagnosisTypeListByDiseaseCode(UserVO _userVO,String str1,String str2)
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
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
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
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
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

	
	
	public void create(EHRSection_DiagnosisVO _episodediagVO, UserVO _userVO)
	{
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_DiagnosisDAO");
		String pmode="0";
		String strDBErr;
		ResultSet objResSet;
		
		String strEpisodeVisitNo= _episodediagVO.getEpisodeVisitNo().replace("^","@");
		String strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		String source=_episodediagVO.getPatAdmNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISOPD;
		else
			source=OpdConfig.SOURCE_ISIPD;
	
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_DIAGNOSIS_DETAILS);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_puk", (_episodediagVO.getPatCrNo()==null) ? "":_episodediagVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (_episodediagVO.getEpisodeCode()==null) ? "": _episodediagVO.getEpisodeCode(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no",  strConsentNo,4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admissionno", (_episodediagVO.getPatAdmNo()==null) ? "":_episodediagVO.getPatAdmNo(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnostictypecode",(_episodediagVO.getDiagnosticTypeCode()==null) ? "": _episodediagVO.getDiagnosticTypeCode(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnosticcode",(_episodediagVO.getDiagnosticCode()==null) ? "": _episodediagVO.getDiagnosticCode(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", _userVO.getSeatId(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodedate", (_episodediagVO.getEpisodeDate()==null) ? "": _episodediagVO.getEpisodeDate(),10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_remarks", (_episodediagVO.getRemarks()==null) ? "": _episodediagVO.getRemarks(),11);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnosiscode_type",(_episodediagVO.getDiagnosisCodeType()==null) ? "": _episodediagVO.getDiagnosisCodeType(),12);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode",(_userVO.getHospitalCode()==null) ? "":  _userVO.getHospitalCode(),13);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isrepeat",(_episodediagVO.getIsRepeat()==null) ? "": _episodediagVO.getIsRepeat(),14);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diseasesiteid",(_episodediagVO.getDiagnosisSite()==null) ? "": _episodediagVO.getDiagnosisSite(),15);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnostic_name", (_episodediagVO.getDignosisName()==null) ? "":_episodediagVO.getDignosisName(),16);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnostictypename",(_episodediagVO.getDiagnosticTypeCode()==null) ? "": _episodediagVO.getDiagnosticTypeCode(),17);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_disease_site", (_episodediagVO.getDiagnosisSiteLabel()==null) ? "": _episodediagVO.getDiagnosisSiteLabel(),18);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,19);
		hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,20); // varchar
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
	


		
	public void revokeDiagnosis(EHRSection_DiagnosisVO episodeDiaVO,UserVO userVO)
	{
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_DiagnosisDAO");
		String pmode="0";
		String strDBErr;
		ResultSet objResSet;
		
		
	
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.REVOKE_DIAGNOSIS);
	    
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crNo",(episodeDiaVO.getPatCrNo()==null) ? "":episodeDiaVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode",(userVO.getHospitalCode()==null) ? "":  userVO.getHospitalCode(),3);	
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no", (episodeDiaVO.getEpisodeVisitNo()==null) ? "":  episodeDiaVO.getEpisodeVisitNo(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnosisCodeType",(episodeDiaVO.getDiagnosisCodeType()==null) ? "": episodeDiaVO.getDiagnosisCodeType() ,5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_diagnosticCode", (episodeDiaVO.getDiagnosticCode()==null) ? "":episodeDiaVO.getDiagnosticCode(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isRepeat", OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED,7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode",(episodeDiaVO.getEpisodeCode()==null) ? "": episodeDiaVO.getEpisodeCode(),8);
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

	
	
}