package registration.dao;

import java.util.ArrayList;
import java.util.List;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import javax.sql.rowset.WebRowSet;

import vo.registration.MlcParameterDtlVO;
import vo.registration.MlcVO;

public class MlcDAO extends DataAccessObject
{
	public MlcDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}	
	
	
	/***
	 * 
	 * @param strMode_p
	 * @param objHisDAO_p
	 * @param objMlcVO_p
	 * @param objUserVO_p
	 * @param strModeExecuteProc_p is 1 for BatchExecution And Other For Individual Execution of Procedure 
	 * @return MlcVO
	 */
	public MlcVO saveMlcPatientDtl(String strMode_p, HisDAO objHisDAO_p, MlcVO objMlcVO_p, UserVO objUserVO_p, String strModeExecuteProc_p)
	{
		//String queryKey = "INSERT.HRGT_PATIENT_MLC_DTL";
    	String strErr = "";
		int nProcIndex1 = 0, index=1;
		String strProcName1="";
		try 
		{
			System.out.println("------------------------------");
			System.out.println("MlcDAO :: saveMlcPatientDtl()");
			////////////////////////////////////////////////////////   /************ 1-5 **********/	/******** 6-10 ***********/		/************* 11-15 ***************/	 /********* 16-20 *********/	/***************** 21-25 *******************/	/********* 26-30 **********/	/***** 31-35 ******/	/***** 36-40 ******/	/***** 41-45 ******/	/************** 46-50 *************/	/***** 51-55 ******/	/* 56-60 */		/**** 61-65 *****/		/********* 66-70 *********/		/* 71-75*/	/************* 76-80 **************/	/**** 81-85 ******/		/* 86-90*/		/* 91-95*/	/*96-98*/
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_mlc_dtl(?,?::numeric,?::numeric,?,?,	?::numeric,?::numeric,?,?,?,	?::numeric,?,?::numeric,?::numeric,?,	?::numeric,?,?,?,?::numeric,	?,?::numeric,?::numeric,?::numeric,?::numeric,	?,?,?,?::numeric,?::numeric,	?,?::numeric,?,?,?,		?,?,?,?,?::numeric,		?,?,?,?,?::numeric,		?,?::numeric,?::numeric,?,?::numeric,	?,?,?,?,?::numeric,		?,?,?,?,?,		?,?,?,?::numeric,?,		?,?,?::numeric,?::numeric,?,	?,?,?,?,?,	?::numeric,?,?,?::numeric,?::numeric,	?::numeric,?,?,?,?,		?,?,?,?,?,		?,?,?,?,?,	?,?,?)}";
			//strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_mlc_dtl_a(?,?::numeric,?::numeric,?,?,	?::numeric,?::numeric,?,?,?,	?)}";
			
			
			HelperMethods.setNullToEmpty(objMlcVO_p);
			HelperMethods.setNullToEmpty(objMlcVO_p.getPatAddress());
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", strMode_p, index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objMlcVO_p.getPatCrNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objMlcVO_p.getEpisodeCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob", objMlcVO_p.getPatDOB(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no", objMlcVO_p.getSerialNo().isEmpty()?"0":objMlcVO_p.getSerialNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgbl_is_actual_dob", objMlcVO_p.getIsActualDob(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objMlcVO_p.getEpisodeVisitNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname", objMlcVO_p.getPatFirstName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname", objMlcVO_p.getPatMiddleName(), index++);
			//objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname", objMlcVO_p.getPatLastName().isEmpty()?"default":objMlcVO_p.getPatLastName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname", objMlcVO_p.getPatLastName(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_document_code", objMlcVO_p.getPatDocType(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_gender_code", objMlcVO_p.getPatGenderCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", objMlcVO_p.getPatMaritalStatusCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_appellation_code", objMlcVO_p.getPatAppellationCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", objMlcVO_p.getPatIdMark1(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_suffix_code", objMlcVO_p.getSuffixCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_father_name", objMlcVO_p.getPatGuardianName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_momname", objMlcVO_p.getPatMotherName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_name_type", objMlcVO_p.getPatNameType(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", objMlcVO_p.getPatPrimaryCatCode(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date", objMlcVO_p.getEntryDate(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid", objMlcVO_p.getIsValid(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", objMlcVO_p.getIsUnknown(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_month_income", objMlcVO_p.getPatMonthlyIncome(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", objMlcVO_p.getPatBirthPlace(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_id_no", objMlcVO_p.getPatIdNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_occupation_code", objMlcVO_p.getPatOccupation(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_religion_code", objMlcVO_p.getPatReligionCode(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_age", objMlcVO_p.getPatAge(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_nationality", objMlcVO_p.getPatNationality(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_spousename", objMlcVO_p.getPatHusbandName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_address_line1", objMlcVO_p.getPatAddHNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_national_id", objMlcVO_p.getPatNationalId(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality1", objMlcVO_p.getPatAddStreet(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality2", objMlcVO_p.getPatAddLandMarks(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city_location", objMlcVO_p.getPatAddCityLoc(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city", objMlcVO_p.getPatAddCity(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_pincode", objMlcVO_p.getPatAddPIN(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_district", objMlcVO_p.getPatAddDistrict(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_state_name", objMlcVO_p.getPatAddStateName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_country_name", objMlcVO_p.getPatAddCountry(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname", objMlcVO_p.getPatNickName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", objMlcVO_p.getPatIsUrban(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_no", objMlcVO_p.getPatCardNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_local", objMlcVO_p.getPatIsLocal(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", objMlcVO_p.getPatIdMark2(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_phone_owner", objMlcVO_p.getPatAddPhoneOwner(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no", objMlcVO_p.getPatAddMobileNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_phone_no", objMlcVO_p.getPatAddPhoneNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_caste_code", objMlcVO_p.getPatCasteCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_email_id", objMlcVO_p.getPatAddEmailId(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_education_code", objMlcVO_p.getPatEducationCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_register_date", objMlcVO_p.getRegisterDate(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", objMlcVO_p.getPatFirstNameInMultiLang(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", objMlcVO_p.getPatMiddleNameInMultiLang(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", objMlcVO_p.getPatLastNameInMultiLang(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname_local", objMlcVO_p.getPatNickNameInMultiLang(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_state_code", objMlcVO_p.getPatAddStateCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_district_code", objMlcVO_p.getPatAddDistrictCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_country_code", objMlcVO_p.getPatAddCountryCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", objMlcVO_p.getPrevCrNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", objMlcVO_p.getPatMlcNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_mlc_date", objMlcVO_p.getMlcDate(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgtm_mlc_time", objMlcVO_p.getMlcTime(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_istransfer", objMlcVO_p.getIsTransfer(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", objMlcVO_p.getIsReferred(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mlc_type", objMlcVO_p.getMlcType(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mlc_bookno", objMlcVO_p.getMlcBookNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mlc_pageno", objMlcVO_p.getMlcPageNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_status_code", objMlcVO_p.getPatMlcStatusCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_patient_condition", objMlcVO_p.getPatCondition(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_daignosis", objMlcVO_p.getDiagnosis(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isfit", objMlcVO_p.getFitnessStatus(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_fit_datetime", objMlcVO_p.getFitDateTime(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mlc_remarks", objMlcVO_p.getMlcRemark(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_imageuploaded", objMlcVO_p.getIsImageUploaded(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_dead", objMlcVO_p.getPatIsDead(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", objMlcVO_p.getIsBroughtDead(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_psstr_refer_doc_code", objMlcVO_p.getReferDoctorCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_refer_doc_remarks", objMlcVO_p.getReferDocotorRemarks(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", objMlcVO_p.getPatRefGnctdHospitalCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", objMlcVO_p.getPatRefDoctor(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", objMlcVO_p.getPatAddTypeCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", objMlcVO_p.getPatRefGnctdHospitalCrno(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_final_opinion", objMlcVO_p.getFinalOpinion(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept", objMlcVO_p.getPatRefGnctdHospitalDept(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_provisional_opinion", objMlcVO_p.getProvisionalOpinion(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept_unit", objMlcVO_p.getPatRefGnctdHospitalDeptUnit(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_handover_datetime", objMlcVO_p.getHandoverDateTime(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_officer_name", objMlcVO_p.getHandoverOffName(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_officer_desig", objMlcVO_p.getHandoverOffDesg(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_officer_badgeno", objMlcVO_p.getHandoverOffBadgeNo(), index++);
			//objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mlc_status", objMlcVO_p.getMlcStatus(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mlc_status", objMlcVO_p.getPatMlcStatusCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_psstr_cmo_code", objMlcVO_p.getCmoCode(), index++);
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,index++);
			
			
			//////////////////////Printing Values on Console	////////////////////////////////
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+ objMlcVO_p.getPatCrNo());
			System.out.println("p_hrgnum_episode_code :"+ objMlcVO_p.getEpisodeCode());
			System.out.println("p_hrgdt_dob :"+ objMlcVO_p.getPatDOB());
			System.out.println("p_hrgnum_s_no :"+ objMlcVO_p.getSerialNo());
			System.out.println("p_hrgbl_is_actual_dob :"+ objMlcVO_p.getIsActualDob());
			System.out.println("p_hrgnum_visit_no :"+ objMlcVO_p.getEpisodeVisitNo());
			System.out.println("p_hrgstr_fname :"+ objMlcVO_p.getPatFirstName());
			System.out.println("p_hrgstr_mname :"+ objMlcVO_p.getPatMiddleName());
			System.out.println("p_hrgstr_lname :"+ objMlcVO_p.getPatLastName());
			
			System.out.println("p_gnum_document_code :"+ objMlcVO_p.getPatDocType());
			System.out.println("p_gstr_gender_code :"+ objMlcVO_p.getPatGenderCode());
			System.out.println("p_gnum_marital_status_code :"+ objMlcVO_p.getPatMaritalStatusCode());
			System.out.println("p_gnum_appellation_code :"+ objMlcVO_p.getPatAppellationCode());
			System.out.println("p_hrgstr_idmark1 :"+ objMlcVO_p.getPatIdMark1());
			System.out.println("p_gnum_suffix_code :"+ objMlcVO_p.getSuffixCode());
			System.out.println("p_hrgstr_father_name :"+ objMlcVO_p.getPatGuardianName());
			System.out.println("p_hrgstr_momname :"+ objMlcVO_p.getPatMotherName());
			System.out.println("p_hrgstr_name_type :"+ objMlcVO_p.getPatNameType());
			System.out.println("p_hgnum_patient_cat_code :"+ objMlcVO_p.getPatPrimaryCatCode());
			
			System.out.println("p_gdt_entry_date :"+ objMlcVO_p.getEntryDate());
			System.out.println("p_gnum_seat_id :"+ objUserVO_p.getSeatId());
			System.out.println("p_gnum_isvalid :"+ objMlcVO_p.getIsValid());
			System.out.println("p_hrgnum_isunknown :"+ objMlcVO_p.getIsUnknown());
			System.out.println("p_hrgnum_month_income :"+ objMlcVO_p.getPatMonthlyIncome());
			System.out.println("p_hrgstr_birth_place :"+ objMlcVO_p.getPatBirthPlace());
			System.out.println("p_hrgstr_ip_add :"+ objUserVO_p.getIpAddress());
			System.out.println("p_hrgnum_id_no :"+ objMlcVO_p.getPatIdNo());
			System.out.println("p_gnum_occupation_code :"+ objMlcVO_p.getPatOccupation());
			System.out.println("p_gnum_religion_code :"+ objMlcVO_p.getPatReligionCode());
			
			System.out.println("p_hrgstr_age :"+ objMlcVO_p.getPatAge());
			System.out.println("p_gnum_nationality :"+ objMlcVO_p.getPatNationality());
			System.out.println("p_hrgstr_spousename :"+ objMlcVO_p.getPatHusbandName());
			System.out.println("p_hrgstr_address_line1 :"+ objMlcVO_p.getPatAddHNo());
			System.out.println("p_hrgstr_national_id :"+ objMlcVO_p.getPatNationalId());
			System.out.println("p_hrgstr_sub_locality1 :"+ objMlcVO_p.getPatAddStreet());
			System.out.println("p_hrgstr_sub_locality2 :"+ objMlcVO_p.getPatAddLandMarks());
			System.out.println("p_hrgstr_city_location :"+ objMlcVO_p.getPatAddCityLoc());
			System.out.println("p_hrgstr_city :"+ objMlcVO_p.getPatAddCity());
			System.out.println("p_hrgnum_pincode :"+ objMlcVO_p.getPatAddPIN());
			
			System.out.println("p_hrgstr_district :"+ objMlcVO_p.getPatAddDistrict());
			System.out.println("p_hrgstr_state_name :"+ objMlcVO_p.getPatAddStateName());
			System.out.println("p_gstr_country_name :"+ objMlcVO_p.getPatAddCountry());
			System.out.println("p_hrgstr_nickname :"+ objMlcVO_p.getPatNickName());
			System.out.println("p_hrgnum_is_urban :"+ objMlcVO_p.getPatIsUrban());
			System.out.println("p_hrgstr_card_no :"+ objMlcVO_p.getPatCardNo());
			System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
			System.out.println("p_hrgnum_is_local :"+ objMlcVO_p.getPatIsLocal());
			System.out.println("p_hrgstr_idmark2 :"+ objMlcVO_p.getPatIdMark2());
			System.out.println("p_hrgnum_phone_owner :"+ objMlcVO_p.getPatAddPhoneOwner());
			
			System.out.println("p_hrgstr_mobile_no :"+ objMlcVO_p.getPatAddMobileNo());
			System.out.println("p_hrgstr_phone_no :"+ objMlcVO_p.getPatAddPhoneNo());
			System.out.println("p_gstr_caste_code :"+ objMlcVO_p.getPatCasteCode());
			System.out.println("p_hrgstr_email_id :"+ objMlcVO_p.getPatAddEmailId());
			System.out.println("p_gnum_education_code :"+ objMlcVO_p.getPatEducationCode());
			System.out.println("p_hrgdt_register_date :"+ objMlcVO_p.getRegisterDate());
			System.out.println("p_hrgstr_fname_local :"+ objMlcVO_p.getPatFirstNameInMultiLang());
			System.out.println("p_hrgstr_mname_local :"+ objMlcVO_p.getPatMiddleNameInMultiLang());
			System.out.println("p_hrgstr_lname_local :"+ objMlcVO_p.getPatLastNameInMultiLang());
			System.out.println("p_hrgstr_nickname_local :"+ objMlcVO_p.getPatNickNameInMultiLang());
			
			System.out.println("p_gnum_state_code :"+ objMlcVO_p.getPatAddStateCode());
			System.out.println("p_gnum_district_code :"+ objMlcVO_p.getPatAddDistrictCode());
			System.out.println("p_gstr_country_code :"+ objMlcVO_p.getPatAddCountryCode());
			System.out.println("p_hrgstr_prev_puk :"+ objMlcVO_p.getPrevCrNo());
			System.out.println("p_hrgstr_mlc_no :"+ objMlcVO_p.getPatMlcNo());
			System.out.println("p_hrgdt_mlc_date :"+ objMlcVO_p.getMlcDate());
			System.out.println("p_hrgtm_mlc_time :"+ objMlcVO_p.getMlcTime());
			System.out.println("p_hrgnum_istransfer :"+ objMlcVO_p.getIsTransfer());
			System.out.println("p_hrgnum_is_ref :"+ objMlcVO_p.getIsReferred());
			System.out.println("p_hrgnum_mlc_type :"+ objMlcVO_p.getMlcType());
			
			System.out.println("p_hrgstr_mlc_bookno :"+ objMlcVO_p.getMlcBookNo());
			System.out.println("p_hrgnum_mlc_pageno :"+ objMlcVO_p.getMlcPageNo());
			System.out.println("p_hrgnum_status_code :"+ objMlcVO_p.getPatMlcStatusCode());
			System.out.println("p_hrgstr_patient_condition :"+ objMlcVO_p.getPatCondition());
			System.out.println("p_hrgstr_daignosis :"+ objMlcVO_p.getDiagnosis());
			System.out.println("p_hrgnum_isfit :"+ objMlcVO_p.getFitnessStatus());
			System.out.println("p_hrgdt_fit_datetime :"+ objMlcVO_p.getFitDateTime());
			System.out.println("p_hrgstr_mlc_remarks :"+ objMlcVO_p.getMlcRemark());
			System.out.println("p_hrgnum_is_imageuploaded :"+ objMlcVO_p.getIsImageUploaded());
			System.out.println("p_hrgnum_is_dead :"+ objMlcVO_p.getPatIsDead());
			
			System.out.println("p_hrgnum_is_broughtdead :"+ objMlcVO_p.getIsBroughtDead());
			System.out.println("p_psstr_refer_doc_code :"+ objMlcVO_p.getReferDoctorCode());
			System.out.println("p_hrgstr_refer_doc_remarks :"+ objMlcVO_p.getReferDocotorRemarks());
			System.out.println("p_hrgnum_ext_hospital_code :"+ objMlcVO_p.getPatRefGnctdHospitalCode());
			System.out.println("p_hrgstr_ext_hosp_doct_name :"+ objMlcVO_p.getPatRefDoctor());
			System.out.println("p_hrgnum_add_type_code :"+ objMlcVO_p.getPatAddTypeCode());
			System.out.println("p_hrgnum_ext_hospital_crno :"+ objMlcVO_p.getPatRefGnctdHospitalCrno());
			System.out.println("p_hrgstr_final_opinion :"+ objMlcVO_p.getFinalOpinion());
			System.out.println("p_hrgnum_ext_hospital_dept :"+ objMlcVO_p.getPatRefGnctdHospitalDept());
			System.out.println("p_hrgstr_provisional_opinion :"+ objMlcVO_p.getProvisionalOpinion());
			
			System.out.println("p_hrgnum_ext_hospital_dept_unit :"+ objMlcVO_p.getPatRefGnctdHospitalDeptUnit());
			System.out.println("p_hrgdt_handover_datetime :"+ objMlcVO_p.getHandoverDateTime());
			System.out.println("p_hrgstr_officer_name :"+ objMlcVO_p.getHandoverOffName());
			System.out.println("p_hrgstr_officer_desig :"+ objMlcVO_p.getHandoverOffDesg());
			System.out.println("p_hrgstr_officer_badgeno :"+ objMlcVO_p.getHandoverOffBadgeNo());
			//System.out.println("p_hrgnum_mlc_status :"+ objMlcVO_p.getMlcStatus());
			System.out.println("p_hrgnum_mlc_status :"+ objMlcVO_p.getPatMlcStatusCode());
			System.out.println("p_psstr_cmo_code :"+ objMlcVO_p.getCmoCode());
			//////////////////////////////////////////////////////////////////////////////////////


			if(!strModeExecuteProc_p.isEmpty() && strModeExecuteProc_p.equals("1")){
				objHisDAO_p.execute(nProcIndex1,1);
			}else{
				objHisDAO_p.executeProcedureByPosition(nProcIndex1);
				strErr = objHisDAO_p.getString(nProcIndex1, "err");
			}		
			
			if (!strErr.isEmpty()) 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objMlcVO_p;
	}
	
	public MlcVO getMlcDtls(MlcVO objMlcVO_p, UserVO objUserVO_p, String strMode_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_patient_mlc_dtl(?,?,?,?,?, ?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		MlcVO objMlcVO =new MlcVO();
		try 
		{
			System.out.println("MlcDAO :: getMlcDtls()");
			daoObj = new HisDAO("Registration","MlcDAO");
			HelperMethods.setNullToEmpty(objMlcVO_p);
			nProcIndex = daoObj.setProcedure(strProcName);
			
			////////////////////////////////////////////////////////
			System.out.println("p_mode :"+ strMode_p);
			System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
			System.out.println("p_hrgnum_puk :"+ objMlcVO_p.getPatCrNo());
			System.out.println("p_hrgnum_episode_code :"+ objMlcVO_p.getEpisodeCode());
			System.out.println("p_hrgnum_visit_no :"+ objMlcVO_p.getEpisodeVisitNo());
			System.out.println("p_hrgstr_mlc_no :"+ objMlcVO_p.getPatMlcNo());
			////////////////////////////////////////////////////////
			
			daoObj.setProcInValue(nProcIndex, "p_mode", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", objMlcVO_p.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_episode_code", objMlcVO_p.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_no", objMlcVO_p.getEpisodeVisitNo(),5);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_mlc_no", objMlcVO_p.getPatMlcNo(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(e.getMessage());
		}

		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No MLC Detail Found");
			}
			else
			{
				System.out.println("rs.size :"+rs.size());
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(objMlcVO, rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("MlcDAO: getMlcDtls :: " + e);
		}
		return objMlcVO;
	}
	
	public List<MlcVO> getMlcDtlList(MlcVO objMlcVO_p, UserVO objUserVO_p, String strMode_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_patient_mlc_dtl(?,?,?,?,?, ?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		List lstMlcVO = new ArrayList<MlcVO>();
		try 
		{
			System.out.println("MlcDAO :: getMlcDtlList()");
			daoObj = new HisDAO("Registration","MlcDAO");
			HelperMethods.setNullToEmpty(objMlcVO_p);
			nProcIndex = daoObj.setProcedure(strProcName);
			
			////////////////////////////////////////////////////////
			System.out.println("p_mode :"+ strMode_p);
			System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
			System.out.println("p_hrgnum_puk :"+ objMlcVO_p.getPatCrNo());
			System.out.println("p_hrgnum_episode_code :"+ objMlcVO_p.getEpisodeCode());
			System.out.println("p_hrgnum_visit_no :"+ objMlcVO_p.getEpisodeVisitNo());
			System.out.println("p_hrgstr_mlc_no :"+ objMlcVO_p.getPatMlcNo());
			////////////////////////////////////////////////////////
			
			daoObj.setProcInValue(nProcIndex, "p_mode", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", objMlcVO_p.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_episode_code", objMlcVO_p.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_no", objMlcVO_p.getEpisodeVisitNo(),5);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_mlc_no", objMlcVO_p.getPatMlcNo(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(e.getMessage());
		}

		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No MLC Detail Found");
			}
			else
			{
				rs.beforeFirst();
				while(rs.next()){
					MlcVO objMlcVO =new MlcVO();
					HelperMethods.populateVOfrmRS(objMlcVO, rs);
					lstMlcVO.add(objMlcVO);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("MlcDAO: getMlcDtls :: " + e);
		}
		return lstMlcVO;
	}
	
	/*	
  	public boolean checkExistingMLCNo(MlcVO objMlcVO_p, UserVO objUserVO_p)
	{
		boolean flagCheck=false;
		WebRowSet rs = null;
		HisDAO objHisDAO = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_MLC_DTL(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		try
		{
			objHisDAO = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = objHisDAO.setProcedure(strProcName);
			objHisDAO.setProcInValue(nProcIndex, "p_mode", "1",1);
			objHisDAO.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			objHisDAO.setProcInValue(nProcIndex, "p_hrgstr_mlc_no", objMlcVO_p.getPatMlcNo(),3);
			objHisDAO.setProcOutValue(nProcIndex, "err", 1,4);
			objHisDAO.setProcOutValue(nProcIndex, "resultset", 2,5);
			
			objHisDAO.executeProcedureByPosition(nProcIndex);
			
			strErr = objHisDAO.getString(nProcIndex, "err");
			rs = objHisDAO.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HisDAO.executeProcedureByPosition(nProcIndex)" + e+ strErr);
		}

		try{
			if (!rs.next())
				flagCheck=false;
			else
				flagCheck=true;
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}finally{
			if (objHisDAO != null)
			{
				objHisDAO.free();
				objHisDAO = null;
			}
		}
		
		return flagCheck;
	}*/
	
	/*Start : Surabhi
	 * Reason : to get the mlc details of the patients for printing the certificate
	 * date : 7th oct 2016 */
	public MlcVO getMlcDetailByCrNo(MlcVO objMlcVO_p,String strCrNo_p, UserVO objUserVO_p, String strMode_p)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_patient_mlc_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		MlcVO objMlcVO =new MlcVO();
		try 
		{
			System.out.println("MlcDAO :: getMlcDetailByCrNo()");
			daoObj = new HisDAO("Registration","MlcDAO");
			HelperMethods.setNullToEmpty(objMlcVO_p);
			nProcIndex = daoObj.setProcedure(strProcName);
			
			////////////////////////////////////////////////////////
			System.out.println("p_mode :"+ strMode_p);
			System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
			System.out.println("p_hrgnum_puk :"+ strCrNo_p);
			System.out.println("p_hrgnum_episode_code :"+ objMlcVO_p.getEpisodeCode());
			System.out.println("p_hrgnum_visit_no :"+ objMlcVO_p.getEpisodeVisitNo());
			System.out.println("p_hrgstr_mlc_no :"+ objMlcVO_p.getPatMlcNo());
			////////////////////////////////////////////////////////
			
			daoObj.setProcInValue(nProcIndex, "p_mode", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", strCrNo_p,3);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_episode_code", objMlcVO_p.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_no", objMlcVO_p.getEpisodeVisitNo(),5);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_mlc_no", objMlcVO_p.getPatMlcNo(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(e.getMessage());
		}

		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No MLC Detail Found");
			}
			else
			{
				System.out.println("rs.size :"+rs.size());
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(objMlcVO, rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("MlcDAO: getMlcDtls :: " + e);
		}
		return objMlcVO;
	}
	
	//end
}
