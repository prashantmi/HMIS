package new_investigation.transactions.dao;

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


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Sequence;
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
import java.util.HashMap;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvestigationRequisitionBillDtlVO;
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
		String billNo="1";
		HisDAO hisDAO_p=new HisDAO("New Investigation", "InvestigationBillingDAO");
		final String strProcName = InvestigationDaoConfig.PROCEDURE_UPDATE_BILLING;
		final int nProcedureIndex;
		final String strDbErr;
		String ageUnit="";
		String patAge = "";
		String patAddress = "";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			
			patAddress = patVO.getPatAddress().replaceAll("\\s+","");
			//patVO.setPatAddress(patVO.getPatAddress().replaceAll("\\s+",""));
			patVO.setPatAge(patVO.getPatAge().replaceAll("\\s+",""));
			//patVO.setPatAge(patVO.getPatAge().replace("Yr",""));

			/*if(patVO.getPatAddress().equals(""))
				patVO.setPatAddress("0  ");*/
			
			if(patAddress.equals(""))
				patAddress = "0  ";
				
			if(patVO.getPatAge().equals(""))
				patVO.setPatAge("0  ");
			
			
			if(patVO.getPatAge() != null && !patVO.getPatAge().equals("")){
				
				patAge = patVO.getPatAge().split("\\s+")[0];
				
				//System.out.println("patAge : "+patAge);
				
				if(patAge.contains("Yr")){
					ageUnit = "1";
					patAge = patAge.substring(0, (patAge.length()-2));
				}else if(patAge.contains("Mth")){
					ageUnit = "2";
					patAge = patAge.substring(0, (patAge.length()-3));
				}else if(patAge.contains("Wk")){
					ageUnit = "3";
					patAge = patAge.substring(0, (patAge.length()-2));
				}else if(patAge.contains("D")){
					ageUnit = "4";
					patAge = patAge.substring(0, (patAge.length()-1));
				}
					
			}
			
			//System.out.println("patAge : "+patAge+ " : ageUnit : "+ageUnit);
			System.out.println("department code................"+voBillingDtl.getDeptCode());
			
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
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_patient_name",patVO.getPatFirstName()==null?"":patVO.getPatFirstName()+" "+patVO.getPatMiddleName()==null?"":patVO.getPatMiddleName(),11);
			//hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_pat_address",patVO.getPatAddress()==null?"":patVO.getPatAddress().split("\\s+")[0],12);  // For Temp Purpose.. need to change after
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_pat_address",patAddress==null?"":patAddress.split("\\s+")[0],12);  // For Temp Purpose.. need to change after
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_contact_no",patVO.getPatMobileNo()==null?"0":patVO.getPatMobileNo(),13);
			//hisDAO_p.setProcInValue(nProcedureIndex, "age",patVO.getPatAge()==null?"0":patVO.getPatAge().split("\\s+")[0],14);
			//hisDAO_p.setProcInValue(nProcedureIndex, "ageunit","",15);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "age",patVO.getPatAge()==null?"0":patAge,14);
			hisDAO_p.setProcInValue(nProcedureIndex, "ageunit",ageUnit,15);
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
			if(e.getMessage().contains("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!"))
			{
				patVO.setIsamountshort("1");
				throw new HisDataAccessException("Data Base Error:" + "Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!");
                       
				//System.out.println("");
			}
			else
			{
			throw new HisDataAccessException(e.getMessage());
			}
			
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

			System.out.println("voBillingDtl.getBillNo()======="+voBillingDtl.getBillNo() + voBillingDtl.getTariffId());
			
			
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



	public String makeBillingTestWisexray(InvestigationRequisitionBillDtlVO voBillingDtl,Inv_RequisitionRaisingPatientVO patVO,String tariffdetails,String tariffQty,String testGroupVal,UserVO userVO)
	{
		String errorMsg = "";
		String billNo="1";
		HisDAO hisDAO_p=new HisDAO("New Investigation", "InvestigationBillingDAO");
		final String strProcName = InvestigationDaoConfig.PROCEDURE_UPDATE_BILLING;
		final int nProcedureIndex;
		final String strDbErr;
		String ageUnit="";
		String patAge = "";
		String patAddress = "";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			
			patAddress = patVO.getPatAddress().replaceAll("\\s+","");
			//patVO.setPatAddress(patVO.getPatAddress().replaceAll("\\s+",""));
			patVO.setPatAge(patVO.getPatAge().replaceAll("\\s+",""));
			//patVO.setPatAge(patVO.getPatAge().replace("Yr",""));

			/*if(patVO.getPatAddress().equals(""))
				patVO.setPatAddress("0  ");*/
			
			if(patAddress.equals(""))
				patAddress = "0  ";
				
			if(patVO.getPatAge().equals(""))
				patVO.setPatAge("0  ");
			
			
			if(patVO.getPatAge() != null && !patVO.getPatAge().equals("")){
				
				patAge = patVO.getPatAge().split("\\s+")[0];
				
				//System.out.println("patAge : "+patAge);
				
				if(patAge.contains("Yr")){
					ageUnit = "1";
					patAge = patAge.substring(0, (patAge.length()-2));
				}else if(patAge.contains("Mth")){
					ageUnit = "2";
					patAge = patAge.substring(0, (patAge.length()-3));
				}else if(patAge.contains("Wk")){
					ageUnit = "3";
					patAge = patAge.substring(0, (patAge.length()-2));
				}else if(patAge.contains("D")){
					ageUnit = "4";
					patAge = patAge.substring(0, (patAge.length()-1));
				}
					
			}
			
			//System.out.println("patAge : "+patAge+ " : ageUnit : "+ageUnit);
			System.out.println("department code................"+voBillingDtl.getDeptCode());
			
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			hisDAO_p.setProcInValue(nProcedureIndex, "modval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "gnum_dept_code",voBillingDtl.getDeptCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_chargetype_id",voBillingDtl.getRequisitionType(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_service_id",testGroupVal,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "gstr_req_no",voBillingDtl.getRequisitionNos(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "gnum_treatment_cat",patVO.getPatCategoryCode(),6); //xrayyyyy
			hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_episode_code", patVO.getPatepisodecode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_puk",patVO.getPatCRNo(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "gstr_tariff",tariffdetails,9);
			hisDAO_p.setProcInValue(nProcedureIndex, "gstr_reqqty",tariffQty,10);			
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_patient_name",patVO.getPatFirstName()==null?"":patVO.getPatFirstName()+" "+patVO.getPatMiddleName()==null?"":patVO.getPatMiddleName(),11);
			//hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_pat_address",patVO.getPatAddress()==null?"":patVO.getPatAddress().split("\\s+")[0],12);  // For Temp Purpose.. need to change after
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_pat_address",patAddress==null?"":patAddress.split("\\s+")[0],12);  // For Temp Purpose.. need to change after
			hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_contact_no",patVO.getPatMobileNo()==null?"0":patVO.getPatMobileNo(),13);
			//hisDAO_p.setProcInValue(nProcedureIndex, "age",patVO.getPatAge()==null?"0":patVO.getPatAge().split("\\s+")[0],14);
			//hisDAO_p.setProcInValue(nProcedureIndex, "ageunit","",15);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "age",patVO.getPatAge()==null?"0":patAge,14);
			hisDAO_p.setProcInValue(nProcedureIndex, "ageunit",ageUnit,15);
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
			if(e.getMessage().contains("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!"))
			{
				patVO.setIsamountshort("1");
				throw new HisDataAccessException("Data Base Error:" + "Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!");
                       
				//System.out.println("");
			}
			else
			{
			throw new HisDataAccessException(e.getMessage());
			}
			
			}
		return billNo;
	}

	
	
	public void insertviewsdetail(String testcode,String viewcode,String viewcount,String reqno, String reqdno, UserVO voo,int toalcountviews,String type) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT_VIEW_DETAILS_HIVT_REQ_VIEW_DETAILS";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			
			populateMAP.put(sq.next(),testcode);
			populateMAP.put(sq.next(),viewcode==null?"":viewcode);
			populateMAP.put(sq.next(),viewcount);

			populateMAP.put(sq.next(),reqno);
			populateMAP.put(sq.next(),reqdno);
			populateMAP.put(sq.next(),voo.getHospitalCode());
			populateMAP.put(sq.next(),Integer.toString(toalcountviews));
			populateMAP.put(sq.next(),type);  //1-extra_charge_test //2-no count //3-count view

        	
            	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
		}

	}
	
	
}





