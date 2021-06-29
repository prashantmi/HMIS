/**
 * 
 */
package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.PatCategoryVO;

/**
 * @author s.singaravelan
 *
 */
public class PatCategoryDAO
{
	
	//To Save the Patient Category in the Patient Category Mst
	public static String savePatCategoryDetails(PatCategoryVO objModelPatCat,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCATEGORY_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelPatCat);			
			String strVerificationDoc[]=objModelPatCat.getStrIdVerificationDoc().split("#");
			if(objModelPatCat.getStrHospitalCode().equalsIgnoreCase(Config.SUPER_HOSPITAL_CODE))
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			else
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_catcode",objModelPatCat.getStrPatCategoryCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_cattype",objModelPatCat.getStrPatCategoryType(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_catname",objModelPatCat.getStrPatCategoryName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_catshort",objModelPatCat.getStrPatCategoryShort(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_estatus",objModelPatCat.getStrEconomicStatus(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_priority",objModelPatCat.getStrCatPriority(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ispaid",objModelPatCat.getStrIsPaid(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isidrequire",objModelPatCat.getStrIsIdReq(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_idsize",objModelPatCat.getStrIdSize(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_validationtype",objModelPatCat.getStrIdValidationType(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isexpiry",objModelPatCat.getStrIsExpiry(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_expirydays",objModelPatCat.getStrExpiryDays(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_expirymnth",objModelPatCat.getStrExpiryMonth(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_benefit",objModelPatCat.getStrBenefit(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_criteria",objModelPatCat.getStrCriteria(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelPatCat.getStrRemarks(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_catgroup",objModelPatCat.getStrPatCategoryGroup(),18);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,19);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),20);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelPatCat.getStrHospitalCode(),21);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_document_code",strVerificationDoc[0],22);
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
			 */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isapproval_req",objModelPatCat.getStrIsApprovalRequired(),23);
			//End:Sheeldarshi
			//Changes done for Client combo by Surabhi on 26th july 2016
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_default_client_code",objModelPatCat.getStrPatClient(),24);
			//End
			/**By Mukund on 06.Nov.17*/
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isspecial_category",objModelPatCat.getStrIsCatSpl(),25);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_departments_bound",objModelPatCat.getStrBoundDeptCode(),26);
			/**End On 06.Nov.17*/
			/**By Vasu on 15.May.18*/
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_default",objModelPatCat.getStrIsDefault(),27);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_applicable_service_code",objModelPatCat.getStrApplicableServicesCode(),28);
			/**End On 15.May.18*/
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,29); 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Inserted Successussfully");
			
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
			
		}

		catch (Exception e)
		{
			e.printStackTrace();
			objModelPatCat.setStrMsgString("PatientCtegoryDAO.getStoreName() --> "	+ e.getMessage());
			objModelPatCat.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	

	//To get Patient Category Details in Modify
	public static PatCategoryVO modifyDetails(PatCategoryVO objModelPatCat,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCATEGORY_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;
		
		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelPatCat);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_catcode",objModelPatCat.getStrPatCategoryCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_catname",objModelPatCat.getStrPatCategoryName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelPatCat.getStrHospitalCode(),4);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelPatCat.getStrHospitalCode(),4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objModelPatCat.setStrMsgString("RequestdetailDAO.getStoreName() --> "	+ e.getMessage());
			objModelPatCat.setStrMsgType("1");
		}
		
		
			try
			{
				webRowSet.beforeFirst();
				webRowSet.next();
				objModelPatCat.setStrPatCategoryCode(webRowSet.getString(1));
				objModelPatCat.setStrPatCategoryName(webRowSet.getString(2));
				objModelPatCat.setStrPatCategoryShort(webRowSet.getString(3));
				objModelPatCat.setStrPatCategoryType(webRowSet.getString(4));
				objModelPatCat.setStrIsPaid(webRowSet.getString(5));
				objModelPatCat.setStrIdSize(webRowSet.getString(6));
				objModelPatCat.setStrCatPriority(webRowSet.getString(7));
				objModelPatCat.setStrIsExpiry(webRowSet.getString(8));
				objModelPatCat.setStrIdValidationType(webRowSet.getString(9));
				objModelPatCat.setStrEconomicStatus(webRowSet.getString(10));
				objModelPatCat.setStrExpiryMonth(webRowSet.getString(11));
				objModelPatCat.setStrExpiryDays(webRowSet.getString(12));
				objModelPatCat.setStrBenefit(webRowSet.getString(13));
				objModelPatCat.setStrCriteria(webRowSet.getString(14));
				objModelPatCat.setStrIsIdReq(webRowSet.getString(15));
				objModelPatCat.setStrHospitalCode(webRowSet.getString(16));
				//objModelPatCat.setStrIsValid(webRowSet.getString(17));
				objModelPatCat.setStrSeatId(webRowSet.getString(18));
				objModelPatCat.setStrRemarks(webRowSet.getString(19));
				objModelPatCat.setStrPatCategoryGroup(webRowSet.getString(20));
				objModelPatCat.setStrGlobalPatCategoryName(webRowSet.getString(21));
				objModelPatCat.setStrIdVerificationDoc(webRowSet.getString(22));
				objModelPatCat.setStrPatClient(webRowSet.getString(23));
				objModelPatCat.setStrIsCatSpl(webRowSet.getString(24));
				objModelPatCat.setStrBoundDeptCode(webRowSet.getString(25));
				objModelPatCat.setStrIsDefault(webRowSet.getString(26));
				objModelPatCat.setStrApplicableServicesCode(webRowSet.getString(27));			
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("RequestdetailDAO:getStoreName():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
		return objModelPatCat;
		
	}
	
	//To Update Patient Category Details
	public static void updatePatCategoryDetails(PatCategoryVO objModelPatCat,HisDAO hisDAO_p,UserVO uservo ) {

		
		final String strProcName =  RegistrationDaoConfig.PROCEDURE_PATCATEGORY_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strVerificationDoc[]=objModelPatCat.getStrIdVerificationDoc().split("#");
				
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelPatCat);
						
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_catcode",objModelPatCat.getStrPatCategoryCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_cattype",objModelPatCat.getStrPatCategoryType(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_catname",objModelPatCat.getStrPatCategoryName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_catshort",objModelPatCat.getStrPatCategoryShort(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_estatus",objModelPatCat.getStrEconomicStatus(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_priority",objModelPatCat.getStrCatPriority(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ispaid",objModelPatCat.getStrIsPaid(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isidrequire",objModelPatCat.getStrIsIdReq(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_idsize",objModelPatCat.getStrIdSize(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_validationtype",objModelPatCat.getStrIdValidationType(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isexpiry",objModelPatCat.getStrIsExpiry(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_expirydays",objModelPatCat.getStrExpiryDays(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_expirymnth",objModelPatCat.getStrExpiryMonth(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_benefit",objModelPatCat.getStrBenefit(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_criteria",objModelPatCat.getStrCriteria(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelPatCat.getStrRemarks(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_catgroup",objModelPatCat.getStrPatCategoryGroup(),18);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,19);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),20);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelPatCat.getStrHospitalCode(),21);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_document_code",strVerificationDoc[0],22);
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
			 */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isapproval_req",objModelPatCat.getStrIsApprovalRequired(),23);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_default_client_code",objModelPatCat.getStrPatClient(),24);
			/**By Mukund on 06.Nov.17*/
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isspecial_category",objModelPatCat.getStrIsCatSpl(),25);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_departments_bound",objModelPatCat.getStrBoundDeptCode(),26);
			/**End On 06.Nov.17*/
			/**By Vasu on 15.May.18*/
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_default",objModelPatCat.getStrIsDefault(),27);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_applicable_service_code",objModelPatCat.getStrApplicableServicesCode(),28);
			/**End On 15.May.18*/
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,29); 
			//End:Sheeldarshi
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			
			

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException(e.getMessage());
			
		}
		
	}
	
	//For Duplicacy Check
	public static boolean chkPatCategoryDuplicate(PatCategoryVO objModelPatCat,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		ResultSet rs = null;
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCATEGORY_VIEW;;
		final int nProcedureIndex;
		int ncount=0;

		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelPatCat);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_catcode",objModelPatCat.getStrPatCategoryCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_catname",objModelPatCat.getStrPatCategoryName().trim(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelPatCat.getStrHospitalCode(),4);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}catch (Exception e) {
			e.printStackTrace();
			objModelPatCat.setStrMsgString("PatientCtegoryDAO.chkLocationDuplicate() --> " + e.getMessage());
			objModelPatCat.setStrMsgType("1");

		} 
		
		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			ncount=Integer.parseInt(webRowSet.getString(1));
			System.out.println("------"+ncount+"-----");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientCtegoryDAO():HelperMethodsDAO.chkPatientCtegoryDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2")) {
			if(objModelPatCat.getStrOldPatCategoryName().equalsIgnoreCase(objModelPatCat.getStrPatCategoryName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelPatCat.getStrOldPatCategoryName().equalsIgnoreCase(objModelPatCat.getStrPatCategoryName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		}  else {
			bExistStatus=false;
		}
		return bExistStatus;
	}
	
	
	//To List the Global Patient Categories from the Patient Category Master
	public static List getGlobalPatCategory(HisDAO hisDAO_p,UserVO uservo,String strMode_p)
	{
		List alRecord = new ArrayList(); 
			
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATIENTCATEGORY_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
			{
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",strMode_p,1);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",Config.SUPER_HOSPITAL_CODE,3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode_local",uservo.getHospitalCode(),4);
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
				hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);
				
				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			
				
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}
			
			try 
			{
				if (!rs.next()) {
					throw new HisRecordNotFoundException("Global Patient Category Not Found");
				}else{
					rs.beforeFirst();
					alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			} catch (Exception e) {
				if (e.getClass() == HisRecordNotFoundException.class) {
					e.printStackTrace();
					//throw new HisRecordNotFoundException(e.getMessage());
				} else
					throw new HisDataAccessException("PatientCtegoryDAO:getGlobalPatCategory:HelperMethods :: " + e);
			}
			finally 
			{
				if (hisDAO_p != null) 
				{
					hisDAO_p.free();hisDAO_p = null;
				}
			}
			
			return alRecord;
		}
	
	
	//To List the Primary Patient Categories from the Patient Category Master
	public static List getPrimaryPatCategory(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 
			
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATIENTCATEGORY_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
			{
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",Config.SUPER_HOSPITAL_CODE,3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode_local",uservo.getHospitalCode(),4);
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
				hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);
				
				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			
				
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}
			
			try 
			{
				if (!rs.next()) {
				//throw new HisRecordNotFoundException("Global Patient Category Not Found");
				}else{
					rs.beforeFirst();
					alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			} catch (Exception e) {
				if (e.getClass() == HisRecordNotFoundException.class) {
					throw new HisRecordNotFoundException(e.getMessage());
				} else
					throw new HisDataAccessException("PatientCtegoryDAO:getGlobalPatCategory:HelperMethods :: " + e);
			}
			finally 
			{
				if (hisDAO_p != null) 
				{
					hisDAO_p.free();hisDAO_p = null;
				}
			}
			
			return alRecord;
		}
	
	/** To get Users List by Raj Kumar ON 1-Nov-2018 **/
	public static List getMyUsers(HisDAO hisDAO_p, UserVO uservo)
	{
		//String hoscode=96101;
		List alRecord = new ArrayList(); 
			
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_USERLST;
		final int nProcedureIndex;
		final String strDbErr;
		try
			{
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);
				hisDAO_p.setProcInValue(nProcedureIndex, "hoscode",uservo.getHospitalCode(),1);
				hisDAO_p.setProcInValue(nProcedureIndex, "crUser",uservo.getUserId(),2);
				
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); 
				hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4);
				
				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			
				
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}
			
			try 
			{
				if (!rs.next()) {
				}else{
					rs.beforeFirst();
					alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
					System.out.print("Set Find::::::::::::::::::::::::::::::::::::::::::::::::::::"+alRecord);
				}
			} catch (Exception e) {
				if (e.getClass() == HisRecordNotFoundException.class) {
					throw new HisRecordNotFoundException(e.getMessage());
				} else
					throw new HisDataAccessException("PatientCtegoryDAO:getGlobalPatCategory:HelperMethods :: " + e);
			}
			finally 
			{
				if (hisDAO_p != null) 
				{
					hisDAO_p.free();hisDAO_p = null;
				}
			}
			
			return alRecord;
		}
		
	
   /**To get Applicable Services for Treatment Categories,Added by Vasu on 14.May.2018 */
	public static List getApplicableServices(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		List alRecord = new ArrayList();
		HisDAO daoObj = null;
		Map populateMAP = new HashMap();
		String strProcName = "{call Pkg_Reg_View.proc_ghmis_service_type_mst(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";		//first call the getQueryMethod with arguments filename,querykey from prop file
		try 
		{
		daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_pat_cat_type_secondary", RegistrationConfig.PATIENT_CAT_TYPE_SECONDARY,1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_super_hospital_code", RegistrationConfig.SUPER_USER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "p_isvalid_active", Config.IS_VALID_ACTIVE,3);
		daoObj.setProcInValue(nProcIndex, "p_isvalid_inactive", Config.IS_VALID_INACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "p_seatid",  _userVO.getSeatId(),5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code",  _userVO.getHospitalCode(),6);
		daoObj.setProcInValue(nProcIndex, "p_module_id",  Config.MODULE_ID_REGISTRATION,7);
		daoObj.setProcOutValue(nProcIndex, "err", 1,8);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try
		{
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Records For Treatment Category Found. Either There Are No Records In Database Or No Records Against Your Seat Id Exist  ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		try {
			if (strErr.equals("")) 
			{
				//while(rs.next()){
				//	alRecord.add(rs.getString(1));
				//	System.out.println("webRs.getString(1) :" + rs.getString(1));
				alRecord=	HelperMethodsDAO.getAlOfEntryObjects(rs);
				//}
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
		return alRecord;
	}


/*public static List getMySeatId(HisDAO hisDAO_p, UserVO uservo) {

	List alRecord = new ArrayList(); 
		
	ResultSet rs = null;
	final String strProcName = RegistrationDaoConfig.PROCEDURE_USERLST;
	final int nProcedureIndex;
	final String strDbErr;
	try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "hoscode",uservo.getHospitalCode(),1);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,2); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,3);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		try 
		{
			if (!rs.next()) {
			}else{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
				System.out.print("Set Find::::::::::::::::::::::::::::::::::::::::::::::::::::"+alRecord);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientCtegoryDAO:getGlobalPatCategory:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}
		
		return alRecord;
	
	
	
	
	
	
	
}*/
		

	
}
