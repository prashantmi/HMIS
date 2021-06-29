package registration.reports.controller.action;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.reports.controller.util.ConsolidatedCollectionReportUTIL;
import hisglobal.presentation.WebUTIL;
import vo.registration.CategoryWiseCashCollectionReportVO;

public class ConsolidatedCollectionReportACTION extends CategoryWiseCashCollectionReportVO
{   private static final long serialVersionUID = 1L;
	private String message;
	private String flag;
	
	public String execute() throws Exception {
		
		return init();
}
public String init(){
		
		WebUTIL.refreshTransState(super.getRequest(),"ConsolidatedCollectionReportACTION");	
		this.reset();
		ConsolidatedCollectionReportUTIL.getEssentials(super.getRequest());
		Date Date=(Date)request.getSession().getAttribute("sysDate");
		Date lastDate=(Date)request.getSession().getAttribute("yesterDay");

		String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
		String yesterDay=WebUTIL.getCustomisedSysDate(lastDate, "dd-MMM-yyyy");

	
		System.out.println("----yesterDay----"+yesterDay+"------");
		System.out.println("----sysDate----"+sysDate+"------");
		

		this.setStrChoice("0");this.setSysDate(sysDate);
		
		this.setGraphOrText("1");this.setReportType("2");
		return "NEW";
	}
public void showReport() {
	   
	System.out.println("CConsolidatedCollection Report :: showReport()");
	message = "Inside showReport() method";			
	ConsolidatedCollectionReportUTIL.showReport(this, request, response);
}	


public void showReport1() {
	   
	System.out.println(" Cashier wise Consolidated Collection Amt Report :: showReport1()");
	message = "Inside showReport() method";			
	ConsolidatedCollectionReportUTIL.showReport1(this, request, response);
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
