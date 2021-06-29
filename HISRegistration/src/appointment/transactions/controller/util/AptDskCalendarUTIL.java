/**
 * 
 */
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisRenewalRequiredException;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.appointment.AppointmentParameterVO;
import vo.appointment.AptDskCalendarVO;
import vo.appointment.NewAppointmentVO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.PatientVO;
import appointment.config.AppointmentConfig;
import appointment.transactions.controller.actionsupport.AptDskCalendarSUP;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
import appointment.transactions.controller.actionsupport.RescheduleCancelAppointmentSUP;
import appointment.transactions.controller.data.AppointmentTagDATA;
import appointment.transactions.controller.data.AptDskCalendarDATA;
import appointment.transactions.controller.data.NewAppointmentDATA;
import appointment.transactions.controller.data.RescheduleCancelAppointmentDATA;

/**
 * @author singaravelan
 *
 */
public class AptDskCalendarUTIL extends ControllerUTIL{
	
		
	public static void getActualParaIdWiseDetail(String actualParameterReferenceId,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		String jsonString="";
		int maxdisplayOrder=0;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			jsonString =NewAppointmentDATA.getAppointmentTypeList(actualParameterReferenceId,userVO);
			jsonString=  "{\"optionHTML\":\"" + jsonString + "\"}";
			System.out.println("json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public static void getGenderList(HttpServletRequest objRequest) {
		try{
			List lstGender =NewAppointmentDATA.getGenderList();
			objRequest.getSession().setAttribute(AppointmentConfig.LSTGENDER, lstGender);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void getAgeType(HttpServletRequest objRequest,AptDskCalendarSUP objAptDskCalendar_p)
	{
		List<Entry> lstAgeType = new ArrayList<Entry>();
		Entry objEntry = new Entry((String) "Years", "Yr");
		lstAgeType.add(objEntry);
		objAptDskCalendar_p.setPatAgeUnit("Yr");
		
		objEntry = new Entry((String) "Months", "Mth");
		lstAgeType.add(objEntry);
		
		objEntry = new Entry((String) "Weeks", "Wk");
		lstAgeType.add(objEntry);
		
		objEntry = new Entry((String) "Days", "D");
		lstAgeType.add(objEntry);
		objRequest.getSession().setAttribute(AppointmentConfig.LSTAGETYPE, lstAgeType);		
	}
	
	public static void getSlotDurationDetail(String startDate,String endDate,String paraId,String aptForId,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		String jsonString="";
		int maxdisplayOrder=0;
		String actualParaRefIdSlotDuration=null;
		String actualParaRefId,slotDuration=null;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			AptDskCalendarVO _aptDskCalendarVo=new AptDskCalendarVO();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	        _aptDskCalendarVo.setStartDate(sdf.format(new Date(startDate)));
	        _aptDskCalendarVo.setEndDate(sdf.format(new Date(endDate)));

			actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptForId, paraId);//actualParaRefId^slotDuartion
			actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
			slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
			
			jsonString =slotDuration;
			WebUTIL.setAttributeInSession(objRequest, "actualParaRefIdSlotDuration", actualParaRefIdSlotDuration);
			//jsonString ="{\"events\":["+jsonString+"]}";
			System.out.println("getSlotDurationDetail json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	
	public static void getDayWiseAptCountDetail(String startDate,String endDate,String paraId,String aptForId,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		String jsonString="";
		int maxdisplayOrder=0;
		String actualParaRefIdSlotDuration=null;
		String actualParaRefId,slotDuration=null;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			AptDskCalendarVO _aptDskCalendarVo=new AptDskCalendarVO();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	        _aptDskCalendarVo.setStartDate(sdf.format(new Date(startDate)));
	        _aptDskCalendarVo.setEndDate(sdf.format(new Date(endDate)));

	        if(objRequest.getAttribute("actualParaRefIdSlotDuration")!=null)
	        	actualParaRefIdSlotDuration=(String) objRequest.getAttribute("actualParaRefIdSlotDuration");
	        else
	        	actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptForId, paraId);//actualParaRefId^slotDuartion
			
	        actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
			slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
			_aptDskCalendarVo.setParaRefId(actualParaRefId);
			paraId=paraId.replace('^', '#');
	        _aptDskCalendarVo.setParaId(paraId);
			_aptDskCalendarVo.setSlotDuration(slotDuration+" minutes");
			_aptDskCalendarVo.setAptId(aptForId);
			
			jsonString =AptDskCalendarDATA.getDayWiseAptCountDetail(_aptDskCalendarVo, userVO);
			//jsonString ="{\"events\":["+jsonString+"]}";
			System.out.println("json----" + jsonString);
			WebUTIL.setAttributeInSession(objRequest, "actualParaRefIdSlotDuration", actualParaRefIdSlotDuration);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	
	public static void getDayWiseAptDetail(String startDate,String endDate,String paraId,String aptForId,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		String jsonString="";
		int maxdisplayOrder=0;
		String actualParaRefIdSlotDuration=null;
		String actualParaRefId,slotDuration=null;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			AptDskCalendarVO _aptDskCalendarVo=new AptDskCalendarVO();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	        _aptDskCalendarVo.setStartDate(sdf.format(new Date(startDate)));
	        _aptDskCalendarVo.setEndDate(sdf.format(new Date(endDate)));

			//actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptForId, paraId);//actualParaRefId^slotDuartion
			//actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
			//slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
			//_aptDskCalendarVo.setParaRefId(actualParaRefId);
			//_aptDskCalendarVo.setSlotDuration(slotDuration+" minutes");
	        paraId=paraId.replace('^', '#');
	        _aptDskCalendarVo.setParaId(paraId);
			_aptDskCalendarVo.setAptId(aptForId);
			
			jsonString =AptDskCalendarDATA.getDayWiseAptDetail(_aptDskCalendarVo, userVO);
			//jsonString ="{\"events\":["+jsonString+"]}";
			System.out.println("getDayWiseAptDetail json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	
	public static void getShiftDetail(String startDate,String endDate,String paraId,String aptForId,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		String jsonString="";
		String actualParaRefIdSlotDuration=null;
		String actualParaRefId,slotDuration=null;
		WebRowSet ws=null;

		try
		{
			UserVO userVO=getUserVO(objRequest);
			AptDskCalendarVO _aptDskCalendarVo=new AptDskCalendarVO();
	       	AppointmentParameterVO appParaObj= new AppointmentParameterVO();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	        SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
	        String slotTime=stf.format(new Date(startDate));
	        _aptDskCalendarVo.setStartDate(sdf.format(new Date(startDate)));
	        _aptDskCalendarVo.setEndDate(sdf.format(new Date(endDate)));
	        String slotDate=_aptDskCalendarVo.getStartDate();
	        String actualParaId[]=new String[7];
	        List<String> shiftId=new ArrayList<String>();


	        if(objRequest.getAttribute("actualParaRefIdSlotDuration")!=null)
	        	actualParaRefIdSlotDuration=(String) objRequest.getAttribute("actualParaRefIdSlotDuration");
	        else
	        	actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptForId, paraId);//actualParaRefId^slotDuartion
			
	        actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
			slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
					
			appParaObj.setAppointmentForId(aptForId);
			actualParaId=paraId.replace("^", "#").split("#");
			appParaObj.setArrActualParaId(actualParaId);
			
			appParaObj.setSlotDuration(slotDuration);
			appParaObj.setActualParameterReferenceId(actualParaRefId);
			appParaObj.setAppointmentForDate(_aptDskCalendarVo.getStartDate());			
			
			if(objRequest.getAttribute("slotDate")!=null && objRequest.getAttribute("slotData")!=null){
				String alFetchDate=(String)objRequest.getAttribute("slotDate");
				if(slotDate.equals(alFetchDate))
					ws=(WebRowSet)objRequest.getAttribute("slotData");				
			}
			else
				ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
			
			if(ws!=null && ws.size()>0)
			{
				while(ws.next())
				{
					System.out.println(ws.getString(8)+"--"+slotTime+"--"+ws.getString(4).equals(slotTime));
					if(ws.getString(8).equals(slotTime)){
						shiftId.add(ws.getString(2));
					}
				}
			}
			
			if(shiftId.size()>0)
				jsonString=shiftId.get(0);
			else
				jsonString="";
			
			WebUTIL.setAttributeInSession(objRequest, "slotData", ws);
			WebUTIL.setAttributeInSession(objRequest, "slotDate", _aptDskCalendarVo.getStartDate());

			//jsonString ="{\"events\":["+jsonString+"]}";
			System.out.println("getShiftDetail json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("deprecation")
	public static void rescheduleAppointment(AptDskCalendarSUP objAptDskCalendar_p,HttpServletRequest objRequest,HttpServletResponse objResponse) {
		Status status=new Status();
		String jsonString="";
		try
		{
			UserVO userVO=getUserVO(objRequest);
			RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO= new RescheduleCancelAppointmentVO(); 
			HelperMethods.populate(objRescheduleCancelAppointmentVO, objAptDskCalendar_p);
			
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Date startDate=new Date(objAptDskCalendar_p.getStartDate());
			Date endDate=null;
			if(objAptDskCalendar_p.getEndDate()!=null&&!objAptDskCalendar_p.getEndDate().equals(""))
				endDate=new Date(objAptDskCalendar_p.getEndDate());
			else
				endDate=startDate;
			String slotST=getSlotTime(startDate);
			String slotET=getSlotTime(endDate);


			if(objAptDskCalendar_p.getAppointmentNo()!=null){
				System.out.println("AptDskCalendarUTIL:::rescheduleAppointment()::");
				objRescheduleCancelAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_RESCHEDULED);
				objRescheduleCancelAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_PRIOR_APPOINTMENT_FROM_APPT_MODULE);
				objRescheduleCancelAppointmentVO.setAppointmentDate(sdf.format(startDate));
				objRescheduleCancelAppointmentVO.setSlotST(slotST);
				objRescheduleCancelAppointmentVO.setSlotET(slotET);
				objRescheduleCancelAppointmentVO.setAppointmentTime(slotST);

				PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				if(objPatientVO!=null)
					HelperMethods.populate(objRescheduleCancelAppointmentVO, objPatientVO);
				objRescheduleCancelAppointmentVO= RescheduleCancelAppointmentDATA.saveRescheduleCancelAppointment(objRescheduleCancelAppointmentVO,userVO);
				status.add(Status.DONE,"Appointment Rescheduled Successfully" ,"");
				jsonString="Appointment Rescheduled Successfully";
			}
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.ERROR,"Unable to Reschedule the Appointment" ,"");
			jsonString="Unable to Reschedule the Appointment";
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
			WebUTIL.writeResponse(objResponse, jsonString,null);

		}
	}
	
	
	public static String getSlotTime(Date slotDate)
	{
		String sloTime="";
		int hours,minutes;
		
		hours=slotDate.getHours();
		minutes=slotDate.getMinutes();
		
		sloTime = String.format("%02d:%02d", hours, minutes);
		
		
		return sloTime;
	}
	
	
	public static void cancelAppointment(AptDskCalendarSUP objAptDskCalendar_p,HttpServletRequest objRequest) {
		Status status=new Status();
		try						
		{
			System.out.println("AptDskCalendarUTIL()::cancelAppointment");
			UserVO userVO=getUserVO(objRequest);
			RescheduleCancelAppointmentVO objCancelAppointmentVO= new RescheduleCancelAppointmentVO(); 
			HelperMethods.populate(objCancelAppointmentVO, objAptDskCalendar_p);
			
			objCancelAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_CANCELLED);
				objCancelAppointmentVO.setRemarks(objAptDskCalendar_p.getRemarks());
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
	
	public static void setPatientDtlByCrno(AptDskCalendarSUP objAptDskCalendar_p,HttpServletRequest objRequest_p){
		
		System.out.println("AptDskCalendarUTIL :: setPatientDtlByCrno()");
		
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		objPatientVO.setPatCrNo(objAptDskCalendar_p.getPatCrNo());
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
		try{
			objPatientVO=PatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, visitType);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			objAptDskCalendar_p.setAfterGo("1");
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
	   		//objAptDskCalendar_p.setErrorMessage(e.getMessage());
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
	
	public static void SaveNewAppointment(AptDskCalendarSUP objAptDskCalendar_p,HttpServletRequest objRequest) {
		
		Status status=new Status();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			NewAppointmentVO objNewAppointmentVO= new NewAppointmentVO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat stf = new SimpleDateFormat("HH:mm");				
			
			HelperMethods.populate(objNewAppointmentVO, objAptDskCalendar_p);
			objNewAppointmentVO.setAppointmentDate(sdf.format(new Date(objAptDskCalendar_p.getStartDate())));
			objNewAppointmentVO.setSlotST(stf.format(new Date(objAptDskCalendar_p.getStartDate())));
			objNewAppointmentVO.setSlotET(stf.format(new Date(objAptDskCalendar_p.getEndDate())));
			objNewAppointmentVO.setAppointmentTime(stf.format(new Date(objAptDskCalendar_p.getStartDate())));
			objNewAppointmentVO.setAppointmentForId(objAptDskCalendar_p.getAptId());


			String[] actualParaId= new String[7];
			objAptDskCalendar_p.setActualParameterId(objAptDskCalendar_p.getParaId().replace("^", "#").split("#"));
			
			if(objAptDskCalendar_p.getActualParameterId()!=null &&  objAptDskCalendar_p.getActualParameterId().length>0){
				for(int i=0;i<objAptDskCalendar_p.getActualParameterId().length;i++){
					actualParaId[i]=objAptDskCalendar_p.getActualParameterId()[i];
				}
				for(int i=objAptDskCalendar_p.getActualParameterId().length; i<7;i++){
					actualParaId[i]="0";
				}
				objNewAppointmentVO.setActualParameterId(actualParaId);
				objNewAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_NEW);
				objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_PRIOR_APPOINTMENT_FROM_APPT_MODULE);
				
				PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				if(objPatientVO!=null)
					HelperMethods.populate(objNewAppointmentVO, objPatientVO);
				else
					objNewAppointmentVO.setPatCrNo(null);
				objNewAppointmentVO= NewAppointmentDATA.SaveNewAppointment(objNewAppointmentVO,userVO);
				/*  ## 		Modification Log							
		 		##		Modify Date				:26thFeb'15 
		 		##		Reason	(CR/PRS)		:SMS Integration
		 		##		Modify By				:Sheeldarshi */
				
				//SMS Code Commented by Singaravelan on 20-Mar-2015

				/*SMSConfig objSMSConfig=new SMSConfig();
				String   message  = "You have successfully booked an appointment in "+objAptDskCalendar_p.getDepartmentUnitName()+" on "+objNewAppointmentVO.getAppointmentDate()+ " at " +objNewAppointmentVO.getAppointmentTime()+" with Reference No. "+objNewAppointmentVO.getAppointmentNo();
				SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url, objNewAppointmentVO.getMobileNo(),message);
				*///End:Sheeldarshi 
				status.add(Status.DONE,"Appointment Booked Successfully with reference No. "+objNewAppointmentVO.getAppointmentNo()+" ." ,"");
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
		
	}

}
