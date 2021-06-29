package appointment.transactions.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import appointment.transactions.controller.actionsupport.RescheduleCancelAppointmentSUP;
import appointment.transactions.controller.util.ShiftAndSlotCancellationUTIL;

public class ShiftAndSlotCancellationACTION extends RescheduleCancelAppointmentSUP{

	private static final long serialVersionUID = 1L;
	private String message;
	private String strMsgString;
	private String strMsgType;
	private String strNormalMessage;
	private String strWarning;
	private String strErrorMsg;
	 
	
	public String execute() throws Exception
	{
		System.out.println("ShiftSlotCancellationACTION :: execute()");
		message = "Inside execute method";
		objRequest.getSession().removeAttribute("MAP_SHIFT_SLOT_AVAIL");
		return init();
	}
	 
	public String init()
	{
		System.out.println("ShiftSlotCancellationACTION :: init()");
		message = "Inside list method";
		this.clear();
		WebUTIL.refreshTransState(objRequest,"ShiftSlotCancellationACTION");
		Status objStatus=new Status();
		WebUTIL.setStatus(objRequest,objStatus);
		objRequest.getSession().removeAttribute("MAP_SHIFT_SLOT_AVAIL");
		return "NEW";
	}
	
	//To Fetch the Shift List for the Given AptId,ParaId & Date
	public String getAvailShift()
	{
		String aptDate="";
		String paraId=objRequest.getParameter("paraId");
		String aptId=objRequest.getParameter("aptId");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		ShiftAndSlotCancellationUTIL.getAvailableShift(objRequest, objResponse, aptId, paraId, aptDate);
		return null;
	}
	
	//To Fetch the Slot List for the Selected Shift
	public String getSlots()
	{
		String aptDate="";
		String paraId=objRequest.getParameter("paraId");
		String aptId=objRequest.getParameter("aptId");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		String shiftId=objRequest.getParameter("shiftId");

		ShiftAndSlotCancellationUTIL.getSlots(objRequest, objResponse, aptId, paraId, aptDate, shiftId);
		return null;
	}
	
	//To Hold All the Slots of given Shift to Cancel
	public void holdAllSlotsToCancel()
	{
		String aptDate="";
		String paraId=objRequest.getParameter("paraId");
		String aptId=objRequest.getParameter("aptId");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		String shiftId=objRequest.getParameter("shiftId");

		ShiftAndSlotCancellationUTIL.getSlots(objRequest, objResponse, aptId, paraId, aptDate, shiftId);
	}
	
	
	//To Cancel the slected Shift from the List
	public void cancelShift()
	{
		String aptDate="";
		String paraId=objRequest.getParameter("paraId");
		String aptId=objRequest.getParameter("aptId");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		String shiftId=objRequest.getParameter("shiftId");
		String actualParaRefId=objRequest.getParameter("actualParaRefId");
		this.setRemarks(objRequest.getParameter("remarks"));

		ShiftAndSlotCancellationUTIL.cancelShift(this,objRequest, objResponse, aptId, paraId, aptDate, shiftId, actualParaRefId);
	}
	
	//To Cancel the slots
	public void cancelSlots()
	{
		String paraId=objRequest.getParameter("paraId");
		String actualParaRefId=objRequest.getParameter("actualParaRefId");		
		String slotsData=objRequest.getParameter("slotsData");
		String aptType=objRequest.getParameter("aptType");

		ShiftAndSlotCancellationUTIL.cancelSlots(this, objRequest, objResponse, slotsData, actualParaRefId, paraId, aptType);
	}
		
		
	public String getActualParaIdWiseDetail()
	{
		System.out.println("RescheduleCancelAppointmentACTION :: getActualParaIdWiseDetail()");
		ShiftAndSlotCancellationUTIL.getActualParaIdWiseDetail(this, objRequest,objResponse,mapSesion);		
		return null;
	}	
		
		
	public String cancel()
	{
		return init();
	}
}
