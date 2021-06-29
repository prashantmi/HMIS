package ehr.visitreason.dataentry;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;

import opd.OpdConfig;
import ehr.EHRConfig;
import ehr.visitreason.vo.EHRSection_VisitReasonVO;

public class EHRSection_VisitReasonDAO extends DataAccessObject {

	public EHRSection_VisitReasonDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public EHRSection_VisitReasonVO[] getEssentials(
			PatientDetailVO visitVO, UserVO userVO,String pmode) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_VisitReasonDAO");
		//String pmode="1";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
		EHRSection_VisitReasonVO visitreasonVO[] = null;
	
		String source=visitVO.getPatAdmNo();
		if(source==null || source.isEmpty())
			source=OpdConfig.SOURCE_ISIPD;
		else
			source=OpdConfig.SOURCE_ISOPD;
	
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_VISIT_REASON);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (visitVO.getPatCrNo()==null) ? "":visitVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", (visitVO.getEpisodeCode()==null) ? "":visitVO.getEpisodeCode(),3);
	//Added by prachi on 27-3-2019
		
		hisDAO_p.setProcInValue(nProcedureIndex, "visit_no", (visitVO.getEpisodeVisitNo()==null) ? "":visitVO.getEpisodeVisitNo(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),5);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,4);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor
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
			throw new HisDataAccessException("EHRSection_VisitReasonDAO_Create::hisDAO_p.execute" + EHRConfig.GET_VISIT_REASON
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		try
		{
			
			
			
			vo = HelperMethods.populateVOfrmRS(EHRSection_VisitReasonVO.class, objResSet);
			visitreasonVO = new EHRSection_VisitReasonVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				visitreasonVO[i] = (EHRSection_VisitReasonVO) vo[i];
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
		return visitreasonVO;
	}

	public void saveDetails(EHRSection_VisitReasonVO visitVO, UserVO userVO,String pmode) {
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_VisitReasonDAO");
		//String pmode="1";
		String strDBErr;
		ResultSet objResSet;
		ValueObject vo[] = null;
		String strEpisodeVisitNo= "", strConsentNo="";
		if(visitVO.getEpisodeVisitNo()!=null)
		{strEpisodeVisitNo= visitVO.getEpisodeVisitNo().replace("^","@");
		strConsentNo=strEpisodeVisitNo.split("\\@")[0];
		}
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_VISIT_REASON_DETAILS);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", (visitVO.getPatCrNo()==null) ? "":visitVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitreason", (visitVO.getEhrVisitReason()==null) ? "": visitVO.getEhrVisitReason(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedPT", (visitVO.getSnomdPTVisitReason()==null) ? "": visitVO.getSnomdPTVisitReason(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_snomedCId", (visitVO.getSnomdCIdVisitReason()==null) ? "": visitVO.getSnomdCIdVisitReason(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (visitVO.getEpisodeCode()==null) ? "":visitVO.getEpisodeCode(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", strConsentNo,8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hpi", visitVO.getPresentIllnessHistory(), 10);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admNo", (visitVO.getAdmissionNo()==null) ? "": visitVO.getAdmissionNo(),11);
		//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,4);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,12); // varchar
		
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
			throw new HisDataAccessException("EHRSection_VisitReasonDAO_Create::hisDAO_p.execute" + EHRConfig.SAVE_VISIT_REASON_DETAILS
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
