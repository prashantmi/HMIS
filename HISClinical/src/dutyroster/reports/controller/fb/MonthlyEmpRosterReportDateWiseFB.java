package dutyroster.reports.controller.fb;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MonthlyEmpRosterReportDateWiseFB extends ActionForm {

	private String year;
	private String month;
	private String rosterId;
	private String areaTypeCode;
	private String areaCode;
	private String hmode;
	private String isValid;
	private String hospitalCode;
	private String serialNo;	
	private String designationId;
	private String rosterCategory;
	private String headerMsg;
	private String startDate;
	private String endDate;
	private String printingFormat;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		this.year = "-1";
		this.month = "-1";	
		this.areaTypeCode = "-1";		
		this.areaCode = "-1";		
		this.rosterId = "-1";		
		this.isValid = "-1";
		this.hospitalCode = "";	
		this.serialNo = "";			
		this.designationId="ALL";
		this.rosterCategory="";
		this.headerMsg="";
		this.startDate="";
		this.endDate="";
		this.printingFormat="C";
				
	}

	


	public String getPrintingFormat() {
		return printingFormat;
	}




	public void setPrintingFormat(String printingFormat) {
		this.printingFormat = printingFormat;
	}




	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getHeaderMsg() {
		return headerMsg;
	}

	public void setHeaderMsg(String headerMsg) {
		this.headerMsg = headerMsg;
	}

	public String getRosterCategory() {
		return rosterCategory;
	}

	public void setRosterCategory(String rosterCategory) {
		this.rosterCategory = rosterCategory;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	
	public String getRosterId() {
		return rosterId;
	}

	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

		
}
