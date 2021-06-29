package ehr.visitreason.dataentry;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import registration.RegistrationConfig;

public class EHRSection_VisitReasonFB extends ActionForm{

	private String hmode;
	private String patCrNo;
	
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;

    private String departmentUnitCode;
	private String deskType;   
	private String entryDate;

	//private String visitSummary;
	private String visitNotes;		// Visit Progress Notes
	private String ehrVisitReason;
	private String snomdPTVisitReason;
	private String snomdCIdVisitReason;
	private String isSet_OPDNEXTVISITDETAIL;
	private String presentIllnessHistory;

	
	public EHRSection_VisitReasonFB()
	{
	
		this.visitNotes = "";
		this.entryDate = "";
		this.visitNotes ="";
		this.isSet_OPDNEXTVISITDETAIL="0";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
	
		this.visitNotes = "";
		this.entryDate = "";
		this.visitNotes ="";
		
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}



	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	
	public String getVisitNotes()
	{
		return visitNotes;
	}

	public void setVisitNotes(String visitNotes)
	{
		this.visitNotes = visitNotes;
	}

	

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}



	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}




	public String getEhrVisitReason() {
		return ehrVisitReason;
	}

	public void setEhrVisitReason(String ehrVisitReason) {
		this.ehrVisitReason = ehrVisitReason;
	}

	

	public String getSnomdPTVisitReason() {
		return snomdPTVisitReason;
	}

	public void setSnomdPTVisitReason(String snomdPTVisitReason) {
		this.snomdPTVisitReason = snomdPTVisitReason;
	}

	public String getSnomdCIdVisitReason() {
		return snomdCIdVisitReason;
	}

	public void setSnomdCIdVisitReason(String snomdCIdVisitReason) {
		this.snomdCIdVisitReason = snomdCIdVisitReason;
	}

	public String getIsSet_OPDNEXTVISITDETAIL() {
		return isSet_OPDNEXTVISITDETAIL;
	}

	public void setIsSet_OPDNEXTVISITDETAIL(String isSet_OPDNEXTVISITDETAIL) {
		this.isSet_OPDNEXTVISITDETAIL = isSet_OPDNEXTVISITDETAIL;
	}

	public String getPresentIllnessHistory() {
		return presentIllnessHistory;
	}

	public void setPresentIllnessHistory(String presentIllnessHistory) {
		this.presentIllnessHistory = presentIllnessHistory;
	}

	


}
