package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class invReportHistoryVO extends ValueObject {

	private String serviceId;
	private String entryDate;
	private String lastDate;
	private String frequency;
	private String activeTime;
	private String isValid;
	private String insertDate;
	private String mongoDbNmae;
	private String mongoUri;
	private String pgUri;
	private String pgUsername;
	private String reportPath;
	private String activeReportTime;
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getMongoDbNmae() {
		return mongoDbNmae;
	}
	public void setMongoDbNmae(String mongoDbNmae) {
		this.mongoDbNmae = mongoDbNmae;
	}
	public String getMongoUri() {
		return mongoUri;
	}
	public void setMongoUri(String mongoUri) {
		this.mongoUri = mongoUri;
	}
	public String getPgUri() {
		return pgUri;
	}
	public void setPgUri(String pgUri) {
		this.pgUri = pgUri;
	}
	public String getPgUsername() {
		return pgUsername;
	}
	public void setPgUsername(String pgUsername) {
		this.pgUsername = pgUsername;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	public String getActiveReportTime() {
		return activeReportTime;
	}
	public void setActiveReportTime(String activeReportTime) {
		this.activeReportTime = activeReportTime;
	}

	
	
	
}
