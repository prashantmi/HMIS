package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import vo.registration.PatientHospitalDtlVO;

public class PatientHospitalDtlDAO extends DataAccessObject {
	
	public PatientHospitalDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}

public PatientHospitalDtlVO savePatientHospitalDtlDAO(HisDAO daoObj,PatientHospitalDtlVO objPatientHospitalDtlVO_p, UserVO objUserVO_p,String strMode_p){
	
	String strErr = "";
	int nProcIndex1 = 0;
	String strProcName1="";
	
	try 
	{
		System.out.println("----------------------------------------------------");
		System.out.println("PatientHospitalDtlDAO :: savePatientHospitalDtlDAO()");
		
		System.out.println("p_modeVal :"+ strMode_p);		
		System.out.println("p_hrgnum_puk :"+objPatientHospitalDtlVO_p.getPatCrNo());
		System.out.println("p_hgnum_pat_status_code :"+objPatientHospitalDtlVO_p.getPatStatusCode());
		System.out.println("p_hgnum_patient_cat_code :"+objPatientHospitalDtlVO_p.getPatPrimaryCatCode());
		System.out.println("p_gdt_entry_date :"+objPatientHospitalDtlVO_p.getEntryDate());
		System.out.println("p_gnum_seat_id :"+objUserVO_p.getSeatId());
		System.out.println("p_gnum_isvalid :"+Config.IS_VALID_ACTIVE);
		System.out.println("p_hrgstr_ip_add :"+objUserVO_p.getIpAddress());
		System.out.println("p_hrgnum_id_no :"+objPatientHospitalDtlVO_p.getPatIdNo());
		System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode());
		System.out.println("p_hrgdt_primarycat_validupto :"+objPatientHospitalDtlVO_p.getPatPrimaryCatValid());
		System.out.println("p_hrgdt_general_expdate :"+objPatientHospitalDtlVO_p.getPatGeneralExpDate());
		System.out.println("p_hrgdt_special_expdate :"+objPatientHospitalDtlVO_p.getPatSpecialExpDate());
		System.out.println("p_hrgdt_casuality_expdate :"+objPatientHospitalDtlVO_p.getPatCasualityExpDate());
		System.out.println("----------------------------------------------------");
		
		strProcName1 = "{call pkg_reg_dml.hrgt_patient_hospital_dtl(?,?::numeric,?::numeric,?::numeric,?,	?::numeric,?::numeric,?,?,?::numeric,	?,?::timestamp,?::timestamp,?::timestamp,?)}";
		
		nProcIndex1 = daoObj.setProcedure(strProcName1);
		
		HelperMethods.setNullToEmpty(objPatientHospitalDtlVO_p);
		objPatientHospitalDtlVO_p.setSeatId(objUserVO_p.getSeatId());
	    
		daoObj.setProcInValue(nProcIndex1, "p_modeVal", strMode_p,1);		
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",objPatientHospitalDtlVO_p.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code",objPatientHospitalDtlVO_p.getPatStatusCode(),3);
		daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code",objPatientHospitalDtlVO_p.getPatPrimaryCatCode(),4);
		daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date",objPatientHospitalDtlVO_p.getEntryDate(),5);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id",objUserVO_p.getSeatId(),6);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",objUserVO_p.getIpAddress(),8);
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no",objPatientHospitalDtlVO_p.getPatIdNo(),9);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),10);
		
		
		daoObj.setProcInValue(nProcIndex1, "p_hrgdt_primarycat_validupto",objPatientHospitalDtlVO_p.getPatPrimaryCatValid(),11);
		daoObj.setProcInValue(nProcIndex1, "p_hrgdt_general_expdate",objPatientHospitalDtlVO_p.getPatGeneralExpDate(),12);
		daoObj.setProcInValue(nProcIndex1, "p_hrgdt_special_expdate",objPatientHospitalDtlVO_p.getPatSpecialExpDate(),13);
		daoObj.setProcInValue(nProcIndex1, "p_hrgdt_casuality_expdate",objPatientHospitalDtlVO_p.getPatCasualityExpDate(),14);
		
		daoObj.setProcOutValue(nProcIndex1, "err", 1,15);
		
		daoObj.execute(nProcIndex1,1);			
		
		//strErr = daoObj.getString(nProcIndex1, "err");
			if (strErr == null)
				strErr = "";

				if (!strErr.equals("")) 
				{
					throw new Exception(strErr);
				}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return objPatientHospitalDtlVO_p;	
}
	

}
