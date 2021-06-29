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
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import vo.appointment.NewAppointmentVO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.PatientVO;
import appointment.config.AppointmentConfig;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
import appointment.transactions.controller.actionsupport.RescheduleCancelAppointmentSUP;
import appointment.transactions.controller.data.NewAppointmentDATA;
import appointment.transactions.controller.data.RescheduleCancelAppointmentDATA;

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
				objCancelAppointmentVO= RescheduleCancelAppointmentDATA.cancelAppointment(objCancelAppointmentVO,userVO);
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
