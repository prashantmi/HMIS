package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ImagePointerMasterFB extends ActionForm
{
	private String hmode;
	private String imageDescId;
	private String imageDesc;
	private String slNo;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;
	private String chk;
	private String color;
	private String tempMode;
	private String isDefault;
	private String isActive;
	
	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.imageDesc="";
		this.color="#000000";
		this.isDefault=OpdConfig.NO;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getImageDescId() {
		return imageDescId;
	}

	public void setImageDescId(String imageDescId) {
		this.imageDescId = imageDescId;
	}

	public String getImageDesc() {
		return imageDesc;
	}

	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
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

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
