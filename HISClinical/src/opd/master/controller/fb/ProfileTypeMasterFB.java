package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ProfileTypeMasterFB extends ActionForm
{
	private String profileType;
	private String profileName;
	private String profileDesc;
	private String hospitalCode;
	private String isUnique;
	private String generationMode;
	private String defaultName;
	private String profileUsablity;
	private String isCdBurn;
	private String isBilling;
	private String isActive;
	private String chk;
	private String hmode;
	private String isDesclaimerRequired;
	private String slNo;
	private String isValid;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	
	
	
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getIsDesclaimerRequired() {
		return isDesclaimerRequired;
	}
	public void setIsDesclaimerRequired(String isDesclaimerRequired) {
		this.isDesclaimerRequired = isDesclaimerRequired;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileDesc() {
		return profileDesc;
	}
	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsUnique() {
		return isUnique;
	}
	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}
	public String getGenerationMode() {
		return generationMode;
	}
	public void setGenerationMode(String generationMode) {
		this.generationMode = generationMode;
	}
	public String getDefaultName() {
		return defaultName;
	}
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}
	public String getProfileUsablity() {
		return profileUsablity;
	}
	public void setProfileUsablity(String profileUsablity) {
		this.profileUsablity = profileUsablity;
	}
	public String getIsBilling() {
		return isBilling;
	}
	public void setIsBilling(String isBilling) {
		this.isBilling = isBilling;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.isUnique="0";
		this.isCdBurn="0";
		this.isBilling="0";
		this.isDesclaimerRequired="0";
		this.generationMode="";
		this.profileUsablity="";
		this.profileType="";
		this.profileName="";
		this.profileDesc="";
		this.defaultName="";
		this.isActive="Active";
	}
	
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getIsCdBurn() {
		return isCdBurn;
	}
	public void setIsCdBurn(String isCdBurn) {
		this.isCdBurn = isCdBurn;
	}
	
	
	
}
