package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LabTestMstFB extends ActionForm
{
	
	private String checklistID;
	private String slNO;
	private String checklist;
	private String isCompulsory;
	private String hmode;
	private String chk[];
	private String checklistPrevious;
	 
	
	private String labCode;
	private String hospitalCode;
	private String laboratoryName;
	private String labShortSName;
	private String department;
	private String sampStartHr;
	private String sampStartMin;
	private String sampEndHr;
	private String sampEndMin;
	private String labType;
	private String labWorkingDays;
	private String numberofTests;
	private String headertext;
	private String footerText;
	private String remarks;
	private String isActive;
	private String sampleCStrtTime;
	private String sampleCEndTime;
	private String deptCode;
	private String systemDate;
	private String systemTimeInHr;
	private String systemTimeInMin;
	private String time; 
	private String systemTime; 
	private String PreviousLaboratoryName;
	private String sampleNumberConfig;
	 




	 

	public String getSampleNumberConfig() {
		return sampleNumberConfig;
	}







	public void setSampleNumberConfig(String sampleNumberConfig) {
		this.sampleNumberConfig = sampleNumberConfig;
	}







	public String getPreviousLaboratoryName() {
		return PreviousLaboratoryName;
	}







	public void setPreviousLaboratoryName(String previousLaboratoryName) {
		PreviousLaboratoryName = previousLaboratoryName;
	}







	public String getSystemTime() {
		return systemTime;
	}







	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}







	public String getTime() {
		return time;
	}







	public void setTime(String time) {
		this.time = time;
	}







	public String getSystemDate() {
		return systemDate;
	}







	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
	}







	public String getSystemTimeInHr() {
		return systemTimeInHr;
	}







	public void setSystemTimeInHr(String systemTimeInHr) {
		this.systemTimeInHr = systemTimeInHr;
	}







	public String getSystemTimeInMin() {
		return systemTimeInMin;
	}







	public void setSystemTimeInMin(String systemTimeInMin) {
		this.systemTimeInMin = systemTimeInMin;
	}







	public String getDeptCode() {
		return deptCode;
	}







	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}







	public String getSampleCStrtTime() {
		return sampleCStrtTime;
	}







	public void setSampleCStrtTime(String sampleCStrtTime) {
		this.sampleCStrtTime = sampleCStrtTime;
	}







	public String getSampleCEndTime() {
		return sampleCEndTime;
	}







	public void setSampleCEndTime(String sampleCEndTime) {
		this.sampleCEndTime = sampleCEndTime;
	}







	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.checklist="";
		this.isCompulsory="0";
		
		 
		this.sampStartHr ="";
		this.sampStartMin ="";
		this.sampEndHr ="";
		this.sampEndMin ="";
	}
		
		
	
	
	
	
	
	public String getLabCode() {
		return labCode;
	}







	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}







	public String getHospitalCode() {
		return hospitalCode;
	}







	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}







	public String getLaboratoryName() {
		return laboratoryName;
	}







	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}







	public String getLabShortSName() {
		return labShortSName;
	}







	public void setLabShortSName(String labShortSName) {
		this.labShortSName = labShortSName;
	}







	public String getDepartment() {
		return department;
	}







	public void setDepartment(String department) {
		this.department = department;
	}







	public String getSampStartHr() {
		return sampStartHr;
	}







	public void setSampStartHr(String sampStartHr) {
		this.sampStartHr = sampStartHr;
	}







	public String getSampStartMin() {
		return sampStartMin;
	}







	public void setSampStartMin(String sampStartMin) {
		this.sampStartMin = sampStartMin;
	}







	public String getSampEndHr() {
		return sampEndHr;
	}







	public void setSampEndHr(String sampEndHr) {
		this.sampEndHr = sampEndHr;
	}







	public String getSampEndMin() {
		return sampEndMin;
	}







	public void setSampEndMin(String sampEndMin) {
		this.sampEndMin = sampEndMin;
	}







	public String getLabType() {
		return labType;
	}







	public void setLabType(String labType) {
		this.labType = labType;
	}







	public String getLabWorkingDays() {
		return labWorkingDays;
	}







	public void setLabWorkingDays(String labWorkingDays) {
		this.labWorkingDays = labWorkingDays;
	}







	public String getNumberofTests() {
		return numberofTests;
	}







	public void setNumberofTests(String numberofTests) {
		this.numberofTests = numberofTests;
	}







	public String getHeadertext() {
		return headertext;
	}







	public void setHeadertext(String headertext) {
		this.headertext = headertext;
	}







	public String getFooterText() {
		return footerText;
	}







	public void setFooterText(String footerText) {
		this.footerText = footerText;
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

}
