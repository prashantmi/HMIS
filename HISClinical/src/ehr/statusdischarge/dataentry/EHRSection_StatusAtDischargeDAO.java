package ehr.statusdischarge.dataentry;
/**
 * Created By Nilesh Gupta
 * Date: 27.Oct.17
 * */
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


public class EHRSection_StatusAtDischargeDAO extends DataAccessObject
{
	public EHRSection_StatusAtDischargeDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	

		public EHRSection_StatusAtDischargeVO getEssentials(EHRSection_StatusAtDischargeVO statusVO, UserVO userVO) {
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_StatusAtDischargeDAO");
			String pmode="1";
			String strDBErr;
			ResultSet objResSet;
			ValueObject vo[] = null;
			EHRSection_StatusAtDischargeVO conditionDischargeDtlVO = null;

			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_CONDITION_AT_DISCHARGE);
				// Setting and Registering In and Out Parameters 
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
				hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (statusVO.getPatCrNo()==null) ? "":statusVO.getPatCrNo(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_episodeCode", (statusVO.getEpisodeCode()==null) ? "": statusVO.getEpisodeCode(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_visitNo", (statusVO.getEpisodeVisitNo()==null) ? "": statusVO.getEpisodeVisitNo(),5);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_admNo", (statusVO.getAdmissionNo()==null) ? "": statusVO.getAdmissionNo(),6);
				
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
				throw new HisDataAccessException("EHRSection_StatusAtDischargeDAO_Create::hisDAO_p.execute" + EHRConfig.GET_STATUS_AT_DIRCHARGE
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
				
				
				
				vo = HelperMethods.populateVOfrmRS(EHRSection_StatusAtDischargeVO.class, objResSet);
				conditionDischargeDtlVO = new EHRSection_StatusAtDischargeVO();
				
				if(vo.length>0)
					conditionDischargeDtlVO = (EHRSection_StatusAtDischargeVO) vo[0];

			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return conditionDischargeDtlVO;
		}

		public void saveDetails(EHRSection_StatusAtDischargeVO dischargeVO, UserVO userVO) {
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_StatusAtDischargeDAO");
			String pmode="1";
			
			String strDBErr;
			ResultSet objResSet;
			ValueObject vo[] = null;
			//String strEpisodeVisitNo= "", strConsentNo="";
			/*if(dischargeVO.getEpisodeVisitNo()!=null)
			{strEpisodeVisitNo= dischargeVO.getEpisodeVisitNo().replace("^","@");
			strConsentNo=strEpisodeVisitNo.split("\\@")[0];
			}*/
			try
			{
			nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_STATUS_AT_DISCHARGE);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (dischargeVO.getPatCrNo()==null) ? "":dischargeVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", dischargeVO.getEpisodeCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", dischargeVO.getEpisodeVisitNo(),4);
		//	hisDAO_p.setProcInValue(nProcedureIndex, "p_adm_no", dischargeVO.getAdmissionNo(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code", (dischargeVO.getDepartmentCode()==null) ? "":dischargeVO.getDepartmentCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_unit_code", dischargeVO.getDepartmentUnitCode(), 6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (userVO.getSeatId() ==null )? "" :userVO.getSeatId(), 7);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_ip_add", (userVO.getIpAddress()== null)? "":userVO.getIpAddress(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_adm_no", (dischargeVO.getAdmissionNo() ==null)? "": dischargeVO.getAdmissionNo(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_SAD", (dischargeVO.getStatusAtDischarge()==null) ? "": dischargeVO.getStatusAtDischarge(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_idsSAD", (dischargeVO.getSnomdCIdstatusAtDischarge()==null) ? "": dischargeVO.getSnomdCIdstatusAtDischarge(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_ptermsSAD", (dischargeVO.getSnomdPTstatusAtDischarge()==null) ? "": dischargeVO.getSnomdPTstatusAtDischarge(),13);
			
			
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,14); // varchar
		//	hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,15);
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
				throw new HisDataAccessException("EHRSection_StatusAtDischargeDAO_Create::hisDAO_p.execute" + EHRConfig.SAVE_STATUS_AT_DISCHARGE
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
