package appointment.reports.controller.action;

import hisglobal.presentation.WebUTIL;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import appointment.reports.controller.util.AppointmentListingReportUTIL;
import vo.appointment.AppontmentListingReportVO;

public class AppointmentListingReportACTION extends AppontmentListingReportVO
{
	private static final long serialVersionUID = 1L;
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 private String flag;
	 
	 public String execute() throws Exception
	 {
		System.out.println("AppointmentListingReportAction :: execute()");
		message = "Inside execute method";
		return init();
	 
	 }
	 public String init()
	 {
		 WebUTIL.refreshTransState(super.getRequest(),"AppointmentListingReportACTION");	
			this.reset();
			AppointmentListingReportUTIL.getEssentials(super.getRequest());
			Date Date=(Date)request.getSession().getAttribute("sysDate");
			Date lastDate=(Date)request.getSession().getAttribute("yesterDay");

			String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
			String yesterDay=WebUTIL.getCustomisedSysDate(lastDate, "dd-MMM-yyyy");

			String sysTime=WebUTIL.getCustomisedSysDate(Date, "HH:mm");
			System.out.println("----yesterDay----"+yesterDay+"------");
			System.out.println("----sysDate----"+sysDate+"------");
			System.out.println("----sysTime----"+sysTime+"------");
			

			this.setStrChoice("0");this.setSysDate(sysDate);
			this.setFromHour("00");this.setFromMin("01");
			this.setToHour(sysTime.split(":")[0]);this.setToMin(sysTime.split(":")[1]);
			this.setGraphOrText("1");this.setReportType("2");
			return "NEW"; 
		 
	 }
	 
	 public void showReport() {
		   
			System.out.println("Appointment Listing Report :: showReport()");
			message = "Inside showReport() method";			
			AppointmentListingReportUTIL.showReport(this, request, response);
		}	
	 
	 public String cancel() {
			
			return init();
		}	
			
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}	

		public HttpServletRequest getRequest() {
			return request;
		}

		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}


		@Override
		public void setSession(Map arg0) {
			// TODO Auto-generated method stub
			
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
	
}
