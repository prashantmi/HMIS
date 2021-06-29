package new_investigation.vo;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import hisglobal.vo.ValueObject;



public class InvResultReportPrintingNewVO extends ValueObject
{
	                    
	private String patCRNo;
	
	private String requistionNo;
	
	private String labCode;
	
	private String labName;
	
	private String testCode;
	
	private String testName;
	
	private String groupCode;
	
	private String groupName;
	
	private String reqStatusCode;
	
	private String reqstatusName;
	
	private String sampleNo;
	
	private String LabNo;
	
	private String reportGeneDate;
	
	private String reportReqDate;
	
	private String prioirtyCode;
	
	private String priorityName;
	
	private String billNo;
	
	private String reportUrl;

	private String pendingTest;

	private String reportgeneratetestname;
	private String isreqstatus;
	private String  reportgeneratetestnamegroupname;
	
	private String sampleCollectionDate;
	
	
	public String getPatCRNo() {
		return patCRNo;
	}

	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}

	public String getRequistionNo() {
		return requistionNo;
	}

	public void setRequistionNo(String requistionNo) {
		this.requistionNo = requistionNo;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getReqStatusCode() {
		return reqStatusCode;
	}

	public void setReqStatusCode(String reqStatusCode) {
		this.reqStatusCode = reqStatusCode;
	}

	public String getReqstatusName() {
		return reqstatusName;
	}

	public void setReqstatusName(String reqstatusName) {
		this.reqstatusName = reqstatusName;
	}

	public String getSampleNo() {
		return sampleNo;
	}

	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}

	public String getLabNo() {
		return LabNo;
	}

	public void setLabNo(String labNo) {
		LabNo = labNo;
	}

	public String getReportGeneDate() {
		return reportGeneDate;
	}

	public void setReportGeneDate(String reportGeneDate) {
		this.reportGeneDate = reportGeneDate;
	}

	public String getReportReqDate() {
		return reportReqDate;
	}

	public void setReportReqDate(String reportReqDate) {
		this.reportReqDate = reportReqDate;
	}

	public String getPrioirtyCode() {
		return prioirtyCode;
	}

	public void setPrioirtyCode(String prioirtyCode) {
		this.prioirtyCode = prioirtyCode;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getPendingTest() {
		return pendingTest;
	}

	public void setPendingTest(String pendingTest) {
		this.pendingTest = pendingTest;
	}

	public String getReportgeneratetestname() {
		return reportgeneratetestname;
	}

	public void setReportgeneratetestname(String reportgeneratetestname) {
		this.reportgeneratetestname = reportgeneratetestname;
	}

	public String getIsreqstatus() {
		return isreqstatus;
	}

	public void setIsreqstatus(String isreqstatus) {
		this.isreqstatus = isreqstatus;
	}

	public String getReportgeneratetestnamegroupname() {
		return reportgeneratetestnamegroupname;
	}

	public void setReportgeneratetestnamegroupname(
			String reportgeneratetestnamegroupname) {
		this.reportgeneratetestnamegroupname = reportgeneratetestnamegroupname;
	}

	public String getSampleCollectionDate() {
		return sampleCollectionDate;
	}

	public void setSampleCollectionDate(String sampleCollectionDate) {
		this.sampleCollectionDate = sampleCollectionDate;
	}
	
	
	
	
}

