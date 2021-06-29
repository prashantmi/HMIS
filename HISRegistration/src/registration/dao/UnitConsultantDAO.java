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
import vo.registration.UnitConsultantVO;

/**
 * @author s.singaravelan
 *
 */
public class UnitConsultantDAO
{
	
	//To List the Consultant Names in the Unit Consultant Master
	public static List getConsultantList(HisDAO hisDAO_p,UnitConsultantVO objModelDeptUnitCon,UserVO uservo)
	{
		List alRecord = new ArrayList(); 
	
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_HEADOFDEPARTMENT_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_desigcode","",4);
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
			//throw new HisRecordNotFoundException("Consultant Details Not Found");
			}else{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("UnitConsultantDAO:getRoomsList:HelperMethods :: " + e);
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
	
	//To Get Alloted Consultant Details in he Unit Consultant Mst
	public static UnitConsultantVO[] getAllotedConsultantsToUnits(HisDAO hisDAO_p,String _deptUnitCode,UserVO uservo){
		
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNITCONSULTANT_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		ValueObject[] vo= {};  
		UnitConsultantVO[] unitConsultantMasterVO;
   
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);			
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",_deptUnitCode,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_empcode","",3);
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
		 	    	vo=HelperMethods.populateVOfrmRS(UnitConsultantVO.class,rs);
		 	    	unitConsultantMasterVO =new UnitConsultantVO[vo.length];
		 	    	 for(int i=0;i<vo.length;i++){					
		 	    		unitConsultantMasterVO[i]=(UnitConsultantVO)vo[i];					
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
		return unitConsultantMasterVO;
		
	}		 
	
	//To Save the Room Details in the Department Unit Room Mst
	public static String createUnitconsultantDtl(UnitConsultantVO objModelUnitCon,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNITCONSULTANT_DML;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		Boolean flag = null;
		String strReuestNo="";
		int funcIndex = 0;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelUnitCon);			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnitCon.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_empcode",objModelUnitCon.getStrEmpCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_locationcode",objModelUnitCon.getStrLocCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ihou",objModelUnitCon.getStrIsHeadOfUnit(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hierarchy",objModelUnitCon.getStrHierarchyLevel(),6);
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnitCon.getStrHospitalCode(),9);

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
			objModelUnitCon.setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
			objModelUnitCon.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	
//	//To Delete the Room Details in the Department Unit Room Mst
//	public static String deleteDeptUnitRoomDtl(UnitConsultantVO objModelUnitCon,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
//	{
//		ResultSet rs = null;
//		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNITCONSULTANT_DML;
//		final int nProcedureIndex;
//		final String strDbErr;
//		WebRowSet webRowSet = null;
//		Boolean flag = null;
//		String strReuestNo="";
//		int funcIndex = 0;
//		//
//		try
//		{
//			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
//			HisUtil.replaceNullValueWithEmptyString(objModelUnitCon);			
//			
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnitCon.getStrDeptUnitCode(),2);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_empcode",objModelUnitCon.getStrEmpCode(),3);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_locationcode",objModelUnitCon.getStrLocCode(),4);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ihou",objModelUnitCon.getStrIsHeadOfUnit(),5);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hierarchy",objModelUnitCon.getStrHierarchyLevel(),6);
//		
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);
//			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnitCon.getStrHospitalCode(),9);
//
//			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,10); 
//		
//			hisDAO_p.executeProcedureByPosition(nProcedureIndex);			
//			
//			/* Getting out parameters */
//			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
//			System.out.println("Data Inserted Successussfully");
//			
//			/* If Database Error Occurs, No farther processing is required. */
//			if (strDbErr != null && !strDbErr.equals("")) {
//				throw new Exception("Data Base Error:" + strDbErr);
//			}
//			
//		}
//
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			objModelUnitCon.setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
//			objModelUnitCon.setStrMsgType("1");
//			//throw new HisDataAccessException(e.getMessage());
//		}
//		return strReuestNo;
//	}
	
//	//To Get the Deleted or Updated Record Details in he Department Unit Room Mst
//	public static Collection getDeletedOrUpdatedRecords(HisDAO hisDAO_p,UserVO uservo){
//		
//		ResultSet rs = null;
//		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNITCONSULTANT_VIEW;
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
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_empcode","",3);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
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
//		      	  col.add(new UnitConsultantVO(str,str1));    		
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
	
	
	
	//To get Consultants Details in Modify
	public static UnitConsultantVO modifyDetails(UnitConsultantVO objModelDept,HisDAO hisDAO_p) 
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNITCONSULTANT_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		ValueObject[] vo= {};  
		UnitConsultantVO departmentUnitRoomMasterVO=new UnitConsultantVO();
   
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);			
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDept.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_empcode",objModelDept.getStrEmpCode(),3);
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
	
	//To Update the Consultant Details in the Unit Consultant Mst
	public static String updateDeptUnitRoomDetails(UnitConsultantVO objModelUnitCon,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNITCONSULTANT_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelUnitCon);
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelUnitCon.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_empcode",objModelUnitCon.getStrEmpCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_locationcode",objModelUnitCon.getStrLocCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_ihou",objModelUnitCon.getStrIsHeadOfUnit(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hierarchy",objModelUnitCon.getStrHierarchyLevel(),6);
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelUnitCon.getStrHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,10); 
		
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
			objModelUnitCon.setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
			objModelUnitCon.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}

	
}
