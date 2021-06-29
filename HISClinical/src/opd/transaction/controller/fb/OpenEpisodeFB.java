package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import registration.controller.fb.CRNoFB;

public class OpenEpisodeFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String department;
	private String unit;
	private String episodeType;
	private String reason;
	private String selectEpisodeCode[];

	
	
	public String[] getSelectEpisodeCode() {
		return selectEpisodeCode;
	}
	public void setSelectEpisodeCode(String[] selectEpisodeCode) {
		this.selectEpisodeCode = selectEpisodeCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEpisodeType() {
		return episodeType;
	}
	public void setEpisodeType(String episodeType) {
		this.episodeType = episodeType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
		
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setHmode("");
//		this.setPatCrNo(" ");
		this.setDepartment("");
		this.setUnit("");
		this.setEpisodeCode("");
		this.setEpisodeType("");
	}
	public String getPatCrNo()
	{
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}
	
	
	
	
}
