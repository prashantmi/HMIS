package registration.dao;


import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.AddressVO;
import vo.registration.CreditAvailDetailVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.RenewalConfigVO;

/**
 * PatientDAO is a class which describes all the methods required for database interaction for HRGT_PATIENT_DTL table, for example, insert, update, select
 * and delete. PatientDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class CreditAvailDtlDAO extends RegistrationDAO {

	Logger log;
	/**
	 * Creates a new PatientDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public CreditAvailDtlDAO(TransactionContext _transactionContext) 
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}
		
	// CRUD function
	/**
	 * 
	 * Creates a new patient entry in DB for a Beneficiary Patient Dtl Table. 
	 * @param daoObj
	 * @param objPatientVO_p	Provides patient details to be entered.
	 * @param objUserVO_p		Provides User details.
	 * @param strMode_p , 		strMode_p =1 -->> To insert into DataBase
	 * 							
	 * @return	PatientVO with values stored in DB.
	 */
	public void saveCreditTarriffAvailDtl(HisDAO daoObj,CreditAvailDetailVO objCreditAvailDtl_p, UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			strProcName2 = "{call pkg_reg_dml.dml_hblt_credit_tariff_avail_dtl(?,?,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,	?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,	?,?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(objCreditAvailDtl_p);			
			
			System.out.println("----------------------------------------------------");
			System.out.println("CreditAvailDtlDAO :: saveCreditTarriffAvailDtl()");
			System.out.println("p_modeVal :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+objCreditAvailDtl_p.getPatCrNo());
			System.out.println("p_hrgnum_letter_no :"+ objCreditAvailDtl_p.getCreditLetterRefNo());
			System.out.println("p_hrgnum_tafiff_id :"+ objCreditAvailDtl_p.getTariffId());
			System.out.println("p_hrgnum_group_id :"+ objCreditAvailDtl_p.getPatPrimaryCatCode());
			System.out.println("p_hrgnum_bill_no :"+ objCreditAvailDtl_p.getBillNo());
			System.out.println("p_hrgnum_receipt_no :"+ objCreditAvailDtl_p.getRecieptNo());
			System.out.println("p_hrgnum_receipt_type :"+ objCreditAvailDtl_p.getRecieptType());
			
			System.out.println("p_hrgnum_bill_qty :"+ objCreditAvailDtl_p.getBillQty());
			System.out.println("p_hrgnum_processesd_qty :"+ objCreditAvailDtl_p.getProcessedQty());
			System.out.println("p_hrgnum_remained_qty :"+ objCreditAvailDtl_p.getRemainedQty());
			System.out.println("p_hrgnum_qty_unitId :"+ objCreditAvailDtl_p.getQtyUnitId());
			System.out.println("p_hrgnum_patcat_code :"+ objCreditAvailDtl_p.getPatPrimaryCatCode()); 
			System.out.println("p_hrgnum_bservice_id :"+ objCreditAvailDtl_p.getBillServiceId()); 
			System.out.println("p_hrgnum_service_id :"+ objCreditAvailDtl_p.getServiceId()); 
			System.out.println("p_hrgstr_tariff :"+ objCreditAvailDtl_p.getTariff()); 
			System.out.println("p_hrgnum_ispackage :"+ objCreditAvailDtl_p.getIsPackage()); 
			System.out.println("p_hrgnum_actual_rate :"+ objCreditAvailDtl_p.getTariffActualRate()); 
			System.out.println("p_hrgnum_rate_unit_code :"+ objCreditAvailDtl_p.getRateUnitCode()); 
			System.out.println("p_hrgnum_net_amt :"+ objCreditAvailDtl_p.getNetAmount()); 
			System.out.println("p_hrgnum_paid_by_patamt :"+ objCreditAvailDtl_p.getPaidByPatAmt()); 
			System.out.println("p_hrgnum_paid_by_clientamt :"+ objCreditAvailDtl_p.getPaidByClientAmt()); 
			System.out.println("p_hrgstr_approved_by :"+ objCreditAvailDtl_p.getCreditApprovedBy()); 
			System.out.println("p_hrgstr_remarks :"+ objCreditAvailDtl_p.getRemarks()); 

			System.out.println("p_hrgnum_seatid :"+ objUserVO_p.getSeatId()); 
			System.out.println("p_hrgnum_hospital_code :"+ objUserVO_p.getHospitalCode()); 
			System.out.println("p_hrgnum_isvalid :"+ Config.IS_VALID_ACTIVE); 
			System.out.println("p_hrgnum_clientcode :"+ objCreditAvailDtl_p.getClientCode()); 

			System.out.println("----------------------------------------------------");
			
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk",objCreditAvailDtl_p.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_letter_no", objCreditAvailDtl_p.getCreditLetterRefNo(),3);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_tafiff_id", objCreditAvailDtl_p.getTariffId(),4);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_group_id", objCreditAvailDtl_p.getGroupId(),5);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_bill_no", objCreditAvailDtl_p.getBillNo(),6);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_receipt_no", objCreditAvailDtl_p.getRecieptNo(),7);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_receipt_type", objCreditAvailDtl_p.getRecieptType(),8);
		
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_bill_qty", objCreditAvailDtl_p.getBillQty(),9);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_processesd_qty", objCreditAvailDtl_p.getProcessedQty(),10);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_remained_qty", objCreditAvailDtl_p.getRemainedQty(),11);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_qty_unitId", objCreditAvailDtl_p.getQtyUnitId(),12); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_patcat_code", objCreditAvailDtl_p.getPatPrimaryCatCode(),13); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_bservice_id", objCreditAvailDtl_p.getBillServiceId(),14); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_service_id", objCreditAvailDtl_p.getServiceId(),15); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_tariff", objCreditAvailDtl_p.getTariff(),16); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_ispackage",objCreditAvailDtl_p.getIsPackage(),17);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_actual_rate",objCreditAvailDtl_p.getTariffActualRate(),18);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_rate_unit_code",objCreditAvailDtl_p.getRateUnitCode(),19);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_net_amt",objCreditAvailDtl_p.getNetAmount(),20);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_paid_by_patamt",objCreditAvailDtl_p.getPaidByPatAmt(),21);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_paid_by_clientamt",objCreditAvailDtl_p.getPaidByClientAmt(),22);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_approved_by",objCreditAvailDtl_p.getCreditApprovedBy(),23);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_remarks",objCreditAvailDtl_p.getRemarks(),24);

			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_seatid",objUserVO_p.getSeatId(),25);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_hospital_code",objUserVO_p.getHospitalCode(),26);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isvalid",Config.IS_VALID_ACTIVE,27);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_clientcode",objCreditAvailDtl_p.getClientCode(),28);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,29);

			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
	
	public String checkBeneficiaryIdDuplicy(UserVO objUserVO_p, CreditAvailDetailVO objCreditAvailDtlVO_p)
	{
		String strUniqueRefLetterFlag="0";
		int funcIndex=0;
		HisDAO daoObj=null;
		
		try 
		{
			HelperMethods.setNullToEmpty(objCreditAvailDtlVO_p);
			daoObj = new HisDAO("Registration","CreditAvailDtlDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_check_beneficiary_duplicacy(?,?,?,?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, objCreditAvailDtlVO_p.getCreditLetterRefNo());					// p_aadhar_no
			daoObj.setFuncInValue(funcIndex, 4, objCreditAvailDtlVO_p.getTariffId());	
			daoObj.setFuncInValue(funcIndex, 5, objCreditAvailDtlVO_p.getPatCrNo());
			daoObj.setFuncInValue(funcIndex, 6, objCreditAvailDtlVO_p.getClientCode());
			daoObj.setFuncInValue(funcIndex, 7, objUserVO_p.getHospitalCode());
			daoObj.setFuncInValue(funcIndex, 8, objCreditAvailDtlVO_p.getCreditLetterDate()); //edited by sandip naik on 18 May,2017 
            //end by sandip naik
			daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strUniqueRefLetterFlag = daoObj.getFuncNumeric(funcIndex).toString();		
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("RegEssentialDAO.checkBeneficiaryIdDuplicy()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return strUniqueRefLetterFlag;
	}
	
	
}//end of class
