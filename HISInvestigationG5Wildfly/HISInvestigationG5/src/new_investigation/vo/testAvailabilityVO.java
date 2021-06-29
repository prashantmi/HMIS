package new_investigation.vo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import hisglobal.vo.ValueObject;



public class testAvailabilityVO extends ValueObject
{
	                    
	private String seatId;
	
	private String patCrNo;
	private int startIndex;
	private int endIndex;
	private String resultArray;
	private String testCode;
	private String labCode;
	private String isAvailable;
	private String fromDate="";
	private String toDate="";
	private String testName;
	private String availableValue;
	private String remarks;
	private String testStatus;
	private String searchTest;

	
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getResultArray() {
		return resultArray;
	}
	public void setResultArray(String resultArray) {
		this.resultArray = resultArray;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
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
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getAvailableValue() {
		return availableValue;
	}
	public void setAvailableValue(String availableValue) {
		this.availableValue = availableValue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getSearchTest() {
		return searchTest;
	}
	public void setSearchTest(String searchTest) {
		this.searchTest = searchTest;
	}

	
	


}

