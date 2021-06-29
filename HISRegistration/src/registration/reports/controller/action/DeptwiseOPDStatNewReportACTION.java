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
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
import registration.reports.controller.actionsupport.ReportSUP;
import registration.reports.controller.util.DeptwiseOPDStatNewReportUTIL;
import registration.transactions.controller.util.NewRegistrationUTIL;
import vo.registration.StatisticsReportVO;


/** 
* this new department wise opd statistics Action file for multiple hospitals

 */

public class DeptwiseOPDStatNewReportACTION extends StatisticsReportVO
{
	private String message;
	private String flag;
	
	public String execute() throws Exception {
		
		return init();
	}	

public String init(){
		
		WebUTIL.refreshTransState(super.getRequest(),"DeptwiseOPDStatNewReportACTION");	
		this.reset();
		DeptwiseOPDStatNewReportUTIL.getEssentials(super.getRequest());
		UserVO _userVo=ControllerUTIL.getUserVO(super.getRequest());
		
		
		Date Date=(Date)request.getSession().getAttribute("sysDate");
		Date lastDate=(Date)request.getSession().getAttribute("yesterDay");

		String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
		String yesterDay=WebUTIL.getCustomisedSysDate(lastDate, "dd-MMM-yyyy");

		String sysTime=WebUTIL.getCustomisedSysDate(Date, "HH:mm");
		String sysMonth=WebUTIL.getCustomisedSysDate(Date, "MMM");
		String sysYear=WebUTIL.getCustomisedSysDate(Date, "yyyy");

		System.out.println("----yesterDay----"+yesterDay+"------");
		System.out.println("----sysDate----"+sysDate+"------");
		System.out.println("----sysTime----"+sysTime+"------");
		System.out.println("----sysMonth----"+sysMonth+"------");
		System.out.println("----sysYear----"+sysYear+"------");


		this.setStrChoice("0");this.setSysDate(sysDate);
		this.setFromHour("00");this.setFromMin("01");
		this.setToHour(sysTime.split(":")[0]);this.setToMin(sysTime.split(":")[1]);
		this.setStrOrderBy("3");this.setStrGroupBy("3");
		this.setGraphOrText("1");this.setReportType("2");
		
		this.setFromMonth(sysMonth);this.setToMonth(sysMonth);
		this.setFromYear(sysYear);this.setToYear(sysYear);
		this.setFromMonthYear(sysYear);this.setToMonthYear(sysYear);
		this.setHospitalCode(_userVo.getHospitalCode());
		
		
		return "NEW";
	}
public void getUnit() {
	   
	System.out.println("deptwise statistics New  Report :: getUnit()");
	message = "Inside getUnit() method";			
	DeptwiseOPDStatNewReportUTIL.getUnit_AJAX(request,response);
   }
public void showReport() {
	   
	System.out.println("Deptwise OPDStatistics New Report :: showReport()");
	message = "Inside showReport() method";			
	DeptwiseOPDStatNewReportUTIL.showReport(this, request, response);
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





