package registration.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;

import registration.transactions.controller.actionsupport.PatientReferralSUP;
import registration.transactions.controller.util.PatientReferralUTIL;

public class PatientReferralACTION extends PatientReferralSUP {

	private String message;
	private String strMsgString;
	private String strMsgType;
	private String strNormalMessage;
	private String strWarning;
	private String strErrorMsg;

	public String execute() {
		return NEW();
	}

	public String NEW() {

		WebUTIL.refreshTransState(super.getObjRequest(),"PatientReferralACTION");
		PatientReferralUTIL.setSysdate(super.getObjRequest());
		Status status = new Status();
		status.add(Status.NEW);
		
		System.out.println("Is Desk Call :: "+this.isDesk);
		if(this.isDesk!=null && !this.isDesk.equals("0")){		
			WebUTIL.setStatus(super.getObjRequest(), status);
			return GETPATDTL();
		}
		else{			
			this.setPatCrNo((String) super.getObjRequest().getSession()
					.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			this.setGoFlag("0");
			this.setAfterGo("0");
			this.setIsDesk("0");
			WebUTIL.setStatus(super.getObjRequest(), status);	
			return "NEW";
		}
	}

	public String GETPATDTL() {
		PatientReferralUTIL.setPatientDtlByCrno(this, super.getObjRequest());
		if(this.getGoFlag().equals("1"))
		{
		populateformvalues();
	
		}
		return "NEW";
	}
	
	public void populateformvalues() {

		System.out.println("PatientReferralACTION :: populateFormValues()");
		message = "Inside populateFormValues_AJAX() method";

		PatientReferralUTIL.getEssentials(this, objRequest);

	}

	public void getRefDept() {

		System.out.println("PatientReferralACTION :: getRefDept()");
		message = "Inside getRefDept() method";
		PatientReferralUTIL.getRefDept_AJAX(objRequest, objResponse);
	}

	public String SAVE() {

		PatientReferralUTIL.saveReferPatientDetail(this, super.getObjRequest());
		if(this.getAppointmentNo()!=null)
		{
		String appointmentNo      = this.getAppointmentNo().replaceAll(",", "").trim();
		String appointmentQueueNo = this.getAppointmentQueueNo().replaceAll(",", "").trim();
		this.setNormalMessage("Patient Referred with Appointment No. "+appointmentNo+" and Queue No. " +appointmentQueueNo);
		}
		else
			this.setNormalMessage("Patient Referred");	
		if(isDesk.equals("1")){
			return GETPATDTL();
		}
		else{
		this.setGoFlag("0");
		this.setAfterGo("0");
		this.setPatCrNo((String) super.getObjRequest().getSession()
				.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		return "NEW";
		}
	}

	public String cancel() throws Exception {
		return NEW();
	}

	public void setServletContext(ServletContext context) {
		this.objContext = context;

	}

	public String getIsDesk() {
		return isDesk;
	}

	public void setIsDesk(String isDesk) {
		this.isDesk = isDesk;
	}
	
	/*Method calling is from New Responsive desk*/
	public String NEWDRDESK() {
		WebUTIL.refreshTransState(super.getObjRequest(),"PatientReferralACTION");
		PatientReferralUTIL.setSysdate(super.getObjRequest());
		Status status = new Status();
		status.add(Status.NEW); 
		System.out.println("Is Desk Call :: "+this.isDesk);
		if(this.isDesk!=null && !this.isDesk.equals("0")){		
			WebUTIL.setStatus(super.getObjRequest(), status);
			return GETPATDTLDRDESK();
		}else{			
			this.setPatCrNo((String) super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			this.setGoFlag("0");
			this.setAfterGo("0");
			this.setIsDesk("0");
			WebUTIL.setStatus(super.getObjRequest(), status);	
			return "drdeskprocess";
		}
	}
	public String GETPATDTLDRDESK() {
		this.setPatCrNo(super.getObjRequest().getParameter("patCrNo"));
		//this.setPatCrNo("379111900000314");
		
		PatientReferralUTIL.setPatientDtlByCrno(this, super.getObjRequest());
		if(this.getGoFlag().equals("1"))
		{
		populateformvalues();
	
		}
		return "drdeskprocess";
	}

	/*Method calling is from New Responsive desk*/
	public String SAVEDRDESK() {

		PatientReferralUTIL.saveReferPatientDetail(this, super.getObjRequest());
		if(this.getAppointmentNo()!=null){
			String appointmentNo      = this.getAppointmentNo().replaceAll(",", "").trim();
			String appointmentQueueNo = this.getAppointmentQueueNo().replaceAll(",", "").trim();
			this.setNormalMessage("Patient Referred with Appointment No. "+appointmentNo+" and Queue No. " +appointmentQueueNo);
		}else{
			this.setNormalMessage("Patient Referred");	
		}
		if(isDesk.equals("1")){
			return GETPATDTLDRDESK();
		}else{
			this.setGoFlag("0");
			this.setAfterGo("0");
			this.setPatCrNo((String) super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			return "drdeskprocess";
		}
	}
	
}
