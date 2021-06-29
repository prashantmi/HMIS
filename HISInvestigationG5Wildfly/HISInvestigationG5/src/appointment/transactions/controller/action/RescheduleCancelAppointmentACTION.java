package appointment.transactions.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.appointment.RescheduleCancelAppointmentVO;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import appointment.config.AppointmentConfig;
import appointment.transactions.controller.actionsupport.RescheduleCancelAppointmentSUP;
import appointment.transactions.controller.util.NewAppointmentUTIL;
import appointment.transactions.controller.util.RescheduleCancelAppointmentUTIL;

public class RescheduleCancelAppointmentACTION extends RescheduleCancelAppointmentSUP{

	private static final long serialVersionUID = 1L;
	private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 RescheduleCancelAppointmentVO vo=null;
	 
	
	public String execute() throws Exception
	 {
		System.out.println("RescheduleCancelAppointmentACTION :: execute()");
		message = "Inside execute method";
		objRequest.getSession().removeAttribute("MAP_PREV_APPT");
		return init();
	 }
	 
	 public String init()
	 {
		System.out.println("RescheduleCancelAppointmentACTION :: init()");
		message = "Inside list method";
		this.clear();
		WebUTIL.refreshTransState(objRequest,"RescheduleCancelAppointmentACTION");
		Status objStatus=new Status();
		RescheduleCancelAppointmentUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
		WebUTIL.setStatus(objRequest,objStatus);
		RescheduleCancelAppointmentUTIL.getAgeType(objRequest,this);
		RescheduleCancelAppointmentUTIL.getGenderList(objRequest);
		RescheduleCancelAppointmentUTIL.getAppointmentMode(objRequest, this);
		objRequest.getSession().removeAttribute("MAP_PREV_APPT");
		return "NEW";
	 }
	 
		public String GETPATDTL()
		{
			RescheduleCancelAppointmentUTIL.setPatientDtlByCrno(this,objRequest);
			System.out.println("RescheduleCancelAppointmentACTION :: GETPATDTL::getPreviousAppointmentList");
			RescheduleCancelAppointmentUTIL.getPreviousAppointmentList(this, objRequest);
			return "NEW";
		}
		
		public String getActualParaIdWiseDetail()
		{
			System.out.println("RescheduleCancelAppointmentACTION :: getActualParaIdWiseDetail()");
			RescheduleCancelAppointmentUTIL.getActualParaIdWiseDetail(this, objRequest,objResponse,mapSesion);		
			return null;
		}
		
		public String save()
		 {
			System.out.println("RescheduleCancelAppointmentACTION :: Save()");
			RescheduleCancelAppointmentUTIL.saveRescheduleCancelAppointment(this, objRequest);
			return init();
		 }
		
		public String SearchAppointmentNoWise()
		 {
			System.out.println("RescheduleCancelAppointmentACTION :: SearchAppointmentNoWise()");
		    RescheduleCancelAppointmentUTIL.searchAppointmentNoWise(this, objRequest);
		    return "NEW";
		 }
		
		public String cancelAppointment()
		{
			System.out.println("RescheduleCancelAppointmentACTION :: CancelAppointment()");
			RescheduleCancelAppointmentUTIL.cancelAppointment(this, objRequest);
			return init();
		}
		
		public String cancel()
		{
			return init();
		}
}
