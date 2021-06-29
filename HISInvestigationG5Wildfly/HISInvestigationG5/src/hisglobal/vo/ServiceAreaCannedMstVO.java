/**
 * ServiceAreaCannedMstVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */

package hisglobal.vo;

public class ServiceAreaCannedMstVO extends ValueObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serviceAreaCode;
	private String serviceAreaName;
	private String hospitalCode;
	private String procedureName;
	private String procedureCode;
	private String cannedFile;
	private String cannedText;
	private String location;
	private String cannedCode;
	private String remarks;
	private String isValid;
	private String isValidLabel;
	private String msg;
	private String tempCannedCode;
	private String tempServiceAreaCode;
	private String tempProcedureCode;
	
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getServiceAreaCode()
	{
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode)
	{
		this.serviceAreaCode = serviceAreaCode;
	}
	public String getServiceAreaName()
	{
		return serviceAreaName;
	}
	public void setServiceAreaName(String serviceAreaName)
	{
		this.serviceAreaName = serviceAreaName;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	public String getCannedFile() {
		return cannedFile;
	}
	public void setCannedFile(String cannedFile) {
		this.cannedFile = cannedFile;
	}
	public String getCannedText() {
		return cannedText;
	}
	public void setCannedText(String cannedText) {
		this.cannedText = cannedText;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCannedCode() {
		return cannedCode;
	}
	public void setCannedCode(String cannedCode) {
		this.cannedCode = cannedCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsValidLabel() {
		return isValidLabel;
	}
	public void setIsValidLabel(String isValidLabel) {
		this.isValidLabel = isValidLabel;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTempCannedCode() {
		return tempCannedCode;
	}
	public void setTempCannedCode(String tempCannedCode) {
		this.tempCannedCode = tempCannedCode;
	}
	public String getTempServiceAreaCode() {
		return tempServiceAreaCode;
	}
	public void setTempServiceAreaCode(String tempServiceAreaCode) {
		this.tempServiceAreaCode = tempServiceAreaCode;
	}
	public String getTempProcedureCode() {
		return tempProcedureCode;
	}
	public void setTempProcedureCode(String tempProcedureCode) {
		this.tempProcedureCode = tempProcedureCode;
	}
}
