package registration.dao;

import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import vo.registration.UnknownChangeDtlVO;
/**
 * AddressDAO is a class which describes all the methods required for database interaction
 * for HRGT_PATIENT_ADD_DTL table, for example, insert, update, select and delete.
 * @author AHIS
 *
 */
public class UnknownChangeDtlDAO extends RegistrationDAO
{
	public UnknownChangeDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	

    
    /**
	 * Save/Update an address entry in DB for a patient.
	 * @param	objUnknownChangeDtlVO_p	Provides address details to be entered.
	 * @param	objUserVO_p		Provides User details.
	 * @return	AddressVO with values stored in DB.
	 * if strMode_p = 1 -->> Insert
	 * if strMode_p = 2 -->> Update
	 */
    public UnknownChangeDtlVO saveUnknownChangeDtl(HisDAO objHisDAO_p,UnknownChangeDtlVO objUnknownChangeDtlVO_p, UserVO objUserVO_p,String strMode_p)
    {
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_unknown_change_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)}";
			HelperMethods.setNullToEmpty(objUnknownChangeDtlVO_p);
			HelperMethods.setNullToEmpty(objUserVO_p);

			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			System.out.println("UnknownChangeDtlDAO :: saveUnknownChangeDtl()");
			
			//////////////////////
	    	 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_mode", strMode_p,1);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objUnknownChangeDtlVO_p.getPatCrNo().equals("")?"0":objUnknownChangeDtlVO_p.getPatCrNo(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_dob", objUnknownChangeDtlVO_p.getPatDOB(),3);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgbl_is_actual_dob", objUnknownChangeDtlVO_p.getIsActualDob(),4);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_document_code", objUnknownChangeDtlVO_p.getPatDocType(),5);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname", objUnknownChangeDtlVO_p.getPatFirstName(),6);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname",objUnknownChangeDtlVO_p.getPatMiddleName(),7);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname",objUnknownChangeDtlVO_p.getPatLastName(),8);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_gender_code", objUnknownChangeDtlVO_p.getPatGenderCode(),9);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_appellation_code",  objUnknownChangeDtlVO_p.getPatAppellationCode(),10);
	    	
	    	
	    	
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", objUnknownChangeDtlVO_p.getPatMaritalStatusCode(),11);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_suffix_code",objUnknownChangeDtlVO_p.getSuffixCode(),12);
	    //--
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_name_type", "K",13);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", objUnknownChangeDtlVO_p.getPatIdMark1(),14);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_father_name",objUnknownChangeDtlVO_p.getPatGuardianName(),15);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_momname",  objUnknownChangeDtlVO_p.getPatMotherName(),16);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", objUnknownChangeDtlVO_p.getPatCatCode(),17);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),18);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", objUnknownChangeDtlVO_p.getPatBirthPlace(),19);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid", "1",20);
	    	
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", objUnknownChangeDtlVO_p.getIsUnknown(),21);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_month_income",objUnknownChangeDtlVO_p.getPatMonthlyIncome(),22);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_occupation_code", objUnknownChangeDtlVO_p.getPatOccupation(),23);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(),24);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_id_no", objUnknownChangeDtlVO_p.getPatIdNo(),25);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_religion_code",  objUnknownChangeDtlVO_p.getPatReligionCode(),26);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_nationality", objUnknownChangeDtlVO_p.getPatNationalityCode(),27);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_age", objUnknownChangeDtlVO_p.getPatAge(),28);
	    	
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_address_line1", objUnknownChangeDtlVO_p.getPatAddHNo(),29);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality1",objUnknownChangeDtlVO_p.getPatAddStreet(),30);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_sub_locality2", objUnknownChangeDtlVO_p.getPatAddLandMarks(),31);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_spousename",objUnknownChangeDtlVO_p.getPatAddCity(),32);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city_location", objUnknownChangeDtlVO_p.getPatAddCityLoc(),33);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_national_id", objUnknownChangeDtlVO_p.getPatIdNo(),34);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city",objUnknownChangeDtlVO_p.getPatAddCity(),35);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_pincode",  objUnknownChangeDtlVO_p.getPatAddPIN(),36);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_district", objUnknownChangeDtlVO_p.getPatAddDistrict(),37);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_state_name", objUnknownChangeDtlVO_p.getPatAddState(),38);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_country_name", objUnknownChangeDtlVO_p.getPatAddCountryCode(),39);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", objUnknownChangeDtlVO_p.getPatIsUrban(),40);
	    	
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_local", objUnknownChangeDtlVO_p.getPatIsLocal(),41);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_phone_owner",objUnknownChangeDtlVO_p.getPatAddPhoneOwner(),42);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname", objUnknownChangeDtlVO_p.getPatNickName(),43);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no", objUnknownChangeDtlVO_p.getPatAddMobileNo(),44);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_no", objUnknownChangeDtlVO_p.getPatCardNo(),45);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",  objUserVO_p.getHospitalCode(),46);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_phone_no", objUnknownChangeDtlVO_p.getPatAddPhoneNo(),47);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_email_id", objUnknownChangeDtlVO_p.getPatAddEmailId(),48);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", objUnknownChangeDtlVO_p.getPatIdMark2(),49);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_caste_code", objUnknownChangeDtlVO_p.getPatCasteCode(),50); 	

	    	
	     	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_register_date", objUnknownChangeDtlVO_p.getRegisterDate(),51);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_education_code",objUnknownChangeDtlVO_p.getPatEducationCode(),52);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", "",53);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", "",54);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", "",55);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_nickname_local",  "",56);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_change_req_by", objUnknownChangeDtlVO_p.getRequestBy(),57);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_relation_code", objUnknownChangeDtlVO_p.getRequestRelation(),58);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_name", objUnknownChangeDtlVO_p.getRequestByName(),59);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_add", objUnknownChangeDtlVO_p.getRequestByAddress(),60);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno", objUnknownChangeDtlVO_p.getConstableBadgeNo(),61); 	
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig", objUnknownChangeDtlVO_p.getConstableDesig(),62);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_change_regby_phone", objUnknownChangeDtlVO_p.getRequestRelation(),63);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_ageUnit", objUnknownChangeDtlVO_p.getPatAgeUnit(),64);
	    	
	    	
	    	
	    	
	    	
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,65);

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
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objUnknownChangeDtlVO_p;
    }
    
}