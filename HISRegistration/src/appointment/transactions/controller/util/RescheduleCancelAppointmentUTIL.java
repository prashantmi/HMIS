package appointment.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.SMSSender.SMSHttpPostClient;
import hisglobal.utility.SMSSender.config.SMSConfig;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisRenewalRequiredException;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.appointment.NewAppointmentVO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.PatientVO;
import appointment.config.AppointmentConfig;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
import appointment.transactions.controller.actionsupport.RescheduleCancelAppointmentSUP;
import appointment.transactions.controller.data.NewAppointmentDATA;
import appointment.transactions.controller.data.RescheduleCancelAppointmentDATA;
//Added by Mukund on 10.10.2016 for alert through sms in case of rescheduling and cancel of appointment






import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RescheduleCancelAppointmentUTIL extends ControllerUTIL{

	public static void getGenderList(HttpServletRequest objRequest) {
		try{
			List lstGender =RescheduleCancelAppointmentDATA.getGenderList();
			objRequest.getSession().setAttribute(AppointmentConfig.LSTGENDER, lstGender);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	public static void getAgeType(HttpServletRequest objRequest,RescheduleCancelAppointmentSUP objRescheduleCancelAppointmentSUP_p )
	{
		List<Entry> lstAgeType = new ArrayList<Entry>();
		Entry objEntry = new Entry((String) "Years", "Yr");
		lstAgeType.add(objEntry);
		objRescheduleCancelAppointmentSUP_p.setPatAgeUnit("Yr");
		
		objEntry = new Entry((String) "Months", "Mth");
		lstAgeType.add(objEntry);
		
		objEntry = new Entry((String) "Weeks", "Wk");
		lstAgeType.add(objEntry);
		
		objEntry = new Entry((String) "Days", "D");
		lstAgeType.add(objEntry);
		objRequest.getSession().setAttribute(AppointmentConfig.LSTAGETYPE, lstAgeType);		
	}
	public static void getAppointmentMode(HttpServletRequest objRequest,RescheduleCancelAppointmentSUP objRescheduleCancelAppointment_p )
	{
		List<Entry> lstAppointmentMode = new ArrayList<Entry>();
		Entry objEntry = new Entry((String) "In Hospital", AppointmentConfig.APPPOINTMENT_MODE_IN_HOSPITAL);
		lstAppointmentMode.add(objEntry);
		objRescheduleCancelAppointment_p.setAppointmentMode(AppointmentConfig.APPPOINTMENT_MODE_IN_HOSPITAL);
		
		objEntry = new Entry((String) "By Phone", AppointmentConfig.APPPOINTMENT_MODE_BY_PHONE);
		lstAppointmentMode.add(objEntry);
		
		objRequest.getSession().setAttribute(AppointmentConfig.LSTAPPOINTMENTMODE, lstAppointmentMode);		
	}
	
public static void setPatientDtlByCrno( RescheduleCancelAppointmentSUP objRescheduleCancelAppointment_p,HttpServletRequest objRequest_p){
		
		System.out.println("RescheduleCancelAppointmentUTIL :: setPatientDtlByCrno()");
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		objPatientVO.setPatCrNo(objRescheduleCancelAppointment_p.getPatCrNo());
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
		try{
			objPatientVO=PatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, visitType);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			objRescheduleCancelAppointment_p.setAfterGo("1");
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   		{
	   			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   		}
	   		status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
		}
		catch(HisDuplicateRecordException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objRescheduleCancelAppointment_p.setErrorMessage(e.getMessage());
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
	   	catch(HisNotAnOPDcaseException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
	}

	public static void getActualParaIdWiseDetail(RescheduleCancelAppointmentSUP objRescheduleCancelAppointmentSUP_p,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		
		String jsonString="";
		int maxdisplayOrder=0;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			jsonString =RescheduleCancelAppointmentDATA.getAppointmentTypeList(objRescheduleCancelAppointmentSUP_p.getActualParameterReferenceId(),userVO);
			jsonString=  "{\"optionHTML\":\"" + jsonString + "\"}";
			System.out.println("json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public static void saveRescheduleCancelAppointment(	RescheduleCancelAppointmentSUP objRescheduleCancelAppointmentSUP_p,HttpServletRequest objRequest) {
		Status status=new Status();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO= new RescheduleCancelAppointmentVO(); 
			HelperMethods.populate(objRescheduleCancelAppointmentVO, objRescheduleCancelAppointmentSUP_p);
			
			if(objRescheduleCancelAppointmentSUP_p.getAppointmentNo()!=null){
				System.out.println("saveRescheduleCancelAppointmentUTIL");
				objRescheduleCancelAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_RESCHEDULED);
				objRescheduleCancelAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_PRIOR_APPOINTMENT_FROM_APPT_MODULE);

				PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				if(objPatientVO!=null)
					HelperMethods.populate(objRescheduleCancelAppointmentVO, objPatientVO);
				objRescheduleCancelAppointmentVO= RescheduleCancelAppointmentDATA.saveRescheduleCancelAppointment(objRescheduleCancelAppointmentVO,userVO);
				
				//By Mukund on 08.03.2017 to get values over RescheduleCancelAppointmentVO
				Map mp = (Map)objRequest.getSession().getAttribute("MAP_PREV_APPT");
				RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO2= new RescheduleCancelAppointmentVO();
				objRescheduleCancelAppointmentVO2 = (RescheduleCancelAppointmentVO)mp.get(objRescheduleCancelAppointmentVO.getAppointmentNo());
				
				//By Mukund on 10.10.2016 for alert through sms in case of rescheduling and cancel of appointment
				String prevDate=objRescheduleCancelAppointmentVO.getPrevApptDate();

				SimpleDateFormat readTimeFrmt = new SimpleDateFormat("HH:mm");
				SimpleDateFormat wrtTimeFrmt=new SimpleDateFormat("hh:mm a");//10:00 AM/PM
				
				String shiftStartTime = objRescheduleCancelAppointmentSUP_p.getShiftST().split("\\,")[0];
				String shiftEndTime = objRescheduleCancelAppointmentSUP_p.getShiftET().split("\\,")[0];
				
				Date time2 = readTimeFrmt.parse(shiftStartTime);
				String finalShiftStartTime = wrtTimeFrmt.format(time2);
				
				time2 = readTimeFrmt.parse(shiftEndTime);
				String finalShiftEndTime = wrtTimeFrmt.format(time2);
				
				//SMS Code 
				SMSConfig objSMSConfig=new SMSConfig();
				String   message  = "";//"Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled from "+prevDate+ " at " +objRescheduleCancelAppointmentVO.getPrevApptTime()+" to "+objRescheduleCancelAppointmentVO.getAppointmentDate()+ " at " +objRescheduleCancelAppointmentVO.getAppointmentTime()+" as per your request.";
				//System.out.println(message);//comment this line after use			
				if(objRescheduleCancelAppointmentVO.getAppointmentForId().equals("1")){//Special Clinic
					message  = "Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled from "+prevDate+ " at " +objRescheduleCancelAppointmentVO.getPrevApptTime()+" to "+objRescheduleCancelAppointmentVO.getAppointmentDate()+ " at " +objRescheduleCancelAppointmentVO.getAppointmentTime()+" for "+objRescheduleCancelAppointmentVO2.getDepartmentUnit()+" as per your request.\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else if(objRescheduleCancelAppointmentVO.getAppointmentForId().equals("2")){//Investigation Lab Test Wise
					//message  = "Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled from "+prevDate+ " at " +objRescheduleCancelAppointmentVO.getPrevApptTime()+" to "+objRescheduleCancelAppointmentVO.getAppointmentDate()+ " at " +objRescheduleCancelAppointmentVO.getAppointmentTime()+" for "+objRescheduleCancelAppointmentVO2.getDepartmentUnit()+" "+objRescheduleCancelAppointmentVO2.getTestProcName()+" as per your request.";
					message  = "Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled between "+finalShiftStartTime+" to "+finalShiftEndTime+" on " +objRescheduleCancelAppointmentVO.getAppointmentDate()+" for "+objRescheduleCancelAppointmentVO2.getDepartmentUnit()+" "+objRescheduleCancelAppointmentVO2.getTestProcName()+" as per your request. Please report atleast 15 min prior to avoid any inconvenience.\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else if(objRescheduleCancelAppointmentVO.getAppointmentForId().equals("3")){//Service Area
					message  = "Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled from "+prevDate+ " at " +objRescheduleCancelAppointmentVO.getPrevApptTime()+" to "+objRescheduleCancelAppointmentVO.getAppointmentDate()+ " at " +objRescheduleCancelAppointmentVO.getAppointmentTime()+" for "+objRescheduleCancelAppointmentVO2.getDepartmentUnit()+" "+objRescheduleCancelAppointmentVO2.getTestProcName()+" as per your request.\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else if(objRescheduleCancelAppointmentVO.getAppointmentForId().equals("4")){//Investigation LabWise
					//message  = "Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled from "+prevDate+ " at " +objRescheduleCancelAppointmentVO.getPrevApptTime()+" to "+objRescheduleCancelAppointmentVO.getAppointmentDate()+ " at " +objRescheduleCancelAppointmentVO.getAppointmentTime()+" for "+objRescheduleCancelAppointmentVO2.getDepartmentUnit()+" as per your request.";
					message  = "Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled between "+finalShiftStartTime+" to "+finalShiftEndTime+" on " +objRescheduleCancelAppointmentVO.getAppointmentDate()+" for "+objRescheduleCancelAppointmentVO2.getDepartmentUnit()+" as per your request. Please report atleast 15 min prior to avoid any inconvenience.\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else{
					message  = "Your appointment with appointment no.:"+objRescheduleCancelAppointmentVO.getAppointmentNo()+" is successfully rescheduled from "+prevDate+ " at " +objRescheduleCancelAppointmentVO.getPrevApptTime()+" to "+objRescheduleCancelAppointmentVO.getAppointmentDate()+ " at " +objRescheduleCancelAppointmentVO.getAppointmentTime()+" for "+objRescheduleCancelAppointmentVO2.getDepartmentUnit()+" as per your request.\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}
				//SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url, objPatientVO.getPatAddMobileNo(),message);
				
				//code from sending message through NIC SMS Gateway
				//SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(objSMSConfig.nic_sms_username, objSMSConfig.nic_sms_password,objSMSConfig.nic_sms_senderId,objSMSConfig.nic_sms_url, objPatientVO.getPatAddMobileNo(),message);
				
				//End:Mukund
				System.out.println("Rescheduled Message: "+ message);
				final String   smsmessage  = message;
				final String patMobileNo=objRescheduleCancelAppointmentVO.getMobileNo();
				
				new Thread( new Runnable() {
			           public void run(){

			        	 SMSHttpPostClient.sendSMS (patMobileNo,smsmessage);

			          return; // to stop the thread
			                          }
			         }).start();
				status.add(Status.DONE,"Appointment Rescheduled Successfully" ,"");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.ERROR,"Unable to Reschedule the Appointment" ,"");
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
	}
	
	public static void cancelAppointment(	RescheduleCancelAppointmentSUP objRescheduleCancelAppointmentSUP_p,HttpServletRequest objRequest) {
		Status status=new Status();
		try						
		{
			System.out.println("CancelAppointment()::UTIL");
			UserVO userVO=getUserVO(objRequest);
			RescheduleCancelAppointmentVO objCancelAppointmentVO= new RescheduleCancelAppointmentVO(); 
			HelperMethods.populate(objCancelAppointmentVO, objRescheduleCancelAppointmentSUP_p);
			
			objCancelAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_CANCELLED);
				objCancelAppointmentVO.setRemarks(objRescheduleCancelAppointmentSUP_p.getRemarks());
				PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				if(objPatientVO!=null)
					HelperMethods.populate(objCancelAppointmentVO, objPatientVO);
				//By Mukund on 08.03.2017 to get values over RescheduleCancelAppointmentVO
				Map mp = (Map)objRequest.getSession().getAttribute("MAP_PREV_APPT");
				RescheduleCancelAppointmentVO objCancelAppointmentVO2= new RescheduleCancelAppointmentVO();
				objCancelAppointmentVO2 = (RescheduleCancelAppointmentVO)mp.get(objCancelAppointmentVO.getAppointmentNo());
				
				//End 08.03.2017
				objCancelAppointmentVO= RescheduleCancelAppointmentDATA.cancelAppointment(objCancelAppointmentVO,userVO);
				//By Mukund on 10.10.2016 for for alert through sms in case of rescheduling and cancel of appointment

				String input_date=objCancelAppointmentVO2.getAppointmentDate();//"28-Feb-2017"
				SimpleDateFormat readFrmt=new SimpleDateFormat("dd-MMM-yyyy");//"EEE, MMM d, ''yy"
				Date dt1=readFrmt.parse(input_date);
				SimpleDateFormat wrtFrmt=new SimpleDateFormat("EEE, MMM d");
				String finalDay=wrtFrmt.format(dt1);
				
				String input_time = objCancelAppointmentVO2.getAppointmentTime();
				SimpleDateFormat readTimeFrmt = new SimpleDateFormat("HH:mm");
				Date time1 = readTimeFrmt.parse(input_time);
				SimpleDateFormat wrtTimeFrmt=new SimpleDateFormat("hh:mm a");
				String finalTime = wrtTimeFrmt.format(time1);
				//String dsrdDt=wrtFrmt2.format(dt1);
				/**
				 * 
				String input_time = objNewAppointmentVO.getAppointmentTime();
				SimpleDateFormat readTimeFrmt = new SimpleDateFormat("HH:mm");
				Date time1 = readTimeFrmt.parse(input_time);
				SimpleDateFormat wrtTimeFrmt=new SimpleDateFormat("hh:mm a");
				String finalTime = wrtTimeFrmt.format(time1);*/
				//SMS Code 
				SMSConfig objSMSConfig=new SMSConfig();
				//String   message  = "Your appointment with appointment no.: "+objCancelAppointmentVO.getAppointmentNo()+"	at deptUnit: "+objCancelAppointmentVO.getAllActualParameterId()+"	on "+finalDay+"	"+finalTime+"	"+" is successfully cancelled as per your request.";
				//String msg ="Your appointment with "+(objPatientVO.getUnitConsultant().equals("")?"-NA-":objPatientVO.getUnitConsultant())+"("+(objPatientVO.getUnit().equals("")?"-N.A-":objPatientVO.getUnit())+") on "+finalDay+" , "+objCancelAppointmentVO.getAppointmentTime()+" is cancelled due to"+objCancelAppointmentVO.getRemarks()+".\n-NIMS" ;
				String message = "";
				if(objCancelAppointmentVO2.getAppointmentForId().equals("1")){//Special Clinic
					message  = "Your appointment with Reference no: "+objCancelAppointmentVO2.getAppointmentNo()+" with "+objCancelAppointmentVO2.getDepartmentUnit()+" on "+finalDay+", "+ finalTime+" is cancelled due to "+objCancelAppointmentVO.getRemarks()+"\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else if(objCancelAppointmentVO2.getAppointmentForId().equals("2")){//Investigation Lab Test Wise
					message  = "Your appointment with Reference no: "+objCancelAppointmentVO2.getAppointmentNo()+" with "+objCancelAppointmentVO2.getDepartmentUnit()+" for "+objCancelAppointmentVO2.getTestProcName()+" on "+finalDay+", "+ finalTime+" is cancelled due to "+objCancelAppointmentVO.getRemarks()+"\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else if(objCancelAppointmentVO2.getAppointmentForId().equals("3")){//Service Area
					message  = "Your appointment with Reference no: "+objCancelAppointmentVO2.getAppointmentNo()+" with "+objCancelAppointmentVO2.getDepartmentUnit()+" for "+objCancelAppointmentVO2.getTestProcName()+" on "+finalDay+", "+ finalTime+" is cancelled due to "+objCancelAppointmentVO.getRemarks()+"\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else if(objCancelAppointmentVO2.getAppointmentForId().equals("4")){//Investigation LabWise
					message  = "Your appointment with Reference no: "+objCancelAppointmentVO2.getAppointmentNo()+" with "+objCancelAppointmentVO2.getDepartmentUnit()+" on "+finalDay+", "+ finalTime+" is cancelled due to "+objCancelAppointmentVO.getRemarks()+"\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}else{
					message  = "Your appointment with Reference no: "+objCancelAppointmentVO2.getAppointmentNo()+" with "+objCancelAppointmentVO2.getDepartmentUnit()+" for "+objCancelAppointmentVO2.getTestProcName()+" on "+finalDay+", "+ finalTime+" is cancelled due to "+objCancelAppointmentVO.getRemarks()+"\n-"+AppointmentConfig.MSG_HOSP_NAME;
				}
				System.out.println(message);
				//System.out.println(msg);	
				//SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url, objPatientVO.getPatAddMobileNo(),message);
				
				//code from sending message through NIC SMS Gateway
				//SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(objSMSConfig.nic_sms_username, objSMSConfig.nic_sms_password,objSMSConfig.nic_sms_senderId,objSMSConfig.nic_sms_url, objPatientVO.getPatAddMobileNo(),message);
				
				final String   smsmessage  = message;
				final String patMobileNo=objCancelAppointmentVO2.getMobileNo();
				
				new Thread( new Runnable() {
			           public void run(){

			        	 SMSHttpPostClient.sendSMS (patMobileNo,smsmessage);

			          return; // to stop the thread
			                          }
			         }).start();
				
				//End:Mukund
				status.add(Status.DONE,"Appointment Cancelled Successfully" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.ERROR,"Unable to Cancel the Appointment" ,"");
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
	}
	
public static void getPreviousAppointmentList( RescheduleCancelAppointmentSUP objRescheduleCancelAppointment_p,HttpServletRequest objRequest_p){
		
		System.out.println("RescheduleCancelAppointmentUTIL :: getPreviousAppointmentList()");
		
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		try{
			RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO= new RescheduleCancelAppointmentVO();
			objRescheduleCancelAppointmentVO.setPatCrNo(objRescheduleCancelAppointment_p.getPatCrNo());
			objRescheduleCancelAppointmentVO.setAppointmentNo(objRescheduleCancelAppointment_p.getAppointmentNo());
			Map mp =RescheduleCancelAppointmentDATA.getPreviousAppointmentList(objRescheduleCancelAppointmentVO, objUserVO);
			objRequest_p.getSession().setAttribute("MAP_PREV_APPT", mp);
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
	}

public static void searchAppointmentNoWise( RescheduleCancelAppointmentSUP objRescheduleCancelAppointment_p,HttpServletRequest objRequest_p){
	
	System.out.println("RescheduleCancelAppointmentUTIL :: setPatientDtlByAppointmentNo()");
	Status status=new Status();
	UserVO objUserVO =getUserVO(objRequest_p);
	PatientVO  objPatientVO=new PatientVO();
	RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO=new RescheduleCancelAppointmentVO();
	objRescheduleCancelAppointmentVO.setAppointmentNo(objRescheduleCancelAppointment_p.getAppointmentNo());
	//String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
	try{
		String aptNo=objRescheduleCancelAppointmentVO.getAppointmentNo();
		Map mp =new HashMap();
		mp=RescheduleCancelAppointmentDATA.searchAppointmentNoWise(objRescheduleCancelAppointmentVO,objUserVO);
		HelperMethods.populate(objPatientVO,objRescheduleCancelAppointmentVO);
		WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
		objRequest_p.getSession().setAttribute("MAP_PREV_APPT", mp);
		objRescheduleCancelAppointment_p.setAfterGo("2");
		status.add(Status.TRANSINPROCESS);
	}
	catch(HisRenewalRequiredException e){
   		//e.printStackTrace();
		System.out.println(e.getMessage());
   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
	}
	catch(HisRecordNotFoundException e){
   		//e.printStackTrace();
		System.out.println(e.getMessage());
//   		if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
//   		{
//   			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
//   		}
   		status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
	}
	catch(HisDuplicateRecordException e){
		//e.printStackTrace();
   		System.out.println(e.getMessage());
   		objRescheduleCancelAppointment_p.setErrorMessage(e.getMessage());
		status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
	}
   	catch(HisNotAnOPDcaseException e){
		//e.printStackTrace();
   		System.out.println(e.getMessage());
		status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
	}
	catch(HisNotAnIPDcaseException e){
		//e.printStackTrace();
		System.out.println(e.getMessage());
		status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
	}
	catch(HisDeadPatientException e){
		//e.printStackTrace();
		System.out.println(e.getMessage());
		status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
	}
	catch(HisApplicationExecutionException e){
		e.printStackTrace();
		status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
	}
	catch(HisDataAccessException e){
		e.printStackTrace();
		status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
	}
	catch(Exception e){
		e.printStackTrace();
		status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
	}
	finally{
		WebUTIL.setStatus(objRequest_p, status);
	}
}




}
