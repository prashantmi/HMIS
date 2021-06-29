/**
 * 
 */
package registration.dao;

import freemarker.ext.beans.HashAdapter;
import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.DataAccessObject;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.qryHandler_master;
import registration.config.RegistrationDaoConfig;
import vo.registration.DepartmentVO;
import vo.registration.LocationVO;
import vo.registration.OccupationVO;
import vo.registration.RoomVO;
import vo.registration.VerificationDocVO;

/**
 * @author s.singaravelan
 *
 */
public class RoomDAO
{

	//To Save the Room in the Room Mst
	public static String saveRoomDetails(RoomVO objModelRoom,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_ROOM_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelRoom);			

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelRoom.getStrRoomCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_roomname",objModelRoom.getStrRoomName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelRoom.getStrLocCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_roomdescription",objModelRoom.getStrRoomDescription(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomid",objModelRoom.getStrRoomId(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelRoom.getStrHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,10); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Inserted Succesfully");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}

		catch (Exception e)
		{
			e.printStackTrace();
			objModelRoom.setStrMsgString("RoomDAO.SaveRoom() --> "	+ e.getMessage());
			objModelRoom.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}


	//To get Room Details in Modify
	public static RoomVO modifyDetails(RoomVO objModelRoom,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_ROOM_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelRoom);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelRoom.getStrRoomCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_roomname",objModelRoom.getStrRoomName(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelRoom.getStrHospitalCode(),4);			
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
			objModelRoom.setStrMsgString("RoomDAO.getRoomName() --> "	+ e.getMessage());
			objModelRoom.setStrMsgType("1");
		}

		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelRoom.setStrRoomCode(webRowSet.getString(1));
			objModelRoom.setStrRoomName(webRowSet.getString(2));
			objModelRoom.setStrLocCode(webRowSet.getString(4));
			objModelRoom.setStrRoomDescription(webRowSet.getString(3));								

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RoomDAO:getRooName():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelRoom;

	}

	//To Update Room Details
	public static void updateRoomDetails(RoomVO objModelRoom,HisDAO hisDAO_p,UserVO uservo ) 
	{
		final String strProcName =  RegistrationDaoConfig.PROCEDURE_ROOM_DML;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelRoom);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelRoom.getStrRoomCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_roomname",objModelRoom.getStrRoomName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_loccode",objModelRoom.getStrLocCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_roomdescription",objModelRoom.getStrRoomDescription(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomid",objModelRoom.getStrRoomId(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelRoom.getStrHospitalCode(),9);
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

	//For Duplicacy check
	public static boolean chkRoomDuplicate(RoomVO objModelRoom,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		ResultSet rs = null;
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_ROOM_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelRoom);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelRoom.getStrRoomCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_roomname",objModelRoom.getStrRoomName(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelRoom.getStrHospitalCode(),4);			
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

		}catch (Exception e) 
		{
			e.printStackTrace();
			objModelRoom.setStrMsgString("RoomDAO.chkRoomDuplicate() --> " + e.getMessage());
			objModelRoom.setStrMsgType("1");

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
			throw new HisDataAccessException("RoomDAO():HelperMethodsDAO.chkRoomDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2"))
		{
			if(objModelRoom.getStrOldRoomName().equalsIgnoreCase(objModelRoom.getStrRoomName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelRoom.getStrOldRoomName().equalsIgnoreCase(objModelRoom.getStrRoomName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else {
			bExistStatus=false;
		}
		return bExistStatus;
	}

	//To List the Room in the Room Master
	public static List getRoomList(String deptUnitCode,HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_ROOMSLIST_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",deptUnitCode,4);
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
				//throw new HisRecordNotFoundException("Room List Not Found");
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


}
