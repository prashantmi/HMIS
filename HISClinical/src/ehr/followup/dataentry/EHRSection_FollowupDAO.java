package ehr.followup.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import registration.RegistrationConfig;
import ehr.EHRConfig;
import ehr.followup.vo.EHRSection_FollowupVO;

public class EHRSection_FollowupDAO extends DataAccessObject {

	public EHRSection_FollowupDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	


	

	public List<EHRSection_FollowupVO> retrieveAllVisitsByEpisodeNo(
			EHRSection_FollowupVO followVO, UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_VisitReasonDAO");
		String pmode="1";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
		EHRSection_FollowupVO followupVO[] = null;
	/*
		String source=followVO.getPatAdmNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISIPD;
		else
			source=OpdConfig.SOURCE_ISOPD;
	*/
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_FOLLOW_UP);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (followVO.getPatCrNo()==null) ? "":followVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodeCode", (followVO.getEpisodeCode()==null) ? "": followVO.getEpisodeCode(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitNo", (followVO.getEpisodeVisitNo()==null) ? "": followVO.getEpisodeVisitNo(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 6);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,4);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor
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
			throw new HisDataAccessException("EHRSection_VisitReasonDAO_Create::hisDAO_p.execute" + EHRConfig.GET_FOLLOW_UP
					+ ") -> " + e.getMessage());
		}
		//ValueObject vo[] = null;
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
		List<EHRSection_FollowupVO> lstEpisodeDetail = new ArrayList<EHRSection_FollowupVO>();
		try
		{
				
			vo = HelperMethods.populateVOfrmRS(EHRSection_FollowupVO.class, objResSet);
			for(ValueObject o : vo)
				lstEpisodeDetail.add((EHRSection_FollowupVO)o);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
		else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return lstEpisodeDetail;
	}

	public List<EHRSection_FollowupVO> getAllEpisodeKeywordList(UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_VisitReasonDAO");
		String pmode="3";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
		EHRSection_FollowupVO followupVO[] = null;
	/*
		String source=followVO.getPatAdmNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISIPD;
		else
			source=OpdConfig.SOURCE_ISOPD;
	*/
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_FOLLOW_UP);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", "sdas",2);
		hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodeCode", "4",4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitNo", "5",5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 6);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,4);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor
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
			throw new HisDataAccessException("EHRSection_VisitReasonDAO_Create::hisDAO_p.execute" + EHRConfig.GET_FOLLOW_UP
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
		List<EHRSection_FollowupVO> lstEpisodeSummary = new ArrayList<EHRSection_FollowupVO>();
		try
		{
				
			vo = HelperMethods.populateVOfrmRS(EHRSection_FollowupVO.class, objResSet);
			for(ValueObject o : vo)
				lstEpisodeSummary.add((EHRSection_FollowupVO)o);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
		else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return lstEpisodeSummary;
	}

	

	public List<EHRSection_FollowupVO> getAllVisitSummaryByEpisodeVisit(
			EHRSection_FollowupVO followVO, UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_VisitReasonDAO");
		String pmode="2";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
		EHRSection_FollowupVO followupVO[] = null;
	/*
		String source=followVO.getPatAdmNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISIPD;
		else
			source=OpdConfig.SOURCE_ISOPD;
	*/
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_FOLLOW_UP);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (followVO.getPatCrNo()==null) ? "":followVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodeCode", (followVO.getEpisodeCode()==null) ? "": followVO.getEpisodeCode(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitNo", (followVO.getEpisodeVisitNo()==null) ? "": followVO.getEpisodeVisitNo(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 6);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,4);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor
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
			throw new HisDataAccessException("EHRSection_VisitReasonDAO_Create::hisDAO_p.execute" + EHRConfig.GET_FOLLOW_UP
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
		List<EHRSection_FollowupVO> lstKeywords = new ArrayList<EHRSection_FollowupVO>();
		try
		{
				
			vo = HelperMethods.populateVOfrmRS(EHRSection_FollowupVO.class, objResSet);
			for(ValueObject o : vo)
				lstKeywords.add((EHRSection_FollowupVO)o);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
		else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return lstKeywords;
	}



	public void updateDailyPatientDetail(EHRSection_FollowupVO followVO,
			UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_FollowupDAO");
		String pmode="1";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
	
		String strEpisodeVisitNo= "", strConsentNo="";
		if(followVO.getEpisodeVisitNo()!=null)
		strEpisodeVisitNo= followVO.getEpisodeVisitNo().replace("^","@");
		 strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		try
		{
		
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_FOLLOW_UP_DETAILS);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_open", ( followVO.getEpisodeIsOpen()==null) ? "": followVO.getEpisodeIsOpen(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_confirm", (followVO.getIsConfirmed()==null) ? "": followVO.getIsConfirmed(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitnotes", ( followVO.getVisitNotes()==null) ? "":  followVO.getVisitNotes(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitdate", (followVO.getEpisodeNextVisitDate()==null) ? "": followVO.getEpisodeNextVisitDate(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitcriteria", (followVO.getNextVisitCriteria()==null) ? "": followVO.getNextVisitCriteria(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_empId", (userVO.getUserEmpID()==null) ? "":userVO.getUserEmpID(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitDu", (followVO.getNextVisitDuration()==null) ? "":followVO.getNextVisitDuration(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_seatId", (userVO.getSeatId()==null) ? "":userVO.getSeatId(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epkeyword", (followVO.getEpisodeKeywords()==null) ? "":followVO.getEpisodeKeywords(),10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epSummary", (followVO.getEpisodeSummary()==null) ? "":followVO.getEpisodeSummary(),11);
		
		// updating appointment detail, when selected patient unit is apppointment based
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptNo", (followVO.getPatNextAptNo()==null) ? "":followVO.getPatNextAptNo(),12);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptQueNo", (followVO.getPatNextAptQueueNo()==null) ? "":followVO.getPatNextAptQueueNo(),13);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptSlot", (followVO.getPatNextAptSlot()==null) ? "":followVO.getPatNextAptSlot(),14);
		
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", (followVO.getPatCrNo()==null) ? "":followVO.getPatCrNo(),15);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (followVO.getEpisodeCode()==null) ? "":followVO.getEpisodeCode(),16);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", (followVO.getEpisodeVisitNo()==null) ? "":followVO.getEpisodeVisitNo(),17);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "":userVO.getHospitalCode(),18);
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_closedate", (followVO.getEpisodeCloseDate()==null) ? "":followVO.getEpisodeCloseDate(),19);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdkeyword", (followVO.getSnomdCIdKeywords()==null) ? "":followVO.getSnomdCIdKeywords(),20);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTkeyword", (followVO.getSnomdPTKeywords()==null) ? "":followVO.getSnomdPTKeywords(),21);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdvisitnotes", (followVO.getSnomdCIdVisitNotes()==null) ? "":followVO.getSnomdCIdVisitNotes(),22);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTvisitnotes", (followVO.getSnomdPTVisitNotes()==null) ? "":followVO.getSnomdPTVisitNotes(),23);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdepSumry", (followVO.getSnomdCIdEpisodeSummary()==null) ? "":followVO.getSnomdCIdEpisodeSummary(),24);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPtepSumry", (followVO.getSnomdPTEpisodeSummary()==null) ? "":followVO.getSnomdPTEpisodeSummary(),25);
	
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epTypecode", (followVO.getEpisodeTypeCode()==null) ? "":followVO.getEpisodeTypeCode(),26);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admno", (followVO.getAdmissionNo()==null) ? "":followVO.getAdmissionNo(),27);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epdate", (followVO.getEpisodeDate()==null) ? "":followVO.getEpisodeDate(),28);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epcloseType", (followVO.getEpisodeCloseType()==null) ? "":followVO.getEpisodeCloseType(),29);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE,30);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_autocloseno", RegistrationConfig.EPISODE_AUTO_CLOSE_NO,31);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_id", (followVO.getRequestByName()==null) ? "":followVO.getRequestById(),32);

		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,33); // varchar
		
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		//objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_FollowupDAO_create::hisDAO_p.execute" + EHRConfig.SAVE_FOLLOW_UP_DETAILS
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
		
	}






	public void updateNextVisitDetail(EHRSection_FollowupVO followVO,
			UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_FollowupDAO");
		String pmode="2";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
	
		String strEpisodeVisitNo= "", strConsentNo="";
		if(followVO.getEpisodeVisitNo()!=null)
		strEpisodeVisitNo= followVO.getEpisodeVisitNo().replace("^","@");
		 strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		try
		{
		
	
			
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_FOLLOW_UP_DETAILS);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_open", ( followVO.getEpisodeIsOpen()==null) ? "": followVO.getEpisodeIsOpen(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_confirm", (followVO.getIsConfirmed()==null) ? "": followVO.getIsConfirmed(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitnotes", ( followVO.getVisitNotes()==null) ? "":  followVO.getVisitNotes(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitdate", (followVO.getEpisodeNextVisitDate()==null) ? "": followVO.getEpisodeNextVisitDate(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitcriteria", (followVO.getNextVisitCriteria()==null) ? "": followVO.getNextVisitCriteria(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_empId", (followVO.getRequestById()==null) ? "":followVO.getRequestById(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitDu", (followVO.getNextVisitDuration()==null) ? "":followVO.getNextVisitDuration(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_seatId", (userVO.getSeatId()==null) ? "":userVO.getSeatId(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epkeyword", (followVO.getEpisodeKeywords()==null) ? "":followVO.getEpisodeKeywords(),10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epSummary", (followVO.getEpisodeSummary()==null) ? "":followVO.getEpisodeSummary(),11);
		
		// updating appointment detail, when selected patient unit is apppointment based
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptNo", (followVO.getPatNextAptNo()==null) ? "":followVO.getPatNextAptNo(),12);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptQueNo", (followVO.getPatNextAptQueueNo()==null) ? "":followVO.getPatNextAptQueueNo(),13);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptSlot", (followVO.getPatNextAptSlot()==null) ? "":followVO.getPatNextAptSlot(),14);
		
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", (followVO.getPatCrNo()==null) ? "":followVO.getPatCrNo(),15);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (followVO.getEpisodeCode()==null) ? "":followVO.getEpisodeCode(),16);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", (followVO.getEpisodeVisitNo()==null) ? "":followVO.getEpisodeVisitNo(),17);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "":userVO.getHospitalCode(),18);
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_closedate", (followVO.getEpisodeCloseDate()==null) ? "":followVO.getEpisodeCloseDate(),19);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdkeyword", (followVO.getSnomdCIdKeywords()==null) ? "":followVO.getSnomdCIdKeywords(),20);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTkeyword", (followVO.getSnomdPTKeywords()==null) ? "":followVO.getSnomdPTKeywords(),21);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdvisitnotes", (followVO.getSnomdCIdVisitNotes()==null) ? "":followVO.getSnomdCIdVisitNotes(),22);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTvisitnotes", (followVO.getSnomdPTVisitNotes()==null) ? "":followVO.getSnomdPTVisitNotes(),23);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdepSumry", (followVO.getSnomdCIdEpisodeSummary()==null) ? "":followVO.getSnomdCIdEpisodeSummary(),24);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPtepSumry", (followVO.getSnomdPTEpisodeSummary()==null) ? "":followVO.getSnomdPTEpisodeSummary(),25);
	
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epTypecode", (followVO.getEpisodeTypeCode()==null) ? "":followVO.getEpisodeTypeCode(),26);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admno", (followVO.getAdmissionNo()==null) ? "":followVO.getAdmissionNo(),27);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epdate", (followVO.getEpisodeDate()==null) ? "":followVO.getEpisodeDate(),28);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epcloseType", (followVO.getEpisodeCloseType()==null) ? "":followVO.getEpisodeCloseType(),29);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE,30);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_autocloseno", RegistrationConfig.EPISODE_AUTO_CLOSE_NO,31);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_id", (followVO.getRequestById()==null) ? "":followVO.getRequestById(),32);

		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,33); // varchar
		
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		//objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_FollowupDAO_create::hisDAO_p.execute" + EHRConfig.SAVE_FOLLOW_UP_DETAILS
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
		
	}






	public void create(EHRSection_FollowupVO followVO, UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_FollowupDAO");
		String pmode="3";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
	
		String strEpisodeVisitNo= "", strConsentNo="";
		if(followVO.getEpisodeVisitNo()!=null)
		strEpisodeVisitNo= followVO.getEpisodeVisitNo().replace("^","@");
		 strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		try
		{
		
			
		
			
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_FOLLOW_UP_DETAILS);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_open", ( followVO.getEpisodeIsOpen()==null) ? "": followVO.getEpisodeIsOpen(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_confirm", (followVO.getIsConfirmed()==null) ? "": followVO.getIsConfirmed(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitnotes", ( followVO.getVisitNotes()==null) ? "":  followVO.getVisitNotes(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitdate", (followVO.getEpisodeNextVisitDate()==null) ? "": followVO.getEpisodeNextVisitDate(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitcriteria", (followVO.getNextVisitCriteria()==null) ? "": followVO.getNextVisitCriteria(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_empId", (userVO.getUserEmpID()==null) ? "":userVO.getUserEmpID(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitDu", (followVO.getNextVisitDuration()==null) ? "":followVO.getNextVisitDuration(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_seatId", (userVO.getSeatId()==null) ? "":userVO.getSeatId(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epkeyword", (followVO.getEpisodeKeywords()==null) ? "":followVO.getEpisodeKeywords(),10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epSummary", (followVO.getEpisodeSummary()==null) ? "":followVO.getEpisodeSummary(),11);
		
		// updating appointment detail, when selected patient unit is apppointment based
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptNo", (followVO.getPatNextAptNo()==null) ? "":followVO.getPatNextAptNo(),12);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptQueNo", (followVO.getPatNextAptQueueNo()==null) ? "":followVO.getPatNextAptQueueNo(),13);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptSlot", (followVO.getPatNextAptSlot()==null) ? "":followVO.getPatNextAptSlot(),14);
		
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", (followVO.getPatCrNo()==null) ? "":followVO.getPatCrNo(),15);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (followVO.getEpisodeCode()==null) ? "":followVO.getEpisodeCode(),16);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", (followVO.getEpisodeVisitNo()==null) ? "":followVO.getEpisodeVisitNo(),17);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "":userVO.getHospitalCode(),18);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_closedate", (followVO.getEpisodeCloseDate()==null) ? "":followVO.getEpisodeCloseDate(),19);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdkeyword", (followVO.getSnomdCIdKeywords()==null) ? "":followVO.getSnomdCIdKeywords(),20);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTkeyword", (followVO.getSnomdPTKeywords()==null) ? "":followVO.getSnomdPTKeywords(),21);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdvisitnotes", (followVO.getSnomdCIdVisitNotes()==null) ? "":followVO.getSnomdCIdVisitNotes(),22);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTvisitnotes", (followVO.getSnomdPTVisitNotes()==null) ? "":followVO.getSnomdPTVisitNotes(),23);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdepSumry", (followVO.getSnomdCIdEpisodeSummary()==null) ? "":followVO.getSnomdCIdEpisodeSummary(),24);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPtepSumry", (followVO.getSnomdPTEpisodeSummary()==null) ? "":followVO.getSnomdPTEpisodeSummary(),25);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epTypecode", (followVO.getEpisodeTypeCode()==null) ? "":followVO.getEpisodeTypeCode(),26);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admno", (followVO.getAdmissionNo()==null) ? "":followVO.getAdmissionNo(),27);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epdate", (followVO.getEpisodeDate()==null) ? "":followVO.getEpisodeDate(),28);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epcloseType", (followVO.getEpisodeCloseType()==null) ? "":followVO.getEpisodeCloseType(),29);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE,30);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_autocloseno", RegistrationConfig.EPISODE_AUTO_CLOSE_NO,31);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_id", (followVO.getRequestByName()==null) ? "":followVO.getRequestById(),32);

		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,33); // varchar
		
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		//objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_FollowupDAO_create::hisDAO_p.execute" + EHRConfig.SAVE_FOLLOW_UP_DETAILS
					+ ") -> " + e.getMessage());
		}
		
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
	}






	public void updateIsconfirmed(EHRSection_FollowupVO followVO, UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_FollowupDAO");
		String pmode="5";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
		String strEpisodeVisitNo= "", strConsentNo="";
		if(followVO.getEpisodeVisitNo()!=null)
		strEpisodeVisitNo= followVO.getEpisodeVisitNo().replace("^","@");
		 strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		try
		{
		
			
		
			
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_FOLLOW_UP_DETAILS);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_open", ( followVO.getEpisodeIsOpen()==null) ? "": followVO.getEpisodeIsOpen(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_confirm", (followVO.getIsConfirmed()==null) ? "": followVO.getIsConfirmed(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitnotes", ( followVO.getVisitNotes()==null) ? "":  followVO.getVisitNotes(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitdate", (followVO.getEpisodeNextVisitDate()==null) ? "": followVO.getEpisodeNextVisitDate(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitcriteria", (followVO.getNextVisitCriteria()==null) ? "": followVO.getNextVisitCriteria(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_empId", (userVO.getUserEmpID()==null) ? "":userVO.getUserEmpID(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitDu", (followVO.getNextVisitDuration()==null) ? "":followVO.getNextVisitDuration(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_seatId", (userVO.getSeatId()==null) ? "":userVO.getSeatId(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epkeyword", (followVO.getEpisodeKeywords()==null) ? "":followVO.getEpisodeKeywords(),10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epSummary", (followVO.getEpisodeSummary()==null) ? "":followVO.getEpisodeSummary(),11);
		
		// updating appointment detail, when selected patient unit is apppointment based
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptNo", (followVO.getPatNextAptNo()==null) ? "":followVO.getPatNextAptNo(),12);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptQueNo", (followVO.getPatNextAptQueueNo()==null) ? "":followVO.getPatNextAptQueueNo(),13);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptSlot", (followVO.getPatNextAptSlot()==null) ? "":followVO.getPatNextAptSlot(),14);
		
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", (followVO.getPatCrNo()==null) ? "":followVO.getPatCrNo(),15);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (followVO.getEpisodeCode()==null) ? "":followVO.getEpisodeCode(),16);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", (followVO.getEpisodeVisitNo()==null) ? "":followVO.getEpisodeVisitNo(),17);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "":userVO.getHospitalCode(),18);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_closedate", (followVO.getEpisodeCloseDate()==null) ? "":followVO.getEpisodeCloseDate(),19);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdkeyword", (followVO.getSnomdCIdKeywords()==null) ? "":followVO.getSnomdCIdKeywords(),20);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTkeyword", (followVO.getSnomdPTKeywords()==null) ? "":followVO.getSnomdPTKeywords(),21);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdvisitnotes", (followVO.getSnomdCIdVisitNotes()==null) ? "":followVO.getSnomdCIdVisitNotes(),22);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTvisitnotes", (followVO.getSnomdPTVisitNotes()==null) ? "":followVO.getSnomdPTVisitNotes(),23);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdepSumry", (followVO.getSnomdCIdEpisodeSummary()==null) ? "":followVO.getSnomdCIdEpisodeSummary(),24);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPtepSumry", (followVO.getSnomdPTEpisodeSummary()==null) ? "":followVO.getSnomdPTEpisodeSummary(),25);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epTypecode", (followVO.getEpisodeTypeCode()==null) ? "":followVO.getEpisodeTypeCode(),26);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admno", (followVO.getAdmissionNo()==null) ? "":followVO.getAdmissionNo(),27);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epdate", (followVO.getEpisodeDate()==null) ? "":followVO.getEpisodeDate(),28);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epcloseType", (followVO.getEpisodeCloseType()==null) ? "":followVO.getEpisodeCloseType(),29);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE,30);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_autocloseno", RegistrationConfig.EPISODE_AUTO_CLOSE_NO,31);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_id", (followVO.getRequestByName()==null) ? "":followVO.getRequestById(),32);

		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,33); // varchar
		
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		//objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_FollowupDAO_create::hisDAO_p.execute" + EHRConfig.SAVE_FOLLOW_UP_DETAILS
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
	}






	public void updateNextVisitAuditLog(EHRSection_FollowupVO followVO,
			UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_FollowupDAO");
		String pmode="4";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
	
		String strEpisodeVisitNo= "", strConsentNo="";
		if(followVO.getEpisodeVisitNo()!=null)
		strEpisodeVisitNo= followVO.getEpisodeVisitNo().replace("^","@");
		 strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		
		try
		{
		
			
		
			
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_FOLLOW_UP_DETAILS);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_open", ( followVO.getEpisodeIsOpen()==null) ? "": followVO.getEpisodeIsOpen(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_is_confirm", (followVO.getIsConfirmed()==null) ? "": followVO.getIsConfirmed(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitnotes", ( followVO.getVisitNotes()==null) ? "":  followVO.getVisitNotes(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitdate", (followVO.getEpisodeNextVisitDate()==null) ? "": followVO.getEpisodeNextVisitDate(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitcriteria", (followVO.getNextVisitCriteria()==null) ? "": followVO.getNextVisitCriteria(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_empId", (userVO.getUserEmpID()==null) ? "":userVO.getUserEmpID(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitDu", (followVO.getNextVisitDuration()==null) ? "":followVO.getNextVisitDuration(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_seatId", (userVO.getSeatId()==null) ? "":userVO.getSeatId(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epkeyword", (followVO.getEpisodeKeywords()==null) ? "":followVO.getEpisodeKeywords(),10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epSummary", (followVO.getEpisodeSummary()==null) ? "":followVO.getEpisodeSummary(),11);
		
		// updating appointment detail, when selected patient unit is apppointment based
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptNo", (followVO.getPatNextAptNo()==null) ? "":followVO.getPatNextAptNo(),12);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptQueNo", (followVO.getPatNextAptQueueNo()==null) ? "":followVO.getPatNextAptQueueNo(),13);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_aptSlot", (followVO.getPatNextAptSlot()==null) ? "":followVO.getPatNextAptSlot(),14);
		
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", (followVO.getPatCrNo()==null) ? "":followVO.getPatCrNo(),15);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (followVO.getEpisodeCode()==null) ? "":followVO.getEpisodeCode(),16);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", (followVO.getEpisodeVisitNo()==null) ? "":followVO.getEpisodeVisitNo(),17);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "":userVO.getHospitalCode(),18);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_closedate", (followVO.getEpisodeCloseDate()==null) ? "":followVO.getEpisodeCloseDate(),19);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdkeyword", (followVO.getSnomdCIdKeywords()==null) ? "":followVO.getSnomdCIdKeywords(),20);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTkeyword", (followVO.getSnomdPTKeywords()==null) ? "":followVO.getSnomdPTKeywords(),21);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdvisitnotes", (followVO.getSnomdCIdVisitNotes()==null) ? "":followVO.getSnomdCIdVisitNotes(),22);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPTvisitnotes", (followVO.getSnomdPTVisitNotes()==null) ? "":followVO.getSnomdPTVisitNotes(),23);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCIdepSumry", (followVO.getSnomdCIdEpisodeSummary()==null) ? "":followVO.getSnomdCIdEpisodeSummary(),24);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPtepSumry", (followVO.getSnomdPTEpisodeSummary()==null) ? "":followVO.getSnomdPTEpisodeSummary(),25);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epTypecode", (followVO.getEpisodeTypeCode()==null) ? "":followVO.getEpisodeTypeCode(),26);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admno", (followVO.getAdmissionNo()==null) ? "":followVO.getAdmissionNo(),27);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epdate", (followVO.getEpisodeDate()==null) ? "":followVO.getEpisodeDate(),28);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_epcloseType", (followVO.getEpisodeCloseType()==null) ? "":followVO.getEpisodeCloseType(),29);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE,30);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_autocloseno", RegistrationConfig.EPISODE_AUTO_CLOSE_NO,31);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_id", (followVO.getRequestByName()==null) ? "":followVO.getRequestById(),32);

		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,33); // varchar
		
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		//objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_FollowupDAO_create::hisDAO_p.execute" + EHRConfig.SAVE_FOLLOW_UP_DETAILS
					+ ") -> " + e.getMessage());
		}
		
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
	}	
	
	
	//Added by Vasu on 18.Dec.2018 for Discharge FollowUP
	public List getDischargeStatusList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_DISCHARGE_STATUS.HIPT_DISCHARGE_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	
	public String getPatientProfileStatus(PatientDetailVO patientVO,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		String profileStatus="";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "GET_PROFILE_STATUS.HPMRT_PAT_PROFILE_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{	
			populateMAP.put(sq.next(), OpdConfig.PROFILE_TYPE_DISCHARGE);
			populateMAP.put(sq.next(), patientVO.getPatCrNo());
			populateMAP.put(sq.next(), patientVO.getEpisodeCode());
			populateMAP.put(sq.next(), patientVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), patientVO.getPatAdmNo());
			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				profileStatus="0";
			}
			else
				profileStatus=rs.getString(1); 
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return profileStatus;
	}
	
	public boolean checkPatientStatus(String admNo,UserVO userVO)
	{
		boolean flag=false;
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	    String queryKey="SELECT_PAT_STATUS.HIPT_PATADMISSION_DTL";
	    Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMAP.put(sq.next(), admNo);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		
	    try
	    {
	        query =HelperMethodsDAO.getQuery(filename,queryKey);
	        
	    }     
	    catch(Exception e)
	    {
	    	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if (rs.next())
			{
				 rs.first();
				 if(rs.getString(1).equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED))
					 flag=true;
				 else
					 flag=false;
			}
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return flag;
	}
	
	public EHRSection_FollowupVO getDischargeRemarks(String admNo,UserVO userVO)
	{
		EHRSection_FollowupVO patDischargeReqVO=new EHRSection_FollowupVO();
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.HIPT_PATDISCHARGE_REQ_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), admNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), InpatientConfig.DISCHARGE_REQUEST_TYPE_REQUEST);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Remarks Found");
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(patDischargeReqVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return patDischargeReqVO;
	}
    
	//Added by Vasu on 03.Jan.2019 to save Discharge FollowUp Details
	public void saveDischargeFollowUpDetails(EHRSection_FollowupVO followVO, UserVO userVO) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_FollowupDAO");
		String pmode="1";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;

		try
		{
		
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_DISC_FOLLOW_UP_DETAILS);
		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_puk",followVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_episode_code", followVO.getEpisodeCode(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_visit_no",followVO.getEpisodeVisitNo(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hipnum_slno","",5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_admission_no",followVO.getAdmissionNo(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hipstr_remarks",followVO.getDischargeRemarks(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hipdt_next_visit_date",followVO.getNextVisitDate(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seat_id",userVO.getSeatId(),10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_gdt_entry_date","",11);
		
	
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hipnum_req_type",followVO.getRequestType(),12);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_psrstr_emp_no","",13);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",(userVO.getHospitalCode()==null) ? "":userVO.getHospitalCode(),14);		
		hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ip_add",userVO.getIpAddress(),15);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hipdt_tent_disdatetime","",16);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hipdt_disstatus_code",followVO.getDischargeStatus(),17);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_psrstr_emp_name",followVO.getRequestByName(),18);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_psrstr_emp_desig_code","",19);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_psrstr_emp_desig_name","",20);
		
		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,21); // varchar
		
		// Executing Procedure 
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		// Getting out parameters 
		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		//objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		// If Database Error Occurs, No further processing is required. 
		if (strDBErr != null && !strDBErr.equals(""))
		{
			throw new Exception("Data Base Error:" + strDBErr);
		}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_FollowupDAO_create::hisDAO_p.execute" + EHRConfig.SAVE_FOLLOW_UP_DETAILS
					+ ") -> " + e.getMessage());
		}
		
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
	}
	
	//Added by Vasu on 29.Jan.2019
	public void saveSinglePageDischargeFollowUpDetails(EHRSection_FollowupVO followVO, UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HIPT_PATDISCHARGE_REQ_DTL.SINGLE_PAGE_DISCHARGE";
        
       
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try
        {
        	populateMAP.put(sq.next(), followVO.getPatCrNo());
        	populateMAP.put(sq.next(), followVO.getEpisodeCode());
        	populateMAP.put(sq.next(), followVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), followVO.getPatAdmNo());
        	populateMAP.put(sq.next(), followVO.getPatAdmNo());
        	populateMAP.put(sq.next(), followVO.getDischargeRemarks());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), followVO.getNextVisitDate());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(), followVO.getRequestType());
        	//populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getIpAddress());
        	populateMAP.put(sq.next(),followVO.getDischargeStatus());
        	populateMAP.put(sq.next(),followVO.getRequestByName());
        	populateMAP.put(sq.next(),followVO.getDischargePreparedByName());
        	populateMAP.put(sq.next(),followVO.getDischargeApprovedByName());
        	populateMAP.put(sq.next(),followVO.getDischargeDate());
        	populateMAP.put(sq.next(),followVO.getDischargePreparedDate());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatDischargeRequestDAO.populateMAP::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	
	//Added By Vasu on 04.Jan.2018
	public EHRSection_FollowupVO getDischargeFollowUpDetails(EHRSection_FollowupVO followUpVO,UserVO userVO)
	{
		//EHRSection_FollowupVO patDischargeReqVO=new EHRSection_FollowupVO();
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.HIPT_PATDISCHARGE_REQ_DTL.DISCHARGE_SUMMARY";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(),followUpVO.getAdmissionNo());
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(),followUpVO.getPatCrNo());
        populateMAP.put(sq.next(),followUpVO.getEpisodeCode());
        populateMAP.put(sq.next(),followUpVO.getEpisodeVisitNo());
        populateMAP.put(sq.next(),followUpVO.getAdmissionNo());
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(),followUpVO.getPatCrNo());
        populateMAP.put(sq.next(),followUpVO.getEpisodeCode());
        populateMAP.put(sq.next(),followUpVO.getEpisodeVisitNo());
        
        populateMAP.put(sq.next(),followUpVO.getAdmissionNo());
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(),followUpVO.getAdmissionNo());
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        populateMAP.put(sq.next(),followUpVO.getPatCrNo());
        populateMAP.put(sq.next(),followUpVO.getEpisodeCode());
        populateMAP.put(sq.next(),followUpVO.getEpisodeVisitNo());
        populateMAP.put(sq.next(),followUpVO.getEpisodeCode());
        populateMAP.put(sq.next(),followUpVO.getEpisodeVisitNo());
        populateMAP.put(sq.next(),followUpVO.getPatCrNo());
       
        try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Remarks Found");
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(followUpVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return followUpVO;
	}
	
	//Added by Vasu on 29.Jan.2019
	
	public void updatePatStatusForDischargeReq(String patStatus,EHRSection_FollowupVO followUpVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE_PAT_STATUS.HIPT_PATADMISSION_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(), patStatus);
			  populateMAP.put(sq.next(), followUpVO.getIsDead());
			  populateMAP.put(sq.next(), followUpVO.getNextVisitDate());
			  populateMAP.put(sq.next(), followUpVO.getDischargeStatus());
			  populateMAP.put(sq.next(), followUpVO.getRemarks());
			  populateMAP.put(sq.next(), followUpVO.getAdviceBy());
			  populateMAP.put(sq.next(), followUpVO.getPatAdmNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
	
	public void updatePatStatusForDischargeRevoke(String patStatus,EHRSection_FollowupVO followUpVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE_PAT_STATUS_REVOKE.HIPT_PATADMISSION_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(), patStatus);
			  populateMAP.put(sq.next(), followUpVO.getIsDead());
			  populateMAP.put(sq.next(), followUpVO.getNextVisitDate());
			  populateMAP.put(sq.next(), followUpVO.getDischargeStatus());
			  populateMAP.put(sq.next(), followUpVO.getRemarks());
			  populateMAP.put(sq.next(), followUpVO.getAdviceBy());
			  populateMAP.put(sq.next(), "");
			  populateMAP.put(sq.next(), followUpVO.getPatAdmNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}






	public void updatePatStatusForDischargeReqInPatADMDiscTable(String status,EHRSection_FollowupVO followVO, UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE_PAT_STATUS.HIPT_PATADMDISC_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(), status);
			  populateMAP.put(sq.next(), followVO.getIsDead());
			  populateMAP.put(sq.next(), followVO.getNextVisitDate());
			  populateMAP.put(sq.next(), followVO.getDischargeStatus());
			  populateMAP.put(sq.next(), followVO.getRemarks());
			  //populateMAP.put(sq.next(), patDischargeReqVO.getAdviceBy());
			  populateMAP.put(sq.next(), followVO.getPatAdmNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
		
	}
	

	
}//end of Class
