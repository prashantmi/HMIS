package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisSQLManualException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import vo.registration.AddressVO;
//import hisglobal.utility.Sequence;
import vo.registration.DailyPatientVO;
import vo.registration.PatientDetailVO;
import vo.registration.PatientVO;
import vo.registration.RoomChangeVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.WebRowSet;









//import opd.OpdConfig;
import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisAppointmentNotAvailableException;
import registration.config.Exceptions.HisInvalidTokenNumberException;

/**
 * DailyPatientDAO is a class which describes all the methods required for database interaction for HRGT_DAILY_PATIENT_DTL
 * table, for example, insert, update, select and delete. DailyPatientDAO class provides a standard interface between
 * Business Objects and Database.
 * 
 * @author AHIS
 * 
 */
public class DailyPatientDAO extends RegistrationDAO
{

	/**
	 * Creates a new DailyPatientDAO object.
	 * 
	 * @param _transactionContext Provides the lock on a transaction.
	 */
	public DailyPatientDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	



	/**
	 * Retrieves the Patient details for a particular CrNo.
	 * 
	 * @param objDailyPatientVO_p Provides daily patient details.
	 * @param objUserVO_p Provides User details.
	 * @return DailyPatientVO pateint detail.
	 */
	public DailyPatientVO searchPatientByCrNo(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
	{
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		WebRowSet rs = null;
		HisDAO objHisDAO_p = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_DAILY_PATIENT_DTL(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		objHisDAO_p = new HisDAO("Registration","EssentialDAO");
		nProcIndex = objHisDAO_p.setProcedure(strProcName);
		
		objHisDAO_p.setProcInValue(nProcIndex, "p_mode", "1",1);
		objHisDAO_p.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
		objHisDAO_p.setProcInValue(nProcIndex, "p_hrgnum_puk", objDailyPatientVO_p.getPatCrNo(),3);
		objHisDAO_p.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
		objHisDAO_p.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getSeatId(),5);
		objHisDAO_p.setProcInValue(nProcIndex, "p_super_hospital_code", Config.SUPER_HOSPITAL_CODE,6);
		objHisDAO_p.setProcOutValue(nProcIndex, "err", 1,7);
		objHisDAO_p.setProcOutValue(nProcIndex, "resultset", 2,8);
		objHisDAO_p.executeProcedureByPosition(nProcIndex);
		strErr = objHisDAO_p.getString(nProcIndex, "err");
		rs = objHisDAO_p.getWebRowSet(nProcIndex, "resultset");
		
		strErr = objHisDAO_p.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record for this CrNo Found");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(objDailyPatientVO_p, rs);
				/*
				 * ResultSet date = HelperMethodsDAO.executeQuery(conn, queryDate, _populateMapForAge); if (!date.next()) {
				 * throw new HisRecordNotFoundException(""); } else { ResultSetMetaData rsmd = date.getMetaData(); int
				 * no_of_col = rsmd.getColumnCount(); System.out.println("no_of_col" + no_of_col); date.beforeFirst(); while
				 * (date.next()) { for (int i = 1; i <= no_of_col; i++) { i++; age = date.getString(rsmd.getColumnName(i));
				 * System.out.println("age:::..." + age); } }
				 */
				/*
				 * System.out.println("age:::..." + age); _patientVO.setPatAge(age); }
				 */
				// _patientVO.setPatAddress(_addressVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("PatientDAO:searchPatientByCrNo:HelperMethods :: " + e);
		}
		return objDailyPatientVO_p;
	}

	
	
	public DailyPatientVO generateQueueNumber(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p, String strRosterType, String strIsgeneral, String callByProcessName)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		HisDAO objHisDAO_p=null;
		String strProcName1="";
		int nProcIndex1=0;
		String strErr="";
		try
		{
				System.out.println("DailyPatientDAO :: generateQueueNumber()");
				
				HelperMethods.setNullToEmpty(objDailyPatientVO_p);
				
				//strProcName1 = "{call pkg_reg_util.gen_queno_deptwise(?,?,?::numeric,?::numeric,?::numeric,   ?::numeric,	?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,	?::numeric,?,?,		?,?,?)}";

				strProcName1 = "{call pkg_reg_util.gen_queno_deptwise_under_limiting_cap(?,?,?::numeric,?::numeric,?::numeric,   ?::numeric,	?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,	?::numeric,?,?,		?,?,?,?)}";

				objHisDAO_p = new HisDAO("Registration","DailyPatientDAO");

				nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);

				objHisDAO_p.setProcInValue(nProcIndex1,  "p_modeVal", "1",1);
				objHisDAO_p.setProcInValue(nProcIndex1,  "p_rostertype", strRosterType,2);
				objHisDAO_p.setProcInValue(nProcIndex1,  "i_dept_code", objDailyPatientVO_p.getDepartmentCode(),3);
				objHisDAO_p.setProcInValue(nProcIndex1,  "pri_cat", objDailyPatientVO_p.getPatPrimaryCatCode(),4);//objDailyPatientVO_p.getDepartmentCode()
				objHisDAO_p.setProcInValue(nProcIndex1,  "sec_cat", objDailyPatientVO_p.getPatSecondaryCatCode(),5);
				objHisDAO_p.setProcInValue(nProcIndex1,  "is_unit_round_robin", objDailyPatientVO_p.getRoundRobinUnitFlag().equals("")?"0":objDailyPatientVO_p.getRoundRobinUnitFlag(),6);
				objHisDAO_p.setProcInValue(nProcIndex1,  "is_room_round_robin", objDailyPatientVO_p.getRoundRobinRoomFlag().equals("")?"0": objDailyPatientVO_p.getRoundRobinRoomFlag(),7);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_que_no", 1,8);
				objHisDAO_p.setProcInValue(nProcIndex1,  "o_room_code",objDailyPatientVO_p.getRoomCode().equals("")?"0":objDailyPatientVO_p.getRoomCode(),9);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_room_code",1,9);
				objHisDAO_p.setProcInValue(nProcIndex1,  "o_unit", objDailyPatientVO_p.getDepartmentUnitCode().equals("")?"0":objDailyPatientVO_p.getDepartmentUnitCode(),10);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_unit", 1,10);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_cap",1,11);
				objHisDAO_p.setProcInValue(nProcIndex1,  "i_hospital_code", objUserVO_p.getHospitalCode(),12);
				//objHisDAO_p.setProcOutValue(nProcIndex1, "filenumber", 1,13);
				//objHisDAO_p.setProcInValue(nProcIndex1,  "usability_flag", (objDailyPatientVO_p.getRoomUsability()!=null)?"0":"0",14);
				objHisDAO_p.setProcOutValue(nProcIndex1, "unit_name",1,13);
				objHisDAO_p.setProcOutValue(nProcIndex1, "room_name", 1,14);
				//objHisDAO_p.setProcOutValue(nProcIndex1, "status", 1,17);
				objHisDAO_p.setProcOutValue(nProcIndex1, "unittime", 1,15);
				objHisDAO_p.setProcOutValue(nProcIndex1, "unit_inc_name", 1,16);
				//objHisDAO_p.setProcOutValue(nProcIndex1, "cardsetting", 1,20);	
				//objHisDAO_p.setProcInValue(nProcIndex1, "isGeneral", strIsgeneral,21);
				
				objHisDAO_p.setProcInValue(nProcIndex1,  "i_callby_process_name", callByProcessName,17);

				objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,18);
				
				objHisDAO_p.executeProcedureByPosition(nProcIndex1);
				strErr = objHisDAO_p.getString(nProcIndex1, "err");
					if (strErr == null)
						strErr = "";
		
						if (!strErr.equals("")) 
						{
							System.out.println(strErr);
							throw new Exception(strErr);
						}
						else
						{
							String queNo = objHisDAO_p.getString(nProcIndex1, "o_que_no");
							String roomCode = objHisDAO_p.getString(nProcIndex1, "o_room_code");
							String deptUnitCode = objHisDAO_p.getString(nProcIndex1, "o_unit");
							String unitCap = objHisDAO_p.getString(nProcIndex1, "o_cap");
							//String strFileNo= objHisDAO_p.getString(nProcIndex1, "filenumber");
							String unitName=objHisDAO_p.getString(nProcIndex1, "unit_name");
							String roomName=objHisDAO_p.getString(nProcIndex1, "room_name");
							//String status=objHisDAO_p.getString(nProcIndex1, "status");
							String unitDays=objHisDAO_p.getString(nProcIndex1, "unittime");
							String unitConsultant = objHisDAO_p.getString(nProcIndex1, "unit_inc_name");
							//String cardPrintSetting = objHisDAO_p.getString(nProcIndex1, "cardsetting");
							
							//if(status.equals("2"))
								//throw new HisAppointmentNotAvailableException("Appointment Not Available");
							
							objDailyPatientVO_p.setQueNo(queNo);
							objDailyPatientVO_p.setRoomCode(roomCode);
							objDailyPatientVO_p.setDepartmentUnitCode(deptUnitCode);
							objDailyPatientVO_p.setPatientAllowed(unitCap);
							objDailyPatientVO_p.setDepartmentUnit(unitName);
							objDailyPatientVO_p.setRoom(roomName);
							objDailyPatientVO_p.setUnitWorkingDays(unitDays);
							objDailyPatientVO_p.setUnitConsultant(unitConsultant);
							//objDailyPatientVO_p.setCardPrintSetting(cardPrintSetting);
							//objDailyPatientVO_p.setFileNo(strFileNo);
							
							//////generate episode code
							String episodeCode=deptUnitCode+"001";
							objDailyPatientVO_p.setEpisodeCode(episodeCode);
							///////////
							//for printing formats
							//objDailyPatientVO_p.setCardPrintSetting("");
							objDailyPatientVO_p.setFilePrintSetting("");
		
								
							if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
									|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
									"Procedure Generate Que No:: returns null value");
							objDailyPatientVO_p.setFileNoView("");
						}
						
						System.out.println("p_rostertype :"+ strRosterType);
						System.out.println("i_dept_code :"+ objDailyPatientVO_p.getDepartmentCode());
						System.out.println("pri_cat :"+ objDailyPatientVO_p.getPatPrimaryCatCode());//objDailyPatientVO_p.getDepartmentCode()
						System.out.println("sec_cat :"+ objDailyPatientVO_p.getPatSecondaryCatCode());
						System.out.println("is_unit_round_robin :"+ objDailyPatientVO_p.getRoundRobinUnitFlag());
						System.out.println("is_room_round_robin :"+ objDailyPatientVO_p.getRoundRobinRoomFlag());
						System.out.println("o_room_code :"+objDailyPatientVO_p.getRoomCode());
						System.out.println("o_unit :"+ objDailyPatientVO_p.getDepartmentUnitCode());
						System.out.println("i_hospital_code :"+ objUserVO_p.getHospitalCode());
						System.out.println("usability_flag :"+ objDailyPatientVO_p.getRoomUsability());
						
						System.out.println("------------------");
						System.out.println("Values After Call");
						System.out.println("queNo :"+objDailyPatientVO_p.getQueNo());
						System.out.println("roomCode :"+objDailyPatientVO_p.getRoomCode());
						System.out.println("deptUnitCode :"+objDailyPatientVO_p.getDepartmentUnitCode());
						System.out.println("unitCap :"+objDailyPatientVO_p.getPatientAllowed());
						System.out.println("unitName :"+objDailyPatientVO_p.getDepartmentUnit());
						System.out.println("roomName :"+objDailyPatientVO_p.getRoom());
						System.out.println("unitDays :"+objDailyPatientVO_p.getUnitWorkingDays());
						System.out.println("unitConsultant :"+objDailyPatientVO_p.getUnitConsultant());
						System.out.println("cardPrintSetting :"+objDailyPatientVO_p.getCardPrintSetting());
						System.out.println("strFileNo :"+objDailyPatientVO_p.getFileNo());
			//}
			
		}catch (SQLException e) {
			System.out.println(" SQLException state: "+e.getSQLState());
			final String ss = e.getSQLState();
			e.printStackTrace();
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//System.out.println(e.getLocalizedMessage()+"\n"+e.getCause()+"\n"+e.getMessage());
			if(e.getMessage().contains("Department-Unit Limit Exhausted in Error"))
				throw new HisSQLManualException("Department-Unit Limit Exhausted");
			else 
				throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		return objDailyPatientVO_p;
	}
	
	
	public DailyPatientVO saveDailyPatientDtl(HisDAO objHisDAO_p, DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p, String strMode_p, String strRegFlag )
	{
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="",strEpisodeCode="";
		try 
		{
			//dml_hrgt_daily_patient_dtl_broughtDead_consultant strProcName1 = "{call pkg_reg_dml.dml_hrgt_daily_patient_dtl(?,?,?,?,?,		?::numeric,?::numeric,?,?,?::numeric,	?,?::numeric,?::numeric,?,?,	?::numeric,?,?,?::numeric,?::numeric,	?,?::numeric,?,?::numeric,?,	?,?,?::numeric,?::numeric,?::numeric,	?::numeric,?::numeric,?,?::numeric,?::numeric,		?::numeric,?::numeric,?::numeric,?,?,		?,?,?::numeric,?,?,		?,?,?::numeric,?::numeric,?::numeric,	?,?::numeric,?,?::numeric,?,	?,?::numeric,?::numeric,?,?::numeric,	?,?,?::numeric,?,?::numeric,	?,?::numeric,?,?,?::numeric,	?::numeric,?,?::numeric,?::numeric,?::numeric,	?::numeric,?,?,?,?,		?,?::numeric,?::numeric,?,?::numeric,	?,?,?,?::numeric,?::numeric,	?::numeric,?,?,?,?,		?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,		?::numeric,?,?,?,?::numeric,	?::numeric,?::numeric,?,?,?::numeric,	?::numeric,?::numeric,?,?::timestamp,?::timestamp,	?::timestamp,?,?,?,?,	?,?,?,?,?, ?,?,?,?)}";
			/*strProcName1 = "{call pkg_reg_dml.dml_hrgt_daily_patient_dtl_odisha_dev_old(?,?,?,?,?,		?::numeric,?::numeric,?,?,?::numeric,	?,?::numeric,?::numeric,?,?,	?::numeric,?,?,?::numeric,?::numeric,	?,?::numeric,?,?::numeric,?,	?,?,?::numeric,?::numeric,?::numeric,	?::numeric,?::numeric,?,?::numeric,?::numeric,		?::numeric,?::numeric,?::numeric,?,?,		?,?,?::numeric,?,?,		?,?,?::numeric,?::numeric,?::numeric,	?,?::numeric,?,?::numeric,?,	?,?::numeric,?::numeric,?,?::numeric,	?,?,?::numeric,?,?::numeric,	?,?::numeric,?,?,?::numeric,	?::numeric,?,?::numeric,?::numeric,?::numeric,	?::numeric,?,?,?,?,		?,?::numeric,?::numeric,?,?::numeric,	?,?,?,?::numeric,?::numeric,	?::numeric,?,?,?,?,		?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,		?::numeric,?,?,?,?::numeric,	?::numeric,?::numeric,?,?,?::numeric,	?::numeric,?::numeric,?,?::timestamp,?::timestamp,	?::timestamp,?,?,?,?,	?,?,?,?,?,?::character varying, ?)}"; //Last one added by Vasu on 03.May.18
*/			strProcName1 = "{call pkg_reg_dml.dml_hrgt_daily_patient_dtl_odisha_dev(?,?,?,?,?,		?::numeric,?::numeric,?,?,?::numeric,	?,?::numeric,?::numeric,?,?,	?::numeric,?,?,?::numeric,?::numeric,	?,?::numeric,?,?::numeric,?,	?,?,?::numeric,?::numeric,?::numeric,	?::numeric,?::numeric,?,?::numeric,?::numeric,		?::numeric,?::numeric,?::numeric,?,?,		?,?,?::numeric,?,?,		?,?,?::numeric,?::numeric,?::numeric,	?,?::numeric,?,?::numeric,?,	?,?::numeric,?::numeric,?,?::numeric,	?,?,?::numeric,?,?::numeric,	?,?::numeric,?,?,?::numeric,	?::numeric,?,?::numeric,?::numeric,?::numeric,	?::numeric,?,?,?,?,		?,?::numeric,?::numeric,?,?::numeric,	?,?,?,?::numeric,?::numeric,	?::numeric,?,?,?,?,		?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,		?::numeric,?,?,?,?::numeric,	?::numeric,?::numeric,?,?,?::numeric,	?::numeric,?::numeric,?,?::timestamp,?::timestamp,	?::timestamp,?,?,?,?,	?,?,?,?,?,?::character varying, ?)}"; //Last one added by warish on 27.july.18
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			HelperMethods.setNullToEmpty(objDailyPatientVO_p);

			HelperMethods.setNullToEmpty(objDailyPatientVO_p.getPatAddress());
			HelperMethods.setNullToEmpty(objUserVO_p);

			//HelperMethods.setEmptyToNull(objDailyPatientVO_p);
	
			
			System.out.println("DailyPatientDAO :: saveDailyPatientDtl()");
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeVal",  strMode_p,1);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgbl_is_actual_dob", objDailyPatientVO_p.getIsActualDob(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_age", objDailyPatientVO_p.getPatAge(),3); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_ageunit", objDailyPatientVO_p.getPatAgeUnit(),4); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_dob", ((objDailyPatientVO_p.getPatDOB().equals(null)|| objDailyPatientVO_p.getPatDOB().equals(""))?"":objDailyPatientVO_p.getPatDOB()),5); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objDailyPatientVO_p.getPatCrNo(),6);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk",  objDailyPatientVO_p.getPrevCrNo(),7);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objDailyPatientVO_p.getEpisodeCode(),8);
			objHisDAO_p.setProcOutValue(nProcIndex1, "p_hrgnum_episode_code", 1,8);
			
			if(objDailyPatientVO_p.getPatDOB().equalsIgnoreCase("") || objDailyPatientVO_p.getPatDOB().equals(null))
				 objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob", "" ,9);
			else
				objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob",  objDailyPatientVO_p.getPatDOB(),9);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objDailyPatientVO_p.getEpisodeVisitNo(),10); 
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname", objDailyPatientVO_p.getPatFirstName(),11); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", objDailyPatientVO_p.getEpisodeTypeCode(),12); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", objDailyPatientVO_p.getEpisodeVisitType(),13); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname", objDailyPatientVO_p.getPatMiddleName(),14); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname", objDailyPatientVO_p.getPatLastName(),15); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code", objDailyPatientVO_p.getDepartmentCode(),16); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_gender_code", objDailyPatientVO_p.getPatGenderCode(),17); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_dept_name", objDailyPatientVO_p.getDepartment(),18); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", objDailyPatientVO_p.getPatMaritalStatusCode(),19); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", objDailyPatientVO_p.getDepartmentUnitCode(),20);
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgstr_unitname", objDailyPatientVO_p.getDepartmentUnit(),21); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", objDailyPatientVO_p.getPatStatusCode(),22); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", objDailyPatientVO_p.getPatIdMark1(),23); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_room_code", objDailyPatientVO_p.getRoomCode(),24); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgstr_room_name", objDailyPatientVO_p.getRoom(),25); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_father_name", objDailyPatientVO_p.getPatGuardianName(),26); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_momname", objDailyPatientVO_p.getPatMotherName(),27); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_que_no", objDailyPatientVO_p.getQueNo(),28); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", objDailyPatientVO_p.getAdmissionNo(),29); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_reg_cat_code", objDailyPatientVO_p.getPatRegCatCode(),30); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", objDailyPatientVO_p.getPatPrimaryCatCode(),31); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_document_code", objDailyPatientVO_p.getPatDocType(),32); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date", "",33); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),34); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", objDailyPatientVO_p.getIsMLC()==""?"0":objDailyPatientVO_p.getIsMLC(),35); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid", objDailyPatientVO_p.getIsValid(),36); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", objDailyPatientVO_p.getIsUnknown(),37); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_month_income", (objDailyPatientVO_p.getPatMonthlyIncome().equals("")?"0":objDailyPatientVO_p.getPatMonthlyIncome()),38); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_patient_cat_name", objDailyPatientVO_p.getPatPrimaryCat(),39); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_patient_cat_short", objDailyPatientVO_p.getPatCatShortName(),40); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_primarycat_validupto", objDailyPatientVO_p.getPatPrimaryCatValid(),41); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", (objUserVO_p.getIpAddress()==null?"127.0.0.1":objUserVO_p.getIpAddress()),42); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", objDailyPatientVO_p.getPatSecondaryCatCode(),43); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_treatment_cat_name", objDailyPatientVO_p.getPatSecondaryCat(),44); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_treatment_cat_short", "",45); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_id_no", objDailyPatientVO_p.getPatIdNo(),46); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_treatmentcat_validupto", objDailyPatientVO_p.getTreatmentValidUpTo(),47); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", objDailyPatientVO_p.getIsReferred(),48); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_religion_code", objDailyPatientVO_p.getPatReligionCode(),49); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_appellation_code", objDailyPatientVO_p.getPatAppellationCode(),50); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_age", objDailyPatientVO_p.getPatAge(),51); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_suffix_code", objDailyPatientVO_p.getSuffixCode(),52); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_name_type", objDailyPatientVO_p.getPatNameType(),53); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_imageuploaded", (objDailyPatientVO_p.getIsImageUploaded().equals("")?"0":objDailyPatientVO_p.getIsImageUploaded()),54); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_spousename", objDailyPatientVO_p.getPatHusbandName(),55); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_national_id", objDailyPatientVO_p.getPatNationalId(),56); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", (objDailyPatientVO_p.getPatBloodGroupCode().equals("")?"0":objDailyPatientVO_p.getPatBloodGroupCode()),57); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", objDailyPatientVO_p.getIsNewBorn(),58); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_gender_name", objDailyPatientVO_p.getPatGender(),59); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", (objDailyPatientVO_p.getPatMotherCrNo().equals("")?"0":objDailyPatientVO_p.getPatMotherCrNo()),60);
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", objDailyPatientVO_p.getPatBirthPlace(),61); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname", objDailyPatientVO_p.getPatNickName(),62); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_special_vulnerability", objDailyPatientVO_p.getPatSplVulnerability(),63); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_no", objDailyPatientVO_p.getPatCardNo(),64); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),65); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", objDailyPatientVO_p.getPatIdMark2(),66); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", objDailyPatientVO_p.getIsBroughtDead(),67); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_relation_name", " ",68); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_caste_code", objDailyPatientVO_p.getPatCasteCode(),69); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_occupation_code", objDailyPatientVO_p.getPatOccupation(),70); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_education_code", objDailyPatientVO_p.getPatEducationCode(),71); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_caste_name", objDailyPatientVO_p.getPatCaste(),72); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_dead", objDailyPatientVO_p.getPatIsDead(),73); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ismerged", objDailyPatientVO_p.getPatIsMerged(),74); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_verification_status", objDailyPatientVO_p.getPatAddress().getPatVerificationStatus(),75); 
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_nationality", objDailyPatientVO_p.getPatNationalityCode(),76); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_address_line1", objDailyPatientVO_p.getPatAddHNo(),77); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality1", objDailyPatientVO_p.getPatAddStreet(),78); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality2", objDailyPatientVO_p.getPatAddLandMarks(),79); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city_location", objDailyPatientVO_p.getPatAddCityLoc(),80); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city", objDailyPatientVO_p.getPatAddCity(),81); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_pincode", objDailyPatientVO_p.getPatAddPIN(),82); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_district_code", objDailyPatientVO_p.getPatAddDistrictCode(),83); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_district", objDailyPatientVO_p.getPatAddDistrict(),84); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_state_code", objDailyPatientVO_p.getPatAddStateCode(),85); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_state_name", objDailyPatientVO_p.getPatAddState(),86); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_country_code", objDailyPatientVO_p.getPatAddCountryCode(),87); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_country_name", objDailyPatientVO_p.getPatAddCountry(),88); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", objDailyPatientVO_p.getPatIsUrban(),89); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_local", objDailyPatientVO_p.getPatIsLocal(),90); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_phone_owner", objDailyPatientVO_p.getPatAddPhoneOwner(),91); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no", objDailyPatientVO_p.getPatAddMobileNo(),92); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_phone_no", objDailyPatientVO_p.getPatAddPhoneNo(),93); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_email_id", objDailyPatientVO_p.getPatAddEmailId(),94); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_visit_reason", objDailyPatientVO_p.getPatVisitReason(),95); 
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_ward_code", objDailyPatientVO_p.getWardCode(),96); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", objDailyPatientVO_p.getEpisodeIsOpen(),97); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", objDailyPatientVO_p.getIsConfirmed(),98); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint", objDailyPatientVO_p.getIsCardPrint(),99); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isforceful", objDailyPatientVO_p.getIsForceful()==null || objDailyPatientVO_p.getIsForceful().equals("")?"0":objDailyPatientVO_p.getIsForceful(),100); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ref_episode_code", objDailyPatientVO_p.getEpisodeReferCode(),101); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_exp_date", objDailyPatientVO_p.getEpisodeExpiryDate(),102); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_episode_start_date", objDailyPatientVO_p.getEpisodeStartDate(),103); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_episode_lastvisit_date", objDailyPatientVO_p.getLastVisitDate(),104); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_tariff_id", objDailyPatientVO_p.getTariffId(),105); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_bill_no", objDailyPatientVO_p.getBillNo(),106); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", objDailyPatientVO_p.getPatAmountCollected(),107); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_hospital_name", objUserVO_p.getStrHospitalMstVo().getHospitalName(),108); 
			
			String strHospAdd= "";
			if(objUserVO_p.getStrHospitalMstVo().getAddress1() != null)
				strHospAdd =objUserVO_p.getStrHospitalMstVo().getAddress1() ;
			if(objUserVO_p.getStrHospitalMstVo().getAddress2() != null)
				strHospAdd=strHospAdd+objUserVO_p.getStrHospitalMstVo().getAddress2();
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_hospital_address",strHospAdd,109);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_changeto_deptunitcode", objDailyPatientVO_p.getChangeToDeptUnitCode(),110); 
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_changeto_room_code", objDailyPatientVO_p.getChangeToRoomCode(),111); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_change_type", objDailyPatientVO_p.getChangeType(),112); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_display_queno", objDailyPatientVO_p.getQueNo(),113); 
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_general_expdate", objDailyPatientVO_p.getPatGeneralExpDate(),114);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_casuality_expdate", objDailyPatientVO_p.getPatCasualityExpDate(),115);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_special_expdate", objDailyPatientVO_p.getPatSpecialExpDate(),116);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_marital_status", objDailyPatientVO_p.getPatMaritalStatus(),117);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_religion_name", objDailyPatientVO_p.getPatReligion(),118);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", objDailyPatientVO_p.getPatientAllowed(),119);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_reg_flag", strRegFlag,120);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", objDailyPatientVO_p.getPatFirstNameInMultiLang(),121);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", objDailyPatientVO_p.getPatMiddleNameInMultiLang(),122);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", objDailyPatientVO_p.getPatLastNameInMultiLang(),123);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mlc_flag", objDailyPatientVO_p.getMlcFlag(),124);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_ambulatory_flg",objDailyPatientVO_p.getIsAmbulatory(),125);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgstr_broughtdead_declaredby",objDailyPatientVO_p.getBroughtByConsultant(),126);
			//objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isapt_flag", objDailyPatientVO_p.getIsAppointment(),125);
			//objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_apt_no", objDailyPatientVO_p.getPatAptNo(),126);
			//objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_apt_slot", objDailyPatientVO_p.getPatAptSlot(),127);
			//objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_apt_qno", objDailyPatientVO_p.getPatAptQueueNO(),128);

			
			//objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,129);
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,127);
			
			//objHisDAO_p.setProcOutValue(nProcIndex1, "patDOB", 1);
			//objHisDAO_p.setProcOutValue(nProcIndex1, "patAge", 1);
			
			objHisDAO_p.execute(nProcIndex1,1);
			
			System.out.println("---------------------------------------");
			System.out.println("p_modeVal :"+ strMode_p);
			System.out.println("p_hrgbl_is_actual_dob :"+objDailyPatientVO_p.getIsActualDob());
			System.out.println("p_age :"+objDailyPatientVO_p.getPatAge()); 
			System.out.println("p_ageunit :"+objDailyPatientVO_p.getPatAgeUnit()); 
			System.out.println("p_dob :"+((objDailyPatientVO_p.getPatDOB().equals(null)|| objDailyPatientVO_p.getPatDOB().equals(""))?"":objDailyPatientVO_p.getPatDOB())); 
			System.out.println("p_hrgnum_puk :"+objDailyPatientVO_p.getPatCrNo());
			
			System.out.println("p_hrgstr_prev_puk :"+ objDailyPatientVO_p.getPrevCrNo());
			
			System.out.println("p_hrgnum_episode_code :"+objDailyPatientVO_p.getEpisodeCode()); 
			System.out.println("p_hrgdt_dob :"+ objDailyPatientVO_p.getPatDOB());
			
			System.out.println("p_hrgnum_visit_no :"+objDailyPatientVO_p.getEpisodeVisitNo()); 
			
			System.out.println("p_hrgstr_fname :"+objDailyPatientVO_p.getPatFirstName()); 
			System.out.println("p_hrgnum_episode_type_code :"+objDailyPatientVO_p.getEpisodeTypeCode()); 
			System.out.println("p_hrgnum_visit_type :"+objDailyPatientVO_p.getEpisodeVisitType()); 
			System.out.println("p_hrgstr_mname :"+objDailyPatientVO_p.getPatMiddleName()); 
			System.out.println("p_hrgstr_lname :"+objDailyPatientVO_p.getPatLastName()); 
			System.out.println("p_gnum_dept_code :"+objDailyPatientVO_p.getDepartmentCode()); 
			System.out.println("p_gstr_gender_code :"+objDailyPatientVO_p.getPatGenderCode()); 
			System.out.println("p_gstr_dept_name :"+objDailyPatientVO_p.getDepartment()); 
			System.out.println("p_gnum_marital_status_code :"+(objDailyPatientVO_p.getPatMaritalStatusCode().equals("")?"0":objDailyPatientVO_p.getPatMaritalStatusCode())); 
			System.out.println("p_hgnum_deptunitcode :"+objDailyPatientVO_p.getDepartmentUnitCode());
			
			
			System.out.println("p_hgstr_unitname :"+objDailyPatientVO_p.getDepartmentUnit()); 
			System.out.println("p_hgnum_pat_status_code :"+objDailyPatientVO_p.getPatStatusCode()); 
			System.out.println("p_hrgstr_idmark1 :"+objDailyPatientVO_p.getPatIdMark1()); 
			System.out.println("p_hgnum_room_code :"+objDailyPatientVO_p.getRoomCode()); 
			System.out.println("p_hgstr_room_name :"+objDailyPatientVO_p.getRoom()); 
			System.out.println("p_hrgstr_father_name :"+objDailyPatientVO_p.getPatGuardianName()); 
			System.out.println("p_hrgstr_momname :"+objDailyPatientVO_p.getPatMotherName()); 
			System.out.println("p_hrgnum_que_no :"+objDailyPatientVO_p.getQueNo()); 
			System.out.println("p_hrgnum_admission_no :"+objDailyPatientVO_p.getAdmissionNo()); 
			System.out.println("p_hrgnum_reg_cat_code :"+objDailyPatientVO_p.getPatRegCatCode()); 
			
			
			System.out.println("p_hgnum_patient_cat_code :"+objDailyPatientVO_p.getPatPrimaryCatCode()); 
			System.out.println("p_gnum_document_code :"+objDailyPatientVO_p.getPatDocType()); 
			System.out.println("p_gdt_entry_date :"+""); 
			System.out.println("p_gnum_seat_id :"+objUserVO_p.getSeatId()); 
			System.out.println("p_hrgnum_is_mlc :"+objDailyPatientVO_p.getIsMLC()==""?"0":objDailyPatientVO_p.getIsMLC()); 
			System.out.println("p_gnum_isvalid :"+objDailyPatientVO_p.getIsValid()); 
			System.out.println("p_hrgnum_isunknown :"+objDailyPatientVO_p.getIsUnknown()); 
			System.out.println("p_hrgnum_month_income :"+(objDailyPatientVO_p.getPatMonthlyIncome().equals("")?"0":objDailyPatientVO_p.getPatMonthlyIncome())); 
			System.out.println("p_gstr_patient_cat_name :"+objDailyPatientVO_p.getPatPrimaryCat()); 
			System.out.println("p_gstr_patient_cat_short :"+" "); 
			
			
			System.out.println("p_hrgdt_primarycat_validupto :"+objDailyPatientVO_p.getPatPrimaryCatValid()); 
			System.out.println("p_hrgstr_ip_add :"+(objUserVO_p.getIpAddress()==null?"127.0.0.1":objUserVO_p.getIpAddress())); 
			System.out.println("p_hgnum_treatment_cat_code :"+objDailyPatientVO_p.getPatSecondaryCatCode()); 
			System.out.println("p_gstr_treatment_cat_name :"+objDailyPatientVO_p.getPatSecondaryCat()); 
			System.out.println("p_gstr_treatment_cat_short :"+""); 
			System.out.println("p_hrgnum_id_no :"+objDailyPatientVO_p.getPatIdNo()); 
			System.out.println("p_hrgdt_treatmentcat_validupto :"+objDailyPatientVO_p.getTreatmentValidUpTo()); 
			System.out.println("p_hrgnum_is_ref :"+objDailyPatientVO_p.getIsReferred()); 
			System.out.println("p_gnum_religion_code :"+objDailyPatientVO_p.getPatReligionCode()); 
			System.out.println("p_gnum_appellation_code :"+objDailyPatientVO_p.getPatAppellationCode()); 
			
			
			System.out.println("p_hrgstr_age :"+objDailyPatientVO_p.getPatAge()); 
			System.out.println("p_gnum_suffix_code :"+objDailyPatientVO_p.getSuffixCode()); 
			System.out.println("p_hrgstr_name_type :"+objDailyPatientVO_p.getPatNameType()); 
			System.out.println("p_hrgnum_is_imageuploaded :"+"0"); 
			System.out.println("p_hrgstr_spousename :"+objDailyPatientVO_p.getPatHusbandName()); 
			System.out.println("p_hrgstr_national_id :"+objDailyPatientVO_p.getPatNationalId()); 
			System.out.println("p_hbnum_bldgrp_code :"+(objDailyPatientVO_p.getPatBloodGroupCode().equals("")?"0":objDailyPatientVO_p.getPatBloodGroupCode())); 
			System.out.println("p_hrgnum_isnewborn :"+objDailyPatientVO_p.getIsNewBorn()); 
			System.out.println("p_gstr_gender_name :"+objDailyPatientVO_p.getPatGender()); 
			System.out.println("p_hrgnum_mother_puk :"+(objDailyPatientVO_p.getPatMotherCrNo().equals("")?"0":objDailyPatientVO_p.getPatMotherCrNo()));
			
			
			System.out.println("p_hrgstr_birth_place :"+objDailyPatientVO_p.getPatBirthPlace()); 
			System.out.println("p_hrgstr_nickname :"+objDailyPatientVO_p.getPatNickName()); 
			System.out.println("p_hrgnum_special_vulnerability :"+objDailyPatientVO_p.getPatSplVulnerability()); 
			System.out.println("p_hrgstr_card_no :"+objDailyPatientVO_p.getPatCardNo()); 
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode()); 
			System.out.println("p_hrgstr_idmark2 :"+objDailyPatientVO_p.getPatIdMark2()); 
			System.out.println("p_hrgnum_is_broughtdead :"+objDailyPatientVO_p.getIsBroughtDead()); 
			System.out.println("p_gstr_relation_name :"+" "); 
			System.out.println("p_gstr_caste_code :"+objDailyPatientVO_p.getPatCasteCode()); 
			System.out.println("p_gnum_occupation_code :"+objDailyPatientVO_p.getPatOccupation()); 
			
			
			System.out.println("p_gnum_education_code :"+objDailyPatientVO_p.getPatEducationCode()); 
			System.out.println("p_gstr_caste_name :"+objDailyPatientVO_p.getPatCaste()); 
			System.out.println("p_hrgnum_is_dead :"+objDailyPatientVO_p.getPatIsDead()); 
			System.out.println("p_hrgnum_ismerged :"+objDailyPatientVO_p.getPatIsMerged()); 
			System.out.println("p_hrgnum_verification_status :"+objDailyPatientVO_p.getPatAddress().getPatVerificationStatus()); 
			
			System.out.println("p_gnum_nationality :"+objDailyPatientVO_p.getPatNationalityCode()); 
			System.out.println("p_hrgstr_address_line1 :"+objDailyPatientVO_p.getPatAddHNo()); 
			System.out.println("p_hrgstr_sub_locality1 :"+objDailyPatientVO_p.getPatAddStreet()); 
			System.out.println("p_hrgstr_sub_locality2 :"+objDailyPatientVO_p.getPatAddLandMarks()); 
			System.out.println("p_hrgstr_city_location :"+objDailyPatientVO_p.getPatAddCityLoc()); 
			
			
			System.out.println("p_hrgstr_city :"+objDailyPatientVO_p.getPatAddCity()); 
			System.out.println("p_hrgnum_pincode :"+objDailyPatientVO_p.getPatAddPIN()); 
			System.out.println("p_gnum_district_code :"+objDailyPatientVO_p.getPatAddDistrictCode()); 
			System.out.println("p_hrgstr_district :"+objDailyPatientVO_p.getPatAddDistrict()); 
			System.out.println("p_gnum_state_code :"+objDailyPatientVO_p.getPatAddStateCode()); 
			System.out.println("p_hrgstr_state_name :"+objDailyPatientVO_p.getPatAddState()); 
			System.out.println("p_gstr_country_code :"+objDailyPatientVO_p.getPatAddCountryCode()); 
			System.out.println("p_gstr_country_name :"+objDailyPatientVO_p.getPatAddCountry()); 
			System.out.println("p_hrgnum_is_urban :"+objDailyPatientVO_p.getPatIsUrban()); 
			System.out.println("p_hrgnum_is_local :"+objDailyPatientVO_p.getPatIsLocal()); 
			
			
			System.out.println("p_hrgnum_phone_owner :"+objDailyPatientVO_p.getPatAddPhoneOwner()); 
			System.out.println("p_hrgstr_mobile_no :"+objDailyPatientVO_p.getPatAddMobileNo()); 
			System.out.println("p_hrgstr_phone_no :"+objDailyPatientVO_p.getPatAddPhoneNo()); 
			System.out.println("p_hrgstr_email_id :"+objDailyPatientVO_p.getPatAddEmailId()); 
			System.out.println("p_hrgstr_visit_reason :"+objDailyPatientVO_p.getPatVisitReason()); 
			
			System.out.println("p_hgnum_ward_code :"+objDailyPatientVO_p.getWardCode()); 
			System.out.println("p_hrgnum_is_episode_open :"+objDailyPatientVO_p.getEpisodeIsOpen()); 
			System.out.println("p_hrgnum_is_confirmed :"+objDailyPatientVO_p.getIsConfirmed()); 
			System.out.println("p_hrgnum_iscardprint :"+objDailyPatientVO_p.getIsCardPrint()); 
			System.out.println("p_hrgnum_isforceful :"+" "); 
			
			
			System.out.println("p_hrgnum_ref_episode_code :"+objDailyPatientVO_p.getEpisodeReferCode()); 
			System.out.println("p_hrgdt_exp_date :"+objDailyPatientVO_p.getEpisodeExpiryDate()); 
			System.out.println("p_hrgdt_episode_start_date :"+objDailyPatientVO_p.getEpisodeStartDate()); 
			System.out.println("p_hrgdt_episode_lastvisit_date :"+objDailyPatientVO_p.getLastVisitDate()); 
			System.out.println("p_hblnum_tariff_id :"+objDailyPatientVO_p.getTariffId()); 
			System.out.println("p_hblnum_bill_no :"+objDailyPatientVO_p.getBillNo()); 
			System.out.println("p_hrgnum_amt_collected :"+objDailyPatientVO_p.getPatAmountCollected()); 
			System.out.println("p_gstr_hospital_name :"+""); 
			System.out.println("p_gstr_hospital_address :"+"");
			System.out.println("p_hgnum_changeto_deptunitcode :"+objDailyPatientVO_p.getChangeToDeptUnitCode()); 
			
			
			System.out.println("p_hgnum_changeto_room_code :"+objDailyPatientVO_p.getChangeToRoomCode()); 
			System.out.println("p_hrgnum_change_type :"+objDailyPatientVO_p.getChangeType()); 
			System.out.println("p_hrgstr_display_queno :"+objDailyPatientVO_p.getQueNo()); 
			
			System.out.println("p_hrgdt_general_expdate :"+ objDailyPatientVO_p.getPatGeneralExpDate());
			System.out.println("p_hrgdt_casuality_expdate :"+ objDailyPatientVO_p.getPatCasualityExpDate());
			System.out.println("p_hrgdt_special_expdate :"+ objDailyPatientVO_p.getPatSpecialExpDate());
			System.out.println("p_gstr_marital_status :"+ objDailyPatientVO_p.getPatMaritalStatus());
			System.out.println("p_gstr_religion_name :"+ objDailyPatientVO_p.getPatReligion());
			System.out.println("p_hrgnum_patient_allowed :"+ "1");
			System.out.println("p_hrgnum_reg_flag :"+ strRegFlag);
			System.out.println("p_hrgnum_mlc_flag :"+ objDailyPatientVO_p.getMlcFlag());
			
			System.out.println("p_hrgnum_isapt_flag :"+ objDailyPatientVO_p.getIsAppointment());
			System.out.println("p_hrgnum_apt_no :"+ objDailyPatientVO_p.getPatAptNo());
			System.out.println("p_hrgstr_apt_slot :"+ objDailyPatientVO_p.getPatAptSlot());
			System.out.println("p_hrgnum_apt_qno :"+ objDailyPatientVO_p.getPatAptQueueNO());

			
			System.out.println("----------------------------------------------------");
			
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//strEpisodeCode = objHisDAO_p.getString(nProcIndex1, "p_hrgnum_episode_code");
						//System.out.println("strEpisodeCode :"+strEpisodeCode);
						//objDailyPatientVO_p.setPatDOB(objHisDAO_p.getString(nProcIndex1, "patDOB"));
						if(objDailyPatientVO_p.getIsActualDob().equals("1")){
							objDailyPatientVO_p.setPatAgeWithUnit(getAgeFromDOB(objDailyPatientVO_p.getPatDOB()));
						}else{
							objDailyPatientVO_p.setPatAgeWithUnit(objDailyPatientVO_p.getPatAge()+" "+objDailyPatientVO_p.getPatAgeUnit());
						}
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return objDailyPatientVO_p;
	}

	public String getAgeFromDOB(String dob)
	{
		String age="";
		int funcIndex=0;
		HisDAO objHisDAO_p=null;
		try 
		{
			objHisDAO_p = new HisDAO("Registration","DailyPatientDAO");
			funcIndex = objHisDAO_p.setFunction("{? = call pkg_reg_util.DOB_AGE_ON(?::timestamp,?)}");
			objHisDAO_p.setFuncInValue(funcIndex, 2, dob);
			objHisDAO_p.setFuncInValue(funcIndex, 3,"");
			objHisDAO_p.setFuncOutValue(funcIndex,1);
			
			objHisDAO_p.executeFunction(funcIndex);
			age = objHisDAO_p.getFuncString(funcIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDAO.getAgeFromDOB()" + e);
		}
		finally 
		{
			if (objHisDAO_p != null) 
			{
				objHisDAO_p.free();
				objHisDAO_p = null;
			}
		}
		return age;
	}
	
	public String generateBillNo(UserVO objUserVO_p, String strProcesType_p)
	{
		String strBillNo="";
		int funcIndex=0;
		HisDAO objHisDAO_p=null;
		try 
		{
			objHisDAO_p = new HisDAO("Registration","DailyPatientDAO");
			funcIndex = objHisDAO_p.setFunction("{? = call pkg_reg_util.fun_generatebillno(?::numeric,?::numeric)}");
			objHisDAO_p.setFuncInValue(funcIndex, 2, objUserVO_p.getHospitalCode());
			objHisDAO_p.setFuncInValue(funcIndex, 3,strProcesType_p);
			objHisDAO_p.setFuncOutValue(funcIndex,3);
			
			objHisDAO_p.executeFuncForNumeric(funcIndex);
			strBillNo = objHisDAO_p.getFuncNumeric(funcIndex)+"";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDAO.generateBillNo()" + e);
		}
		finally 
		{
			if (objHisDAO_p != null) 
			{
				objHisDAO_p.free();
				objHisDAO_p = null;
			}
		}
		return strBillNo;
	}
	
	/*public DailyPatientVO getTokenDtlOldDeptVisitNew(HisDAO objHisDAO_p,DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
	{
		objDailyPatientVO_p=generateQueueNumber(objDailyPatientVO_p, objUserVO_p);
	
	
	if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
		objDailyPatientVO_p.setFileNo(fileNo);
		
	if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
			|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
			"Procedure Generate Que No:: returns null value");

		///filenoview same as file number
		//objDailyPatientVO_p.setFileNoView(objDailyPatientVO_p.getFileNo());


	
	
	return objDailyPatientVO_p;
	}
	
	public DailyPatientVO getTokenDtlOldDeptVisitNew(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
			{
		Connection conn = super.getTransactionContext().getConnection();
		try{
				Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_OLD_PATIENT);
			strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentUnitCode());
			strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getRoomCode());
			strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
			strProc.addInParameter(4, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
			strProc.addOutParameter(5, Types.VARCHAR);
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addOutParameter(8, Types.VARCHAR);
			strProc.addInParameter(9, Types.VARCHAR, objUserVO_p.getHospitalCode());
			strProc.addInParameter(10, Types.VARCHAR,objDailyPatientVO_p.getIsNewFileRequired());//to be pass ed from new and old process
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addInParameter(12, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
			strProc.addOutParameter(13, Types.VARCHAR);
			strProc.addOutParameter(14, Types.VARCHAR);
			strProc.addOutParameter(15, Types.VARCHAR);
			strProc.addOutParameter(16, Types.VARCHAR);
			strProc.execute(conn);
			String queNo = (String) strProc.getParameterAt(5);
			String roomCode = (String) strProc.getParameterAt(6);
			String deptUnitCode = (String) strProc.getParameterAt(7);
			String unitCap = (String) strProc.getParameterAt(8);
			String fileNo=(String) strProc.getParameterAt(11); 
			String unitName=(String) strProc.getParameterAt(13);
			String roomName=(String) strProc.getParameterAt(14);
			String status=(String) strProc.getParameterAt(15);
			String unitDays=(String) strProc.getParameterAt(16);
			// String
			// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+objDailyPatientVO_p.getDepartmentCode()+unitCode;
			if(status.equals("2"))
				throw new HisAppointmentNotAvailableException("Appointment Not Available");
			
			objDailyPatientVO_p.setQueNo(queNo);
			objDailyPatientVO_p.setRoomCode(roomCode);
			objDailyPatientVO_p.setDepartmentUnitCode(deptUnitCode);
			objDailyPatientVO_p.setPatientAllowed(unitCap);
			objDailyPatientVO_p.setDepartmentUnit(unitName);
			objDailyPatientVO_p.setRoom(roomName);
			objDailyPatientVO_p.setUnitWorkingDays(unitDays);
			
			
			if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
				objDailyPatientVO_p.setFileNo(fileNo);
				
			if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
					|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
					"Procedure Generate Que No:: returns null value");

	///filenoview same as file number
			//objDailyPatientVO_p.setFileNoView(objDailyPatientVO_p.getFileNo());
	
	
			}
			catch (HisInvalidTokenNumberException e)
			{
				e.printStackTrace();
				throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
			}
			catch (HisAppointmentNotAvailableException e)
			{
				e.printStackTrace();
				throw new HisAppointmentNotAvailableException(e.getMessage());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new HisDataAccessException("Procedure Generate Que No::" + e);
			}
			
			return objDailyPatientVO_p;
			}	
*/
	
	
	////Create for new Department visit
	/*public DailyPatientVO createNewDepartment(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p,String _referMlcNo)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = "";	//RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{

			if (objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG)) || 
					( objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG)) 	)
			{
				// code to execute procedure for queue no for special clinic

				Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_SPECIAL_CLINIC_NEW);
				strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, objUserVO_p.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,objDailyPatientVO_p.getIsNewFileRequired());
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
			    String [] printFlagArray=printFlagFileNo.split("@");
			    String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");


				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				objDailyPatientVO_p.setQueNo(queNo);
				objDailyPatientVO_p.setRoomCode(roomCode);
				objDailyPatientVO_p.setDepartmentUnitCode(deptUnitCode);
				objDailyPatientVO_p.setPatientAllowed(unitCap);
				//objDailyPatientVO_p.setDepartment(deptName);
				objDailyPatientVO_p.setRoom(roomName);
				objDailyPatientVO_p.setUnitWorkingDays(unitDays);
				objDailyPatientVO_p.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				String episodeCode=deptUnitCode+"001";
				objDailyPatientVO_p.setEpisodeCode(episodeCode);
				///////////
				//for printing formats
				objDailyPatientVO_p.setCardPrintSetting(printFlagFileNo.substring(0,2));
				objDailyPatientVO_p.setFilePrintSetting(printFlagFileNo.substring(2,5));

				
				
				if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
						|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			}
			else if (objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL))
			{

				Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_NEW);
				strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentCode());
				strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, objUserVO_p.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,objDailyPatientVO_p.getIsNewFileRequired());//to be pass ed from new and old process
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
			    String [] printFlagArray=printFlagFileNo.split("@");
			    String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");
				String unitName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				// String
				// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+objDailyPatientVO_p.getDepartmentCode()+unitCode;
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				objDailyPatientVO_p.setQueNo(queNo);
				objDailyPatientVO_p.setRoomCode(roomCode);
				objDailyPatientVO_p.setDepartmentUnitCode(deptUnitCode);
				objDailyPatientVO_p.setPatientAllowed(unitCap);
				objDailyPatientVO_p.setDepartmentUnit(unitName);
				objDailyPatientVO_p.setRoom(roomName);
				objDailyPatientVO_p.setUnitWorkingDays(unitDays);
				objDailyPatientVO_p.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				String episodeCode=deptUnitCode+"001";
				objDailyPatientVO_p.setEpisodeCode(episodeCode);
				///////////
				//for printing formats
				objDailyPatientVO_p.setCardPrintSetting(printFlagFileNo.substring(0,2));
				objDailyPatientVO_p.setFilePrintSetting(printFlagFileNo.substring(2,5));

				if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
						|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
						"Procedure Generate Que No:: returns null value");
			}
						///filenoview same as file number
			objDailyPatientVO_p.setFileNoView(objDailyPatientVO_p.getFileNo());
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}

		try
		{
			
			populateMapNewDepartment(objDailyPatientVO_p, objUserVO_p, populateMAP);
			////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				objDailyPatientVO_p.setEpisodeTypeCode(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(objDailyPatientVO_p,populateMAP)::" + e);
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

		
		return objDailyPatientVO_p;
	}*/
	
////Create for new Department visit
	/*public DailyPatientVO createNewDepartment(HisDAO objHisDAO_p,DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p,String _referMlcNo)
	{

		String query = "";
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try
		{

			objDailyPatientVO_p=generateQueueNumber(objDailyPatientVO_p, objUserVO_p);
						///filenoview same as file number
			
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?)}";
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(objDailyPatientVO_p);
			HelperMethods.setNullToEmpty(objDailyPatientVO_p.getPatAddress());
		
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgbl_is_actual_dob",objDailyPatientVO_p.getIsActualDob(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_age", objDailyPatientVO_p.getPatAge(),3);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_ageUnit", objDailyPatientVO_p.getPatAgeUnit(),4);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_DOB", (objDailyPatientVO_p.getPatDOB().equals(null)|| objDailyPatientVO_p.getPatDOB().equals(""))?"":objDailyPatientVO_p.getPatDOB(),5);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objDailyPatientVO_p.getPatCrNo(),6);
			if(objDailyPatientVO_p.getPrevCrNo().equalsIgnoreCase("") || objDailyPatientVO_p.getPrevCrNo().equals(null))
			{
				objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", "0",11);
			}
			else
			{
				objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", objDailyPatientVO_p.getPrevCrNo(),11);
			}
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objDailyPatientVO_p.getEpisodeCode(),47);
			if(objDailyPatientVO_p.getPatDOB().equalsIgnoreCase("") || objDailyPatientVO_p.getPatDOB().equals(null))
			{
				objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob","" ,12);
			}
			else
			{
				objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob", objDailyPatientVO_p.getPatDOB(),12);
			}
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objDailyPatientVO_p.getEpisodeVisitNo(),7);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname", objDailyPatientVO_p.getPatFirstName(),16);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", objDailyPatientVO_p.getEpisodeTypeCode(),48);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_id_no", objDailyPatientVO_p.getPatIdNo(),8);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", objDailyPatientVO_p.getPatPrimaryCatCode(),9);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", objDailyPatientVO_p.getPatSecondaryCatCode()==""?"0":objDailyPatientVO_p.getPatSecondaryCatCode(),10);
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", objDailyPatientVO_p.getIsMLC()==""?"0":objDailyPatientVO_p.getIsMLC(),13);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", objDailyPatientVO_p.getIsActualDob(),14);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_old", objDailyPatientVO_p.getPatIsOld(),15);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname", objDailyPatientVO_p.getPatMiddleName(),17);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname", objDailyPatientVO_p.getPatLastName(),18);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_gender_code", objDailyPatientVO_p.getPatGenderCode(),19);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", objDailyPatientVO_p.getPatMaritalStatusCode().equals("")?"0":objDailyPatientVO_p.getPatMaritalStatusCode(),20);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_religion_code", objDailyPatientVO_p.getPatReligionCode(),21);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", objDailyPatientVO_p.getPatStatusCode(),22);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_father_name", objDailyPatientVO_p.getPatGuardianName(),23);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_momname", objDailyPatientVO_p.getPatMotherName(),24);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", objDailyPatientVO_p.getPatRegCatCode(),25);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_register_date", objDailyPatientVO_p.getRegisterDate().equals("")?"":objDailyPatientVO_p.getRegisterDate(),26);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code", objDailyPatientVO_p.getDepartmentCode().substring(0,3),27);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", objDailyPatientVO_p.getDepartmentUnitCode(),28);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_que_no", objDailyPatientVO_p.getQueNo(),29);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", objDailyPatientVO_p.getIsUnknown(),30);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_month_income", objDailyPatientVO_p.getPatMonthlyIncome().equals("")?"0":objDailyPatientVO_p.getPatMonthlyIncome(),31);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", objDailyPatientVO_p.getPatAmountCollected(),32);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", objDailyPatientVO_p.getPatIsUrban(),33);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", objDailyPatientVO_p.getIsReferred().equals("")?"0":objDailyPatientVO_p.getIsReferred(),34);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", objDailyPatientVO_p.getPatRefGnctdHospitalCode().equals("")?"0":objDailyPatientVO_p.getPatRefGnctdHospitalCode(),35);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", objDailyPatientVO_p.getPatRefDoctor(),36);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_local", objDailyPatientVO_p.getPatAddress().getIsAddressDelhi(),37);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_house_no", objDailyPatientVO_p.getPatAddress().getPatAddHNo(),38);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", objDailyPatientVO_p.getPatAddress().getPatAddCityLocCode().equals("")?"0":objDailyPatientVO_p.getPatAddress().getPatAddCityLocCode(),39);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_district", objDailyPatientVO_p.getPatAddress().getPatAddDistrict(),40);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_num_dist_id", objDailyPatientVO_p.getPatAddress().getPatAddDistrictCode().equals("")?"0":objDailyPatientVO_p.getPatAddDistrictCode(),41);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city", objDailyPatientVO_p.getPatAddress().getPatAddCity(),42);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_statecode", objDailyPatientVO_p.getPatAddress().getPatAddStateCode(),43);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_state_name", objDailyPatientVO_p.getPatAddress().getPatAddStateName(),44);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_countrycode", objDailyPatientVO_p.getPatAddress().getPatAddCountryCode(),45);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_pin", objDailyPatientVO_p.getPatAddress().getPatAddPIN().equals("")?"0": objDailyPatientVO_p.getPatAddress().getPatAddPIN(),46);
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid", objDailyPatientVO_p.getIsValid(),50);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", objDailyPatientVO_p.getPatientAllowed(),51);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_room_code", objDailyPatientVO_p.getRoomCode(),52);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(),53);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", objDailyPatientVO_p.getPatAddress().getPatAddContactNo(),54);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", objDailyPatientVO_p.getPatRefHospitalName(),55);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", objDailyPatientVO_p.getPatAddress().getPatAddTypeCode(),56);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city_location", objDailyPatientVO_p.getPatAddress().getPatAddCityLoc(),57);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objDailyPatientVO_p.getPatAddress().getSeatId().equals("")?objUserVO_p.getSeatId():objDailyPatientVO_p.getPatAddress().getSeatId(),58);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_nationality_code", objDailyPatientVO_p.getPatNationalityCode(),59);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", objDailyPatientVO_p.getPatRefGnctdHospitalCrno().equals("")?"0":objDailyPatientVO_p.getPatRefGnctdHospitalCrno(),60);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", objDailyPatientVO_p.getPatRefGnctdHospitalDept(),61);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", objDailyPatientVO_p.getPatRefGnctdHospitalDeptUnit(),62);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_spousename", objDailyPatientVO_p.getPatHusbandName(),63);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_national_id", objDailyPatientVO_p.getPatNationalId(),64);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", objDailyPatientVO_p.getPatOccupation(),65);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", objDailyPatientVO_p.getPatFatherOccupation(),66);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", objDailyPatientVO_p.getPatHusbandOccupation(),67);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname", objDailyPatientVO_p.getPatNickName(),68);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_no", objDailyPatientVO_p.getPatCardNo(),69);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),70);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_file_no", objDailyPatientVO_p.getFileNo(),71);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", objDailyPatientVO_p.getPatIdMark1(),72);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", objDailyPatientVO_p.getPatIdMark2(),73);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", objDailyPatientVO_p.getIsFree().equals("")?"0":objDailyPatientVO_p.getIsFree(),74);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", objDailyPatientVO_p.getIsBroughtDead().equals("")?"0":objDailyPatientVO_p.getIsBroughtDead(),75);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", objDailyPatientVO_p.getDisasterId().equals("")?"0":objDailyPatientVO_p.getDisasterId(),76);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", objDailyPatientVO_p.getOldCrNo(),77);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", objDailyPatientVO_p.getOldRegDate().equals("")?"":objDailyPatientVO_p.getOldRegDate(),78);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_num_caste_id", objDailyPatientVO_p.getPatCasteCode(),79);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", objDailyPatientVO_p.getPatBloodGroupCode().equals("")?"0":objDailyPatientVO_p.getPatBloodGroupCode(),80);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", objDailyPatientVO_p.getIsNewBorn(),81);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", objDailyPatientVO_p.getPatMotherCrNo().equals("")?"0":objDailyPatientVO_p.getPatMotherCrNo(),82);
			//objHisDAO_p.setProcOutValue(nProcIndex1, "patDOB", 1);
			//objHisDAO_p.setProcOutValue(nProcIndex1, "patAge", 1);
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,83);
			
			
			objHisDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//objDailyPatientVO_p.setPatDOB(objHisDAO_p.getString(nProcIndex1, "patDOB"));
						//objDailyPatientVO_p.setPatAge(objHisDAO_p.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
				
		return objDailyPatientVO_p;
	}*/
	
	/**
	 * Populates the map with the patient's daily details to be entered in the DB.
	 * 
	 * @param objDailyPatientVO_p Provides daily patient details to be entered.
	 * @param _populateMAP Map containig values which will be used for insert query.
	 */
	public void populateMapNewDepartment(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p, Map _populateMAP) throws SQLException
	{
		System.out.println("inside populateMap::" + objDailyPatientVO_p);

		Sequence sq = new Sequence();

		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo());
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo());//setting the crno for genrating sl no
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode()); // generating
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo()); // sl	
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode());//no
		//_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());//for
		//_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());//visit
		/*_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo()); //setting the crno for genrating visitno
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode()); //setting the episode number for genrating visitno
		_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());*/
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeVisitNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatPrimaryCatCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPrevCrNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIdNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatDOB());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getPatDOB().toString());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsMLC());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsActualDob());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIsOld());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatFirstName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMiddleName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatLastName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatGenderCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatReligionCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatStatusCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatGuardianName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMotherName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRegCatCode());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getRegisterDate().toString());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getExpiryDate().toString());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getRegisterDate());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getExpiryDate());		 
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getDepartmentCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getDepartmentUnitCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getQueNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsUnknown());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAmountCollected());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIsUrban());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsReferred());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefDoctor());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getEmergencyType());		 
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getIsAddressDelhi());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddHNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCityLocCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddDistrict());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddDistrictCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCity());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddStateCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddStateName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCountryCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddPIN());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getEpisodeType()); 
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeTypeCode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getEpisodeDate());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsValid());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatientAllowed());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getRoomCode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getEntryDate());
		_populateMAP.put(sq.next(), objUserVO_p.getIpAddress());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddContactNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefHospitalName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddTypeCode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getPatCatcode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCityLoc());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getSeatId());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatNationalityCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalCrno());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalDept());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatHusbandName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatNationalId());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatOccupation());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatFatherOccupation());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatHusbandOccupation());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatNickName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCardNo());
		_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getFileNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIdMark1());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIdMark2());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsFree());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsBroughtDead());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getDisasterId());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getOldCrNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getOldRegDate()==null||objDailyPatientVO_p.getOldRegDate().trim().equals("")?"":objDailyPatientVO_p.getOldRegDate().trim());
	}

	
////Create for new Department visit (in use, but procedure is not created yet)
	public DailyPatientVO createForceFulVisit(HisDAO objHisDAO_p,DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p,String _referMlcNo)
	{

		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		String strErr = "";
		int nfuncIndex = 0;
		String strFunc="";
		
		// call the getQueryMethod with arguments filename,querykey from prop file
		
		objDailyPatientVO_p=generateQueueNumberDepartmentWise(objDailyPatientVO_p, objUserVO_p);
		
		strFunc = "{? = call REG_FUNCTION.puk_episode_gen(?,?,?)}";
		//////generate episode code
		try
		{
			nfuncIndex = objHisDAO_p.setFunction(strFunc);
			
			objHisDAO_p.setFuncInValue(nfuncIndex, 2, objDailyPatientVO_p.getPatCrNo());
			objHisDAO_p.setFuncInValue(nfuncIndex, 3, objUserVO_p.getHospitalCode());
			objHisDAO_p.setFuncInValue(nfuncIndex, 4, objDailyPatientVO_p.getDepartmentUnitCode());
			objHisDAO_p.setFuncOutValue(nfuncIndex, 3);
		
			objHisDAO_p.executeFuncForNumeric(nfuncIndex);
		
			String episodeNo = objHisDAO_p.getFuncNumeric(nfuncIndex);
			
			objDailyPatientVO_p.setEpisodeCode(episodeNo);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}

		///////////

		try
		{
			
		////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				objDailyPatientVO_p.setEpisodeTypeCode(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(objDailyPatientVO_p,populateMAP)::" + e);
		}

		return objDailyPatientVO_p;
	}
	public DailyPatientVO createForceFulVisit(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p,String _referMlcNo)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = "";	//RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		//System.out.println("query" + query);

		
		
		try
		{

			if (objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL) || 
					(objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG)) || 
					( objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG)) 	)
			{
				// code to execute procedure for queue no for special clinic

				Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_SPECIAL_CLINIC_NEW);
				strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, objUserVO_p.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,objDailyPatientVO_p.getIsNewFileRequired());
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String fileNo=(String) strProc.getParameterAt(10); 
				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				objDailyPatientVO_p.setQueNo(queNo);
				objDailyPatientVO_p.setRoomCode(roomCode);
				objDailyPatientVO_p.setDepartmentUnitCode(deptUnitCode);
				objDailyPatientVO_p.setPatientAllowed(unitCap);
				//objDailyPatientVO_p.setDepartment(deptName);
				objDailyPatientVO_p.setRoom(roomName);
				objDailyPatientVO_p.setUnitWorkingDays(unitDays);
				
				
				
				/*if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					objDailyPatientVO_p.setFileNo(fileNo);*/
				
				if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
						|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			}
			else if (objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL))
			{

				Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_NEW);
				strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentCode());
				strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, objUserVO_p.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,objDailyPatientVO_p.getIsNewFileRequired());//to be pass ed from new and old process
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
				String [] printFlagArray=printFlagFileNo.split("@");
				String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");


				String unitName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				// String
				// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+objDailyPatientVO_p.getDepartmentCode()+unitCode;
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				objDailyPatientVO_p.setQueNo(queNo);
				objDailyPatientVO_p.setRoomCode(roomCode);
				objDailyPatientVO_p.setDepartmentUnitCode(deptUnitCode);
				objDailyPatientVO_p.setPatientAllowed(unitCap);
				objDailyPatientVO_p.setDepartmentUnit(unitName);
				objDailyPatientVO_p.setRoom(roomName);
				objDailyPatientVO_p.setUnitWorkingDays(unitDays);
				
			
				//for printing formats
				objDailyPatientVO_p.setCardPrintSetting(printFlagFileNo.substring(0,2));
				objDailyPatientVO_p.setFilePrintSetting(printFlagFileNo.substring(2,5));

				/*if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					objDailyPatientVO_p.setFileNo(fileNo);*/
					
				if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
						|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
						"Procedure Generate Que No:: returns null value");
			}
						///filenoview same as file number
			objDailyPatientVO_p.setFileNoView(objDailyPatientVO_p.getFileNo());
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		
		//////generate episode code
		try
		{
			
				Procedure strEpisodeProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_GET_EPISODENO);
				strEpisodeProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getPatCrNo());
				strEpisodeProc.addInParameter(2, Types.VARCHAR, objUserVO_p.getHospitalCode());
				strEpisodeProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getDepartmentUnitCode());
				strEpisodeProc.setReturnType(Types.VARCHAR);
				String episodeNo = (String) strEpisodeProc.execute(conn);
				objDailyPatientVO_p.setEpisodeCode(episodeNo);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}

		///////////

		try
		{
			
			populateMapForceFulVisit(objDailyPatientVO_p, objUserVO_p, populateMAP);
			////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				objDailyPatientVO_p.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(objDailyPatientVO_p,populateMAP)::" + e);
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

		
		return objDailyPatientVO_p;
	}

	/**
	 * Populates the map with the patient's daily details to be entered in the DB.
	 * 
	 * @param objDailyPatientVO_p Provides daily patient details to be entered.
	 * @param _populateMAP Map containig values which will be used for insert query.
	 */
	public void populateMapForceFulVisit(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p, Map _populateMAP) throws SQLException
	{
		System.out.println("inside populateMap::" + objDailyPatientVO_p);

		Sequence sq = new Sequence();

		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo());
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo());//setting the crno for genrating sl no
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode()); // generating
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo()); // sl	
		//_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode());//no
		//_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());//for
		//_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());//visit
		/*_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCrNo()); //setting the crno for genrating visitno
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode()); //setting the episode number for genrating visitno
		_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());*/
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeVisitNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatPrimaryCatCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPrevCrNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIdNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatDOB());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getPatDOB().toString());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsMLC());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsActualDob());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIsOld());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatFirstName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMiddleName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatLastName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatGenderCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatReligionCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatStatusCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatGuardianName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMotherName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRegCatCode());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getRegisterDate().toString());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getExpiryDate().toString());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getRegisterDate());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getExpiryDate());		 
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getDepartmentCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getDepartmentUnitCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getQueNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsUnknown());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAmountCollected());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIsUrban());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsReferred());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefDoctor());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getEmergencyType());		 
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getIsAddressDelhi());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddHNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCityLocCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddDistrict());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddDistrictCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCity());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddStateCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddStateName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCountryCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddPIN());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeCode());
		//_populateMAP.put(sq.next(),objDailyPatientVO_p.getEpisodeType()); 
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getEpisodeTypeCode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getEpisodeDate());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsValid());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatientAllowed());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getRoomCode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getEntryDate());
		_populateMAP.put(sq.next(), objUserVO_p.getIpAddress());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddContactNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefHospitalName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddTypeCode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getPatCatcode());
		// _populateMAP.put(sq.next(),objDailyPatientVO_p.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getPatAddCityLoc());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatAddress().getSeatId());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatNationalityCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalCrno());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalDept());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatHusbandName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatNationalId());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatOccupation());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatFatherOccupation());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatHusbandOccupation());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatNickName());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatCardNo());
		_populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getFileNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIdMark1());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getPatIdMark2());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsFree());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getIsBroughtDead());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getDisasterId());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getOldCrNo());
		_populateMAP.put(sq.next(), objDailyPatientVO_p.getOldRegDate());
	}

	public void updateUnknownConversion(HisDAO objHisDAO_p, DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
	{
		//String queryKey = "UPDATE.UNKNOWN_CONVERSION.HRGT_DAILY_PATIENT_DTL";
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(objDailyPatientVO_p);
			HelperMethods.setNullToEmpty(objDailyPatientVO_p.getPatAddress());
			
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeVal", "3");
			objHisDAO_p.setProcInValue(nProcIndex1, "p_isActialDOB",objDailyPatientVO_p.getIsActualDob());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_age", objDailyPatientVO_p.getPatAge());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_ageUnit", objDailyPatientVO_p.getPatAgeUnit());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_DOB", objDailyPatientVO_p.getPatDOB());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objDailyPatientVO_p.getPatCrNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objDailyPatientVO_p.getEpisodeVisitNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", objDailyPatientVO_p.getPatPrimaryCatCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", objDailyPatientVO_p.getPatSecondaryCatCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", objDailyPatientVO_p.getPrevCrNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_id_no", objDailyPatientVO_p.getPatIdNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob", objDailyPatientVO_p.getPatDOB());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", objDailyPatientVO_p.getIsMLC());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", objDailyPatientVO_p.getIsActualDob());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_old", objDailyPatientVO_p.getPatIsOld());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname", objDailyPatientVO_p.getPatFirstName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname", objDailyPatientVO_p.getPatMiddleName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname", objDailyPatientVO_p.getPatLastName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_gender_code", objDailyPatientVO_p.getPatGenderCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", objDailyPatientVO_p.getPatMaritalStatusCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_religion_code", objDailyPatientVO_p.getPatReligionCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", objDailyPatientVO_p.getPatStatusCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_father_name", objDailyPatientVO_p.getPatGuardianName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_momname", objDailyPatientVO_p.getPatMotherName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", objDailyPatientVO_p.getPatRegCatCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_register_date", objDailyPatientVO_p.getRegisterDate());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code", "");
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", objDailyPatientVO_p.getDepartmentCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_que_no", objDailyPatientVO_p.getQueNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", objDailyPatientVO_p.getIsUnknown());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_month_income", objDailyPatientVO_p.getPatMonthlyIncome());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", objDailyPatientVO_p.getPatAmountCollected());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", objDailyPatientVO_p.getPatIsUrban());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", objDailyPatientVO_p.getIsReferred());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", objDailyPatientVO_p.getPatRefGnctdHospitalCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", objDailyPatientVO_p.getPatRefDoctor());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_local", objDailyPatientVO_p.getPatAddress().getIsAddressDelhi());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_house_no", objDailyPatientVO_p.getPatAddress().getPatAddHNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", objDailyPatientVO_p.getPatAddress().getPatAddCityLocCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_district", objDailyPatientVO_p.getPatAddress().getPatAddDistrict());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_num_dist_id", objDailyPatientVO_p.getPatAddress().getPatAddDistrictCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city", objDailyPatientVO_p.getPatAddress().getPatAddCity());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_statecode", objDailyPatientVO_p.getPatAddress().getPatAddStateCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_state_name", objDailyPatientVO_p.getPatAddress().getPatAddStateName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_countrycode", objDailyPatientVO_p.getPatAddress().getPatAddCountryCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_pin", objDailyPatientVO_p.getPatAddress().getPatAddPIN());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objDailyPatientVO_p.getEpisodeCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", objDailyPatientVO_p.getEpisodeTypeCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "");
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid", objDailyPatientVO_p.getIsValid());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", objDailyPatientVO_p.getPatientAllowed());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_room_code", objDailyPatientVO_p.getRoomCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", objDailyPatientVO_p.getPatAddress().getPatAddContactNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", objDailyPatientVO_p.getPatRefHospitalName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", objDailyPatientVO_p.getPatAddress().getPatAddTypeCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city_location", objDailyPatientVO_p.getPatAddress().getPatAddCityLoc());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objDailyPatientVO_p.getPatAddress().getSeatId());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_nationality_code", objDailyPatientVO_p.getPatNationalityCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", objDailyPatientVO_p.getPatRefGnctdHospitalCrno());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", objDailyPatientVO_p.getPatRefGnctdHospitalDept());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", objDailyPatientVO_p.getPatRefGnctdHospitalDeptUnit());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_spousename", objDailyPatientVO_p.getPatHusbandName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_national_id", objDailyPatientVO_p.getPatNationalId());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", objDailyPatientVO_p.getPatOccupation());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", objDailyPatientVO_p.getPatFatherOccupation());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", objDailyPatientVO_p.getPatHusbandOccupation());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname", objDailyPatientVO_p.getPatNickName());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_no", objDailyPatientVO_p.getPatCardNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_file_no", objDailyPatientVO_p.getFileNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", objDailyPatientVO_p.getPatIdMark1());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", objDailyPatientVO_p.getPatIdMark2());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", objDailyPatientVO_p.getIsFree());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", objDailyPatientVO_p.getIsBroughtDead());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", objDailyPatientVO_p.getDisasterId());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", objDailyPatientVO_p.getOldCrNo());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", objDailyPatientVO_p.getOldRegDate());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_num_caste_id", objDailyPatientVO_p.getPatCasteCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", objDailyPatientVO_p.getPatBloodGroupCode());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", objDailyPatientVO_p.getIsNewBorn());
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", objDailyPatientVO_p.getPatMotherCrNo());
			//objHisDAO_p.setProcOutValue(nProcIndex1, "patDOB", 1);
			//objHisDAO_p.setProcOutValue(nProcIndex1, "patAge", 1);
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1);
			
			objHisDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//objDailyPatientVO_p.setPatDOB(objHisDAO_p.getString(nProcIndex1, "patDOB"));
						//objDailyPatientVO_p.setPatAge(objHisDAO_p.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	}
	
	public int updateChangeRoomDetail(RoomChangeVO roomChangeVO,UserVO objUserVO_p)
	{
		int x = 0;
		Sequence sq = new Sequence();
		String query = "";
		Map _populateUpdateMap = new HashMap();
		String filename = "";	//RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HRGT_DAILY_PATIENT_DTL.ROOM_CHANGE_DETAIL";
		Connection conn = super.getTransactionContext().getConnection();
		System.out.println("After obtaining connection in update of PatientDAO ");
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{

			_populateUpdateMap.put(sq.next(), roomChangeVO.getChangeType());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getFromRoomCode());// it may change so ask
			_populateUpdateMap.put(sq.next(), roomChangeVO.getToDeptUnitCode());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getToRoomCode());
			
			_populateUpdateMap.put(sq.next(), roomChangeVO.getPatCrNo());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getEpisodeCode());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getEpisodeVisitNo());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			x = HelperMethodsDAO.excecuteUpdate(conn, query, _populateUpdateMap);
			if (x == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException("update unsuccessful::" + e);
			}
			else throw new HisDataAccessException("DailyPatientDAO::while updating data " + e);
		}
		return x;
	}
	
	public DailyPatientVO generateQueueNumberRoomWise(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
				
		try
		{
						
			///Deciding file generation type
			String fileNoGeneration="";
			
			/*if(objDailyPatientVO_p.getFileNo()==null || objDailyPatientVO_p.getFileNo().equals(""))
				fileNoGeneration=Config.FILE_NO_GENRATION_AUTO_TRUE;
			else
				fileNoGeneration=Config.FILE_NO_GENRATION_MANNUAL_TRUE;*/
			
				// code to execute procedure for queue no

				Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_OFFLINE_REGISTRATION_GET_TOKEN_DTL);
				strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addInParameter(5, Types.NUMERIC,objDailyPatientVO_p.getRoomCode());
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, objUserVO_p.getHospitalCode());
				//strProc.addInParameter(9, Types.VARCHAR,Config.FILE_NO_GENERATION_FLAG);/// also commented setfileno method
				strProc.addInParameter(9, Types.VARCHAR,fileNoGeneration);
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				//String roomCode = (String) strProc.getParameterAt(5);
				//String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
				
				String [] printFlagArray=null;
				if(printFlagFileNo!=null && printFlagFileNo.length() > 0)
					printFlagArray=printFlagFileNo.split("@");
				String fileNo=(printFlagArray!=null && printFlagArray.length>1?printFlagArray[1]:"");

				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				System.out.println("unitConsultant-----"+unitConsultant);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				objDailyPatientVO_p.setQueNo(queNo);
				objDailyPatientVO_p.setRoomCode(objDailyPatientVO_p.getRoomCode());
				objDailyPatientVO_p.setDepartmentUnitCode(objDailyPatientVO_p.getDepartmentUnitCode());
				objDailyPatientVO_p.setPatientAllowed(unitCap);
				//objDailyPatientVO_p.setDepartment(deptName);
				objDailyPatientVO_p.setRoom(roomName);
				objDailyPatientVO_p.setUnitWorkingDays(unitDays);
				objDailyPatientVO_p.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				String episodeCode=objDailyPatientVO_p.getDepartmentUnitCode()+"001";
				objDailyPatientVO_p.setEpisodeCode(episodeCode);
				///////////
				//for printing formats
				objDailyPatientVO_p.setCardPrintSetting(printFlagFileNo.substring(0,2));
				objDailyPatientVO_p.setFilePrintSetting(printFlagFileNo.substring(2,5));
			
				/*if(fileNoGeneration.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					objDailyPatientVO_p.setFileNo(fileNo);*/
				
				if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
						|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
							
			///filenoview same as file number
			objDailyPatientVO_p.setFileNoView(objDailyPatientVO_p.getFileNo());
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		return objDailyPatientVO_p;
	}
	//offline registration now createNewregistrationMethod is used
	/*public DailyPatientVO createNewOfflineRegistration(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL_NEW_REGISTRATION";
		String queryKeyGenrateRoomEmg="GENRATE_ROOM_CODE.EMERGENCY";
		String queryGenrateRoomEmg="";
		Map populateMAPGenrateRoomEmg=new HashMap();
		String roomCodeEmg=null;
		
		

		try
		{
			populateMapNewRegistration(objDailyPatientVO_p, objUserVO_p, populateMAP);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return objDailyPatientVO_p;
	}*/

////Create for new Department visit roomWise
	public DailyPatientVO createNewDepartmentRoomWise(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p,String _referMlcNo)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename ="";	// RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		//System.out.println("query" + query);

				
		try
		{
			
			///Deciding file generation type
			String fileNoGeneration="";
			
			/*if(objDailyPatientVO_p.getFileNo()==null || objDailyPatientVO_p.getFileNo().equals(""))
				fileNoGeneration=Config.FILE_NO_GENRATION_AUTO_TRUE;
			else
				fileNoGeneration=Config.FILE_NO_GENRATION_MANNUAL_TRUE;*/

			Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_OFFLINE_REGISTRATION_GET_TOKEN_DTL);
			strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentUnitCode());
			strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
			strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addInParameter(5, Types.NUMERIC,objDailyPatientVO_p.getRoomCode());
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addInParameter(8, Types.VARCHAR, objUserVO_p.getHospitalCode());
			//strProc.addInParameter(9, Types.VARCHAR,Config.FILE_NO_GENERATION_FLAG);/// also commented setfileno method
			strProc.addInParameter(9, Types.VARCHAR,fileNoGeneration);
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addInParameter(11, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
			strProc.addOutParameter(12, Types.VARCHAR);
			strProc.addOutParameter(13, Types.VARCHAR);
			strProc.addOutParameter(14, Types.VARCHAR);
			strProc.addOutParameter(15, Types.VARCHAR);
			strProc.addOutParameter(16, Types.VARCHAR);
			strProc.execute(conn);
			String queNo = (String) strProc.getParameterAt(4);
			//String roomCode = (String) strProc.getParameterAt(5);
			//String deptUnitCode = (String) strProc.getParameterAt(6);
			String unitCap = (String) strProc.getParameterAt(7);
			String printFlagFileNo=(String) strProc.getParameterAt(10); 
			String [] printFlagArray=printFlagFileNo.split("@");
			String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");

			String deptName=(String) strProc.getParameterAt(12);
			String roomName=(String) strProc.getParameterAt(13);
			String status=(String) strProc.getParameterAt(14);
			String unitDays=(String) strProc.getParameterAt(15);
			String unitConsultant=(String) strProc.getParameterAt(16);
			System.out.println("unitConsultant----"+unitConsultant);
			
			if(status.equals("2"))
				throw new HisAppointmentNotAvailableException("Appointment Not Available");
			
			objDailyPatientVO_p.setQueNo(queNo);
			objDailyPatientVO_p.setRoomCode(objDailyPatientVO_p.getRoomCode());
			objDailyPatientVO_p.setDepartmentUnitCode(objDailyPatientVO_p.getDepartmentUnitCode());
			objDailyPatientVO_p.setPatientAllowed(unitCap);
			//objDailyPatientVO_p.setDepartment(deptName);
			objDailyPatientVO_p.setRoom(roomName);
			objDailyPatientVO_p.setUnitWorkingDays(unitDays);
			objDailyPatientVO_p.setUnitConsultant(unitConsultant);
			
			//////generate episode code
			String episodeCode=objDailyPatientVO_p.getDepartmentUnitCode()+"001";
			objDailyPatientVO_p.setEpisodeCode(episodeCode);
			///////////
			//for printing formats
			objDailyPatientVO_p.setCardPrintSetting(printFlagFileNo.substring(0,2));
			objDailyPatientVO_p.setFilePrintSetting(printFlagFileNo.substring(2,5));

			/*if(fileNoGeneration.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
				objDailyPatientVO_p.setFileNo(fileNo);*/
			
			if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
					|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) 
				throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
						///filenoview same as file number
			objDailyPatientVO_p.setFileNoView(objDailyPatientVO_p.getFileNo());
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}

		try
		{
			
			populateMapNewDepartment(objDailyPatientVO_p, objUserVO_p, populateMAP);
			////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				objDailyPatientVO_p.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(objDailyPatientVO_p,populateMAP)::" + e);
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

		
		return objDailyPatientVO_p;
	}
	
	public DailyPatientVO[] getTodayPatientListByDeptUnitCode(String mode,String departmentUnitCode, UserVO objUserVO_p)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		DailyPatientVO []dPatientVO=null;
		ValueObject[] valueObjects = null;
		String filename = "";	//RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey ="";
		if(mode.equals("abcFile")){	//(RegistrationConfig.MODE_FILE_NUMBER_PRINT)){
			queryKey = "SELECT.TODAY_PATIENT_FOR_FILE_NO_PRINTING.HRGT_DAILY_PATIENT_DTL";
			
		}else
			queryKey="SELECT.TODAY_PATIENT_FOR_SLIP_PRINTING.HRGT_DAILY_PATIENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		 populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());
		 //if(!departmentUnitCode.equals("%"))
		//	 populateMAP.put(sq.next(), departmentUnitCode.substring(0,3));
		// else
			 //populateMAP.put(sq.next(), departmentUnitCode);
		 populateMAP.put(sq.next(), departmentUnitCode);
		 /*if(mode.equals(RegistrationConfig.MODE_FILE_NUMBER_PRINT)){
			 populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		 }*/
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
			dPatientVO = new DailyPatientVO[valueObjects.length];
			for (int i = 0; i < valueObjects.length; i++)
			{
				dPatientVO[i] = (PatientDetailVO) valueObjects[i];
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
		return dPatientVO;
	}
	
	
	public void updateFilePrintFlag(DailyPatientVO dailyPatientVO, UserVO objUserVO_p)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename ="";	// RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.FILE_PRINT_FLAG.HRGT_DAILY_PATIENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);//pring flag
		populateMAP.put(sq.next(), dailyPatientVO.getPatCrNo());
		populateMAP.put(sq.next(), dailyPatientVO.getEpisodeCode());
		populateMAP.put(sq.next(), dailyPatientVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), objUserVO_p.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
	}
	
	
	
	//old patient dept visit stamp room wise
	public DailyPatientVO createOldPatientDeptVisitRoomWise(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = "";	//RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL_NEW_REGISTRATION";
		//String queryKeyGenrateRoomEmg="GENRATE_ROOM_CODE.EMERGENCY";
		//String queryGenrateRoomEmg="";
		//Map populateMAPGenrateRoomEmg=new HashMap();
		//String roomCodeEmg=null;
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{
						
			///Deciding file generation type
			String fileNoGeneration="";
			
			//if(objDailyPatientVO_p.getFileNo()==null || objDailyPatientVO_p.getFileNo().equals(""))
				//fileNoGeneration=Config.FILE_NO_GENRATION_AUTO_TRUE;
			//else
				//fileNoGeneration=Config.FILE_NO_GENRATION_MANNUAL_TRUE;
			
				// code to execute procedure for queue no

				Procedure strProc = new Procedure("");	//(RegistrationDaoConfig.PROCEDURE_OFFLINE_REGISTRATION_GET_TOKEN_DTL);
				strProc.addInParameter(1, Types.VARCHAR, objDailyPatientVO_p.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, objDailyPatientVO_p.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addInParameter(5, Types.NUMERIC,objDailyPatientVO_p.getRoomCode());
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, objUserVO_p.getHospitalCode());
				//strProc.addInParameter(9, Types.VARCHAR,Config.FILE_NO_GENERATION_FLAG);/// also commented setfileno method
				strProc.addInParameter(9, Types.VARCHAR,fileNoGeneration);
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, objDailyPatientVO_p.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				//String roomCode = (String) strProc.getParameterAt(5);
				//String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String fileNo=(String) strProc.getParameterAt(10); 
				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				System.out.println("unitConsultant-----"+unitConsultant);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				objDailyPatientVO_p.setQueNo(queNo);
				objDailyPatientVO_p.setRoomCode(objDailyPatientVO_p.getRoomCode());
				objDailyPatientVO_p.setDepartmentUnitCode(objDailyPatientVO_p.getDepartmentUnitCode());
				objDailyPatientVO_p.setPatientAllowed(unitCap);
				//objDailyPatientVO_p.setDepartment(deptName);
				objDailyPatientVO_p.setRoom(roomName);
				objDailyPatientVO_p.setUnitWorkingDays(unitDays);
				objDailyPatientVO_p.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				//old episode code will be continued
				//String episodeCode=objDailyPatientVO_p.getDepartmentUnitCode()+"001";
				//objDailyPatientVO_p.setEpisodeCode(episodeCode);
				//increase the visit no by 1
				//objDailyPatientVO_p.setEpisodeVisitNo(String.valueOf(Integer.parseInt(objDailyPatientVO_p.getEpisodeVisitNo()+1)));
				///////////
				
				//if(fileNoGeneration.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					//objDailyPatientVO_p.setFileNo(fileNo);
				
				if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
						|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			///filenoview same as file number
			objDailyPatientVO_p.setFileNoView(objDailyPatientVO_p.getFileNo());
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}

		try
		{
			
			//populateMapNewRegistration(objDailyPatientVO_p, objUserVO_p, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(objDailyPatientVO_p,populateMAP)::" + e);
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

		
		return objDailyPatientVO_p;
	}
	public DailyPatientVO generateQueueNumberDepartmentWise(DailyPatientVO objDailyPatientVO_p, UserVO objUserVO_p)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		HisDAO objHisDAO_p=null;
		String strProcName1="";
		int nProcIndex1=0;
		String strErr="";
		try
		{

			
			
			/*
			 * 1	p_modval in varchar2 default '1',
				2 i_dept_code IN HRGT_DAILY_PATIENT_DTL.GNUM_DEPT_CODE%TYPE,
				3 pri_cat IN  GBLT_PATIENT_CAT_MST.GNUM_PATIENT_CAT_CODE%TYPE,
				4 sec_cat IN  GBLT_PATIENT_CAT_MST.GNUM_PATIENT_CAT_CODE%TYPE,
				5 o_que_no OUT HRGT_DAILY_PATIENT_DTL.HRGNUM_QUE_NO%TYPE,
				6 o_room_code OUT HRGT_DAILY_PATIENT_DTL.HGNUM_ROOM_CODE%TYPE,
				7 o_unit OUT HGBT_UNIT_MST.HGNUM_DEPTUNITCODE%TYPE,
				8 o_cap OUT HRGT_DAILY_PATIENT_DTL.HRGNUM_PATIENT_ALLOWED%TYPE,
				9 i_hospital_code IN GBLT_HOSPITAL_MST.GNUM_HOSPITAL_CODE%TYPE,
				10 fileflag IN NUMBER,
				11 filenumber OUT VARCHAR2,
				12 usability_flag in HOPT_DEPT_UNIT_ROSTER_DTL.HRGNUM_ROOM_USABILITY%type default 0,
				13 unit_name out varchar2,
				14 room_name out varchar2,
				15 status out varchar2,
				16 unittime out varchar2,
				17 unit_inc_name out varchar2,
				18 cardSetting OUT VARCHAR2, 
				19 err OUT VARCHAR2
			 * 
			 * 
			 * if (objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)) || 
					( objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC))||objDailyPatientVO_p.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL) )
			{*/
				
				strProcName1 = "{call PKG_REG_DML.gen_queno_deptwise(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";  // Total 18 Variables

				objHisDAO_p = new HisDAO("Registration","DailyPatientDAO");

				nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);

				objHisDAO_p.setProcInValue(nProcIndex1, "p_modval", "1",1);
				objHisDAO_p.setProcInValue(nProcIndex1, "i_dept_code", objDailyPatientVO_p.getDepartmentUnitCode().substring(0, 3),2);//objDailyPatientVO_p.getDepartmentCode()
				objHisDAO_p.setProcInValue(nProcIndex1, "pri_cat", objDailyPatientVO_p.getPatPrimaryCatCode(),3);
				objHisDAO_p.setProcInValue(nProcIndex1, "sec_cat", objDailyPatientVO_p.getPatPrimaryCatCode(),4);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_que_no", 1,5);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_room_code",1,6);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_unit", 1,7);
				objHisDAO_p.setProcOutValue(nProcIndex1, "o_cap",1,8);				
				objHisDAO_p.setProcInValue(nProcIndex1, "i_hospital_code", objUserVO_p.getHospitalCode(),9);
				//objHisDAO_p.setProcInValue(nProcIndex1, "fileflag","0");
				objHisDAO_p.setProcOutValue(nProcIndex1, "filenumber",1,10);
				objHisDAO_p.setProcInValue(nProcIndex1, "usability_flag", (objDailyPatientVO_p.getRoomUsability()!=null)?"0":"0",11);				
				objHisDAO_p.setProcOutValue(nProcIndex1, "unit_name",1,12);
				objHisDAO_p.setProcOutValue(nProcIndex1, "room_name", 1,13);
				objHisDAO_p.setProcOutValue(nProcIndex1, "status", 1,14);
				objHisDAO_p.setProcOutValue(nProcIndex1, "unittime", 1,15);
				objHisDAO_p.setProcOutValue(nProcIndex1, "unit_inc_name", 1,16);
				objHisDAO_p.setProcOutValue(nProcIndex1, "cardSetting", 1,17);				
				objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,18);
				
				
				
				
				objHisDAO_p.executeProcedureByPosition(nProcIndex1);
				strErr = objHisDAO_p.getString(nProcIndex1, "err");
					if (strErr == null)
						strErr = "";
		
						if (!strErr.equals("")) 
						{
							System.out.println(strErr);
							throw new Exception(strErr);
						}
						else
						{
							String queNo = objHisDAO_p.getString(nProcIndex1, "o_que_no");
							String roomCode = objHisDAO_p.getString(nProcIndex1, "o_room_code");
							String deptUnitCode = objHisDAO_p.getString(nProcIndex1, "o_unit");
							String unitCap = objHisDAO_p.getString(nProcIndex1, "o_cap");
							String unitName=objHisDAO_p.getString(nProcIndex1, "unit_name");
							String roomName=objHisDAO_p.getString(nProcIndex1, "room_name");
							String status=objHisDAO_p.getString(nProcIndex1, "status");
							String unitDays=objHisDAO_p.getString(nProcIndex1, "unittime");
							String unitConsultant = objHisDAO_p.getString(nProcIndex1, "unit_inc_name");
							String cardPrintSetting = objHisDAO_p.getString(nProcIndex1, "cardSetting");
							
							if(status.equals("2"))
								throw new HisAppointmentNotAvailableException("Appointment Not Available");
							
							objDailyPatientVO_p.setQueNo(queNo);
							objDailyPatientVO_p.setRoomCode(roomCode);
							objDailyPatientVO_p.setDepartmentUnitCode(deptUnitCode);
							objDailyPatientVO_p.setPatientAllowed(unitCap);
							objDailyPatientVO_p.setDepartmentUnit(unitName);
							objDailyPatientVO_p.setRoom(roomName);
							objDailyPatientVO_p.setUnitWorkingDays(unitDays);
							objDailyPatientVO_p.setUnitConsultant(unitConsultant);
							objDailyPatientVO_p.setCardPrintSetting(cardPrintSetting);
							//////generate episode code
							String episodeCode=deptUnitCode+"001";
							objDailyPatientVO_p.setEpisodeCode(episodeCode);
							///////////
							//for printing formats
							//objDailyPatientVO_p.setCardPrintSetting("");
							objDailyPatientVO_p.setFilePrintSetting("");
		
							objDailyPatientVO_p.setFileNo("");
								
							if (objDailyPatientVO_p.getQueNo() == null || objDailyPatientVO_p.getRoomCode() == null
									|| objDailyPatientVO_p.getDepartmentUnitCode() == null || objDailyPatientVO_p.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
									"Procedure Generate Que No:: returns null value");
							objDailyPatientVO_p.setFileNoView("");
						}
			//}
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		return objDailyPatientVO_p;
	}
	
	
	public String generateEpisodeCode(UserVO objUserVO_p, String strCRNo, String strDeptUnitCode)
	{
		String strEpisodeCode="";
		int funcIndex=0;
		HisDAO objHisDAO_p=null;
		try 
		{
			objHisDAO_p = new HisDAO("Registration","DailyPatientDAO");
			funcIndex = objHisDAO_p.setFunction("{? = call pkg_reg_util.fun_gen_episodecode(?::numeric,?::numeric,?::numeric)}");
			objHisDAO_p.setFuncInValue(funcIndex, 2, objUserVO_p.getHospitalCode());
			objHisDAO_p.setFuncInValue(funcIndex, 3,strCRNo);
			objHisDAO_p.setFuncInValue(funcIndex, 4,strDeptUnitCode);
			objHisDAO_p.setFuncOutValue(funcIndex,3);
			
			objHisDAO_p.executeFuncForNumeric(funcIndex);
			strEpisodeCode = objHisDAO_p.getFuncNumeric(funcIndex)+"";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDAO.generateBillNo()" + e);
		}
		finally 
		{
			if (objHisDAO_p != null) 
			{
				objHisDAO_p.free();
				objHisDAO_p = null;
			}
		}
		return strEpisodeCode;
	}
	
	
	/**
	 * Retrieves the details of a patient on the basis of CR No. Calculates the
	 * age of the patient as on today.
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public PatientVO retrieveByCrNoDailyPatient(PatientVO objPatientVO_p,  UserVO objUserVO_p,String strVisitType) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_patient_detail_from_Daily_Patient_Dtl_with_crno(?,?,?,?, ?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		AddressVO _addressVO= new AddressVO();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),1);
		//daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_HOSPITAL_CODE,2);p_visittype
		daoObj.setProcInValue(nProcIndex, "p_crno", objPatientVO_p.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex, "p_visittype", strVisitType,3);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
		if(strVisitType.equals("3")){
			daoObj.setProcInValue(nProcIndex, "p_regcatcode", "13",5);
		}
		else if(strVisitType.equals("1")){
			daoObj.setProcInValue(nProcIndex, "p_regcatcode", "11",5);
		}
		else if(strVisitType.equals("1")){
			daoObj.setProcInValue(nProcIndex, "p_regcatcode", "12",5);
		}
		else 
			daoObj.setProcInValue(nProcIndex, "p_regcatcode", "11",5);
		daoObj.setProcOutValue(nProcIndex, "err", 1,6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}


		try {
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Patient Details Not Found");
			} else {
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(objPatientVO_p, rs);
				HelperMethods.populate(_addressVO, objPatientVO_p);
				objPatientVO_p.setPatAddress(_addressVO);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return objPatientVO_p;
	}
}
