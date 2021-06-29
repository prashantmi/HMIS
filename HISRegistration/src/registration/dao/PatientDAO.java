package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.AddressVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;

/**
 * PatientDAO is a class which describes all the methods required for database interaction for HRGT_PATIENT_DTL table, for example, insert, update, select
 * and delete. PatientDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class PatientDAO extends RegistrationDAO {

	Logger log;
	/**
	 * Creates a new PatientDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public PatientDAO(TransactionContext _transactionContext) 
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}
	

	/**
	 * Retrieves the details of a patient on the basis of CR No. Calculates the
	 * age of the patient as on today.
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public PatientVO retrieveByCrNo(PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_DETAIL_WITH_CRNO(?,?,?,?,?,?)}";//changed fr patient vist by deepika
		int nProcIndex = 0;
		String strErr="",strMode = "0";
		AddressVO _addressVO= new AddressVO();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1"))
			strMode="1";

		daoObj.setProcInValue(nProcIndex, "p_mode", strMode,1);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_crno", objPatientVO_p.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
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
			e.printStackTrace();
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
	/**
	 * Retrieves the the CR No of all patients who have been registered with in last 10 minutes. Used for modification at
	 * Registration Desk.
	 * 
	 * @param objUserVO_p Provides User details.
	 * @return Array of PatientVO containing all the CR No entered with in last 10 minutes.
	 */
	public PatientVO[] retrieveCrNoForModification(UserVO objUserVO_p, String episodeVisitType)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_DTL(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		if(episodeVisitType.equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC) || episodeVisitType.equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC))
			daoObj.setProcInValue(nProcIndex, "strMode_p", "2",1);
		else
			daoObj.setProcInValue(nProcIndex, "strMode_p", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", "",3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getSeatId(),5);
		daoObj.setProcInValue(nProcIndex, "p_super_hospital_code", Config.SUPER_HOSPITAL_CODE,6);
		daoObj.setProcInValue(nProcIndex, "p_modification_time", "1000000",7);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_type", episodeVisitType,8);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_isunknown", RegistrationConfig.PATIENT_ISUNKNOWN_FALSE,9);
		daoObj.setProcOutValue(nProcIndex, "err", 1,10);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		PatientVO[] objPatientVO_p={};
		ValueObject[] vo = {};
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Registered With In Configured Time");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				objPatientVO_p = new PatientVO[vo.length];
				for (int i = 0; i < vo.length; i++)
					objPatientVO_p[i] = (PatientVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveCrNoForModification:HelperMethods :: " + e);
		}
		return objPatientVO_p;
	}
	
	

	/**
	 * Update the patient detail without updating the age and dob of the patient.
	 * @param objPatientVO_p
	 * @param objUserVO_p
	 * @return
	 */
	public int updateWithoutAge(HisDAO daoObj, PatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		int x = 1;
		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			strProcName2 = "{call pkg_reg_dml.dml_hrgt_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?::timestamp,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(objPatientVO_p);
			
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex2, "p_isActialDOB",objPatientVO_p.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", objPatientVO_p.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", objPatientVO_p.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB",objPatientVO_p.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", objPatientVO_p.getPatCrNo()==""?"0": objPatientVO_p.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", objPatientVO_p.getPrevCrNo()==""?"0": objPatientVO_p.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", objPatientVO_p.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", objPatientVO_p.getIsActualDob()==""?"0": objPatientVO_p.getIsActualDob(),9);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", objPatientVO_p.getPatFirstName(),10);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", objPatientVO_p.getPatMiddleName(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", objPatientVO_p.getPatLastName(),12);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", objPatientVO_p.getPatGenderCode()==""?"0": objPatientVO_p.getPatGenderCode(),13);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", objPatientVO_p.getPatMaritalStatusCode()==""?"0": objPatientVO_p.getPatMaritalStatusCode(),14);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", objPatientVO_p.getPatStatusCode()==""?"0": objPatientVO_p.getPatStatusCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", objPatientVO_p.getPatIdMark1(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", objPatientVO_p.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", objPatientVO_p.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", objPatientVO_p.getPatRegCatCode()==""?"0": objPatientVO_p.getPatRegCatCode(),19);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", objPatientVO_p.getPatPrimaryCatCode()==""?"0": objPatientVO_p.getPatPrimaryCatCode(),20);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date","",21);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",22);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", objPatientVO_p.getSeatId()==""?"0": objPatientVO_p.getSeatId(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", objPatientVO_p.getIsMLC()==""?"0": objPatientVO_p.getIsMLC(),24);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", objPatientVO_p.getIsValid()==""?"0": objPatientVO_p.getIsValid(),25);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", objPatientVO_p.getIsUnknown()==""?"0": objPatientVO_p.getIsUnknown(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", objPatientVO_p.getPatMonthlyIncome()==""?"0": objPatientVO_p.getPatMonthlyIncome(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", objPatientVO_p.getPatIsUrban()==""?"0": objPatientVO_p.getPatIsUrban(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", objPatientVO_p.getIsFree()==""?"0": objPatientVO_p.getIsFree(),29);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", objPatientVO_p.getDesigSemester()==""?"0": objPatientVO_p.getDesigSemester(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", objPatientVO_p.getDeptStaffStudent()==""?"0": objPatientVO_p.getDeptStaffStudent(),31);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", objUserVO_p.getIpAddress(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", objPatientVO_p.getPatAddress().getPatAddContactNo(),33);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", objPatientVO_p.getPatIdNo(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", objPatientVO_p.getIsReferred()==""?"0": objPatientVO_p.getIsReferred(),35);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", objPatientVO_p.getPatReligionCode()==""?"0": objPatientVO_p.getPatReligionCode(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", objPatientVO_p.getPatAge(),37);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", objPatientVO_p.getPatNationalityCode()==""?"0": objPatientVO_p.getPatNationalityCode(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", objPatientVO_p.getIsImageUploaded()==""?"0": objPatientVO_p.getIsImageUploaded(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", objPatientVO_p.getPatHusbandName(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", objPatientVO_p.getPatNationalId(),41);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", objPatientVO_p.getPatBloodGroup()==""?"0": objPatientVO_p.getPatBloodGroup(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", objPatientVO_p.getIsNewBorn()==""?"0": objPatientVO_p.getIsNewBorn(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", objPatientVO_p.getPatMotherCrNo()==""?"0": objPatientVO_p.getPatMotherCrNo(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",objPatientVO_p.getPatOccupation(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",objPatientVO_p.getPatFatherOccupation(),46);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",objPatientVO_p.getPatHusbandOccupation(),47);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",objPatientVO_p.getPatNickName(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",objPatientVO_p.getPatCardNo(),49);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",objUserVO_p.getHospitalCode()==""?"0": objUserVO_p.getHospitalCode(),50);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", objPatientVO_p.getPatIdMark2(),51);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", objPatientVO_p.getIsBroughtDead()==""?"0": objPatientVO_p.getIsBroughtDead(),52);
			daoObj.setProcInValue(nProcIndex2, "hdsnum_disaster_id", objPatientVO_p.getDisasterId()==""?"0": objPatientVO_p.getDisasterId(),53);
			daoObj.setProcInValue(nProcIndex2, "num_caste_id", objPatientVO_p.getPatCasteCode()==""?"0": objPatientVO_p.getPatCasteCode(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno", objPatientVO_p.getVerificationDocumentId(),55);
			daoObj.setProcInValue(nProcIndex2, "gnum_pat_edustatus_code", "0",56);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_birth_place", objPatientVO_p.getPatBirthPlace().equals("")?" ":objPatientVO_p.getPatBirthPlace(),57);
			daoObj.setProcInValue(nProcIndex2, "hgnum_id_doc_code", objPatientVO_p.getPatDocType().equalsIgnoreCase("-1")?"-1":objPatientVO_p.getPatDocType().substring(0, 3),58);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_exp_date", objPatientVO_p.getExpiryDate(),59);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,60);
			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return x;				
	}

	public String generateCrNumber(UserVO objUserVO_p)
	{
		String strCrNo=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			daoObj = new HisDAO("Registration","PatientDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_generatecrno(?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, objUserVO_p.getHospitalCode());
			if(objUserVO_p.getHospitalCode().length()==5)
				daoObj.setFuncInValue(funcIndex, 3, Config.CR_NO_FORMAT_SIZE_FIFTEEN);
			else 
				daoObj.setFuncInValue(funcIndex, 3, Config.CR_NO_FORMAT_SIZE);
			daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strCrNo = daoObj.getFuncNumeric(funcIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDAO.generateCrNumber()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return strCrNo;
	}
	
	public PatientVO getDOBAndAge(PatientVO objPatientVO_p)
	{
		Connection conn = super.getTransactionContext().getConnection();
		//String strCrNo=new String();
		try{
		if (objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE)) {
			Procedure proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_DOB);
			proc.addInParameter(1, Types.VARCHAR, objPatientVO_p.getPatAge());
			proc.addInParameter(2, Types.VARCHAR, objPatientVO_p.getPatAgeUnit());
			

			proc.setReturnType(Types.DATE);
			java.sql.Date DOB = (java.sql.Date) proc.execute(conn);
			SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
			sf.applyPattern("dd/MMM/yyyy");
			String date = sf.format(DOB);
			objPatientVO_p.setPatDOB(date);
			objPatientVO_p.setPatAge(objPatientVO_p.getPatAge()+" "+objPatientVO_p.getPatAgeUnit());
		} 
		else {
			Procedure proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_PATIENT_AGE_BY_DOB);
			String systemDate=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(objPatientVO_p.getSystemDate(),""), "dd-MMM-yyyy");
			proc.addInParameter(1, Types.VARCHAR, objPatientVO_p.getPatDOB());
			proc.addInParameter(2, Types.VARCHAR, systemDate);
			proc.setReturnType(Types.VARCHAR);
			String age = (String) proc.execute(conn);
			objPatientVO_p.setPatAge(age);
			
		}                                  
		
	} catch (Exception e) {
		throw new HisDataAccessException("PatientDAO:getDOBAndAge()::=call GetDob(?)" + e);
	}
	return objPatientVO_p;
	}
	
	
	public String getRegExpiry(UserVO objUserVO_p, RenewalConfigVO objRenewalConfigVO_p, String strDeptUnitCode_p)
	{
		String strExpiry=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			HelperMethods.setNullToEmpty(objRenewalConfigVO_p);
			System.out.println("PatientDAO :: getRegExpiry()");
			daoObj = new HisDAO("Registration","EpisodeDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_calc_reg_expiry(?,?,?,?,?, ?,?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, objRenewalConfigVO_p.getStrRenewalType());
			daoObj.setFuncInValue(funcIndex, 4, objRenewalConfigVO_p.getStrRenewalMode());
			daoObj.setFuncInValue(funcIndex, 5, objRenewalConfigVO_p.getStrNoOfDays().equals("")?"0":objRenewalConfigVO_p.getStrNoOfDays());
			daoObj.setFuncInValue(funcIndex, 6, objRenewalConfigVO_p.getStrIsMultipleMonth());
			daoObj.setFuncInValue(funcIndex, 7, objRenewalConfigVO_p.getStrMonth1());
			daoObj.setFuncInValue(funcIndex, 8, objRenewalConfigVO_p.getStrMonth2());
			daoObj.setFuncInValue(funcIndex, 9, objRenewalConfigVO_p.getStrMonth3());
			daoObj.setFuncInValue(funcIndex, 10, objUserVO_p.getHospitalCode());
			daoObj.setFuncInValue(funcIndex, 11, strDeptUnitCode_p);
			daoObj.setFuncOutValue(funcIndex,1);
			
			daoObj.executeFunction(funcIndex);
			strExpiry = daoObj.getFuncString(funcIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EpisodeDAO.getRegExpiry()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return strExpiry;
	}
	/*  ## 		Modification Log							
		##		Modify Date				:29thJan'15 
		##		Reason	(CR/PRS)		:Expiry date calculation on basis of hours in emg reg
		##		Modify By				:Sheeldarshi */
	
	public String getRegExpiryHoursBased(UserVO objUserVO_p, RenewalConfigVO objRenewalConfigVO_p, String strDeptUnitCode_p,RegistrationConfigVO objRegistrationConfigVO)
	{
		String strExpiry=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			HelperMethods.setNullToEmpty(objRenewalConfigVO_p);
			System.out.println("PatientDAO :: getRegExpiry()");
			daoObj = new HisDAO("Registration","EpisodeDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_calc_reg_expiry(?,?,?,?,?, ?,?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, objRenewalConfigVO_p.getStrRenewalType());
			if(objRegistrationConfigVO.getIsHourBased()!="" || objRegistrationConfigVO.getIsHourBased()!=null)
			{
			if(objRegistrationConfigVO.getIsHourBased().equals("1"))
				daoObj.setFuncInValue(funcIndex, 4, "3");
			else
				daoObj.setFuncInValue(funcIndex, 4, objRenewalConfigVO_p.getStrRenewalMode());	
			}
			else
			{
				daoObj.setFuncInValue(funcIndex, 4, objRenewalConfigVO_p.getStrRenewalMode());
			}
			
			daoObj.setFuncInValue(funcIndex, 5, objRenewalConfigVO_p.getStrNoOfDays().equals("")?"0":objRenewalConfigVO_p.getStrNoOfDays());
			daoObj.setFuncInValue(funcIndex, 6, objRenewalConfigVO_p.getStrIsMultipleMonth());
			daoObj.setFuncInValue(funcIndex, 7, objRenewalConfigVO_p.getStrMonth1());
			daoObj.setFuncInValue(funcIndex, 8, objRenewalConfigVO_p.getStrMonth2());
			daoObj.setFuncInValue(funcIndex, 9, objRenewalConfigVO_p.getStrMonth3());
			daoObj.setFuncInValue(funcIndex, 10, objUserVO_p.getHospitalCode());
			daoObj.setFuncInValue(funcIndex, 11, strDeptUnitCode_p);
			daoObj.setFuncOutValue(funcIndex,1);
			
			daoObj.executeFunction(funcIndex);
			strExpiry = daoObj.getFuncString(funcIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EpisodeDAO.getRegExpiry()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return strExpiry;
	}
	// CRUD function
	/**
	 * 
	 * Creates a new patient entry in DB for a patient. 
	 * @param daoObj
	 * @param objPatientVO_p	Provides patient details to be entered.
	 * @param objUserVO_p		Provides User details.
	 * @param strMode_p , 		strMode_p =1 -->> To insert into DataBase
	 * 							strMode_p =4 -->> To Update Patient Status and Expiry Date
	 * 							strMode_p =6 -->> To Update Patient Status Only
	 * @return	PatientVO with values stored in DB.
	 */
	public PatientVO savePatientDtl(HisDAO daoObj,PatientVO objPatientVO_p, UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			//strProcName2 = "{call pkg_reg_dml.dml_hrgt_patient_dtl(?,?,?,?,?,   ?::numeric,?::numeric,?,?,?,   ?,?::numeric,?,?::numeric,?::numeric,   ?,?,?,?::numeric,?::numeric,   ?::numeric,?,?::numeric,?::numeric,?,   ?::numeric,?::numeric,?::numeric,?::numeric,?,   ?,?::numeric,?,?::numeric,?::numeric,   ?,?::numeric,?,?::numeric,?,   ?::numeric,?,?::numeric,?,?::numeric,   ?,?::numeric,?,?::numeric,?::numeric,   ?,?::numeric,?,?,?::numeric,   ?,?,?,?::numeric,?,   ?,?,?::numeric,?::numeric,?::numeric,   ?::numeric,?,?,?,?::timestamp,   ?::timestamp,?::timestamp,?,?,?,?,?,?, ?)}";
			/*strProcName2 = "{call pkg_reg_dml.dml_hrgt_patient_dtl_old(?,?,?,?,?::character varying,   ?::numeric,?::numeric,?::character varying,?::character varying,?::character varying,   ?::character varying,?::numeric,?::character varying,?::numeric,?::numeric,   ?::character varying,?::character varying,?::character varying,?::numeric,?::numeric,   ?::numeric,?::character varying,?::numeric,?::numeric,?::character varying,   ?::numeric,?::numeric,?::numeric,?::numeric,?::character varying,   ?::character varying,?::numeric,?::character varying,?::numeric,?::numeric,   ?::character varying,?::numeric,?::character varying,?::numeric,?::character varying,   ?::numeric,?::character varying,?::numeric,?::character varying,?::numeric,   ?::character varying,?::numeric,?::character varying,?::numeric,?::numeric,   ?::character varying,?::numeric,?::character varying,?::character varying,?::numeric,   ?::character varying,?::character varying,?::character varying,?::numeric,?::character varying,   ?::character varying,?::character varying,?::numeric,?::numeric,?::numeric,   ?::numeric,?::character varying,?::character varying,?::character varying,?::timestamp,   ?::timestamp,?::timestamp,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?,?,?, 	?,?,?::character varying)}";*/
			strProcName2 = "{call pkg_reg_dml.dml_hrgt_patient_dtl(?,?,?,?,?::character varying,   ?::numeric,?::numeric,?::character varying,?::character varying,?::character varying,   ?::character varying,?::numeric,?::character varying,?::numeric,?::numeric,   ?::character varying,?::character varying,?::character varying,?::numeric,?::numeric,   ?::numeric,?::character varying,?::numeric,?::numeric,?::character varying,   ?::numeric,?::numeric,?::numeric,?::numeric,?::character varying,   ?::character varying,?::numeric,?::character varying,?::numeric,?::numeric,   ?::character varying,?::numeric,?::character varying,?::numeric,?::character varying,   ?::numeric,?::character varying,?::numeric,?::character varying,?::numeric,   ?::character varying,?::numeric,?::character varying,?::numeric,?::numeric,   ?::character varying,?::numeric,?::character varying,?::character varying,?::numeric,   ?::character varying,?::character varying,?::character varying,?::numeric,?::character varying,   ?::character varying,?::character varying,?::numeric,?::numeric,?::numeric,   ?::numeric,?::character varying,?::character varying,?::character varying,?::timestamp,   ?::timestamp,?::timestamp,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?,?,?, 	?,?,?::character varying)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(objPatientVO_p);
			HelperMethods.setNullToEmpty(objPatientVO_p.getPatAddress());
			
			
			System.out.println("----------------------------------------------------");
			System.out.println("PatientDAO :: savePatientDtl()");
			System.out.println("p_modeVal :"+ strMode_p);
			System.out.println("p_hrgbl_is_actual_dob :"+objPatientVO_p.getIsActualDob());
			System.out.println("p_age :"+ objPatientVO_p.getPatAge());
			System.out.println("p_ageUnit :"+ objPatientVO_p.getPatAgeUnit());
			System.out.println("p_DOB :"+ objPatientVO_p.getPatDOB());
			System.out.println("p_hrgnum_puk :"+ objPatientVO_p.getPatCrNo());
			System.out.println("p_hrgstr_prev_puk :"+ objPatientVO_p.getPrevCrNo());
			System.out.println("p_hrgdt_dob :"+ objPatientVO_p.getPatDOB()+" p_hrgstr_fname :"+ objPatientVO_p.getPatFirstName());
			System.out.println("p_hrgstr_mname :"+ objPatientVO_p.getPatMiddleName());
			System.out.println("p_hrgstr_lname :"+ objPatientVO_p.getPatLastName());
			System.out.println("p_gnum_document_code :"+"");
			System.out.println("p_gstr_gender_code :"+ objPatientVO_p.getPatGenderCode()); 
			System.out.println("p_gnum_marital_status_code :"+ objPatientVO_p.getPatMaritalStatusCode()); 
			System.out.println("p_hgnum_pat_status_code :"+ objPatientVO_p.getPatStatusCode()); 
			System.out.println("p_hrgstr_idmark1 :"+ objPatientVO_p.getPatIdMark1()); 
			System.out.println("p_hrgstr_father_name :"+ objPatientVO_p.getPatGuardianName());
			System.out.println("p_hrgstr_momname :"+ objPatientVO_p.getPatMotherName());
			System.out.println("p_hrgnum_reg_cat_code :"+ objPatientVO_p.getPatRegCatCode()); 
			System.out.println("p_hgnum_patient_cat_code :"+ objPatientVO_p.getPatPrimaryCatCode());
			System.out.println("p_gnum_appellation_code :"+""); 
			System.out.println("p_gdt_entry_date :"+""); 
			System.out.println("p_gnum_suffix_code :"+""); 
			//System.out.println("p_gnum_seat_id :"+ objPatientVO_p.getSeatId()); 
			System.out.println("p_gnum_seat_id :"+ objUserVO_p.getSeatId()); 
			System.out.println("p_hrgstr_name_type :"+"L"); 
			System.out.println("p_hrgnum_is_mlc :"+ (objPatientVO_p.getIsMLC().equals("")?"0":objPatientVO_p.getIsMLC())); 
			System.out.println("p_gnum_isvalid :"+ objPatientVO_p.getIsValid()); 
			System.out.println("p_hrgnum_isunknown :"+ (objPatientVO_p.getIsUnknown().equals("")?"0":objPatientVO_p.getIsUnknown())); 
			System.out.println("p_hrgnum_month_income :"+objPatientVO_p.getPatMonthlyIncome()); 
			System.out.println("p_hrgstr_ip_add :"+ (objUserVO_p.getIpAddress()==null || objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress())); 
			System.out.println("p_hrgstr_birth_place :"+""); 
			System.out.println("p_hrgnum_special_vulnerability :"+""); 
			System.out.println("p_hrgnum_id_no :"+ objPatientVO_p.getPatIdNo()); 
			System.out.println("p_hrgnum_is_ref :"+ objPatientVO_p.getIsReferred()); 
			System.out.println("p_gnum_religion_code :"+ objPatientVO_p.getPatReligionCode()); 
			System.out.println("p_hrgstr_age :"+ objPatientVO_p.getPatAge()); 
			System.out.println("p_hrgnum_is_imageuploaded :"+ (objPatientVO_p.getIsImageUploaded().equals("")?"0":objPatientVO_p.getIsImageUploaded())); 
			System.out.println("p_hrgstr_spousename :"+ objPatientVO_p.getPatHusbandName()); 
			System.out.println("p_gnum_occupation_code :"+objPatientVO_p.getPatOccupation()); 
			System.out.println("p_hrgstr_national_id :"+ objPatientVO_p.getPatNationalId()); 
			System.out.println("p_hbnum_bldgrp_code :"+ objPatientVO_p.getPatBloodGroup()); 
			System.out.println("p_hrgnum_isnewborn :"+ objPatientVO_p.getIsNewBorn()); 
			System.out.println("p_hrgnum_mother_puk :"+ objPatientVO_p.getPatMotherCrNo()); 
			System.out.println("p_hrgstr_nickname :"+objPatientVO_p.getPatNickName()); 
			System.out.println("p_hrgnum_is_dead :"+"1"); 	// 1- For Alive
			System.out.println("p_hrgstr_card_no :"+objPatientVO_p.getPatCardNo()); 
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode()); 
			System.out.println("p_hrgstr_idmark2 :"+ objPatientVO_p.getPatIdMark2()); 
			System.out.println("p_hrgnum_is_broughtdead :"+ (objPatientVO_p.getIsBroughtDead().equals("")?"0":objPatientVO_p.getIsBroughtDead())); 
			System.out.println("p_hrgnum_verification_status :"+"2"); 
			System.out.println("p_gstr_caste_code :"+ objPatientVO_p.getPatCasteCode()); 
			System.out.println("p_gnum_nationality :"+ objPatientVO_p.getPatNationalityCode()); 
			System.out.println("p_hrgstr_address_line1 :"+objPatientVO_p.getPatAddHNo());
			System.out.println("p_hrgstr_sub_locality1 :"+objPatientVO_p.getPatAddStreet()); 
			System.out.println("p_gnum_education_code :"+ (objPatientVO_p.getPatEducationCode().equals("")?"0":objPatientVO_p.getPatEducationCode())); 
			System.out.println("p_hrgstr_sub_locality2 :"+objPatientVO_p.getPatAddLandMarks()); 
			System.out.println("p_hrgstr_city_location :"+objPatientVO_p.getPatAddCityLoc()); 
			System.out.println("p_hrgstr_city :"+objPatientVO_p.getPatAddCity()); 
			System.out.println("p_hrgnum_pincode :"+objPatientVO_p.getPatAddPIN()); 
			System.out.println("p_hrgstr_district :"+objPatientVO_p.getPatAddDistrict()); 
			System.out.println("p_hrgstr_state_name :"+objPatientVO_p.getPatAddState()); 
			System.out.println("p_gstr_country_name :"+objPatientVO_p.getPatAddCountry()); 
			System.out.println("p_hrgnum_is_urban :"+ objPatientVO_p.getPatIsUrban()); 
			System.out.println("p_hrgnum_is_local :"+objPatientVO_p.getPatIsLocal()); 
			System.out.println("p_hrgnum_ismerged :"+"0"); 
			System.out.println("p_hrgnum_phone_owner :"+objPatientVO_p.getPatAddPhoneOwner()); 
			System.out.println("p_hrgstr_mobile_no :"+objPatientVO_p.getPatAddMobileNo()); 
			System.out.println("p_hrgstr_phone_no :"+objPatientVO_p.getPatAddPhoneNo()); 
			System.out.println("p_hrgstr_email_id :"+objPatientVO_p.getPatAddEmailId()); 
			
			System.out.println("p_hrgdt_general_expdate :"+objPatientVO_p.getPatGeneralExpDate());
			System.out.println("p_hrgdt_special_expdate :"+objPatientVO_p.getPatSpecialExpDate());
			System.out.println("p_hrgdt_causal_expdate :"+objPatientVO_p.getPatCasualityExpDate());
			
			System.out.println("p_hrgstr_fname_local :"+ objPatientVO_p.getPatFirstNameInMultiLang());
			System.out.println("p_hrgstr_mname_local :"+ objPatientVO_p.getPatMiddleNameInMultiLang());
			System.out.println("p_hrgstr_lname_local :"+ objPatientVO_p.getPatLastNameInMultiLang());
			
			System.out.println("p_hrgstr_sec_uhid :"+ objPatientVO_p.getPatSecUHID());
			System.out.println("----------------------------------------------------");
			
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgbl_is_actual_dob",objPatientVO_p.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", objPatientVO_p.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", objPatientVO_p.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB", objPatientVO_p.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk", objPatientVO_p.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_prev_puk", objPatientVO_p.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "p_hrgdt_dob", objPatientVO_p.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_fname", objPatientVO_p.getPatFirstName(),9);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_mname", objPatientVO_p.getPatMiddleName(),10);
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_lname", objPatientVO_p.getPatLastName(),11);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_document_code",objPatientVO_p.getPatDocType(),12);
			daoObj.setProcInValue(nProcIndex2, "p_gstr_gender_code", objPatientVO_p.getPatGenderCode(),13); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_marital_status_code", objPatientVO_p.getPatMaritalStatusCode(),14); 
			daoObj.setProcInValue(nProcIndex2, "p_hgnum_pat_status_code", objPatientVO_p.getPatStatusCode(),15); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_idmark1", objPatientVO_p.getPatIdMark1(),16); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_father_name", objPatientVO_p.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_momname", objPatientVO_p.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_reg_cat_code", objPatientVO_p.getPatRegCatCode(),19); 
			daoObj.setProcInValue(nProcIndex2, "p_hgnum_patient_cat_code", objPatientVO_p.getPatPrimaryCatCode(),20);
			
			daoObj.setProcInValue(nProcIndex2, "p_gnum_appellation_code",objPatientVO_p.getPatAppellationCode(),21); 
			daoObj.setProcInValue(nProcIndex2, "p_gdt_entry_date","",22); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_suffix_code",objPatientVO_p.getSuffixCode(),23); 
			//daoObj.setProcInValue(nProcIndex2, "p_gnum_seat_id", objPatientVO_p.getSeatId(),24); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_seat_id", objUserVO_p.getSeatId(),24);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_name_type",objPatientVO_p.getPatNameType(),25); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_mlc", objPatientVO_p.getIsMLC().equals("")?"0":objPatientVO_p.getIsMLC(),26); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_isvalid", objPatientVO_p.getIsValid(),27); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isunknown", (objPatientVO_p.getIsUnknown().equals("")?"0":objPatientVO_p.getIsUnknown()),28); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_month_income",objPatientVO_p.getPatMonthlyIncome(),29); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_ip_add", (objUserVO_p.getIpAddress()==null|| objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()),30); 
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_birth_place",objPatientVO_p.getPatBirthPlace(),31); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_special_vulnerability",objPatientVO_p.getPatSplVulnerability(),32); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_id_no", objPatientVO_p.getPatIdNo(),33); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_ref", objPatientVO_p.getIsReferred(),34); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_religion_code", objPatientVO_p.getPatReligionCode(),35); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_age", objPatientVO_p.getPatAge(),36); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_imageuploaded", (objPatientVO_p.getIsImageUploaded().equals("")?"0":objPatientVO_p.getIsImageUploaded()),37); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_spousename", objPatientVO_p.getPatHusbandName(),38); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_occupation_code",objPatientVO_p.getPatOccupation(),39); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_national_id", objPatientVO_p.getPatNationalId(),40);
			
			daoObj.setProcInValue(nProcIndex2, "p_hbnum_bldgrp_code", objPatientVO_p.getPatBloodGroup(),41); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isnewborn", objPatientVO_p.getIsNewBorn(),42); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_mother_puk", objPatientVO_p.getPatMotherCrNo(),43); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_nickname",objPatientVO_p.getPatNickName(),44); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_dead",objPatientVO_p.getPatIsDead(),45); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_card_no",objPatientVO_p.getPatCardNo(),46); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),47); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_idmark2", objPatientVO_p.getPatIdMark2(),48); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_broughtdead", objPatientVO_p.getIsBroughtDead().equals("")?"0":objPatientVO_p.getIsBroughtDead(),49); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_verification_status",objPatientVO_p.getPatVerificationStatus(),50); 
			
			daoObj.setProcInValue(nProcIndex2, "p_gstr_caste_code", objPatientVO_p.getPatCasteCode(),51); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_nationality", objPatientVO_p.getPatNationalityCode(),52); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_address_line1",objPatientVO_p.getPatAddHNo(),53);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_sub_locality1",objPatientVO_p.getPatAddStreet(),54); 
			daoObj.setProcInValue(nProcIndex2, "p_gnum_education_code", objPatientVO_p.getPatEducationCode(),55); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_sub_locality2",objPatientVO_p.getPatAddLandMarks(),56); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_city_location",objPatientVO_p.getPatAddCityLoc(),57); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_city",objPatientVO_p.getPatAddCity(),58); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_pincode",objPatientVO_p.getPatAddPIN(),59); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_district",objPatientVO_p.getPatAddDistrict(),60);
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_state_name",objPatientVO_p.getPatAddState(),61);
			daoObj.setProcInValue(nProcIndex2, "p_gstr_country_name",objPatientVO_p.getPatAddCountry(),62); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_urban", objPatientVO_p.getPatIsUrban(),63); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_local",objPatientVO_p.getPatIsLocal(),64); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_ismerged",objPatientVO_p.getPatIsMerged().equals("")?"0":objPatientVO_p.getPatIsMerged(),65); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_phone_owner",objPatientVO_p.getPatAddPhoneOwner(),66); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_mobile_no",objPatientVO_p.getPatAddMobileNo(),67); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_phone_no",objPatientVO_p.getPatAddPhoneNo(),68); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_email_id",objPatientVO_p.getPatAddEmailId(),69); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgdt_general_expdate",objPatientVO_p.getPatGeneralExpDate(),70);
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgdt_special_expdate",objPatientVO_p.getPatSpecialExpDate(),71);
			daoObj.setProcInValue(nProcIndex2, "p_hrgdt_casuality_expdate",objPatientVO_p.getPatCasualityExpDate(),72);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_state_code ",objPatientVO_p.getPatAddStateCode(),73);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_district_code",objPatientVO_p.getPatAddDistrictCode(),74);
			daoObj.setProcInValue(nProcIndex2, "p_gstr_country_code",objPatientVO_p.getPatAddCountryCode(),75);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_fname_local", objPatientVO_p.getPatFirstNameInMultiLang(),76);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_mname_local", objPatientVO_p.getPatMiddleNameInMultiLang(),77);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_lname_local", objPatientVO_p.getPatLastNameInMultiLang(),78);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_sec_uhid", objPatientVO_p.getPatSecUHID(),79);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_cat_approved_by", objPatientVO_p.getApprovedBy(),80);
			
			daoObj.setProcInValue(nProcIndex2,"p_hrgnum_consentstatus_aadhaar_auth", objPatientVO_p.getIsAadharConsentGiven(), 81);
			daoObj.setProcInValue(nProcIndex2,"p_hrgstr_emg_cntc",  objPatientVO_p.getPatEmgCntNo(), 82);
			daoObj.setProcInValue(nProcIndex2,"p_hgstr_broughtdead_declaredby",  objPatientVO_p.getBroughtByConsultant(), 83);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,84);
			
			
			
			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return objPatientVO_p;				
	}
	
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByCRNoForSearchTile(PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_SEARCH_PATIENT_DTL(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		AddressVO _addressVO= new AddressVO();
		List lstPatientJsonObjString=new ArrayList();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		HelperMethods.setNullToEmpty(objPatientVO_p);
		daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
		//daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objPatientVO_p.getPatRefGnctdHospitalCode(),2);
		//daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "p_crno", objPatientVO_p.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_mobileno", objPatientVO_p.getPatAddMobileNo(),4);
		daoObj.setProcInValue(nProcIndex, "p_email", objPatientVO_p.getPatAddEmailId(),5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isglobal", objPatientVO_p.getIsGlobal(),6);
		
		System.out.println("---objPatientVO_p.getPatRefGnctdHospitalCode()------"+objPatientVO_p.getPatRefGnctdHospitalCode()+"-----------");
		System.out.println("---objPatientVO_p.getPatCrNo()------"+objPatientVO_p.getPatCrNo()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddMobileNo()------"+objPatientVO_p.getPatAddMobileNo()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddEmailId()------"+objPatientVO_p.getPatAddEmailId()+"-----------");
		System.out.println("---objPatientVO_p.getIsGlobal()------"+objPatientVO_p.getIsGlobal()+"-----------");

		
		daoObj.setProcOutValue(nProcIndex, "err", 1,7);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}


		try {
			if (strErr.equals("")) 
			{
				while(rs.next()){
					lstPatientJsonObjString.add(rs.getString(1));
					System.out.println("webRs.getString(1) :" + rs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:searchPatientDetailFromSearchTile:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return lstPatientJsonObjString;
	}
	
	

	
	/**
	 * Retrieves the list of a patient category except patient primary category for Primary Patient Category Combo
	 * @param patCat	Provides Patient Primary Category Code.
	 * @param _userVO	Provides User details.
	 * @return List containing patient category 
	 */
	public List getPrimaryCatListExceptPatientCat(String patCatCode,UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName ="{call Pkg_Reg_View.proc_gblt_patient_cat_combo_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_catcode", patCatCode,2);
			daoObj.setProcInValue(nProcIndex, "p_gnum_cattype", RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_moduleid",Config.MODULE_ID_REGISTRATION,5);
			daoObj.setProcInValue(nProcIndex, "p_seatid",_userVO.getUserSeatId(),6);

			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				throw new HisRecordNotFoundException("No Category Found");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
		
	}

	///////////////////////// Vellan ///////////////////////
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByMobileNoForSearchTile(PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_SEARCH_PATIENT_DTL(?,?,?,?,?,?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		AddressVO _addressVO= new AddressVO();
		List lstPatientJsonObjString=new ArrayList();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		HelperMethods.setNullToEmpty(objPatientVO_p);
		daoObj.setProcInValue(nProcIndex, "p_modeval", "2",1);
		//daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objPatientVO_p.getPatRefGnctdHospitalCode(),2);
		//daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "p_crno", objPatientVO_p.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_mobileno", objPatientVO_p.getPatAddMobileNo(),4);
		daoObj.setProcInValue(nProcIndex, "p_email", objPatientVO_p.getPatAddEmailId(),5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isglobal", objPatientVO_p.getIsGlobal(),6);
		
		System.out.println("---objPatientVO_p.getPatRefGnctdHospitalCode()------"+objPatientVO_p.getPatRefGnctdHospitalCode()+"-----------");
		System.out.println("---objPatientVO_p.getPatCrNo()------"+objPatientVO_p.getPatCrNo()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddMobileNo()------"+objPatientVO_p.getPatAddMobileNo()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddEmailId()------"+objPatientVO_p.getPatAddEmailId()+"-----------");
		System.out.println("---objPatientVO_p.getIsGlobal()------"+objPatientVO_p.getIsGlobal()+"-----------");

		
		daoObj.setProcOutValue(nProcIndex, "err", 1,7);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}


		try {
			if (strErr.equals("")) 
			{
				while(rs.next()){
					lstPatientJsonObjString.add(rs.getString(1));
					System.out.println("webRs.getString(1) :" + rs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:searchPatientDetailFromSearchTile:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return lstPatientJsonObjString;
	}
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByEmailForSearchTile(PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_SEARCH_PATIENT_DTL(?,?,?,?,?,?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		AddressVO _addressVO= new AddressVO();
		List lstPatientJsonObjString=new ArrayList();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		HelperMethods.setNullToEmpty(objPatientVO_p);
		daoObj.setProcInValue(nProcIndex, "p_modeval", "3",1);
		//daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objPatientVO_p.getPatRefGnctdHospitalCode(),2);
		//daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "p_crno", objPatientVO_p.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_mobileno", objPatientVO_p.getPatAddMobileNo(),4);
		daoObj.setProcInValue(nProcIndex, "p_email", objPatientVO_p.getPatAddEmailId(),5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isglobal", objPatientVO_p.getIsGlobal(),6);
		
		System.out.println("---objPatientVO_p.getPatRefGnctdHospitalCode()------"+objPatientVO_p.getPatRefGnctdHospitalCode()+"-----------");
		System.out.println("---objPatientVO_p.getPatCrNo()------"+objPatientVO_p.getPatCrNo()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddMobileNo()------"+objPatientVO_p.getPatAddMobileNo()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddEmailId()------"+objPatientVO_p.getPatAddEmailId()+"-----------");
		System.out.println("---objPatientVO_p.getIsGlobal()------"+objPatientVO_p.getIsGlobal()+"-----------");

		daoObj.setProcOutValue(nProcIndex, "err", 1,7);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}


		try {
			if (strErr.equals("")) 
			{
				while(rs.next()){
					lstPatientJsonObjString.add(rs.getString(1));
					System.out.println("webRs.getString(1) :" + rs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:searchPatientDetailFromSearchTile:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return lstPatientJsonObjString;
	}
	
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByDemographicDetailForSearchTile(PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_search_pat_by_demographic_dtl(?,?,?,?,?,?,?::numeric,?::numeric,?,?,?,?,?::numeric,?,?,?,?,?::numeric,?,?,?::numeric,?,?::numeric,?::timestamp without time zone,?::timestamp without time zone,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		AddressVO _addressVO= new AddressVO();
		List lstPatientJsonObjString=new ArrayList();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		HelperMethods.setNullToEmpty(objPatientVO_p);
	/*  ## 		Modification Log							
		##		Modify Date				:18thDec'14 
		##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
		##		Modify By				:Sheeldarshi */
		if(!objPatientVO_p.getIsUnknown().equals("1"))
		{		
		daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
		}
		else{
			daoObj.setProcInValue(nProcIndex, "p_modeval", "2",1);
		}
		//End:Sheeldarshi
		String hosCode=!objPatientVO_p.getPatRefGnctdHospitalCode().equals("")?objPatientVO_p.getPatRefGnctdHospitalCode():objUserVO_p.getHospitalCode();
		daoObj.setProcInValue(nProcIndex, "p_hosp_code",hosCode ,2);
		daoObj.setProcInValue(nProcIndex, "p_fname", objPatientVO_p.getPatFirstName(),3);
		daoObj.setProcInValue(nProcIndex, "p_mname", objPatientVO_p.getPatMiddleName(),4);
		daoObj.setProcInValue(nProcIndex, "p_lname", objPatientVO_p.getPatLastName(),5);
		daoObj.setProcInValue(nProcIndex, "p_gender", (!objPatientVO_p.getPatGenderCode().equals("-1"))?objPatientVO_p.getPatGenderCode():"",6);
		daoObj.setProcInValue(nProcIndex, "p_minage_limit", (!objPatientVO_p.getPatAge().equals("-1")&&objPatientVO_p.getPatAge()!=""&&objPatientVO_p.getPatAge()!=null)?objPatientVO_p.getPatAge().split("-")[0]:"",7);
		daoObj.setProcInValue(nProcIndex, "p_maxage_limit", (!objPatientVO_p.getPatAge().equals("-1")&&objPatientVO_p.getPatAge()!=""&&objPatientVO_p.getPatAge()!=null)?objPatientVO_p.getPatAge().split("-")[1]:"",8);
		daoObj.setProcInValue(nProcIndex, "p_fathername", objPatientVO_p.getPatGuardianName(),9);
		daoObj.setProcInValue(nProcIndex, "p_spousename", objPatientVO_p.getPatHusbandName(),10);
		daoObj.setProcInValue(nProcIndex, "p_mothername", objPatientVO_p.getPatMotherName(),11);
		daoObj.setProcInValue(nProcIndex, "p_countrycode", (!objPatientVO_p.getPatAddCountryCode().equals("-1"))?objPatientVO_p.getPatAddCountryCode():"",12);
		daoObj.setProcInValue(nProcIndex, "p_statecode", (!objPatientVO_p.getPatAddStateCode().equals("-1"))?objPatientVO_p.getPatAddStateCode():"",13);
		daoObj.setProcInValue(nProcIndex, "p_statename", objPatientVO_p.getPatAddStateName(),14);
		daoObj.setProcInValue(nProcIndex, "p_houseno", objPatientVO_p.getPatAddHNo(),15);
		daoObj.setProcInValue(nProcIndex, "p_street", objPatientVO_p.getPatAddStreet(),16);
		daoObj.setProcInValue(nProcIndex, "p_location", objPatientVO_p.getPatAddCityLoc(),17);
		daoObj.setProcInValue(nProcIndex, "p_districtcode", (!objPatientVO_p.getPatAddDistrictCode().equals("-1"))?objPatientVO_p.getPatAddDistrictCode():"",18);
		daoObj.setProcInValue(nProcIndex, "p_districtname", objPatientVO_p.getPatAddDistrict(),19);
		daoObj.setProcInValue(nProcIndex, "p_city", objPatientVO_p.getPatAddCity(),20);
		daoObj.setProcInValue(nProcIndex, "p_pincode", objPatientVO_p.getPatAddPIN(),21);
		daoObj.setProcInValue(nProcIndex, "p_landmark", objPatientVO_p.getPatAddLandMarks(),22);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isglobal", objPatientVO_p.getIsGlobal(),23);
	/*  ## 		Modification Log							
		##		Modify Date				:18thDec'14 
		##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
		##		Modify By				:Sheeldarshi */
		daoObj.setProcInValue(nProcIndex, "p_fromdate", objPatientVO_p.getFromDate(),24);
		daoObj.setProcInValue(nProcIndex, "p_todate", objPatientVO_p.getToDate(),25);
		//End:Sheeldarshi
		System.out.println("---objPatientVO_p.getPatFirstName()------"+objPatientVO_p.getPatFirstName()+"-----------");
		System.out.println("---objPatientVO_p.getPatMiddleName()------"+objPatientVO_p.getPatMiddleName()+"-----------");
		System.out.println("---objPatientVO_p.getPatLastName()------"+objPatientVO_p.getPatLastName()+"-----------");
		System.out.println("---objPatientVO_p.getPatGenderCode()------"+((!objPatientVO_p.getPatGenderCode().equals("-1"))?objPatientVO_p.getPatGenderCode():"")+"-----------");
		System.out.println("---objPatientVO_p.getPatAgeMin()------"+((!objPatientVO_p.getPatAge().equals("-1")&&objPatientVO_p.getPatAge()!=""&&objPatientVO_p.getPatAge()!=null)?objPatientVO_p.getPatAge().split("-")[0]:"")+"-----------");
		System.out.println("---objPatientVO_p.getPatAgeMax()------"+((!objPatientVO_p.getPatAge().equals("-1")&&objPatientVO_p.getPatAge()!=""&&objPatientVO_p.getPatAge()!=null)?objPatientVO_p.getPatAge().split("-")[1]:"")+"-----------");
		System.out.println("---objPatientVO_p.getPatGuardianName()------"+objPatientVO_p.getPatGuardianName()+"-----------");
		System.out.println("---objPatientVO_p.getPatHusbandName()------"+objPatientVO_p.getPatHusbandName()+"-----------");
		System.out.println("---objPatientVO_p.getPatMotherName()------"+objPatientVO_p.getPatMotherName()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddHNo()------"+objPatientVO_p.getPatAddHNo()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddStreet()------"+objPatientVO_p.getPatAddStreet()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddCityLoc()------"+objPatientVO_p.getPatAddCityLoc()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddCity()------"+objPatientVO_p.getPatAddCity()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddPIN()------"+objPatientVO_p.getPatAddPIN()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddLandMarks()------"+objPatientVO_p.getPatAddLandMarks()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddCountryCode()------"+((!objPatientVO_p.getPatAddCountryCode().equals("-1"))?objPatientVO_p.getPatAddCountryCode():"")+"-----------");
		System.out.println("---objPatientVO_p.getPatAddStateCode()------"+((!objPatientVO_p.getPatAddStateCode().equals("-1"))?objPatientVO_p.getPatAddStateCode():"")+"-----------");
		System.out.println("---objPatientVO_p.getPatAddStateName()------"+objPatientVO_p.getPatAddStateName()+"-----------");
		System.out.println("---objPatientVO_p.getPatAddDistrictCode()------"+((!objPatientVO_p.getPatAddDistrictCode().equals("-1"))?objPatientVO_p.getPatAddDistrictCode():"")+"-----------");
		System.out.println("---objPatientVO_p.getPatAddDistrict()------"+objPatientVO_p.getPatAddDistrict()+"-----------");

		daoObj.setProcOutValue(nProcIndex, "err", 1,26);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,27);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}


		try {
			if (strErr.equals("")) 
			{
				while(rs.next()){
					lstPatientJsonObjString.add(rs.getString(1));
					System.out.println("webRs.getString(1) :" + rs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:searchPatientDetailFromSearchTile:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return lstPatientJsonObjString;
	}
	
	/**
	 * Retrieves the List of all patients who has the appointments for Special Clinic Registration in a day
	 * Registration Desk.
	 * 
	 * @param _userVO Provides User details.
	 * @return Array of PatientVO containing all the CR No entered with in a Day.
	 * Added by Singaravelan on 28-Aug-2014.
	 */
	public PatientVO[] getCRNoWithAppointment(UserVO _userVO, String mode)
	{
		//String queryKey = "SELECT.HRGT_PATIENT_DTL.CR_NO1";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HAPT_APPOINTMENT_DTL_ON_SPLREG(?,?::character varying,?,?,?, ?::character varying,?::character varying,?,?,?, ?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_mode",mode,1);		
		daoObj.setProcInValue(nProcIndex, "p_gnum_no", "",2);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_id", RegistrationConfig.PAT_APT_OPD_ID,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_status",RegistrationConfig.PAT_APT_OPD_STATUS_WAITING,5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_search_id", "",6);
		daoObj.setProcInValue(nProcIndex, "p_gnum_search_value", "",7);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _userVO.getSeatId(),9);
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,10);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		PatientVO[] _patientVO={};
		ValueObject[] vo = {};
		try
		{
			if (!rs.next())
			{
				if(mode.equalsIgnoreCase("1"))
					throw new HisRecordNotFoundException("No Appointments Available For Registration");
				else
					throw new HisRecordNotFoundException("No Appointments Available For Visit");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				for (int i = 0; i < vo.length; i++)
					_patientVO[i] = (PatientVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveCrNoForModification:HelperMethods :: " + e);
		}
		return _patientVO;
	}
	
	
	/**
	 * Retrieves the the CR No/Apt No Details who has the appointments for Special Clinic Registration
	 * Registration Desk.
	 * 
	 * @param _userVO Provides User details.
	 * @return PatientVO containing the CR No/Apt No Details entered with in a Day. 
	 * Added by Singaravelan on 28-Aug-2014.
	 */
	public PatientVO getDetailsWithAppointment(PatientVO _patientVO,UserVO _userVO, String _mode)
	{
		//String queryKey = "SELECT.HRGT_PATIENT_DTL.CR_NO1";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HAPT_APPOINTMENT_DTL_ON_SPLREG(?,?::character varying,?,?,?, ?::character varying,?::character varying,?,?,?, ?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		HelperMethods.setNullToEmpty(_patientVO);

		daoObj.setProcInValue(nProcIndex, "p_mode", _mode,1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_no", _mode.equalsIgnoreCase("2")?_patientVO.getPatAptNo():_patientVO.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_id", RegistrationConfig.PAT_APT_OPD_ID,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_status", RegistrationConfig.PAT_APT_OPD_STATUS_WAITING,5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_search_id", _patientVO.getSearchId(),6);
		daoObj.setProcInValue(nProcIndex, "p_gnum_search_value", _patientVO.getSearchValue(),7);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _userVO.getSeatId(),9);
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,10);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
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
				throw new HisRecordNotFoundException("No Patient Registered With In Configured Time");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_patientVO, rs);				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveCrNoForModification:HelperMethods :: " + e);
		}
		return _patientVO;
	}
	
	public PatientVO updateAppointmentinSplClinicReg(HisDAO daoObj,PatientVO _patientVO, UserVO _userVO) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{			

			strProcName2 = "{call pkg_reg_dml.PROC_HAPT_APPOINTMENT_DTL(?,?,?,?,?, ?,?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientVO);			

			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk", _patientVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_apt_no", _patientVO.getPatAptNo(),3);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code", _userVO.getHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_apt_id", RegistrationConfig.PAT_APT_OPD_ID,5);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_apt_status", RegistrationConfig.PAT_APT_OPD_STATUS_CONFIRMED,6);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_seat_id", _userVO.getSeatId(),8);
			
			daoObj.setProcOutValue(nProcIndex2, "err", 1,9);

			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return _patientVO;				
	}
	
	/**
	 * Retrieves the List of all patients who has the appointments for Special Clinic Registration in a day
	 * Registration Desk.
	 * 
	 * @param _userVO Provides User details.
	 * @return Array of PatientVO containing all the CR No entered with in a Day.
	 * Added by Singaravelan on 28-Aug-2014.
	 */
	public PatientVO[] searchSpecialCRNoWithAppointment(PatientVO _patientVO,UserVO _userVO)
	{
		//String queryKey = "SELECT.HRGT_PATIENT_DTL.CR_NO1";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HAPT_APPOINTMENT_DTL_ON_SPLREG(?,?::character varying,?,?,?, ?::character varying,?::character varying,?,?,?, ?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_no", "",2);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_id", RegistrationConfig.PAT_APT_OPD_ID,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_status", RegistrationConfig.PAT_APT_OPD_STATUS_WAITING,5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_search_id", _patientVO.getSearchId(),6);
		daoObj.setProcInValue(nProcIndex, "p_gnum_search_value", _patientVO.getSearchValue(),7);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _userVO.getSeatId(),9);
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,10);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		PatientVO[] _patientVOArr={};
		ValueObject[] vo = {};
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Patient Appointment Details Not Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVOArr = new PatientVO[vo.length];
				for (int i = 0; i < vo.length; i++)
					_patientVOArr[i] = (PatientVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveCrNoForModification:HelperMethods :: " + e);
		}
		return _patientVOArr;
	}
	
	public String checkPatientIsAdmitted(String cr_NO,UserVO objUserVO_p)
	{
		String strIsAdmitted=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			
			System.out.println("PatientDAO :: checkPatientIsAdmitted()");
			daoObj = new HisDAO("Registration","PatientDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_is_admitted(?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, cr_NO);
			daoObj.setFuncInValue(funcIndex, 4, objUserVO_p.getHospitalCode());
					daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strIsAdmitted = daoObj.getFuncNumeric(funcIndex).toString();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDAO.checkPatientIsAdmitted()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return strIsAdmitted;
	}
	//Start: Sheeldarshi: 30thSep'14
	public List<RenewalConfigVO> getListOfRenewelConfigDtl(UserVO voUser_p,String strRenewelGrp_p, String strMode_p)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = RegistrationDaoConfig.RenewalConfigProcedure_view;
		int nProcIndex = 0;
		String strErr = "";
		List<RenewalConfigVO> lstRenewalConfigVO= new ArrayList();
		

		try 
		{
			
			daoObj = new HisDAO("Registration","EssentialDAO");
			
			////////////
			System.out.println("----------------------------------------");
			System.out.println("RegEssentialDAO :: getListOfRenewelConfigDtl()");
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_gnum_hospital_code :"+ voUser_p.getHospitalCode());
			System.out.println("p_renewal_gp :"+ strRenewelGrp_p);
			////////////
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", voUser_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_renewal_gp", strRenewelGrp_p,3);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				throw new HisRecordNotFoundException("No Renewal Config Detail Found");
				
			webRs.beforeFirst();
			if (strErr!=null && !strErr.equals("")) 
			{
				System.out.println("getRenewelConfigDtl() b4 throw");
				throw new Exception(strErr);
			} 
			
		}catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("getRenewelConfigDtl:: " + e.getMessage());
		}
		
		
		try {
			while(webRs.next()){
				RenewalConfigVO voRenewalConfigVO= new RenewalConfigVO();
				HelperMethods.populateVOfrmRS(voRenewalConfigVO, webRs);
				lstRenewalConfigVO.add(voRenewalConfigVO);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}	
		System.out.println("lstRenewalConfigVO.size :"+lstRenewalConfigVO.size());
		
		return lstRenewalConfigVO;
	}
	public Map<String, RenewalConfigVO> convertRenewalVoToMapOfRenewalVoOnKeyPatCat(List<RenewalConfigVO> lstrenRenewalConfigVOs){
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat = new HashMap<String, RenewalConfigVO>();
		for(RenewalConfigVO vo : lstrenRenewalConfigVOs){
			mapOfRenewalVoOnKeyPatCat.put(vo.getStrRenewalPatCat(), vo);
		}
		return mapOfRenewalVoOnKeyPatCat;
	}
	
	public RegistrationConfigVO getRegistrationConfigDtl(UserVO voUser_p,String strRenewelGrp_p, String strMode_p)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_reg_view.proc_hrgt_renewal_config_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		RegistrationConfigVO voRegistrationConfig=null;

		try 
		{
			voRegistrationConfig= new RegistrationConfigVO();
			daoObj = new HisDAO("Registration","EssentialDAO");
			
			////////////
			System.out.println("----------------------------------------");
			System.out.println("RegEssentialDAO :: getRegistrationConfigDtl()");
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_gnum_hospital_code :"+ voUser_p.getHospitalCode());
			System.out.println("p_renewal_gp :"+ strRenewelGrp_p);
			////////////
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", voUser_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_renewal_gp", strRenewelGrp_p,3);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				//throw new HisRecordNotFoundException("No Relation Found");
				webRs.beforeFirst();
			if (strErr!=null && !strErr.equals("")) 
			{
				System.out.println("getRegistrationConfigDtl() b4 throw");
				throw new Exception(strErr);
			} 
			
		}catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("getRegistrationConfigDtl:: " + e.getMessage());
		}
		
		
		try {
			if (webRs.next())
				HelperMethods.populateVOfrmRS(voRegistrationConfig, webRs);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}	
		
		return voRegistrationConfig;
	}
	//End: Sheeldarshi: 30thSep'14
	public static void updateCategoryFlag(String p_Mode,PatientVO objPatientVO_p,UserVO _uservo)
	{
		final String strProcName = "{call pkg_reg_dml.dml_pist_emp_and_family_patient_cat_dtl(?,?,?,?,?)}";
		final int nProcedureIndex;
		String strErr = "";
		final String strDbErr;
		HisDAO daoObj = null;
		daoObj = new HisDAO("Registration","EssentialDAO");
		try
		{
			nProcedureIndex = daoObj.setProcedure(strProcName);
			
			 //Setting and Registering In and Out Parameters 
			daoObj.setProcInValue(nProcedureIndex, "p_modeval",p_Mode,1);
			daoObj.setProcInValue(nProcedureIndex, "p_str_emp_no",objPatientVO_p.getHiddenPatIdNo(),2);
			daoObj.setProcInValue(nProcedureIndex, "p_num_mem_sl_no",objPatientVO_p.getMemSlNo(),3);
			daoObj.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",_uservo.getHospitalCode(),4);
			daoObj.setProcOutValue(nProcedureIndex, "err",1,5);
			daoObj.executeProcedureByPosition(nProcedureIndex);	
			strErr = daoObj.getString(nProcedureIndex, "err");
			
			if (strErr == null)
				strErr = "";

				if (!strErr.equals("")) 
				{
					throw new Exception(strErr);
				}
				else
				{						
				}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException(e.getMessage());
		}
	}
	public PatientVO savePreRegisteredPatDtl(HisDAO daoObj,PatientVO objPatientVO_p, UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			
			strProcName2 = "{call pkg_reg_dml.dml_mhrgt_mobreg_dtl(?,?,?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(objPatientVO_p);
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_ppuk",objPatientVO_p.getpatRefNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk", objPatientVO_p.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),4);
			//daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code", "33101",4);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,5);
			//End
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return objPatientVO_p;				
	}
	
	public PatientVO savePortalRegPatDtl(HisDAO daoObj,PatientVO objPatientVO_p, UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			
			strProcName2 = "{call pkg_reg_dml.dml_mhrgt_mobreg_dtl(?,?,?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(objPatientVO_p);
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_ppuk",objPatientVO_p.getPrevCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk", objPatientVO_p.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),4);
			//daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code", "33101",4);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,5);
			//End
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return objPatientVO_p;				
	}
	
	/**
	 * By Mukund On 12.01.18
	 * */
	public void savePatientMinimalDtl(HisDAO daoObj,PatientVO objPatVO, String _QRData, UserVO objUserVO_p, String mode) {
		
		WebRowSet rs = null;
		
		String strProcName = "{call pkg_reg_dml.dml_hrgt_patient_minimal_dtl(?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?)}";//45 args as on Apr '18
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
			nProcIndex = daoObj.setProcedure(strProcName);
			System.out.println("PatientDAO :: savePatientMinimalDtl()");
			/*
			 * PROCEDURE dml_hrgt_patient_minimal_dtl(p_modeval character varying DEFAULT '1'::character varying, p_hrgnum_puk character varying, 
			 * p_gnum_hospital_code character varying, p_hrgst_sec_uhid character varying, p_hrgdt_dob character varying,
			 *  p_hrgbl_is_actual_dob character varying, p_hrgstr_fname character varying, p_gstr_gender_code character varying, 
			 *  p_hrgjson_qr_data character varying, OUT err character varying) IS
			 *  
			 *  strProcName1 = "{call pkg_reg_dml.dml_hrgt_barcode_print_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?)}";//12 args as on sep'17
			
			HelperMethods.setNullToEmpty(objBarcodeSlipVO);
			HelperMethods.setNullToEmpty(objUserVO_p);

			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			System.out.println("BarcodeGenerationDAO :: saveBarcodeSlipDtl()");
			//////////////////////
	    	 int i =1;
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", strMode_p,i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", (objBarcodeSlipVO.getPatCrNo().equals("")?"0":objBarcodeSlipVO.getPatCrNo()),i++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objBarcodeSlipVO.getEpisodeCode(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",  objBarcodeSlipVO.getEpisodeVisitNo(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code",objBarcodeSlipVO.getDepartmentCode(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objBarcodeSlipVO.getDepartmentUnitCode(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_barcode_print_flag", objBarcodeSlipVO.getBarcodePrintFlag(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",  Config.IS_VALID_ACTIVE,i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),i++);
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,i++);//12th arg

			objHisDAO_p.execute(nProcIndex1,1);			
			
			strErr = objHisDAO_p.getString(nProcIndex1, "err");

	*/		HelperMethods.setNullToEmpty(objPatVO);
			HelperMethods.setNullToEmpty(objUserVO_p);
			int i = 1;
			////////////////////////////////////////////////////////////////////////
			System.out.println("------qrcode :--------"+_QRData);
			System.out.println("------crno :-------"+objPatVO.getPatCrNo());
			////////////////////////////////////////////////////////////////////////
			daoObj.setProcInValue(nProcIndex, "p_modeval", mode,i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk",objPatVO.getPatCrNo(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_sec_uhid", objPatVO.getPatSecUHID(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgdt_dob", "0",i++);
			
			daoObj.setProcInValue(nProcIndex, "p_hrgbl_is_actual_dob", objPatVO.getIsActualDob(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_fname", objPatVO.getPatFirstName(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gstr_gender_code", objPatVO.getPatGenderCode(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgjson_qr_data",  _QRData,i++);//9th argument
			daoObj.setProcInValue(nProcIndex, "p_ageUnit",objPatVO.getPatAgeUnit(),i++);
			
			
			/****************************/
			
			daoObj.setProcInValue(nProcIndex, "p_age", objPatVO.getPatAge(),i++);
			daoObj.setProcInValue(nProcIndex, "p_dob", objPatVO.getPatDOB(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_mname", objPatVO.getPatMiddleName(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_lname", objPatVO.getPatLastName(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gnum_document_code",objPatVO.getPatDocType(),i++);
			
			
			daoObj.setProcInValue(nProcIndex, "p_gnum_marital_status_code", objPatVO.getPatMaritalStatusCode(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_father_name", objPatVO.getPatGuardianName(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_momname", objPatVO.getPatMotherName(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hgnum_patient_cat_code", objPatVO.getPatPrimaryCatCode(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getSeatId(),i++);
			
			daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid", objPatVO.getIsValid(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_ip_add", (objUserVO_p.getIpAddress()==null|| objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_age", objPatVO.getPatAge(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_is_imageuploaded", (objPatVO.getIsImageUploaded().equals("")?"0":objPatVO.getIsImageUploaded()),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_spousename", objPatVO.getPatHusbandName(),i++); 
			
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_national_id", objPatVO.getPatNationalId(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_is_dead",objPatVO.getPatIsDead(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_card_no",objPatVO.getPatCardNo(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_address_line1",objPatVO.getPatAddHNo(),i++);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_sub_locality1",objPatVO.getPatAddStreet(),i++); 
			
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_sub_locality2",objPatVO.getPatAddLandMarks(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_city_location",objPatVO.getPatAddCityLoc(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_city",objPatVO.getPatAddCity(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_pincode",objPatVO.getPatAddPIN(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_district",objPatVO.getPatAddDistrict(),i++);
			
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_state_name",objPatVO.getPatAddState(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gstr_country_name",objPatVO.getPatAddCountry(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_is_urban", objPatVO.getPatIsUrban(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_mobile_no",objPatVO.getPatAddMobileNo(),i++); 
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_phone_no",objPatVO.getPatAddPhoneNo(),i++); 
			
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_email_id",objPatVO.getPatAddEmailId(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gnum_state_code ",objPatVO.getPatAddStateCode(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gnum_district_code",objPatVO.getPatAddDistrictCode(),i++);
			daoObj.setProcInValue(nProcIndex, "p_gstr_country_code",objPatVO.getPatAddCountryCode(),i++);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,i++);//45th Argument //Kindly update the argument number accordingly
			/****************************/

			//daoObj.executeProcedureByPosition(nProcIndex);
			daoObj.execute(nProcIndex, 1);
			//strErr = daoObj.getString(nProcIndex, "err");
			if(strErr == null)
				strErr="";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*End */
	
	//Added by Garima on 29-Mar-2019 for MyHealthRecordIntegration
		public PatientVO savePatDetailForMyHealthRecordIntegration(HisDAO daoObj,PatientVO objPatientVO_p, UserVO objUserVO_p,String strMode_p,String strModeExecuteProc_p) {

			String strErr = "";
			int nProcIndex1 = 0, index=1;
			String strProcName1="";
			
			try 
			{
				strProcName1 = "{call pkg_phrms_integration.dml_hint_phrms_upload_pat_dtl_log(?,?,?)}";
				HelperMethods.setNullToEmpty(objPatientVO_p);
				nProcIndex1 = daoObj.setProcedure(strProcName1);			
				
				daoObj.setProcInValue(nProcIndex1, "p_modeVal", strMode_p,index++);
				daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", objPatientVO_p.getPatCrNo(),index++);
				//daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode());
				daoObj.setProcOutValue(nProcIndex1, "err", 1,index++);
				
				if(!strModeExecuteProc_p.isEmpty() && strModeExecuteProc_p.equals("1")){
					daoObj.execute(nProcIndex1,1);
				}else{
					daoObj.executeProcedureByPosition(nProcIndex1);
					strErr = daoObj.getString(nProcIndex1, "err");
				}	
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}		
			return objPatientVO_p;				
		}
		//End VASU
}//end of class
