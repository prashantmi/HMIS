package appointment.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.util.List;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
import appointment.transactions.controller.util.NewAppointmentUTIL;

public class NewAppointmentAction extends NewAppointmentSUP 
{
	private static final long serialVersionUID = 1L;
	private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 
	public String execute() throws Exception
	 {
		System.out.println("NewAppointmentAction :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return init();
	 }
	 
	 public String init()
	 {
		System.out.println("NewAppointmentAction :: init()");
		message = "Inside list method";
		this.clear();
		WebUTIL.refreshTransState(objRequest,"NewAppointmentAction");
		Status objStatus=new Status();
		NewAppointmentUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
		WebUTIL.setStatus(objRequest,objStatus);
		this.setPatCrNo((String) super.getObjRequest().getSession()
				.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		NewAppointmentUTIL.getGenderList(objRequest);
		NewAppointmentUTIL.getAgeType(objRequest,this);
		NewAppointmentUTIL.getAppointmentMode(objRequest, this);
		return "NEW";
	 }
		public String GETPATDTL()
		{
			System.out.println("NewAppointmentAction :: GETPATDTL()");
			NewAppointmentUTIL.setPatientDtlByCrno(this,objRequest);			
			return "NEW";
		}
		public String getActualParaIdWiseDetail()
		{
			System.out.println("NewAppointmentAction :: getActualParaIdWiseDetail()");
			NewAppointmentUTIL.getActualParaIdWiseDetail(this, objRequest,objResponse,mapSesion);		
			return null;
		}
		public String Save()
		 {
			System.out.println("NewAppointmentAction :: Save()");
			NewAppointmentUTIL.SaveNewAppointment(this, objRequest);
			return init();
		 }
		public String Confirm(){
			System.out.println("NewAppointmentAction :: ConfirmAppointment()");
			NewAppointmentUTIL.ConfirmAppointment(this, objRequest);
			return init();
		}
		
		public String TEST(){
			System.out.println("NewAppointmentAction :: ConfirmAppointment()");
			NewAppointmentUTIL.ConfirmAppointment(this, objRequest);
			return "TEST";
		}
}
