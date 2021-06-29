package hisglobal.utility.masterVerification;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MasterVerificationFB extends ActionForm{

	 private String hmode;
	 private String moduleId; 
	 private String moduleName; 
	 private String mainHeader; 
	 private String Query; 
	 private String columnName;
	 private String orderBy[];
	 private String orderByString;
	 private String criteriaCode;
	 private String criteriaValue;
	 private String criteriaLabel;
	 private String criteriaColumn;
	 private String isGrouped;
	 private String htmlContent;
	 private String byteArray;
	 private String isActive;
	
	
	public String getHtmlContent() {
		return htmlContent;
	}


	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.criteriaValue="";
		this.criteriaLabel="";
		this.criteriaColumn="";
		this.criteriaCode="-1";
		this.isGrouped="0";
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
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


	public String getMainHeader() {
		return mainHeader;
	}


	public void setMainHeader(String mainHeader) {
		this.mainHeader = mainHeader;
	}


	public String getQuery() {
		return Query;
	}


	public void setQuery(String query) {
		Query = query;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public String[] getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String[] orderBy) {
		this.orderBy = orderBy;
	}


	public String getOrderByString() {
		return orderByString;
	}


	public void setOrderByString(String orderByString) {
		this.orderByString = orderByString;
	}


	public String getByteArray() {
		return byteArray;
	}


	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}


	public String getCriteriaCode() {
		return criteriaCode;
	}


	public void setCriteriaCode(String criteriaCode) {
		this.criteriaCode = criteriaCode;
	}


	public String getCriteriaValue() {
		return criteriaValue;
	}


	public void setCriteriaValue(String criteriaValue) {
		this.criteriaValue = criteriaValue;
	}


	public String getCriteriaLabel() {
		return criteriaLabel;
	}


	public void setCriteriaLabel(String criteriaLabel) {
		this.criteriaLabel = criteriaLabel;
	}


	public String getCriteriaColumn() {
		return criteriaColumn;
	}


	public void setCriteriaColumn(String criteriaColumn) {
		this.criteriaColumn = criteriaColumn;
	}


	public String getIsGrouped() {
		return isGrouped;
	}


	public void setIsGrouped(String isGrouped) {
		this.isGrouped = isGrouped;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
	
}
