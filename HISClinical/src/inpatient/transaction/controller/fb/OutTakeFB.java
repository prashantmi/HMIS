package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class OutTakeFB extends ActionForm
{
	private String inTakeOutParaId;
	private String remarks;
	private String volume;
	private String hmode;
	private String patCrNo;
	private String outTakeDate="";
	private String outTakeTimeHr;
	private String outTakeTimeMin;
	private String hiddenParaId;
	private String hiddenParaName;
	private String outTakeRouteId;
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String sysDate="";
	private String prevDate;
	
	private String inTakeDate="";
	private String inTakeTimeHr;
	private String inTakeTimeMin;
	private String intakeRemarks;
	private String inTakeRouteId;
	private String intakeVolume;
	private String intakeParaId;
	
	private String intakeVOExistFlag;
	private String outtakeVOExistFlag;
	
	private String intakeSummaryStatus;
	private String outtakeSummaryStatus;
	private String totalIntake;
	private String totalOuttake;
	private String balance;
	private String intakeOutMode;
	private String isIntakeOuttakeTimeBased;
	private String totalViewIntake;
	private String totalViewOuttake;
	private String viewRemarks;
	
	private String fromDate;
	private String toDate;
	
	private String totalViewSummaryIntake;
	private String totalViewSummaryOuttake;
	private String viewSummarybalance;
	private String snomdPTVisitNotes;
	private String snomdCIdVisitNotes;
	private String snomdPTRemarks;
	private String snomdCIdRemarks;
	private String snomdPTintakeRemarks;
	private String snomdCIdintakeRemarks;
	private String visitNotes;
	private String isSnomedConcept;
	private String isSnomedFlagValue;
	private String prefferedTerm;
	private String conceptId;
	private String paraName; 
	private String paraType;
	
	private String deskMenuId;
	
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getInTakeOutParaId() {
		return inTakeOutParaId;
	}
	public void setInTakeOutParaId(String inTakeOutParaId) {
		this.inTakeOutParaId = inTakeOutParaId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String getOutTakeDate() {
		return outTakeDate;
	}
	public void setOutTakeDate(String outTakeDate) {
		this.outTakeDate = outTakeDate;
	}
	public String getOutTakeTimeHr() {
		return outTakeTimeHr;
	}
	public void setOutTakeTimeHr(String outTakeTimeHr) {
		this.outTakeTimeHr = outTakeTimeHr;
	}
	public String getOutTakeTimeMin() {
		return outTakeTimeMin;
	}
	public void setOutTakeTimeMin(String outTakeTimeMin) {
		this.outTakeTimeMin = outTakeTimeMin;
	}
	public String getHiddenParaId() {
		return hiddenParaId;
	}
	public void setHiddenParaId(String hiddenParaId) {
		this.hiddenParaId = hiddenParaId;
	}
	public String getHiddenParaName() {
		return hiddenParaName;
	}
	public void setHiddenParaName(String hiddenParaName) {
		this.hiddenParaName = hiddenParaName;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setInTakeOutParaId("");
		this.setRemarks("");
		this.setVolume("");
		//this.setOutTakeTimeHr("");
		//this.setOutTakeTimeMin("");
		this.setOutTakeDate("");
		this.setOutTakeRouteId("");
	}
	public void resetIntake(ActionMapping mapping,HttpServletRequest request)
	{
		this.setIntakeParaId("");
		this.setIntakeRemarks("");
		this.setIntakeVolume("");
		this.setInTakeDate("");
		this.setInTakeRouteId("");
	}
	public String getOutTakeRouteId() {
		return outTakeRouteId;
	}
	public void setOutTakeRouteId(String outTakeRouteId) {
		this.outTakeRouteId = outTakeRouteId;
	}
	public String getHiddenTimeHr() {
		return hiddenTimeHr;
	}
	public void setHiddenTimeHr(String hiddenTimeHr) {
		this.hiddenTimeHr = hiddenTimeHr;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getInTakeDate() {
		return inTakeDate;
	}
	public void setInTakeDate(String inTakeDate) {
		this.inTakeDate = inTakeDate;
	}
	public String getInTakeTimeHr() {
		return inTakeTimeHr;
	}
	public void setInTakeTimeHr(String inTakeTimeHr) {
		this.inTakeTimeHr = inTakeTimeHr;
	}
	public String getInTakeTimeMin() {
		return inTakeTimeMin;
	}
	public void setInTakeTimeMin(String inTakeTimeMin) {
		this.inTakeTimeMin = inTakeTimeMin;
	}
	public String getIntakeRemarks() {
		return intakeRemarks;
	}
	public void setIntakeRemarks(String intakeRemarks) {
		this.intakeRemarks = intakeRemarks;
	}
	public String getInTakeRouteId() {
		return inTakeRouteId;
	}
	public void setInTakeRouteId(String inTakeRouteId) {
		this.inTakeRouteId = inTakeRouteId;
	}
	public String getIntakeVolume() {
		return intakeVolume;
	}
	public void setIntakeVolume(String intakeVolume) {
		this.intakeVolume = intakeVolume;
	}
	public String getIntakeParaId() {
		return intakeParaId;
	}
	public void setIntakeParaId(String intakeParaId) {
		this.intakeParaId = intakeParaId;
	}
	public String getIntakeVOExistFlag() {
		return intakeVOExistFlag;
	}
	public void setIntakeVOExistFlag(String intakeVOExistFlag) {
		this.intakeVOExistFlag = intakeVOExistFlag;
	}
	public String getOuttakeVOExistFlag() {
		return outtakeVOExistFlag;
	}
	public void setOuttakeVOExistFlag(String outtakeVOExistFlag) {
		this.outtakeVOExistFlag = outtakeVOExistFlag;
	}
	public String getPrevDate() {
		return prevDate;
	}
	public void setPrevDate(String prevDate) {
		this.prevDate = prevDate;
	}
	public String getIntakeSummaryStatus() {
		return intakeSummaryStatus;
	}
	public void setIntakeSummaryStatus(String intakeSummaryStatus) {
		this.intakeSummaryStatus = intakeSummaryStatus;
	}
	public String getOuttakeSummaryStatus() {
		return outtakeSummaryStatus;
	}
	public void setOuttakeSummaryStatus(String outtakeSummaryStatus) {
		this.outtakeSummaryStatus = outtakeSummaryStatus;
	}
	public String getTotalIntake() {
		return totalIntake;
	}
	public void setTotalIntake(String totalIntake) {
		this.totalIntake = totalIntake;
	}
	public String getTotalOuttake() {
		return totalOuttake;
	}
	public void setTotalOuttake(String totalOuttake) {
		this.totalOuttake = totalOuttake;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getIntakeOutMode() {
		return intakeOutMode;
	}
	public void setIntakeOutMode(String intakeOutMode) {
		this.intakeOutMode = intakeOutMode;
	}
	public String getIsIntakeOuttakeTimeBased() {
		return isIntakeOuttakeTimeBased;
	}
	public void setIsIntakeOuttakeTimeBased(String isIntakeOuttakeTimeBased) {
		this.isIntakeOuttakeTimeBased = isIntakeOuttakeTimeBased;
	}
	public String getTotalViewIntake() {
		return totalViewIntake;
	}
	public void setTotalViewIntake(String totalViewIntake) {
		this.totalViewIntake = totalViewIntake;
	}
	public String getTotalViewOuttake() {
		return totalViewOuttake;
	}
	public void setTotalViewOuttake(String totalViewOuttake) {
		this.totalViewOuttake = totalViewOuttake;
	}
	public String getViewRemarks() {
		return viewRemarks;
	}
	public void setViewRemarks(String viewRemarks) {
		this.viewRemarks = viewRemarks;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getTotalViewSummaryIntake() {
		return totalViewSummaryIntake;
	}
	public void setTotalViewSummaryIntake(String totalViewSummaryIntake) {
		this.totalViewSummaryIntake = totalViewSummaryIntake;
	}
	public String getTotalViewSummaryOuttake() {
		return totalViewSummaryOuttake;
	}
	public void setTotalViewSummaryOuttake(String totalViewSummaryOuttake) {
		this.totalViewSummaryOuttake = totalViewSummaryOuttake;
	}
	public String getViewSummarybalance() {
		return viewSummarybalance;
	}
	public void setViewSummarybalance(String viewSummarybalance) {
		this.viewSummarybalance = viewSummarybalance;
	}
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
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
	public String getVisitNotes() {
		return visitNotes;
	}
	public void setVisitNotes(String visitNotes) {
		this.visitNotes = visitNotes;
	}
	public String getSnomdPTRemarks() {
		return snomdPTRemarks;
	}
	public void setSnomdPTRemarks(String snomdPTRemarks) {
		this.snomdPTRemarks = snomdPTRemarks;
	}
	public String getSnomdCIdRemarks() {
		return snomdCIdRemarks;
	}
	public void setSnomdCIdRemarks(String snomdCIdRemarks) {
		this.snomdCIdRemarks = snomdCIdRemarks;
	}
	public String getSnomdPTintakeRemarks() {
		return snomdPTintakeRemarks;
	}
	public void setSnomdPTintakeRemarks(String snomdPTintakeRemarks) {
		this.snomdPTintakeRemarks = snomdPTintakeRemarks;
	}
	public String getSnomdCIdintakeRemarks() {
		return snomdCIdintakeRemarks;
	}
	public void setSnomdCIdintakeRemarks(String snomdCIdintakeRemarks) {
		this.snomdCIdintakeRemarks = snomdCIdintakeRemarks;
	}
	public String getIsSnomedConcept() {
		return isSnomedConcept;
	}
	public void setIsSnomedConcept(String isSnomedConcept) {
		this.isSnomedConcept = isSnomedConcept;
	}
	public String getIsSnomedFlagValue() {
		return isSnomedFlagValue;
	}
	public void setIsSnomedFlagValue(String isSnomedFlagValue) {
		this.isSnomedFlagValue = isSnomedFlagValue;
	}
	public String getPrefferedTerm() {
		return prefferedTerm;
	}
	public void setPrefferedTerm(String prefferedTerm) {
		this.prefferedTerm = prefferedTerm;
	}
	public String getConceptId() {
		return conceptId;
	}
	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	
	
}
