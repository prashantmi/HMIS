package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ExtAdministrationFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
		
	private String sysDate;
	
	private String activityExtId;
	private String activitySelStartExecTimeHrs;
	private String activitySelStartExecTimeMin;
	private String activitySelRemarks;
	
	private String[] extSelIndexArray;
	private String[] extSelTreatmentNameArray;
	private String[] extSelDoseTimeArray;
	private String[] extSelStartExecutionTimeHrs;
	private String[] extSelStartExecutionTimeMin;
	private String[] extSelEndExecutionTimeHrs;
	private String[] extSelEndExecutionTimeMin;
	private String[] extSelRemarksArray;
	private String[] extSelIsDurationBound;
	private String[] extSelAdviceDateArray; 
	private String[] extSelSerealNoArray; 
	

	private String choice;
	private String administrationDate;
	private String showRemarks;
	private String index;
	private String admissionDate;
	
	private String sysHours;
	private String sysMinut;
	private String timeLimit;
	private String beforeTimeLimit;
	
	private String deskMenuId;
	
	
	
	public String getSysHours() {
		return sysHours;
	}



	public void setSysHours(String sysHours) {
		this.sysHours = sysHours;
	}



	public String getSysMinut() {
		return sysMinut;
	}



	public void setSysMinut(String sysMinut) {
		this.sysMinut = sysMinut;
	}



	public String getTimeLimit() {
		return timeLimit;
	}



	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}



	public String getBeforeTimeLimit() {
		return beforeTimeLimit;
	}



	public void setBeforeTimeLimit(String beforeTimeLimit) {
		this.beforeTimeLimit = beforeTimeLimit;
	}



	public String getIndex() {
		return index;
	}



	public void setIndex(String index) {
		this.index = index;
	}



	public String getAdmissionDate() {
		return admissionDate;
	}



	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.activitySelStartExecTimeHrs=null;
		this.activitySelStartExecTimeMin=null;
		this.extSelEndExecutionTimeHrs=null;
		this.extSelEndExecutionTimeMin=null;
		this.extSelStartExecutionTimeHrs=null;
		this.extSelStartExecutionTimeMin=null;
	}



	public String getHmode() {
		return hmode;
	}



	public void setHmode(String hmode) {
		this.hmode = hmode;
	}



	public String getPatCrNo() {
		return patCrNo;
	}



	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}



	public String getEpisodeCode() {
		return episodeCode;
	}



	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}



	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}



	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}



	public String getAdmissionNo() {
		return admissionNo;
	}



	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}



	public String getSysDate() {
		return sysDate;
	}



	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}



	public String getActivityExtId() {
		return activityExtId;
	}



	public void setActivityExtId(String activityExtId) {
		this.activityExtId = activityExtId;
	}



	public String getActivitySelStartExecTimeHrs() {
		return activitySelStartExecTimeHrs;
	}



	public void setActivitySelStartExecTimeHrs(String activitySelStartExecTimeHrs) {
		this.activitySelStartExecTimeHrs = activitySelStartExecTimeHrs;
	}



	public String getActivitySelStartExecTimeMin() {
		return activitySelStartExecTimeMin;
	}



	public void setActivitySelStartExecTimeMin(String activitySelStartExecTimeMin) {
		this.activitySelStartExecTimeMin = activitySelStartExecTimeMin;
	}



	public String getActivitySelRemarks() {
		return activitySelRemarks;
	}



	public void setActivitySelRemarks(String activitySelRemarks) {
		this.activitySelRemarks = activitySelRemarks;
	}



	public String[] getExtSelIndexArray() {
		return extSelIndexArray;
	}



	public void setExtSelIndexArray(String[] extSelIndexArray) {
		this.extSelIndexArray = extSelIndexArray;
	}



	public String[] getExtSelTreatmentNameArray() {
		return extSelTreatmentNameArray;
	}



	public void setExtSelTreatmentNameArray(String[] extSelTreatmentNameArray) {
		this.extSelTreatmentNameArray = extSelTreatmentNameArray;
	}



	public String[] getExtSelDoseTimeArray() {
		return extSelDoseTimeArray;
	}



	public void setExtSelDoseTimeArray(String[] extSelDoseTimeArray) {
		this.extSelDoseTimeArray = extSelDoseTimeArray;
	}



	public String[] getExtSelStartExecutionTimeHrs() {
		return extSelStartExecutionTimeHrs;
	}



	public void setExtSelStartExecutionTimeHrs(String[] extSelStartExecutionTimeHrs) {
		this.extSelStartExecutionTimeHrs = extSelStartExecutionTimeHrs;
	}



	public String[] getExtSelStartExecutionTimeMin() {
		return extSelStartExecutionTimeMin;
	}



	public void setExtSelStartExecutionTimeMin(String[] extSelStartExecutionTimeMin) {
		this.extSelStartExecutionTimeMin = extSelStartExecutionTimeMin;
	}



	public String[] getExtSelEndExecutionTimeHrs() {
		return extSelEndExecutionTimeHrs;
	}



	public void setExtSelEndExecutionTimeHrs(String[] extSelEndExecutionTimeHrs) {
		this.extSelEndExecutionTimeHrs = extSelEndExecutionTimeHrs;
	}



	public String[] getExtSelEndExecutionTimeMin() {
		return extSelEndExecutionTimeMin;
	}



	public void setExtSelEndExecutionTimeMin(String[] extSelEndExecutionTimeMin) {
		this.extSelEndExecutionTimeMin = extSelEndExecutionTimeMin;
	}



	public String[] getExtSelRemarksArray() {
		return extSelRemarksArray;
	}



	public void setExtSelRemarksArray(String[] extSelRemarksArray) {
		this.extSelRemarksArray = extSelRemarksArray;
	}



	public String[] getExtSelIsDurationBound() {
		return extSelIsDurationBound;
	}



	public void setExtSelIsDurationBound(String[] extSelIsDurationBound) {
		this.extSelIsDurationBound = extSelIsDurationBound;
	}



	public String[] getExtSelAdviceDateArray() {
		return extSelAdviceDateArray;
	}



	public void setExtSelAdviceDateArray(String[] extSelAdviceDateArray) {
		this.extSelAdviceDateArray = extSelAdviceDateArray;
	}



	public String[] getExtSelSerealNoArray() {
		return extSelSerealNoArray;
	}



	public void setExtSelSerealNoArray(String[] extSelSerealNoArray) {
		this.extSelSerealNoArray = extSelSerealNoArray;
	}


	public String getChoice() {
		return choice;
	}



	public void setChoice(String choice) {
		this.choice = choice;
	}



	public String getAdministrationDate() {
		return administrationDate;
	}



	public void setAdministrationDate(String administrationDate) {
		this.administrationDate = administrationDate;
	}



	public String getShowRemarks() {
		return showRemarks;
	}



	public void setShowRemarks(String showRemarks) {
		this.showRemarks = showRemarks;
	}



	public String getDeskMenuId() {
		return deskMenuId;
	}



	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
}
