package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LabMstFB extends ActionForm
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
	private String selectedChk;
	private String labNumberConfig;
	
	private String location;
	private String labIncharge;
	private String displayHeader;
	private String finalRemark;
	private String isTimeBound;
	private String startTimeHH;
	private String startTimeMM;
	private String endTimeHH;
	private String endTimeMM;

	private String istestmodify;

	private String isreportlablabelchanged;

	private String isreportcollectionlabelchanged;

	private String isreportsamplelabelrequired;

	private String isAppointment;

	private String isaptmand;

	private String isfilmused;
	private String resultentered;
	private String hidefromdesk;
	private String LabMstFB;
	private String accesstoaddendum;
	private String entryuser;
	private String validation;
	private String revalidation;

	private String sopbased;

	public String getSopbased() {
		return sopbased;
	}


	public void setSopbased(String sopbased) {
		this.sopbased = sopbased;
	}


	public String getLabMstFB() {
		return LabMstFB;
	}



	public void setLabMstFB(String labMstFB) {
		LabMstFB = labMstFB;
	}



	public String getAccesstoaddendum() {
		return accesstoaddendum;
	}

	public void setAccesstoaddendum(String accesstoaddendum) {
		this.accesstoaddendum = accesstoaddendum;
	}


	public String getHidefromdesk() {
		return hidefromdesk;
	}


	public void setHidefromdesk(String hidefromdesk) {
		this.hidefromdesk = hidefromdesk;
	}







	public String getResultentered() {
		return resultentered;
	}







	public void setResultentered(String resultentered) {
		this.resultentered = resultentered;
	}







	public String getIsaptmand() {
		return isaptmand;
	}







	public void setIsaptmand(String isaptmand) {
		this.isaptmand = isaptmand;
	}







	public String getIsAppointment() {
		return isAppointment;
	}







	public void setIsAppointment(String isAppointment) {
		this.isAppointment = isAppointment;
	}







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
		this.laboratoryName="";
		this.labShortSName="";
		this.footerText="";
		this.headertext="";
		this.labType="1";
		this.labWorkingDays="1111111";
		this.department="-1";
		this.labCode="-1";
		this.location="-1";
		this.labIncharge="-1";

		this.numberofTests="9999";
		this.sampleNumberConfig="1";
		this.labNumberConfig="1";

		this.remarks="";
		this.displayHeader="1";
		
		
	}
		
		
	
	
	
	
	
	public String getEntryuser() {
		return entryuser;
	}







	public void setEntryuser(String entryuser) {
		this.entryuser = entryuser;
	}







	public String getValidation() {
		return validation;
	}







	public void setValidation(String validation) {
		this.validation = validation;
	}







	public String getRevalidation() {
		return revalidation;
	}







	public void setRevalidation(String revalidation) {
		this.revalidation = revalidation;
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







	public String getSelectedChk() {
		return selectedChk;
	}







	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}







	public String getLabNumberConfig() {
		return labNumberConfig;
	}







	public void setLabNumberConfig(String labNumberConfig) {
		this.labNumberConfig = labNumberConfig;
	}







	public String getLocation() {
		return location;
	}







	public void setLocation(String location) {
		this.location = location;
	}







	public String getLabIncharge() {
		return labIncharge;
	}







	public void setLabIncharge(String labIncharge) {
		this.labIncharge = labIncharge;
	}







	public String getDisplayHeader() {
		return displayHeader;
	}







	public void setDisplayHeader(String displayHeader) {
		this.displayHeader = displayHeader;
	}







	public String getFinalRemark() {
		return finalRemark;
	}







	public void setFinalRemark(String finalRemark) {
		this.finalRemark = finalRemark;
	}
	
	public String getIsTimeBound() {
		return isTimeBound;
	}







	public void setIsTimeBound(String isTimeBound) {
		this.isTimeBound = isTimeBound;
	}
	public String getStartTimeHH() {
		return startTimeHH;
	}







	public void setStartTimeHH(String startTimeHH) {
		this.startTimeHH = startTimeHH;
	}







	public String getStartTimeMM() {
		return startTimeMM;
	}







	public void setStartTimeMM(String startTimeMM) {
		this.startTimeMM = startTimeMM;
	}







	public String getEndTimeHH() {
		return endTimeHH;
	}







	public void setEndTimeHH(String endTimeHH) {
		this.endTimeHH = endTimeHH;
	}







	public String getEndTimeMM() {
		return endTimeMM;
	}







	public void setEndTimeMM(String endTimeMM) {
		this.endTimeMM = endTimeMM;
	}







	public String getIstestmodify() {
		return istestmodify;
	}







	public void setIstestmodify(String istestmodify) {
		this.istestmodify = istestmodify;
	}







	public String getIsreportlablabelchanged() {
		return isreportlablabelchanged;
	}







	public void setIsreportlablabelchanged(String isreportlablabelchanged) {
		this.isreportlablabelchanged = isreportlablabelchanged;
	}







	public String getIsreportcollectionlabelchanged() {
		return isreportcollectionlabelchanged;
	}







	public void setIsreportcollectionlabelchanged(
			String isreportcollectionlabelchanged) {
		this.isreportcollectionlabelchanged = isreportcollectionlabelchanged;
	}







	public String getIsreportsamplelabelrequired() {
		return isreportsamplelabelrequired;
	}







	public void setIsreportsamplelabelrequired(String isreportsamplelabelrequired) {
		this.isreportsamplelabelrequired = isreportsamplelabelrequired;
	}







	public String getIsfilmused() {
		return isfilmused;
	}







	public void setIsfilmused(String isfilmused) {
		this.isfilmused = isfilmused;
	}
	
	







	







	

}
