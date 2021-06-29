/**
 * 
 */
package registration.dao;


import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationDaoConfig;
import vo.registration.EmgPatStatusVO;

/**
 * @author s.singaravelan
 *
 */
public class EmgPatStatusDAO
{

	//To Save the Patient Status in the Patient Status Mst
	public static String saveEmgPatStatusDetails(EmgPatStatusVO objModelEmgPatStatus,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_PAT_STATUS_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgPatStatus);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_statuscode",objModelEmgPatStatus.getStrEmgPatStatusCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_status",objModelEmgPatStatus.getStrEmgPatStatusDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),5);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 

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
			objModelEmgPatStatus.setStrMsgString("EmgPatStatusDAO.saveEmgPatStatusDetails() --> "	+ e.getMessage());
			objModelEmgPatStatus.setStrMsgType("1");
		}
		return strReuestNo;
	}


	//To get EmgPatStatus Details in Modify
	public static EmgPatStatusVO modifyDetails(EmgPatStatusVO objModelEmgPatStatus,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_PAT_STATUS_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgPatStatus);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_statuscode",objModelEmgPatStatus.getStrEmgPatStatusCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_status",objModelEmgPatStatus.getStrEmgPatStatusDesc(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,6); 

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
			objModelEmgPatStatus.setStrMsgString("EmgPatStatusDAO.modifyDetails() --> "	+ e.getMessage());
			objModelEmgPatStatus.setStrMsgType("1");
		}


		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelEmgPatStatus.setStrEmgPatStatusCode(webRowSet.getString(1));
			objModelEmgPatStatus.setStrEmgPatStatusDesc(webRowSet.getString(2));
			objModelEmgPatStatus.setStrIsValid(webRowSet.getString(3));				

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmgPatStatusDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelEmgPatStatus;

	}

	//To Update EmgPatStatus Details
	public static void updateEmgPatStatusDetails(EmgPatStatusVO objModelEmgPatStatus,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_EMG_PAT_STATUS_DML;
		final int nProcedureIndex;
		final String strDbErr;



		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgPatStatus);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_statuscode",objModelEmgPatStatus.getStrEmgPatStatusCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_status",objModelEmgPatStatus.getStrEmgPatStatusDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),5);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 

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

	public static boolean chkEmgPatStatusDuplicate(EmgPatStatusVO objModelEmgPatStatus,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_PAT_STATUS_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgPatStatus);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_statuscode",objModelEmgPatStatus.getStrEmgPatStatusCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_status",objModelEmgPatStatus.getStrEmgPatStatusDesc(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,6); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}catch (Exception e) 
		{
			e.printStackTrace();
			objModelEmgPatStatus.setStrMsgString("EmgPatStatusDAO.chkEmgPatStatusDuplicate() --> " + e.getMessage());
			objModelEmgPatStatus.setStrMsgType("1");

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
			throw new HisDataAccessException("EmgPatStatusDAO():HelperMethodsDAO.chkEmgPatStatusDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) 
		{
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2"))
		{
			if(objModelEmgPatStatus.getStrOldEmgPatStatusDesc().equalsIgnoreCase(objModelEmgPatStatus.getStrEmgPatStatusDesc()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelEmgPatStatus.getStrOldEmgPatStatusDesc().equalsIgnoreCase(objModelEmgPatStatus.getStrEmgPatStatusDesc())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else 
		{
			bExistStatus=false;
		}
		return bExistStatus;
	}	
	
	
}
