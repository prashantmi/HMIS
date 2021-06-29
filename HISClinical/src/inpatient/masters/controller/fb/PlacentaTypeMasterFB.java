package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PlacentaTypeMasterFB extends ActionForm
{
	private String placentaTypeId;
	private String slNo;
	private String placentaType;
		
	private String chk[];
	private String hmode;
	private String isValid;
	private String tempMode;
	
	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPlacentaType("");
			
	}

	public String getPlacentaTypeId() {
		return placentaTypeId;
	}

	public void setPlacentaTypeId(String placentaTypeId) {
		this.placentaTypeId = placentaTypeId;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getPlacentaType() {
		return placentaType;
	}

	public void setPlacentaType(String placentaType) {
		this.placentaType = placentaType;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	
}
