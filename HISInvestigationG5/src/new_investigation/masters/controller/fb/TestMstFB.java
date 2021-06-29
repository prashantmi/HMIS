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
	private String ishidefromrequisitionraising;
	private String issugartest;
	private String sugartestcode;
	private String machinetest;
	private String turnaround_time;
	private String isconfidential;
	private  String anyinstruction;
	private String isextracharge;
	private String instType;
	private String fastingtime;
	private String fastingtype;
	private String blddrTxt;
	public String machinetestcode;
	public String getMachinetestcode() {
		return machinetestcode;
	}



	public void setMachinetestcode(String machinetestcode) {
		this.machinetestcode = machinetestcode;
	}



	//private String 
	public String getInstType() {
		return instType;
	}



	public String getFastingtime() {
		return fastingtime;
	}



	public void setFastingtime(String fastingtime) {
		this.fastingtime = fastingtime;
	}



	public String getFastingtype() {
		return fastingtype;
	}



	public void setFastingtype(String fastingtype) {
		this.fastingtype = fastingtype;
	}



	public String getBlddrTxt() {
		return blddrTxt;
	}



	public void setBlddrTxt(String blddrTxt) {
		this.blddrTxt = blddrTxt;
	}



	public void setInstType(String instType) {
		this.instType = instType;
	}



	public String getSugartestcode() {
		return sugartestcode;
	}



	public void setSugartestcode(String sugartestcode) {
		this.sugartestcode = sugartestcode;
	}



	public String getMachinetest() {
		return machinetest;
	}



	public void setMachinetest(String machinetest) {
		this.machinetest = machinetest;
	}



	public String getTurnaround_time() {
		return turnaround_time;
	}



	public void setTurnaround_time(String turnaround_time) {
		this.turnaround_time = turnaround_time;
	}



	public String getIsconfidential() {
		return isconfidential;
	}



	public void setIsconfidential(String isconfidential) {
		this.isconfidential = isconfidential;
	}



	public String getAnyinstruction() {
		return anyinstruction;
	}



	public void setAnyinstruction(String anyinstruction) {
		this.anyinstruction = anyinstruction;
	}



	public String getIsextracharge() {
		return isextracharge;
	}



	public void setIsextracharge(String isextracharge) {
		this.isextracharge = isextracharge;
	}



	public String getIssugartest() {
		return issugartest;
	}



	public void setIssugartest(String issugartest) {
		this.issugartest = issugartest;
	}



	public String getIshidefromrequisitionraising() {
		return ishidefromrequisitionraising;
	}



	public void setIshidefromrequisitionraising(String ishidefromrequisitionraising) {
		this.ishidefromrequisitionraising = ishidefromrequisitionraising;
	}



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
		 this. ishidefromrequisitionraising="1";
		 this.issugartest="0";
		 this. sugartestcode="";
		 this. machinetest ="0";
		 this. turnaround_time ="";
		 this. isconfidential="0";
		 this. anyinstruction="0";
		 this. isextracharge="0";
		 this. instType="0";
		 this. fastingtime="0";
		 this.fastingtype="0";
		 this.blddrTxt="";
		 this. machinetestcode="0";
		   
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
