package hisglobal.utility.patientAuditLog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PatientAuditLogMstFB extends ActionForm{

	 private String hmode;
	 private String auditLogId; 
	 private String patCrNo; 
	 private String currentRecordQuery; 
	 private String auditLogQuery; 
	 private String auditHeader; 
	 private String selectedAuditHeader[]; 
	 private String htmlContent;
	 private String byteArray;
	 private String isDateWise;
	 private String fromDate;
	 private String toDate;
	 private String moduleId;
	 private String selectedModuleId[];
	 private String registerDate;
	 private String displayLogic;
	 private String isMultipleHeader="0";
	 private String auditLogIdToRemove;
	 private String auditHeaderSize="0";
	
	
	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.patCrNo="";
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(String auditLogId) {
		this.auditLogId = auditLogId;
	}

	public String getPatCrNo() {
		return patCrNo;
	}


	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}


	public String getCurrentRecordQuery() {
		return currentRecordQuery;
	}


	public void setCurrentRecordQuery(String currentRecordQuery) {
		this.currentRecordQuery = currentRecordQuery;
	}


	public String getAuditLogQuery() {
		return auditLogQuery;
	}


	public void setAuditLogQuery(String auditLogQuery) {
		this.auditLogQuery = auditLogQuery;
	}

	public String getAuditHeader() {
		return auditHeader;
	}

	public void setAuditHeader(String auditHeader) {
		this.auditHeader = auditHeader;
	}

	public String[] getSelectedAuditHeader() {
		return selectedAuditHeader;
	}

	public void setSelectedAuditHeader(String[] selectedAuditHeader) {
		this.selectedAuditHeader = selectedAuditHeader;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String[] getSelectedModuleId() {
		return selectedModuleId;
	}

	public void setSelectedModuleId(String[] selectedModuleId) {
		this.selectedModuleId = selectedModuleId;
	}

	public String getByteArray() {
		return byteArray;
	}


	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}

	public String getIsDateWise() {
		return isDateWise;
	}

	public void setIsDateWise(String isDateWise) {
		this.isDateWise = isDateWise;
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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getDisplayLogic() {
		return displayLogic;
	}

	public void setDisplayLogic(String displayLogic) {
		this.displayLogic = displayLogic;
	}

	public String getIsMultipleHeader() {
		return isMultipleHeader;
	}

	public void setIsMultipleHeader(String isMultipleHeader) {
		this.isMultipleHeader = isMultipleHeader;
	}

	public String getAuditLogIdToRemove() {
		return auditLogIdToRemove;
	}

	public void setAuditLogIdToRemove(String auditLogIdToRemove) {
		this.auditLogIdToRemove = auditLogIdToRemove;
	}

	public String getAuditHeaderSize() {
		return auditHeaderSize;
	}

	public void setAuditHeaderSize(String auditHeaderSize) {
		this.auditHeaderSize = auditHeaderSize;
	}
	
	
}
