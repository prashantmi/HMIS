/*
  * Copyright ©.
 */
package appointment.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import appointment.config.AppointmentConfig;
import vo.appointment.NewAppointmentVO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.AddressVO;
import vo.registration.PatientVO;

public class AppointmentDAO extends DataAccessObject
{

	Logger log;

	public AppointmentDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}

	  public NewAppointmentVO saveNewAppointment(NewAppointmentVO _objNewAppointmentVO, UserVO _userVO){
		/*  PROCEDURE proc_new_appt_save(appointmentforid character varying, hospitalcode character varying, crno character varying, episodecode character varying, 
					firstname character varying, middlename character varying, lastname character varying, fathername character varying, gendercode character varying, 
					patdob character varying, appointmentdate character varying, emailid character varying, mobileno character varying, actualparavalue1 character varying, 
					actualparavalue2 character varying, actualparavalue3 character varying, actualparavalue4 character varying, actualparavalue5 character varying, 
					actualparavalue6 character varying, actualparavalue7 character varying, seatid character varying, appointmenttime character varying, appointmentstatus 
					character varying, slottype character varying, remarks character varying, appointmenttypeid character varying, modulespecificcode character varying, 
					appointmentmode character varying, modulespecifickeyname character varying, patientage character varying, spousename character varying, 
					OUT err character varying, OUT appointmentno character varying, OUT appointmentqueueno character varying)
		 */
		  
		  	System.out.println("AppointmentQueueNo="+ _objNewAppointmentVO.getAppointmentQueueNo());
			System.out.println("apt no="+ _objNewAppointmentVO.getAppointmentNo());
			System.out.println("appointmentmode="+  _objNewAppointmentVO.getAppointmentMode());
			System.out.println("slottype="+ _objNewAppointmentVO.getSlotType());
			System.out.println("seatid="+ _userVO.getSeatId() );
			System.out.println("appointmentDate="+ _objNewAppointmentVO.getAppointmentDate());
			System.out.println("hospitalcode="+  _userVO.getHospitalCode());
			System.out.println("appointmentstatus="+ _objNewAppointmentVO.getAppointmentStatus());
			System.out.println("appointmenttime="+ _objNewAppointmentVO.getAppointmentTime());

		  	HisDAO daoObj = null;
			String strProcName = "{call pkg_appointment_Transaction.proc_new_appt_save(?,?,?,?,?,?::character varying,?::character varying,?::character varying,?::character varying,?,?,?::character varying,?,?,?,?,?,?,?,?,?,?,?,?,?::character varying,?,?::character varying,?,?::character varying,?,?::character varying,?,?,?)}";
			
			int nProcIndex = 0;
			int sq=0;
			String strErr = "";
			try 
			{
			
			HelperMethods.setNullToEmpty(_objNewAppointmentVO);	
			if(_objNewAppointmentVO.getEpisodeCode()==null || _objNewAppointmentVO.getEpisodeCode().equals(""))
				_objNewAppointmentVO.setEpisodeCode("0");
			
			if(_objNewAppointmentVO.getPatGenderCode().equals("-1"))
				_objNewAppointmentVO.setPatGenderCode("");
			
		
			daoObj = new HisDAO("Appointment","AppointmentDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "appointmentForId", _objNewAppointmentVO.getAppointmentForId() ,++sq);
			daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(),++sq);
			daoObj.setProcInValue(nProcIndex, "crNo", _objNewAppointmentVO.getPatCrNo() ,++sq);
			daoObj.setProcInValue(nProcIndex, "episodeCode", _objNewAppointmentVO.getEpisodeCode()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "firstName", _objNewAppointmentVO.getPatFirstName()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "middleName", _objNewAppointmentVO.getPatMiddleName()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "lastName", _objNewAppointmentVO.getPatLastName()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "fatherName", _objNewAppointmentVO.getPatGuardianName()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "genderCode", _objNewAppointmentVO.getPatGenderCode()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "patdob", _objNewAppointmentVO.getPatDOB()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentDate", _objNewAppointmentVO.getAppointmentDate()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "emailId", _objNewAppointmentVO.getEmailId()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "mobileNo", _objNewAppointmentVO.getMobileNo()   ,++sq);
			for(int i=0;i<_objNewAppointmentVO.getActualParameterId().length;i++){
				daoObj.setProcInValue(nProcIndex, "actualparavalue"+ (i+1), _objNewAppointmentVO.getActualParameterId()[i] ,++sq);	
			}
			
			daoObj.setProcInValue(nProcIndex, "seatid", _userVO.getSeatId()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmenttime", _objNewAppointmentVO.getAppointmentTime()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentstatus", _objNewAppointmentVO.getAppointmentStatus()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "slottype", _objNewAppointmentVO.getSlotType()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "remarks", _objNewAppointmentVO.getRemarks() ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmenttypeid", _objNewAppointmentVO.getAppointmentTypeId()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentmode", _objNewAppointmentVO.getAppointmentMode()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "modulespecificcode", _objNewAppointmentVO.getModuleSpecificCode()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "modulespecifickeyname", _objNewAppointmentVO.getModuleSpecificKeyName()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "patientage", _objNewAppointmentVO.getPatAge() +" "+ _objNewAppointmentVO.getPatAgeUnit()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "spousename", _objNewAppointmentVO.getPatHusbandName()   ,++sq);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
			daoObj.setProcOutValue(nProcIndex, "appointmentno", 1,++sq);
			daoObj.setProcOutValue(nProcIndex, "appointmentqueueno", 1,++sq);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			_objNewAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
			_objNewAppointmentVO.setAppointmentNo(daoObj.getString (nProcIndex, "appointmentno"));
			_objNewAppointmentVO.setAppointmentQueueNo(daoObj.getString(nProcIndex, "appointmentqueueno"));
			System.out.println("apt no--"+ _objNewAppointmentVO.getAppointmentNo());
			System.out.println("AppointmentQueueNo --"+ _objNewAppointmentVO.getAppointmentQueueNo());
			System.out.println("saveNEWAppointmentDAO");
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
			}
			finally 
			{
				if (daoObj != null) 
				{
					daoObj.free();
					daoObj = null;
				}
			}
	    	return _objNewAppointmentVO;
	    } 
	  
	  public NewAppointmentVO ConfirmAppointment(NewAppointmentVO _objNewAppointmentVO, UserVO _userVO){
		  //PROCEDURE proc_Confirm_appt_save(appointmentno character varying, hospitalcode character varying, seatId character varying, appointmentstatus character varying,OUT err character varying) IS
		  
		  	HisDAO daoObj = null;
			String strProcName = "{call pkg_appointment_Transaction.proc_confirm_appt_save(?::character varying,?::character varying,?::character varying,?::character varying,?)}";
			int nProcIndex = 0;
			int sq=0;
			String strErr = "";
			System.out.println("Confirm Appointment DAO");
			System.out.println("AppointmentQueueNo="+ _objNewAppointmentVO.getAppointmentQueueNo());
			System.out.println("apt no="+ _objNewAppointmentVO.getAppointmentNo());
			System.out.println("appointmentmode="+  _objNewAppointmentVO.getAppointmentMode());
			System.out.println("slottype="+ _objNewAppointmentVO.getSlotType());
			System.out.println("seatid="+ _userVO.getSeatId() );
			System.out.println("appointmentDate="+ _objNewAppointmentVO.getAppointmentDate());
			System.out.println("hospitalcode="+  _userVO.getHospitalCode());
			System.out.println("appointmentstatus="+ _objNewAppointmentVO.getAppointmentStatus());
			System.out.println("appointmenttime="+ _objNewAppointmentVO.getAppointmentTime());
			try 
			{
			HelperMethods.setNullToEmpty(_objNewAppointmentVO);	
			daoObj = new HisDAO("Appointment","AppointmentDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "appointmentno", _objNewAppointmentVO.getAppointmentNo()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(),++sq);
			daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId() ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentstatus", AppointmentConfig.APPOINTMENT_STATUS_CONFIRMED  ,++sq);
			daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
			daoObj.executeProcedureByPosition(nProcIndex);
			_objNewAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
			
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
			}
			finally 
			{
				if (daoObj != null) 
				{
					daoObj.free();
					daoObj = null;
				}
			}
	    	return _objNewAppointmentVO;
	    } 
	  
	  public NewAppointmentVO ConfirmAppointment(HisDAO daoObj,NewAppointmentVO _objNewAppointmentVO, UserVO _userVO){
		  //PROCEDURE proc_Confirm_appt_save(appointmentno character varying, hospitalcode character varying, seatId character varying, appointmentstatus character varying,OUT err character varying) IS
		  
			String strProcName = "{call pkg_appointment_Transaction.proc_confirm_appt_save(?::character varying,?::character varying,?::character varying,?::character varying,?)}";
			int nProcIndex = 0;
			int sq=0;
			String strErr = "";
			System.out.println("Confirm Appointment DAO in batch");
			System.out.println("apt no="+ _objNewAppointmentVO.getAppointmentNo());
			System.out.println("seatid="+ _userVO.getSeatId() );
			System.out.println("hospitalcode="+  _userVO.getHospitalCode());
			try 
			{
				HelperMethods.setNullToEmpty(_objNewAppointmentVO);	
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "appointmentno", _objNewAppointmentVO.getAppointmentNo()  ,++sq);
				daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(),++sq);
				daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId() ,++sq);
				daoObj.setProcInValue(nProcIndex, "appointmentstatus", AppointmentConfig.APPOINTMENT_STATUS_CONFIRMED  ,++sq);
				daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
				daoObj.execute(nProcIndex,1);
				//_objNewAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
			
			} catch (Exception e) {
				throw new HisApplicationExecutionException("AppointmentDAO.ConfirmAppointment :"+ e + strErr);
			}
			
	    	return _objNewAppointmentVO;
	    } 
	    
	   
	
  public RescheduleCancelAppointmentVO cancelAppointment(RescheduleCancelAppointmentVO _objRescheduleCancelAppointmentVO, UserVO _userVO){
	 // PROCEDURE proc_cancel_appt(appointmentno numeric,hospitalcode character varying, seatId character varying, appointmentstatus character varying, remarks character varying, OUT err character varying);
		  
		  	HisDAO daoObj = null;
			String strProcName = "{call pkg_appointment_Transaction.proc_cancel_appt(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?)}";
			int nProcIndex = 0;
			int sq=0;
			String strErr = "";
			try 
			{
			HelperMethods.setNullToEmpty(_objRescheduleCancelAppointmentVO);	
			daoObj = new HisDAO("Appointment","AppointmentDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "appointmentNo" ,_objRescheduleCancelAppointmentVO.getAppointmentNo()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(),++sq);
			daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId() ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentstatus", AppointmentConfig.APPOINTMENT_STATUS_CANCELLED  ,++sq);
			daoObj.setProcInValue(nProcIndex, "remarks", _objRescheduleCancelAppointmentVO.getRemarks() ,++sq);
			daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
			
			System.out.println( "DAOremark::"+ _objRescheduleCancelAppointmentVO.getRemarks() );	
			
			daoObj.executeProcedureByPosition(nProcIndex);
			_objRescheduleCancelAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
			
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
			}
			finally 
			{
				if (daoObj != null) 
				{
				
					daoObj.free();
					daoObj = null;
				}
			}
	    	return _objRescheduleCancelAppointmentVO;
	    } 
	  
	  public RescheduleCancelAppointmentVO saveRescheduleCancelAppointment(RescheduleCancelAppointmentVO _objRescheduleCancelAppointmentVO, UserVO _userVO)
	  {
		/*  PROCEDURE proc_reschedule_appt_save(hospitalcode character varying, appointmentno numeric, appointmentdate character varying, seatid character varying, 
					appointmenttime character varying, appointmentstatus character varying, slottype character varying, remarks character varying,appointmentmode character varying, 
					OUT err character varying*/

		    HisDAO daoObj = null;
			String strProcName = "{call pkg_appointment_Transaction.proc_reschedule_appt_save(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?,?)}";
			int nProcIndex = 0;
			int sq=0;
			String strErr = "";
			System.out.println("DAO::AppointmentQueueNo="+ _objRescheduleCancelAppointmentVO.getAppointmentQueueNo());
			System.out.println("apt no="+ _objRescheduleCancelAppointmentVO.getAppointmentNo());
			System.out.println("appointmentmode="+  _objRescheduleCancelAppointmentVO.getAppointmentMode());
			System.out.println("Remarks="+ _objRescheduleCancelAppointmentVO.getResheduleRemarks());
			System.out.println("slottype="+ _objRescheduleCancelAppointmentVO.getSlotType());
			System.out.println("seatid="+ _userVO.getSeatId() );
			System.out.println("appointmentDate="+ _objRescheduleCancelAppointmentVO.getAppointmentDate());
			System.out.println("hospitalcode="+  _userVO.getHospitalCode());
			System.out.println("appointmentstatus="+  _objRescheduleCancelAppointmentVO.getAppointmentStatus());
			System.out.println("appointmenttime="+ _objRescheduleCancelAppointmentVO.getAppointmentTime());
			System.out.println("email id="+ _objRescheduleCancelAppointmentVO.getEmailId());
			System.out.println("mobile no="+ _objRescheduleCancelAppointmentVO.getMobileNo());
			
			try 
			{
			HelperMethods.setNullToEmpty(_objRescheduleCancelAppointmentVO);	
        
			daoObj = new HisDAO("Appointment","AppointmentDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(),++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentno", _objRescheduleCancelAppointmentVO.getAppointmentNo(),++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentDate", _objRescheduleCancelAppointmentVO.getAppointmentDate()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "seatid", _userVO.getSeatId()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmenttime", _objRescheduleCancelAppointmentVO.getAppointmentTime()   ,++sq);
			
			daoObj.setProcInValue(nProcIndex, "appointmentstatus", _objRescheduleCancelAppointmentVO.getAppointmentStatus()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "slottype", _objRescheduleCancelAppointmentVO.getSlotType()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "slotST", _objRescheduleCancelAppointmentVO.getSlotST()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "slotET", _objRescheduleCancelAppointmentVO.getSlotET()   ,++sq);

			daoObj.setProcInValue(nProcIndex, "shiftId", _objRescheduleCancelAppointmentVO.getShiftId()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "remarks", _objRescheduleCancelAppointmentVO.getResheduleRemarks() ,++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentmode", _objRescheduleCancelAppointmentVO.getAppointmentMode()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "emailId", _objRescheduleCancelAppointmentVO.getEmailId()   ,++sq);
			daoObj.setProcInValue(nProcIndex, "mobileNo", _objRescheduleCancelAppointmentVO.getMobileNo()   ,++sq);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
			daoObj.setProcOutValue(nProcIndex, "appointmentqueueno", 1,++sq);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			_objRescheduleCancelAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
			_objRescheduleCancelAppointmentVO.setAppointmentQueueNo(daoObj.getString(nProcIndex, "appointmentqueueno"));
			System.out.println("AFTER CALL:::::DAO::AppointmentQueueNo="+ _objRescheduleCancelAppointmentVO.getAppointmentQueueNo());
			System.out.println("apt no="+ _objRescheduleCancelAppointmentVO.getAppointmentNo());
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
			}
			finally 
			{
				if (daoObj != null) 
				{
					daoObj.free();
					daoObj = null;
				}
			}
	    	return _objRescheduleCancelAppointmentVO;
	    } 

	  public RescheduleCancelAppointmentVO searchAppointmentNoWise(RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO_p,  UserVO objUserVO_p) {
			
			WebRowSet rs = null;
			HisDAO daoObj = null;
			PatientVO objPatientVO_p = null;
			System.out.println("Appointment DAO::SearchAppointmentNoWise");
			String strProcName = "{call pkg_appointment_Transaction.proc_appointmentNo_Wise(?,?,?,?,?)}";//changed fr patient vist by deepika
			int nProcIndex = 0;
			int sq=0;
			String strErr="",strMode = "0";
			AddressVO _addressVO= new AddressVO();
			try 
			{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			strMode="1";

			daoObj.setProcInValue(nProcIndex, "p_mode", strMode,++sq);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),++sq);
			daoObj.setProcInValue(nProcIndex, "p_Appointmentno", objRescheduleCancelAppointmentVO_p.getAppointmentNo(),++sq);
			//daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,++sq);
			daoObj.setProcOutValue(nProcIndex, "resultset", 1,++sq);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");

			strErr = daoObj.getString(nProcIndex, "err");
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}

			try {
				if (!rs.next()) {
					throw new HisRecordNotFoundException("Patient Details Not Found");
				} else {
					rs.beforeFirst();
					HelperMethods.populateVOfrmRS(objRescheduleCancelAppointmentVO_p, rs);
					HelperMethods.populate(_addressVO, objRescheduleCancelAppointmentVO_p);
				}
			} catch (Exception e) {
				if (e.getClass() == HisRecordNotFoundException.class) {
					throw new HisRecordNotFoundException(e.getMessage());
				} else
					throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
			}
			finally 
			{
				if (daoObj != null) 
				{
					daoObj.free();
					daoObj = null;
				}
			}
			return objRescheduleCancelAppointmentVO_p;
		}
	  
	  //To Create the New Appointmnet with Slot Time Details Added by Singaravelan on 22-Jan-2015
	  public NewAppointmentVO saveNewAppointmentSlotWise(NewAppointmentVO _objNewAppointmentVO, UserVO _userVO){
						  
			  	HisDAO daoObj = null;
				String strProcName = "{call pkg_appointment_Transaction.proc_new_appt_save_slotwise(?,?,?,?,?,?::character varying,?::character varying,?::character varying,?::character varying,?,?,?::character varying,?,?,?,?,?,?,?,?,?,?,?,?,?::character varying,?,?::character varying,?,?::character varying,?,?::character varying,?,?,?,?,?,?,?)}";
				
				int nProcIndex = 0;
				int sq=0;
				String strErr = "";
				try 
				{
				
				HelperMethods.setNullToEmpty(_objNewAppointmentVO);	
				if(_objNewAppointmentVO.getEpisodeCode()==null || _objNewAppointmentVO.getEpisodeCode().equals(""))
					_objNewAppointmentVO.setEpisodeCode("0");
				
				if(_objNewAppointmentVO.getPatGenderCode().equals("-1"))
					_objNewAppointmentVO.setPatGenderCode("");
				
			
				daoObj = new HisDAO("Appointment","AppointmentDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "appointmentForId", _objNewAppointmentVO.getAppointmentForId() ,++sq);
				daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(),++sq);
				daoObj.setProcInValue(nProcIndex, "crNo", _objNewAppointmentVO.getPatCrNo() ,++sq);
				daoObj.setProcInValue(nProcIndex, "episodeCode", _objNewAppointmentVO.getEpisodeCode()  ,++sq);
				daoObj.setProcInValue(nProcIndex, "firstName", _objNewAppointmentVO.getPatFirstName()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "middleName", _objNewAppointmentVO.getPatMiddleName()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "lastName", _objNewAppointmentVO.getPatLastName()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "fatherName", _objNewAppointmentVO.getPatGuardianName()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "genderCode", _objNewAppointmentVO.getPatGenderCode()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "patdob", _objNewAppointmentVO.getPatDOB()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "appointmentDate", _objNewAppointmentVO.getAppointmentDate()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "emailId", _objNewAppointmentVO.getEmailId()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "mobileNo", _objNewAppointmentVO.getMobileNo()   ,++sq);
				for(int i=0;i<_objNewAppointmentVO.getActualParameterId().length;i++){
					daoObj.setProcInValue(nProcIndex, "actualparavalue"+ (i+1), _objNewAppointmentVO.getActualParameterId()[i] ,++sq);	
				}
				
				daoObj.setProcInValue(nProcIndex, "seatid", _userVO.getSeatId()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "appointmenttime", _objNewAppointmentVO.getAppointmentTime()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "appointmentstatus", _objNewAppointmentVO.getAppointmentStatus()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "slottype", _objNewAppointmentVO.getSlotType()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "remarks", _objNewAppointmentVO.getRemarks() ,++sq);
				daoObj.setProcInValue(nProcIndex, "appointmenttypeid", _objNewAppointmentVO.getAppointmentTypeId()  ,++sq);
				daoObj.setProcInValue(nProcIndex, "appointmentmode", _objNewAppointmentVO.getAppointmentMode()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "modulespecificcode", _objNewAppointmentVO.getModuleSpecificCode()   ,++sq);
				daoObj.setProcInValue(nProcIndex, "modulespecifickeyname", _objNewAppointmentVO.getModuleSpecificKeyName()  ,++sq);
				daoObj.setProcInValue(nProcIndex, "patientage", _objNewAppointmentVO.getPatAge() +" "+ _objNewAppointmentVO.getPatAgeUnit()  ,++sq);
				daoObj.setProcInValue(nProcIndex, "spousename", _objNewAppointmentVO.getPatHusbandName()   ,++sq);
				
				daoObj.setProcInValue(nProcIndex, "shiftId", _objNewAppointmentVO.getShiftId() ,++sq);
				daoObj.setProcInValue(nProcIndex, "slotStartTime", _objNewAppointmentVO.getSlotST() ,++sq);
				daoObj.setProcInValue(nProcIndex, "slotEndTime", _objNewAppointmentVO.getSlotET(),++sq);
			
				if(_objNewAppointmentVO.getActualParameterReferenceId().indexOf("^")>0)
					daoObj.setProcInValue(nProcIndex, "actualParaRefId", _objNewAppointmentVO.getActualParameterReferenceId().replace("^", "#").split("#")[0]  ,++sq);
				else
					daoObj.setProcInValue(nProcIndex, "actualParaRefId", _objNewAppointmentVO.getActualParameterReferenceId()  ,++sq);

				daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
				daoObj.setProcOutValue(nProcIndex, "appointmentno", 1,++sq);
				daoObj.setProcOutValue(nProcIndex, "appointmentqueueno", 1,++sq);
				
				daoObj.executeProcedureByPosition(nProcIndex);
				_objNewAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
				_objNewAppointmentVO.setAppointmentNo(daoObj.getString (nProcIndex, "appointmentno"));
				_objNewAppointmentVO.setAppointmentQueueNo(daoObj.getString(nProcIndex, "appointmentqueueno"));
				System.out.println("apt no--"+ _objNewAppointmentVO.getAppointmentNo());
				System.out.println("AppointmentQueueNo --"+ _objNewAppointmentVO.getAppointmentQueueNo());
				System.out.println("saveNEWAppointmentDAO");
				} catch (Exception e) {
					throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
				}
				finally 
				{
					if (daoObj != null) 
					{
						daoObj.free();
						daoObj = null;
					}
				}
		    	return _objNewAppointmentVO;
		    } 
	  
	  public Map<String,RescheduleCancelAppointmentVO> getAppointmentDtl(																																																																																																												
				UserVO userVO,			RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO) {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			RescheduleCancelAppointmentVO obj= new RescheduleCancelAppointmentVO();
			Map <String, RescheduleCancelAppointmentVO>mp= new HashMap<String, RescheduleCancelAppointmentVO>();

			String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
			String queryKey = "GETAPPOINTMENTDTL";
			
			System.out.println("AppointMentEssential_DAO :: getAppointmentDtl");
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			log.error(query + "\n");
			
			System.out.println("query" + query);
			
			Sequence sq = new Sequence();	
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), objRescheduleCancelAppointmentVO.getAppointmentNo());
			System.out.println("Appointment_dao");
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,populateMAP);
				//SELECT hapnum_apt_no, hrgnum_puk,hrgnum_episode_code, hapstr_pat_first_name, hapstr_pat_middle_name, hapstr_pat_last_name,hapstr_patient_fname, gstr_gender_code, hapstr_email, hapstr_mobile_no,habnum_appt_queue_no, hapstr_slot_time, hapnum_apt_status,hapstr_statusremarks, hapnum_slot_type,hapstr_remarks, hapnum_appt_type_id, hapstr_module_specific_code, hapnum_appt_mode, hapstr_mod_specific_key_name,hapstr_patage, hapstr_spousename,hapdt_appointment_date FROM hapt_appointment_dtl where gnum_isvalid=1 and gnum_hospital_code=101 and hapnum_apt_id=1 and  hapstr_actual_para_id_1=103 and hapstr_actual_para_id_2=10311 and hapstr_actual_para_id_3=0 and hapstr_actual_para_id_4=0 and hapstr_actual_para_id_5=0 and hapstr_actual_para_id_6=0 and hapstr_actual_para_id_7=0 and hapdt_appointment_date=28-Feb-2014::timestamp and hapnum_apt_status not in  (0,3)
				while(rs.next()){
					obj.setAppointmentNo(rs.getLong(1)+"");
					obj.setPatCrNo(rs.getLong(2)+"");
					obj.setEpisodeCode(rs.getInt(3)+"");
					obj.setPatFirstName(rs.getString(4));
					obj.setPatMiddleName(rs.getString(5));
					obj.setPatLastName(rs.getString(6));
					obj.setPatGuardianName(rs.getString(7));
					obj.setPatGenderCode(rs.getString(8));
					obj.setEmailId(rs.getString(9));
					obj.setMobileNo(rs.getLong(10)+"");
					obj.setAppointmentQueueNo(rs.getInt(11)+"");
					obj.setAppointmentTime(rs.getString(12));
					obj.setAppointmentStatus(rs.getInt(13)+"");
					obj.setStatusRemarks(rs.getString(14));
					obj.setSlotType(rs.getInt(15)+"");
					obj.setRemarks(rs.getString(16));
					obj.setAppointmentTypeId(rs.getInt(17)+"");
					obj.setModuleSpecificCode(rs.getString(18));
					obj.setAppointmentMode(rs.getInt(19)+"");
					obj.setModuleSpecificKeyName(rs.getString(20));
					obj.setPatAge(rs.getString(21));
					obj.setPatHusbandName(rs.getString(22));
					obj.setAppointmentDate(rs.getString(23));
					obj.setAllActualParameterId(rs.getString(26)+"^"+rs.getString(27)+"^"+rs.getString(28)+"^"+rs.getString(29)+"^"+rs.getString(30)+"^"+rs.getString(31)+"^"+rs.getString(32));
					mp.put(obj.getAppointmentNo(), obj);		

					
				}
			}
			catch (Exception e)
			{

				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			return mp;

		}
	  
	  public RescheduleCancelAppointmentVO cancelShift(RescheduleCancelAppointmentVO _objRescheduleCancelAppointmentVO, UserVO _userVO,String _pmode){
				  
		  	HisDAO daoObj = null;
			String strProcName = "{call pkg_appointment_Transaction.proc_shift_schedule_change_dtl(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,  ?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?,?)}";
			int nProcIndex = 0;
			int sq=0;
			String strErr = "";
			try 
			{
			HelperMethods.setNullToEmpty(_objRescheduleCancelAppointmentVO);	
			daoObj = new HisDAO("Appointment","AppointmentDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "mode" ,_pmode  ,++sq);
			daoObj.setProcInValue(nProcIndex, "shiftId" ,_objRescheduleCancelAppointmentVO.getShiftId()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "aptDate" ,_objRescheduleCancelAppointmentVO.getAppointmentDate()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "paraRefId" ,_objRescheduleCancelAppointmentVO.getParaRefId()  ,++sq);
			if(_pmode.equals("1"))
				daoObj.setProcInValue(nProcIndex, "status", AppointmentConfig.SHIFT_CANCELLED  ,++sq);
			else if(_pmode.equals("2"))
				daoObj.setProcInValue(nProcIndex, "status", AppointmentConfig.SLOT_CANCELLED  ,++sq);
			
			daoObj.setProcInValue(nProcIndex, "slotST", _objRescheduleCancelAppointmentVO.getSlotST()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "slotET", _objRescheduleCancelAppointmentVO.getSlotET()  ,++sq);

			daoObj.setProcInValue(nProcIndex, "hospCode", _userVO.getHospitalCode(),++sq);
			daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId() ,++sq);
			daoObj.setProcInValue(nProcIndex, "remarks", _objRescheduleCancelAppointmentVO.getRemarks() ,++sq);
			daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
			daoObj.setProcOutValue(nProcIndex, "sno", 1 ,++sq);

			System.out.println( "DAOremark::"+ _objRescheduleCancelAppointmentVO.getRemarks() );	
			
			daoObj.executeProcedureByPosition(nProcIndex);
			_objRescheduleCancelAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
			_objRescheduleCancelAppointmentVO.setsNo(daoObj.getString(nProcIndex, "sno"));

			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
			}
			finally 
			{
				if (daoObj != null) 
				{
				
					daoObj.free();
					daoObj = null;
				}
			}
	    	return _objRescheduleCancelAppointmentVO;
	    } 	
	  
	  
	  public RescheduleCancelAppointmentVO cancelSlot(RescheduleCancelAppointmentVO _objRescheduleCancelAppointmentVO, UserVO _userVO,String _pmode){
		  
		  	HisDAO daoObj = null;
			String strProcName = "{call pkg_appointment_Transaction.proc_slot_change_dtl(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,  ?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?)}";
			int nProcIndex = 0;
			int sq=0;
			String strErr = "";
			try 
			{
			HelperMethods.setNullToEmpty(_objRescheduleCancelAppointmentVO);	
			daoObj = new HisDAO("Appointment","AppointmentDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "mode" ,_pmode  ,++sq);
			daoObj.setProcInValue(nProcIndex, "sNO" ,_objRescheduleCancelAppointmentVO.getsNo()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "shiftId" ,_objRescheduleCancelAppointmentVO.getShiftId()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "aptDate" ,_objRescheduleCancelAppointmentVO.getAppointmentDate()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "paraRefId" ,_objRescheduleCancelAppointmentVO.getParaRefId()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "status", AppointmentConfig.SLOT_CANCELLED  ,++sq);
			
			daoObj.setProcInValue(nProcIndex, "slotST", _objRescheduleCancelAppointmentVO.getSlotST()  ,++sq);
			daoObj.setProcInValue(nProcIndex, "slotET", _objRescheduleCancelAppointmentVO.getSlotET()  ,++sq);

			daoObj.setProcInValue(nProcIndex, "hospCode", _userVO.getHospitalCode(),++sq);
			daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId() ,++sq);
			daoObj.setProcInValue(nProcIndex, "remarks", _objRescheduleCancelAppointmentVO.getRemarks() ,++sq);
			daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
			
			System.out.println( "DAOremark::"+ _objRescheduleCancelAppointmentVO.getRemarks() );	
			
			daoObj.executeProcedureByPosition(nProcIndex);
			_objRescheduleCancelAppointmentVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
			
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
			}
			finally 
			{
				if (daoObj != null) 
				{
				
					daoObj.free();
					daoObj = null;
				}
			}
	    	return _objRescheduleCancelAppointmentVO;
	    } 	
	  
	  
}//end class
