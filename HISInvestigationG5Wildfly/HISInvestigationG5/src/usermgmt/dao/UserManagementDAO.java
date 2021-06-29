package usermgmt.dao;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HISDataAccessException;
import hisglobal.exceptions.HISException;
import hisglobal.exceptions.HISRecordNotFoundException;
import hisglobal.exceptions.HISDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.helper.PopulateHelperMethods;
import hisglobal.vo.CommonAlertVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.persistence.internal.xr.ValueObject;

import usermgmt.config.UserManagementConfig;
import vo.usermgmt.HolidayMasterVO;
import vo.usermgmt.HospitalMasterVO;
import vo.usermgmt.MenuMasterVO;
import vo.usermgmt.UserLoginLogVO;
import vo.usermgmt.UserMasterVO;

public class UserManagementDAO extends DataAccessObject
{
	public UserManagementDAO()
	{
		super(null);
	}

	public UserManagementDAO(TransactionContext objTransactionContext_p)
	{
		super(objTransactionContext_p);
	}

	/**
	 * Fetching User Detail
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voUser_p
	 * @return List<UserMasterVO>
	 * @throws Exception
	 * @author Pragya Sharma on 2014.01.07
	 */
	public List<UserMasterVO> getUserDetail(HisDAO hisDAO_p, String strMode_p, UserMasterVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_VIEW_USER_MASTER);

			// Setting and Registering In and Out Parameters 
		    hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (voUser_p.getVarHospitalCode()==null) ? "":voUser_p.getVarHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (voUser_p.getVarUserId()==null) ? "":voUser_p.getVarUserId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_name", (voUser_p.getVarUserName()==null) ? "":voUser_p.getVarUserName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (voUser_p.getVarUserSeatId()==null) ? "":voUser_p.getVarUserSeatId(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_no", (voUser_p.getVarEmpNo()==null) ? "":voUser_p.getVarEmpNo(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_question_id", (voUser_p.getVarQuestionId()==null) ? "":voUser_p.getVarQuestionId(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hint_answer", (voUser_p.getVarHintAnswer()==null) ? "":voUser_p.getVarHintAnswer(),8);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,9); // VarChar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,10); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getUserDetail()::hisDAO_p.executeProcedureByPosition" + UserManagementConfig.PROC_VIEW_USER_MASTER 
					+ ") -> " + e.getMessage());
		}
		
		List<UserMasterVO> lst = new ArrayList<UserMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				
				objResSet.beforeFirst();
				arrVOs = PopulateHelperMethods.populateVOfrmRS(UserMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((UserMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getUserDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	/**
	 * Inserting/Update User Master
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya Sharma on 2014.01.09
	 */
	public void dmlUserDetail(HisDAO hisDAO_p, String strMode_p, UserMasterVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_DML_USER_MASTER);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (voUser_p.getVarHospitalCode()==null) ? "":voUser_p.getVarHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (voUser_p.getVarUserId()==null) ? "":voUser_p.getVarUserId(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_name", (voUser_p.getVarUserName()==null) ? "":voUser_p.getVarUserName(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (voUser_p.getVarUserSeatId()==null) ? "":voUser_p.getVarUserSeatId(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_no", (voUser_p.getVarEmpNo()==null) ? "":voUser_p.getVarEmpNo(), 6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_question_id", (voUser_p.getVarQuestionId()==null) ? "":voUser_p.getVarQuestionId(), 7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hint_answer", (voUser_p.getVarHintAnswer()==null) ? "":voUser_p.getVarHintAnswer(), 8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_password", (voUser_p.getVarPassword()==null) ? "":voUser_p.getVarPassword(), 9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_old_password", (voUser_p.getVarOldPassword()==null) ? "":voUser_p.getVarOldPassword(), 10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mobile_number", (voUser_p.getVarMobileNumber()==null) ? "":voUser_p.getVarMobileNumber(), 11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_email_id", (voUser_p.getVarEmailId()==null) ? "":voUser_p.getVarEmailId(), 12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_menu_id", (voUser_p.getVarMenuId()==null) ? "":voUser_p.getVarMenuId(), 13);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 14); // varchar

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters not in case of Batch Processing 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.dmlUserDetail()::hisDAO_p.executeProcedureByPosition" + UserManagementConfig.PROC_DML_USER_MASTER 
					+ ") -> " + e.getMessage());
		}
	}

	/**
	 * Inserting/Update Menu Master
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param menuMasterVO_p
	 * @return
	 * @throws Exception
	 * @author Komal Yadav on 2014.02.14
	 */
	public void dmlMenuMasterDetail(HisDAO hisDAO_p, String strMode_p, MenuMasterVO menuVO_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		try
		{   System.out.println("user id dao===="+menuVO_p.getVarUserId());
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_DML_MENU_MASTER);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (menuVO_p.getVarHospitalCode()==null) ? "":menuVO_p.getVarHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (menuVO_p.getVarUserId()==null) ? "":menuVO_p.getVarUserId(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_menu_id", (menuVO_p.getVarMenuId()==null) ? "":menuVO_p.getVarMenuId(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (menuVO_p.getVarSeatId()==null) ? "":menuVO_p.getVarSeatId(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_display_order", (menuVO_p.getVarDisplayOrder()==null) ? "":menuVO_p.getVarDisplayOrder(), 6);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 7); // varchar

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters not in case of Batch Processing 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.dmlUserDetail()::hisDAO_p.executeProcedureByPosition" + UserManagementConfig.PROC_DML_USER_MASTER 
					+ ") -> " + e.getMessage());
		}
	}

	/**
	 * Fetching Hospital Detail
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voHospital_p
	 * @return List<HospitalMasterVO>
	 * @throws Exception
	 * @author Pragya Sharma on 2014.01.10
	 */
	public List<HospitalMasterVO> getHospitalDetail(HisDAO hisDAO_p, String strMode_p, HospitalMasterVO voHospital_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_VIEW_HOSPITAL_MASTER);

			// Setting and Registering In and Out Parameters 
		    hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (voHospital_p.getVarHospitalCode()==null) ? "":voHospital_p.getVarHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (voHospital_p.getVarUserId()==null) ? "":voHospital_p.getVarUserId(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (voHospital_p.getVarUserSeatId()==null) ? "":voHospital_p.getVarUserSeatId(), 4);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 5); // VarChar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 6); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getHospitalDetail()::hisDAO_p.executeProcedureByPosition" + UserManagementConfig.PROC_VIEW_HOSPITAL_MASTER 
					+ ") -> " + e.getMessage());
		}
		
		List<HospitalMasterVO> lst = new ArrayList<HospitalMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = PopulateHelperMethods.populateVOfrmRS(HospitalMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((HospitalMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getHospitalDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	/**
	 * Fetching Detailed List
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voUser_p
	 * @return List<Entry>
	 * @throws Exception
	 * @author Pragya Sharma on 2014.01.10
	 */
	public List<Entry> getDetailedList(HisDAO hisDAO_p, String strMode_p, UserMasterVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_VIEW_UM_TABLES);

			// Setting and Registering In and Out Parameters 
		    hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (voUser_p.getVarHospitalCode()==null) ? "":voUser_p.getVarHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (voUser_p.getVarUserId()==null) ? "":voUser_p.getVarUserId(), 3);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_Default_menu_id", (voUser_p.getVarDefaultMenuId()==null) ? "":voUser_p.getVarDefaultMenuId(), 4);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 4); // VarChar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 5); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getHospitalDetail()::hisDAO_p.executeProcedureByPosition" + UserManagementConfig.PROC_VIEW_HOSPITAL_MASTER 
					+ ") -> " + e.getMessage());
		}
		
		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			if (objResSet.next())
			{
				lst = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getHospitalDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Fetching User Menu Detail
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voHospital_p
	 * @return List<HospitalMasterVO>
	 * @throws Exception
	 * @author Pragya Sharma on 2014.01.10
	 */
	public List<MenuMasterVO> getUserMenuDetail(HisDAO hisDAO_p, String strMode_p, UserMasterVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_VIEW_MENU_MASTER);
			
			// Setting and Registering In and Out Parameters 
		    hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (voUser_p.getVarHospitalCode()==null) ? "":voUser_p.getVarHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (voUser_p.getVarUserId()==null) ? "":voUser_p.getVarUserId(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (voUser_p.getVarUserSeatId()==null) ? "":voUser_p.getVarUserSeatId(), 4);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 5); // VarChar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 6); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getUserMenuDetail()::hisDAO_p.executeProcedureByPosition" + UserManagementConfig.PROC_VIEW_MENU_MASTER 
					+ ") -> " + e.getMessage());
		}
		
		List<MenuMasterVO> lst = new ArrayList<MenuMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = PopulateHelperMethods.populateVOfrmRS(MenuMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((MenuMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getUserMenuDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Inserting/Update User Unsuccessful Log
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voUserLoginLog_p
	 * @return
	 * @throws Exception
	 * @author Pragya Sharma on 2014.01.09
	 */
	public void dmlUserLoginLog(HisDAO hisDAO_p, String strMode_p, UserLoginLogVO voUserLoginLog_p) throws Exception
	{
		int nProcedureIndex;
		//String strDBErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_DML_USER_LOGIN_LOG_DETAIL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (voUserLoginLog_p.getVarHospitalCode()==null) ? "":voUserLoginLog_p.getVarHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (voUserLoginLog_p.getVarUserId()==null) ? "":voUserLoginLog_p.getVarUserId(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_login_user_name", (voUserLoginLog_p.getVarUserName()==null) ? "":voUserLoginLog_p.getVarUserName(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_ip_address", (voUserLoginLog_p.getVarIPAddress()==null) ? "":voUserLoginLog_p.getVarIPAddress(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (voUserLoginLog_p.getVarSeatId()==null) ? "":voUserLoginLog_p.getVarSeatId(), 6);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 7); // varchar

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters not in case of Batch Processing 
//			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
//
//			// If Database Error Occurs, No farther processing is required. 
//			if (strDBErr != null && !strDBErr.equals(""))
//			{
//				throw new Exception("Data Base Error:" + strDBErr);
//			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.dmlUserLoginLog()::hisDAO_p.execute" + UserManagementConfig.PROC_DML_USER_LOGIN_LOG_DETAIL 
					+ ") -> " + e.getMessage());
		}
	}

	/**
	 * Fetching User Detail
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voUserLoginLog_p
	 * 
	 * @return List<UserLoginLogVO>
	 * @throws Exception
	 * @author Pragya Sharma on 2014.01.09
	 */
	public List<UserLoginLogVO> getUserLoginLog(HisDAO hisDAO_p, String strMode_p, UserLoginLogVO voUserLoginLog_p,String frDate,String toDate) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(UserManagementConfig.PROC_VIEW_USER_LOGIN_LOG_DETAIL);

			// Setting and Registering In and Out Parameters 
		    hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", (voUserLoginLog_p.getVarHospitalCode()==null) ? "":voUserLoginLog_p.getVarHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", (voUserLoginLog_p.getVarUserId()==null) ? "":voUserLoginLog_p.getVarUserId(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_login_user_name",voUserLoginLog_p.getVarUserName()==null?"":voUserLoginLog_p.getVarUserName(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_ip_address",voUserLoginLog_p.getVarIPAddress()==null?"":voUserLoginLog_p.getVarIPAddress(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (voUserLoginLog_p.getVarSeatId()==null) ? "":voUserLoginLog_p.getVarSeatId(), 6);
     		hisDAO_p.setProcInValue(nProcedureIndex, "p_fdate",frDate==null?"":frDate, 7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_tdate",toDate==null?"":toDate, 8);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 9); // VarChar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 10); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getUserLoginLog()::hisDAO_p.executeProcedureByPosition" + UserManagementConfig.PROC_VIEW_USER_LOGIN_LOG_DETAIL 
					+ ") -> " + e.getMessage());
		}
		
		List<UserLoginLogVO> lst = new ArrayList<UserLoginLogVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = PopulateHelperMethods.populateVOfrmRS(UserLoginLogVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((UserLoginLogVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getUserLoginLog()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	
	/**
	 * Fetching Alert county
	 * 
	 * @param hisDAO_p
	 * @param voUserLoginLog_p
	 * 
	 * @return List<UserLoginLogVO>
	 * @throws Exception
	 * @author Singaravelan on 21-Nov-2014
	 */
	public CommonAlertVO[] getAllAutomaticAlertBySeatID(HisDAO hisDAO_p, UserMasterVO userVO) throws Exception
	{
		String errorMsg="";
		hisglobal.vo.ValueObject[] vo={};
		CommonAlertVO[] commonAlertVO;
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure("{call pkg_alert_dtl.proc_alert_list(?,?,?,?)}");
			
			 hisDAO_p.setProcInValue(nProcedureIndex, "hcode", userVO.getVarHospitalCode(), 1);
			 hisDAO_p.setProcInValue(nProcedureIndex, "userid",userVO.getVarUserId(), 2);
				
			 hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 3); // VarChar
			 hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 4); // Cursor

			 // Executing Procedure 
			 hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			 
			 strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			 objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		}
		catch(HISRecordNotFoundException e)
		{
			throw new HISRecordNotFoundException("No Record Found");
		}
		catch (HISException e)
		{
			throw new HISDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			vo=HelperMethods.populateVOfrmRS(CommonAlertVO.class, objResSet);
			commonAlertVO=new CommonAlertVO[vo.length];
			
			for(int i=0;i<vo.length;i++)
			{
				commonAlertVO[i]=(CommonAlertVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO::getAllAutomaticAlertBySeatID"+e);
		}
		return commonAlertVO;
	}
	
	
	/**
	 * Fetching User Cash Collected Details
	 * 
	 * @param hisDAO_p
	 * @param voUserLoginLog_p
	 * 
	 * @return Map<Key,Map>
	 * @throws Exception
	 * @author Singaravelan on 02-Jun-2015
	 */
	public Map<String,String> getUserWiseCashCollected(HisDAO hisDAO_p, UserMasterVO userVO) throws Exception
	{
		String errorMsg="";
		hisglobal.vo.ValueObject[] vo={};
		Map<String,String> cashCollected=new HashMap<String, String>();
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure("{call pkg_reg_view.proc_cashcoll_outbound_dtl(?,?,?,?)}");
			
			 hisDAO_p.setProcInValue(nProcedureIndex, "hcode", userVO.getVarHospitalCode(), 1);
			 hisDAO_p.setProcInValue(nProcedureIndex, "userid",userVO.getVarUserId(), 2);
				
			 hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 3); // VarChar
			 hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 4); // Cursor

			 // Executing Procedure 
			 hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			 
			 strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			 objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		}
		catch(HISRecordNotFoundException e)
		{
			throw new HISRecordNotFoundException("No Record Found");
		}
		catch (HISException e)
		{
			throw new HISDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			while(objResSet.next()){
				cashCollected.put("userName", objResSet.getString(1));
				cashCollected.put("billCount", objResSet.getString(2));
				cashCollected.put("receivedAmount", objResSet.getString(3));
				cashCollected.put("refundAmount", objResSet.getString(4));
				cashCollected.put("netAmount", objResSet.getString(5));
			}
		}
		catch(Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO::getUserWiseCashCollected"+e);
		}
		return cashCollected;
	}
	
	
	/**
	 * To Fetch checkBackDateDayEnd Flag Details
	 * 
	 * @param hisDAO_p
	 * @param voUserLoginLog_p
	 * 
	 * @return String
	 * @throws Exception
	 * @author Singaravelan on 02-Jun-2015
	 */
	public String checkBackDateDayEnd(HisDAO hisDAO_p,String _mode, UserMasterVO userVO) throws Exception
	{
				
		String _checkBackDateDayEndFlag=new String();
		int funcIndex=0;
		try 
		{
			funcIndex = hisDAO_p.setFunction("{? = call pkg_reg_util.fun_check_backdate_dayend(?,?,?)}");
			hisDAO_p.setFuncInValue(funcIndex, 2, _mode);
			hisDAO_p.setFuncInValue(funcIndex, 3, userVO.getVarUserId());
			hisDAO_p.setFuncInValue(funcIndex, 4, userVO.getVarHospitalCode());

			hisDAO_p.setFuncOutValue(funcIndex,3);
			
			hisDAO_p.executeFuncForNumeric(funcIndex);
			_checkBackDateDayEndFlag = hisDAO_p.getFuncNumeric(funcIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HISDataAccessException("UserManagementDAO::checkBackDateDayEnd()" + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		return _checkBackDateDayEndFlag;		
		
	}
	
	/**
	 * Fetching Holiday Details
	 * 
	 * @param hisDAO_p
	 * @param voUserLoginLog_p
	 * 
	 * @return List<HolidayMasterVO>
	 * @throws Exception
	 * @author Singaravelan on 16-Nov-2015
	 */
	public List<HolidayMasterVO> getAllHolidayBySeatID(HisDAO hisDAO_p, UserMasterVO userVO,String mode) throws Exception
	{
		String errorMsg="";
		hisglobal.vo.ValueObject[] vo={};
		CommonAlertVO[] commonAlertVO;
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure("{call pkg_usermgmt.proc_gblt_holiday_list(?,?,?,?,?)}");
			
			 hisDAO_p.setProcInValue(nProcedureIndex, "mode", mode, 1);
			 hisDAO_p.setProcInValue(nProcedureIndex, "hcode", userVO.getVarHospitalCode(), 2);
			 hisDAO_p.setProcInValue(nProcedureIndex, "userid",userVO.getVarUserId(), 3);
				
			 hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 4); // VarChar
			 hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 5); // Cursor

			 // Executing Procedure 
			 hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			 
			 strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			 objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

		}
		catch(HISRecordNotFoundException e)
		{
			throw new HISRecordNotFoundException("No Record Found");
		}
		catch (HISException e)
		{
			throw new HISDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		List<HolidayMasterVO> lst = new ArrayList<HolidayMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = PopulateHelperMethods.populateVOfrmRS(HolidayMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((HolidayMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HISDataAccessException("UserManagementDAO.getAllHolidayBySeatID()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
}