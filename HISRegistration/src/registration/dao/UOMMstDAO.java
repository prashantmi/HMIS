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
import vo.registration.UOMVO;
import vo.registration.ExtInstituteVO;
import vo.registration.PatCatDocVO;

/**
 * @author s.singaravelan
 *
 */
public class UOMMstDAO
{

	//To Save the UOM Details 
	public static String saveUOMDetails(UOMVO objModelUOM,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UOM_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelUOM);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_uomcode",objModelUOM.getStrUOMId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_uomname",objModelUOM.getStrUOMName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_uomshortname",objModelUOM.getStrUOMShortName(),4);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),6);

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
			objModelUOM.setStrMsgString("UOMMstDAO.saveUOMDetails() --> "	+ e.getMessage());
			objModelUOM.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
		
	//To Update the UOM Details 
	public static String updateUOMDetails(UOMVO objModelUOM,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UOM_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelUOM);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_uomcode",objModelUOM.getStrUOMId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_uomname",objModelUOM.getStrUOMName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_uomshortname",objModelUOM.getStrUOMShortName(),4);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),6);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); 

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
			objModelUOM.setStrMsgString("UOMMstDAO.UpdateUOMDetails() --> "	+ e.getMessage());
			objModelUOM.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	//To get UOM Details in Modify
	public static UOMVO modifyDetails(UOMVO objUOMModel,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UOM_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objUOMModel);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_uomcode",objUOMModel.getStrUOMId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_uomname",objUOMModel.getStrUOMName(),3);			
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
			objUOMModel.setStrMsgString("UOMVO.modifyDetails() --> "	+ e.getMessage());
			objUOMModel.setStrMsgType("1");
		}


		try
		{
			//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
			webRowSet.beforeFirst();
			webRowSet.next();
			objUOMModel.setStrUOMId(webRowSet.getString(1));
			objUOMModel.setStrUOMName(webRowSet.getString(2));
			objUOMModel.setStrUOMShortName(webRowSet.getString(3));
			//objUOMModel.setStrIsValid(webRowSet.getString(4));

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UOMVO:modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objUOMModel;

	}
	
	public static boolean chkUOMDuplicate(UOMVO objUOMModel,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UOM_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objUOMModel);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_uomcode",objUOMModel.getStrUOMId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_uomname",objUOMModel.getStrUOMName().trim(),3);			
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

		}catch (Exception e) {
			e.printStackTrace();
			objUOMModel.setStrMsgString("UOMDAO.chkUOMDuplicate() --> " + e.getMessage());
			objUOMModel.setStrMsgType("1");

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
			throw new HisDataAccessException("UOMDAO():HelperMethodsDAO.chkUOMDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2")) {
			if(objUOMModel.getStrOldUOMName().equalsIgnoreCase(objUOMModel.getStrUOMName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objUOMModel.getStrOldUOMName().equalsIgnoreCase(objUOMModel.getStrUOMName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else {
			bExistStatus=false;
		}
		return bExistStatus;
	}



}
