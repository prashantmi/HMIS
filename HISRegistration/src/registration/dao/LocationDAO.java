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
import vo.registration.LocationVO;

/**
 * @author s.singaravelan
 *
 */
public class LocationDAO
{

	//To List the Department Locations in the Department Master
	public static List getLocation(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_LOCATION_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",Config.SUPER_HOSPITAL_CODE,3);
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
				throw new HisRecordNotFoundException("Department Location Details Not Found");
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
				throw new HisDataAccessException("DepartmentDAO:getLocation:HelperMethods :: " + e);
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

	//To List the Location Type in the Location Master
	public static List getLocationType(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_LOCATION_TYPE_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4);

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
				throw new HisRecordNotFoundException("Location Type Not Found");
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
				throw new HisDataAccessException("LocationDAO:getLocationType:HelperMethods :: " + e);
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


	//To Save the Location in the Location Mst
	public static String saveLocationDetails(LocationVO objModelLoc,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_LOCATION_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelLoc);			

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelLoc.getStrLocCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_locname",objModelLoc.getStrLocDescription(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loctype",objModelLoc.getStrLocTypeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_landmark",objModelLoc.getStrLandmark(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_floor",objModelLoc.getStrFloor(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_building",objModelLoc.getStrBuilding(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_room",objModelLoc.getStrRoom(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_block",objModelLoc.getStrBlock(),9);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,10);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),11);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelLoc.getStrHospitalCode(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),12);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,13); 

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
			objModelLoc.setStrMsgString("LocationDAO.saveLocationDetails() --> "	+ e.getMessage());
			objModelLoc.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}


	//To get Location Details in Modify
	public static LocationVO modifyDetails(LocationVO objModelLoc,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_LOCATION_MODIFY;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelLoc);
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelLoc.getStrLocCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_locname",objModelLoc.getStrLocDescription(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelLoc.getStrHospitalCode(),4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) 
			{
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
			objModelLoc.setStrMsgString("LocationDAO.modifyDetails() --> "	+ e.getMessage());
			objModelLoc.setStrMsgType("1");
		}

		try
		{
			//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelLoc.setStrLocCode(webRowSet.getString(1));
			objModelLoc.setStrLocDescription(webRowSet.getString(2));
			objModelLoc.setStrLocTypeCode(webRowSet.getString(4));
			objModelLoc.setStrLandmark(webRowSet.getString(6));								

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelLoc;

	}

	//To Update Location Details
	public static void updateLocationDetails(LocationVO objModelLoc,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_LOCATION_DML;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelLoc);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelLoc.getStrLocCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_locname",objModelLoc.getStrLocDescription(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loctype",objModelLoc.getStrLocTypeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_landmark",objModelLoc.getStrLandmark(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_floor",objModelLoc.getStrFloor(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_building",objModelLoc.getStrBuilding(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_room",objModelLoc.getStrRoom(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_block",objModelLoc.getStrBlock(),9);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,10);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),12);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelLoc.getStrHospitalCode(),12);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,13); 

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
	public static boolean chkLocationDuplicate(LocationVO objModelLoc,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_LOCATION_MODIFY;;
		final int nProcedureIndex;
		int ncount=0;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelLoc);
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelLoc.getStrLocCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_locname",objModelLoc.getStrLocDescription(),3);			
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelLoc.getStrHospitalCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) 
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			objModelLoc.setStrMsgString("LocationDAO.chkLocationDuplicate() --> " + e.getMessage());
			objModelLoc.setStrMsgType("1");

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
			throw new HisDataAccessException("LocationDAO():HelperMethodsDAO.chkLocationDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2")) {
			if(objModelLoc.getStrOldLocDescription().equalsIgnoreCase(objModelLoc.getStrLocDescription()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelLoc.getStrOldLocDescription().equalsIgnoreCase(objModelLoc.getStrLocDescription())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		}
		else 
		{
			bExistStatus=false;
		}
		return bExistStatus;
	}



}
