package appointment.transactions.controller.action;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import appointment.transactions.controller.actionsupport.AppointmentGlobalSUP;
import appointment.transactions.controller.util.AppointmentTagUTIL;
import appointment.transactions.controller.util.NewAppointmentUTIL;

public class AppointmentTagAction extends AppointmentGlobalSUP 
{
	
	private static final long serialVersionUID = 1L;
	private String message;
	private String strMsgString;
	private String strMsgType;
	private String strNormalMessage;
	private String strWarning;
	private String strErrorMsg;
	private Date _tday=new Date();
	 
	public String execute() throws Exception
	 {
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return null;
	 }
	public void makeParameterHTML()
	 {
		message = "Inside makeParameterHTML() method";
		AppointmentTagUTIL.getParameterHTML(this, objRequest,objResponse,mapSesion);
	 }
	public void getParameterComboValueHTML()
	 {
		message = "Inside getParameterComboValueHTML() method";
		AppointmentTagUTIL.getParameterComboValueHTML(this, objRequest,objResponse,mapSesion);
	 }
	
	public void getAppointmentListHTML()
	 {
		message = "Inside getAppointmentListHTML() method";
		//For Testing Purpose Only Uncomment this code when Tag is done and comment tag part
		
		//AppointmentTagUTIL.getAppointmentListHTML(this, objRequest,objResponse,mapSesion);		
		////AppointmentTagUTIL.getAppointmentListHTML(this, objRequest,objResponse,mapSesion);
		//String tagView="1";//Latest Free Slot,2=Whole Tag View
		//AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
		//AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10412", "0","0","0","0","0","0","1","",tagView);
		
		String tagView="2";//Latest Free Slot,2=Whole Tag View
		String aptId="1";
		String _paraId[]={"0","0","0","0","0","0","0"};
		String paraId=objRequest.getParameter("allActualParameterId");
		//String crNo=objRequest.getParameter("crNo");
		String aptForDate=objRequest.getParameter("appointmentDate");
		if(paraId.indexOf("^")>0)
			 _paraId=paraId.replace("^", "#").split("#");	
		else
			_paraId[0]=paraId;
		
		if(null!=objRequest.getParameter("aptId"))
			aptId=objRequest.getParameter("aptId");
		
		AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"", aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptForDate);

	}
	
	
	public void setSelectedAppointmentObjectInSession()
	 {
		message = "Inside setSelectedAppointmentObjectInSession() method";
		AppointmentTagUTIL.setSelectedAppointmentObjectInSession(this, objRequest,objResponse,mapSesion);
	 }
	public void makeAppointmentTagHTML()
	 {
		message = "Inside makeAppointmentTagHTML() method";
		String tagView="1";//1=Latest Free Slot,2=Whole Tag View
		//AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
		
		String aptDate="";
		String aptId="1";
		String _paraId[]={"0","0","0","0","0","0","0"};
		String paraId=objRequest.getParameter("paraId");
		String crNo=objRequest.getParameter("crNo");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		if(paraId.indexOf("^")>0)
			 _paraId=paraId.replace("^", "#").split("#");	
		if(null!=objRequest.getParameter("aptId"))
			aptId=objRequest.getParameter("aptId");
		
		AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate);
		
	 }
	public String makeAppointmentTagHTMLWholeTag()
	 {
		message = "Inside makeAppointmentTagHTML() method";
		String tagView="2";//1=Latest Free Slot,2=Whole Tag View
		//String tagHTML=AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
		
		String aptDate="",aptId="1";
		String _paraId[]={"0","0","0","0","0","0","0"};
		String paraId=objRequest.getParameter("paraId");
		String crNo=objRequest.getParameter("crNo");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		if(paraId.indexOf("^")>0)
			 _paraId=paraId.replace("^", "#").split("#");		
		if(null!=objRequest.getParameter("aptId"))
			aptId=objRequest.getParameter("aptId");
		
		String tagHTML=AppointmentTagUTIL.MakeAppointmentWholeTagForNewAppt(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate);
		//String tagHTML=AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate);
		this.setAptTagHTML(tagHTML);
		return "TAG";
	 }
	public void makeTimeSlotTagHTML()
	 {
		message = "Inside makeAppointmentTagHTML() method";
		String tagView="1";//1=Latest Free Slot,2=Whole Tag View
		//AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
		
		String aptDate="",aptId="1";
		String _paraId[]={"0","0","0","0","0","0","0"};
		String paraId=objRequest.getParameter("paraId");
		String crNo=objRequest.getParameter("crNo");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		if(paraId.indexOf("^")>0)
			 _paraId=paraId.replace("^", "#").split("#");		
		if(null!=objRequest.getParameter("aptId"))
			aptId=objRequest.getParameter("aptId");
		
		AppointmentTagUTIL.MakeTimeSlotTag(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate);
		
	 }
	//For Multiple Row Appointment Slot Integration
		public void makeAppointmentTagHTMLDivWise()
		 {
			message = "Inside makeAppointmentTagHTML() method";
			String tagView="1";//1=Latest Free Slot,2=Whole Tag View
			//AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
			
			String aptDate="";
			String aptId="1";
			String _paraId[]={"0","0","0","0","0","0","0"};
			String paraId=objRequest.getParameter("paraId");
			String crNo=objRequest.getParameter("crNo");
			String divId=objRequest.getParameter("divId");

			if(null!=objRequest.getParameter("appointmentDate"))
				aptDate=objRequest.getParameter("appointmentDate");
			if(paraId.indexOf("^")>0)
				 _paraId=paraId.replace("^", "#").split("#");	
			if(null!=objRequest.getParameter("aptId"))
				aptId=objRequest.getParameter("aptId");
			if(null!=objRequest.getParameter("tagView"))
				tagView=objRequest.getParameter("tagView");
			
			AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate,divId);
			
		 }
		//For Multiple Row Appointment Slot Integration
		public String makeAppointmentTagHTMLWholeTagDivWise()
		 {
			message = "Inside makeAppointmentTagHTML() method";
			String tagView="2";//1=Latest Free Slot,2=Whole Tag View
			//String tagHTML=AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
			
			String aptDate="",aptId="1";
			String _paraId[]={"0","0","0","0","0","0","0"};
			String paraId=objRequest.getParameter("paraId");
			String crNo=objRequest.getParameter("crNo");
			String divId=objRequest.getParameter("divId");
			
			if(null!=objRequest.getParameter("appointmentDate"))
				aptDate=objRequest.getParameter("appointmentDate");
			if(paraId.indexOf("^")>0)
				 _paraId=paraId.replace("^", "#").split("#");		
			if(null!=objRequest.getParameter("aptId"))
				aptId=objRequest.getParameter("aptId");
			
			String tagHTML=AppointmentTagUTIL.MakeAppointmentWholeTagForInvestigation(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate,divId);
			this.setAptTagHTML(tagHTML);
			return "TAG";
		 }
	
	public void checkSlotAvailibilty()
	 {
		message = "Inside checkSlotAvailibilty() method";
		AppointmentTagUTIL.checkSlotAvailibilty(this,objRequest,objResponse);

	 }	
	
	public void holdSlot()
	 {
		message = "Inside holdSlot() method";
		AppointmentTagUTIL.HoldSlotForNewAppointment(this, objRequest, objResponse);

	 }
	
	public void save()
	 {
		message = "Inside save() method";
		if(null!=objRequest.getParameter("appointmentDate"))
			this.setAppointmentDate(objRequest.getParameter("appointmentDate"));
		if(null!=objRequest.getParameter("patCrNo"))
			this.setPatCrNo(objRequest.getParameter("patCrNo"));
		AppointmentTagUTIL.SaveNewAppointment(this,objRequest,objResponse);

	 }	
	
	public void saveHoldSlot()
	 {
		message = "Inside saveHoldSlot() method";
		AppointmentTagUTIL.SaveHoldedAppointment(this, objRequest, objResponse);

	 }	
	
	public void checkSlotAvailibiltyOnSaveTime()
	 {
		message = "Inside saveHoldSlot() method";
		AppointmentTagUTIL.checkSlotAvailibiltyOnSaveTime(this, objRequest, objResponse);

	 }	
	
	public void makeTimeTag()
	 {
		message = "Inside makeTimeTag() method";
		AppointmentTagUTIL.MakeTimeTag(objRequest, objResponse);

	 }
	/*For TImeSLotTag with capping*/
	public void makeTimeSlotTagHTML_WithCapping()
	 {
		message = "Inside makeAppointmentTagHTML() method";
		String tagView="1";//1=Latest Free Slot,2=Whole Tag View
		//AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
		
		String aptDate="",aptId="1";
		String _paraId[]={"0","0","0","0","0","0","0"};
		String paraId=objRequest.getParameter("paraId");
		String crNo=objRequest.getParameter("crNo");
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		if(paraId.indexOf("^")>0)
			 _paraId=paraId.replace("^", "#").split("#");		
		if(null!=objRequest.getParameter("aptId"))
			aptId=objRequest.getParameter("aptId");
		
		 String apptType =objRequest.getParameter("apptType");// in action while calling
		
		AppointmentTagUTIL.MakeTimeSlotTag_withCapping(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate, apptType);
		
	 }//End : TIme SLot Tag with capping
	//
	//For Multiple Row Appointment Slot Integration
	public String makeAppointmentTagHTMLWholeTagDivWise_withCapping()
	 {
		message = "Inside makeAppointmentTagHTMLWholeTagDivWise_withCapping() method";
		String tagView="2";//1=Latest Free Slot,2=Whole Tag View
		//String tagHTML=AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
		
		String aptDate="",aptId="1";
		String _paraId[]={"0","0","0","0","0","0","0"};
		String paraId=objRequest.getParameter("paraId");
		String crNo=objRequest.getParameter("crNo");
		String divId=objRequest.getParameter("divId");
		
		if(null!=objRequest.getParameter("appointmentDate"))
			aptDate=objRequest.getParameter("appointmentDate");
		if(paraId.indexOf("^")>0)
			 _paraId=paraId.replace("^", "#").split("#");		
		if(null!=objRequest.getParameter("aptId"))
			aptId=objRequest.getParameter("aptId");
		
		 String apptType =objRequest.getParameter("apptType");// in action while calling
		 
		String tagHTML=AppointmentTagUTIL.MakeAppointmentWholeTagForInvestigation_withCapping(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate,divId, apptType);
		this.setAptTagHTML(tagHTML);
		return "TAG";
	 }
	//For Multiple Row Appointment Slot Integration
			public void makeAppointmentTagHTMLDivWise_withCapping()
			 {
				message = "Inside makeAppointmentTagHTML() method";
				String tagView="1";//1=Latest Free Slot,2=Whole Tag View
				//AppointmentTagUTIL.MakeAppointmentTag(objRequest,objResponse,"10111111", "1", "10114", "0","0","0","0","0","0","1","",tagView);
				String apptType="1";
				String aptDate="";
				String aptId="1";
				String _paraId[]={"0","0","0","0","0","0","0"};
				String paraId=objRequest.getParameter("paraId");
				String crNo=objRequest.getParameter("crNo");
				String divId=objRequest.getParameter("divId");

				if(null!=objRequest.getParameter("appointmentDate"))
					aptDate=objRequest.getParameter("appointmentDate");
				if(paraId.indexOf("^")>0)
					 _paraId=paraId.replace("^", "#").split("#");	
				if(null!=objRequest.getParameter("aptId"))
					aptId=objRequest.getParameter("aptId");
				if(null!=objRequest.getParameter("tagView"))
					tagView=objRequest.getParameter("tagView");
				if(null!=objRequest.getParameter("apptType"))
					apptType=objRequest.getParameter("apptType");
			
				
				AppointmentTagUTIL.MakeAppointmentTag_withCapping(objRequest,objResponse,crNo, aptId, _paraId[0], _paraId[1],_paraId[2],_paraId[3],_paraId[4],_paraId[5],_paraId[6],"1","",tagView,aptDate,divId, apptType);
				
			 }
}
