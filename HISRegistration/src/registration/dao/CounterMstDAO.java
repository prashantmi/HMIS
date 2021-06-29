/**
 * 
 */
package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.CounterVO;
import vo.registration.CounterVO;
import vo.registration.CounterVO;
import vo.registration.ExtInstituteVO;
import vo.registration.PatCatDocVO;

/**
 * @author s.singaravelan
 *
 */
public class CounterMstDAO
{

	//To List the Counter Type in the Counter Master
	public static List getCounterTypeList(HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_COUNTERTYPE_LIST;
		final int nProcedureIndex;
		final String strDbErr;		
		List alRecord = new ArrayList(); 

		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);

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
				//throw new HisRecordNotFoundException("Department Location Details Not Found");
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
			} 
			else
				throw new HisDataAccessException("CounterMstDAO:getCounterDeptList:HelperMethods :: " + e);
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

		
	//To Save the Counter Details 
	public static String saveCounterDetails(CounterVO objModelCounter,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_COUNTER_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelCounter);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_countercode",objModelCounter.getStrCounterCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_countername",objModelCounter.getStrCounterName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_countertype",objModelCounter.getStrCounterType(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelCounter.getStrLocationCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ipaddress",objModelCounter.getStrIPAddress(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelCounter.getStrRemarks(),7);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),10);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,11); 

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
			objModelCounter.setStrMsgString("CounterMstDAO.saveCounterDetails() --> "	+ e.getMessage());
			objModelCounter.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
		
	//To Update the Counter Details 
	public static String updateCounterDetails(CounterVO objModelCounter,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_COUNTER_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelCounter);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_countercode",objModelCounter.getStrCounterCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_countername",objModelCounter.getStrCounterName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_countertype",objModelCounter.getStrCounterType(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelCounter.getStrLocationCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ipaddress",objModelCounter.getStrIPAddress(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelCounter.getStrRemarks(),7);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),10);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,11); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);


			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Updated Successussfully");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objModelCounter.setStrMsgString("CounterMstDAO.UpdateCounterDetails() --> "	+ e.getMessage());
			objModelCounter.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	//To get Counter Details in Modify
	public static CounterVO modifyDetails(CounterVO objCounterModel,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_COUNTER_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objCounterModel);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_countercode",objCounterModel.getStrCounterCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_countername",objCounterModel.getStrCounterName(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objCounterModel.getStrHospitalCode(),4);			
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
			objCounterModel.setStrMsgString("CounterVO.modifyDetails() --> "	+ e.getMessage());
			objCounterModel.setStrMsgType("1");
		}


		try
		{
			//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
			webRowSet.beforeFirst();
			webRowSet.next();
			objCounterModel.setStrCounterCode(webRowSet.getString(1));
			objCounterModel.setStrCounterName(webRowSet.getString(2));
			objCounterModel.setStrCounterType(webRowSet.getString(3));
			objCounterModel.setStrLocationCode(webRowSet.getString(4));
			objCounterModel.setStrIPAddress(webRowSet.getString(5));
			objCounterModel.setStrRemarks(webRowSet.getString(6));
			objCounterModel.setStrHospitalCode(webRowSet.getString(7));
			//objCounterModel.setStrIsValid(webRowSet.getString(8));

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("CounterVO:modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objCounterModel;

	}
	
	public static boolean chkCounterDuplicate(CounterVO objModelCounter,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_COUNTER_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelCounter);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_countercode",objModelCounter.getStrCounterCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_countername",objModelCounter.getStrCounterName()+"#"+objModelCounter.getStrIPAddress(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelCounter.getStrHospitalCode(),4);			
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
			objModelCounter.setStrMsgString("CounterDAO.chkCounterDuplicate() --> " + e.getMessage());
			objModelCounter.setStrMsgType("1");

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
			throw new HisDataAccessException("CounterDAO():HelperMethodsDAO.chkCounterDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2")) {
			if(objModelCounter.getStrOldCounterName().equalsIgnoreCase(objModelCounter.getStrCounterName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelCounter.getStrOldCounterName().equalsIgnoreCase(objModelCounter.getStrCounterName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else {
			bExistStatus=false;
		}
		return bExistStatus;
	}



}
