/**
 * 
 */
package registration.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;



import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationDaoConfig;
import vo.registration.UnitVO;

/**
 * @author s.singaravelan
 *
 */
public class UnitDAO
{
		
	//To Save the Units in the Unit Mst
	public static String saveUnitDetails(UnitVO objModelUnit,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNIT_DML;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		Boolean flag = null;
		String strReuestNo="";
		int funcIndex = 0;
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelUnit);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelUnit.getStrUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelUnit.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_unitname",objModelUnit.getStrUnitName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unittype",objModelUnit.getStrIsGeneral(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isrefer",objModelUnit.getStrIsRefer(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unithod",objModelUnit.getStrEmpCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_diagnosistype",objModelUnit.getStrDiagnosisType(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelUnit.getStrUnitLocCode(),9);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_closedays",objModelUnit.getStrEpiDefCloseDays(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_expmode",objModelUnit.getStrIsExpiry(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_renday",objModelUnit.getStrExpiryDay(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_renmonth",objModelUnit.getStrExpiryMonth(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnit.getStrDeptUnitCode(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isunit",objModelUnit.getStrIsUnit(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_cardprint",objModelUnit.getStrCardPrintSetup(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isappointment",objModelUnit.getStrIsAppointment(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_MobileNo",objModelUnit.getStrMobileNo(),18); // ADDED BY WARISH 22-aug-2017
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,19);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),20);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),20);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnit.getStrHospitalCode(),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelUnit.getStrRemarks(),22);
		
			
			
			//Added For: Setup Max Walkin in Unit Master   By: Raj Kumar
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_lower_age_limit",objModelUnit.getStrLowerAgeLimit(),23);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_walkin",objModelUnit.getStrMaxWalkinReg(),24);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_walkin",objModelUnit.getStrMaxWalkinFolloUp(),25);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_portal",objModelUnit.getStrMaxWalkinPortReg(),26);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_portal",objModelUnit.getStrMaxWalkinPortFollowUP(),27);
			
			
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,28);
			          
			
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
			objModelUnit.setStrMsgString("UnitDAO.saveUnitDetails() --> "	+ e.getMessage());
			objModelUnit.setStrMsgType("1");
			throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	

	//To get Unit Details in Modify
	public static UnitVO modifyDetails(UnitVO objModelUnit,HisDAO hisDAO_p) 
	{
		String abc="";
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNIT_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;
		
		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelUnit);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelUnit.getStrUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnit.getStrDeptUnitCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_unitname",objModelUnit.getStrUnitName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnit.getStrHospitalCode(),5);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);			
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 7);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,8); 
			
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
			objModelUnit.setStrMsgString("UnitDAO.modifyDetails() --> "	+ e.getMessage());
			objModelUnit.setStrMsgType("1");
		}
		
		
			try
			{
				//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
				webRowSet.beforeFirst();
				webRowSet.next();
				objModelUnit.setStrDeptUnitCode(webRowSet.getString(1));
				objModelUnit.setStrDeptCode(webRowSet.getString(2));
				objModelUnit.setStrUnitName(webRowSet.getString(3));
				objModelUnit.setStrUnitLocCode(webRowSet.getString(4));
				objModelUnit.setStrEmpCode(webRowSet.getString(5));
				objModelUnit.setStrIsGeneral(webRowSet.getString(6));
				objModelUnit.setStrUnitCode(webRowSet.getString(7));
				objModelUnit.setStrIsExpiry(webRowSet.getString(8));
				objModelUnit.setStrExpiryDay(webRowSet.getString(9));
				objModelUnit.setStrExpiryMonth(webRowSet.getString(10));
				objModelUnit.setStrDiagnosisType(webRowSet.getString(11));
				objModelUnit.setStrEpiDefCloseDays(webRowSet.getString(12));
				objModelUnit.setStrIsRefer(webRowSet.getString(13));
				objModelUnit.setStrHospitalCode(webRowSet.getString(14));
				objModelUnit.setStrRemarks(webRowSet.getString(15));
				//objModelUnit.setStrIsValid(webRowSet.getString(16));
				objModelUnit.setStrIsUnit(webRowSet.getString(17));
				objModelUnit.setStrCardPrintSetup(webRowSet.getString(18));		
				objModelUnit.setStrIsAppointment(webRowSet.getString(19));
				objModelUnit.setStrMobileNo(webRowSet.getString(20));  //Added by warish 22-aug-2017
				
				
				objModelUnit.setStrLowerAgeLimit(webRowSet.getString(21));
				objModelUnit.setStrMaxWalkinReg(webRowSet.getString(22));
				objModelUnit.setStrMaxWalkinFolloUp(webRowSet.getString(23));
				objModelUnit.setStrMaxWalkinPortReg(webRowSet.getString(24));
				objModelUnit.setStrMaxWalkinPortFollowUP(webRowSet.getString(25));
				
				
							
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("UnitDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
		return objModelUnit;
		
	}
	
	//To get Unit Details in Modify
	public static UnitVO renewalDetails(UnitVO objModelUnit,HisDAO hisDAO_p) 
	{
		String strFuncName = "";
		String strRenewalType = "";
		int nFuncIndex = 0;
		
		try
		
		{
			strFuncName = RegistrationDaoConfig.FUNCTION_RENEWAL_VIEW;
			
			HisUtil.replaceNullValueWithEmptyString(objModelUnit);
			
			nFuncIndex = hisDAO_p.setFunction(strFuncName);
			
			hisDAO_p.setFuncInValue(nFuncIndex, 2, objModelUnit.getStrIsGeneral());
			hisDAO_p.setFuncInValue(nFuncIndex, 3, objModelUnit.getStrHospitalCode());
			
			hisDAO_p.setFuncOutValue(nFuncIndex, 1);
			hisDAO_p.executeFunction(nFuncIndex);
			strRenewalType = hisDAO_p.getFuncString(nFuncIndex);
			System.out.println("---------"+strRenewalType+"---------");
			
			objModelUnit.setStrRenewalType(strRenewalType);	
      			
		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objModelUnit.setStrMsgString("UnitDAO.getRenewalType() --> "	+ e.getMessage());
			objModelUnit.setStrMsgType("1");
			throw new HisDataAccessException(e.getMessage());
		}			
			
		return objModelUnit;
		
	}
	
	//To Update Unit Details
	public static void updateUnitDetails(UnitVO objModelUnit,HisDAO hisDAO_p,UserVO uservo ) {

		
		final String strProcName =  RegistrationDaoConfig.PROCEDURE_UNIT_DML;
		final int nProcedureIndex;
		final String strDbErr;
		
				
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelUnit);
						
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelUnit.getStrUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelUnit.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_unitname",objModelUnit.getStrUnitName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unittype",objModelUnit.getStrIsGeneral(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isrefer",objModelUnit.getStrIsRefer(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unithod",objModelUnit.getStrEmpCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_diagnosistype",objModelUnit.getStrDiagnosisType(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelUnit.getStrUnitLocCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_closedays",objModelUnit.getStrEpiDefCloseDays(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_expmode",objModelUnit.getStrIsExpiry(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_renday",objModelUnit.getStrExpiryDay(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_renmonth",objModelUnit.getStrExpiryMonth(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnit.getStrDeptUnitCode(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isunit",objModelUnit.getStrIsUnit(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_cardprint",objModelUnit.getStrCardPrintSetup(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isappointment",objModelUnit.getStrIsAppointment(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_MobileNo",objModelUnit.getStrMobileNo(),18); // ADDED BY WARISH 22-aug-2017
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,19);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),20);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),20);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnit.getStrHospitalCode(),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelUnit.getStrRemarks(),22);
			
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_lower_age_limit",objModelUnit.getStrLowerAgeLimit(),23);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_walkin",objModelUnit.getStrMaxWalkinReg(),24);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_walkin",objModelUnit.getStrMaxWalkinFolloUp(),25);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_portal",objModelUnit.getStrMaxWalkinPortReg(),26);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_portal",objModelUnit.getStrMaxWalkinPortFollowUP(),27);

			
		

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,28);
			
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
			throw new HisDataAccessException(e.getMessage());
			
		}
		
	}
	
	//For Duplicacy Check
	public static boolean chkUnitDuplicate(UnitVO objModelUnit,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		int nqryIndex;
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNIT_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelUnit);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelUnit.getStrUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnit.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_unitname",objModelUnit.getStrUnitName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnit.getStrHospitalCode(),5);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 7);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,8); 
			
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}catch (Exception e) {
			e.printStackTrace();
			objModelUnit.setStrMsgString("UnittDAO.chkUnitDuplicate() --> " + e.getMessage());
			objModelUnit.setStrMsgType("1");

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
			throw new HisDataAccessException("UnittDAO():HelperMethodsDAO.chkUnitDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2")) {
			if(objModelUnit.getStrOldUnitName().equalsIgnoreCase(objModelUnit.getStrUnitName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelUnit.getStrOldUnitName().equalsIgnoreCase(objModelUnit.getStrUnitName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} 
		else {
			bExistStatus=false;
		}
		return bExistStatus;
	}
	
	//To List the Units in the Master
	public static List getUnitList(String deptCode,HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNIT_LIST;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",deptCode,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}

		try 
		{
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("Unit List Not Found");
			}
			else
			{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("RoomDAO:getRoomList:HelperMethods :: " + e);
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
	
	//To Get Dept Unit Code
	public static String getDeptUnitCode(UnitVO objModelUnit,HisDAO hisDAO_p,UserVO uservo) 
	{
		String strFuncName = "";
		String strDeptUnitCode = "";
		int nFuncIndex = 0;

		try
		
		{
			strFuncName = RegistrationDaoConfig.FUNCTION_DEPT_UNITCODE;
			
			HisUtil.replaceNullValueWithEmptyString(objModelUnit);
			
			nFuncIndex = hisDAO_p.setFunction(strFuncName);
			
			hisDAO_p.setFuncInValue(nFuncIndex, 2, objModelUnit.getStrDeptCode());
			hisDAO_p.setFuncInValue(nFuncIndex, 3, uservo.getHospitalCode());
			
			hisDAO_p.setFuncOutValue(nFuncIndex, 1);
			hisDAO_p.executeFunction(nFuncIndex);
			strDeptUnitCode = hisDAO_p.getFuncString(nFuncIndex);
			System.out.println("---------"+strDeptUnitCode+"---------");			

		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objModelUnit.setStrMsgString("UnitDAO.getDeptUnitCode() --> "	+ e.getMessage());
			objModelUnit.setStrMsgType("1");
			throw new HisDataAccessException(e.getMessage());
		}			
			
		return strDeptUnitCode;
	}
	
	//To Save the Units in the Unit Mst
	public static void updateUnitForRoster(UnitVO objModelUnit,HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNIT_DML;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		Boolean flag = null;
		String strReuestNo="";
		int funcIndex = 0;
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelUnit);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelUnit.getStrUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelUnit.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_unitname",objModelUnit.getStrUnitName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unittype",objModelUnit.getStrIsGeneral(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isrefer",objModelUnit.getStrIsRefer(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unithod",objModelUnit.getStrEmpCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_diagnosistype",objModelUnit.getStrDiagnosisType(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelUnit.getStrUnitLocCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_closedays",objModelUnit.getStrEpiDefCloseDays(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_expmode",objModelUnit.getStrIsExpiry(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_renday",objModelUnit.getStrExpiryDay(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_renmonth",objModelUnit.getStrExpiryMonth(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnit.getStrDeptUnitCode(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isunit",objModelUnit.getStrIsUnit(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_cardprint",objModelUnit.getStrCardPrintSetup(),16);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),18);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),19);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnit.getStrHospitalCode(),19);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelUnit.getStrRemarks(),20);
     		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,21); 
			
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
			objModelUnit.setStrMsgString("UnitDAO.saveUnitDetails() --> "	+ e.getMessage());
			objModelUnit.setStrMsgType("1");
			throw new HisDataAccessException(e.getMessage());
		}
	}

	
	
	
}
