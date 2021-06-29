package registration.dao;

import registration.config.RegistrationConfig;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import vo.registration.PatientModificationVO;
import vo.registration.UnknownVerificationDtlVO;
/**
 * AddressDAO is a class which describes all the methods required for database interaction
 * for HRGT_PATIENT_ADD_DTL table, for example, insert, update, select and delete.
 * @author AHIS
 *
 */
public class UnknownVerificationDtlDAO extends RegistrationDAO
{
	public UnknownVerificationDtlDAO(TransactionContext _transactionContext){
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
    public UnknownVerificationDtlVO saveModifictaionDtl(HisDAO objHisDAO_p,UnknownVerificationDtlVO UnknownVerificationDtlVO_p, UserVO objUserVO_p,String strMode_p)
    {
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_unknown_verification_dtl(?,?,?,?,?, ?,?,?,?,?)}";
			
			HelperMethods.setNullToEmpty(UnknownVerificationDtlVO_p);
			HelperMethods.setNullToEmpty(objUserVO_p);

			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			System.out.println("AddressDAO :: saveAddressDtl()");
			//////////////////////
			
	    	/*
	    	 *  PROCEDURE (p_mode character varying, p_gnum_document_code character varying,
	    	 *  p_hrgnum_puk character varying, p_gstr_document_name character varying, p_hrgnum_is_upload character varying,
	    	 *   p_gnum_seatid character varying, 
	    	 *  p_gnum_hospital_code character varying,  character varying, OUT err character varying);
	    	 * */
	    	
	    	 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_mode", strMode_p,1);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_document_code", UnknownVerificationDtlVO_p.getStrDocumentCode(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", UnknownVerificationDtlVO_p.getPatCrNo(),3);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_document_name", UnknownVerificationDtlVO_p.getStrDocumentId(),4);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_upload", RegistrationConfig.IS_UPLOAD_FALSE,5);///
	    	//isupload 1or 0
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seatid",objUserVO_p.getSeatId(),6);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),7);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);   	
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",objUserVO_p.getIpAddress(),9);  	
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,10);

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
		return UnknownVerificationDtlVO_p;
    }
    
}