package auditlogClient.vo;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author siddharthdixit
 *
 */
@XmlRootElement
public class AuditLogVO extends ValueObject{
	
	// develeper had to set following variable
	
	public AuditLogVO() {		
	}
		
	public AuditLogVO(String strModuleId , String strProcessId) {
	
		this.strModuleId = strModuleId;
		this.strProcessId = strProcessId;
	}
	
	
	
	private String strModuleId; 
	private String strProcessId;
	
	String[] arrKeyVariablesValue;
	private String  keyVariableValue;
	
	//--------------------------------------------------------------
	
	
	private String hospitalCode;	
	private String seatId;
	private String userName;
	private String strInitVariableJSON;
	private String strVariableJSON;	
	private String entryDate;
	private String strModuleName;
	private String strProcessName;
	
	/* put following record status
	 * 1 - insert audit
	 * 2-  update audit
	 * 0-  delete audit
	 */
	private String recordStatus;
	private String fromDate;
	private String toDate;
	private String sqNo;
	
	private String keyVariableName;
	
	int isInsertLogOn;
	int isUpdateLogOn;
	int isDeleteLogOn;
	
	Map mpCompare;
	
	
	
	
	
	
	public Map getMpCompare() {
		return mpCompare;
	}

	public void setMpCompare(Map mpCompare) {
		this.mpCompare = mpCompare;
	}

	public String getKeyVariableValue() {
		return keyVariableValue;
	}

	public void setKeyVariableValue(String keyVariableValue) {
		this.keyVariableValue = keyVariableValue;
	}

	public int getIsInsertLogOn() {
		return isInsertLogOn;
	}
	public void setIsInsertLogOn(int isInsertLogOn) {
		this.isInsertLogOn = isInsertLogOn;
	}
	public int getIsUpdateLogOn() {
		return isUpdateLogOn;
	}
	public void setIsUpdateLogOn(int isUpdateLogOn) {
		this.isUpdateLogOn = isUpdateLogOn;
	}
	public int getIsDeleteLogOn() {
		return isDeleteLogOn;
	}
	public void setIsDeleteLogOn(int isDeleteLogOn) {
		this.isDeleteLogOn = isDeleteLogOn;
	}
	
	public String getKeyVariableName() {
		return keyVariableName;
	}
	public void setKeyVariableName(String keyVariableName) {
		this.keyVariableName = keyVariableName;
	}
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrProcessId() {
		return strProcessId;
	}
	public void setStrProcessId(String strProcessId) {
		this.strProcessId = strProcessId;
	}
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStrInitVariableJSON() {
		return strInitVariableJSON;
	}
	public void setStrInitVariableJSON(String strInitVariableJSON) {
		this.strInitVariableJSON = strInitVariableJSON;
	}
	public String getStrVariableJSON() {
		return strVariableJSON;
	}
	public void setStrVariableJSON(String strVariableJSON) {
		this.strVariableJSON = strVariableJSON;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getStrModuleName() {
		return strModuleName;
	}
	public void setStrModuleName(String strModuleName) {
		this.strModuleName = strModuleName;
	}
	public String getStrProcessName() {
		return strProcessName;
	}
	public void setStrProcessName(String strProcessName) {
		this.strProcessName = strProcessName;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
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
	public String getSqNo() {
		return sqNo;
	}
	public void setSqNo(String sqNo) {
		this.sqNo = sqNo;
	}

	public String[] getArrKeyVariablesValue() {
		return arrKeyVariablesValue;
	}

	public void setArrKeyVariablesValue(String[] arrKeyVariablesValue) {
		this.arrKeyVariablesValue = arrKeyVariablesValue;
	}		
	
}
