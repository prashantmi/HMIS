package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InvSampleMstFB extends ActionForm
{
	
	private String checklistID;
	private String slNO;
	private String checklist;
	private String isCompulsory;
	private String hmode;
	private String chk[];
	private String checklistPrevious;
	private String loincSystem;
	private String sampleName;
	private String sampleSName;
	private String remarks;
	private String sampleCode;
	private String valid_invalid;
	private String previousSampleName;
	private String selectedChk;
	
	public String getPreviousSampleName() {
		return previousSampleName;
	}



	public void setPreviousSampleName(String previousSampleName) {
		this.previousSampleName = previousSampleName;
	}
	private String isActive;
	
	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public String getValid_invalid() {
		return valid_invalid;
	}



	public void setValid_invalid(String valid_invalid) {
		this.valid_invalid = valid_invalid;
	}



	public String getLoincSys() {
		return loincSys;
	}



	public void setLoincSys(String loincSys) {
		this.loincSys = loincSys;
	}
	private String loincSys;
	
	public String getSampleCode() {
		return sampleCode;
	}



	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getLoincSystem() {
		return loincSystem;
	}



	public void setLoincSystem(String loincSystem) {
		this.loincSystem = loincSystem;
	}



	public String getSampleName() {
		return sampleName;
	}



	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}



	public String getSampleSName() {
		return sampleSName;
	}



	public void setSampleSName(String sampleSName) {
		this.sampleSName = sampleSName;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.checklist="";
		this.isCompulsory="0";
		 this.sampleName="";
	  this.sampleSName="";
	  this.loincSystem="-1";
	  this.remarks="";
		  
		
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

}
