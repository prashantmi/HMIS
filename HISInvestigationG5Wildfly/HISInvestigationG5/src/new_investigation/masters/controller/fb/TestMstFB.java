package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TestMstFB extends ActionForm
{
	
	private String checklistID;
	private String slNO;
	private String checklist;
	private String isCompulsory;
	private String hmode;
	private String chk[];
	private String checklistPrevious;
	 
	
	private String isActive;
	
	private String testCode;
	private String testName;
	private String testSName;
	private String testDescription;
	private String testType;
	private String loincTiming;
	private String selectedChk;
	
	private String printedWith;
	private String resultEntryForm;
	private String printingTemplateCode;
	
	
	private String departmentPrintedWith;
	private String departmentResultEntryForm;
	private String departmentPrintingTemplateCode;
	private String reqMasterFormType;
	private String isreportrequiredonseperatepage;
	private String isreportupload;
	private String isfrontpage;
	
	
	public String getIsfrontpage() {
		return isfrontpage;
	}



	public void setIsfrontpage(String isfrontpage) {
		this.isfrontpage = isfrontpage;
	}



	public String getIsreportupload() {
		return isreportupload;
	}



	public void setIsreportupload(String isreportupload) {
		this.isreportupload = isreportupload;
	}



	public String getIsreportrequiredonseperatepage() {
		return isreportrequiredonseperatepage;
	}



	public void setIsreportrequiredonseperatepage(
			String isreportrequiredonseperatepage) {
		this.isreportrequiredonseperatepage = isreportrequiredonseperatepage;
	}



	public String getPrintedWith() {
		return printedWith;
	}



	public void setPrintedWith(String printedWith) {
		this.printedWith = printedWith;
	}



	public String getResultEntryForm() {
		return resultEntryForm;
	}



	public void setResultEntryForm(String resultEntryForm) {
		this.resultEntryForm = resultEntryForm;
	}



	public String getTestCode() {
		return testCode;
	}



	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}



	public String getTestName() {
		return testName;
	}



	public void setTestName(String testName) {
		this.testName = testName;
	}



	public String getTestSName() {
		return testSName;
	}



	public void setTestSName(String testSName) {
		this.testSName = testSName;
	}



	public String getTestDescription() {
		return testDescription;
	}



	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}



	public String getTestType() {
		return testType;
	}



	public void setTestType(String testType) {
		this.testType = testType;
	}



	public String getLoincTiming() {
		return loincTiming;
	}



	public void setLoincTiming(String loincTiming) {
		this.loincTiming = loincTiming;
	}
	 
	 
	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	 



	 
	 



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		 this.testName="";
		 this.testSName="";
		 this.testType="S";
		 this.loincTiming="-1";
		 this.testDescription="";
		 this.printingTemplateCode="-1";
		 this.printedWith="0";
		 this.resultEntryForm="0";
		 this.departmentPrintingTemplateCode="-1";
		 this.departmentPrintedWith="0";
		 this.departmentResultEntryForm="0";
		
		   
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



	public String getPrintingTemplateCode() {
		return printingTemplateCode;
	}



	public void setPrintingTemplateCode(String printingTemplateCode) {
		this.printingTemplateCode = printingTemplateCode;
	}




	public String getDepartmentResultEntryForm() {
		return departmentResultEntryForm;
	}



	public void setDepartmentResultEntryForm(String departmentResultEntryForm) {
		this.departmentResultEntryForm = departmentResultEntryForm;
	}



	public String getDepartmentPrintingTemplateCode() {
		return departmentPrintingTemplateCode;
	}



	public void setDepartmentPrintingTemplateCode(
			String departmentPrintingTemplateCode) {
		this.departmentPrintingTemplateCode = departmentPrintingTemplateCode;
	}



	public String getDepartmentPrintedWith() {
		return departmentPrintedWith;
	}



	public void setDepartmentPrintedWith(String departmentPrintedWith) {
		this.departmentPrintedWith = departmentPrintedWith;
	}



	public String getReqMasterFormType() {
		return reqMasterFormType;
	}



	public void setReqMasterFormType(String reqMasterFormType) {
		this.reqMasterFormType = reqMasterFormType;
	}





}
