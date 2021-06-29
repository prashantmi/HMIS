package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeceasedInjuriesFB extends ActionForm
{
	private String hmode;
	private String postmortemId;
	private String deceasedNo;
	private String deathMannerCode;
	private String deathMannerName;
	private String injuryNatureCode;
	private String injuryTypeCode;
	private String injuryNatureName;
	private String injuryTypeName;
	private String injuryRemarks;
	private String injuryDate;
	private String injuryTimeHr;
	private String injuryTimeMin;
	private String weaponUsed;
	private String weaponRemarks;
	private String injuryPhoto;
	private String injuryExistingFlag;
	private String injuryPhotoValue;
	private String weaponUsedValue;
	
	private String deathDate;
	private String deathTimeHr;
	private String deathTimeMin;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getDeathMannerCode() {
		return deathMannerCode;
	}
	public void setDeathMannerCode(String deathMannerCode) {
		this.deathMannerCode = deathMannerCode;
	}
	public String getDeathMannerName() {
		return deathMannerName;
	}
	public void setDeathMannerName(String deathMannerName) {
		this.deathMannerName = deathMannerName;
	}
	public String getInjuryNatureCode() {
		return injuryNatureCode;
	}
	public void setInjuryNatureCode(String injuryNatureCode) {
		this.injuryNatureCode = injuryNatureCode;
	}
	public String getInjuryTypeCode() {
		return injuryTypeCode;
	}
	public void setInjuryTypeCode(String injuryTypeCode) {
		this.injuryTypeCode = injuryTypeCode;
	}
	public String getInjuryNatureName() {
		return injuryNatureName;
	}
	public void setInjuryNatureName(String injuryNatureName) {
		this.injuryNatureName = injuryNatureName;
	}
	public String getInjuryTypeName() {
		return injuryTypeName;
	}
	public void setInjuryTypeName(String injuryTypeName) {
		this.injuryTypeName = injuryTypeName;
	}
	public String getInjuryRemarks() {
		return injuryRemarks;
	}
	public void setInjuryRemarks(String injuryRemarks) {
		this.injuryRemarks = injuryRemarks;
	}
	public String getInjuryDate() {
		return injuryDate;
	}
	public void setInjuryDate(String injuryDate) {
		this.injuryDate = injuryDate;
	}
	public String getInjuryTimeHr() {
		return injuryTimeHr;
	}
	public void setInjuryTimeHr(String injuryTimeHr) {
		this.injuryTimeHr = injuryTimeHr;
	}
	public String getInjuryTimeMin() {
		return injuryTimeMin;
	}
	public void setInjuryTimeMin(String injuryTimeMin) {
		this.injuryTimeMin = injuryTimeMin;
	}
	public String getWeaponUsed() {
		return weaponUsed;
	}
	public void setWeaponUsed(String weaponUsed) {
		this.weaponUsed = weaponUsed;
	}
	public String getWeaponRemarks() {
		return weaponRemarks;
	}
	public void setWeaponRemarks(String weaponRemarks) {
		this.weaponRemarks = weaponRemarks;
	}
	public String getInjuryPhoto() {
		return injuryPhoto;
	}
	public void setInjuryPhoto(String injuryPhoto) {
		this.injuryPhoto = injuryPhoto;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getInjuryExistingFlag() {
		return injuryExistingFlag;
	}
	public void setInjuryExistingFlag(String injuryExistingFlag) {
		this.injuryExistingFlag = injuryExistingFlag;
	}
	public String getInjuryPhotoValue() {
		return injuryPhotoValue;
	}
	public void setInjuryPhotoValue(String injuryPhotoValue) {
		this.injuryPhotoValue = injuryPhotoValue;
	}
	public String getWeaponUsedValue() {
		return weaponUsedValue;
	}
	public void setWeaponUsedValue(String weaponUsedValue) {
		this.weaponUsedValue = weaponUsedValue;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getDeathTimeHr() {
		return deathTimeHr;
	}
	public void setDeathTimeHr(String deathTimeHr) {
		this.deathTimeHr = deathTimeHr;
	}
	public String getDeathTimeMin() {
		return deathTimeMin;
	}
	public void setDeathTimeMin(String deathTimeMin) {
		this.deathTimeMin = deathTimeMin;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setInjuryDate("");
		this.setInjuryTimeHr("");
		this.setInjuryTimeMin("");
		this.setInjuryRemarks("");
		this.setWeaponRemarks("");
		this.setInjuryNatureCode("");
		this.setInjuryTypeCode("");
		this.setDeathMannerCode("");
	}
	
}
