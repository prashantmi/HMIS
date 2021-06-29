package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AddressVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UnknownChangeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;
//import startup.UserMgmtConfigXmlHandler;


/**
 * PatientDAO is a class which describes all the methods required for database interaction for HRGT_PATIENT_DTL table, for example, insert, update, select
 * and delete. PatientDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class PatientDAO extends RegistrationDAO implements PatientDAOi {

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
	
	public String generateCrNumber(UserVO _userVO)
	{
		String strCrNo=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			daoObj = new HisDAO("Registration","PatientDAO");
			funcIndex = daoObj.setFunction("{? = call reg_function.generateCRno(?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, _userVO.getHospitalCode());
			daoObj.setFuncInValue(funcIndex, 4, Config.CR_NO_FORMAT_SIZE);
			daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strCrNo = daoObj.getFuncNumeric(funcIndex);
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("PatientDAO.generateCRno()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}
		return strCrNo;
	}
	
	public PatientVO getDOBAndAge(PatientVO _patientVO)
	{
		Connection conn = super.getTransactionContext().getConnection();
		//String strCrNo=new String();
		try{
		if (_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE)) {
			Procedure proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_DOB);
			proc.addInParameter(1, Types.VARCHAR, _patientVO.getPatAge());
			proc.addInParameter(2, Types.VARCHAR, _patientVO.getPatAgeUnit());
			

			proc.setReturnType(Types.DATE);
			java.sql.Date DOB = (java.sql.Date) proc.execute(conn);
			SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
			sf.applyPattern("dd/MMM/yyyy");
			String date = sf.format(DOB);
			_patientVO.setPatDOB(date);
			_patientVO.setPatAge(_patientVO.getPatAge()+" "+_patientVO.getPatAgeUnit());
		} 
		else {
			Procedure proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_PATIENT_AGE_BY_DOB);
			String systemDate=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_patientVO.getSystemDate(),""), "dd-MMM-yyyy");
			proc.addInParameter(1, Types.VARCHAR, _patientVO.getPatDOB());
			proc.addInParameter(2, Types.VARCHAR, systemDate);
			proc.setReturnType(Types.VARCHAR);
			String age = (String) proc.execute(conn);
			_patientVO.setPatAge(age);
			
		}                                  
		
	} catch (Exception e) {
		throw new HisDataAccessException("PatientDAO:getDOBAndAge()::=call GetDob(?)" + e);
	}
	return _patientVO;
	}

	// CRUD function
	/**
	 * Creates a new patient entry in DB for a patient. Generates a 12-digit CR
	 * No using a SQL Procedure. When patient's age is entered, isActualDob flag
	 * is reset and DOB is calulated for the patient to be stored in the Db.
	 * When patient's DOB is entered, isActualDob flag is set.
	 * @param _patientVO	Provides patient details to be entered.
	 * @param _userVO	Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	public PatientVO create(HisDAO daoObj,PatientVO _patientVO, UserVO _userVO) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			

			strProcName2 = "{call pkg_reg_dml.proc_hrgt_patient_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?::timestamp,?, ?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientVO);
			/*
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex2, "p_isActialDOB",_patientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", _patientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", _patientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB", _patientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", _patientVO.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", _patientVO.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", _patientVO.getIsActualDob(),9);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", _patientVO.getPatFirstName(),10);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", _patientVO.getPatMiddleName(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", _patientVO.getPatLastName(),12);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", _patientVO.getPatGenderCode(),13);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", _patientVO.getPatMaritalStatusCode(),14);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", _patientVO.getPatStatusCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", _patientVO.getPatIdMark1(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", _patientVO.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", _patientVO.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", _patientVO.getPatRegCatCode(),19);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", _patientVO.getPatPrimaryCatCode(),20);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date","",21);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",22);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", _patientVO.getSeatId(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", _patientVO.getIsMLC(),24);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientVO.getIsValid(),25);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", _patientVO.getIsUnknown(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", _patientVO.getPatMonthlyIncome(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", _patientVO.getPatIsUrban(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", _patientVO.getIsFree(),29);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVO.getDesigSemester(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVO.getDeptStaffStudent(),31);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", _patientVO.getPatAddress().getPatAddContactNo(),33);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", _patientVO.getPatIdNo(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", _patientVO.getIsReferred(),35);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", _patientVO.getPatReligionCode(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", _patientVO.getPatAge(),37);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", _patientVO.getPatNationalityCode(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", _patientVO.getIsImageUploaded(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", _patientVO.getPatHusbandName(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", _patientVO.getPatNationalId(),41);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", _patientVO.getPatBloodGroup(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", _patientVO.getIsNewBorn(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", _patientVO.getPatMotherCrNo(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",_patientVO.getPatOccupation(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",_patientVO.getPatFatherOccupation(),46);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",_patientVO.getPatHusbandOccupation(),47);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",_patientVO.getPatNickName(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",_patientVO.getPatCardNo(),49);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode(),50);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", _patientVO.getPatIdMark2(),51);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", _patientVO.getIsBroughtDead(),52);
			daoObj.setProcInValue(nProcIndex2, "hdsnum_disaster_id", _patientVO.getDisasterId(),53);
			daoObj.setProcInValue(nProcIndex2, "num_caste_id", _patientVO.getPatCasteCode(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno","",55);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,56);*/

			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex2, "p_isActialDOB",_patientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", _patientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", _patientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB", _patientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", _patientVO.getPrevCrNo().equals("")?"0":_patientVO.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", _patientVO.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", _patientVO.getIsActualDob(),9);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", _patientVO.getPatFirstName(),10);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", _patientVO.getPatMiddleName(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", _patientVO.getPatLastName(),12);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", _patientVO.getPatGenderCode(),13);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", _patientVO.getPatMaritalStatusCode().equals("")?"0":_patientVO.getPatMaritalStatusCode(),14);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", _patientVO.getPatStatusCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", _patientVO.getPatIdMark1(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", _patientVO.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", _patientVO.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", _patientVO.getPatRegCatCode(),19);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", _patientVO.getPatPrimaryCatCode(),20);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date","",21);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",22);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", _patientVO.getSeatId(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", _patientVO.getIsMLC().equals("")?"0":_patientVO.getIsMLC(),24);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientVO.getIsValid(),25);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", _patientVO.getIsUnknown(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", _patientVO.getPatMonthlyIncome().equals("")?"0":_patientVO.getPatMonthlyIncome(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", _patientVO.getPatIsUrban(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", _patientVO.getIsFree().equals("")?"0":_patientVO.getIsFree(),29);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVO.getDesigSemester().equals("")?"0":_patientVO.getDesigSemester(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVO.getDeptStaffStudent().equals("")?"0":_patientVO.getDeptStaffStudent(),31);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", _patientVO.getPatAddress().getPatAddContactNo(),33);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", _patientVO.getPatIdNo(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", _patientVO.getIsReferred(),35);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", _patientVO.getPatReligionCode(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", _patientVO.getPatAge(),37);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", _patientVO.getPatNationalityCode(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", _patientVO.getIsImageUploaded(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", _patientVO.getPatHusbandName(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", _patientVO.getPatNationalId(),41);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", _patientVO.getPatBloodGroup().equals("")?"0":_patientVO.getPatBloodGroup(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", _patientVO.getIsNewBorn(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", _patientVO.getPatMotherCrNo().equals("")?"0":_patientVO.getPatMotherCrNo(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",_patientVO.getPatOccupation(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",_patientVO.getPatFatherOccupation(),46);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",_patientVO.getPatHusbandOccupation(),47);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",_patientVO.getPatNickName(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",_patientVO.getPatCardNo(),49);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode(),50);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", _patientVO.getPatIdMark2(),51);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", _patientVO.getIsBroughtDead().equals("")?"0":_patientVO.getIsBroughtDead(),52);
			daoObj.setProcInValue(nProcIndex2, "hdsnum_disaster_id", _patientVO.getDisasterId().equals("")?"0":_patientVO.getDisasterId(),53);
			daoObj.setProcInValue(nProcIndex2, "num_caste_id", _patientVO.getPatCasteCode(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno","",55);
			daoObj.setProcInValue(nProcIndex2, "gnum_pat_edustatus_code", _patientVO.getPatEducationCode().equals("")?"0":_patientVO.getPatEducationCode(),56);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_birth_place", _patientVO.getPatBirthPlace().equals("")?" ":_patientVO.getPatBirthPlace(),57);
			daoObj.setProcInValue(nProcIndex2, "hgnum_id_doc_code", (_patientVO.getPatDocType().equalsIgnoreCase("-1")||_patientVO.getPatDocType().equalsIgnoreCase(""))?"-1":_patientVO.getPatDocType(),58);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_exp_date", _patientVO.getExpiryDate(),59);
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_fname_local", _patientVO.getPatFirstNameInMultiLang(),60);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_mname_local", _patientVO.getPatMiddleNameInMultiLang(),61);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_lname_local", _patientVO.getPatLastNameInMultiLang(),62);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,63);

			
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _patientVO;				
	}
	
	public PatientVO createEmgReg(HisDAO daoObj,PatientVO _patientVO, UserVO _userVO) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			

			strProcName2 = "{call pkg_reg_dml.proc_hrgt_patient_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientVO);
			/*
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex2, "p_isActialDOB",_patientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", _patientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", _patientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB", _patientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", _patientVO.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", _patientVO.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", _patientVO.getIsActualDob(),9);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", _patientVO.getPatFirstName(),10);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", _patientVO.getPatMiddleName(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", _patientVO.getPatLastName(),12);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", _patientVO.getPatGenderCode(),13);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", _patientVO.getPatMaritalStatusCode(),14);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", _patientVO.getPatStatusCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", _patientVO.getPatIdMark1(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", _patientVO.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", _patientVO.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", _patientVO.getPatRegCatCode(),19);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", _patientVO.getPatPrimaryCatCode(),20);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date","",21);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",22);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", _patientVO.getSeatId(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", _patientVO.getIsMLC(),24);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientVO.getIsValid(),25);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", _patientVO.getIsUnknown(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", _patientVO.getPatMonthlyIncome(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", _patientVO.getPatIsUrban(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", _patientVO.getIsFree(),29);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVO.getDesigSemester(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVO.getDeptStaffStudent(),31);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", _patientVO.getPatAddress().getPatAddContactNo(),33);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", _patientVO.getPatIdNo(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", _patientVO.getIsReferred(),35);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", _patientVO.getPatReligionCode(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", _patientVO.getPatAge(),37);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", _patientVO.getPatNationalityCode(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", _patientVO.getIsImageUploaded(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", _patientVO.getPatHusbandName(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", _patientVO.getPatNationalId(),41);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", _patientVO.getPatBloodGroup(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", _patientVO.getIsNewBorn(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", _patientVO.getPatMotherCrNo(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",_patientVO.getPatOccupation(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",_patientVO.getPatFatherOccupation(),46);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",_patientVO.getPatHusbandOccupation(),47);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",_patientVO.getPatNickName(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",_patientVO.getPatCardNo(),49);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode(),50);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", _patientVO.getPatIdMark2(),51);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", _patientVO.getIsBroughtDead(),52);
			daoObj.setProcInValue(nProcIndex2, "hdsnum_disaster_id", _patientVO.getDisasterId(),53);
			daoObj.setProcInValue(nProcIndex2, "num_caste_id", _patientVO.getPatCasteCode(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno","",55);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,56);*/

			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "4",1);
			daoObj.setProcInValue(nProcIndex2, "p_isActialDOB",_patientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", _patientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", _patientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB", _patientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", _patientVO.getPrevCrNo().equals("")?"0":_patientVO.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", _patientVO.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", _patientVO.getIsActualDob(),9);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", _patientVO.getPatFirstName(),10);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", _patientVO.getPatMiddleName(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", _patientVO.getPatLastName(),12);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", _patientVO.getPatGenderCode(),13);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", _patientVO.getPatMaritalStatusCode().equals("")?"0":_patientVO.getPatMaritalStatusCode(),14);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", _patientVO.getPatStatusCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", _patientVO.getPatIdMark1(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", _patientVO.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", _patientVO.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", _patientVO.getPatRegCatCode(),19);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", _patientVO.getPatPrimaryCatCode(),20);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date","",21);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",22);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", _patientVO.getSeatId(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", _patientVO.getIsMLC().equals("")?"0":_patientVO.getIsMLC(),24);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientVO.getIsValid(),25);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", _patientVO.getIsUnknown(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", _patientVO.getPatMonthlyIncome().equals("")?"0":_patientVO.getPatMonthlyIncome(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", _patientVO.getPatIsUrban(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", _patientVO.getIsFree().equals("")?"0":_patientVO.getIsFree(),29);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVO.getDesigSemester().equals("")?"0":_patientVO.getDesigSemester(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVO.getDeptStaffStudent().equals("")?"0":_patientVO.getDeptStaffStudent(),31);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", _patientVO.getPatAddress().getPatAddContactNo(),33);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", _patientVO.getPatIdNo(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", _patientVO.getIsReferred(),35);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", _patientVO.getPatReligionCode(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", _patientVO.getPatAge(),37);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", _patientVO.getPatNationalityCode(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", _patientVO.getIsImageUploaded(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", _patientVO.getPatHusbandName(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", _patientVO.getPatNationalId(),41);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", _patientVO.getPatBloodGroup().equals("")?"0":_patientVO.getPatBloodGroup(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", _patientVO.getIsNewBorn(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", _patientVO.getPatMotherCrNo().equals("")?"0":_patientVO.getPatMotherCrNo(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",_patientVO.getPatOccupation(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",_patientVO.getPatFatherOccupation(),46);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",_patientVO.getPatHusbandOccupation(),47);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",_patientVO.getPatNickName(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",_patientVO.getPatCardNo(),49);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode(),50);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", _patientVO.getPatIdMark2(),51);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", _patientVO.getIsBroughtDead().equals("")?"0":_patientVO.getIsBroughtDead(),52);
			daoObj.setProcInValue(nProcIndex2, "hdsnum_disaster_id", _patientVO.getDisasterId().equals("")?"0":_patientVO.getDisasterId(),53);
			daoObj.setProcInValue(nProcIndex2, "num_caste_id", _patientVO.getPatCasteCode(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno","",55);
			daoObj.setProcInValue(nProcIndex2, "gnum_pat_edustatus_code", _patientVO.getPatEducationCode().equals("")?"0":_patientVO.getPatEducationCode(),56);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_birth_place", _patientVO.getPatBirthPlace().equals("")?" ":_patientVO.getPatBirthPlace(),57);
			daoObj.setProcInValue(nProcIndex2, "hgnum_id_doc_code", (_patientVO.getPatDocType().equalsIgnoreCase("-1")||_patientVO.getPatDocType().equalsIgnoreCase("")||_patientVO.getPatDocType().equalsIgnoreCase(null))?"-1":_patientVO.getPatDocType(),58);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_exp_date", _patientVO.getExpiryDate(),59);
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_fname_local", _patientVO.getPatFirstNameInMultiLang(),60);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_mname_local", _patientVO.getPatMiddleNameInMultiLang(),61);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_lname_local", _patientVO.getPatLastNameInMultiLang(),62);


			daoObj.setProcOutValue(nProcIndex2, "err", 1,63);

			
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _patientVO;				
	}

	/**
	 * Populates the map with the Patient details to be entered in the DB.
	 * @param _patientVO	Provides PatientVO details to be entered.
	 * @param _populateMAP	Map containig values which will be used for insert query.
	 */
	public void populateMap(PatientVO _patientVO,UserVO _userVO,Map _populateMAP) throws SQLException {

		System.out.println("inside populateMap");

		Sequence sq = new Sequence();
		_populateMAP.put(sq.next(), _patientVO.getPatCrNo());
		_populateMAP.put(sq.next(), _patientVO.getPrevCrNo());
		_populateMAP.put(sq.next(), _patientVO.getPatDOB());
		_populateMAP.put(sq.next(), _patientVO.getIsActualDob());
		_populateMAP.put(sq.next(), _patientVO.getPatFirstName());
		//_populateMAP.put(sq.next(), _patientVO.getPatSalutationCode());
		_populateMAP.put(sq.next(), _patientVO.getPatMiddleName());
		_populateMAP.put(sq.next(), _patientVO.getPatLastName());
		_populateMAP.put(sq.next(), _patientVO.getPatGenderCode());
		_populateMAP.put(sq.next(), _patientVO.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), _patientVO.getPatStatusCode());
		_populateMAP.put(sq.next(), _patientVO.getPatIdMark1());
		_populateMAP.put(sq.next(), _patientVO.getPatGuardianName());
		_populateMAP.put(sq.next(), _patientVO.getPatMotherName());
		_populateMAP.put(sq.next(), _patientVO.getPatRegCatCode());
		_populateMAP.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		// _populateMAP.put(sq.next(), _patientVO.getRegisterDate());
		//_populateMAP.put(sq.next(), _patientVO.getExpiryDate());
		//_populateMAP.put(sq.next(), _patientVO.getDepartmentCode());
		//_populateMAP.put(sq.next(), _patientVO.getIsLock());
		//_populateMAP.put(sq.next(), _patientVO.getIsLockReason());
		// _populateMAP.put(sq.next(), _patientVO.getEntryDate());
		_populateMAP.put(sq.next(), _patientVO.getSeatId());
		_populateMAP.put(sq.next(), _patientVO.getIsMLC());
		_populateMAP.put(sq.next(), _patientVO.getIsValid());
		_populateMAP.put(sq.next(), _patientVO.getIsUnknown());
		_populateMAP.put(sq.next(), _patientVO.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), _patientVO.getPatIsUrban());
		//_populateMAP.put(sq.next(), _patientVO.getIsNProgram());
		_populateMAP.put(sq.next(), _patientVO.getIsFree());
		//_populateMAP.put(sq.next(), _patientVO.getCurrentDepartmentCode());
		_populateMAP.put(sq.next(), _patientVO.getDesigSemester());
		_populateMAP.put(sq.next(), _patientVO.getDeptStaffStudent());
		//_populateMAP.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _userVO.getIpAddress());
		_populateMAP.put(sq.next(), _patientVO.getPatAddress().getPatAddContactNo());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefHospitalName());
		_populateMAP.put(sq.next(), _patientVO.getPatIdNo());
		//_populateMAP.put(sq.next(), _patientVO.getPatAmountCollected());
		_populateMAP.put(sq.next(), _patientVO.getIsReferred());
		//_populateMAP.put(sq.next(), _patientVO.getEmergencyType());
		//_populateMAP.put(sq.next(), _patientVO.getPatAddress().getPatAddTypeCode());
		_populateMAP.put(sq.next(), _patientVO.getPatReligionCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefDoctor());
		_populateMAP.put(sq.next(), _patientVO.getPatAge());
		_populateMAP.put(sq.next(), _patientVO.getPatNationalityCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalCrno());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalDept());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), _patientVO.getIsImageUploaded());
		_populateMAP.put(sq.next(), _patientVO.getPatHusbandName());
		_populateMAP.put(sq.next(), _patientVO.getPatNationalId());
		_populateMAP.put(sq.next(), _patientVO.getPatBloodGroup());
		_populateMAP.put(sq.next(), _patientVO.getIsNewBorn());
		_populateMAP.put(sq.next(), _patientVO.getPatMotherCrNo());
		 _populateMAP.put(sq.next(),_patientVO.getPatOccupation());
		 _populateMAP.put(sq.next(),_patientVO.getPatFatherOccupation());
		 _populateMAP.put(sq.next(),_patientVO.getPatHusbandOccupation());
		 _populateMAP.put(sq.next(),_patientVO.getPatNickName());
		 _populateMAP.put(sq.next(),_patientVO.getPatCardNo());	
		_populateMAP.put(sq.next(),_userVO.getHospitalCode());	
		_populateMAP.put(sq.next(), _patientVO.getPatIdMark2());
		_populateMAP.put(sq.next(), _patientVO.getIsBroughtDead());
		_populateMAP.put(sq.next(), _patientVO.getDisasterId());
	}
 
	/**
	 * Updates already stored patient record in the DB. When patient's age is entered, isActualDob flag is reset and DOB is calulated for the patient
	 * to be stored in the Db. When patient's DOB is entered, isActualDob flag is set.
	 * @param _patientVO	Provides address details to be entered.
	 * @param _userVO	Provides User details.
	 * @return Number of records updated.
	 */
	public PatientVO create(HisDAO daoObj,PatientVO _patientVO, UserVO _userVO,String daysOrMonthForRenewalExpiry) {

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="";
		if(daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_DAY))
		 	 queryKey="INSERT.HRGT_PATIENT_DTL_DAY";	 	  
		 	else
		 		if(daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_MONTH))
		 		 queryKey="INSERT.HRGT_PATIENT_DTL_MONTH";	
		//String queryDate = "";
		//String queryKeyDate = "SELECT.SYSDATE";

		// CallableStatement clst=null;
		Connection conn = super.getTransactionContext().getConnection();
		// call the getQueryMethod with arguments filename,querykey from prop file
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		
		//	queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			

		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		//String dateForAge = "";
		/*try {
			ResultSet dt = HelperMethodsDAO.executeQuery(conn, queryDate);
			if (!dt.next()) {
				throw new HisRecordNotFoundException("");
			} else {
				ResultSetMetaData rsmd = dt.getMetaData();
				int no_of_col = rsmd.getColumnCount();
				System.out.println("no_of_col" + no_of_col);
				dt.beforeFirst();
				while (dt.next()) {
					for (int i = 1; i <= no_of_col; i++) {
						dateForAge = dt.getString(i);
						System.out.println("date:::..." + dateForAge);
					}
				}
			}
		} catch (Exception e) {
			throw new HisDataAccessException("PatientDAO:create:HelperMethods :: " + e);
		}*/
		try {
			Procedure proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_CRNO);
			/*proc.addInParameter(1, Types.VARCHAR,_userVO.getHospitalCode());
			proc.addInParameter(2, Types.VARCHAR,Config.CR_NO_FORMAT_SIZE);*/
			proc.addInParameter(1, Types.VARCHAR,_userVO.getHospitalCode());
			proc.addInParameter(2, Types.VARCHAR,Config.CR_NO_FORMAT_SIZE);
			proc.setReturnType(Types.VARCHAR);
			String strCrNo = (String) proc.execute(conn);
			// String str=clst.getString(1);
			_patientVO.setPatCrNo(strCrNo);
			_patientVO.setSeatId(_userVO.getSeatId());
			// _patientVO.setPatRegCatCode("0");
			
			// get dob if not actual dob
			if (_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE)) {
				proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_DOB);
				proc.addInParameter(1, Types.VARCHAR, _patientVO.getPatAge());
				proc.addInParameter(2, Types.VARCHAR, _patientVO.getPatAgeUnit());
				proc.setReturnType(Types.DATE);
				java.sql.Date DOB = (java.sql.Date) proc.execute(conn);

				SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
				sf.applyPattern("dd/MMM/yyyy");
				String date = sf.format(DOB);
				_patientVO.setPatDOB(date);
				//String ageWithUnit=_patientVO.getPatAge()+_patientVO.getPatAgeUnit();
				_patientVO.setPatAge(_patientVO.getPatAge()+" "+_patientVO.getPatAgeUnit());
			} 
			else {
				proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_PATIENT_AGE_BY_DOB);
				String systemDate=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_patientVO.getSystemDate(),""), "dd-MMM-yyyy");
				proc.addInParameter(1, Types.VARCHAR, _patientVO.getPatDOB());
				
				proc.addInParameter(2, Types.VARCHAR, systemDate);
				proc.setReturnType(Types.VARCHAR);
				String age = (String) proc.execute(conn);
				_patientVO.setPatAge(age);
				
			}                                  
			
		} catch (Exception e) {
			throw new HisDataAccessException("PatientDAO:prepareCall()::=call generateCRno(?)" + e);
		}
		try {
			populateMapWithDaysOrMonthRenewalExp(_patientVO, populateMAP,_userVO,daysOrMonthForRenewalExpiry);
			
		} catch (Exception e) {
			throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+ e);
		}
		return _patientVO;
	}

	/**
	 * Populates the map with the Patient details to be entered in the DB.
	 * @param _patientVO	Provides PatientVO details to be entered.
	 * @param _populateMAP	Map containig values which will be used for insert query.
	 */
	public void populateMapWithDaysOrMonthRenewalExp(PatientVO _patientVO, Map _populateMAP,UserVO _userVO,String daysOrMonthForRenewalExpiry) throws SQLException {

		System.out.println("inside populateMap");

		Sequence sq = new Sequence();
		_populateMAP.put(sq.next(), _patientVO.getPatCrNo());
		_populateMAP.put(sq.next(), _patientVO.getPrevCrNo());
		_populateMAP.put(sq.next(), _patientVO.getPatDOB());
		_populateMAP.put(sq.next(), _patientVO.getIsActualDob());
		_populateMAP.put(sq.next(), _patientVO.getPatFirstName());
		//_populateMAP.put(sq.next(), _patientVO.getPatSalutationCode());
		_populateMAP.put(sq.next(), _patientVO.getPatMiddleName());
		_populateMAP.put(sq.next(), _patientVO.getPatLastName());
		_populateMAP.put(sq.next(), _patientVO.getPatGenderCode());
		_populateMAP.put(sq.next(), _patientVO.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), _patientVO.getPatStatusCode());
		_populateMAP.put(sq.next(), _patientVO.getPatIdMark1());
		_populateMAP.put(sq.next(), _patientVO.getPatGuardianName());
		_populateMAP.put(sq.next(), _patientVO.getPatMotherName());
		_populateMAP.put(sq.next(), _patientVO.getPatRegCatCode());
		_populateMAP.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		// _populateMAP.put(sq.next(), _patientVO.getRegisterDate());
		//_populateMAP.put(sq.next(), _patientVO.getExpiryDate());
		//_populateMAP.put(sq.next(), _patientVO.getDepartmentCode());
		//_populateMAP.put(sq.next(), _patientVO.getIsLock());
		//_populateMAP.put(sq.next(), _patientVO.getIsLockReason());
		// _populateMAP.put(sq.next(), _patientVO.getEntryDate());
		_populateMAP.put(sq.next(), _patientVO.getSeatId());
		_populateMAP.put(sq.next(), _patientVO.getIsMLC());
		_populateMAP.put(sq.next(), _patientVO.getIsValid());
		_populateMAP.put(sq.next(), _patientVO.getIsUnknown());
		_populateMAP.put(sq.next(), _patientVO.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), _patientVO.getPatIsUrban());
		//_populateMAP.put(sq.next(), _patientVO.getIsNProgram());
		_populateMAP.put(sq.next(), _patientVO.getIsFree());
		//_populateMAP.put(sq.next(), _patientVO.getCurrentDepartmentCode());
		_populateMAP.put(sq.next(), _patientVO.getDesigSemester());
		_populateMAP.put(sq.next(), _patientVO.getDeptStaffStudent());
		//_populateMAP.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _userVO.getIpAddress());
		_populateMAP.put(sq.next(), _patientVO.getPatAddress().getPatAddContactNo());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefHospitalName());
		_populateMAP.put(sq.next(), _patientVO.getPatIdNo());
		//_populateMAP.put(sq.next(), _patientVO.getPatAmountCollected());
		_populateMAP.put(sq.next(), _patientVO.getIsReferred());
		//_populateMAP.put(sq.next(), _patientVO.getEmergencyType());
		//_populateMAP.put(sq.next(), _patientVO.getPatAddress().getPatAddTypeCode());
		_populateMAP.put(sq.next(), _patientVO.getPatReligionCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefDoctor());
		_populateMAP.put(sq.next(), _patientVO.getPatAge());
		_populateMAP.put(sq.next(), _patientVO.getPatNationalityCode());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalCrno());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalDept());
		//_populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), _patientVO.getIsImageUploaded());
		_populateMAP.put(sq.next(), _patientVO.getPatHusbandName());
		_populateMAP.put(sq.next(), _patientVO.getPatNationalId());
		_populateMAP.put(sq.next(), _patientVO.getPatBloodGroup());
		_populateMAP.put(sq.next(), _patientVO.getIsNewBorn());
		_populateMAP.put(sq.next(), _patientVO.getPatMotherCrNo());
		if(daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_MONTH))
			_populateMAP.put(sq.next(), _patientVO.getExpiryDate());
		_populateMAP.put(sq.next(), _patientVO.getExpiryDate());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		_populateMAP.put(sq.next(), _patientVO.getPatIdMark2());
		_populateMAP.put(sq.next(), _patientVO.getIsBroughtDead());
		_populateMAP.put(sq.next(), _patientVO.getDisasterId());
	}

	public int update(HisDAO daoObj, PatientVO _patientVO, UserVO _userVO)
	{
		int x = 1;
		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			strProcName2 ="{call pkg_reg_dml.proc_hrgt_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?::timestamp,?,?,?,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientVO);
			
						
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "3",1);
			daoObj.setProcInValue(nProcIndex2, "p_isActialDOB",_patientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", _patientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", _patientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB",_patientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientVO.getPatCrNo()==""?"0": _patientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", _patientVO.getPrevCrNo()==""?"0": _patientVO.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", _patientVO.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", _patientVO.getIsActualDob()==""?"0": _patientVO.getIsActualDob(),9);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", _patientVO.getPatFirstName(),10);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", _patientVO.getPatMiddleName(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", _patientVO.getPatLastName(),12);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", _patientVO.getPatGenderCode()==""?"0": _patientVO.getPatGenderCode(),13);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", _patientVO.getPatMaritalStatusCode()==""?"0": _patientVO.getPatMaritalStatusCode(),14);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", _patientVO.getPatStatusCode()==""?"0": _patientVO.getPatStatusCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", _patientVO.getPatIdMark1(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", _patientVO.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", _patientVO.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", _patientVO.getPatRegCatCode()==""?"0": _patientVO.getPatRegCatCode(),19);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", _patientVO.getPatPrimaryCatCode()==""?"0": _patientVO.getPatPrimaryCatCode(),20);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date","",21);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",22);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", _patientVO.getSeatId()==""?"0": _patientVO.getSeatId(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", _patientVO.getIsMLC()==""?"0": _patientVO.getIsMLC(),24);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientVO.getIsValid()==""?"0": _patientVO.getIsValid(),25);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", _patientVO.getIsUnknown()==""?"0": _patientVO.getIsUnknown(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", _patientVO.getPatMonthlyIncome()==""?"0": _patientVO.getPatMonthlyIncome(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", _patientVO.getPatIsUrban()==""?"0": _patientVO.getPatIsUrban(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", _patientVO.getIsFree()==""?"0": _patientVO.getIsFree(),29);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVO.getDesigSemester()==""?"0": _patientVO.getDesigSemester(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVO.getDeptStaffStudent()==""?"0": _patientVO.getDeptStaffStudent(),31);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", _patientVO.getPatAddress().getPatAddContactNo(),33);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", _patientVO.getPatIdNo(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", _patientVO.getIsReferred()==""?"0": _patientVO.getIsReferred(),35);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", _patientVO.getPatReligionCode()==""?"0": _patientVO.getPatReligionCode(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", _patientVO.getPatAge(),37);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", _patientVO.getPatNationalityCode()==""?"0": _patientVO.getPatNationalityCode(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", _patientVO.getIsImageUploaded()==""?"0": _patientVO.getIsImageUploaded(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", _patientVO.getPatHusbandName(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", _patientVO.getPatNationalId(),41);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", _patientVO.getPatBloodGroup()==""?"0": _patientVO.getPatBloodGroup(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", _patientVO.getIsNewBorn()==""?"0": _patientVO.getIsNewBorn(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", _patientVO.getPatMotherCrNo()==""?"0": _patientVO.getPatMotherCrNo(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",_patientVO.getPatOccupation(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",_patientVO.getPatFatherOccupation(),46);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",_patientVO.getPatHusbandOccupation(),47);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",_patientVO.getPatNickName(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",_patientVO.getPatCardNo(),49);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode()==""?"0": _userVO.getHospitalCode(),50);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", _patientVO.getPatIdMark2(),51);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", _patientVO.getIsBroughtDead()==""?"0": _patientVO.getIsBroughtDead(),52);
			daoObj.setProcInValue(nProcIndex2, "hdsnum_disaster_id", _patientVO.getDisasterId()==""?"0": _patientVO.getDisasterId(),53);
			daoObj.setProcInValue(nProcIndex2, "num_caste_id", _patientVO.getPatCasteCode()==""?"0": _patientVO.getPatCasteCode(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno", _patientVO.getVerificationDocumentId(),55);
			daoObj.setProcInValue(nProcIndex2, "gnum_pat_edustatus_code", "0",56);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_birth_place", _patientVO.getPatBirthPlace().equals("")?" ":_patientVO.getPatBirthPlace(),57);
			daoObj.setProcInValue(nProcIndex2, "hgnum_id_doc_code", (_patientVO.getPatDocType().equalsIgnoreCase("-1")||_patientVO.getPatDocType().equalsIgnoreCase("")||_patientVO.getPatDocType().equalsIgnoreCase(null))?"-1":_patientVO.getPatDocType(),58);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_exp_date", _patientVO.getExpiryDate(),59);
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_fname_local", _patientVO.getPatFirstNameInMultiLang(),60);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_mname_local", _patientVO.getPatMiddleNameInMultiLang(),61);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_lname_local", _patientVO.getPatLastNameInMultiLang(),62);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,63);
			
			
			
			
			
			
			daoObj.execute(nProcIndex2,1);
			
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return x;
	}

	/**
	 * Update the patient detail without updating the age and dob of the patient.
	 * @param _patientVO
	 * @param _userVO
	 * @return
	 */
	public int updateWithoutAge(HisDAO daoObj, PatientVO _patientVO, UserVO _userVO)
	{
		int x = 1;
		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			strProcName2 = "{call pkg_reg_dml.proc_hrgt_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?::timestamp,?,?,?,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientVO);
			
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex2, "p_isActialDOB",_patientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex2, "p_age", _patientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex2, "p_ageUnit", _patientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex2, "p_DOB",_patientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientVO.getPatCrNo()==""?"0": _patientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", _patientVO.getPrevCrNo()==""?"0": _patientVO.getPrevCrNo(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", _patientVO.getPatDOB(),8);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", _patientVO.getIsActualDob()==""?"0": _patientVO.getIsActualDob(),9);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", _patientVO.getPatFirstName(),10);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", _patientVO.getPatMiddleName(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", _patientVO.getPatLastName(),12);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", _patientVO.getPatGenderCode()==""?"0": _patientVO.getPatGenderCode(),13);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", _patientVO.getPatMaritalStatusCode()==""?"0": _patientVO.getPatMaritalStatusCode(),14);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", _patientVO.getPatStatusCode()==""?"0": _patientVO.getPatStatusCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", _patientVO.getPatIdMark1(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", _patientVO.getPatGuardianName(),17);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", _patientVO.getPatMotherName(),18);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", _patientVO.getPatRegCatCode()==""?"0": _patientVO.getPatRegCatCode(),19);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", _patientVO.getPatPrimaryCatCode()==""?"0": _patientVO.getPatPrimaryCatCode(),20);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date","",21);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",22);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", _patientVO.getSeatId()==""?"0": _patientVO.getSeatId(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", _patientVO.getIsMLC()==""?"0": _patientVO.getIsMLC(),24);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientVO.getIsValid()==""?"0": _patientVO.getIsValid(),25);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", _patientVO.getIsUnknown()==""?"0": _patientVO.getIsUnknown(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", _patientVO.getPatMonthlyIncome()==""?"0": _patientVO.getPatMonthlyIncome(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", _patientVO.getPatIsUrban()==""?"0": _patientVO.getPatIsUrban(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", _patientVO.getIsFree()==""?"0": _patientVO.getIsFree(),29);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVO.getDesigSemester()==""?"0": _patientVO.getDesigSemester(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVO.getDeptStaffStudent()==""?"0": _patientVO.getDeptStaffStudent(),31);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", _patientVO.getPatAddress().getPatAddContactNo(),33);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", _patientVO.getPatIdNo(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", _patientVO.getIsReferred()==""?"0": _patientVO.getIsReferred(),35);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", _patientVO.getPatReligionCode()==""?"0": _patientVO.getPatReligionCode(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", _patientVO.getPatAge(),37);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", _patientVO.getPatNationalityCode()==""?"0": _patientVO.getPatNationalityCode(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", _patientVO.getIsImageUploaded()==""?"0": _patientVO.getIsImageUploaded(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", _patientVO.getPatHusbandName(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", _patientVO.getPatNationalId(),41);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", _patientVO.getPatBloodGroup()==""?"0": _patientVO.getPatBloodGroup(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", _patientVO.getIsNewBorn()==""?"0": _patientVO.getIsNewBorn(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", _patientVO.getPatMotherCrNo()==""?"0": _patientVO.getPatMotherCrNo(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",_patientVO.getPatOccupation(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",_patientVO.getPatFatherOccupation(),46);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",_patientVO.getPatHusbandOccupation(),47);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",_patientVO.getPatNickName(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",_patientVO.getPatCardNo(),49);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode()==""?"0": _userVO.getHospitalCode(),50);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", _patientVO.getPatIdMark2(),51);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", _patientVO.getIsBroughtDead()==""?"0": _patientVO.getIsBroughtDead(),52);
			daoObj.setProcInValue(nProcIndex2, "hdsnum_disaster_id", _patientVO.getDisasterId()==""?"0": _patientVO.getDisasterId(),53);
			daoObj.setProcInValue(nProcIndex2, "num_caste_id", _patientVO.getPatCasteCode()==""?"0": _patientVO.getPatCasteCode(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno", _patientVO.getVerificationDocumentId(),55);
			daoObj.setProcInValue(nProcIndex2, "gnum_pat_edustatus_code", "0",56);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_birth_place", _patientVO.getPatBirthPlace().equals("")?" ":_patientVO.getPatBirthPlace(),57);
			daoObj.setProcInValue(nProcIndex2, "hgnum_id_doc_code", _patientVO.getPatDocType().equalsIgnoreCase("-1")?"-1":_patientVO.getPatDocType(),58);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_exp_date", _patientVO.getExpiryDate(),59);
			
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_fname_local", _patientVO.getPatFirstNameInMultiLang(),60);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_mname_local", _patientVO.getPatMiddleNameInMultiLang(),61);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_lname_local", _patientVO.getPatLastNameInMultiLang(),62);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,63);
			
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return x;				
	}
	
	/**
	 * Populates the map with the patient details to be updated in the DB.
	 * @param _addressVO	Provides patient details to be entered.
	 * @param _populateUpdateMAP	Map containig values which will be used for update query.
	 */
	public void populateUpdate(PatientVO _patientVO, Map _populateUpdateMap,UserVO _userVO) throws SQLException {

		System.out.println("inside populateMap");
		Sequence sq = new Sequence();
		System.out.println("_patientVO.getPatRegCat()..::10"+ _patientVO.getPatRegCatCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPrevCrNo());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatDOB());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsActualDob());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatFirstName());
		System.out.println("_patientVO.getPatFirstName()::::....1"+ _patientVO.getPatFirstName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMiddleName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatLastName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatGenderCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMaritalStatusCode());
		//_populateUpdateMap.put(sq.next(), _patientVO.getPatStatusCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIdMark1());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatGuardianName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMotherName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatRegCatCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		//_populateUpdateMap.put(sq.next(), _patientVO.getSeatId());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsMLC());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsValid());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsUnknown());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMonthlyIncome());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIsUrban());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsFree());
		_populateUpdateMap.put(sq.next(), _patientVO.getDesigSemester());
		_populateUpdateMap.put(sq.next(), _patientVO.getDeptStaffStudent());
		_populateUpdateMap.put(sq.next(), _userVO.getIpAddress());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatAddress().getPatAddContactNo());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIdNo());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsReferred());
		//_populateUpdateMap.put(sq.next(), _patientVO.getPatAddress().getPatAddTypeCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatReligionCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatAge());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatNationalityCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsImageUploaded());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatHusbandName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatNationalId());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatBloodGroup());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsNewBorn());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMotherCrNo());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatOccupation());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatFatherOccupation());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatHusbandOccupation());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatNickName());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatCardNo());  	
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIdMark2());
		_populateUpdateMap.put(sq.next(), _patientVO.getVerificationDocumentId());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsBroughtDead());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatStatusCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatCrNo());
		_populateUpdateMap.put(sq.next(), _userVO.getHospitalCode());
	
	}
 
	/**
	 * Populates the map with the patient details to be updated in the DB.
	 * @param _addressVO	Provides patient details to be entered.
	 * @param _populateUpdateMAP	Map containig values which will be used for update query.
	 */
	public void populateUpdateWithoutAge(PatientVO _patientVO, Map _populateUpdateMap,UserVO _userVO) throws SQLException {
		
		
		Sequence sq = new Sequence();
		
		_populateUpdateMap.put(sq.next(), _patientVO.getPrevCrNo());
		//_populateUpdateMap.put(sq.next(), _patientVO.getPatDOB());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsActualDob());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatFirstName());
		
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMiddleName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatLastName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatGenderCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMaritalStatusCode());
		//_populateUpdateMap.put(sq.next(), _patientVO.getPatStatusCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIdMark1());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatGuardianName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMotherName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatRegCatCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		//_populateUpdateMap.put(sq.next(), _patientVO.getSeatId());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsMLC());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsValid());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsUnknown());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMonthlyIncome());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIsUrban());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsFree());
		_populateUpdateMap.put(sq.next(), _patientVO.getDesigSemester());
		_populateUpdateMap.put(sq.next(), _patientVO.getDeptStaffStudent());
		_populateUpdateMap.put(sq.next(), _userVO.getIpAddress());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatAddress().getPatAddContactNo());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIdNo());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsReferred());
		//_populateUpdateMap.put(sq.next(), _patientVO.getPatAddress().getPatAddTypeCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatReligionCode());
		//_populateUpdateMap.put(sq.next(), _patientVO.getPatAge());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatNationalityCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsImageUploaded());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatHusbandName());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatNationalId());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatBloodGroup());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsNewBorn());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatMotherCrNo());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatOccupation());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatFatherOccupation());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatHusbandOccupation());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatNickName());
		_populateUpdateMap.put(sq.next(),_patientVO.getPatCardNo());  	
		_populateUpdateMap.put(sq.next(), _patientVO.getPatIdMark2());
		_populateUpdateMap.put(sq.next(), _patientVO.getVerificationDocumentId());
		_populateUpdateMap.put(sq.next(), _patientVO.getIsBroughtDead());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatStatusCode());
		_populateUpdateMap.put(sq.next(), _patientVO.getPatCrNo());
		_populateUpdateMap.put(sq.next(), _userVO.getHospitalCode());
		
	}
	
	/**
	 * Calculates the timestamp of a record entered for a patient. 
	 * Whenpatient's age is entered, isActualDob flag is reset and DOB is calulated
	 * for the patient to be stored in the Db. When patient's DOB is entered, isActualDob flag is set.
	 * @param _patientVO	Provides patient details.
	 * @param _userVO	Provides User details.
	 * @return Time-duration since the entry of the record.
	 */
	public float checkTimeStamp(PatientVO _patientVO, UserVO _userVO)
	{
		//String queryKey = "TIMESTAMP.HRGT_PATIENT_DTL";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_DTL(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_mode", "2",1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patientVO.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _patientVO.getSeatId(),5);
		daoObj.setProcInValue(nProcIndex, "p_super_hospital_code", Config.SUPER_USER_HOSPITAL_CODE,6);
		daoObj.setProcInValue(nProcIndex, "p_modification_time", "",7);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_type", "",8);
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		float timestamp = 0;
		try
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next())
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
					timestamp = rs.getFloat(i);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDAO:checkTimeStamp:HelperMethods :: " + e);
		}
		return timestamp;
	}

	/**
	 * Retrieves the details of a patient on the basis of CR No. Calculates the
	 * age of the patient as on today.
	 * @param _patientVO	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param _userVO	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public PatientVO retrieveByCrNo(PatientVO _patientVO, AddressVO _addressVO, UserVO _userVO) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_DETAIL_WITH_CRNO(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);

		/*daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),1);
		daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_USER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "p_crno", _patientVO.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
		daoObj.executeProcedureByPosition(nProcIndex);
		//daoObj.execute(nProcIndex,1);	*/

		//daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),1);
		daoObj.setProcInValue(nProcIndex, "p_mode", "0",1);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", _patientVO.getPatCrNo().substring(0,3),2);
		/*daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_USER_HOSPITAL_CODE,2);*/
		daoObj.setProcInValue(nProcIndex, "p_crno", _patientVO.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
			/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			System.out.println("queryDate " + queryDate);
*/
		strErr = daoObj.getString(nProcIndex, "err");
		//System.out.println("strErr----------------------->"+strErr);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}

		try {
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Patient Details Not Found");
			} else {
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_patientVO, rs);
				_patientVO.setPatAddress(_addressVO);
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
		return _patientVO;
	}

	/**
	 * Retrieves the the CR No of all patients who have been registered with in last 10 minutes. Used for modification at
	 * Registration Desk.
	 * 
	 * @param _userVO Provides User details.
	 * @return Array of PatientVO containing all the CR No entered with in last 10 minutes.
	 */
	public PatientVO[] retrieveCrNoForModification(UserVO _userVO, String episodeVisitType)
	{
		//String queryKey = "SELECT.HRGT_PATIENT_DTL.CR_NO1";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_DTL(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		UserMgmtConfigXmlHandler configHandler = new UserMgmtConfigXmlHandler();
		int modificationTime = configHandler.getRegistrationModificationTime();
		String modTime = String.valueOf(modificationTime);

		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", "",3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _userVO.getSeatId(),5);
		daoObj.setProcInValue(nProcIndex, "p_super_hospital_code", Config.SUPER_USER_HOSPITAL_CODE,6);
		daoObj.setProcInValue(nProcIndex, "p_modification_time", modTime,7);
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		PatientVO[] _patientVO={};
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
	 * Retrieves the the CR No of all patients who have been registered with in
	 * last 10 minutes. Used for modification at Registration Desk.
	 * @param _userVO	Provides User details.
	 * @return List of CR No entered with in last 10 minutes.
	 */
	public List getCrNoForModification(UserVO _userVO) {
		ResultSet rs = null;
		Map _populateMapPatientCrNo = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.CR_NO";

		Sequence sq = new Sequence();
		_populateMapPatientCrNo.put(sq.next(), Config.TIME_DURATION_MODIFICATION);
		_populateMapPatientCrNo.put(sq.next(), _userVO.getSeatId());
		_populateMapPatientCrNo.put(sq.next(), Config.IS_VALID_ACTIVE);
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);
		List alRecord = new ArrayList();
		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientCrNo);
			System.out.println("hi..>>>");
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				System.out.println("hi..>>>1");
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
				System.out.println("hi..>>>2");
				System.out.println("after populating _patientVO");
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("getCrNoForModification::HelperMethodsDAO.getResultset"+ e);
		}
		System.out.println("alRecord registration category" + alRecord);
		return alRecord;
	}

	/**
	 * Retrieves the patient details from another DB system on the basis of
	 * Previous CR No. Calculates the age of the patient as on today.
	 * @param _patientVO	Provides previous CR No of the patient whose address is to be searched.
	 * @param _userVO	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public PatientVO previousSystemCrNo(PatientVO _patientVO, AddressVO _addressVO, UserVO _userVO) {

		Map _populateMapPrevSystemPatientDtl = new HashMap();
		//Map _populateMapForAge = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.PREVIOUS_SYSTEM.HRGT_PREV_PATIENT_DTL";

		//String age = "";
		//String queryDate = "";
		//String queryKeyDate = "SELECT.PREVIOUS_SYSTEM.GET_AGE_ON";

		//Sequence sqAge = new Sequence();
		//_populateMapForAge.put(sqAge.next(), _patientVO.getPrevCrNo());

		Sequence sq = new Sequence();
		_populateMapPrevSystemPatientDtl.put(sq.next(), _patientVO.getPrevCrNo());
		_populateMapPrevSystemPatientDtl.put(sq.next(), _patientVO.getPrevCrNo());
		_populateMapPrevSystemPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		Connection conn = super.getTransactionContext().getConnection();
		
		try {
			//queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			//System.out.println("queryDate " + queryDate);

			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);
		
		/*try {
			System.out.println("hi!!!!!");
			//ResultSet date = HelperMethodsDAO.executeQuery(conn, queryDate, _populateMapForAge);
			//System.out.println("hi!!!!!..1");

			if (!date.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + date.getString(1));
				date.beforeFirst();
				ResultSetMetaData rsmd = date.getMetaData();
				System.out.println("hi!!!!!..2");
				int no_of_col = rsmd.getColumnCount();
				System.out.println("hi!!!!!..3");
				while (date.next()) {
					for (int i = 1; i <= no_of_col; i++) {
						i++;
						age = date.getString(rsmd.getColumnName(i));
					}
				}
				System.out.println("age:::..." + age);
				_patientVO.setPatAddress(_addressVO);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:previousSystemCrNo:HelperMethods :: " + e);
		}
*/
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPrevSystemPatientDtl);
			System.out.println("rows:::" + rs.getRow());

			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_patientVO, rs);
				// System.out.println("_patientVO.getPatFirstName()"+_patientVO.getPatFirstName());
				// System.out.println("_patientVO.getPatMiddleName()"+_patientVO.getPatMiddleName());
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:previousSystemCrNo:HelperMethods :: " + e);
		}
		
		System.out.println("_patientVO.getPatAge() in patientDAO..::.. "+_patientVO.getPatAge());
		 
		return _patientVO;
	}

	/**
	 * Retrieves all the patients with same name. Used for modification at Registration Desk.
	 * @param _patientName	Name to be searched.
	 * @param _userVO	Provides User details. 
	 * @return Array of PatientVO containing details of all the patient having same name.
	 */
	public PatientVO[] retrieveByName(String _patientName, AddressVO[] _addressVO,  UserVO _userVO) {
		PatientVO[] _patientVO={};
		ValueObject[] vo = {};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_NAME";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), _patientName);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
			
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException("No Record Found Against This Name");
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}

		return _patientVO;
	}
	
	/**
	 * Changes primary catgory of a patient.
	 * @param _patientVO	Provides patient details.
	 * @param _userVO	Provides User details.
	 */
	public int changePatientPrimaryCat(PatientVO _patientVO, UserVO _userVO,String _patIdNo) {
		
		Map _populateMapPatientDtl = new HashMap();
		int x = 0;
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.PRIMARY_CATEGORY.HRGT_PATIENT_DTL";
		Sequence sq = new Sequence();
		_populateMapPatientDtl.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		_populateMapPatientDtl.put(sq.next(), _patIdNo);
		_populateMapPatientDtl.put(sq.next(), _patientVO.getPatCrNo());
		//_populateMapPatientDtl.put(sq.next(),_userVO.getHospitalCode());
		Connection conn = super.getTransactionContext().getConnection();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);
		try {
			x = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMapPatientDtl);
			if (x == 0) {
				throw new HisUpdateUnsuccesfullException();
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisUpdateUnsuccesfullException();
			} else
				throw new HisDataAccessException("PatientDAO:changePatientPrimaryCat:HelperMethods :: " + e);
		}
		System.out.println("value of x in PatientDAO..." + x);
		return x;
	}


	/**
	 * Updates patient refer details at MRD.
	 * @param _patientVO	Provides patient details.
	 * @param _userVO	Provides User details.
	 */
	public int updateRefDtlAtMRD(PatientVO _patientVO, UserVO _userVO) {

		Map _populateMapPatientDtl = new HashMap();
		int x = 0;
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.REF_DTL_MRD.HRGT_PATIENT_DTL";
		//Sequence sq = new Sequence();
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatRefGnctdHospitalCode());
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatRefHospitalName());
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatRefDoctor());
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatRefGnctdHospitalCrno());
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatRefGnctdHospitalDept());
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatRefGnctdHospitalDeptUnit());
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatCrNo());
		Connection conn = super.getTransactionContext().getConnection();
	
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);
		try {
			x = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMapPatientDtl);
			if (x == 0) {
				throw new HisUpdateUnsuccesfullException();
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisUpdateUnsuccesfullException();
			} else
				throw new HisDataAccessException("PatientDAO:changePatientCat:HelperMethods :: " + e);
		}
		System.out.println("value of x in PatientDAO..." + x);
		return x;
	}

	public PatientVO[] retrieveByContactNo(String contactNo, AddressVO[] _addressVO, UserVO _userVO) {
		ValueObject[] vo = {};
		PatientVO[] _patientVO={};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_CONTACT_NUMBER";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), contactNo);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByContactNo:HelperMethods :: " + e);
		}
		return _patientVO;
	}

	public PatientVO[] retrieveByNationalID(String nationalID, AddressVO[] _addressVO, UserVO _uservo) {
		ValueObject[] vo = {};
		PatientVO[] _patientVO={};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_NATIONALID";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), nationalID+"%");
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByNationalID:HelperMethods :: " + e);
		}
		
		return _patientVO;
	}
	public PatientVO[] retrieveByEmployeeID(String nationalID, AddressVO[] _addressVO, UserVO _uservo) {
		ValueObject[] vo = {};
		PatientVO[] _patientVO={};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_EMPLOYEEID";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), nationalID);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else 
				throw new HisDataAccessException("PatientDAO:retrieveByNationalID:HelperMethods :: " + e);
		}
		
		return _patientVO;
	}

	public void updateExpiryDate(String _patCrNo,String _expiryDate,UserVO _userVO){
			String query  = "";
		   Map populateMAP =new HashMap();
		   String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		   String queryKey="UPDATE.SET.EXPIRYDATE.HRGT_PATIENT_DTL";
		   
		   try{
		       query =HelperMethodsDAO.getQuery(filename,queryKey);
		       
		       
		       
		       
		    }     
		   catch(Exception e){
		   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		   }
		   System.out.println("query"+query);
		   
		   try{    	  
			      Sequence sq=new Sequence();
			      populateMAP.put(sq.next(), _expiryDate);
			      populateMAP.put(sq.next(), _expiryDate);
			      populateMAP.put(sq.next(),_patCrNo);
			      populateMAP.put(sq.next(),_userVO.getHospitalCode());
			       
			      
			           
		   }
		   catch(Exception e){
		   	throw new HisApplicationExecutionException("PatientDAoDAO:updateexpiry()::"+e);
		   }
		   try{
		   	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	
		   	
		   }
		   catch(Exception e){
		   	e.printStackTrace();
		   	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		   }     
			
	}
	
	//////////////update hrgt patient dtl in case category changes to employee

	public void updatePatientEmployeeDetails(PatientVO _patientVO,UserVO _userVO){
		//String query  = "";
	  // Map populateMAP =new HashMap();
	   //String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	   //String queryKey="UPDATE.CATEGORY.AND.EMPLOYEE.DETAIL.HRGT_PATIENT_DTL";
	   Procedure proc =new Procedure(RegistrationDaoConfig.PROCEDURE_UPDATE_PATIENT_DTL);
	   
	   
	   try{    	  
		      Sequence sq=new Sequence();
		      
		      //
		     if( _patientVO.getPatMonthlyIncome()==null || _patientVO.getPatMonthlyIncome()=="")
		    	 {
		    	 _patientVO.setPatMonthlyIncome("0");
		    	 
		    	 }
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatCrNo());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatDOB());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatFirstName());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatMiddleName());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatLastName());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatGenderCode());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatMaritalStatusCode());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatIdNo());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatMonthlyIncome());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatGuardianName());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatMotherName());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatHusbandName());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatAddHNo());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatAddPIN());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatAddStateCode());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatAddContactNo());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatAddCountryCode());
				proc.addOutParameter(sq.next(),Types.VARCHAR);
				proc.addInParameter(sq.next(), Types.VARCHAR, _userVO.getHospitalCode());
				proc.addInParameter(sq.next(), Types.VARCHAR, _patientVO.getPatPrimaryCatCode());
				
				
			
				//proc.setReturnType(Types.VARCHAR);
				
		       
		      
		           
	   }
	   catch(Exception e){
	   	throw new HisApplicationExecutionException("PatientDAoDAO:updatePatientEmployeeDetails()::"+e);
	   }
	   try{
		   proc.execute(super.getTransactionContext().getConnection());
	   	
	   	
	   }
	   catch(Exception e){
	   	e.printStackTrace();
	   	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
	   }     
		
}

public PatientVO[] searchPatient(HashMap _searchMap,UserVO _userVO)
	
	{
		PatientVO[] patientVOs=null;
		AddressVO[] addressVOs=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		//PatientVO[] _patVO=null;
		String query="";
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.SEARCH.PATIENT";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		int Rs;
		String orderBy=" ORDER BY  initcap(a.hrgstr_fname||a.hrgstr_mname||a.hrgstr_lname),a.hrgstr_father_name ";
		Sequence sq = new Sequence();
		
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		//////////////Preparing query to fetch patient records using patient details/////////
				
		
		Set keySet=(Set)_searchMap.keySet();
		
		Iterator keySetItr=keySet.iterator();
		
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_searchMap.get(mapKey);
			mapValue=mapValue.trim();
			if(mapKey.equals("gnum_city_loc_code")){
				whereCOndition=whereCOndition+" AND "+"( UPPER (b.hrgstr_city_location) LIKE UPPER ('"+mapValue+"%'))";
			}
			else if(mapKey.equalsIgnoreCase("fromDate")){
				
				whereCOndition=whereCOndition+" AND a.hrgnum_puk IN (SELECT hrgnum_puk FROM hrgt_episode_dtl WHERE "+
					"TRUNC (hrgdt_episode_start_date)>= trunc(to_date('"+_searchMap.get("fromdate")+"','dd-Mon-yyyy'))"+ 
					" and gnum_hospital_code=a.gnum_hospital_code)";
			}
			
			else if(mapKey.equalsIgnoreCase("toDate")){
				
				whereCOndition=whereCOndition+" AND a.hrgnum_puk IN (SELECT hrgnum_puk FROM hrgt_episode_dtl WHERE "+
				"TRUNC (hrgdt_episode_start_date)<= trunc(to_date('"+_searchMap.get("todate")+"','dd-Mon-yyyy'))"+ 
				" and gnum_hospital_code=a.gnum_hospital_code)";
			}
			else if(mapKey.equals("hrgstr_age")){
				String lowerLimit=mapValue.split("-")[0];
				String upperLimit=mapValue.split("-")[1];
				//modified by yogender(below 1 year case)
				if(lowerLimit.equals("0"))
				{
					lowerLimit="-365";
				}
				whereCOndition=whereCOndition+"AND round(date_part('day',((TO_DATE(TO_CHAR(sysdate::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy')- TO_DATE(TO_CHAR(HRGDT_DOB::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy'))))/365) >" + lowerLimit ;
				whereCOndition=whereCOndition+" AND  round(date_part('day',((TO_DATE(TO_CHAR(sysdate::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy')- TO_DATE(TO_CHAR(HRGDT_DOB::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy'))))/365) <=" + upperLimit ;
			}
			else if(mapKey.equals("hrgstr_file_no")){
				
				whereCOndition=whereCOndition+" AND a.hrgnum_puk IN (SELECT hrgnum_puk FROM hrgt_episode_dtl WHERE "+
				mapKey+" like '"+mapValue+"'"+ 
				" and gnum_hospital_code=a.gnum_hospital_code)";
			}
			else if(mapKey.equals("gnum_hospital_code")){
				if(!mapValue.equalsIgnoreCase("100")){
				whereCOndition=whereCOndition+" AND "+"( UPPER (a."+mapKey+") = ('"+mapValue+"'))";
				}
			}
		
			else
				whereCOndition=whereCOndition+" AND "+"( UPPER (a."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		finalQuery=query + whereCOndition + orderBy;
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("NO Patient found matching the search criteria");
			}
			else{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
				addressVOs=new AddressVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					addressVOs[i]=(AddressVO)valueObjects[i];
					
				}
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				patientVOs=new PatientVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					patientVOs[i]=(PatientVO)valueObjects[i];
					patientVOs[i].setPatAddress(addressVOs[i]);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			throw new HisDataAccessException("PatientDAO:searchPatient() Data access exception "+e);
		}
		
		return patientVOs;
	}


	
public List getPrimaryCatListExceptPatientCat(String patCat, UserVO userVO)
{
	ResultSet rs=null;
	String query =  "" ;
	Map populateMAP =new HashMap();
	String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
	String queryKey="SELECT_PAT_CAT_LIST_EXCEPT_SELECTED_PAT_CAT.GBLT_PATIENT_CAT_MST";
	Sequence sq=new Sequence();
	
	try
	{
		query =HelperMethodsDAO.getQuery(fileName,queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	}
	
	populateMAP.put(sq.next(), RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY);
	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	populateMAP.put(sq.next(),patCat);
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
	populateMAP.put(sq.next(), userVO.getSeatId());
	populateMAP.put(sq.next(), userVO.getHospitalCode());
	populateMAP.put(sq.next(), Config.MODULE_ID_REGISTRATION);
	
	
	try
	{
		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		if(!rs.next())
		{
			throw new HisRecordNotFoundException("No Category Found");
		}
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	}
	List alRecord = new ArrayList();
	try
	{
		alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
	}
	return alRecord;
}

	public void updateAreaCategory(String _patCrNo,String _patIsUrban,UserVO _userVO)
	{
		String query  = "";
		   Map populateMAP =new HashMap();
		   String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		   String queryKey="UPDATE.SET.IS_URBAN.HRGT_PATIENT_DTL";
		   
		   try{
		       query =HelperMethodsDAO.getQuery(filename,queryKey);
		       
		       
		       
		       
		    }     
		   catch(Exception e){
		   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		   }
		   System.out.println("updateAreaCategoryQuery------>"+query);
		   
		   try{    	  
			      Sequence sq=new Sequence();
			      populateMAP.put(sq.next(), _patIsUrban);
			      populateMAP.put(sq.next(),_patCrNo);
			           
		   }
		   catch(Exception e){
		   	throw new HisApplicationExecutionException("PatientDAoDAO:updateexpiry()::"+e);
		   }
		   try{
		   	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	
		   	
		   }
		   catch(Exception e){
		   	e.printStackTrace();
		   	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		   }     
			
	}


public PatientDetailVO getPatientHrgtEssentials(String _patCrNo,UserVO _userVO)
	
	{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	String queryKey = "SELECT_PAT_AUDIT_TRAIL.HRGT_PATIENT_DTL";
	//int count=0;
	ResultSet rs;
	PatientDetailVO _patDetailVO=new PatientDetailVO();
	AddressVO _addressVO=new AddressVO();
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	
	log.info(query);
	try  
	{
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_patCrNo);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
		
		
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
		System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		 if(!rs.next()){
			 throw new HisRecordNotFoundException("Details Not Found For The Corresponding Cr No.");
		               }
		 			else {
		       			rs.beforeFirst();
		    			HelperMethods.populateVOfrmRS(_patDetailVO, rs);
		    			HelperMethods.populateVOfrmRS(_addressVO, rs);
		    			
		    			_patDetailVO.setPatAddContactNo(_addressVO.getPatAddContactNo());
		    				
		    			}
		            		   
		              	
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) 
			throw new HisRecordNotFoundException(e.getMessage());
		else
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	

return _patDetailVO;
	}

public PatientDetailVO[] getPatientAuditEssentials(String _patCrNo,UserVO _userVO)

{
String query = "";
Map populateMAP = new HashMap();
Sequence sq = new Sequence();
 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
String queryKey = "SELECT_PAT_AUDIT_TRAIL.HRGT_PATIENT_AUDIT_DTL";
//int count=0;
ResultSet rs;
PatientDetailVO[] _patDetailVO=null;
AddressVO[] _addressVO=null;
ValueObject[] valueObjects1=null;
ValueObject[] valueObjects2=null;

try
{
	query = HelperMethodsDAO.getQuery(filename, queryKey);
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
}

log.info(query);
try  
{
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),_patCrNo);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	
	
	
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
}
try
{
	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	rs.last();
	System.out.println("Number of rows=========="+rs.getRow());
	rs.beforeFirst();
	 if(!rs.next()){
	//	 throw new HisRecordNotFoundException("Details Not Found");
	               }
	 			else {
	       				
	    			rs.beforeFirst();
	    			valueObjects1=HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
	    			rs.beforeFirst();
	    			valueObjects2=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
	    			
	    			_patDetailVO=new PatientDetailVO[valueObjects1.length];
	    			_addressVO=new AddressVO[valueObjects2.length];
	    			
	    			for(int i=0;i< valueObjects1.length;i++){
	    				
	    				_patDetailVO[i]=(PatientDetailVO)valueObjects1[i];
	    				_addressVO[i]=(AddressVO)valueObjects2[i];
	    				_patDetailVO[i].setPatAddContactNo(_addressVO[i].getPatAddContactNo());
	    													}
	 			}
	              	
}
catch (Exception e)
{
	if (e.getClass() == HisRecordNotFoundException.class) 
		throw new HisRecordNotFoundException(e.getMessage());
	else
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
}


return _patDetailVO;
	}

public AddressVO[] getPatientAddressDetailsEssentials(String _patCrNo,UserVO _userVO)

{
String query = "";
Map populateMAP = new HashMap();
Sequence sq = new Sequence();
 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
String queryKey = "SELECT_PAT_AUDIT_TRAIL.HRGT_PATIENT_ADD_DTL";
//int count=0;
ResultSet rs;

AddressVO[] _addressVO=null;
ValueObject[] valueObjects=null;


try
{
	query = HelperMethodsDAO.getQuery(filename, queryKey);
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
}

log.info(query);
try  
{
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),_patCrNo);
	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	
	
	
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
}
try
{
	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	rs.last();
	System.out.println("Number of rows=========="+rs.getRow());
	rs.beforeFirst();
	 if(!rs.next()){
	//	 throw new HisRecordNotFoundException("Details Not Found");
	               }
	 			else {
	       				
	    			rs.beforeFirst();
	    			
	    			valueObjects=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
	    			_addressVO=new AddressVO[valueObjects.length];
	    			
	    			for(int i=0;i< valueObjects.length;i++){
	    				   			_addressVO[i]=(AddressVO)valueObjects[i];
	    				
	    													}
	 			}
	              	
}
catch (Exception e)
{
	if (e.getClass() == HisRecordNotFoundException.class) 
	{
	//	throw new HisRecordNotFoundException(e.getMessage());
	}
	else
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
}


return _addressVO;
	}

public PrimaryCategoryChangeVO[] getPrimaryCategotyChangeDetailsEssentials(String _patCrNo,UserVO _userVO)

{
String query = "";
Map populateMAP = new HashMap();
Sequence sq = new Sequence();
 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
String queryKey = "SELECT_PAT_AUDIT_TRAIL.HRGT_PRICAT_CHANGE_DTL";
//int count=0;
ResultSet rs;

PrimaryCategoryChangeVO[] _primaryCatgVO=null;
ValueObject[] valueObjects=null;


try
{
	query = HelperMethodsDAO.getQuery(filename, queryKey);
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
}

log.info(query);
try  
{
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),RegistrationConfig.PATIENT_CATEGORY_PROCESS_ID);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),_patCrNo);
	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	
	
	
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
}
try
{
	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	rs.last();
	System.out.println("Number of rows=========="+rs.getRow());
	rs.beforeFirst();
	 if(!rs.next()){
	//	 throw new HisRecordNotFoundException("Details Not Found");
	               }
	 			else {
	       				
	    			rs.beforeFirst();
	    			
	    			valueObjects=HelperMethods.populateVOfrmRS(PrimaryCategoryChangeVO.class, rs);
	    			_primaryCatgVO=new PrimaryCategoryChangeVO[valueObjects.length];
	    			
	    			for(int i=0;i< valueObjects.length;i++){
	    				_primaryCatgVO[i]=(PrimaryCategoryChangeVO)valueObjects[i];
	    				
	    													}
	 			}
	              	
}
catch (Exception e)
{
	if (e.getClass() == HisRecordNotFoundException.class) 
	{
	//	throw new HisRecordNotFoundException(e.getMessage());
	}
	else
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
}


return _primaryCatgVO;
	}

public SecondaryCategoryChangeVO[] getSecondaryCategotyChangeDetailsEssentials(String _patCrNo,UserVO _userVO)

{
String query = "";
Map populateMAP = new HashMap();
Sequence sq = new Sequence();
 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
String queryKey = "SELECT_PAT_AUDIT_TRAIL.HRGT_SECCAT_CHANGE_DTL";
//int count=0;
ResultSet rs;

SecondaryCategoryChangeVO[] _secondaryCatgVO=null;
ValueObject[] valueObjects=null;


try
{
	query = HelperMethodsDAO.getQuery(filename, queryKey);
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
}

log.info(query);
try  
{
	
	populateMAP.put(sq.next(),_patCrNo);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);	
	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	
	
	
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
}
try
{
	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	rs.last();
	System.out.println("Number of rows=========="+rs.getRow());
	rs.beforeFirst();
	 if(!rs.next()){
	//	 throw new HisRecordNotFoundException("Details Not Found");
	               }
	 			else {
	       				
	    			rs.beforeFirst();
	    			
	    			valueObjects=HelperMethods.populateVOfrmRS(SecondaryCategoryChangeVO.class, rs);
	    			_secondaryCatgVO=new SecondaryCategoryChangeVO[valueObjects.length];
	    			
	    			for(int i=0;i< valueObjects.length;i++){
	    				_secondaryCatgVO[i]=(SecondaryCategoryChangeVO)valueObjects[i];
	    				
	    													}
	 			}
	              	
}
catch (Exception e)
{
	if (e.getClass() == HisRecordNotFoundException.class) 
	{
	//	throw new HisRecordNotFoundException(e.getMessage());
	}
	else
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
}


return _secondaryCatgVO;
	}

public UnknownChangeVO[] getUnknownToKnownChangeDetails(String _patCrNo,UserVO _userVO)

{
String query = "";
Map populateMAP = new HashMap();
Sequence sq = new Sequence();
 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
String queryKey = "SELECT_PAT_AUDIT_TRAIL.HRGT_UNKNOWN_CHANGE_DTL";
//int count=0;
ResultSet rs;
UnknownChangeVO[] _unknownChangeVO=null;
ValueObject[] valueObjects=null;

try
{
	query = HelperMethodsDAO.getQuery(filename, queryKey);
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
}

log.info(query);
try  
{
	
	populateMAP.put(sq.next(),_patCrNo);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	
	
	
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
}
try
{
	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	rs.last();
	System.out.println("Number of rows=========="+rs.getRow());
	rs.beforeFirst();
	 if(!rs.next()){
	//	 throw new HisRecordNotFoundException("Details Not Found For The Corresponding Cr No.");
	               }
	 			else {
	       				   rs.beforeFirst(); 				
	    			valueObjects=HelperMethods.populateVOfrmRS(UnknownChangeVO.class, rs);
	    			_unknownChangeVO=new UnknownChangeVO[valueObjects.length];
	    			
	    			for(int i=0;i< valueObjects.length;i++){
	    				_unknownChangeVO[i]=(UnknownChangeVO)valueObjects[i];
	    													}		
	    			}
	            		   
	              	
}
catch (Exception e)
{
	if (e.getClass() == HisRecordNotFoundException.class) 
	{
	//	throw new HisRecordNotFoundException(e.getMessage());
	}
	else
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
}


return _unknownChangeVO;
}

public MlcVO[] getPatientMlcChangeDetails(String _patCrNo,UserVO _userVO)

{
String query = "";
Map populateMAP = new HashMap();
Sequence sq = new Sequence();
 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
String queryKey = "SELECT_PAT_AUDIT_TRAIL.HRGT_PATIENT_MLC_DTL";
//int count=0;
ResultSet rs;
MlcVO[] _mlcVO=null;
ValueObject[] valueObjects=null;

try
{
	query = HelperMethodsDAO.getQuery(filename, queryKey);
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
}

log.info(query);
try  
{
	
	populateMAP.put(sq.next(),_patCrNo);
	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	
	
	
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
}
try
{
	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	rs.last();
	System.out.println("Number of rows=========="+rs.getRow());
	rs.beforeFirst();
	 if(!rs.next()){
	//	 throw new HisRecordNotFoundException("Details Not Found For The Corresponding Cr No.");
	               }
	 			else {
	 			   rs.beforeFirst();     				
	    			valueObjects=HelperMethods.populateVOfrmRS(MlcVO.class, rs);
	    			_mlcVO=new MlcVO[valueObjects.length];
	    			
	    			for(int i=0;i< valueObjects.length;i++){
	    				_mlcVO[i]=(MlcVO)valueObjects[i];
	    													}		
	    			}
	            		   
	              	
}
catch (Exception e)
{
	if (e.getClass() == HisRecordNotFoundException.class) 
	{
	//	throw new HisRecordNotFoundException(e.getMessage());
	}
	else
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
}


return _mlcVO;
}

public int updatePatStatus(String patCrNo, String SerialNo, String visitNo, String episodeCode, UserVO _userVO)
{
	int x = 0;
	Sequence sq = new Sequence();
	String query = "";
	Map _populateUpdateMap = new HashMap();
	String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
	String queryKey = "UPDATE.HRGT_PATIENT_DTL.PAT_STATUS";
	Connection conn = super.getTransactionContext().getConnection();
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

		_populateUpdateMap.put(sq.next(), RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
		_populateUpdateMap.put(sq.next(), patCrNo);
		//_populateUpdateMap.put(sq.next(), _userVO.getHospitalCode()); //commented by Akash Singh due to multihospital issue for Patient Death Detal process from OPD Desk, dated on 07-05-2015

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

	// Getting Patient Additional Information (for the ANC Process)
	/*public PatientDetailVO getPatientAdditionalInformation(String _patCrNo, UserVO _userVO)
	{
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT_ADDITIONAL_PAT_INFORMATION.HRGT_PATIENT_DTL";
		ResultSet rs;
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		try  
		{
			populateMAP.put(sq.next(),_patCrNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO _patDetailVO=new PatientDetailVO();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
					throw new HisRecordNotFoundException("No Patient Record Found");
			HelperMethods.populateVOfrmRS(_patDetailVO, rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return _patDetailVO;
	}*/

//Getting Patient Additional Information (for the ANC Process)--Changed for IPD
public PatientDetailVO getPatientAdditionalInformation(String _patCrNo, UserVO _userVO)
{
	String query = "";
	String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
	String queryKey = "SELECT_ADDITIONAL_PAT_INFORMATION.HRGT_PATIENT_DTL";
	ResultSet rs;
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);
	
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	try  
	{
		populateMAP.put(sq.next(),_patCrNo);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
	}
	
	PatientDetailVO _patDetailVO=new PatientDetailVO();
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		if(!rs.next())
				throw new HisRecordNotFoundException("No Patient Record Found");
		HelperMethods.populateVOfrmRS(_patDetailVO, rs);
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) 
			throw new HisRecordNotFoundException(e.getMessage());
		else
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	return _patDetailVO;
}

	
	// Updating Patient Additional Information (for the ANC Process)
	/*public void updatePatientAdditionalInformation(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.SET_ADDITIONAL_PAT_INFORMATION.HRGT_PATIENT_DTL";
		
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
			Sequence sq=new Sequence();
			populateMAP.put(sq.next(), _patDtlVO.getPatGuardianName());
			populateMAP.put(sq.next(), _patDtlVO.getPatMotherName());
			populateMAP.put(sq.next(), _patDtlVO.getPatHusbandName());
			populateMAP.put(sq.next(), _patDtlVO.getPatBloodGroup());
			populateMAP.put(sq.next(), _patDtlVO.getPatHusbandBloodGroup());
			populateMAP.put(sq.next(), _patDtlVO.getPatOccupation());
			populateMAP.put(sq.next(), _patDtlVO.getPatFatherOccupation());
			populateMAP.put(sq.next(), _patDtlVO.getPatHusbandOccupation());
			populateMAP.put(sq.next(), _patDtlVO.getPatCaste());
			populateMAP.put(sq.next(), _patDtlVO.getPatFamilyType());
			populateMAP.put(sq.next(), _patDtlVO.getPatEducationStatus());
			populateMAP.put(sq.next(), _patDtlVO.getPatSpouseEducationStatus());
			populateMAP.put(sq.next(), _patDtlVO.getPatMotherEducationStatus());
			populateMAP.put(sq.next(), _patDtlVO.getPatFatherEducationStatus());
			populateMAP.put(sq.next(), _patDtlVO.getPatMotherOccupation());
			populateMAP.put(sq.next(), _patDtlVO.getIsActualDateOfMarriage());
			populateMAP.put(sq.next(), RegistrationConfig.NO);
			populateMAP.put(sq.next(), _patDtlVO.getPatAgeOfMarriage());
			populateMAP.put(sq.next(), _patDtlVO.getPatDateOfMarriage());
			populateMAP.put(sq.next(), _patDtlVO.getPatAgeOfMenarche());
			populateMAP.put(sq.next(), _patDtlVO.getPatCrNo());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO:updatePatientAdditionalInformation()::"+e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		}
	}*/

	// Updating Patient Additional Information (for the ANC Process)--Changed for IPD
	public void updatePatientAdditionalInformation(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.SET_ADDITIONAL_PAT_INFORMATION.HRGT_PATIENT_DTL";
		
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
			Sequence sq=new Sequence();
			populateMAP.put(sq.next(), _patDtlVO.getPatGuardianName());
			populateMAP.put(sq.next(), _patDtlVO.getPatMotherName());
			populateMAP.put(sq.next(), _patDtlVO.getPatHusbandName());
			populateMAP.put(sq.next(), _patDtlVO.getPatBloodGroup());
			//populateMAP.put(sq.next(), _patDtlVO.getPatHusbandBloodGroup());
			//populateMAP.put(sq.next(), _patDtlVO.getPatOccupation());
			//populateMAP.put(sq.next(), _patDtlVO.getPatFatherOccupation());
			//populateMAP.put(sq.next(), _patDtlVO.getPatHusbandOccupation());
			//populateMAP.put(sq.next(), _patDtlVO.getPatCaste());
			//populateMAP.put(sq.next(), _patDtlVO.getPatFamilyType());
			//populateMAP.put(sq.next(), _patDtlVO.getPatEducationStatus());
			//populateMAP.put(sq.next(), _patDtlVO.getPatSpouseEducationStatus());
			//populateMAP.put(sq.next(), _patDtlVO.getPatMotherEducationStatus());
			//populateMAP.put(sq.next(), _patDtlVO.getPatFatherEducationStatus());
			//populateMAP.put(sq.next(), _patDtlVO.getPatMotherOccupation());
			//populateMAP.put(sq.next(), _patDtlVO.getIsActualDateOfMarriage());
			//populateMAP.put(sq.next(), RegistrationConfig.NO);
			//populateMAP.put(sq.next(), _patDtlVO.getPatAgeOfMarriage().equals("")?"0": _patDtlVO.getPatAgeOfMarriage());
			//populateMAP.put(sq.next(), _patDtlVO.getPatAgeOfMarriage().equals("")?"0": _patDtlVO.getPatAgeOfMarriage());
			//populateMAP.put(sq.next(), _patDtlVO.getPatDateOfMarriage());
			//populateMAP.put(sq.next(), _patDtlVO.getPatAgeOfMenarche().equals("")?"0": _patDtlVO.getPatAgeOfMenarche());
			//populateMAP.put(sq.next(), _patDtlVO.getPatAgeOfMenarche().equals("")?"0": _patDtlVO.getPatAgeOfMenarche());
			populateMAP.put(sq.next(), _patDtlVO.getPatCrNo());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO:updatePatientAdditionalInformation()::"+e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		}
	}
	
	
	/**
	 * Retrieves the details of a patient on the basis of CR No. For EMR and Calculates the
	 * age of the patient as on today.
	 * @param _patientVO	Provides patient details.
	 * @param _userVO	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public PatientVO retrieveByCrNoEMR(PatientVO _patientVO,UserVO _userVO) {
		Map _populateMapPatientDtl = new HashMap();
		Map _populateMapForAge = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.BY_CRNO_EMR";
		//String age = "";
		
		Sequence sqAge = new Sequence();
		_populateMapForAge.put(sqAge.next(), _patientVO.getPatCrNo());

		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		//_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query::::: " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Patient Details Not Found");
			} else {
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_patientVO, rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return _patientVO;
	}


	
	
	
	
	/*public void updatePatientCasteAreaType(PatientDetailVO pDetailVO,UserVO _userVO){
		String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	   String queryKey="UPDATE_JSYCASTE_AREATYPE.HRGT_PATIENT_DTL";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   System.out.println("query"+query);
	   try{    	  
		      Sequence sq=new Sequence();
		      populateMAP.put(sq.next(), pDetailVO.getPatIsUrban());
		      populateMAP.put(sq.next(), pDetailVO.getPatCaste());
		      populateMAP.put(sq.next(), pDetailVO.getPatCrNo());
		      populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		      populateMAP.put(sq.next(),_userVO.getHospitalCode());
	   }
	   catch(Exception e){
	   	throw new HisApplicationExecutionException("PatientDAoDAO:updateexpiry()::"+e);
	   }
	   try{
	   	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	   }
	   catch(Exception e){
	   	e.printStackTrace();
	   	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
	   }     
 }*/

	public String getExpiryDate(UserVO _uservo) {
		String strExpiryDate=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			daoObj = new HisDAO("Registration","PatientDAO");
			funcIndex = daoObj.setFunction("{? = call REG_FUNCTION.FUN_GET_EXPIRY(?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, _uservo.getHospitalCode());
			daoObj.setFuncOutValue(funcIndex,1);
			
			daoObj.executeFunction(funcIndex);
			strExpiryDate = daoObj.getFuncString(funcIndex);
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("PatientDAO.getExpiryDate()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}
		return strExpiryDate;
	}
	
	public PatientVO retrieveByCrNoFromMCTS(PatientVO _patientVO, AddressVO _addressVO, UserVO _userVO) {
		Map _populateMapPatientDtl = new HashMap();
		Map _populateMapForAge = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_CRNO.FROMMCTS";
		String age = "";
		/*String queryDate = "";
		String queryKeyDate = "SELECT.GET_AGE_ON";*/
		Sequence sqAge = new Sequence();
		//_populateMapForAge.put(sqAge.next(), _patientVO.getPatCrNo());

		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), _patientVO.getPatCrNo());
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatCrNo());
		//_populateMapPatientDtl.put(sq.next(), RegistrationConfig.IS_VALID_ACTIVE);
		//_populateMapPatientDtl.put(sq.next(), _patientVO.getPatCrNo());
		//_populateMapPatientDtl.put(sq.next(), RegistrationConfig.IS_VALID_ACTIVE);
		Connection conn = super.getTransactionContext().getConnection();
		try {
			/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			System.out.println("queryDate " + queryDate);
*/
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			//System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		//System.out.println("query" + query);

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				
				////added by Prakhar
				//to find if this Cr number has been merged
				/*String queryKey1 = "SELECT.HPMRT_CRNO_MERGE_DTL.RETRIEVE_BY_CRNO";
				Sequence sq1 = new Sequence();
				
				Map populateMap=new HashMap();
				
				populateMap.put(sq1.next(), _patientVO.getPatCrNo());
				
				String query1 = HelperMethodsDAO.getQuery(filename, queryKey1);
				
				 ResultSet rs1 = HelperMethodsDAO.executeQuery(conn, query1, populateMap);
				
				 if(!rs1.next())
				 {*/
					 throw new HisRecordNotFoundException("Patient Details Not Found");
				 /*}else{
					 rs1.beforeFirst();
					 rs1.next();
					 throw new HisRecordNotFoundException("This CR Number Is Not Valid As It Has Been Merged With "+rs1.getString(1));
				 }*/
				 
				/////////////////////////////////
				
			} else {
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_patientVO, rs);
				/*ResultSet date = HelperMethodsDAO.executeQuery(conn, queryDate, _populateMapForAge);
				if (!date.next()) {
					throw new HisRecordNotFoundException("");
				} else {
					ResultSetMetaData rsmd = date.getMetaData();
					int no_of_col = rsmd.getColumnCount();
					System.out.println("no_of_col" + no_of_col);
					date.beforeFirst();
					while (date.next()) {
						for (int i = 1; i <= no_of_col; i++) {
							i++;
							age = date.getString(rsmd.getColumnName(i));
							System.out.println("age:::..." + age);
						}
					}
					System.out.println("age:::..." + age);
					_patientVO.setPatAge(age);
				}*/
				_patientVO.setPatAddress(_addressVO);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
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
	public PatientVO[] getCRNoWithAppointment(UserVO _userVO, String mode)
	{
		//String queryKey = "SELECT.HRGT_PATIENT_DTL.CR_NO1";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HAPT_APPOINTMENT_DTL(?,?::character varying,?,?,?, ?::character varying,?::character varying,?,?,?, ?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		UserMgmtConfigXmlHandler configHandler = new UserMgmtConfigXmlHandler();
		int modificationTime = configHandler.getRegistrationModificationTime();
		String modTime = String.valueOf(modificationTime);
		
		daoObj.setProcInValue(nProcIndex, "p_mode",mode,1);		
		daoObj.setProcInValue(nProcIndex, "p_gnum_no", "",2);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_id", RegistrationConfig.PAT_APT_OPD_ID,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_apt_status", mode.equalsIgnoreCase("1")?RegistrationConfig.PAT_APT_OPD_STATUS_WAITING:RegistrationConfig.PAT_APT_OPD_STATUS_REGISTERED,5);
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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
		String strProcName = "{call Pkg_Reg_View.PROC_HAPT_APPOINTMENT_DTL(?,?::character varying,?,?,?, ?::character varying,?::character varying,?,?,?, ?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		UserMgmtConfigXmlHandler configHandler = new UserMgmtConfigXmlHandler();
		int modificationTime = configHandler.getRegistrationModificationTime();
		String modTime = String.valueOf(modificationTime);
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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
			daoObj.setProcInValue(nProcIndex2, "p_gnum_apt_status", RegistrationConfig.PAT_APT_OPD_STATUS_REGISTERED,6);
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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
		String strProcName = "{call Pkg_Reg_View.PROC_HAPT_APPOINTMENT_DTL(?,?::character varying,?,?,?, ?::character varying,?::character varying,?,?,?, ?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		UserMgmtConfigXmlHandler configHandler = new UserMgmtConfigXmlHandler();
		int modificationTime = configHandler.getRegistrationModificationTime();
		String modTime = String.valueOf(modificationTime);

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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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

	/**
	## 		Modification Log		:	updatePatDeadStatus				
	##		Modify Date				: 	26-02-2015
	##		Reason	(CR/PRS)		: 	For updating isDead check from IPD Docter Desk
	##		Modify By				: 	Akash Singh
	*/
	public int updatePatDeadStatus(PatientDeathDetailVO patDeathDtlVO,UserVO userVO) 
	{		
		int x=0;
		Sequence sq = new Sequence();
		String query = "";
		Map _populateUpdateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HIPT_PATADMISSION_DTL.PAT_STATUS";
		Connection conn = super.getTransactionContext().getConnection();
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

			_populateUpdateMap.put(sq.next(), RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
			_populateUpdateMap.put(sq.next(), patDeathDtlVO.getPatAdmNo());
			_populateUpdateMap.put(sq.next(), userVO.getHospitalCode());

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
			else throw new HisDataAccessException("PatAdmissionTransDAO::while updating data " + e);
		}
		return x;
}

	
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByDemographicDetailForSearchTile(vo.registration.PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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
	public List searchPatientDetailByMobileNoForSearchTile(vo.registration.PatientVO   objPatientVO_p,  UserVO objUserVO_p) {
		
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

		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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
	public List searchPatientDetailByEmailForSearchTile(vo.registration.PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
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
	
}//end of class
