package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class InPatientPrintLabelFB extends CRNoFB
{
	private String patCrNo;
	private String noOfLabel ;
	private String episodeVisitNo;
	private String hmode;
	private String printLabelType;
	

	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
		
	public String getNoOfLabel() {
		return noOfLabel;
	}
	public void setNoOfLabel(String noOfLabel) {
		this.noOfLabel = noOfLabel;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPrintLabelType() {
		return printLabelType;
	}
	public void setPrintLabelType(String printLabelType) {
		this.printLabelType = printLabelType;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setNoOfLabel("");
		this.setPrintLabelType("");
		
	}
}
