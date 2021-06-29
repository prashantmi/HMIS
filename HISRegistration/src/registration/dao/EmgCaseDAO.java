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
import vo.registration.EmgCaseVO;
import vo.registration.EmgMlcCaseTypeVO;

/**
 * @author s.singaravelan
 *
 */
public class EmgCaseDAO
{

	//To Save the Emg Case  in the Emg Case  Mst
	public static String saveEmgCaseDetails(EmgCaseVO objModelEmgCase_p,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_CASE_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgCase_p);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_emgrgency_case_code",objModelEmgCase_p.getStrEmgCaseCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_emrgency_case",objModelEmgCase_p.getStrEmgCaseDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ismlc_req",objModelEmgCase_p.getStrIsMlcRequired(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_case_type",objModelEmgCase_p.getCaseType(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",uservo.getHospitalCode(),8);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,9); 

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
			objModelEmgCase_p.setStrMsgString("EmgCaseDAO.saveEmgCaseDetails() --> "	+ e.getMessage());
			objModelEmgCase_p.setStrMsgType("1");
		}
		return strReuestNo;
	}


	//To get Emg Case Details in Modify
	public static EmgCaseVO modifyDetails(EmgCaseVO objModelEmgCase_p,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_CASE_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgCase_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_emrgency_case_code",objModelEmgCase_p.getStrEmgCaseCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_emrgency_case",objModelEmgCase_p.getStrEmgCaseDesc(),3);	
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_ismlc_req",objModelEmgCase_p.getStrIsMlcRequired(),4);	
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_case_type",objModelEmgCase_p.getCaseType(),5);
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
			objModelEmgCase_p.setStrMsgString("EmgCaseDAO.modifyDetails() --> "	+ e.getMessage());
			objModelEmgCase_p.setStrMsgType("1");
		}


		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelEmgCase_p.setStrEmgCaseCode(webRowSet.getString(1));
			objModelEmgCase_p.setStrEmgCaseDesc(webRowSet.getString(2));
			objModelEmgCase_p.setStrIsMlcRequired(webRowSet.getString(3));
			//objModelEmgCase_p.setStrIsValid(webRowSet.getString(4));
			objModelEmgCase_p.setCaseType(webRowSet.getString(4));
			

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmgCaseDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelEmgCase_p;

	}

	//To Update EmgCase Details
	public static void updateEmgCaseDetails(EmgCaseVO objModelEmgCase_p,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_EMG_CASE_DML;
		final int nProcedureIndex;
		final String strDbErr;



		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgCase_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_emgrgency_case_code",objModelEmgCase_p.getStrEmgCaseCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_emrgency_case",objModelEmgCase_p.getStrEmgCaseDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ismlc_req",objModelEmgCase_p.getStrIsMlcRequired(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_case_type",objModelEmgCase_p.getCaseType(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",uservo.getHospitalCode(),8);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,9); 

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

	public static boolean chkEmgCaseDuplicate(EmgCaseVO objModelEmgCase_p,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean _isAllowable=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_CASE_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgCase_p);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_emrgency_case_code",objModelEmgCase_p.getStrEmgCaseCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_emrgency_case",objModelEmgCase_p.getStrEmgCaseDesc(),3);	
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_ismlc_req",objModelEmgCase_p.getStrIsMlcRequired(),4);	
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_case_type",objModelEmgCase_p.getCaseType(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 7);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,8); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}catch (Exception e) 
		{
			e.printStackTrace();
			objModelEmgCase_p.setStrMsgString("EmgCaseDAO.chkEmgCaseDuplicate() --> " + e.getMessage());
			objModelEmgCase_p.setStrMsgType("1");

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
			throw new HisDataAccessException("EmgCaseDAO():HelperMethodsDAO.chkEmgCaseDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) 
		{
			_isAllowable=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2"))
		{
			if(objModelEmgCase_p.getStrEmgCaseDesc().equalsIgnoreCase(objModelEmgCase_p.getStrOldEmgCaseDesc()) && (ncount == 1))
			{
				_isAllowable=true;
			}
			else if(!(objModelEmgCase_p.getStrEmgCaseDesc().equalsIgnoreCase(objModelEmgCase_p.getStrOldEmgCaseDesc())) && (ncount == 0))
			{
				_isAllowable=true;
			}
			else
				_isAllowable=false;
		} else 
		{
			_isAllowable=false;
		}
		return _isAllowable;
	}	
	
	
	
}
