package registration.transactions.controller.action;

import java.awt.Window;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.HospitalMstVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.masters.controller.data.LocationMstDATA;
import  registration.transactions.controller.actionsupport.AppointmentCancellationSUP;
import registration.transactions.controller.util.AppointmentCancellationUTIL;
import vo.registration.PatientVO;


public class AppointmentCancellationAction extends AppointmentCancellationSUP
{
	 private String message;
	
	public String execute() throws Exception
	 {
		System.out.println("Appointment cancellation Action :: execute()");
		message = "Inside execute method";
		
		boolean ff=objRequest.getSession().isNew();
		return NEW();
	 }
	
	public String NEW()
	{   
		System.out.println("Appointment cancellation Action :: NEW()");
		 message = "Inside NEW method";
		 
		
		 WebUTIL.refreshTransState(objRequest,"AppointmentCancellationAction");
			Status objStatus=new Status();
			
			WebUTIL.setStatus(objRequest,objStatus);
		
		WebUTIL.setStatus(super.getObjRequest(), objStatus);
		AppointmentCancellationUTIL.setAllSpecialCRNoWithAppointment(this,objRequest,objResponse);
		
		return "NEW";
	
	}
	public String openPopup()
	 {
		 System.out.println("aptcancellationpopup:: openPatPopup()");
		 System.out.println("---------"+objRequest.getParameter("aptNo")+"-------");
		 
		 this.setPatAptNo(objRequest.getParameter("aptNo"));

		 
		 return "popup";
	 }
	
public String SAVE(){
		
	   CharacterEncoding.setCharacterEncoding(objRequest);
		
		AppointmentCancellationUTIL.saveAppointmentCancellation(this,objRequest);
		
		return NEW();
 }
		
 
 
 public String SEARCH(){
	    
		Status objStatus=new Status();
		HttpSession _httpses=objRequest.getSession();	
		
		
		if(_httpses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST)!=null){
			 AppointmentCancellationUTIL.setVoArrEssentials(objRequest,this);
			}
			else{
				AppointmentCancellationUTIL.searchSpecialCRNoWithAppointment(objRequest, this);
			}
		
		WebUTIL.setStatus(objRequest,objStatus);
	 	
	 	return "NEW";
		
	   }
 
 
 

}





