package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeskMenuMasterFB extends ActionForm
{
	private String hmode;
	private String deskMenuId;
	private String deskMenuName;
	private String deskUrl;
	private String deskType;
	private String isTemplateBased;
	private String templateCategory;
	private String usabilityFlag;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;
	private String chk;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.deskMenuId="";
		this.deskMenuName="";
		this.deskUrl="";
		this.deskType="-1";
		this.templateCategory="-1";
		this.usabilityFlag="1";
	}
	
	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}

	public String getDeskMenuName() {
		return deskMenuName;
	}

	public void setDeskMenuName(String deskMenuName) {
		this.deskMenuName = deskMenuName;
	}

	public String getDeskUrl() {
		return deskUrl;
	}

	public void setDeskUrl(String deskUrl) {
		this.deskUrl = deskUrl;
	}

	public String getDeskType() {
		return deskType;
	}

	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}

	public String getIsTemplateBased() {
		return isTemplateBased;
	}

	public void setIsTemplateBased(String isTemplateBased) {
		this.isTemplateBased = isTemplateBased;
	}

	public String getTemplateCategory() {
		return templateCategory;
	}

	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
	}

	public String getUsabilityFlag() {
		return usabilityFlag;
	}

	public void setUsabilityFlag(String usabilityFlag) {
		this.usabilityFlag = usabilityFlag;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
}
