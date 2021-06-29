/**
 * 
 */
package appointment.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import appointment.transactions.controller.actionsupport.AptDskCalendarSUP;
import appointment.transactions.controller.util.AptDskCalendarUTIL;
import appointment.transactions.controller.util.NewAppointmentUTIL;

/**
 * @author singaravelan
 *
 */
public class AptDskCalendarACTION extends AptDskCalendarSUP{
	
	private static final long serialVersionUID = 1L;
	private String message;
	private String strMsgString;
	private String strMsgType;
	private String strNormalMessage;
	private String strWarning;
	private String strErrorMsg;
	 
	public String execute() throws Exception
	{
		System.out.println("AptDskCalendarACTION :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return init();
	}
	 
	public String init()
	{
		System.out.println("AptDskCalendarACTION :: init()");
		//this.clear();
		WebUTIL.refreshTransState(objRequest,"AptDskCalendarACTION");
		Status objStatus=new Status();
		
		WebUTIL.setStatus(objRequest,objStatus);
		return "NEW";
	}
	public String getActualParaIdWiseDetail()
	{
		System.out.println("AptDskCalendarACTION :: getActualParaIdWiseDetail()");
		AptDskCalendarUTIL.getActualParaIdWiseDetail(this.getActualParameterReferenceId(), objRequest,objResponse,mapSesion);		
		return null;
	}
	
	public String getSlotDurationDetail()
	{
		System.out.println("AptDskCalendarACTION :: getSlotDurationDetail()");
		AptDskCalendarUTIL.getSlotDurationDetail(this.getStartDate(),this.getEndDate(),this.getParaId(),this.getAptId(), objRequest,objResponse,mapSesion);		
		return null;
	}
	
	public String getDayWiseAptCountDetail()
	{
		System.out.println("AptDskCalendarACTION :: getDayWiseAptCountDetail()");
		AptDskCalendarUTIL.getDayWiseAptCountDetail(this.getStartDate(),this.getEndDate(),this.getParaId(),this.getAptId(), objRequest,objResponse,mapSesion);		
		return null;
	}
	
	public String getDayWiseAptDetail()
	{
		System.out.println("AptDskCalendarACTION :: getDayWiseAptDetail()");
		AptDskCalendarUTIL.getDayWiseAptDetail(this.getStartDate(),this.getEndDate(),this.getParaId(),this.getAptId(), objRequest,objResponse,mapSesion);		
		return null;
	}
	
	public String aptReschedule()
	{
		System.out.println("AptDskCalendarACTION :: aptReschedule()");
		AptDskCalendarUTIL.rescheduleAppointment(this, objRequest,objResponse);
		return null;
	}
	
	public String newAptCreation()
	{
		System.out.println("AptDskCalendarACTION :: newAptCreation()");
		NewAppointmentUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
		this.setPatCrNo((String) super.getObjRequest().getSession()
				.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		this.setAfterGo("0");
		AptDskCalendarUTIL.getGenderList(objRequest);
		AptDskCalendarUTIL.getAgeType(objRequest, this);
		return "NEWAPT";
	}
	
	public String getShiftDetail()
	{
		System.out.println("AptDskCalendarACTION :: getShiftDetail()");
		AptDskCalendarUTIL.getShiftDetail(this.getStartDate(),this.getEndDate(),this.getParaId(),this.getAptId(), objRequest, objResponse, mapSesion);
		return null;
	}
	
	public String GETPATDTL()
	{
		System.out.println("AptDskCalendarACTION :: GETPATDTL()");
		AptDskCalendarUTIL.setPatientDtlByCrno(this,objRequest);			
		return "NEWAPT";
	}
	
	public String createNewAppointment()
	{
		System.out.println("AptDskCalendarACTION :: createNewApt()");
		AptDskCalendarUTIL.SaveNewAppointment(this, objRequest);
		return init();
	}
	
	public String aptCancel()
	{
		System.out.println("AptDskCalendarACTION :: aptCancel()");
		AptDskCalendarUTIL.cancelAppointment(this, objRequest);
		return null;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrNormalMessage() {
		return strNormalMessage;
	}

	public void setStrNormalMessage(String strNormalMessage) {
		this.strNormalMessage = strNormalMessage;
	}

	public String getStrWarning() {
		return strWarning;
	}

	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
}
