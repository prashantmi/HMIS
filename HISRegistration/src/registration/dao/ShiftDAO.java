/**
 * 
 */
package registration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import javax.sql.rowset.WebRowSet;









//import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.ShiftVO;

/**
 * @author s.singaravelan
 *
 */
public class ShiftDAO
{

	//To Save the Shift in the Shift Mst
	public static String saveShiftDetails(ShiftVO objModelShift,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_SHIFT_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelShift);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shiftcode",objModelShift.getStrShiftCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_shiftname",objModelShift.getStrShiftDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shifttype",objModelShift.getStrShiftType(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_startTime",objModelShift.getStrShiftStartTime(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_endTime",objModelShift.getStrShiftEndTime(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelShift.getStrHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,10); 

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
			objModelShift.setStrMsgString("ShiftDAO.saveShiftDetails() --> "	+ e.getMessage());
			objModelShift.setStrMsgType("1");
		}
		return strReuestNo;
	}


	//To get Shift Details in Modify
	public static ShiftVO modifyDetails(ShiftVO objModelShift,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_SHIFT_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelShift);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shiftcode",objModelShift.getStrShiftCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_shiftname",objModelShift.getStrShiftDesc(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelShift.getStrHospitalCode(),4);			
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
			objModelShift.setStrMsgString("ShiftDAO.modifyDetails() --> "	+ e.getMessage());
			objModelShift.setStrMsgType("1");
		}


		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelShift.setStrShiftCode(webRowSet.getString(1));
			objModelShift.setStrShiftDesc(webRowSet.getString(2));
			objModelShift.setStrShiftType(webRowSet.getString(3));
			objModelShift.setStrShiftStartTime(webRowSet.getString(4));
			objModelShift.setStrShiftEndTime(webRowSet.getString(5));
			//objModelShift.setStrIsValid(webRowSet.getString(7));				

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ShiftDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelShift;

	}

	//To Update Shift Details
	public static void updateShiftDetails(ShiftVO objModelShift,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_SHIFT_DML;
		final int nProcedureIndex;
		final String strDbErr;



		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelShift);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shiftcode",objModelShift.getStrShiftCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_shiftname",objModelShift.getStrShiftDesc(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shifttype",objModelShift.getStrShiftType(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_startTime",objModelShift.getStrShiftStartTime(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_endTime",objModelShift.getStrShiftEndTime(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelShift.getStrHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,10); 

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

	public static boolean chkShiftDuplicate(ShiftVO objModelShift,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_SHIFT_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelShift);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shiftcode",objModelShift.getStrShiftCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_shiftname",objModelShift.getStrShiftDesc(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelShift.getStrHospitalCode(),4);			
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

		}catch (Exception e) 
		{
			e.printStackTrace();
			objModelShift.setStrMsgString("ShiftDAO.chkShiftDuplicate() --> " + e.getMessage());
			objModelShift.setStrMsgType("1");

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
			throw new HisDataAccessException("ShiftDAO():HelperMethodsDAO.chkShiftDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) 
		{
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2"))
		{
			if(objModelShift.getStrOldShiftDesc().equalsIgnoreCase(objModelShift.getStrShiftDesc()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelShift.getStrOldShiftDesc().equalsIgnoreCase(objModelShift.getStrShiftDesc())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else 
		{
			bExistStatus=false;
		}
		return bExistStatus;
	}
	
	
	//To get Shift Details in Modify
	public static List shiftEssentials(HisDAO hisDAO_p,UserVO uservo) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_SHIFT_LIST;
		final int nProcedureIndex;
		List alRecord = new ArrayList(); 
		final String strDbErr;
		ResultSet rs = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shifttype",RegistrationConfig.SHIFT_TYPE_OPD,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,6); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

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
				throw new HisRecordNotFoundException("Shift Details Not Found");
			}
			else
			{
				alRecord=populateMapList(rs);
			}
		} 
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} 
			else
				throw new HisDataAccessException("ShiftDAO:getShift:HelperMethods :: " + e);
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
	
	public static List populateMapList(ResultSet rs)throws SQLException
    {
    	List temp=new ArrayList();
    	rs.beforeFirst();    	
    		while(rs.next())
        	{    		
    			ShiftVO _shiftMasterVO=new ShiftVO();
        		HelperMethods.populateVOfrmRS(_shiftMasterVO,rs);    		
        		temp.add(_shiftMasterVO);
        	}	    
    	return temp;
    }



}
