package appointment.dao;
/**
 * Created By 	: Neha Sharma
 * Date			: 3-Jan-2014
 */
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import vo.appointment.ApptConfigMstVO;
import vo.appointment.ShiftDayVO;
import appointment.qryHandler_master;

/* 
 * In General..execute method of HISDAO is used cz cun't find executeupdate{for DML queries} in HISDAO..
 * 
 * data is ADDED i.e inserted in order hapt_actual_para_map_mst,hapt_para_appt_type_mst,hapt_para_schedule_mst
 * 
 * For updating data firstly existing data is deleted from tables in the order hapt_para_schedule_mst,hapt_para_appt_type_mst.
 * data is not deleted but updated in hapt_actual_para_map_mst
 * and after deletion new data is inserted in hapt_para_appt_type_mst,hapt_para_schedule_mst
 * */


public class ApptConfigMstDao extends DataAccessObject {

		public ApptConfigMstDao(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings("rawtypes")
	public static List getApptTypeList(HisDAO hisDAO_p)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		final String strProcName = "{call pkg_appointment_masters.proc_appt_config(?,?,?)}";
		final int nProcedureIndex;
		String strDbErr;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","1",1);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,2); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,3);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
			
			
			if (strDbErr == null)
				strDbErr = "";

			if (strDbErr.equals(""))
			{
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("Appointment Type not Found");
				}
				else
				{
					rs.beforeFirst();
					alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			} 
			else
			{
				throw new Exception(strDbErr);
			}			
		}
		catch (Exception e) 
		{
			throw new HisApplicationExecutionException("HisApplicationExecutionException"+ e);
		}		
		
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		
		return alRecord;
	}
	
	public static List getApptTypeModList(HisDAO hisDAO_p,ApptConfigMstVO appConfMstVo)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		final String strProcName = "{call pkg_appointment_masters.proc_appt_config_modify(?,?,?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pararefid",appConfMstVo.getParaRefId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",appConfMstVo.getHospCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);
			
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
			if (!rs.next())
			{
			throw new HisRecordNotFoundException("Appointment Type not Found");
			}
			else
			{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("AppointmentConfigMstDAO:getApptTypeList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		
		return alRecord;
	}
	
	public static List getApptTypeSelModList(HisDAO hisDAO_p,ApptConfigMstVO appConfMstVo)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		final String strProcName = "{call pkg_appointment_masters.proc_appt_config_modify(?,?,?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pararefid",appConfMstVo.getParaRefId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",appConfMstVo.getHospCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);
			
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
			if (!rs.next())
			{
			throw new HisRecordNotFoundException("Appointment Type not Found");
			}
			else
			{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("AppointmentConfigMstDAO:getApptTypeList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		
		return alRecord;
	}
	
	public static List getDefaultApptTypeModList(HisDAO hisDAO_p,ApptConfigMstVO appConfMstVo)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		final String strProcName = "{call pkg_appointment_masters.proc_appt_config_modify(?,?,?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pararefid",appConfMstVo.getParaRefId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",appConfMstVo.getHospCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);
			
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
			if (!rs.next())
			{
			throw new HisRecordNotFoundException("Appointment Type not Found");
			}
			else
			{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("AppointmentConfigMstDAO:getApptTypeList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		
		return alRecord;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getApptDurationList(HisDAO hisDAO_p)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		final String strProcName = "{call pkg_appointment_masters.proc_appt_config(?,?,?)}";
		final int nProcedureIndex;
		String strDbErr;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","2",1);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,2); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,3);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			
			
			if (strDbErr == null)
				strDbErr = "";
	
			if (strDbErr.equals(""))
			{
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("Appointment Slot Duration not Found");
				}
				else
				{
					rs.beforeFirst();
					alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			} 
			else
			{
				throw new Exception(strDbErr);
			}
		}
		catch (Exception e) 
		{
			throw new HisApplicationExecutionException("HisApplicationExecutionException"+ e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		
		return alRecord;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getApptShiftList(HisDAO hisDAO_p)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		final String strProcName = "{call pkg_appointment_masters.proc_appt_config(?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","3",1);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,2); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,3);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
			
			if (strDbErr.equals(""))
			{
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("Appointment Shift not Found");
				}
				else
				{
					rs.beforeFirst();
					alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			} 
			else
			{
				throw new Exception(strDbErr);
			}
		}
		catch (Exception e) 
		{
			throw new HisApplicationExecutionException("HisApplicationExecutionException"+ e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		
		return alRecord;
	}
	
	// to generate unique paraRefID..
	private static String genParaRefId(String hospCode,String apptForId,HisDAO hisDAO_p)
	{
		String tempParaRefId="0";
		WebRowSet wb=null;
		String query=null;
		int qryIndex=0;   
		hisDAO_p=new HisDAO("Appointment Config","HAPT_ACTUAL_PARA_MAP_MST.GENERATEID");
		
		try
		{
			query=qryHandler_master.getMasterQuery("HAPT_ACTUAL_PARA_MAP_MST.GENERATEID");
			qryIndex= hisDAO_p.setQuery(query);
			hisDAO_p.setQryValue(qryIndex, 1, apptForId);
			hisDAO_p.setQryValue(qryIndex, 2, apptForId);
			hisDAO_p.setQryValue(qryIndex, 3, hospCode);
			wb=hisDAO_p.executeQry(qryIndex);
			
			if(wb.next())
			{
				tempParaRefId=wb.getInt(1)+"";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return tempParaRefId;
	}
	
	public static Boolean saveConfigDetails(ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo)
	{
		String tempParaRefId="0";
		Boolean flag=true;
		String query=null;
		int qryIndex=0;   
		hisDAO_p=new HisDAO("Appointment Config","HAPT_ACTUAL_PARA_MAP_MST.INSERT_RECORD");
		String[] tempActualParaIds=new String[7];
		
		try
		{
			
			tempParaRefId=genParaRefId(uservo.getHospitalCode(),apptConfMstVo.getAppointmentForId(), hisDAO_p);
			query=null;
			qryIndex=0;
			query=qryHandler_master.getMasterQuery("HAPT_ACTUAL_PARA_MAP_MST.INSERT_RECORD");
			qryIndex= hisDAO_p.setQuery(query);
		
			hisDAO_p.setQryValue(qryIndex, 1, tempParaRefId);
			hisDAO_p.setQryValue(qryIndex, 2, uservo.getHospitalCode());
			hisDAO_p.setQryValue(qryIndex, 3,apptConfMstVo.getAppointmentForId());
			
			// actual params can be anything between 1 to 7 (count is refered here..)
			// thus following piece of code..
			for(int i=0;i<apptConfMstVo.getActualParameterId().length;i++)
			{
				tempActualParaIds[i]=apptConfMstVo.getActualParameterId()[i];
			}
			
			
			if(tempActualParaIds[0] == null)
			{
				hisDAO_p.setQryValue(qryIndex, 4, apptConfMstVo.getParaId1());
			}
			else
			{
				hisDAO_p.setQryValue(qryIndex, 4, tempActualParaIds[0]);
			}
			
			if(tempActualParaIds[1] == null)
			{
				hisDAO_p.setQryValue(qryIndex, 5, apptConfMstVo.getParaId2());
			}
			else
			{
				hisDAO_p.setQryValue(qryIndex, 5, tempActualParaIds[1]);
			}
			
			if(tempActualParaIds[2] == null)
			{
				hisDAO_p.setQryValue(qryIndex, 6, apptConfMstVo.getParaId3());
			}
			else
			{
				hisDAO_p.setQryValue(qryIndex, 6, tempActualParaIds[2]);
			}
			
			if(tempActualParaIds[3] == null)
			{
				hisDAO_p.setQryValue(qryIndex, 7, apptConfMstVo.getParaId4());
			}
			else
			{
				hisDAO_p.setQryValue(qryIndex, 7, tempActualParaIds[3]);
			}
			
			if(tempActualParaIds[4] == null)
			{
				hisDAO_p.setQryValue(qryIndex, 8, apptConfMstVo.getParaId5());
			}
			else
			{
				hisDAO_p.setQryValue(qryIndex, 8, tempActualParaIds[4]);
			}
			
			if(tempActualParaIds[5] == null)
			{
				hisDAO_p.setQryValue(qryIndex, 9, apptConfMstVo.getParaId6());
			}
			else
			{
				hisDAO_p.setQryValue(qryIndex, 9, tempActualParaIds[5]);
			}
			
			if(tempActualParaIds[6] == null)
			{
				hisDAO_p.setQryValue(qryIndex, 10, apptConfMstVo.getParaId7());
			}
			else
			{
				hisDAO_p.setQryValue(qryIndex, 10,tempActualParaIds[6]);
			}
			
			hisDAO_p.setQryValue(qryIndex, 11, apptConfMstVo.getIsApptTransferable());
			hisDAO_p.setQryValue(qryIndex, 12, "1");
			hisDAO_p.setQryValue(qryIndex, 13, apptConfMstVo.getAcceptTransferedAppt());
			hisDAO_p.setQryValue(qryIndex, 14, uservo.getSeatId());
			hisDAO_p.setQryValue(qryIndex, 15, apptConfMstVo.getIsQuotaShiftWise());
			hisDAO_p.setQryValue(qryIndex, 16, apptConfMstVo.getApptDurationValue());
			hisDAO_p.setQryValue(qryIndex, 17, apptConfMstVo.getApptDurationUnit());
			hisDAO_p.setQryValue(qryIndex, 18, apptConfMstVo.getPriorApptDays());
			hisDAO_p.setQryValue(qryIndex, 19, apptConfMstVo.getApptDurationUnitTime());
			hisDAO_p.execute(qryIndex,0);
			synchronized (hisDAO_p) 
			{
				hisDAO_p.fire();
			}
			flag=saveConfigDetails2(tempParaRefId,apptConfMstVo, hisDAO_p, uservo);
			System.out.println("successfully inserted in hapt_actual_para_map_mst...");
		}
		
		catch(Exception e)
		{
			flag=false;
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// to save data in table hapt_para_appt_type_mst
	public static Boolean saveConfigDetails2(String tempParaRefId,ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo)
	{
		
		WebRowSet wb=null;
		Boolean flag=true;
		String query=null;
		int qryIndex=0;   
		hisDAO_p=new HisDAO("Appointment Config","HAPT_PARA_APPT_TYPE_MST");
		
		try
		{
			String tempArrApptTypeId[] =apptConfMstVo.getSelApptTypesValues().split("@@");
			String tempArrApptTypeText[] =apptConfMstVo.getSelApptTypesText().split("@@");
			
			query=null;
			qryIndex=0;
			query=qryHandler_master.getMasterQuery("HAPT_PARA_APPT_TYPE_MST");
			
			qryIndex= hisDAO_p.setQuery(query);
			for(int i=0;i<tempArrApptTypeId.length;i++)
			{
				hisDAO_p.setQryValue(qryIndex, 1, tempArrApptTypeId[i].replace("^", "@").split("@")[0]);
				hisDAO_p.setQryValue(qryIndex, 2, tempParaRefId);
				hisDAO_p.setQryValue(qryIndex, 3, uservo.getHospitalCode());
				hisDAO_p.setQryValue(qryIndex, 4,tempArrApptTypeText[i]);
				hisDAO_p.setQryValue(qryIndex, 5,tempArrApptTypeId[i].replace("^", "@").split("@")[1]);
				
				if(apptConfMstVo.getDefaultApptTypeId().equals(tempArrApptTypeId[i]))
				{
					hisDAO_p.setQryValue(qryIndex, 6,"1");
				}
				else
				{
					hisDAO_p.setQryValue(qryIndex, 6,"0");
				}
				
				hisDAO_p.setQryValue(qryIndex, 7, "1");
				hisDAO_p.setQryValue(qryIndex, 8,uservo.getSeatId());
					
				hisDAO_p.execute(qryIndex,0);
			
			}
			synchronized (hisDAO_p) 
			{
				hisDAO_p.fire();
			}

			
			System.out.println("successfully inserted in hapt_para_appt_type_mst...");
			flag=saveConfigDetails3(tempParaRefId,apptConfMstVo, hisDAO_p, uservo);
			
		}
		catch(Exception e)
		{
			flag=false;
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// to save data in table hapt_para_schedule_mst
		public static Boolean saveConfigDetails3(String tempParaRefId,ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo)
		{
			
			WebRowSet wb=null;
			Boolean flag=true;
			String query=null;
			int qryIndex=0;   
			hisDAO_p=new HisDAO("Appointment Config","hapt_para_schedule_mst");
			
			try
			{
				query=null;
				qryIndex=0;
				
				query=qryHandler_master.getMasterQuery("HAPT_PARA_SCHEDULE_MST.INSERT_RECORD");
				qryIndex= hisDAO_p.setQuery(query);
				
				//iterate over shift id..
				for(int i=0;i<apptConfMstVo.getShiftId().length;i++)
				{
					// has shift wise sel days..
					String tempShiftWiseDaysSel[] = apptConfMstVo.getShiftwiseSelectedDays()[i].replace("^", "#").split("#");
					
					//insert for every day selected in the shift..
					for(int j=0;j<tempShiftWiseDaysSel.length;j++)
					{
						hisDAO_p.setQryValue(qryIndex, 1, tempParaRefId);
						hisDAO_p.setQryValue(qryIndex, 2, uservo.getHospitalCode());
						hisDAO_p.setQryValue(qryIndex, 3, apptConfMstVo.getShiftId()[i].equals("")?null:apptConfMstVo.getShiftId()[i]);
						hisDAO_p.setQryValue(qryIndex, 4,apptConfMstVo.getCurrentDateAppt()[i].equals("")?null:apptConfMstVo.getCurrentDateAppt()[i]);
						hisDAO_p.setQryValue(qryIndex, 5,tempShiftWiseDaysSel[j].equals("")?null:tempShiftWiseDaysSel[j]);
						hisDAO_p.setQryValue(qryIndex, 6,apptConfMstVo.getPriorAppt()[i].equals("")?null:apptConfMstVo.getPriorAppt()[i]);
						hisDAO_p.setQryValue(qryIndex, 7, apptConfMstVo.getPortal()[i].equals("")?null:apptConfMstVo.getPortal()[i]);
						hisDAO_p.setQryValue(qryIndex, 8,apptConfMstVo.getStartTime()[i].equals("")?null:apptConfMstVo.getStartTime()[i]);
						hisDAO_p.setQryValue(qryIndex, 9,apptConfMstVo.getOverBook()[i].equals("")?null:apptConfMstVo.getOverBook()[i]);
						hisDAO_p.setQryValue(qryIndex, 10,apptConfMstVo.getEndTime()[i].equals("")?null:apptConfMstVo.getEndTime()[i]);
						hisDAO_p.setQryValue(qryIndex, 11,apptConfMstVo.getIsVipSlotAllowed()[i].equals("")?null:apptConfMstVo.getIsVipSlotAllowed()[i]);
						hisDAO_p.setQryValue(qryIndex, 12,"1");
						hisDAO_p.setQryValue(qryIndex, 13,uservo.getSeatId());
						System.out.println("WeekOfMonth--" + apptConfMstVo.getWeekOfMonth()[i] + " ShiftId ---" + apptConfMstVo.getShiftId()[i]+ " dayofweek--" + tempShiftWiseDaysSel[j] );
						hisDAO_p.setQryValue(qryIndex, 14,apptConfMstVo.getWeekOfMonth()[i].equals("")?null:apptConfMstVo.getWeekOfMonth()[i]);
						hisDAO_p.setQryValue(qryIndex, 15,apptConfMstVo.getOpdApptSlots()[i].equals("")?null:apptConfMstVo.getOpdApptSlots()[i]);
						hisDAO_p.setQryValue(qryIndex, 16,apptConfMstVo.getIpdApptSlots()[i].equals("")?null:apptConfMstVo.getIpdApptSlots()[i]);

						hisDAO_p.execute(qryIndex,0);
					}
				}
				synchronized (hisDAO_p) 
				{
					hisDAO_p.fire();
				}

				
				System.out.println("successfully inserted in hapt_para_schedule_mst...");
			}
			catch(Exception e)
			{
				flag=false;
				e.printStackTrace();
			}
			
			return flag;
		}
	
	// this is to get the selected record for modification..
	public static ApptConfigMstVO modifyRecord(ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo,HttpServletRequest request) 
	{
		final String strProcName = "{call pkg_appointment_masters.proc_appt_config_modify(?,?,?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		ApptConfigMstVO apptConfObj=new ApptConfigMstVO();
		
		try
		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			//HisUtil.replaceNullValueWithEmptyString(apptConfMstVo);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","4",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "pararefid",apptConfMstVo.getParaRefId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",apptConfMstVo.getHospCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDbErr != null && !strDbErr.equals("")) {
				System.out.println("db err::"+strDbErr);
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
			apptConfMstVo.setStrMsgString("ApptConfMstDao.modifyRecord() --> "	+ e.getMessage());
			apptConfMstVo.setStrMsgType("1");
		}
	
			try
			{
				webRowSet.beforeFirst();
				webRowSet.next();
			
				apptConfMstVo.setAppointmentForName(webRowSet.getString(1).trim());
				apptConfMstVo.setAppointmentForId(webRowSet.getString(2).trim());
				apptConfMstVo.setParaId1(webRowSet.getString(3).trim());
				apptConfMstVo.setParaId2(webRowSet.getString(4)!=null?webRowSet.getString(4).trim():"");
				apptConfMstVo.setParaId3(webRowSet.getString(5)!=null?webRowSet.getString(5).trim():"");
				apptConfMstVo.setParaId4(webRowSet.getString(6)!=null?webRowSet.getString(6).trim():"");
				apptConfMstVo.setParaId5(webRowSet.getString(7)!=null?webRowSet.getString(7).trim():"");
				apptConfMstVo.setParaId6(webRowSet.getString(8)!=null?webRowSet.getString(8).trim():"");
				apptConfMstVo.setParaId7(webRowSet.getString(9)!=null?webRowSet.getString(9).trim():"");
				apptConfMstVo.setIsApptTransferable(webRowSet.getString(10));
				apptConfMstVo.setAcceptTransferedAppt(webRowSet.getString(11));
				apptConfMstVo.setIsQuotaShiftWise(webRowSet.getString(12));
				apptConfMstVo.setApptDurationValue(webRowSet.getString(13));
				apptConfMstVo.setApptDurationUnit(webRowSet.getString(14));
				apptConfMstVo.setPriorApptDays(webRowSet.getString(15));
				apptConfMstVo.setLabelPara1(webRowSet.getString(16));
				apptConfMstVo.setLabelPara2(webRowSet.getString(17));
				apptConfMstVo.setLabelPara3(webRowSet.getString(18));
				apptConfMstVo.setLabelPara4(webRowSet.getString(19));
				apptConfMstVo.setLabelPara5(webRowSet.getString(20));
				apptConfMstVo.setLabelPara6(webRowSet.getString(21));
				apptConfMstVo.setLabelPara7(webRowSet.getString(22));
				apptConfMstVo.setDepUnitCode(webRowSet.getString(23));
				
				apptConfObj=modifyRecordMultiRow(apptConfMstVo,hisDAO_p,uservo,request);
			
														
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new HisDataAccessException("appConfMstDao-->modifyRecord()" + e);
			}
			finally 
			{
				if (hisDAO_p != null) 
				{
					hisDAO_p.free();
					hisDAO_p = null;
				}
			}
		return apptConfObj;
				
	}
	

	// this is to get the selected record for modification..
		public static ApptConfigMstVO modifyRecordMultiRow(ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo,HttpServletRequest request) 
		{
			//final String strProcName = "{call pkg_appointment_masters.proc_appt_config_modify(?,?,?,?,?)}";
			final String strProcName = "{call pkg_appointment_masters.proc_appt_config_modify_with_new_added_shifts(?,?,?,?,?,?)}";
			final int nProcedureIndex;
			final String strDbErr;
			WebRowSet webRowSet = null;
			
			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);
				
				//HisUtil.replaceNullValueWithEmptyString(apptConfMstVo);
				
				hisDAO_p.setProcInValue(nProcedureIndex, "modeval","1",1);
				hisDAO_p.setProcInValue(nProcedureIndex, "pararefid",apptConfMstVo.getParaRefId(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",apptConfMstVo.getHospCode(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "depunitcode",apptConfMstVo.getDepUnitCode(),4);
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
				hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);
				
 				hisDAO_p.executeProcedureByPosition(nProcedureIndex);
				
				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

				// If Database Error Occurs, No farther processing is required. 
				if (strDbErr != null && !strDbErr.equals("")) {
					System.out.println("db err::"+strDbErr);
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
				apptConfMstVo.setStrMsgString("ApptConfMstDao.modifyRecord() --> "	+ e.getMessage());
				apptConfMstVo.setStrMsgType("1");
			}
		
				try
				{
					webRowSet.beforeFirst();
				
					int previousShiftId=0;
					int previousWeek=0;
					String tempshiftwiseSelectedDays="";
					Map<Integer,ShiftDayVO> mp= new HashMap<Integer ,ShiftDayVO>();
					ShiftDayVO objShiftDayVO=null;
					boolean flag=true;
					String[] splittedTimeValues;
					String[] splittedEndTimeValues;
					String newStartTime="";
					String newEndTime="";
					
					// make a map for multirow...
					int sq=1;
					while(webRowSet.next())
					{
						
						objShiftDayVO= new ShiftDayVO();
						objShiftDayVO.setShiftId(webRowSet.getString(1));
						objShiftDayVO.setShiftName(webRowSet.getString(2));
						objShiftDayVO.setShiftIdHidden(webRowSet.getString(1));
						
						newStartTime="";
						newEndTime="";
						
						splittedTimeValues=webRowSet.getString(3).replace(":","#").split("#");
						newStartTime=splittedTimeValues[0]+":"+splittedTimeValues[1];
						objShiftDayVO.setStartTime(newStartTime);
						splittedEndTimeValues=webRowSet.getString(4).replace(":","#").split("#");
						newEndTime=splittedEndTimeValues[0]+":"+splittedEndTimeValues[1];
						objShiftDayVO.setEndTime(newEndTime);
						objShiftDayVO.setWeekOfMonth(webRowSet.getInt(5) +"");						
						objShiftDayVO.setShiftwiseSelectedDays(webRowSet.getString(6));
						String dayStr=webRowSet.getString(7);
						dayStr=dayStr.replace("^", " ");
						//System.out.println("dayStr---" + dayStr);
						objShiftDayVO.setDaystr(dayStr);						
						objShiftDayVO.setCurrentDateAppt(webRowSet.getString(8) );
						objShiftDayVO.setPriorAppt(webRowSet.getString(9) );
						objShiftDayVO.setPortal(webRowSet.getString(10) );
						objShiftDayVO.setOverBook(webRowSet.getString(11) );
						objShiftDayVO.setIsVipSlotAllowed(webRowSet.getString(12));
						objShiftDayVO.setOpdApptSlots(webRowSet.getString(13));
						objShiftDayVO.setIpdApptSlots(webRowSet.getString(14));
						
						mp.put(sq++ , objShiftDayVO);
											
					}
					request.getSession().setAttribute("multiRowMap", mp);					
					
															
				}
				catch (Exception e)
				{
					e.printStackTrace();
					throw new HisDataAccessException("ApptConfMstDao-->modifyRecord()" + e);
				}
				finally 
				{
					if (hisDAO_p != null) 
					{
						hisDAO_p.free();
						hisDAO_p = null;
					}
				}
			return apptConfMstVo;
			
		}	
			
	
		//update table hapt_actual_para_map_mst
		public static Boolean modifyConfigDetails(ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo)
		{
			
			WebRowSet wb=null;
			Boolean flag=true;
			String query=null;
			int qryIndex=0;   
			hisDAO_p=new HisDAO("Appointment Config","HAPT_ACTUAL_PARA_MAP_MST.UPDATE_RECORD");
			
			try
			{
				query=null;
				qryIndex=0;
				query=qryHandler_master.getMasterQuery("HAPT_ACTUAL_PARA_MAP_MST.UPDATE_RECORD");
				qryIndex= hisDAO_p.setQuery(query);
				
				hisDAO_p.setQryValue(qryIndex, 1, apptConfMstVo.getIsApptTransferable());
				hisDAO_p.setQryValue(qryIndex, 2, apptConfMstVo.getAcceptTransferedAppt());
				hisDAO_p.setQryValue(qryIndex, 3, apptConfMstVo.getIsQuotaShiftWise());
				hisDAO_p.setQryValue(qryIndex, 4, apptConfMstVo.getApptDurationValue());
				hisDAO_p.setQryValue(qryIndex, 5, uservo.getSeatId());
				hisDAO_p.setQryValue(qryIndex, 6, apptConfMstVo.getApptDurationUnit());
				hisDAO_p.setQryValue(qryIndex, 7, apptConfMstVo.getPriorApptDays());
				hisDAO_p.setQryValue(qryIndex, 8, apptConfMstVo.getApptDurationUnitTime());
				hisDAO_p.setQryValue(qryIndex, 9, apptConfMstVo.getParaRefId());
				hisDAO_p.setQryValue(qryIndex,10, uservo.getHospitalCode());
				hisDAO_p.execute(qryIndex,0);
				synchronized (hisDAO_p) 
				{
					hisDAO_p.fire();
				}
				
				System.out.println("successfully updated table hapt_actual_para_map_mst...");
				flag=modifyConfigDetails1(apptConfMstVo,hisDAO_p,uservo);
			}
			
			catch(Exception e)
			{
				flag=false;
				e.printStackTrace();
			}
			
			return flag;
		}
		
		// delete previous data from this table and then insert new values in this table..
		//delete from table hapt_para_schedule_mst
				public static Boolean modifyConfigDetails1(ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo)
				{
					
					WebRowSet wb=null;
					Boolean flag=true;
					String query=null;
					int qryIndex=0;   
					hisDAO_p=new HisDAO("Appointment Config","HAPT_PARA_SCHEDULE_MST.DELETE_RECORD");
					
					try
					{
						query=null;
						qryIndex=0;
						query=qryHandler_master.getMasterQuery("HAPT_PARA_SCHEDULE_MST.DELETE_RECORD");
						qryIndex= hisDAO_p.setQuery(query);
						hisDAO_p.setQryValue(qryIndex, 1, apptConfMstVo.getParaRefId());
						hisDAO_p.setQryValue(qryIndex, 2, uservo.getHospitalCode());
						hisDAO_p.execute(qryIndex,0);
							
						synchronized (hisDAO_p) 
						{
							hisDAO_p.fire();
						}
						
						System.out.println("successfully deleted table hapt_para_schedule_mst...");
						flag=modifyConfigDetails2(apptConfMstVo,hisDAO_p,uservo);
					}
					
					catch(Exception e)
					{
						flag=false;
						e.printStackTrace();
					}
					
					return flag;
				}
				
				// delete previous data from this table and then insert new values in this table..
				//delete from table HAPT_PARA_APPT_TYPE_MST
						public static Boolean modifyConfigDetails2(ApptConfigMstVO apptConfMstVo,HisDAO hisDAO_p,UserVO uservo)
						{
							
							WebRowSet wb=null;
							Boolean flag=true;
							String query=null;
							int qryIndex=0;   
							hisDAO_p=new HisDAO("Appointment Config","HAPT_PARA_APPT_TYPE_MST.DELETE_RECORD");
							
							try
							{
								query=null;
								qryIndex=0;
								query=qryHandler_master.getMasterQuery("HAPT_PARA_APPT_TYPE_MST.DELETE_RECORD");
								qryIndex= hisDAO_p.setQuery(query);
								
								String tempArrApptTypeId[] =apptConfMstVo.getSelApptTypesValues().split("@@");
								String tempArrApptTypeText[] =apptConfMstVo.getSelApptTypesText().split("@@");
								
								query=null;
								qryIndex=0;
								query=qryHandler_master.getMasterQuery("HAPT_PARA_APPT_TYPE_MST.DELETE_RECORD");
								qryIndex= hisDAO_p.setQuery(query);
								hisDAO_p.setQryValue(qryIndex, 1, apptConfMstVo.getParaRefId());
								hisDAO_p.setQryValue(qryIndex, 2, uservo.getHospitalCode());
								
								hisDAO_p.execute(qryIndex,0);
								synchronized (hisDAO_p) 
								{
									hisDAO_p.fire();
								}
								
								System.out.println("successfully deleted table HAPT_PARA_APPT_TYPE_MST...");
								
								// insert values once previous data is deleted..
								// this func internally calls saveConfigDetails3()..
								flag=saveConfigDetails2(apptConfMstVo.getParaRefId(), apptConfMstVo, hisDAO_p, uservo);
							}
							
							catch(Exception e)
							{
								flag=false;
								e.printStackTrace();
							}
							
							return flag;
						}


						public static String getOPDRosterSchedule(String finalParaId, HisDAO hisDAO_p, UserVO uservo) {
							String strResult="";
							WebRowSet wb=null;
							String query=null;
							int qryIndex=0;   
							hisDAO_p=new HisDAO("Appointment Config","HOPT_DEPT_UNIT_ROSTER_MST.GETROSTERDTL");
							
							try
							{
								query=qryHandler_master.getMasterQuery("HOPT_DEPT_UNIT_ROSTER_MST.GETROSTERDTL");
								System.out.println(query);
								System.out.println("finalParaId--" + finalParaId );
								String deptUnitCode=finalParaId;
								qryIndex= hisDAO_p.setQuery(query);
								hisDAO_p.setQryValue(qryIndex, 1, deptUnitCode);
								hisDAO_p.setQryValue(qryIndex, 2, uservo.getHospitalCode());
								wb=hisDAO_p.executeQry(qryIndex);
								int i=0;
								while(wb.next()){
									strResult+="<div class='div-table-row listData multirow' id='TR_SHIFT_NEW_'+"+ i+">";
									strResult+="<div class='div-table-col' style='width: 10%;'>";
									strResult+="<input type='hidden' name='shiftId' id='SHIFTID_"+i+"' value='"+wb.getInt(1)+"' >";
									strResult+=wb.getString(2);		 
									strResult+="</div>";
									strResult+="<div class='div-table-col' style='width: 10%;'>";
									strResult+=wb.getString(4)+" - " +wb.getString(5);
									strResult+="<input type='hidden' name='startTime'  value='"+wb.getString(4)+"' >";
									strResult+="<input type='hidden' name='endTime'  value='"+wb.getString(5)+"' >";
									strResult+="</div>";
									strResult+="<div class='div-table-col' style='width: 10%;' >";
									strResult+="<input type='hidden' name='weekOfMonth'  value='"+wb.getString(3)+"' >";
									strResult+=wb.getString(3);
									strResult+="</div>";
									strResult+="<div class='div-table-col' style='width: 15%;'>";
									if(wb.getString(3)!=null){
										String strArrDayNo[]=wb.getString(6).replace("^", "#").split("#");
										String strArrDay[]=wb.getString(7).replace("^", "#").split("#");
										String displayString="";
										for(int j=1;j<=7;j++){
											String checked="";
											for (int k=0;k<strArrDayNo.length;k++){
											 if(Integer.parseInt(strArrDayNo[k])==j){
												 displayString+=strArrDay[k] + " ";	 
												 checked="checked";
												 break;
											 }
											}
											strResult+="<input type='checkbox' name='chk_"+i+"' "+checked+" style='display:none;'  value='"+j+"' />";
										}
										strResult+="<input type='hidden' name='shiftwiseSelectedDays'  id='SHIFTWISESELECTEDDAYS_"+i+"'>";
										strResult+=displayString;
									}
									strResult+="</div>";
									strResult+="<div class='div-table-col' style='width: 9%;'>";
									strResult+="<input type='text' name='currentDateAppt' size='3' maxlength='3' style='min-width:4px;' onkeypress='return isNumberKey(event)' />";
									strResult+="</div>";
									strResult+="<div class='div-table-col' style='width: 8%;'>";
									strResult+="<input type='text' name='priorAppt' size='3' maxlength='3' style='min-width:4px;' onkeypress='return isNumberKey(event)' />";
									strResult+="</div>";
									/*strResult+="<div class='div-table-col' style='width: 8%;'>";
									strResult+="<input type='text' name='overBook' size='4' maxlength='4' style='min-width:4px;' onkeypress='return isNumberKey(event)' />";
									strResult+="</div>";*/
									strResult+="<div class='div-table-col' style='width: 8%;'>";
									strResult+="<input type='text' name='portal' size='3' maxlength='3' style='min-width:4px;' onkeypress='return isNumberKey(event)' />";											
									strResult+="</div>";
									
									strResult+="<div class='div-table-col' style='width: 8%;'>";
									strResult+="<input type='text' name='opdApptSlots' size='3' maxlength='3' style='min-width:4px;' />";											
									strResult+="</div>";
									strResult+="<div class='div-table-col' style='width: 8%;'>";
									strResult+="<input type='text' name='ipdApptSlots' size='3' maxlength='3' style='min-width:4px;' />";											
									strResult+="</div>";
									strResult+="<div class='div-table-col' style='width: 8%;'>";
									strResult+="<input type='text' name='overBook' size='3' maxlength='3' style='min-width:4px;' onkeypress='return isNumberKey(event)' />";
									strResult+="</div>";
									
									strResult+="<div class='div-table-col ' style='width: 10%;display:none;'>";
									strResult+="<input type='radio'   name='rdo_"+i+"' checked  value='1'/>Yes";
									strResult+="<input type='radio'   name='rdo_"+i+"'  value='2'/>No";
									strResult+="<input type='hidden' name='isVipSlotAllowed'  id='SHIFTWISESELECTEDRADIO_"+i+"' />";
									
									strResult+="</div>";
									strResult+="</div>";
									i++;
									
									
								}
								
							}
							catch(Exception e)
							{
								strResult=null;
								e.printStackTrace();
							}
							
							return strResult;
						}


		
	
}
	
