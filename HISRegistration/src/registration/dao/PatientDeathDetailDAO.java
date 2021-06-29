package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import javax.sql.rowset.WebRowSet;

import vo.registration.MlcVO;
import vo.registration.PatientDeathDetailVO;
import vo.registration.PatientVO;

public class PatientDeathDetailDAO extends DataAccessObject {

	public PatientDeathDetailDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	
	public boolean isPatientDeathDtlAdded(String strCrNo_p,UserVO objUserVO_p)
	{
		int nCountPatDeathDtlAdded=0;
		int funcIndex=0;
		HisDAO objHisDAO_p=null;
		boolean exist=false;
		try 
		{
			System.out.println("PatientDeathDetailDAO :: isPatientDeathDtlAdded()");
			objHisDAO_p = new HisDAO("Registration","PatientDeathDetailDAO");
			funcIndex = objHisDAO_p.setFunction("{? = call pkg_reg_fun.ispatientdeathdeatilsentered(?)}");
			objHisDAO_p.setFuncInValue(funcIndex, 2, strCrNo_p);
			objHisDAO_p.setFuncOutValue(funcIndex,3);
			
			objHisDAO_p.executeFuncForNumeric(funcIndex);
			nCountPatDeathDtlAdded = Integer.parseInt(objHisDAO_p.getFuncNumeric(funcIndex));
			System.out.println("nCountPatDeathDtlAdded :"+nCountPatDeathDtlAdded);
			if(nCountPatDeathDtlAdded>0)
				exist=true;
			else
				exist=false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (objHisDAO_p != null) 
			{
				objHisDAO_p.free();
				objHisDAO_p = null;
			}
		}
		return exist;
	}
	
	
	public PatientDeathDetailVO getPatientDeathDetail(PatientDeathDetailVO objPatientDeathDetailVO_p, UserVO objUserVO_p)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		PatientDeathDetailVO objPatientDeathDetailVO=null;
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", objPatientDeathDetailVO_p.getPatCrNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			
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
				throw new HisRecordNotFoundException("Patient Death Detail is not added");
			}
			else
			{
				rs.beforeFirst();
				objPatientDeathDetailVO= new PatientDeathDetailVO();
				HelperMethods.populateVOfrmRS(objPatientDeathDetailVO, rs);
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return objPatientDeathDetailVO;
	}
	
	/*Start : Surabhi
	 * Reason : to get the death details of the patients for printing the certificates
	 * date : 7th oct 2016 */
	public PatientDeathDetailVO getDeathDetailByCrNo(String strCrNo_p, UserVO objUserVO_p)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_reg_view.proc_hpmrt_pat_death_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		PatientDeathDetailVO objPatientDeathDetailVO=null;
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", strCrNo_p,3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			
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
				throw new HisRecordNotFoundException("Patient Death Detail is not added");
			}
			else
			{
				rs.beforeFirst();
				objPatientDeathDetailVO= new PatientDeathDetailVO();
				HelperMethods.populateVOfrmRS(objPatientDeathDetailVO, rs);
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return objPatientDeathDetailVO;
	}
	//end
	
	public String generateDeathCertificateId(UserVO objUserVO_p, String strCRNo_p)
	{
		String strDeathCertificateId="";
		int funcIndex=0;
		HisDAO objHisDAO_p=null;
		try 
		{
			objHisDAO_p = new HisDAO("Registration","PatientDeathDetailDAO");
			funcIndex = objHisDAO_p.setFunction("{? = call pkg_reg_util.fun_gen_deathCertificateId(?::numeric)}");
			objHisDAO_p.setFuncInValue(funcIndex, 2,objUserVO_p.getHospitalCode());
			objHisDAO_p.setFuncOutValue(funcIndex,3);
			
			objHisDAO_p.executeFuncForNumeric(funcIndex);
			strDeathCertificateId = objHisDAO_p.getFuncNumeric(funcIndex)+"";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDeathDetailDAO.generateDeathCertificateId()" + e);
		}
		finally 
		{
			if (objHisDAO_p != null) 
			{
				objHisDAO_p.free();
				objHisDAO_p = null;
			}
		}
		return strDeathCertificateId;
	}
	
	public void savePatientDeathDtl(String strMode_p, HisDAO objHisDAO_p, PatientDeathDetailVO objPatDeathDtlVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, String strModeExecuteProc_p)
	{
		//String queryKey = "INSERT.HRGT_PATIENT_MLC_DTL";
    	String strErr = "";
		int nProcIndex1 = 0, index=1;
		String strProcName1="";
		try 
		{
			System.out.println("----------------------------------------------");
			System.out.println("PatientDeathDetailDAO :: savePatientDeathDtl()");
			//////////////////////////////////////////////////////// /************ 1-5 **********/	/* 6-10 */	/********* 11-15 **********/	/************ 16-20 **************/		/************ 21-25 ***************/	/****************** 26-30 ******************/	/******** 31-35 ***********/	/******** 36-40 **********/		/*************** 41-45 *************/	/************** 46-50 *************/	/**** 51-55 ******/		/*********** 56-60 ********/	/************** 61-65 *************/	/***** 66-70 *****/		/* 71-75*/	/********************** 76-80 ************************/		/******** 81-85 **********/		/* 86-90*/	/******** 91-95 ***********/	/*96-100*/	/*101-105*/	/****** 106-110 ***/	/****** 111-120****/
			strProcName1 = "{call pkg_reg_dml.dml_hpmrt_pat_death_dtl( ?,?::numeric,?::numeric,?,?,	?,?,?,?,?,	?::numeric,?::numeric,?,?,?,	?,?::numeric,?::numeric,?::numeric,		?,?::numeric,?,?::numeric,?::numeric,	?::numeric,?::numeric,?::numeric,?,?::numeric,	?,?,?::numeric,?::numeric,?,	?::numeric,?::numeric,?,?,?,	?::numeric,?::numeric,?::numeric,?,?,	?::numeric,?::numeric,?::numeric,?,?,	?::numeric,?,?,?,?,		?::numeric,?,?::numeric,?,?,	?,?::numeric,?::numeric,?::numeric,?,	?,?::numeric,?,?,?,		?,?,?,?,?,	?::numeric,?::numeric,?::numeric,?::numeric,?::numeric, 	?::numeric,?::numeric,?,?,?,	?,?,?,?,?,	?::numeric,?::numeric,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?::numeric,?,?,?,	?,?,?,?,?,	?,?,?,?,?)}";
			 															
			
			HelperMethods.setNullToEmpty(objPatDeathDtlVO_p);
			HelperMethods.setNullToEmpty(objPatientVO_p);
			HelperMethods.setNullToEmpty(objPatientVO_p.getPatAddress());
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", strMode_p, index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objPatDeathDtlVO_p.getPatCrNo(),index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk",objPatientVO_p.getPrevCrNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob",objPatientVO_p.getPatDOB(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgbl_is_actual_dob",objPatientVO_p.getIsActualDob(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname",objPatientVO_p.getPatFirstName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_document_code",objPatientVO_p.getPatDocType(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname",objPatientVO_p.getPatMiddleName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname",objPatientVO_p.getPatLastName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_gender_code",objPatientVO_p.getPatGenderCode(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_marital_status_code",objPatientVO_p.getPatMaritalStatusCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code",objPatientVO_p.getPatStatusCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark1",objPatientVO_p.getPatIdMark1(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_primarycat_validupto",objPatientVO_p.getPatPrimaryCatValidUpto(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_father_name",objPatientVO_p.getPatGuardianName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_momname",objPatientVO_p.getPatMotherName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_appellation_code",objPatientVO_p.getPatAppellationCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_reg_cat_code",objPatientVO_p.getPatRegCatCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_suffix_code",objPatientVO_p.getSuffixCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_name_type",objPatientVO_p.getPatNameType(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code",objPatientVO_p.getPatPrimaryCatCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",objUserVO_p.getSeatId(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc",objPatientVO_p.getIsMLC(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",objPatDeathDtlVO_p.getIsValid(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isunknown",objPatientVO_p.getIsUnknown(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_month_income",objPatientVO_p.getPatMonthlyIncome(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_birth_place",objPatientVO_p.getPatBirthPlace(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_special_vulnerability",objPatientVO_p.getPatSplVulnerability(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",(objUserVO_p.getIpAddress()==null|| objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()),index++); 
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_id_no",objPatientVO_p.getPatIdNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_ref",objPatientVO_p.getIsReferred(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_religion_code",objPatientVO_p.getPatReligionCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_age",objPatientVO_p.getPatAge(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_occupation_code",objPatientVO_p.getPatOccupation(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_imageuploaded",objPatientVO_p.getIsImageUploaded(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_spousename",objPatientVO_p.getPatHusbandName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_national_id",objPatientVO_p.getPatNationalId(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code",objPatientVO_p.getPatBloodGroup(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn",objPatientVO_p.getIsNewBorn(),index++); 
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk",objPatientVO_p.getPatMotherCrNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_dead",objPatientVO_p.getPatIsDead(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname",objPatientVO_p.getPatNickName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_no",objPatientVO_p.getPatCardNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_verification_status",objPatientVO_p.getPatVerificationStatus(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_nationality",objPatientVO_p.getPatNationalId(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark2",objPatientVO_p.getPatIdMark2(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_address_line1",objPatientVO_p.getPatAddHNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead",objPatientVO_p.getIsBroughtDead(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality1",objPatientVO_p.getPatAddStreet(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_caste_code",objPatientVO_p.getPatCasteCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality2",objPatientVO_p.getPatAddLandMarks(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city_location",objPatientVO_p.getPatAddCityLoc(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_education_code",objPatientVO_p.getPatEducationCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city",objPatientVO_p.getPatAddCity(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_pincode",objPatientVO_p.getPatAddPIN(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_district",objPatientVO_p.getPatAddDistrict(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_state_name",objPatientVO_p.getPatAddState(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_country_name",objPatientVO_p.getPatAddCountry(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_urban",objPatientVO_p.getPatIsUrban(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_local",objPatientVO_p.getPatIsLocal(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_phone_owner",objPatientVO_p.getPatAddPhoneOwner(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no",objPatientVO_p.getPatAddMobileNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_phone_no",objPatientVO_p.getPatAddPhoneNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ismerged","",index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_email_id",objPatientVO_p.getPatAddEmailId(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_register_date",objPatientVO_p.getRegisterDate(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname_local",objPatientVO_p.getPatFirstNameInMultiLang(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname_local",objPatientVO_p.getPatMiddleNameInMultiLang(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname_local",objPatientVO_p.getPatLastNameInMultiLang(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname_local",objPatientVO_p.getPatNickNameInMultiLang(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgt_episode_date",objPatDeathDtlVO_p.getEpisodeDate(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",objPatDeathDtlVO_p.getEpisodeCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",objPatDeathDtlVO_p.getEpisodeVisitNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code",objPatDeathDtlVO_p.getDepartmentCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objPatDeathDtlVO_p.getDepartmentUnitCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hipnum_room_no",objPatDeathDtlVO_p.getRoomCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no",objPatDeathDtlVO_p.getPatAdmNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hipnum_ward_code",objPatDeathDtlVO_p.getWardCode(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_bed_code",objPatDeathDtlVO_p.getBedCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no",objPatDeathDtlVO_p.getPatMlcNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrdt_death_datetime",objPatDeathDtlVO_p.getDeathDate(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrdt_actual_datetime","",index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrdt_onset_date",objPatDeathDtlVO_p.getOnSetDate(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_immediate_cause1",objPatDeathDtlVO_p.getImmediateCause1(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_immediate_cause2",objPatDeathDtlVO_p.getImmediateCause2(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_immediate_cause3",objPatDeathDtlVO_p.getImmediateCause3(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_other_cause",objPatDeathDtlVO_p.getOtherCause(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_ispregnant",objPatDeathDtlVO_p.getIsPregnant(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_isdelivery",objPatDeathDtlVO_p.getIsDelivery(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_pregnancy_remarks",objPatDeathDtlVO_p.getPregnancyRemarks(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_injury_nature_code",objPatDeathDtlVO_p.getInjuryNatureCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_injury_remarks",objPatDeathDtlVO_p.getInjuryRemarks(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_death_manner_code",objPatDeathDtlVO_p.getDeathMannerCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_consultant_remark",objPatDeathDtlVO_p.getConsultantRemarks(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrdt_verification_datetime",objPatDeathDtlVO_p.getVerificationDate(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_body_handover_to",objPatDeathDtlVO_p.getBodyHandoverTo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrdt_body_handover_datetime",objPatDeathDtlVO_p.getBodyHandoverDateTime(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_relative_name",objPatDeathDtlVO_p.getRelativeName(),index++); 

			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_relative_address",objPatDeathDtlVO_p.getRelativeAddress(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_relativecode",objPatDeathDtlVO_p.getRelativeCode(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_officer_name",objPatDeathDtlVO_p.getOfficerName(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_officer_desig",objPatDeathDtlVO_p.getOfficerDesignation(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_officer_badgeno",objPatDeathDtlVO_p.getOfficerBadgeNo(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_certificate_id",objPatDeathDtlVO_p.getDeathCertificateId(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isdeathdetailentered",objPatDeathDtlVO_p.getIsDeathDetailEntered(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_attend_consultant_id",objPatDeathDtlVO_p.getAttConsultantId(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_handover_by",objPatDeathDtlVO_p.getHandoverBy(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_handoverto_empid",objPatDeathDtlVO_p.getBodyHandoverToEmpId(),index++);
			
			/* Start : Surabhi
			 * Reason : Changes done for adding Snomed CT functionality
			 * */
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_ids_immediate_cause1",objPatDeathDtlVO_p.getSnomdCIdimmediateCause1(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_pterms_immediate_cause1",objPatDeathDtlVO_p.getSnomdPTimmediateCause1(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_ids_immediate_cause2",objPatDeathDtlVO_p.getSnomdCIdimmediateCause2(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_pterms_immediate_cause2",objPatDeathDtlVO_p.getSnomdPTimmediateCause2(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_ids_immediate_cause3",objPatDeathDtlVO_p.getSnomdCIdimmediateCause3(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_pterms_immediate_cause3",objPatDeathDtlVO_p.getSnomdPTimmediateCause3(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_ids_other_cause",objPatDeathDtlVO_p.getSnomdCIdOtherCause(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_pterms_other_cause",objPatDeathDtlVO_p.getSnomdPTOtherCause(),index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sctstr_ids_death_manner_code",objPatDeathDtlVO_p.getSnomdCIdDeathManner(),index++); 
			
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,index++);
			
			
			//////////////////////Printing Values on Console	////////////////////////////////
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+objPatDeathDtlVO_p.getPatCrNo());
			System.out.println("p_hrgstr_prev_puk :"+objPatientVO_p.getPrevCrNo()); 
			System.out.println("p_hrgdt_dob :"+objPatientVO_p.getPatDOB()); 
			System.out.println("p_hrgbl_is_actual_dob :"+objPatientVO_p.getIsActualDob()); 
			System.out.println("p_hrgstr_fname :"+objPatientVO_p.getPatFirstName()); 
			System.out.println("p_gnum_document_code :"+objPatientVO_p.getPatDocType()); 
			System.out.println("p_hrgstr_mname :"+objPatientVO_p.getPatMiddleName()); 
			System.out.println("p_hrgstr_lname :"+objPatientVO_p.getPatLastName()); 
			System.out.println("p_gstr_gender_code :"+objPatientVO_p.getPatGenderCode()); 

			System.out.println("p_gnum_marital_status_code :"+objPatientVO_p.getPatMaritalStatusCode()); 
			System.out.println("p_hgnum_pat_status_code :"+objPatientVO_p.getPatStatusCode()); 
			System.out.println("p_hrgstr_idmark1 :"+objPatientVO_p.getPatIdMark1()); 
			System.out.println("p_hrgdt_primarycat_validupto :"+objPatientVO_p.getPatPrimaryCatValidUpto()); 
			System.out.println("p_hrgstr_father_name :"+objPatientVO_p.getPatGuardianName()); 
			System.out.println("p_hrgstr_momname :"+objPatientVO_p.getPatMotherName()); 
			System.out.println("p_gnum_appellation_code :"+objPatientVO_p.getPatAppellationCode()); 
			System.out.println("p_hrgnum_reg_cat_code :"+objPatientVO_p.getPatRegCatCode()); 
			System.out.println("p_gnum_suffix_code :"+objPatientVO_p.getSuffixCode()); 
			System.out.println("p_hrgstr_name_type :"+objPatientVO_p.getPatNameType()); 

			System.out.println("p_hgnum_patient_cat_code :"+objPatientVO_p.getPatPrimaryCatCode()); 
			System.out.println("p_gdt_entry_date :"+""); 
			System.out.println("p_gnum_seat_id :"+objUserVO_p.getSeatId()); 
			System.out.println("p_hrgnum_is_mlc :"+objPatientVO_p.getIsMLC()); 
			System.out.println("p_gnum_isvalid :"+objPatDeathDtlVO_p.getIsValid()); 
			System.out.println("p_hrgnum_isunknown :"+objPatientVO_p.getIsUnknown()); 
			System.out.println("p_hrgnum_month_income :"+objPatientVO_p.getPatMonthlyIncome()); 
			System.out.println("p_hrgstr_birth_place :"+objPatientVO_p.getPatBirthPlace()); 
			System.out.println("p_hrgnum_special_vulnerability :"+objPatientVO_p.getPatSplVulnerability()); 
			System.out.println("p_hrgstr_ip_add :"+(objUserVO_p.getIpAddress()==null|| objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress())); 
			
			System.out.println("p_hrgnum_id_no :"+objPatientVO_p.getPatIdNo()); 
			System.out.println("p_hrgnum_is_ref :"+objPatientVO_p.getIsReferred()); 
			System.out.println("p_gnum_religion_code :"+objPatientVO_p.getPatReligionCode()); 
			System.out.println("p_hrgstr_age :"+objPatientVO_p.getPatAge()); 
			System.out.println("p_gnum_occupation_code :"+objPatientVO_p.getPatOccupation()); 
			System.out.println("p_hrgnum_is_imageuploaded :"+objPatientVO_p.getIsImageUploaded()); 
			System.out.println("p_hrgstr_spousename :"+objPatientVO_p.getPatHusbandName()); 
			System.out.println("p_hrgstr_national_id :"+objPatientVO_p.getPatNationalId()); 
			System.out.println("p_hbnum_bldgrp_code :"+objPatientVO_p.getPatBloodGroup()); 
			System.out.println("p_hrgnum_isnewborn :"+objPatientVO_p.getIsNewBorn()); 
			
			System.out.println("p_hrgnum_mother_puk :"+objPatientVO_p.getPatMotherCrNo()); 
			System.out.println("p_hrgnum_is_dead :"+objPatientVO_p.getPatIsDead()); 
			System.out.println("p_hrgstr_nickname :"+objPatientVO_p.getPatNickName()); 
			System.out.println("p_hrgstr_card_no :"+objPatientVO_p.getPatCardNo()); 
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode()); 
			System.out.println("p_hrgnum_verification_status :"+objPatientVO_p.getPatVerificationStatus()); 
			System.out.println("p_gnum_nationality :"+objPatientVO_p.getPatNationalId()); 
			System.out.println("p_hrgstr_idmark2 :"+objPatientVO_p.getPatIdMark2()); 
			System.out.println("p_hrgstr_address_line1 :"+objPatientVO_p.getPatAddHNo()); 
			System.out.println("p_hrgnum_is_broughtdead :"+objPatientVO_p.getIsBroughtDead()); 

			System.out.println("p_hrgstr_sub_locality1 :"+objPatientVO_p.getPatAddStreet()); 
			System.out.println("p_gstr_caste_code :"+objPatientVO_p.getPatCasteCode()); 
			System.out.println("p_hrgstr_sub_locality2 :"+objPatientVO_p.getPatAddLandMarks()); 
			System.out.println("p_hrgstr_city_location :"+objPatientVO_p.getPatAddCityLoc()); 
			System.out.println("p_gnum_education_code :"+objPatientVO_p.getPatEducationCode()); 
			System.out.println("p_hrgstr_city :"+objPatientVO_p.getPatAddCity()); 
			System.out.println("p_hrgnum_pincode :"+objPatientVO_p.getPatAddPIN()); 
			System.out.println("p_hrgstr_district :"+objPatientVO_p.getPatAddDistrict()); 
			System.out.println("p_hrgstr_state_name :"+objPatientVO_p.getPatAddState()); 
			System.out.println("p_gstr_country_name :"+objPatientVO_p.getPatAddCountry()); 

			System.out.println("p_hrgnum_is_urban :"+objPatientVO_p.getPatIsUrban()); 
			System.out.println("p_hrgnum_is_local :"+objPatientVO_p.getPatIsLocal()); 
			System.out.println("p_hrgnum_phone_owner :"+objPatientVO_p.getPatAddPhoneOwner()); 
			System.out.println("p_hrgstr_mobile_no :"+objPatientVO_p.getPatAddMobileNo()); 
			System.out.println("p_hrgstr_phone_no :"+objPatientVO_p.getPatAddPhoneNo()); 
			System.out.println("p_hrgnum_ismerged :"+""); 
			System.out.println("p_hrgstr_email_id :"+objPatientVO_p.getPatAddEmailId()); 
			System.out.println("p_hrgdt_register_date :"+objPatientVO_p.getRegisterDate()); 
			System.out.println("p_hrgstr_fname_local :"+objPatientVO_p.getPatFirstNameInMultiLang()); 
			System.out.println("p_hrgstr_mname_local :"+objPatientVO_p.getPatMiddleNameInMultiLang()); 

			System.out.println("p_hrgstr_lname_local :"+objPatientVO_p.getPatLastNameInMultiLang()); 
			System.out.println("p_hrgstr_nickname_local :"+objPatientVO_p.getPatNickNameInMultiLang()); 
			System.out.println("p_hrgt_episode_date :"+objPatDeathDtlVO_p.getEpisodeDate()); 
			System.out.println("p_hrgnum_episode_code :"+objPatDeathDtlVO_p.getEpisodeCode()); 
			System.out.println("p_hrgnum_visit_no :"+objPatDeathDtlVO_p.getEpisodeVisitNo()); 
			System.out.println("p_gnum_dept_code :"+objPatDeathDtlVO_p.getDepartmentCode()); 
			System.out.println("p_hgnum_deptunitcode :"+objPatDeathDtlVO_p.getDepartmentUnitCode()); 
			System.out.println("p_hipnum_room_no :"+objPatDeathDtlVO_p.getRoomCode()); 
			System.out.println("p_hrgnum_admission_no :"+objPatDeathDtlVO_p.getPatAdmNo()); 
			System.out.println("p_hipnum_ward_code :"+objPatDeathDtlVO_p.getWardCode()); 

			System.out.println("p_hgnum_bed_code :"+objPatDeathDtlVO_p.getBedCode()); 
			System.out.println("p_hrgstr_mlc_no :"+objPatDeathDtlVO_p.getPatMlcNo()); 
			System.out.println("p_hpmrdt_death_datetime :"+objPatDeathDtlVO_p.getDeathDate()); 
			System.out.println("p_hpmrdt_actual_datetime :"+""); 
			System.out.println("p_hpmrdt_onset_date :"+objPatDeathDtlVO_p.getOnSetDate()); 
			System.out.println("p_hpmrstr_immediate_cause1 :"+objPatDeathDtlVO_p.getImmediateCause1()); 
			System.out.println("p_hpmrstr_immediate_cause2 :"+objPatDeathDtlVO_p.getImmediateCause2()); 
			System.out.println("p_hpmrstr_immediate_cause3 :"+objPatDeathDtlVO_p.getImmediateCause3()); 
			System.out.println("p_hpmrstr_other_cause :"+objPatDeathDtlVO_p.getOtherCause()); 
			System.out.println("p_hpmrnum_ispregnant :"+objPatDeathDtlVO_p.getIsPregnant()); 

			System.out.println("p_hpmrnum_isdelivery :"+objPatDeathDtlVO_p.getIsDelivery()); 
			System.out.println("p_hpmrstr_pregnancy_remarks :"+objPatDeathDtlVO_p.getPregnancyRemarks()); 
			System.out.println("p_hrgnum_injury_nature_code :"+objPatDeathDtlVO_p.getInjuryNatureCode()); 
			System.out.println("p_hpmrstr_injury_remarks :"+objPatDeathDtlVO_p.getInjuryRemarks()); 
			System.out.println("p_hgnum_death_manner_code :"+objPatDeathDtlVO_p.getDeathMannerCode()); 
			System.out.println("p_hpmrstr_consultant_remark :"+objPatDeathDtlVO_p.getConsultantRemarks()); 
			System.out.println("p_hpmrdt_verification_datetime :"+objPatDeathDtlVO_p.getVerificationDate()); 
			System.out.println("p_hpmrnum_body_handover_to :"+objPatDeathDtlVO_p.getBodyHandoverTo()); 
			System.out.println("p_hpmrdt_body_handover_datetime :"+objPatDeathDtlVO_p.getBodyHandoverDateTime()); 
			System.out.println("p_hpmrstr_relative_name :"+objPatDeathDtlVO_p.getRelativeName()); 

			System.out.println("p_hpmrstr_relative_address :"+objPatDeathDtlVO_p.getRelativeAddress()); 
			System.out.println("p_gnum_relativecode :"+objPatDeathDtlVO_p.getRelativeCode()); 
			System.out.println("p_hpmrstr_officer_name :"+objPatDeathDtlVO_p.getOfficerName()); 
			System.out.println("p_hpmrstr_officer_desig :"+objPatDeathDtlVO_p.getOfficerDesignation()); 
			System.out.println("p_hpmrstr_officer_badgeno :"+objPatDeathDtlVO_p.getOfficerBadgeNo()); 
			System.out.println("p_hpmrnum_certificate_id :"+objPatDeathDtlVO_p.getDeathCertificateId()); 
			System.out.println("p_hrgnum_isdeathdetailentered :"+objPatDeathDtlVO_p.getIsDeathDetailEntered()); 
			System.out.println("p_hrgstr_attend_consultant_id :"+objPatDeathDtlVO_p.getAttConsultantId()); 
			System.out.println("p_gnum_handover_by :"+objPatDeathDtlVO_p.getHandoverBy()); 
			System.out.println("p_hpmrstr_handoverto_empid :"+objPatDeathDtlVO_p.getBodyHandoverToEmpId());
			//////////////////////////////////////////////////////////////////////////////////////


			if(!strModeExecuteProc_p.isEmpty() && strModeExecuteProc_p.equals("1")){
				objHisDAO_p.execute(nProcIndex1,1);
			}else{
				objHisDAO_p.executeProcedureByPosition(nProcIndex1);
				strErr = objHisDAO_p.getString(nProcIndex1, "err");
			}		
			System.out.println("----------------------------------------------");
			
			if (!strErr.isEmpty()) 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
