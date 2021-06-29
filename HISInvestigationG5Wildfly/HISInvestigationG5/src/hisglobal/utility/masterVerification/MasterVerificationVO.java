package hisglobal.utility.masterVerification;

import hisglobal.vo.ValueObject;

public class MasterVerificationVO extends ValueObject
{
	private String moduleId;
	private String moduleName;
	private String mainHeader;
	private String subHeader;
	private String columnHeader; 
	private String footerHeader; 
	private String columnSubHeader; 
	private String mainQuery;
	private String criteriaLabel;
	private String criteriaQuery;
	private String groupQuery;
	
	private String criteriaLabel1;
	private String criteriaColumn1;
	private String criteriaQuery1;
	private String orderOption;
	private String hospitalCode;
	private String serialNo;
	
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
	public String getMainHeader() {
		return mainHeader;
	}
	public void setMainHeader(String mainHeader) {
		this.mainHeader = mainHeader;
	}
	public String getSubHeader() {
		return subHeader;
	}
	public void setSubHeader(String subHeader) {
		this.subHeader = subHeader;
	}
	public String getFooterHeader() {
		return footerHeader;
	}
	public void setFooterHeader(String footerHeader) {
		this.footerHeader = footerHeader;
	}
	public String getMainQuery() {
		return mainQuery;
	}
	public void setMainQuery(String mainQuery) {
		this.mainQuery = mainQuery;
	}
	public String getOrderOption() {
		return orderOption;
	}
	public void setOrderOption(String orderOption) {
		this.orderOption = orderOption;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getCriteriaLabel1() {
		return criteriaLabel1;
	}
	public void setCriteriaLabel1(String criteriaLabel1) {
		this.criteriaLabel1 = criteriaLabel1;
	}
	public String getCriteriaColumn1() {
		return criteriaColumn1;
	}
	public void setCriteriaColumn1(String criteriaColumn1) {
		this.criteriaColumn1 = criteriaColumn1;
	}
	public String getCriteriaQuery1() {
		return criteriaQuery1;
	}
	public void setCriteriaQuery1(String criteriaQuery1) {
		this.criteriaQuery1 = criteriaQuery1;
	}
	public String getGroupQuery() {
		return groupQuery;
	}
	public void setGroupQuery(String groupQuery) {
		this.groupQuery = groupQuery;
	}
	public String getColumnSubHeader() {
		return columnSubHeader;
	}
	public void setColumnSubHeader(String columnSubHeader) {
		this.columnSubHeader = columnSubHeader;
	}
	
	public String getColumnHeader() {
		return columnHeader;
	}
	public void setColumnHeader(String columnHeader) {
		this.columnHeader = columnHeader;
	}
	public String getCriteriaLabel() {
		return criteriaLabel;
	}
	public void setCriteriaLabel(String criteriaLabel) {
		this.criteriaLabel = criteriaLabel;
	}
	public String getCriteriaQuery() {
		return criteriaQuery;
	}
	public void setCriteriaQuery(String criteriaQuery) {
		this.criteriaQuery = criteriaQuery;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

		
}
