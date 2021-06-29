package hisglobal.vo;

public class PatientAuditLogMstVO extends ValueObject
{
	private String auditLogId;
	private String auditHeader;
	private String auditLogQuery;
	private String auditLogDateQuery;
	private String currentRecordQuery;
	private String baseRecordQuery;
	private String isDateWise;
	private String fromDate;
	private String toDate;
	private String moduleId;
	private String moduleName;
	private String displayLogic;
	
	public String getAuditLogDateQuery() {
		return auditLogDateQuery;
	}
	public void setAuditLogDateQuery(String auditLogDateQuery) {
		this.auditLogDateQuery = auditLogDateQuery;
	}
	public String getAuditLogId() {
		return auditLogId;
	}
	public void setAuditLogId(String auditLogId) {
		this.auditLogId = auditLogId;
	}
	public String getAuditHeader() {
		return auditHeader;
	}
	public void setAuditHeader(String auditHeader) {
		this.auditHeader = auditHeader;
	}
	public String getAuditLogQuery() {
		return auditLogQuery;
	}
	public void setAuditLogQuery(String auditLogQuery) {
		this.auditLogQuery = auditLogQuery;
	}
	public String getCurrentRecordQuery() {
		return currentRecordQuery;
	}
	public void setCurrentRecordQuery(String currentRecordQuery) {
		this.currentRecordQuery = currentRecordQuery;
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
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDisplayLogic() {
		return displayLogic;
	}
	public void setDisplayLogic(String displayLogic) {
		this.displayLogic = displayLogic;
	}
	public String getBaseRecordQuery() {
		return baseRecordQuery;
	}
	public void setBaseRecordQuery(String baseRecordQuery) {
		this.baseRecordQuery = baseRecordQuery;
	}
	
	
	
	
}
