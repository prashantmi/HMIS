package ehr.hospitalcourse.dataentry;

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
import ehr.hospitalcourse.vo.EHRSection_HospitalCourseVO;


public class EHRSection_HospitalCourseDAO extends DataAccessObject
{
	public EHRSection_HospitalCourseDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	

		public EHRSection_HospitalCourseVO getEssentials(EHRSection_HospitalCourseVO hospitalCourseVO, UserVO userVO) {
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_CaseSummaryDAO");
			String pmode="1";
			String strDBErr;
			ResultSet objResSet;
			ValueObject vo[] = null;
			EHRSection_HospitalCourseVO courseDtlVO = null;

			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_HOSPITAL_COURSE);
				// Setting and Registering In and Out Parameters 
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
				hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (hospitalCourseVO.getPatCrNo()==null) ? "":hospitalCourseVO.getPatCrNo(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_episodeCode", (hospitalCourseVO.getEpisodeCode()==null) ? "": hospitalCourseVO.getEpisodeCode(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_visitNo", (hospitalCourseVO.getEpisodeVisitNo()==null) ? "": hospitalCourseVO.getEpisodeVisitNo(),5);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_admNo", (hospitalCourseVO.getAdmissionNo()==null) ? "": hospitalCourseVO.getAdmissionNo(),6);
				
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
			finally{
				if (hisDAO_p != null) {
					hisDAO_p.free();
				}
				hisDAO_p = null;
			}
			try
			{
				
				
				
				vo = HelperMethods.populateVOfrmRS(EHRSection_HospitalCourseVO.class, objResSet);
				courseDtlVO = new EHRSection_HospitalCourseVO();
				
				if(vo.length>0)
					courseDtlVO = (EHRSection_HospitalCourseVO) vo[0];

			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return courseDtlVO;
		}

		public void saveDetails(EHRSection_HospitalCourseVO dischargeVO, UserVO userVO) {
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_HospitalCourseDAO");
			String pmode="1";
			
			try
			{
			nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_HOSPITAL_COURSE);
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
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_adm_no", (dischargeVO.getAdmissionNo() ==null)? "": dischargeVO.getAdmissionNo(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hosp_course", (dischargeVO.getHospitalCourse()==null) ? "": dischargeVO.getHospitalCourse(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_ids_course", (dischargeVO.getSnomdCIdHospitalCourse()==null) ? "": dischargeVO.getSnomdCIdHospitalCourse(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_pterms_course", (dischargeVO.getSnomdPTHospitalCourse()==null) ? "": dischargeVO.getSnomdPTHospitalCourse(),12);
			
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,13); // varchar
			
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
