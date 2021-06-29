package registration.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import vo.registration.CertificateIssueDtlVO;
import vo.registration.MlcParameterDtlVO;
import vo.registration.MlcVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class CertificateIssueDtlDAO extends DataAccessObject
{
	public CertificateIssueDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}	
	
	/***
	 * 
	 * @param strMode_p
	 * @param objHisDAO_p
	 * @param objCertificateIssueDtlVO_p
	 * @param objUserVO_p
	 * @param strModeExecuteProc_p=1 -> For Batch, else individual
	 */
	public void saveCertificateIssueDtl(String strMode_p, HisDAO objHisDAO_p, CertificateIssueDtlVO objCertificateIssueDtlVO_p, UserVO objUserVO_p,String strModeExecuteProc_p)
	{
		//String queryKey = "INSERT.HRGT_PATIENT_MLC_DTL";
    	String strErr = "";
		int nProcIndex1 = 0, index=1;
		String strProcName1="";
		try 
		{
			System.out.println("--------------------------------------------------");
			System.out.println("CertificateIssueDtlDAO :: saveCertificateIssueDtl()");
			////////////////////////////////////////////////////////   	
			strProcName1 = "{call pkg_reg_dml.dml_hpmrt_certificate_issue_dtl(?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?)}";
			
			HelperMethods.setNullToEmpty(objCertificateIssueDtlVO_p);
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_p_modeval", strMode_p, index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_certificate_id",objCertificateIssueDtlVO_p.getCertificateId(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_certificate_id",objCertificateIssueDtlVO_p.getCertificateId(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_slno",objCertificateIssueDtlVO_p.getSlNo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_record_type",objCertificateIssueDtlVO_p.getRecordType(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_handover_to",objCertificateIssueDtlVO_p.getHandoverTo(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_isduplicate",objCertificateIssueDtlVO_p.getIsDuplicate(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_isreceipt_taken",objCertificateIssueDtlVO_p.getIsReceiptTaken(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrnum_record_status",objCertificateIssueDtlVO_p.getRecordStatus(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hpmrstr_remarks",objCertificateIssueDtlVO_p.getRemarks(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objCertificateIssueDtlVO_p.getDeptUnitCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",objCertificateIssueDtlVO_p.getIsValid(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",objUserVO_p.getSeatId(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(), index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date",objCertificateIssueDtlVO_p.getEntryDate(), index++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",objUserVO_p.getIpAddress(), index++);
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,index++);
			
			
			
			////////////////////// 	Printing Values on Console	////////////////////////////////
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_hpmrstr_certificate_id :"+ objCertificateIssueDtlVO_p.getCertificateId());
			System.out.println("p_hpmrnum_certificate_id :"+ objCertificateIssueDtlVO_p.getCertificateId());
			System.out.println("p_hpmrnum_slno :"+ objCertificateIssueDtlVO_p.getSlNo());
			System.out.println("p_hpmrnum_record_type :"+ objCertificateIssueDtlVO_p.getRecordType());
			System.out.println("p_hpmrstr_handover_to :"+ objCertificateIssueDtlVO_p.getHandoverTo());
			System.out.println("p_hpmrnum_isduplicate :"+ objCertificateIssueDtlVO_p.getIsDuplicate());
			System.out.println("p_hpmrnum_isreceipt_taken :"+ objCertificateIssueDtlVO_p.getIsReceiptTaken());
			System.out.println("p_hpmrnum_record_status :"+ objCertificateIssueDtlVO_p.getRecordStatus());
			System.out.println("p_hpmrstr_remarks :"+ objCertificateIssueDtlVO_p.getRemarks());
			System.out.println("p_hgnum_deptunitcode :"+ objCertificateIssueDtlVO_p.getDeptUnitCode());
			System.out.println("p_gnum_isvalid :"+ objCertificateIssueDtlVO_p.getIsValid());
			System.out.println("p_gnum_seat_id :"+objUserVO_p.getSeatId());
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode());
			System.out.println("p_gdt_entry_date :"+ objCertificateIssueDtlVO_p.getEntryDate());
			System.out.println("p_hrgstr_ip_add :"+ objUserVO_p.getIpAddress());
			 
			//////////////////////////////////////////////////////////////////////////////////////
			
			if(!strModeExecuteProc_p.isEmpty() && strModeExecuteProc_p.equals("1")){
				objHisDAO_p.execute(nProcIndex1,1);
			}else{
				objHisDAO_p.executeProcedureByPosition(nProcIndex1);
				strErr = objHisDAO_p.getString(nProcIndex1, "err");
			}
			
			System.out.println("----------------------------------------------------");
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
