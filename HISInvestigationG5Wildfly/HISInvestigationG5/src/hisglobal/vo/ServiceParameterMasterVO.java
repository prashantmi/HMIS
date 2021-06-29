package hisglobal.vo;

public class ServiceParameterMasterVO extends ValueObject
{
	private String paraCode;
	private String serviceAreaCode;
	private String[] procedureCode;
	private String serviceAreaName;
	private String paraType;
	private String paraTypeLabel;
	private String comboQry;
	private String paraName;
	private String procedureName;
	private String isActive;
	private String isActiveLabel;
	private String hospitalCode;
	private String comboString;
	private String paraValue1;
		
	public String getParaCode() {
		return paraCode;
	}
	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}	
	public String getProcedureName() {
		return procedureName;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public String getComboQry() {
		return comboQry;
	}
	public void setComboQry(String comboQry) {
		this.comboQry = comboQry;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getServiceAreaName()
	{
		return serviceAreaName;
	}
	public void setServiceAreaName(String serviceAreaName)
	{
		this.serviceAreaName = serviceAreaName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getServiceAreaCode() {
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}
	public String[] getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String[] procedureCode) {
		this.procedureCode = procedureCode;
	}
	public String getIsActiveLabel() {
		return isActiveLabel;
	}
	public void setIsActiveLabel(String isActiveLabel) {
		this.isActiveLabel = isActiveLabel;
	}
	public String getParaTypeLabel() {
		return paraTypeLabel;
	}
	public void setParaTypeLabel(String paraTypeLabel) {
		this.paraTypeLabel = paraTypeLabel;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getComboString() {
		return comboString;
	}
	public void setComboString(String comboString) {
		this.comboString = comboString;
	}
	public String getParaValue1() {
		return paraValue1;
	}
	public void setParaValue1(String paraValue1) {
		this.paraValue1 = paraValue1;
	}
	
}
