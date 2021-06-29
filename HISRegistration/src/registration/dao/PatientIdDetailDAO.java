package registration.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import vo.registration.AddressVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;

public class PatientIdDetailDAO extends DataAccessObject {

	public PatientIdDetailDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public void savePatientIdDtl(HisDAO objHisDAO_p,PatientIdVO objPatientIdVO_p, String strPrimCatgID_p, String strPrimCatgGrp_p ,UserVO objUserVO_p, String strMode_p) {
		
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			System.out.println("PatientIdDetailDAO :: savePatientIdDtl()");
			/**************************************************/	/************* 1-5 ****************/	/********* 5-9 ********/
			//strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_id_dtl(	?,?,?::numeric,?::numeric,?::numeric,	?::numeric,?::numeric,?,?,?, ?, ?::numeric,?,?)}";
			//Commented for Change/Renew Patient Category
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_id_dtl(	?,?,?::numeric,?::numeric,?::numeric,	?::numeric,?::numeric,?,?,?, ?, ?::numeric,?)}";

			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(objPatientIdVO_p);
			
			if(strPrimCatgID_p == null)
				strPrimCatgID_p="";
			if(strPrimCatgGrp_p == null)
				strPrimCatgGrp_p="";
			if(objPatientIdVO_p.getPatIdNo() == null || objPatientIdVO_p.getPatIdNo().equals(""))
				objPatientIdVO_p.setPatIdNo("-1");
			
			System.out.println("----------------------------------------------------");
			System.out.println("p_hrgstr_patient_id :"+objPatientIdVO_p.getPatIdNo());
			System.out.println("p_gnum_document_code :"+objPatientIdVO_p.getPatDocType());
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode());
			System.out.println("p_hrgnum_puk :"+objPatientIdVO_p.getPatCrNo());
			System.out.println("p_gnum_is_alternateid :"+objPatientIdVO_p.getPatIsAlternateId());
			System.out.println("p_gnum_slno :"+objPatientIdVO_p.getMemSlNo());	
			System.out.println("lenghth p_gnum_slno :"+objPatientIdVO_p.getMemSlNo()==null?"Null value":(objPatientIdVO_p.getMemSlNo().equals("")?"Blank value":objPatientIdVO_p.getMemSlNo().length()));	

			System.out.println("----------------------------------------------------");
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", strMode_p,1);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_patient_id",objPatientIdVO_p.getPatIdNo(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_document_code",objPatientIdVO_p.getPatDocType(),3);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),4);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objPatientIdVO_p.getPatCrNo(),5);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_is_alternateid",objPatientIdVO_p.getPatIsAlternateId(),6);	
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",8);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_prim_cat_id",strPrimCatgID_p,9);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_prim_cat_group",strPrimCatgGrp_p,10);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",objUserVO_p.getSeatId(),11);
			/* #Start				:Sheeldarshi 
			#Modify Date(CR/PRS)	:07thJuly'15 
			#Reason					:Bug 9453 - Registration--> Application Exception Error is displayed, if the patient Category has expired/Charges for a paid category is not configured.
			*/
			//objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_slno",objPatientIdVO_p.getMemSlNo(),12);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_slno",((objPatientIdVO_p.getMemSlNo().equals(null)|| objPatientIdVO_p.getMemSlNo().equals("")||objPatientIdVO_p.getMemSlNo().equals("undefined"))?"0":objPatientIdVO_p.getMemSlNo()),12);
			//	End
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,13);
			
			objHisDAO_p.execute(nProcIndex1,1);			
			//objHisDAO_p.executeProcedureByPosition(nProcIndex1);
			
		//	strErr = objHisDAO_p.getString(nProcIndex1, "err");
			/*	if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
					}
*/		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile based on patient unique ID from PAtient ID detail table
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByUniqueIdForSearchTile(PatientIdVO objPatientIdVO_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_search_patient_dtl_by_uniqueid(?,?,?,?,?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		AddressVO _addressVO= new AddressVO();
		List lstPatientJsonObjString=new ArrayList();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		HelperMethods.setNullToEmpty(objPatientIdVO_p);
		daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
		//daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objPatientIdVO_p.getHospitalCode(),2);
		//daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "p_doctype", objPatientIdVO_p.getPatDocType(),3);
		daoObj.setProcInValue(nProcIndex, "p_pat_uniqueid", objPatientIdVO_p.getPatIdNo(),4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isglobal", objPatientIdVO_p.getIsGlobal(),5);
		
		////////////////////////////////////////////////////////////////////////
		System.out.println("------getHospitalCode :-------"+objPatientIdVO_p.getHospitalCode());
		System.out.println("------getPatDocType :--------"+objPatientIdVO_p.getPatDocType());
		System.out.println("------getPatIdNo :-------"+objPatientIdVO_p.getPatDocType());
		System.out.println("------getPatIdNo :-------"+objPatientIdVO_p.getPatIdNo());
		System.out.println("------getIsGlobal :-------"+objPatientIdVO_p.getIsGlobal());	
		////////////////////////////////////////////////////////////////////////

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
				throw new HisDataAccessException("PatientIdDetailDAO:searchPatientDetailByUniqueIdForSearchTile:HelperMethods :: " + e);
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
	 * By Mukund On 12.01.18
	 * */
public void saveAadhaarDatainADV(String crno, String uuid, String aadhaar, String hospitalCode, String mode, HisDAO daoObj) {
		
		WebRowSet rs = null;
		//HisDAO daoObj = null;
		String strProcName = "{call pkg_ehr_adv.dml_ehrt_aadhaar_data_vault(?,?,?,?,?,	?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{// character varying,  character varying,  character varying
			//daoObj = new HisDAO("EHR","ADV");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", mode,1);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_aadhaar_uuid", uuid,2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crno,3);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_national_id", aadhaar,4);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", hospitalCode,5);
						
			////////////////////////////////////////////////////////////////////////
			System.out.println("------aadhar :-------"+aadhaar);
			System.out.println("------uuid :--------"+uuid);
			System.out.println("------crno :-------"+crno);
			////////////////////////////////////////////////////////////////////////
	
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.execute(nProcIndex, 1);
	
			//strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";

				if (!strErr.equals("")) 
				{
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
public String getAadhaarReferenceUUID(String crno, String aadhaar, String mode) {
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call pkg_ehr_adv.get_aadhaar_reference_uuid(?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "", aadhaarReferenceUUID="";
	try 
	{// character varying,  character varying,  character varying
	
		daoObj = new HisDAO("EHR","getAadhaarReferenceUUID");
		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "p_mode", mode,1);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crno,2);
		daoObj.setProcInValue(nProcIndex, "p_hrgstr_national_id", aadhaar,3);
					
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,4);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
		
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		
		
		System.out.println("------aadhar :-------"+aadhaar);
		System.out.println("------crno :-------"+crno);
		System.out.println("------uuid :--------"+aadhaarReferenceUUID);
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}


	try {
		
		if (strErr.equals("")) 
		{
			while(rs.next())
			{
				aadhaarReferenceUUID = rs.getString(1);
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
		throw new HisDataAccessException("PatientIdDetailDAO:getAadhaarReferenceUUID :: " + e);
		}
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return aadhaarReferenceUUID;
}


public String getAadhaarDecrypted(String crno, String uuid) {
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call pkg_ehr_adv.get_aadhaar_decrypted(?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "", decryptedAadhaar="";
	try 
	{// character varying,  character varying,  character varying
		System.out.println("EHR: "+"getAadhaarDecrypted: ");
		
		daoObj = new HisDAO("EHR","getAadhaarDecrypted");
		nProcIndex = daoObj.setProcedure(strProcName);
	
		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crno,2);
		daoObj.setProcInValue(nProcIndex, "p_hrgstr_aadhaar_uuid", uuid,3);
					
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,4);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
		
		daoObj.executeProcedureByPosition(nProcIndex);
	
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
				
		System.out.println("------aadhar :-------"+uuid);
		System.out.println("------crno :-------"+crno);
		
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	
	
	try {
		
		if (strErr.equals("")) 
		{
			while(rs.next())
			{
				decryptedAadhaar = rs.getString(1);
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
		throw new HisDataAccessException("PatientIdDetailDAO:getAadhaarReferenceUUID :: " + e);
		}
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return decryptedAadhaar;
}
	/**End on 12.01.18*/

/**
 * By Mukund On 12.01.18
 * */
public void createQRDataEntry(String mode, PatientVO objPatVO, String _QRData, UserVO objUserVO_p) {
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call pkg_reg_dml.dml_hrgt_patient_minimal_dtl(?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?)}";//45 args as on Apr '18
	int nProcIndex = 0;
	String strErr = "";
	try 
	{// character varying,  character varying,  character varying
		daoObj = new HisDAO("Registration","createQRDataEntry");
		nProcIndex = daoObj.setProcedure(strProcName);
		/*
		 * PROCEDURE dml_hrgt_patient_minimal_dtl(p_modeval character varying DEFAULT '1'::character varying, p_hrgnum_puk character varying, 
		 * p_gnum_hospital_code character varying, p_hrgst_sec_uhid character varying, p_hrgdt_dob character varying,
		 *  p_hrgbl_is_actual_dob character varying, p_hrgstr_fname character varying, p_gstr_gender_code character varying, 
		 *  p_hrgjson_qr_data character varying, OUT err character varying) IS

*/		HelperMethods.setNullToEmpty(objPatVO);
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
		daoObj.setProcInValue(nProcIndex, "p_hrgbl_is_actual_dob",objPatVO.getIsActualDob(),i++);
		
		
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

		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}

	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
}
/*End */
}//End Of Class
