package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
//import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import vo.registration.AddressVO;
import vo.registration.DailyPatientVO;
import vo.registration.PatientModificationVO;
import vo.registration.PatientVO;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

public class PatientAuditDAO extends DataAccessObject
{

	public PatientAuditDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void create(HisDAO daoObj,DailyPatientVO dailyPatVO,PatientModificationVO objPatientModificationVO, PatientVO _patientVOOldData, UserVO _userVO, String mode)
	{
		//String queryKey = "INSERT.HRGT_PATIENT_AUDIT_DTL";
    	String strErr = "";
		int nProcIndex2 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_patient_audit_dtl(?,?::numeric,?::numeric,?,?::numeric,?,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?::numeric,?::numeric,?,?,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?::numeric,?,?,?::numeric,?,?,?::numeric,?::numeric,?,?,?::numeric,?,?,?)}";
			
			HelperMethods.setNullToEmpty(_patientVOOldData);
			HelperMethods.setNullToEmpty(objPatientModificationVO);
			
			nProcIndex2 = daoObj.setProcedure(strProcName1);
			
			/*daoObj.setProcInValue(nProcIndex2, "p_modeVal", mode,1);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", dailyPatVO.getPatCrNo().trim()==""?"0": dailyPatVO.getPatCrNo().trim(),2);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", dailyPatVO.getPrevCrNo().trim()==""?"0": dailyPatVO.getPrevCrNo().trim(),3);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", dailyPatVO.getPatDOB(),4);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", dailyPatVO.getIsActualDob()==""?"0": dailyPatVO.getIsActualDob(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", dailyPatVO.getPatFirstName(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", dailyPatVO.getPatMiddleName(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", dailyPatVO.getPatLastName(),8);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", dailyPatVO.getPatGenderCode()==""?"0": dailyPatVO.getPatGenderCode(),9);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", dailyPatVO.getPatMaritalStatusCode()==""?"0": dailyPatVO.getPatMaritalStatusCode(),10);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", dailyPatVO.getPatStatusCode()==""?"0": dailyPatVO.getPatStatusCode(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", dailyPatVO.getPatIdMark1(),12);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", dailyPatVO.getPatGuardianName(),13);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", dailyPatVO.getPatMotherName(),14);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", dailyPatVO.getPatRegCatCode()==""?"0": dailyPatVO.getPatRegCatCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", dailyPatVO.getPatPrimaryCatCode()==""?"0": dailyPatVO.getPatPrimaryCatCode(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date",dailyPatVO.getRegisterDate(),17);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",18);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", dailyPatVO.getSeatId()==""?"0": dailyPatVO.getSeatId(),19);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", dailyPatVO.getIsMLC()==""?"0": dailyPatVO.getIsMLC(),20);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", dailyPatVO.getIsValid()==""?"0": dailyPatVO.getIsValid(),21);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", dailyPatVO.getIsUnknown()==""?"0": dailyPatVO.getIsUnknown(),22);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", dailyPatVO.getPatMonthlyIncome()==""?"0": dailyPatVO.getPatMonthlyIncome(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", dailyPatVO.getPatIsUrban()==""?"0": dailyPatVO.getPatIsUrban(),24);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", dailyPatVO.getIsFree()==""?"0": dailyPatVO.getIsFree(),25);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVOOldData.getDesigSemester()==""?"0": _patientVOOldData.getDesigSemester(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVOOldData.getDeptStaffStudent()==""?"0": _patientVOOldData.getDeptStaffStudent(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", dailyPatVO.getPatAddress().getPatAddMobileNo(),29);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", dailyPatVO.getPatIdNo(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", dailyPatVO.getIsReferred()==""?"0": dailyPatVO.getIsReferred(),31);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", dailyPatVO.getPatReligionCode()==""?"0": dailyPatVO.getPatReligionCode(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", dailyPatVO.getPatAge(),33);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", dailyPatVO.getPatAddress().getPatAddCountryCode(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", dailyPatVO.getIsImageUploaded()==""?"0": dailyPatVO.getIsImageUploaded(),35);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", dailyPatVO.getPatHusbandName(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", dailyPatVO.getPatNationalId(),37);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", dailyPatVO.getPatBloodGroup()==""?"0": dailyPatVO.getPatBloodGroup(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", dailyPatVO.getIsNewBorn()==""?"0": dailyPatVO.getIsNewBorn(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", dailyPatVO.getPatMotherCrNo()==""?"0": dailyPatVO.getPatMotherCrNo(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_exp_date", dailyPatVO.getExpiryDate(),41);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",dailyPatVO.getPatOccupation(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",dailyPatVO.getPatFatherOccupation(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",dailyPatVO.getPatHusbandOccupation(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",dailyPatVO.getPatNickName(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",dailyPatVO.getPatCardNo(),46);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode()==""?"0": _userVO.getHospitalCode(),47);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", dailyPatVO.getPatIdMark2(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno", _patientVOOldData.getVerificationDocumentId(),49);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", dailyPatVO.getIsBroughtDead(),50);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_remark", "",51);
			daoObj.setProcInValue(nProcIndex2, "gdt_lstmod_date", dailyPatVO.getLastModifyDate(),52);
			daoObj.setProcInValue(nProcIndex2, "gnum_lstmod_seatid", _userVO.getSeatId()==""?"0":  _userVO.getSeatId(),53);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_change_req_by", objPatientModificationVO.getRequestBy()==""?"0": objPatientModificationVO.getRequestBy(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_change_reqby_name", objPatientModificationVO.getRequestByName(),55);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_change_reqby_add", objPatientModificationVO.getRequestByAddress(),56);
			daoObj.setProcInValue(nProcIndex2, "gnum_relation_code", objPatientModificationVO.getRequestRelation()==""?"0": objPatientModificationVO.getRequestRelation(),57);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_constable_badgeno", objPatientModificationVO.getConstableBadgeNo(),58);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_constable_desig", objPatientModificationVO.getConstableDesig(),59);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,60);*/
			
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", mode,1);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientVOOldData.getPatCrNo().trim()==""?"0": _patientVOOldData.getPatCrNo().trim(),2);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_prev_puk", _patientVOOldData.getPrevCrNo().trim()==""?"0": _patientVOOldData.getPrevCrNo().trim(),3);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_dob", _patientVOOldData.getPatDOB(),4);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_actual_dob", _patientVOOldData.getIsActualDob()==""?"0": _patientVOOldData.getIsActualDob(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fname", _patientVOOldData.getPatFirstName(),6);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_mname", _patientVOOldData.getPatMiddleName(),7);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_lname", _patientVOOldData.getPatLastName(),8);
			daoObj.setProcInValue(nProcIndex2, "gnum_gender_code", _patientVOOldData.getPatGenderCode()==""?"0": _patientVOOldData.getPatGenderCode(),9);
			daoObj.setProcInValue(nProcIndex2, "gnum_marital_status_code", _patientVOOldData.getPatMaritalStatusCode()==""?"0": _patientVOOldData.getPatMaritalStatusCode(),10);
			daoObj.setProcInValue(nProcIndex2, "hgnum_pat_status_code", _patientVOOldData.getPatStatusCode()==""?"0": _patientVOOldData.getPatStatusCode(),11);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark1", _patientVOOldData.getPatIdMark1(),12);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_father_name", _patientVOOldData.getPatGuardianName(),13);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_momname", _patientVOOldData.getPatMotherName(),14);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_reg_cat_code", _patientVOOldData.getPatRegCatCode().equals("")?"0": _patientVOOldData.getPatRegCatCode(),15);
			daoObj.setProcInValue(nProcIndex2, "hgnum_patient_cat_code", _patientVOOldData.getPatPrimaryCatCode().equals("")?"0": _patientVOOldData.getPatPrimaryCatCode(),16);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_register_date",dailyPatVO.getRegisterDate(),17);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",18);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", dailyPatVO.getSeatId(),19);//.equals("")?"0": _patientVOOldData.getSeatId(),19);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_mlc", _patientVOOldData.getIsMLC()==""?"0": _patientVOOldData.getIsMLC(),20);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientVOOldData.getIsValid().equals("")?"1": _patientVOOldData.getIsValid(),21);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isunknown", _patientVOOldData.getIsUnknown()==""?"0": _patientVOOldData.getIsUnknown(),22);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_month_income", _patientVOOldData.getPatMonthlyIncome()==""?"0": _patientVOOldData.getPatMonthlyIncome(),23);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_urban", _patientVOOldData.getPatIsUrban().equals("")?"-1": _patientVOOldData.getPatIsUrban(),24);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isreg_free", _patientVOOldData.getIsFree()==""?"0": _patientVOOldData.getIsFree(),25);
			daoObj.setProcInValue(nProcIndex2, "gnum_desig_semester", _patientVOOldData.getDesigSemester()==""?"0": _patientVOOldData.getDesigSemester(),26);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_dept_staff_stud", _patientVOOldData.getDeptStaffStudent()==""?"0": _patientVOOldData.getDeptStaffStudent(),27);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),28);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_contact_no", _patientVOOldData.getPatAddress().getPatAddMobileNo(),29);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_id_no", _patientVOOldData.getPatIdNo(),30);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_ref", _patientVOOldData.getIsReferred()==""?"0": _patientVOOldData.getIsReferred(),31);
			daoObj.setProcInValue(nProcIndex2, "gnum_religion_code", _patientVOOldData.getPatReligionCode()==""?"0": _patientVOOldData.getPatReligionCode(),32);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_age", _patientVOOldData.getPatAge(),33);
			daoObj.setProcInValue(nProcIndex2, "gnum_nationality_code", _patientVOOldData.getPatAddress().getPatAddCountryCode(),34);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_imageuploaded", _patientVOOldData.getIsImageUploaded()==""?"0": _patientVOOldData.getIsImageUploaded(),35);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_spousename", _patientVOOldData.getPatHusbandName(),36);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_national_id", _patientVOOldData.getPatNationalId(),37);
			daoObj.setProcInValue(nProcIndex2, "hbnum_bldgrp_code", _patientVOOldData.getPatBloodGroup()==""?"0": _patientVOOldData.getPatBloodGroup(),38);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_isnewborn", _patientVOOldData.getIsNewBorn()==""?"0": _patientVOOldData.getIsNewBorn(),39);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_mother_puk", _patientVOOldData.getPatMotherCrNo()==""?"0": _patientVOOldData.getPatMotherCrNo(),40);
			daoObj.setProcInValue(nProcIndex2, "hrgdt_exp_date", _patientVOOldData.getExpiryDate(),41);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_pat_occupation",_patientVOOldData.getPatOccupation(),42);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fat_occupation",_patientVOOldData.getPatFatherOccupation(),43);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_hus_occupation",_patientVOOldData.getPatHusbandOccupation(),44);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_nickname",_patientVOOldData.getPatNickName(),45);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_card_no",_patientVOOldData.getPatCardNo(),46);	
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",_userVO.getHospitalCode()==""?"0": _userVO.getHospitalCode(),47);	
			daoObj.setProcInValue(nProcIndex2, "hrgstr_idmark2", _patientVOOldData.getPatIdMark2(),48);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_verification_slno", _patientVOOldData.getVerificationDocumentId(),49);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_broughtdead", _patientVOOldData.getIsBroughtDead(),50);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_remark", "",51);
			daoObj.setProcInValue(nProcIndex2, "gdt_lstmod_date", dailyPatVO.getLastModifyDate(),52);
			daoObj.setProcInValue(nProcIndex2, "gnum_lstmod_seatid", _userVO.getSeatId()==""?"0":  _userVO.getSeatId(),53);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_change_req_by", objPatientModificationVO.getRequestBy()==""?"0": objPatientModificationVO.getRequestBy(),54);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_change_reqby_name", objPatientModificationVO.getRequestByName(),55);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_change_reqby_add", objPatientModificationVO.getRequestByAddress(),56);
			daoObj.setProcInValue(nProcIndex2, "gnum_relation_code", objPatientModificationVO.getRequestRelation().equals("")?"0": objPatientModificationVO.getRequestRelation(),57);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_constable_badgeno", objPatientModificationVO.getConstableBadgeNo(),58);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_constable_desig", objPatientModificationVO.getConstableDesig(),59);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,60);
			
			daoObj.execute(nProcIndex2,1);			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			//strErr = daoObj.getString(nProcIndex1, "err");
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
		}
	}

	public void populateMapWithVO(PatientVO _patientVO, Map _populateMAP, UserVO _userVO) throws SQLException
	{

		System.out.println("inside populateMap");

		Sequence sq = new Sequence();
		_populateMAP.put(sq.next(), _patientVO.getPatCrNo());
		_populateMAP.put(sq.next(), _patientVO.getPrevCrNo());
		_populateMAP.put(sq.next(), _patientVO.getPatDOB());
		_populateMAP.put(sq.next(), _patientVO.getIsActualDob());
		_populateMAP.put(sq.next(), _patientVO.getPatFirstName());
		// _populateMAP.put(sq.next(), _patientVO.getPatSalutationCode());
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
		_populateMAP.put(sq.next(), _patientVO.getRegisterDate());
		_populateMAP.put(sq.next(), _patientVO.getEntryDate());
		// _populateMAP.put(sq.next(), _patientVO.getDepartmentCode());
		// _populateMAP.put(sq.next(), _patientVO.getIsLock());
		// _populateMAP.put(sq.next(), _patientVO.getIsLockReason());
		// _populateMAP.put(sq.next(), _patientVO.getEntryDate());
		_populateMAP.put(sq.next(), _patientVO.getSeatId());
		_populateMAP.put(sq.next(), _patientVO.getIsMLC());
		_populateMAP.put(sq.next(), _patientVO.getIsValid());
		_populateMAP.put(sq.next(), _patientVO.getIsUnknown());
		_populateMAP.put(sq.next(), _patientVO.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), _patientVO.getPatIsUrban());
		// _populateMAP.put(sq.next(), _patientVO.getIsNProgram());
		_populateMAP.put(sq.next(), _patientVO.getIsFree());
		// _populateMAP.put(sq.next(), _patientVO.getCurrentDepartmentCode());
		_populateMAP.put(sq.next(), _patientVO.getDesigSemester());
		_populateMAP.put(sq.next(), _patientVO.getDeptStaffStudent());
		// _populateMAP.put(sq.next(), _patientVO.getPatPrimaryCatCode());
		// _populateMAP.put(sq.next(), _patientVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _patientVO.getSystemIPAddress());
		_populateMAP.put(sq.next(), _patientVO.getPatAddress().getPatAddContactNo());
		// _populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalCode());
		// _populateMAP.put(sq.next(), _patientVO.getPatRefHospitalName());
		_populateMAP.put(sq.next(), _patientVO.getPatIdNo());
		// _populateMAP.put(sq.next(), _patientVO.getPatAmountCollected());
		_populateMAP.put(sq.next(), _patientVO.getIsReferred());
		// _populateMAP.put(sq.next(), _patientVO.getEmergencyType());
		// _populateMAP.put(sq.next(), _patientVO.getPatAddress().getPatAddTypeCode());
		_populateMAP.put(sq.next(), _patientVO.getPatReligionCode());
		// _populateMAP.put(sq.next(), _patientVO.getPatRefDoctor());
		_populateMAP.put(sq.next(), _patientVO.getPatAge());
		_populateMAP.put(sq.next(), _patientVO.getPatNationalityCode());
		// _populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalCrno());
		// _populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalDept());
		// _populateMAP.put(sq.next(), _patientVO.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), _patientVO.getIsImageUploaded());
		_populateMAP.put(sq.next(), _patientVO.getPatHusbandName());
		_populateMAP.put(sq.next(), _patientVO.getPatNationalId());
		_populateMAP.put(sq.next(), _patientVO.getPatBloodGroup());
		_populateMAP.put(sq.next(), _patientVO.getIsNewBorn());
		_populateMAP.put(sq.next(), _patientVO.getPatMotherCrNo());
		_populateMAP.put(sq.next(), _patientVO.getExpiryDate());
		_populateMAP.put(sq.next(), _patientVO.getPatOccupation());
		_populateMAP.put(sq.next(), _patientVO.getPatFatherOccupation());
		_populateMAP.put(sq.next(), _patientVO.getPatHusbandOccupation());
		_populateMAP.put(sq.next(), _patientVO.getPatNickName());
		_populateMAP.put(sq.next(), _patientVO.getPatCardNo());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		_populateMAP.put(sq.next(), _patientVO.getPatIdMark2());
		_populateMAP.put(sq.next(), _patientVO.getVerificationDocumentId());
		_populateMAP.put(sq.next(), _patientVO.getIsBroughtDead());
		_populateMAP.put(sq.next(), _userVO.getSeatId());
		_populateMAP.put(sq.next(), _patientVO.getRequestBy());
		_populateMAP.put(sq.next(), _patientVO.getRequestByName());
		_populateMAP.put(sq.next(), _patientVO.getRequestByAddress());
		_populateMAP.put(sq.next(), _patientVO.getRequestRelation());
		_populateMAP.put(sq.next(), _patientVO.getConstableBadgeNo());
		_populateMAP.put(sq.next(), _patientVO.getConstableDesig());
	}
	
public PatientVO findRow(PatientVO objPatientVO_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_AUDIT_DTL(?,?,?,?,?,?)}";//changed fr patient vist by deepika
		int nProcIndex = 0;
		String strErr="",strMode = "1";
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


		try {
			if (!rs.next()) {
				objPatientVO_p.setFindRow("0");
			} else {
				rs.beforeFirst();
				objPatientVO_p.setFindRow("1");
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

}
