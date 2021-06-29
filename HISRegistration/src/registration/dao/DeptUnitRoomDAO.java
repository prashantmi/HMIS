/**
 * 
 */
package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationDaoConfig;
import vo.registration.DepartmentVO;
import vo.registration.DeptUnitRoomVO;

/**
 * @author s.singaravelan
 *
 */
public class DeptUnitRoomDAO
{
	
	//To List the Room Names in the Department Unit Room Master
	public static List getRoomsList(HisDAO hisDAO_p,DeptUnitRoomVO objModelDeptUnitRoom,UserVO uservo)
	{
		List alRecord = new ArrayList(); 
	
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_ROOMSLIST_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDeptUnitRoom.getStrDeptUnitCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);
			
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
			//throw new HisRecordNotFoundException("Rooms Details Not Found");
			}else{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("DepartmentUnitRoomDAO:getRoomsList:HelperMethods :: " + e);
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
	 
	
	//To Save the Room Details in the Department Unit Room Mst
	public static String createDeptUnitRoomDtl(DeptUnitRoomVO objModelDeptUnitRoom,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNITROOM_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelDeptUnitRoom);			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDeptUnitRoom.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDeptUnitRoom.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelDeptUnitRoom.getStrUnitCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelDeptUnitRoom.getStrRoomCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_capacity",objModelDeptUnitRoom.getStrCapacity(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seqno",objModelDeptUnitRoom.getStrRoomSequence(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_capacitymode",objModelDeptUnitRoom.getStrCapacityMode(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),11);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDeptUnitRoom.getStrHospitalCode(),11);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,12); 
			
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
			objModelDeptUnitRoom.setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
			objModelDeptUnitRoom.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	
	//To Delete the Room Details in the Department Unit Room Mst
	public static String deleteDeptUnitRoomDtl(DeptUnitRoomVO objModelDeptUnitRoom,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNITROOM_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelDeptUnitRoom);
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDeptUnitRoom.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDeptUnitRoom.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelDeptUnitRoom.getStrUnitCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelDeptUnitRoom.getStrRoomCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_capacity",objModelDeptUnitRoom.getStrCapacity(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seqno",objModelDeptUnitRoom.getStrRoomSequence(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_capacitymode",objModelDeptUnitRoom.getStrCapacityMode(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),11);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDeptUnitRoom.getStrHospitalCode(),11);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,12); 
			
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
			objModelDeptUnitRoom.setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
			objModelDeptUnitRoom.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
//	//To Get the Deleted or Updated Record Details in he Department Unit Room Mst
//	public static Collection getDeletedOrUpdatedRecords(HisDAO hisDAO_p,UserVO uservo){
//		
//		ResultSet rs = null;
//		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNITROOM_VIEW;
//		final int nProcedureIndex;
//		final String strDbErr;
//	    Collection col=new ArrayList();   
//		
//		try
//		{
//			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
//			
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode","",2);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deleted",Config.IS_VALID_DELETED,3);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_INACTIVE,4);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),5);
//			
//			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 
//			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7);
//			
//			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
//			
//			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
//			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			
//			
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
//		}
//		
//		try 
//		{
//			while(rs.next()){
//		      	  String str=rs.getString(1);
//		      	  String str1=rs.getString(2);
//		      	  col.add(new DeptUnitRoomVO(str,str1));    		
//		    	}
//		} catch (Exception e) {
//			if (e.getClass() == HisRecordNotFoundException.class) {
//				throw new HisRecordNotFoundException(e.getMessage());
//			} else
//				throw new HisDataAccessException("DepartmentUnitRoomDAO:getRoomsList:HelperMethods :: " + e);
//		}
//		finally 
//		{
//			if (hisDAO_p != null) 
//			{
//				hisDAO_p.free();hisDAO_p = null;
//			}
//		}		
//		return col;
//		
//	}	
//	
	//To Get Alloted Room Record Details in he Department Unit Room Mst
	public static DeptUnitRoomVO[] getAllotedRoomsToUnitsWithSequence(HisDAO hisDAO_p,String _deptUnitCode,UserVO uservo){
		
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNITROOM_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		ValueObject[] vo= {};  
		DeptUnitRoomVO[] departmentUnitRoomMasterVO;
   
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);			
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",_deptUnitCode,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deleted",Config.IS_VALID_DELETED,3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),5);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7);
			
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
	 	    	throw new HisRecordNotFoundException();	 	    
				}else{
					rs.beforeFirst();
		 	    	vo=HelperMethods.populateVOfrmRS(DeptUnitRoomVO.class,rs);
		 	    	departmentUnitRoomMasterVO =new DeptUnitRoomVO[vo.length];
		 	    	 for(int i=0;i<vo.length;i++){					
		 	    		departmentUnitRoomMasterVO[i]=(DeptUnitRoomVO)vo[i];					
					 }	
				}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("DepartmentUnitRoomDAO:getRoomsList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}		
		return departmentUnitRoomMasterVO;
		
	}	
	
	
	//To get Department Details in Modify
	public static DeptUnitRoomVO modifyDetails(DeptUnitRoomVO objModelDept,HisDAO hisDAO_p) 
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNITROOM_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		ValueObject[] vo= {};  
		DeptUnitRoomVO departmentUnitRoomMasterVO=new DeptUnitRoomVO();
   
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);			
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDept.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deleted",objModelDept.getStrRoomCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDept.getStrHospitalCode(),5);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");		
			
		}
		catch (Exception e) {
			e.printStackTrace();
			objModelDept.setStrMsgString("RequestdetailDAO.getStoreName() --> "	+ e.getMessage());
			objModelDept.setStrMsgType("1");
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		try 
		{
			if (!rs.next()) {
	 	    	throw new HisRecordNotFoundException();	 	    
				}else{
					rs.beforeFirst();
		 	    	HelperMethods.populateVOfrmRS(departmentUnitRoomMasterVO, rs);		
					 
				}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("DepartmentUnitRoomDAO:getRoomsList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}		
		return departmentUnitRoomMasterVO;
		
	}	
	
	//To Update the Room Details in the Department Unit Room Mst
	public static String updateDeptUnitRoomDetails(DeptUnitRoomVO objModelDeptUnitRoom,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNITROOM_DML;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		Boolean flag = null;
		String strReuestNo="";
		int funcIndex = 0;
		String capMode="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelDeptUnitRoom);
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDeptUnitRoom.getStrDeptUnitCode(),2);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDeptUnitRoom.getStrDeptCode(),3);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelDeptUnitRoom.getStrUnitCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDeptUnitRoom.getStrDeptUnitCode().substring(0, 3),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode",objModelDeptUnitRoom.getStrDeptUnitCode().substring(4, 5),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelDeptUnitRoom.getStrRoomCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_capacity",objModelDeptUnitRoom.getStrCapacity(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seqno",objModelDeptUnitRoom.getStrRoomSequence(),7);
			if(objModelDeptUnitRoom.getStrCapacityMode().equalsIgnoreCase("true"))
				capMode="2";
			else
				capMode="1";
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_capacitymode",capMode,8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),11);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDeptUnitRoom.getStrHospitalCode(),11);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,12); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);			
			
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Modified Successussfully");
			
			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
			
		}

		catch (Exception e)
		{
			e.printStackTrace();
			objModelDeptUnitRoom.setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
			objModelDeptUnitRoom.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}

	
}
