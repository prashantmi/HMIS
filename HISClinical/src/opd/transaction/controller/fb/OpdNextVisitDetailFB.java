package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import registration.RegistrationConfig;

public class OpdNextVisitDetailFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String serialNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String empNo;
	private String loginSeatId;
    private String departmentUnitCode;
	private String deskType;   
	private String entryDate;
	private String isConfirmed;
	private String isPatDead;
	
	
	private String isEpisodeAlreadyOpen;
	private String episodeIsOpen;
	private String episodeNextVisitDate;
	//private String visitSummary;
	private String visitNotes;		// Visit Progress Notes
	
	private String nextVisitCriteria;
	private String nextVisitDuration;
	private String nextVisitDurationCriteria;
	private String episodeKeywords;
	private String episodeSummary;	// Episode Summary
	private String seatId;

	private String episodeTypeCode;
	private String episodeDate;
	private String episodeCloseDate;
	private String episodeCloseType;
	private String triageDuration;
	// private String episodeActionCode;
	// private String episodeAction;
	// private String visitNotes;
	// private String episodeNextVisitDeptCode;
	// private String episodeNextVisitDeptUnitCode;
	private String isDiagnosisDtlExists;
	
	private String targetFunction;
	private String popupNotes;
	private int currentPage;
	private String episodeKeyword[];
	private String episodeKeywordID;
	private String visitReason;


	private String patAptNo;	  
	private String patAptSlot;
	private String patAptQueueNO;
	private String isAppointment;
	
	private String isUnitAppointmentBased;
	private String patNextAptNo;	
	private String patNextAptSlot;
	private String patNextAptQueueNo;

	private String snomdPTVisitReason;
	private String snomdCIdVisitReason;
	
	private String snomdPTVisitNotes;
	private String snomdCIdVisitNotes;
	
	private String snomdPTKeywords;
	private String snomdCIdKeywords;
	private String keywords;
	private String snomdPTEpisodeSummary;
	private String snomdCIdEpisodeSummary;
	

	
	public OpdNextVisitDetailFB()
	{
		this.episodeNextVisitDate = "";
		this.visitNotes = "";
		this.episodeIsOpen = RegistrationConfig.EPISODE_ISOPEN_TRUE;
		this.entryDate = "";
		this.episodeKeywords = "";
		this.visitNotes ="";
		this.episodeSummary = "";
		this.nextVisitCriteria = OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS;
		this.nextVisitDuration = "";
		this.nextVisitDurationCriteria = OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC;
		
		this.seatId = "";
		
		this.popupNotes = "";
		this.currentPage = 1;
		this.isDiagnosisDtlExists = OpdConfig.NO;
		this.episodeCloseDate= "";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.episodeNextVisitDate = "";
		this.patNextAptNo="";
		this.patNextAptSlot="";
		this.visitNotes = "";
		this.episodeIsOpen = RegistrationConfig.EPISODE_ISOPEN_TRUE;
		this.entryDate = "";
		this.episodeKeywords = "";
		this.visitNotes ="";
		this.episodeSummary = "";
		this.nextVisitCriteria = OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS;
		this.nextVisitDuration = "";
		this.nextVisitDurationCriteria = OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC;

		this.seatId = "";
	
		this.popupNotes = "";
		this.currentPage = 1;
		this.isDiagnosisDtlExists = OpdConfig.NO;
		this.episodeCloseDate= "";
		
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

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
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

	public String getIsConfirmed()
	{
		return isConfirmed;
	}

	public void setIsConfirmed(String isConfirmed)
	{
		this.isConfirmed = isConfirmed;
	}

	public String getEpisodeNextVisitDate()
	{
		return episodeNextVisitDate;
	}

	public void setEpisodeNextVisitDate(String episodeNextVisitDate)
	{
		this.episodeNextVisitDate = episodeNextVisitDate;
	}

	public String getVisitNotes()
	{
		return visitNotes;
	}

	public void setVisitNotes(String visitNotes)
	{
		this.visitNotes = visitNotes;
	}

	public String getEpisodeIsOpen()
	{
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen)
	{
		this.episodeIsOpen = episodeIsOpen;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getNextVisitCriteria()
	{
		return nextVisitCriteria;
	}

	public void setNextVisitCriteria(String nextVisitCriteria)
	{
		this.nextVisitCriteria = nextVisitCriteria;
	}

	public String getNextVisitDuration()
	{
		return nextVisitDuration;
	}

	public void setNextVisitDuration(String nextVisitDuration)
	{
		this.nextVisitDuration = nextVisitDuration;
	}

	public String getNextVisitDurationCriteria()
	{
		return nextVisitDurationCriteria;
	}

	public void setNextVisitDurationCriteria(String nextVisitDurationCriteria)
	{
		this.nextVisitDurationCriteria = nextVisitDurationCriteria;
	}

	public String getEmpNo()
	{
		return empNo;
	}

	public void setEmpNo(String empNo)
	{
		this.empNo = empNo;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
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

	public String getEpisodeTypeCode()
	{
		return episodeTypeCode;
	}

	public void setEpisodeTypeCode(String episodeTypeCode)
	{
		this.episodeTypeCode = episodeTypeCode;
	}

	public String getEpisodeDate()
	{
		return episodeDate;
	}

	public void setEpisodeDate(String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	public String getEpisodeCloseDate()
	{
		return episodeCloseDate;
	}

	public void setEpisodeCloseDate(String episodeCloseDate)
	{
		this.episodeCloseDate = episodeCloseDate;
	}

	public String getEpisodeCloseType()
	{
		return episodeCloseType;
	}

	public void setEpisodeCloseType(String episodeCloseType)
	{
		this.episodeCloseType = episodeCloseType;
	}

	public String getLoginSeatId()
	{
		return loginSeatId;
	}

	public void setLoginSeatId(String loginSeatId)
	{
		this.loginSeatId = loginSeatId;
	}

	public String getIsEpisodeAlreadyOpen()
	{
		return isEpisodeAlreadyOpen;
	}

	public void setIsEpisodeAlreadyOpen(String isEpisodeAlreadyOpen)
	{
		this.isEpisodeAlreadyOpen = isEpisodeAlreadyOpen;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getTriageDuration() {
		return triageDuration;
	}

	public void setTriageDuration(String triageDuration) {
		this.triageDuration = triageDuration;
	}

	public String getIsPatDead() {
		return isPatDead;
	}

	public void setIsPatDead(String isPatDead) {
		this.isPatDead = isPatDead;
	}

	public String getEpisodeKeywords()
	{
		return episodeKeywords;
	}

	public void setEpisodeKeywords(String episodeKeywords)
	{
		this.episodeKeywords = episodeKeywords;
	}

	public String getTargetFunction()
	{
		return targetFunction;
	}

	public void setTargetFunction(String targetFunction)
	{
		this.targetFunction = targetFunction;
	}

	public String getEpisodeSummary()
	{
		return episodeSummary;
	}

	public void setEpisodeSummary(String episodeSummary)
	{
		this.episodeSummary = episodeSummary;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public String getPopupNotes()
	{
		return popupNotes;
	}

	public void setPopupNotes(String popupNotes)
	{
		this.popupNotes = popupNotes;
	}

	public String getIsDiagnosisDtlExists()
	{
		return isDiagnosisDtlExists;
	}

	public void setIsDiagnosisDtlExists(String isDiagnosisDtlExists)
	{
		this.isDiagnosisDtlExists = isDiagnosisDtlExists;
	}

	public String[] getEpisodeKeyword() {
		return episodeKeyword;
	}

	public void setEpisodeKeyword(String[] episodeKeyword) {
		this.episodeKeyword = episodeKeyword;
	}

	public String getEpisodeKeywordID() {
		return episodeKeywordID;
	}

	public void setEpisodeKeywordID(String episodeKeywordID) {
		this.episodeKeywordID = episodeKeywordID;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	public String getIsUnitAppointmentBased() {
		return isUnitAppointmentBased;
	}

	public void setIsUnitAppointmentBased(String isUnitAppointmentBased) {
		this.isUnitAppointmentBased = isUnitAppointmentBased;
	}

	public String getPatAptNo() {
		return patAptNo;
	}

	public void setPatAptNo(String patAptNo) {
		this.patAptNo = patAptNo;
	}

	public String getPatAptSlot() {
		return patAptSlot;
	}

	public void setPatAptSlot(String patAptSlot) {
		this.patAptSlot = patAptSlot;
	}

	public String getPatAptQueueNO() {
		return patAptQueueNO;
	}

	public void setPatAptQueueNO(String patAptQueueNO) {
		this.patAptQueueNO = patAptQueueNO;
	}

	public String getIsAppointment() {
		return isAppointment;
	}

	public void setIsAppointment(String isAppointment) {
		this.isAppointment = isAppointment;
	}

	public String getPatNextAptNo() {
		return patNextAptNo;
	}

	public void setPatNextAptNo(String patNextAptNo) {
		this.patNextAptNo = patNextAptNo;
	}

	public String getPatNextAptSlot() {
		return patNextAptSlot;
	}

	public void setPatNextAptSlot(String patNextAptSlot) {
		this.patNextAptSlot = patNextAptSlot;
	}

	public String getPatNextAptQueueNo() {
		return patNextAptQueueNo;
	}

	public void setPatNextAptQueueNo(String patNextAptQueueNo) {
		this.patNextAptQueueNo = patNextAptQueueNo;
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

	public String getSnomdPTVisitNotes() {
		return snomdPTVisitNotes;
	}

	public void setSnomdPTVisitNotes(String snomdPTVisitNotes) {
		this.snomdPTVisitNotes = snomdPTVisitNotes;
	}

	public String getSnomdCIdVisitNotes() {
		return snomdCIdVisitNotes;
	}

	public void setSnomdCIdVisitNotes(String snomdCIdVisitNotes) {
		this.snomdCIdVisitNotes = snomdCIdVisitNotes;
	}

	public String getSnomdPTKeywords() {
		return snomdPTKeywords;
	}

	public void setSnomdPTKeywords(String snomdPTKeywords) {
		this.snomdPTKeywords = snomdPTKeywords;
	}

	public String getSnomdCIdKeywords() {
		return snomdCIdKeywords;
	}

	public void setSnomdCIdKeywords(String snomdCIdKeywords) {
		this.snomdCIdKeywords = snomdCIdKeywords;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSnomdPTEpisodeSummary() {
		return snomdPTEpisodeSummary;
	}

	public void setSnomdPTEpisodeSummary(String snomdPTEpisodeSummary) {
		this.snomdPTEpisodeSummary = snomdPTEpisodeSummary;
	}

	public String getSnomdCIdEpisodeSummary() {
		return snomdCIdEpisodeSummary;
	}

	public void setSnomdCIdEpisodeSummary(String snomdCIdEpisodeSummary) {
		this.snomdCIdEpisodeSummary = snomdCIdEpisodeSummary;
	}

}
