package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UOMMstFB extends ActionForm
{
	
	private String checklistID;
	private String slNO;
	private String checklist;
	private String isCompulsory;
	private String hmode;
	private String chk[];
	private String checklistPrevious;
	 
	
	
	private String uomCode;
	private String uomName;
	private String loincProperty;
	
	private String isActive;
	private String selectedChk;
	
	 




	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public String getUomCode() {
		return uomCode;
	}



	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}



	public String getUomName() {
		return uomName;
	}



	public void setUomName(String uomName) {
		this.uomName = uomName;
	}



	public String getLoincProperty() {
		return loincProperty;
	}



	public void setLoincProperty(String loincProperty) {
		this.loincProperty = loincProperty;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.checklist="";
		this.isCompulsory="0";
		this.uomName="";
		this.loincProperty="-1";
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
	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
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
