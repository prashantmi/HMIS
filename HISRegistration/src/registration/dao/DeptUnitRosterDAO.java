/**
 * 
 */
package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;






import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.RosterMasterVO;
import vo.registration.UnitVO;

/**
 * @author s.singaravelan
 *
 */
public class DeptUnitRosterDAO
{
	
	//To Get the Dept Unit Shift wise Roster Details in the Dept Unit Roster Master
	public static Collection getDeptUnitShiftWiseRosterDtl(RosterMasterVO objrosterMst_p,HisDAO hisDAO_p,UserVO uservo)
	{
		Collection collRosterMasterVO=new ArrayList();
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNIT_ROSTER_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objrosterMst_p.getStrRoomCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objrosterMst_p.getStrDeptUnitCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shifttype",RegistrationConfig.SHIFT_TYPE_OPD,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8);

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			

		}catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}

		try 
		{
			if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Roster Details Found");
			}
			else
			{
				//rs.beforeFirst();
				do{
				RosterMasterVO _rosterMasterVO=new RosterMasterVO();
				HelperMethods.populateVOfrmRS(_rosterMasterVO, rs);
				collRosterMasterVO.add(_rosterMasterVO);
				}while(rs.next());
			}
		}catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("RosterDAO:getRosterDetails:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}

		return collRosterMasterVO;
	}
	
	//To Retrive the Roster Details in the Dept Unit Roster Master
	public static Collection retrieveRosterDtl(RosterMasterVO objrosterMst_p,HisDAO hisDAO_p,UserVO uservo)
	{
		Collection collRosterMasterVO=new ArrayList();
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNIT_ROSTER_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objrosterMst_p.getStrRoomCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objrosterMst_p.getStrDeptUnitCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shifttype","",4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8);

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
				//throw new HisRecordNotFoundException("Unit List Not Found");
			}
			else
			{
				//rs.beforeFirst();
				do{
				RosterMasterVO _rosterMasterVO=new RosterMasterVO();
				HelperMethods.populateVOfrmRS(_rosterMasterVO, rs);
				collRosterMasterVO.add(_rosterMasterVO);
				}while(rs.next());
			}
		}
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("RosterDAO:getRosterDetails:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}

		return collRosterMasterVO;
	}
	
	//To Save the Roster in the Dept Unit Roster Mst
	public static void setDeleteIsValid(RosterMasterVO objModelRoster,HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNIT_ROSTER_DML;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelRoster);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","6",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelRoster.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelRoster.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_weekofmonth",objModelRoster.getWeekOfMonth(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_dayofweek",objModelRoster.getDayOfWeek(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shiftcode",objModelRoster.getStrShiftCode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelRoster.getStrRoomCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomsequence",objModelRoster.getStrRoomSequence(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcapacity",objModelRoster.getStrRoomCapacity(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_opdname",objModelRoster.getStrOpdName(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomusability",objModelRoster.getRoomUsability(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unit_seqno",objModelRoster.getUnitSequenceNo(),12);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_DELETED,13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),15);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,16); 
			
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
			objModelRoster.setStrMsgString("UnitDAO.saveUnitDetails() --> "	+ e.getMessage());
			objModelRoster.setStrMsgType("1");
			throw new HisDataAccessException(e.getMessage());
		}
	}

	
	//To Save the Roster in the Dept Unit Roster Mst
	public static void create(RosterMasterVO objModelRoster,HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNIT_ROSTER_DML;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelRoster);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelRoster.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelRoster.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_weekofmonth",objModelRoster.getWeekOfMonth(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_dayofweek",objModelRoster.getDayOfWeek(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shiftcode",objModelRoster.getStrShiftCode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelRoster.getStrRoomCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomsequence",objModelRoster.getStrRoomSequence(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcapacity",objModelRoster.getStrRoomCapacity(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_opdname",objModelRoster.getStrOpdName(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomusability",objModelRoster.getRoomUsability(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unit_seqno",objModelRoster.getUnitSequenceNo(),12);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),15);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,16); 
			
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
			objModelRoster.setStrMsgString("UnitDAO.saveUnitDetails() --> "	+ e.getMessage());
			objModelRoster.setStrMsgType("1");
			throw new HisDataAccessException(e.getMessage());
		}
	}
	
	//To Update the Roster in the Dept Unit Roster Mst
	public static void update(RosterMasterVO objModelRoster,HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPTUNIT_ROSTER_DML;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelRoster);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelRoster.getStrDeptUnitCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelRoster.getStrDeptCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_weekofmonth",objModelRoster.getWeekOfMonth(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_dayofweek",objModelRoster.getDayOfWeek(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_shiftcode",objModelRoster.getStrShiftCode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcode",objModelRoster.getStrRoomCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomsequence",objModelRoster.getStrRoomSequence(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomcapacity",objModelRoster.getStrRoomCapacity(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_opdname",objModelRoster.getStrOpdName(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_roomusability",objModelRoster.getRoomUsability(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unit_seqno",objModelRoster.getUnitSequenceNo(),12);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),15);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,16); 
			
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
			objModelRoster.setStrMsgString("UnitDAO.saveUnitDetails() --> "	+ e.getMessage());
			objModelRoster.setStrMsgType("1");
			throw new HisDataAccessException(e.getMessage());
		}
	}
	
	//To Execute Dept Unit Specific Roster
	public static void executeDeptUnitSpecificRoster(RosterMasterVO objModelRoster,UserVO uservo,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_OPD_ROSTER_DUC_MOD;
		final int nProcedureIndex;
		final String strDbErr;		
		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelRoster);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelRoster.getStrDeptUnitCode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_effectdate","",2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);		
			
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			
			
			
      			
		}
		catch(SQLException e )
		{
			e.printStackTrace();
			throw new HisDataAccessException("sql exception in executeRoster-->"+e);				
		}  
		catch(Exception e){
			e.printStackTrace();
			throw new HisException("RosterMasterDAO:retrieveRosterDtl::Roster Master Details:: "+e);
		}		
			
		
	}
	
	//To Execute Roster
	public static int executeRosterForAll(String sysDate,UserVO uservo,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_OPD_ROSTER_MOD;
		final int nProcedureIndex;
		final String strDbErr;		
		int status=0;
		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),1);					
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_effectdate",sysDate,2);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			status=1;
			
			
      			
		}
		catch(SQLException e )
		{
			e.printStackTrace();
			status=0;
			throw new HisDataAccessException("sql exception in executeRoster-->"+e);				
		}  
		catch(Exception e){
			e.printStackTrace();
			status=0;
			throw new HisException("RosterMasterDAO:retrieveRosterDtl::Roster Master Details:: "+e);
		}		
			
		return status;
		
	}

	
}
