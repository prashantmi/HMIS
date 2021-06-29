package ehr.casesummary.dataentry;
import hisglobal.persistence.TransactionContext;
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
import java.util.ArrayList;
import java.util.List;

import opd.OpdConfig;
import registration.RegistrationConfig;
import ehr.EHRConfig;
import ehr.casesummary.vo.EHRSection_CaseSummaryVO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;


public class EHRSection_CaseSummaryDAO extends DataAccessObject
{
	public EHRSection_CaseSummaryDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	

		public EHRSection_CaseSummaryVO getEssentials(EHRSection_CaseSummaryVO summaryVO, UserVO userVO) {
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_CaseSummaryDAO");
			String pmode="1";
			String strDBErr;
			ResultSet objResSet = null;
			ValueObject vo[] = null;
			EHRSection_CaseSummaryVO summaryDtlVO = null;

			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_CASE_SUMMARY);
				// Setting and Registering In and Out Parameters 
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
				hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (summaryVO.getPatCrNo()==null) ? "":summaryVO.getPatCrNo(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_episodeCode", (summaryVO.getEpisodeCode()==null) ? "": summaryVO.getEpisodeCode(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_visitNo", (summaryVO.getEpisodeVisitNo()==null) ? "": summaryVO.getEpisodeVisitNo(),5);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_admNo", (summaryVO.getAdmissionNo()==null) ? "": summaryVO.getAdmissionNo(),6);
				
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
				/*throw new HisDataAccessException("EHRSection_StatusAtDischargeDAO_Create::hisDAO_p.execute" + EHRConfig.GET_STATUS_AT_DIRCHARGE	+ ") -> " + e.getMessage());*/
				e.printStackTrace();
			}
			finally{
				if (hisDAO_p != null) {
					hisDAO_p.free();
				}
				hisDAO_p = null;
			}
			
			try
			{
				
				
				if(objResSet!=null)
				vo = HelperMethods.populateVOfrmRS(EHRSection_CaseSummaryVO.class, objResSet);
				summaryDtlVO = new EHRSection_CaseSummaryVO();
				
				if(vo!=null)
					summaryDtlVO = (EHRSection_CaseSummaryVO) vo[0];

			}
			catch (Exception e)
			{
				e.printStackTrace();
				/*if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);*/
			}
			
			return summaryDtlVO;
		}

		
		
		public void saveDetails(EHRSection_CaseSummaryVO summaryVO, UserVO userVO) {
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_CaseSummaryDAO");
			String pmode="1";
		
			try
			{
			nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_CASE_SUMMARY);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (summaryVO.getPatCrNo()==null) ? "":summaryVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", summaryVO.getEpisodeCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_adm_no", (summaryVO.getAdmissionNo() ==null)? "": summaryVO.getAdmissionNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episode_visit_no", (summaryVO.getEpisodeVisitNo() ==null)? "": summaryVO.getEpisodeVisitNo(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code", (summaryVO.getDeptCode() ==null)? "": summaryVO.getDeptCode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_unit_code", (summaryVO.getDeptUnitCode() ==null)? "": summaryVO.getDeptUnitCode(),7);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (userVO.getSeatId() ==null )? "" :userVO.getSeatId(), 8);
	
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_summary", (summaryVO.getCaseSummary()==null) ? "": summaryVO.getCaseSummary(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_ids_summary", (summaryVO.getSnomdCIDCaseSummary()==null) ? "": summaryVO.getSnomdCIDCaseSummary(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_pterms_summary", (summaryVO.getSnomdPTCaseSummary()==null) ? "": summaryVO.getSnomdPTCaseSummary(),12);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,13);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally{
				if (hisDAO_p != null) {
					hisDAO_p.free();
				}
				hisDAO_p = null;
			}
			
		}	
	}
