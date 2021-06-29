package investigationDesk.transactions.dao;

/**
 * @author : C-DAC, Noida
 * Project : HISInvestigationG5
 * Module  : Blood Bank
 * Created On : 18 Aug, 2008
 * 
 * Developed By : For Common Use
 * 
 * Purpose : This Class should be used for all Essential Data Access Data Layer Methods 
 * 			regarding all processes
 * 
 * Modified By: Pawan Kumar B N
 * 
 * Modified On: 18-11-2011
 */


import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
/*import hisglobal.vo.FormAReportVO;
import hisglobal.vo.FormCReportVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.QualityControlMstVO;
import hisglobal.vo.QualityParameterMappingVO;
import hisglobal.vo.RefreshmentItemMstVO;
import hisglobal.vo.ScreeningOfOtherTTDReportVO;
import hisglobal.vo.TherapeuticPatientDtlVO;*/
//import hisglobal.vo.TherapeuticTypeMstVO;
import hisglobal.vo.UserVO;

import java.sql.Connection;

import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.InvestigationRequisitionBillDtlVO;
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/
//import hisglobal.vo.VoluntaryCardDtlVO;


public class InvestigationBillingDAO extends DataAccessObject implements InvestigationBillingDAOi
{
	public InvestigationBillingDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	public String makeBillingTestWise(InvestigationRequisitionBillDtlVO voBillingDtl,Inv_RequisitionRaisingPatientVO patVO,String tariffdetails,String tariffQty,String testGroupVal,UserVO userVO)
	{
		String errorMsg = "";
		String billNo="";
		HisDAO hisDAO_p=new HisDAO("New Investigation", "InvestigationBillingDAO");
		final String strProcName = InvestigationDaoConfig.PROCEDURE_UPDATE_BILLING;
		final int nProcedureIndex;
		final String strDbErr;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			
			
			patVO.setPatAddress(patVO.getPatAddress().replaceAll("\\s+",""));
		patVO.setPatAge(patVO.getPatAge().replaceAll("\\s+",""));
		if(patVO.getPatAddress().equals(""))
			patVO.setPatAddress("0  ");
		if(patVO.getPatAge().equals(""))
			patVO.setPatAge("0  ");
		
		
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			hisDAO_p.setProcInValue(nProcedureIndex, "modval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "gnum_dept_code",voBillingDtl.getDeptCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_chargetype_id",voBillingDtl.getRequisitionType(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_service_id",testGroupVal,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "gstr_req_no",voBillingDtl.getRequisitionNos(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "gnum_treatment_cat",patVO.getPatCategoryCode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_episode_code", patVO.getPatepisodecode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_puk",patVO.getPatCRNo(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "gstr_tariff",tariffdetails,9);
			hisDAO_p.setProcInValue(nProcedureIndex, "gstr_reqqty",tariffQty,10);			
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_patient_name",patVO.getPatFirstName()+" "+patVO.getPatMiddleName(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_pat_address",patVO.getPatAddress().split("\\s+")[0],12);  // For Temp Purpose.. need to change after
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_contact_no",patVO.getPatMobileNo(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "age",patVO.getPatAge().split("\\s+")[0],14);
			hisDAO_p.setProcInValue(nProcedureIndex, "ageunit","",15);
			hisDAO_p.setProcInValue(nProcedureIndex, "gender",patVO.getPatGenderCode(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "refdoctor",(patVO.getConsultantName()==null?"":patVO.getConsultantName()),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "refdoccontactno","",18);
			hisDAO_p.setProcInValue(nProcedureIndex, "gnum_seatid",userVO.getSeatId(),19);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code",userVO.getHospitalCode(),20);
			hisDAO_p.setProcInValue(nProcedureIndex, "ward_code",(patVO.getPatwardcode()==null?"":patVO.getPatwardcode()),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "admno",(patVO.getPatadmissionno()==null?"":patVO.getPatadmissionno()),22);
			hisDAO_p.setProcInValue(nProcedureIndex, "visitno",(patVO.getPatVisitNo()==null?"":patVO.getPatVisitNo()),23);
			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,24); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Billing Done Successussfully"+strDbErr);

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}

		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		return billNo;
	}
	
	public void updateBillingQty(InvestigationRequisitionBillDtlVO voBillingDtl,String tariffId,String serviceId,UserVO userVO)
	{
		String errorMsg = "";
		HisDAO hisDAO_p=new HisDAO("New Investigation", "InvestigationBillingDAO");
		final String strProcName = InvestigationDaoConfig.PROCEDURE_UPDATE_BILLING_DTY_SAMPLE_COLLECTTION;
		final int nProcedureIndex;
		final String strDbErr;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code",userVO.getHospitalCode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "trfid",voBillingDtl.getTariffId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "serviceid",voBillingDtl.getServiceId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "billno",voBillingDtl.getBillNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "consqty",voBillingDtl.getConsQty(),5);
						

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "rowupdated", 1,7); 
			

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Billing Done Successussfully");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}

		catch (Exception e)
		{
			e.printStackTrace();
			
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
	}
}





