package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class NurseRoundFB extends CRNoFB
{
	private String progressNote;
	private String hmode;
	private String patCrNo;
	private String patAdmNo;
	private String processId;
	private String macroHead;
	private String viewNotes;
	private String hiddenProgNotes;
	private String deskMenuId;
	private String snomdPTProgessNotes;  
	private String snomdCIdProgressNotes;

	
	//for pagination
	private int currentPage;
	
	
	public NurseRoundFB()
	{
		this.currentPage=1;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getHiddenProgNotes() {
		return hiddenProgNotes;
	}
	public void setHiddenProgNotes(String hiddenProgNotes) {
		this.hiddenProgNotes = hiddenProgNotes;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
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
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setProgressNote("");
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
	public String getViewNotes() {
		return viewNotes;
	}
	public void setViewNotes(String viewNotes) {
		this.viewNotes = viewNotes;
	}

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
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
	
}
