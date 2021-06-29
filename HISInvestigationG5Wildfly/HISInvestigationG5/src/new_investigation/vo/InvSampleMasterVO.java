package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class InvSampleMasterVO extends ValueObject 
{
	private String checklistID;
	private String slNO;
	private String checklist;
	private String isCompulsory;
	private String checklistPrevious;
	private String loincSystem;
	private String sampleName;
	private String sampleSName;
	private String remarks;
	private String previousSampleName;
	private String selectedChk;
	
	 public String getPreviousSampleName() {
		return previousSampleName;
	}
	public void setPreviousSampleName(String previousSampleName) {
		this.previousSampleName = previousSampleName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	private String isActive;
	
	private String sampleCode;
	
	
    public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
