package hisglobal.utility.masterVerification;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MasterVerificationMstFB extends ActionForm{

	 private String hmode;
	 private String moduleId; 
	 private String moduleName; 
	 private String masterName; 
	 private String mainHeader; 
	 private String subHeader; 
	 private String columnHeader; 
	 private String footerHeader; 
	 private String columnSubHeader; 
	 private String mainQuery;
	 private String orderBy;
	 private String criteriaLabel;
	 private String criteriaColumn;
	 private String criteriaQuery;
	 private String groupQuery;
	 private String columnHeaderString;
	 private String orderByString;
	 private String serialNo;
	 private String chk[];
	 
	 private String Query; 
	 private String columnName;
	 private String columnValue;
	 private String criteriaCode;
	 private String criteriaValue;
	 private String conditionValue;
	 private String isGrouped;
	 private String htmlContent;
	 private String columnQuery="";
	 private String byteArray;
	 private String whereConditionColumn;
	 private String whereConditionQuery;
	
	
	 public void reset(ActionMapping mapping,HttpServletRequest request)
	 {
		 super.reset(mapping, request);
		 this.moduleId="-1";
		 this.mainHeader="";
		 this.columnHeader="";
		 this.columnSubHeader="";
		 this.mainQuery="";
		 this.orderBy="";
		 this.criteriaLabel="";
		 this.criteriaColumn="";
		 this.criteriaQuery="";
		 this.groupQuery="";
		 this.columnHeaderString="";
		 this.orderByString="";
	 }
	 
	public String getHtmlContent() {
		return htmlContent;
	}


	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
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


	public String getMasterName() {
		return masterName;
	}


	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}


	public String getColumnValue() {
		return columnValue;
	}


	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}


	public String getCriteriaQuery() {
		return criteriaQuery;
	}


	public void setCriteriaQuery(String criteriaQuery) {
		this.criteriaQuery = criteriaQuery;
	}


	public String getConditionValue() {
		return conditionValue;
	}


	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}


	public String getColumnQuery() {
		return columnQuery;
	}


	public void setColumnQuery(String columnQuery) {
		this.columnQuery = columnQuery;
	}

	public String getWhereConditionColumn() {
		return whereConditionColumn;
	}

	public void setWhereConditionColumn(String whereConditionColumn) {
		this.whereConditionColumn = whereConditionColumn;
	}

	public String getWhereConditionQuery() {
		return whereConditionQuery;
	}

	public void setWhereConditionQuery(String whereConditionQuery) {
		this.whereConditionQuery = whereConditionQuery;
	}

	public String getMainQuery() {
		return mainQuery;
	}

	public void setMainQuery(String mainQuery) {
		this.mainQuery = mainQuery;
	}

	public String getFooterHeader() {
		return footerHeader;
	}

	public void setFooterHeader(String footerHeader) {
		this.footerHeader = footerHeader;
	}

	public String getColumnSubHeader() {
		return columnSubHeader;
	}

	public void setColumnSubHeader(String columnSubHeader) {
		this.columnSubHeader = columnSubHeader;
	}

	public String getGroupQuery() {
		return groupQuery;
	}

	public void setGroupQuery(String groupQuery) {
		this.groupQuery = groupQuery;
	}

	public String getSubHeader() {
		return subHeader;
	}

	public void setSubHeader(String subHeader) {
		this.subHeader = subHeader;
	}

	public String getColumnHeader() {
		return columnHeader;
	}

	public void setColumnHeader(String columnHeader) {
		this.columnHeader = columnHeader;
	}

	public String getColumnHeaderString() {
		return columnHeaderString;
	}

	public void setColumnHeaderString(String columnHeaderString) {
		this.columnHeaderString = columnHeaderString;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	
	
}
