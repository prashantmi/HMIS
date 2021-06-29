package registration.dao;

import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import vo.registration.RenewalDetailVO;

public class RenewalDetailDAO extends DataAccessObject {

	public RenewalDetailDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	
	public void saveRenewalDtl(HisDAO daoObj,RenewalDetailVO objRenewalDetailVO_p, UserVO objUserVO_p,String strMode_p) {		
		
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_renew_dtl(?,?::numeric,?::numeric,?,?,	?::numeric,?::numeric,?,?::numeric,?::numeric,	?)}";
			HelperMethods.setNullToEmpty(objRenewalDetailVO_p);
			
			// strMode_p = 1 ---> createForOldPatientRenewal
			if(strMode_p==null || strMode_p.equals(""))
				strMode_p="1";

			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			/////////////////////
			System.out.println("------------------------------------");
			System.out.println("RenewalDetailDAO :: saveRenewalDtl()");
			System.out.println("p_modeVal: "+ strMode_p);
			System.out.println("p_hrgnum_puk: "+ objRenewalDetailVO_p.getPatCrNo());
			System.out.println("hrgnum_renewal_type: "+objRenewalDetailVO_p.getRenewalType());
			System.out.println("p_hrgdt_old_expiry: "+ objRenewalDetailVO_p.getOldExpiryDate());
			System.out.println("p_hrgdt_new_expiry: "+objRenewalDetailVO_p.getNewExpiryDate());
			System.out.println("p_gnum_dept_code: "+ objRenewalDetailVO_p.getDepartmentCode());
			System.out.println("p_hgnum_deptunitcode: "+objRenewalDetailVO_p.getDepartmentUnitCode());
			System.out.println("p_hrgstr_ip_add: "+ objRenewalDetailVO_p.getSystemIPAddress());			
			System.out.println("p_gnum_seat_id: "+ objRenewalDetailVO_p.getSeatId());
			System.out.println("p_gnum_hospital_code: "+ objUserVO_p.getHospitalCode());
			/////////////////////
						
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", objRenewalDetailVO_p.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "hrgnum_renewal_type",objRenewalDetailVO_p.getRenewalType(),3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_old_expiry", objRenewalDetailVO_p.getOldExpiryDate(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_new_expiry",objRenewalDetailVO_p.getNewExpiryDate(),5);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", objRenewalDetailVO_p.getDepartmentCode(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objRenewalDetailVO_p.getDepartmentUnitCode(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objRenewalDetailVO_p.getSystemIPAddress(),8);			
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", objRenewalDetailVO_p.getSeatId(),9);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),10);
			daoObj.setProcOutValue(nProcIndex1, "err",1,11);
			
			//daoObj.executeProcedure(nProcIndex1);		
			daoObj.execute(nProcIndex1, 1);
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			
	}

}
