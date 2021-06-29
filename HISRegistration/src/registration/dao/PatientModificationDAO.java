package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Sequence;
import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.AddressVO;
import vo.registration.PatientModificationVO;
import vo.registration.PatientVO;
/**
 * AddressDAO is a class which describes all the methods required for database interaction
 * for HRGT_PATIENT_ADD_DTL table, for example, insert, update, select and delete.
 * @author AHIS
 *
 */
public class PatientModificationDAO extends RegistrationDAO
{
	public PatientModificationDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	

    
    /**
	 * Save/Update an address entry in DB for a patient.
	 * @param	objPatientModificationVO_p	Provides address details to be entered.
	 * @param	objUserVO_p		Provides User details.
	 * @return	AddressVO with values stored in DB.
	 * if strMode_p = 1 -->> Insert
	 * if strMode_p = 2 -->> Update
	 */
    public PatientModificationVO saveModifictaionDtl(HisDAO objHisDAO_p,PatientModificationVO objPatientModificationVO_p, UserVO objUserVO_p,String strMode_p)
    {
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_modify_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
			
			HelperMethods.setNullToEmpty(objPatientModificationVO_p);
			HelperMethods.setNullToEmpty(objUserVO_p);

			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			System.out.println("AddressDAO :: saveAddressDtl()");
			//////////////////////
			System.out.println("p_mode :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+ (objPatientModificationVO_p.getPatCrNo().equals("")?"0":objPatientModificationVO_p.getPatCrNo()));
	    	System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
	    	System.out.println("p_hrgnum_process_type :"+ (objPatientModificationVO_p.getProcessType()));
	    	System.out.println("p_hrgnum_s_no :"+ "1");
	    	System.out.println("p_hrgnum_change_req_by :"+ objPatientModificationVO_p.getRequestBy());
	    	System.out.println("p_gnum_relation_code :"+ objPatientModificationVO_p.getRequestRelation());
	    	System.out.println("p_gstr_remarks :"+"remarks");
	    	System.out.println("p_gdt_entry_date :"+ "");
	    	System.out.println("p_hrgstr_change_reqby_name :"+objPatientModificationVO_p.getRequestByName());
	    	System.out.println("p_gnum_seat_id :"+ objUserVO_p.getSeatId());
	    	System.out.println("p_hrgstr_change_reqby_add :"+ objPatientModificationVO_p.getRequestByAddress());
	    	System.out.println("p_hrgstr_constable_badgeno :"+ objPatientModificationVO_p.getConstableBadgeNo());
	    	System.out.println("p_gnum_isvalid :"+"1");
	    	System.out.println("p_hrgstr_constable_desig :"+ objPatientModificationVO_p.getConstableDesig());
	    	System.out.println("gnum_document_code :"+ objPatientModificationVO_p.getStrDocumentCode());
	    	System.out.println("hrgstr_card_id :"+ objPatientModificationVO_p.getPatCardNo());
	    	System.out.println("hrgnum_is_upload :"+ objPatientModificationVO_p.getStrIsUpload());
	    	System.out.println("hrgstr_document_id :"+ objPatientModificationVO_p.getStrDocumentCode());
	    	System.out.println("hrgstr_ip_add :"+ objUserVO_p.getIpAddress());
			//////////////////////
	    	 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_mode", strMode_p,1);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_process_type", objPatientModificationVO_p.getProcessType(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objPatientModificationVO_p.getPatCrNo().equals("")?"0":objPatientModificationVO_p.getPatCrNo(),3);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no", "1",4);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_change_req_by", objPatientModificationVO_p.getRequestBy(),5);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_relation_code", objPatientModificationVO_p.getRequestRelation(),6);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_remarks","remarks",7);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",8);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_name", objPatientModificationVO_p.getRequestByName(),9);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",  objUserVO_p.getSeatId(),10);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_add", objPatientModificationVO_p.getRequestByAddress(),11);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",12);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno", objPatientModificationVO_p.getConstableBadgeNo(),13);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig", objPatientModificationVO_p.getConstableDesig(),14);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),15);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_document_code",  objPatientModificationVO_p.getStrDocumentCode(),16);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_id", objPatientModificationVO_p.getPatCardNo(),17);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_upload", objPatientModificationVO_p.getStrIsUpload(),18);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_document_id", objPatientModificationVO_p.getStrDocumentId(),19);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(),20);
	    	
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,21);

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
		return objPatientModificationVO_p;
    }
    
}