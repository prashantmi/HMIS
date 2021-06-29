package mrd.reports.controller.actionsupport;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ReportSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware
{
	
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	protected Map mapSession;	
	
	private String reportType;// rtf/pdf
	private String reportMode;// report or chart
	private String mode;// report or chart
	private String fromHour;
	private String toHour;
	private String fromMin;
	private String toMin;
	private String choice;
	private String fromDate = "";
	private String toDate = "";
	private String rptDesignName;
	private String graphOrText;
	private String chartType;
	private String sysDate;
	private String rptDesignPath;
	private String title;

	private String year;
	private String hospitalName;
	private String hospitalCode;
			
	
	@Override
	public void setSession(Map sessionmap) {
		this.mapSession=sessionmap;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;		
	}



	public HttpServletRequest getRequest() {
		return request;
	}



	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}



	public HttpServletResponse getResponse() {
		return response;
	}



	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}



	public Map getMapSession() {
		return mapSession;
	}



	public void setMapSession(Map mapSession) {
		this.mapSession = mapSession;
	}



	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportMode() {
		return reportMode;
	}

	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFromHour() {
		return fromHour;
	}

	public void setFromHour(String fromHour) {
		this.fromHour = fromHour;
	}

	public String getToHour() {
		return toHour;
	}

	public void setToHour(String toHour) {
		this.toHour = toHour;
	}

	public String getFromMin() {
		return fromMin;
	}

	public void setFromMin(String fromMin) {
		this.fromMin = fromMin;
	}

	public String getToMin() {
		return toMin;
	}

	public void setToMin(String toMin) {
		this.toMin = toMin;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getRptDesignName() {
		return rptDesignName;
	}

	public void setRptDesignName(String rptDesignName) {
		this.rptDesignName = rptDesignName;
	}

	public String getGraphOrText() {
		return graphOrText;
	}

	public void setGraphOrText(String graphOrText) {
		this.graphOrText = graphOrText;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getRptDesignPath() {
		return rptDesignPath;
	}

	public void setRptDesignPath(String rptDesignPath) {
		this.rptDesignPath = rptDesignPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


}
