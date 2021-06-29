package opd.transaction.controller.fb;

/**
 * @author  CDAC
 */

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import registration.controller.fb.CRNoFB;

public class OpdTemplateTabFB extends CRNoFB 
{
	private String hmode;
    
	private String userSeatId;
    private String departmentUnitCode;
    private String episodeCode;
    private String episodeVisitNo;
    private String deskMenuId;
    private String deskMenuName;
    private String consentHtmlToPrint;
    
    private List activeTemplateIds;
    private String selUndefaultTemp;
    private String haveTemplate;
    
    private String choice;
    private String selectedVisitNo;
    private String fromVisitDate;
    private String toVisitDate;
    private String entryDate;
    //private String selectedParaList;

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public OpdTemplateTabFB()
    {
    	this.userSeatId="";
        this.departmentUnitCode="";
        this.episodeCode="";
        this.episodeVisitNo="";
        this.deskMenuId="";
        this.deskMenuName="";
        
        this.activeTemplateIds=null;
        this.selUndefaultTemp="";
        this.haveTemplate="0";
        
        this.choice="";
        this.selectedVisitNo="";
        this.fromVisitDate="";
        this.toVisitDate="";
    }
    
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
    {
    	this.userSeatId="";
        this.departmentUnitCode="";
        this.episodeCode="";
        this.episodeVisitNo="";
        this.deskMenuId="";
        this.deskMenuName="";
        
        this.activeTemplateIds=null;
        this.selUndefaultTemp="";
        this.haveTemplate="0";

        this.choice="";
        this.selectedVisitNo="";
        this.fromVisitDate="";
        this.toVisitDate="";
}
    
    public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	public String getUserSeatId() {
		return userSeatId;
	}
	public void setUserSeatId(String userSeatId) {
		this.userSeatId = userSeatId;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getHaveTemplate() {
		return haveTemplate;
	}
	public void setHaveTemplate(String haveTemplate) {
		this.haveTemplate = haveTemplate;
	}
	public String getDeskMenuName() {
		return deskMenuName;
	}
	public void setDeskMenuName(String deskMenuName) {
		this.deskMenuName = deskMenuName;
	}
	public List getActiveTemplateIds() {
		return activeTemplateIds;
	}
	public void setActiveTemplateIds(List activeTemplateIds) {
		this.activeTemplateIds = activeTemplateIds;
	}
	public String getSelUndefaultTemp() {
		return selUndefaultTemp;
	}
	public void setSelUndefaultTemp(String selUndefaultTemp) {
		this.selUndefaultTemp = selUndefaultTemp;
	}
	public String getConsentHtmlToPrint() {
		return consentHtmlToPrint;
	}
	public void setConsentHtmlToPrint(String consentHtmlToPrint) {
		this.consentHtmlToPrint = consentHtmlToPrint;
	}
    public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSelectedVisitNo() {
		return selectedVisitNo;
	}
	public void setSelectedVisitNo(String selectedVisitNo) {
		this.selectedVisitNo = selectedVisitNo;
	}
	public String getFromVisitDate() {
		return fromVisitDate;
	}
	public void setFromVisitDate(String fromVisitDate) {
		this.fromVisitDate = fromVisitDate;
	}
	public String getToVisitDate() {
		return toVisitDate;
	}
	public void setToVisitDate(String toVisitDate) {
		this.toVisitDate = toVisitDate;
	}
}
