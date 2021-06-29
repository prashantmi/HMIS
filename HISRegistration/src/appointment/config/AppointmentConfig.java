package appointment.config;

                                     
/**
 * AppointmentConfig is an interface that defines hard-coded values that are
 * used for development of BO and DAO.
 * @author AHIS 
 */
public interface AppointmentConfig {

	String HISREG_CONTEXTPATH="/HISRegistration";
	String HIS_CONTEXTPATH="/HIS";
	
	String LSTAPPOINTMENT = "LSTAPPOINTMENT";
	String QUERY_FILE_FOR_TRANSACTIONDAO = "appointment.Appointment_qry_transaction";
	String LSTPARAMETER =	"LSTPARAMETER";
	String HOSPITAL_CODE	=	"#HOSPITAL_CODE#";
	String USER_ID	=	"#USER_ID#";
	String APT_ID =	"#APT_ID#";
	String ACTUALPARAMETERID = "ACTUALPARAMETERID";
	/*Modify Date				: 27th Nov'14
	  Reason	(CR/PRS)		: Bug Id 7594
	  Modify By                 : Sheeldarshi */

	String ACTUALPARAMETER="#ACTUAL_PARAMETER#";
	//End:Sheeldarshi
	String OBJAPPOINTMENTPARAMETERVO = "OBJAPPOINTMENTPARAMETERVO";
	String LSTGENDER = "LSTGENDER";
	String LSTAGETYPE = "LSTAGETYPE";
	
	
	String APPOINTMENT_STATUS_NEW	= "1";
	String APPOINTMENT_STATUS_RESCHEDULED="2";
	String APPOINTMENT_STATUS_CONFIRMED="3";
	String APPOINTMENT_STATUS_CANCELLED="0";
	
	String SLOT_TYPE_PRIOR_APPOINTMENT_FROM_APPT_MODULE="1";
	String SLOT_TYPE_PRIOR_APPOINTMENT_FROM_OTHER_PROCESS="2";
	String SLOT_TYPE_PRIOR_APPOINTMENT_FROM_PORTAL ="3";
	String SLOT_TYPE_VIP="4";
	String SLOT_TYPE_OVERBOOK="5";
	String SLOT_TYPE_CURRENT_DATE_APPOINTMENT="6";
	
	String APPPOINTMENT_MODE_IN_HOSPITAL="11";
	String APPPOINTMENT_MODE_BY_PHONE="12";
	String APPPOINTMENT_MODE_BY_PORTAL="13";
	String LSTAPPOINTMENTMODE = "LSTAPPOINTMENTMODE";
	String MPAPPOINTMENTLISTING = "MPAPPOINTMENTLISTING";
	String OBJAPPOINTMENT = "OBJAPPOINTMENT";
	String APPOINTMENT_ID_FOR_OPD = "1";

	String CONTEXTMAPACTUALPARAREFID="CONTEXTMAPACTUALPARAREFID";
	String APPOINTMENT_VO_LIST="appointmentVoList";
	String HOLDED_SLOT_TIME="holdedSlotTime";
	String SHIFT_SLOT_WEBROWSET="collectiveShiftSlotWRs";
	
	String SHIFT_CANCELLED="0";					//0 - SHIFT CANCELLED
	String SHIFT_TIME_SLOT_DISABLED="1";		//1 - TIME SLOT DISABLED
	String SLOT_CANCELLED="10";					//10 - SLOT CANCELLED
	String SLOT_INACTIVE="11";					//11-  SLOT INACTIVE
	String SLOT_OTHER="13";						//13-  OTHER
	
	String MSG_HOSP_NAME = "AIIMS Patna";


}