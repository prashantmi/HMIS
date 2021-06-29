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
import vo.registration.EmgMlcCaseTypeVO;

/**
 * @author s.singaravelan
 *
 */
public class EmgMlcCaseTypeDAO
{

	//To Save the Mlc Case Type in the Mlc Case Type Mst
	public static String saveEmgMlcCaseTypeDetails(EmgMlcCaseTypeVO objModelEmgMlcCaseType,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_MLC_CASE_TYPE_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgMlcCaseType);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mlctypecode",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_mlctype",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ismlc",objModelEmgMlcCaseType.getStrIsMlcBound(),4);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),6);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); 

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
			objModelEmgMlcCaseType.setStrMsgString("EmgMlcCaseTypeDAO.saveEmgMlcCaseTypeDetails() --> "	+ e.getMessage());
			objModelEmgMlcCaseType.setStrMsgType("1");
		}
		return strReuestNo;
	}


	//To get EmgMlcCaseType Details in Modify
	public static EmgMlcCaseTypeVO modifyDetails(EmgMlcCaseTypeVO objModelEmgMlcCaseType,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_MLC_CASE_TYPE_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgMlcCaseType);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mlctypecode",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_mlctype",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeDesc(),3);			
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
			objModelEmgMlcCaseType.setStrMsgString("EmgMlcCaseTypeDAO.modifyDetails() --> "	+ e.getMessage());
			objModelEmgMlcCaseType.setStrMsgType("1");
		}


		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelEmgMlcCaseType.setStrEmgMlcCaseTypeCode(webRowSet.getString(1));
			objModelEmgMlcCaseType.setStrEmgMlcCaseTypeDesc(webRowSet.getString(2));
			objModelEmgMlcCaseType.setStrIsMlcBound(webRowSet.getString(3));
			objModelEmgMlcCaseType.setStrIsValid(webRowSet.getString(4));				

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmgMlcCaseTypeDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelEmgMlcCaseType;

	}

	//To Update EmgMlcCaseType Details
	public static void updateEmgMlcCaseTypeDetails(EmgMlcCaseTypeVO objModelEmgMlcCaseType,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_EMG_MLC_CASE_TYPE_DML;
		final int nProcedureIndex;
		final String strDbErr;



		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgMlcCaseType);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mlctypecode",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_mlctype",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ismlc",objModelEmgMlcCaseType.getStrIsMlcBound(),4);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),6);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); 

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

	public static boolean chkEmgMlcCaseTypeDuplicate(EmgMlcCaseTypeVO objModelEmgMlcCaseType,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_MLC_CASE_TYPE_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgMlcCaseType);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mlctypecode",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_mlctype",objModelEmgMlcCaseType.getStrEmgMlcCaseTypeDesc(),3);			
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
			objModelEmgMlcCaseType.setStrMsgString("EmgMlcCaseTypeDAO.chkEmgMlcCaseTypeDuplicate() --> " + e.getMessage());
			objModelEmgMlcCaseType.setStrMsgType("1");

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
			throw new HisDataAccessException("EmgMlcCaseTypeDAO():HelperMethodsDAO.chkEmgMlcCaseTypeDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) 
		{
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2"))
		{
			if(objModelEmgMlcCaseType.getStrOldEmgMlcCaseTypeDesc().equalsIgnoreCase(objModelEmgMlcCaseType.getStrEmgMlcCaseTypeDesc()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelEmgMlcCaseType.getStrOldEmgMlcCaseTypeDesc().equalsIgnoreCase(objModelEmgMlcCaseType.getStrEmgMlcCaseTypeDesc())) && (ncount == 0))
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
