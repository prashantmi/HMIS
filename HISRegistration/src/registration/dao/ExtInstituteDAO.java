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
import java.util.List;

import javax.sql.rowset.WebRowSet;


import registration.config.RegistrationDaoConfig;
import vo.registration.ExtInstituteVO;

/**
 * @author s.singaravelan
 *
 */
public class ExtInstituteDAO
{
	
	//To List the State in the Ext Institute Master
	public static List getStateList(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 
	
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_GET_STATE_COMBO;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_countrycode","IND",2);
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
			throw new HisRecordNotFoundException("State Details Not Found");
			}else{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("ExtInstituteDAO:getState:HelperMethods :: " + e);
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
	
	//To Save the ExtInstitute in the ExtInstitute Mst
	public static String saveExtInstituteDetails(ExtInstituteVO objModelExtInstitute,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EXTINSTITUTE_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelExtInstitute);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinstcode",objModelExtInstitute.getStrInstituteCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstname",objModelExtInstitute.getStrInstituteName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstshort",objModelExtInstitute.getStrShortName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isassociated",objModelExtInstitute.getStrIsAssociated(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinsttype",objModelExtInstitute.getStrInstituteType(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstadd1",objModelExtInstitute.getStrAddress1(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstadd2",objModelExtInstitute.getStrAddress2(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstcity",objModelExtInstitute.getStrCity(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinststate",objModelExtInstitute.getStrStateCode(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstperson",objModelExtInstitute.getStrContactPerson(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstphone",objModelExtInstitute.getStrPhone(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstfax",objModelExtInstitute.getStrFax(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstemail",objModelExtInstitute.getStrEmail(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstremarks",objModelExtInstitute.getStrRemarks(),15);

			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",objModelExtInstitute.getStrIsValid(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),17);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelExtInstitute.getStrHospitalCode(),18);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,19); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
					
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Inserted Successussfully");
			
			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}			
		}

		catch (Exception e)
		{
			e.printStackTrace();
			objModelExtInstitute.setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
			objModelExtInstitute.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	

	//To get ExtInstitute Details in Modify
	public static ExtInstituteVO modifyDetails(ExtInstituteVO objModelExtInstitute,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EXTINSTITUTE_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;
		
		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelExtInstitute);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinstcode",objModelExtInstitute.getStrInstituteCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstname",objModelExtInstitute.getStrInstituteName(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelExtInstitute.getStrHospitalCode(),4);			
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
			objModelExtInstitute.setStrMsgString("RequestdetailDAO.getStoreName() --> "	+ e.getMessage());
			objModelExtInstitute.setStrMsgType("1");
		}
		
		
			try
			{
				webRowSet.beforeFirst();
				webRowSet.next();
								
				objModelExtInstitute.setStrIsAssociated(webRowSet.getString(1));
				objModelExtInstitute.setStrInstituteCode(webRowSet.getString(2));
				objModelExtInstitute.setStrInstituteName(webRowSet.getString(3));
				objModelExtInstitute.setStrShortName(webRowSet.getString(4));
				objModelExtInstitute.setStrInstituteType(webRowSet.getString(5));
				objModelExtInstitute.setStrAddress1(webRowSet.getString(6));
				objModelExtInstitute.setStrAddress2(webRowSet.getString(7));
				objModelExtInstitute.setStrCity(webRowSet.getString(8));
				objModelExtInstitute.setStrStateCode(webRowSet.getString(9));
				objModelExtInstitute.setStrContactPerson(webRowSet.getString(10));
				objModelExtInstitute.setStrPhone(webRowSet.getString(11));
				objModelExtInstitute.setStrFax(webRowSet.getString(12));
				objModelExtInstitute.setStrEmail(webRowSet.getString(13));
				objModelExtInstitute.setStrRemarks(webRowSet.getString(14));
				//objModelExtInstitute.setStrIsValid(webRowSet.getString(15));				
							
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("RequestdetailDAO:getStoreName():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
		return objModelExtInstitute;
		
	}
	
	//To Update ExtInstitute Details
	public static void updateExtInstituteDetails(ExtInstituteVO objModelExtInstitute,HisDAO hisDAO_p,UserVO uservo ) {

		
		final String strProcName =  RegistrationDaoConfig.PROCEDURE_EXTINSTITUTE_DML;
		final int nProcedureIndex;
		final String strDbErr;
				
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelExtInstitute);
						
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinstcode",objModelExtInstitute.getStrInstituteCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstname",objModelExtInstitute.getStrInstituteName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstshort",objModelExtInstitute.getStrShortName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isassociated",objModelExtInstitute.getStrIsAssociated(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinsttype",objModelExtInstitute.getStrInstituteType(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstadd1",objModelExtInstitute.getStrAddress1(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstadd2",objModelExtInstitute.getStrAddress2(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstcity",objModelExtInstitute.getStrCity(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinststate",objModelExtInstitute.getStrStateCode(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstperson",objModelExtInstitute.getStrContactPerson(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstphone",objModelExtInstitute.getStrPhone(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstfax",objModelExtInstitute.getStrFax(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstemail",objModelExtInstitute.getStrEmail(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstremarks",objModelExtInstitute.getStrRemarks(),15);

			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",objModelExtInstitute.getStrIsValid(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),17);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelExtInstitute.getStrHospitalCode(),18);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,19); 
			
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
	
	public static boolean chkExtInstituteDuplicate(ExtInstituteVO objModelExtInstitute,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EXTINSTITUTE_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelExtInstitute);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_extinstcode",objModelExtInstitute.getStrInstituteCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstname",objModelExtInstitute.getStrInstituteName(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelExtInstitute.getStrHospitalCode(),4);			
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
			objModelExtInstitute.setStrMsgString("ExtInstituteDAO.chkExtInstituteDuplicate() --> " + e.getMessage());
			objModelExtInstitute.setStrMsgType("1");

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
			e.printStackTrace();
			throw new HisDataAccessException("ExtInstituteDAO():HelperMethodsDAO.chkExtInstituteDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2")) {
			if(objModelExtInstitute.getStrOldInstituteName().equalsIgnoreCase(objModelExtInstitute.getStrInstituteName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelExtInstitute.getStrOldInstituteName().equalsIgnoreCase(objModelExtInstitute.getStrInstituteName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else {
			bExistStatus=false;
		}
		return bExistStatus;
	}
	
	
}
