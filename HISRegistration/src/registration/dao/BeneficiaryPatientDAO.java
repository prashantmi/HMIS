package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
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
import vo.registration.BeneficiaryPatientVO;
import vo.registration.PatientVO;
import vo.registration.RenewalConfigVO;

/**
 * PatientDAO is a class which describes all the methods required for database interaction for HRGT_PATIENT_DTL table, for example, insert, update, select
 * and delete. PatientDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class BeneficiaryPatientDAO extends RegistrationDAO {

	Logger log;
	/**
	 * Creates a new PatientDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public BeneficiaryPatientDAO(TransactionContext _transactionContext) 
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
	public void savePatientBeneficiaryDtl(HisDAO daoObj,BeneficiaryPatientVO objbenPatientVO_p, UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			strProcName2 = "{call pkg_reg_dml.dml_hrgt_beneficiary_pat_dtl(?,?,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::timestamp without time zone,?::character varying,		?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(objbenPatientVO_p);			
			
			System.out.println("----------------------------------------------------");
			System.out.println("PatientDAO :: savePatientBeneficiaryDtl()");
			System.out.println("p_modeVal :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+objbenPatientVO_p.getPatCrNo());
			System.out.println("p_hrgnum_company_code :"+ objbenPatientVO_p.getClientCode());
			System.out.println("p_hrgstr_company_name :"+ objbenPatientVO_p.getClientName());
			System.out.println("p_hrgnum_patient_cat_code :"+ objbenPatientVO_p.getPatPrimaryCatCode());
			System.out.println("p_hrgstr_card_no :"+ objbenPatientVO_p.getCardNo());
			System.out.println("p_hrgstr_card_holder_name :"+ objbenPatientVO_p.getStaffCardName());
			System.out.println("p_hrgnum_seat_id :"+ objUserVO_p.getSeatId());
			
			System.out.println("p_hrgnum_is_dependent :"+ objbenPatientVO_p.getIsDependent());
			System.out.println("p_hrgnum_dependent_relation_code :"+ objbenPatientVO_p.getDependentRelationCode());
			System.out.println("p_hrgstr_dependent_relation :"+ objbenPatientVO_p.getDependentRelation());
			System.out.println("p_hrgstr_dependent_name :"+ objbenPatientVO_p.getDependentName());
			System.out.println("p_hrgnum_card_district_code :"+ objbenPatientVO_p.getAgsDistrictCode()); 
			System.out.println("p_hrgnum_card_validupto :"+ objbenPatientVO_p.getCardvalidityDate()); 
			System.out.println("p_hrgnum_counter_no :"+ objbenPatientVO_p.getAgsCounterNo()); 
		
			System.out.println("p_hrgnum_hospital_code :"+ objUserVO_p.getHospitalCode()); 

			System.out.println("----------------------------------------------------");
			
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk",objbenPatientVO_p.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_company_code", objbenPatientVO_p.getClientCode(),3);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_company_name", objbenPatientVO_p.getClientName(),4);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_patient_cat_code", objbenPatientVO_p.getPatPrimaryCatCode(),5);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_card_no", objbenPatientVO_p.getCardNo(),6);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_card_holder_name", objbenPatientVO_p.getStaffCardName(),7);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_seat_id", objUserVO_p.getSeatId(),8);
		
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_dependent", objbenPatientVO_p.getIsDependent(),9);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_dependent_relation_code", objbenPatientVO_p.getDependentRelationCode(),10);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_dependent_relation", objbenPatientVO_p.getDependentRelation(),11);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_dependent_name", objbenPatientVO_p.getDependentName(),12); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_card_district_code", objbenPatientVO_p.getAgsDistrictCode(),13); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_card_validupto", objbenPatientVO_p.getCardvalidityDate(),14); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_counter_no",objbenPatientVO_p.getAgsCounterNo(),15);

			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_hospital_code", objUserVO_p.getHospitalCode(),16); 
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isvalid",Config.IS_VALID_ACTIVE,17);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,18);

			
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
	
	
	public BeneficiaryPatientVO retrieveBeneficiaryDtlsByCrNo(String _crNo_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		/* Start- Sheel Darshi
		 * Reason- Changed for Getting patient bebeficiary detail from seprate table
		 * 14th June 2016
		 * */
		//String strProcName = "{call Pkg_Reg_View.PROC_HRGT_BENEFICIARY_PAT_DTL_WITH_CRNO(?,?,?,?,?,?)}";
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_CREDIT_PAT_DTL_WITH_CRNO(?,?,?,?,?,?)}";
		//end- sheeldarshi
		
		int nProcIndex = 0;
		String strErr="",strMode = "1";
		BeneficiaryPatientVO _benPatVO= new BeneficiaryPatientVO();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_mode", strMode,1);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_crno", _crNo_p,3);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}


		try {
			if (!rs.next()) {
				// Start : surabhi for resolving the bug
				throw new HisRecordNotFoundException("Beneficiary Patient Details Not Found");
			} else {
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_benPatVO, rs);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return _benPatVO;
	}
	/*Start:Sheeldarshi
	Reason:prevent user from registering patients with same arogyashri number. 
	Date:03-July-15
	*/
	public String checkAarogyashriIdDuplicy(UserVO objUserVO_p, BeneficiaryPatientVO objPatBenlDtlVO_p)
	{
		String strUniqueRefLetterFlag="0";
		int funcIndex=0;
		HisDAO daoObj=null;
		
		try 
		{
			HelperMethods.setNullToEmpty(objPatBenlDtlVO_p);
			daoObj = new HisDAO("Registration","EssentialDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_check_beneficiary_without_reference_duplicacy(?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, objPatBenlDtlVO_p.getAgsNo());		// p_aadhar_no
			daoObj.setFuncInValue(funcIndex, 4, objPatBenlDtlVO_p.getPatCrNo());	
			daoObj.setFuncInValue(funcIndex, 5, objUserVO_p.getHospitalCode());
			
			daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strUniqueRefLetterFlag = daoObj.getFuncNumeric(funcIndex).toString();		
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("RegEssentialDAO.checkAarogyashriIdDuplicy()" + e);
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
	//End
	
	
	//Start:Sheeldarshi
		//Reason:Store letter type and credit limit in new table
		/*public void savePatientCreditDtl(HisDAO daoObj,BeneficiaryPatientVO objbenPatientVO_p, UserVO objUserVO_p,String strMode_p) {

			String strErr = "";
			int nProcIndex2 = 0;
			String strProcName2="";
			
			try 
			{
				
				strProcName2 = "{call pkg_reg_dml.dml_hrgt_credit_pat_dtl(?,?,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::timestamp without time zone,?::character varying,		?,?,?::numeric ,?::character varying,?::timestamp without time zone,?::timestamp without time zone,?::numeric,?)}";


				nProcIndex2 = daoObj.setProcedure(strProcName2);			
				HelperMethods.setNullToEmpty(objbenPatientVO_p);	
				
				daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk",objbenPatientVO_p.getPatCrNo(),2);
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_company_code", objbenPatientVO_p.getClientCode(),3);
				daoObj.setProcInValue(nProcIndex2, "p_hrgstr_company_name", objbenPatientVO_p.getClientName(),4);
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_patient_cat_code", objbenPatientVO_p.getPatPrimaryCatCode(),5);
				daoObj.setProcInValue(nProcIndex2, "p_hrgstr_card_no", objbenPatientVO_p.getCardNo(),6);
				daoObj.setProcInValue(nProcIndex2, "p_hrgstr_card_holder_name", objbenPatientVO_p.getStaffCardName(),7);
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_seat_id", objUserVO_p.getSeatId(),8);
			
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_dependent", objbenPatientVO_p.getIsDependent(),9);
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_dependent_relation_code", objbenPatientVO_p.getDependentRelationCode(),10);
				daoObj.setProcInValue(nProcIndex2, "p_hrgstr_dependent_relation", objbenPatientVO_p.getDependentRelation(),11);
				daoObj.setProcInValue(nProcIndex2, "p_hrgstr_dependent_name", objbenPatientVO_p.getDependentName(),12); 
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_card_district_code", objbenPatientVO_p.getAgsDistrictCode(),13); 
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_card_validupto", objbenPatientVO_p.getCardvalidityDate(),14); 
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_counter_no",objbenPatientVO_p.getAgsCounterNo(),15);

				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_hospital_code", objUserVO_p.getHospitalCode(),16); 
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isvalid",Config.IS_VALID_ACTIVE,17);
				
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_credit_letter_type", objbenPatientVO_p.getLetterType(),18); 
				daoObj.setProcInValue(nProcIndex2, "p_hblstr_credit_letter_ref_no",objbenPatientVO_p.getCreditLetterRefNo(),19);
				daoObj.setProcInValue(nProcIndex2, "p_hbldt_credit_letter_date", objbenPatientVO_p.getCreditLetterDate(),20); 
				daoObj.setProcInValue(nProcIndex2, "p_hbldt_credit_letter_validupto",objbenPatientVO_p.getCardvalidityDate(),21);
				daoObj.setProcInValue(nProcIndex2, "p_hrgnum_credit_limit",objbenPatientVO_p.getCreditLimit(),22);

				daoObj.setProcOutValue(nProcIndex2, "err", 1,23);

				
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
		}*/
		//End
	
	//Start:Sheeldarshi
			//Reason:Store letter type and credit limit in new table
			public void savePatientCreditDtl(HisDAO daoObj,BeneficiaryPatientVO objbenPatientVO_p, UserVO objUserVO_p,String strMode_p) {

				String strErr = "";
				int nProcIndex2 = 0;
				String strProcName2="";
				
				try 
				{
					
					strProcName2 = "{call pkg_reg_dml.dml_hrgt_credit_pat_dtl(?,?,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::timestamp without time zone,?::character varying,		?,?,?::numeric ,?::character varying,?::timestamp without time zone,?::timestamp without time zone,?::numeric,?::numeric,?)}";


					nProcIndex2 = daoObj.setProcedure(strProcName2);			
					HelperMethods.setNullToEmpty(objbenPatientVO_p);	
					
					daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk",objbenPatientVO_p.getPatCrNo(),2);
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_company_code", objbenPatientVO_p.getClientCode().equals("")?"-1":objbenPatientVO_p.getClientCode(),3);
					daoObj.setProcInValue(nProcIndex2, "p_hrgstr_company_name", objbenPatientVO_p.getClientName(),4);
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_patient_cat_code", objbenPatientVO_p.getPatPrimaryCatCode(),5);
					daoObj.setProcInValue(nProcIndex2, "p_hrgstr_card_no", objbenPatientVO_p.getCardNo(),6);
					daoObj.setProcInValue(nProcIndex2, "p_hrgstr_card_holder_name", objbenPatientVO_p.getStaffCardName(),7);
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_seat_id", objUserVO_p.getSeatId(),8);
				
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_is_dependent", objbenPatientVO_p.getIsDependent(),9);
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_dependent_relation_code", objbenPatientVO_p.getDependentRelationCode(),10);
					daoObj.setProcInValue(nProcIndex2, "p_hrgstr_dependent_relation", objbenPatientVO_p.getDependentRelation(),11);
					daoObj.setProcInValue(nProcIndex2, "p_hrgstr_dependent_name", objbenPatientVO_p.getDependentName(),12); 
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_card_district_code", objbenPatientVO_p.getAgsDistrictCode(),13); 
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_card_validupto", objbenPatientVO_p.getCardvalidityDate(),14); 
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_counter_no",objbenPatientVO_p.getAgsCounterNo(),15);

					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_hospital_code", objUserVO_p.getHospitalCode(),16); 
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isvalid",Config.IS_VALID_ACTIVE,17);
					
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_credit_letter_type", objbenPatientVO_p.getLetterType(),18); 
					daoObj.setProcInValue(nProcIndex2, "p_hblstr_credit_letter_ref_no",objbenPatientVO_p.getCreditLetterRefNo(),19);
					daoObj.setProcInValue(nProcIndex2, "p_hbldt_credit_letter_date", objbenPatientVO_p.getCreditLetterDate(),20); 
					daoObj.setProcInValue(nProcIndex2, "p_hbldt_credit_letter_validupto",objbenPatientVO_p.getCardvalidityDate(),21);
					daoObj.setProcInValue(nProcIndex2, "p_hrgnum_credit_limit",objbenPatientVO_p.getCreditLimit(),22);
					daoObj.setProcInValue(nProcIndex2, "p_actual_amount", objbenPatientVO_p.getPatActualAmount(),23);//added by mukund on 26.07.2016

					daoObj.setProcOutValue(nProcIndex2, "err", 1,24);

					
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
			//End
		
		
		public static BeneficiaryPatientVO[] retrieveCreditBeneficiaryDtlsByCrNo(String _crNo_p,  UserVO objUserVO_p,String mode) {
			
			WebRowSet rs = null;
			HisDAO daoObj = null;
			String strProcName = "{call Pkg_Reg_View.PROC_HRGT_CREDIT_PAT_DTL_WITH_CRNO(?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr="",strMode = "1";
			BeneficiaryPatientVO[] _benPatVO;
			try 
			{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", strMode,1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_crno", _crNo_p,3);
			daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");

			strErr = daoObj.getString(nProcIndex, "err");
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}

			ValueObject[] valueObject = {};
			try {
				if (!rs.next()) {
					throw new HisRecordNotFoundException("Benficiary Patient Details Not Found");
				} else {
					rs.beforeFirst();
					valueObject = HelperMethods.populateVOfrmRS(BeneficiaryPatientVO.class, rs);
					_benPatVO=new BeneficiaryPatientVO[valueObject.length];
					
					for(int i=0;i<valueObject.length;i++)
					{
						_benPatVO[i]=(BeneficiaryPatientVO)valueObject[i];
					}				
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (e.getClass() == HisRecordNotFoundException.class) {
					throw new HisRecordNotFoundException(e.getMessage());
				} else
					throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
			}
			finally 
			{
				if (daoObj != null) 
				{
					daoObj.free();
					daoObj = null;
				}
			}
			return _benPatVO;
		}
	
}//end of class
