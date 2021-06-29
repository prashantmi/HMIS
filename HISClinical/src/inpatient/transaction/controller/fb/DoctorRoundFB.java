package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class DoctorRoundFB extends CRNoFB
{
	private String progressNote;
	private String hmode;
	private String patCrNo;
	private String visitNote;
	private String instruction;
	private String enterBy;
	private String employeeNo;
	private String patAdmNo;
	private String radioBtn;
	private String processId;
	private String macroHead;
	private String viewInstruction;
	private String showProgNotes;
	private String showVisitNotes;
	private String showInsNotes;
	private String unitCode;
	private String viewNotes;
	private String hiddenProgNotes;
	private String hiddenVisitNotes;
	private String hiddenInstruction;
	private String hiddenDocInstruction;
	private int currentPage;
	private String docVisitDate;
	private String docVisitTimeHr;
	private String docVisitTimeMin;
	private String deskMenuId;
	

	private String snomdPTVisitNotes; //SNOMD-CT preffered Term
	private String snomdCIdVisitNotes; // SNOMD-CT concept Id
	private String snomdPTProgessNotes;
	private String snomdCIdProgressNotes;
	private String snomdPTInstruction;
	private String snomdCIdInstruction;
	
	//Added by Vasu on 26.Sept.2018
	private String roundNo;
	private String serialNo;
	private String roundDate;
	private String entryDateTime;
	private String change="0";

	public DoctorRoundFB()
	{
		this.currentPage=1;
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getHiddenDocInstruction() {
		return hiddenDocInstruction;
	}
	public void setHiddenDocInstruction(String hiddenDocInstruction) {
		this.hiddenDocInstruction = hiddenDocInstruction;
	}
	public String getHiddenVisitNotes() {
		return hiddenVisitNotes;
	}
	public void setHiddenVisitNotes(String hiddenVisitNotes) {
		this.hiddenVisitNotes = hiddenVisitNotes;
	}
	public String getHiddenInstruction() {
		return hiddenInstruction;
	}
	public void setHiddenInstruction(String hiddenInstruction) {
		this.hiddenInstruction = hiddenInstruction;
	}
	public String getHiddenProgNotes() {
		return hiddenProgNotes;
	}
	public void setHiddenProgNotes(String hiddenProgNotes) {
		this.hiddenProgNotes = hiddenProgNotes;
	}
	public String getViewNotes() {
		return viewNotes;
	}
	public void setViewNotes(String viewNotes) {
		this.viewNotes = viewNotes;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getProgressNote() {
		return progressNote;
	}
	public void setProgressNote(String progressNote) {
		this.progressNote = progressNote;
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
	public String getVisitNote() {
		return visitNote;
	}
	public void setVisitNote(String visitNote) {
		this.visitNote = visitNote;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getEnterBy() {
		return enterBy;
	}
	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	
	
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getRadioBtn() {
		return radioBtn;
	}
	public void setRadioBtn(String radioBtn) {
		this.radioBtn = radioBtn;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setInstruction("");
		this.setProgressNote("");
		this.setVisitNote("");
		this.setRadioBtn("");
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getMacroHead() {
		return macroHead;
	}
	public void setMacroHead(String macroHead) {
		this.macroHead = macroHead;
	}
	public String getViewInstruction() {
		return viewInstruction;
	}
	public void setViewInstruction(String viewInstruction) {
		this.viewInstruction = viewInstruction;
	}
	public String getShowProgNotes() {
		return showProgNotes;
	}
	public void setShowProgNotes(String showProgNotes) {
		this.showProgNotes = showProgNotes;
	}
	public String getShowVisitNotes() {
		return showVisitNotes;
	}
	public void setShowVisitNotes(String showVisitNotes) {
		this.showVisitNotes = showVisitNotes;
	}
	public String getShowInsNotes() {
		return showInsNotes;
	}
	public void setShowInsNotes(String showInsNotes) {
		this.showInsNotes = showInsNotes;
	}
	public String getDocVisitDate() {
		return docVisitDate;
	}
	public void setDocVisitDate(String docVisitDate) {
		this.docVisitDate = docVisitDate;
	}
	public String getDocVisitTimeHr() {
		return docVisitTimeHr;
	}
	public void setDocVisitTimeHr(String docVisitTimeHr) {
		this.docVisitTimeHr = docVisitTimeHr;
	}
	public String getDocVisitTimeMin() {
		return docVisitTimeMin;
	}
	public void setDocVisitTimeMin(String docVisitTimeMin) {
		this.docVisitTimeMin = docVisitTimeMin;
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


	public String getSnomdPTProgessNotes() {
		return snomdPTProgessNotes;
	}


	public void setSnomdPTProgessNotes(String snomdPTProgessNotes) {
		this.snomdPTProgessNotes = snomdPTProgessNotes;
	}


	public String getSnomdCIdProgressNotes() {
		return snomdCIdProgressNotes;
	}


	public void setSnomdCIdProgressNotes(String snomdCIdProgressNotes) {
		this.snomdCIdProgressNotes = snomdCIdProgressNotes;
	}


	public String getSnomdPTInstruction() {
		return snomdPTInstruction;
	}


	public void setSnomdPTInstruction(String snomdPTInstruction) {
		this.snomdPTInstruction = snomdPTInstruction;
	}


	public String getSnomdCIdInstruction() {
		return snomdCIdInstruction;
	}


	public void setSnomdCIdInstruction(String snomdCIdInstruction) {
		this.snomdCIdInstruction = snomdCIdInstruction;
	}


	public String getRoundNo() {
		return roundNo;
	}


	public void setRoundNo(String roundNo) {
		this.roundNo = roundNo;
	}


	public String getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	public String getRoundDate() {
		return roundDate;
	}


	public void setRoundDate(String roundDate) {
		this.roundDate = roundDate;
	}


	public String getEntryDateTime() {
		return entryDateTime;
	}


	public void setEntryDateTime(String entryDateTime) {
		this.entryDateTime = entryDateTime;
	}
	
	public String getChange() {
		return change;
	}


	public void setChange(String change) {
		this.change = change;
	}
	
}
