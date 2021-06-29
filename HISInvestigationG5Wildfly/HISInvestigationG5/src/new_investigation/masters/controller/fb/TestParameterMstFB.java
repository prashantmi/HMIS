package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TestParameterMstFB extends ActionForm
{
	
	private String checklistID;
	private String slNO;
	private String checklist;
	private String isCompulsory;
	private String hmode;
	private String chk[];
	private String checklistPrevious;
	 
	
	 
	private String remarks;
	private String isActive;
	 
	 
	private String testCode;
	private String elementType;
	private String parameterCode;
	private String arrangeAs;
	private String parentId;
	private String printFlag;
	 
	private String criteriaCode;
   private String functionName;	
   private String testName;
   private String parameterName;
   private String criteriaDesk;
   private String parentParameter;
   private String isPrintRequired;
   private String elementPosition;
   private String labelAlignment;
	private String bold;
	private String underline;
    private String showParameterNameasLabel;
	 private String elementAlign;
	 private String rows;
	 private String columns;
	 private String event;
	 private String eventFunction;
	 private String maxlength;
	 private String size;
	 private String callingURL;
	 private String buttonName;
	 private String query;
	 private String elementProperty;
	 private String  selectedChk;
	 private String loincScale;
	 private boolean showDefaultValue;
	 private String textEditorValue;
	 private String editor1;
	 private String defaultTextValue; //For Default Value of TextArea, not in use
	 private String defaultTextBoxValue;
	 private String hyperName;
	 private String urlCode;
	 private String functionality;
	 private String paraType;
	 
	 
	 //added by chandan
	 private String reqMasterFormType;
	 private String mastertestCode;
	 private String testCodee;
	 private String masterreqformcheck="0";
	 
	 
	 
	public String getFunctionality() {
		return functionality;
	}


	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}


	public String getDefaultTextBoxValue() {
		return defaultTextBoxValue;
	}


	public void setDefaultTextBoxValue(String defaultTextBoxValue) {
		this.defaultTextBoxValue = defaultTextBoxValue;
	}


	public String getDefaultTextValue() {
		return defaultTextValue;
	}


	public void setDefaultTextValue(String defaultTextValue) {
		this.defaultTextValue = defaultTextValue;
	}


	public String getEditor1() {
		return editor1;
	}


	public void setEditor1(String editor1) {
		this.editor1 = editor1;
	}


	public String getTextEditorValue() {
		return textEditorValue;
	}


	public void setTextEditorValue(String textEditorValue) {
		this.textEditorValue = textEditorValue;
	}


	public boolean isShowDefaultValue() {
		return showDefaultValue;
	}


	public void setShowDefaultValue(boolean showDefaultValue) {
		this.showDefaultValue = showDefaultValue;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.checklist="";
		this.isCompulsory="0";
		this.elementType="-1";
		this.testCode="-1";
		this.parameterCode="-1";
		this.parentParameter="-1";
		this.criteriaDesk="-1";
		this.isPrintRequired="0";
		this.remarks="";
		 this.elementPosition="";
		 this.loincScale="-1";
		 this.showDefaultValue = false;
		 this.showParameterNameasLabel="1";
		 this.defaultTextValue="";
		 this.defaultTextBoxValue="";
		 this.bold="0";
		 this.underline="0";
		 this.rows="";
		 this.columns="";
		 this.labelAlignment="0";
		 this.elementAlign="0";
		 this.event="0";
		 this.eventFunction="";
		 this.textEditorValue="";
		 this.callingURL="0";
		 this.buttonName="";
		 this.query="";
		 this.size="";
		 this.maxlength="";
		 this.paraType="0";
		 this.functionality=null;
		 
		 super.reset(mapping, request);
	 
	}
		
	 
	public String getElementProperty() {
		return elementProperty;
	}


	public void setElementProperty(String elementProperty) {
		this.elementProperty = elementProperty;
	}


	public String getQuery() {
		return query;
	}

 
	public void setQuery(String query) {
		this.query = query;
	}

 
	public String getCallingURL() {
		return callingURL;
	}

 
	public void setCallingURL(String callingURL) {
		this.callingURL = callingURL;
	}


 

	public String getButtonName() {
		return buttonName;
	}

 



	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}


 
	public String getMaxlength() {
		return maxlength;
	}

 
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

 
	public String getSize() {
		return size;
	}


 

	public void setSize(String size) {
		this.size = size;
	}



 
	public String getShowParameterNameasLabel() {
		return showParameterNameasLabel;
	}



 

	public void setShowParameterNameasLabel(String showParameterNameasLabel) {
		this.showParameterNameasLabel = showParameterNameasLabel;
	}

 

	public String getElementAlign() {
		return elementAlign;
	}

 
	public void setElementAlign(String elementAlign) {
		this.elementAlign = elementAlign;
	}

 
	public String getRows() {
		return rows;
	}

 

	public void setRows(String rows) {
		this.rows = rows;
	}
 

	public String getColumns() {
		return columns;
	}

 
	public void setColumns(String columns) {
		this.columns = columns;
	}

 

	public String getEvent() {
		return event;
	}

 
	public void setEvent(String event) {
		this.event = event;
	}

 

	public String getEventFunction() {
		return eventFunction;
	}

 

	public void setEventFunction(String eventFunction) {
		this.eventFunction = eventFunction;
	}

 
	public String getLabelAlignment() {
		return labelAlignment;
	}
 
	public void setLabelAlignment(String labelAlignment) {
		this.labelAlignment = labelAlignment;
	}
 
	public String getBold() {
		return bold;
	}

 
	public void setBold(String bold) {
		this.bold = bold;
	}

 
	public String getUnderline() {
		return underline;
	}

 
	public void setUnderline(String underline) {
		this.underline = underline;
	}

 

	public String getElementPosition() {
		return elementPosition;
	}
 
	public void setElementPosition(String elementPosition) {
		this.elementPosition = elementPosition;
	}

 
	public String getIsPrintRequired() {
		return isPrintRequired;
	}

 
	public void setIsPrintRequired(String isPrintRequired) {
		this.isPrintRequired = isPrintRequired;
	}

 
	public String getParentParameter() {
		return parentParameter;
	}

 
	public void setParentParameter(String parentParameter) {
		this.parentParameter = parentParameter;
	}
 
	public String getTestCode() {
		return testCode;
	}

public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
 
	public String getElementType() {
		return elementType;
	}


	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
 
	public String getArrangeAs() {
		return arrangeAs;
	}
 
	public void setArrangeAs(String arrangeAs) {
		this.arrangeAs = arrangeAs;
	}

 
	public String getParentId() {
		return parentId;
	}

 
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

 
	public String getPrintFlag() {
		return printFlag;
	}
 
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

 
	public String getCriteriaCode() {
		return criteriaCode;
	}

 
	public void setCriteriaCode(String criteriaCode) {
		this.criteriaCode = criteriaCode;
	}

 
	public String getFunctionName() {
		return functionName;
	}

 
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

 
	public String getTestName() {
		return testName;
	}

 
	public void setTestName(String testName) {
		this.testName = testName;
	}

 
	public String getParameterName() {
		return parameterName;
	}
 
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

 
	public String getCriteriaDesk() {
		return criteriaDesk;
	}

 
	public void setCriteriaDesk(String criteriaDesk) {
		this.criteriaDesk = criteriaDesk;
	}

 
	public String getRemarks() {
		return remarks;
	}
 
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

 
	public String getIsActive() {
		return isActive;
	}
 
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
 
	public String getChecklistID()
	{
		return checklistID;
	}
	public void setChecklistID(String checklistID)
	{
		this.checklistID = checklistID;
	}
	public String getSlNO()
	{
		return slNO;
	}
	public void setSlNO(String slNO)
	{
		this.slNO = slNO;
	}
	public String getChecklist()
	{
		return checklist;
	}
	public void setChecklist(String checklist)
	{
		this.checklist = checklist;
	}
	public String getIsCompulsory()
	{
		return isCompulsory;
	}
	public void setIsCompulsory(String isCompulsory)
	{
		this.isCompulsory = isCompulsory;
	}
	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
	}
	public String getChecklistPrevious()
	{
		return checklistPrevious;
	}
	public void setChecklistPrevious(String checklistPrevious)
	{
		this.checklistPrevious = checklistPrevious;
	}


	public String getSelectedChk() {
		return selectedChk;
	}


	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}


	public String getLoincScale() {
		return loincScale;
	}


	public void setLoincScale(String loincScale) {
		this.loincScale = loincScale;
	}


	public String getUrlCode() {
		return urlCode;
	}


	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}


	public String getHyperName() {
		return hyperName;
	}


	public void setHyperName(String hyperName) {
		this.hyperName = hyperName;
	}


	public String getParaType() {
		return paraType;
	}


	public void setParaType(String paraType) {
		this.paraType = paraType;
	}


	public String getReqMasterFormType() {
		return reqMasterFormType;
	}


	public void setReqMasterFormType(String reqMasterFormType) {
		this.reqMasterFormType = reqMasterFormType;
	}


	public String getMastertestCode() {
		return mastertestCode;
	}


	public void setMastertestCode(String mastertestCode) {
		this.mastertestCode = mastertestCode;
	}


	public String getMasterreqformcheck() {
		return masterreqformcheck;
	}


	public void setMasterreqformcheck(String masterreqformcheck) {
		this.masterreqformcheck = masterreqformcheck;
	}


	public String getTestCodee() {
		return testCodee;
	}


	public void setTestCodee(String testCodee) {
		this.testCodee = testCodee;
	}


	 


	 

}
