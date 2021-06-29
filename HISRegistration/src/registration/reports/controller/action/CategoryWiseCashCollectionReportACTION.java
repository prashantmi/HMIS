package registration.reports.controller.action;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import hisglobal.presentation.WebUTIL;
import registration.reports.controller.util.CategoryWiseCashCollectionReportUTIL;
import vo.registration.CategoryWiseCashCollectionReportVO;

public class CategoryWiseCashCollectionReportACTION extends CategoryWiseCashCollectionReportVO
{   private static final long serialVersionUID = 1L;
	private String message;
	private String flag;
	
	public String execute() throws Exception {
		
		return init();
}
public String init(){
		
		WebUTIL.refreshTransState(super.getRequest(),"CategoryWiseCashCollectionReportACTION");	
		this.reset();
		CategoryWiseCashCollectionReportUTIL.getEssentials(super.getRequest());
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
	   
	System.out.println("CategoryWiseCashCollection Report :: showReport()");
	message = "Inside showReport() method";			
	CategoryWiseCashCollectionReportUTIL.showReport(this, request, response);
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
