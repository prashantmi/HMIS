/**
 * 
 */
package registration.reports.controller.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.config.HISConfig;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import registration.reports.controller.actionsupport.ReportSUP;
import registration.reports.controller.util.MctsPatientlistingReportUTIL;
import registration.transactions.controller.util.NewRegistrationUTIL;
import vo.registration.MctsPatientlistingReportVO;

public class MctsPatientlistingReportACTION extends MctsPatientlistingReportVO
{
	
		private String message;
		private String flag;
		
		public String execute() throws Exception {
			
			return init();
		}	

public String init(){
		
		WebUTIL.refreshTransState(super.getRequest(),"MctsPatientlistingReportACTION");	
		this.reset();
		MctsPatientlistingReportUTIL.getEssentials(super.getRequest());
		Date Date=(Date)request.getSession().getAttribute("sysDate");
		Date lastDate=(Date)request.getSession().getAttribute("yesterDay");

		String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
		String yesterDay=WebUTIL.getCustomisedSysDate(lastDate, "dd-MMM-yyyy");

		
		System.out.println("----yesterDay----"+yesterDay+"------");
		System.out.println("----sysDate----"+sysDate+"------");
		
		this.setFromDate(sysDate);
		this.setToDate(sysDate);
		this.setReportType("2");
	
		return "NEW";
	}
public void showReport() {
	   
	System.out.println("MctsPatientlistingReport :: showReport()");
	message = "Inside showReport() method";			
	MctsPatientlistingReportUTIL.showReport(this, request, response);
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