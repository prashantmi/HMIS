package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpinionApprovalFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String deceasedNo;
	private String postmortemId;
	private String srNo;
	private String startDateTime;
	private String endDateTime;
	private String opinion;
	private String remarks;
	private String conductBy;
	private String complexion;
	private String hairColorLength;
	private String eyeColor;
//	private String length;
//	private String length;
	private String length;
	private String clothDetails;
//	private String weight;
//	private String weight;
	private String weight;
	private String bodyLooks;
	private String startDate;
	private String startDateTimeHr;
	private String startDateTimeMin;
	private String endDate;
	private String endDateTimeHr;
	private String endDateTimeMin;
	private String sysDate="";
	private String pupilRight;
	private String pupilLeft;
	private String corneaRight;
	private String corneaLeft;
	private String cynosis;
	private String lividity;
	private String rigorMortis;
	private String orifices;
	private String mouthOdour;
	private String isDecomposition;
	private String hiddenTimeHour;
	private String hiddenTimeMin;
	private String incisionType;
	
	///Injury
	private String injuryNatureCode;
	private String injuryTypeCode;
	private String injuryRemarks;
	private String injuryDate="";
	private String injuryTimeHr;
	private String injuryTimeMin;
	private String weaponUsed;
	private String weaponRemarks;
	private String injuryPhoto;
	private String injuryPhotoValue;
	private String weaponUsedValue;
	
	private String deathDate;
	private String deathTimeHr;
	private String deathTimeMin;
	
	private String opinionCode[];
	private String opinionName[];
	private String opinionRemarks[];
	
	private String extraOpinionCode;
	private String extraOpinionRemarks;
	private String hiddenExtraOpinionCode;
	private String hiddenExtraOpinionName;
	private String selectedDeceased;
	
	private String deathMannerCode;
	private String addOpinionFlag;
	
	public String getDeathMannerCode() {
		return deathMannerCode;
	}
	public void setDeathMannerCode(String deathMannerCode) {
		this.deathMannerCode = deathMannerCode;
	}
	public String getSelectedDeceased() {
		return selectedDeceased;
	}
	public void setSelectedDeceased(String selectedDeceased) {
		this.selectedDeceased = selectedDeceased;
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
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getConductBy() {
		return conductBy;
	}
	public void setConductBy(String conductBy) {
		this.conductBy = conductBy;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
	public String getHairColorLength() {
		return hairColorLength;
	}
	public void setHairColorLength(String hairColorLength) {
		this.hairColorLength = hairColorLength;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public String getClothDetails() {
		return clothDetails;
	}
	public void setClothDetails(String clothDetails) {
		this.clothDetails = clothDetails;
	}
	public String getBodyLooks() {
		return bodyLooks;
	}
	public void setBodyLooks(String bodyLooks) {
		this.bodyLooks = bodyLooks;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartDateTimeHr() {
		return startDateTimeHr;
	}
	public void setStartDateTimeHr(String startDateTimeHr) {
		this.startDateTimeHr = startDateTimeHr;
	}
	public String getStartDateTimeMin() {
		return startDateTimeMin;
	}
	public void setStartDateTimeMin(String startDateTimeMin) {
		this.startDateTimeMin = startDateTimeMin;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDateTimeHr() {
		return endDateTimeHr;
	}
	public void setEndDateTimeHr(String endDateTimeHr) {
		this.endDateTimeHr = endDateTimeHr;
	}
	public String getEndDateTimeMin() {
		return endDateTimeMin;
	}
	public void setEndDateTimeMin(String endDateTimeMin) {
		this.endDateTimeMin = endDateTimeMin;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getPupilRight() {
		return pupilRight;
	}
	public void setPupilRight(String pupilRight) {
		this.pupilRight = pupilRight;
	}
	public String getPupilLeft() {
		return pupilLeft;
	}
	public void setPupilLeft(String pupilLeft) {
		this.pupilLeft = pupilLeft;
	}
	public String getCorneaRight() {
		return corneaRight;
	}
	public void setCorneaRight(String corneaRight) {
		this.corneaRight = corneaRight;
	}
	public String getCorneaLeft() {
		return corneaLeft;
	}
	public void setCorneaLeft(String corneaLeft) {
		this.corneaLeft = corneaLeft;
	}
	public String getCynosis() {
		return cynosis;
	}
	public void setCynosis(String cynosis) {
		this.cynosis = cynosis;
	}
	public String getLividity() {
		return lividity;
	}
	public void setLividity(String lividity) {
		this.lividity = lividity;
	}
	public String getRigorMortis() {
		return rigorMortis;
	}
	public void setRigorMortis(String rigorMortis) {
		this.rigorMortis = rigorMortis;
	}
	public String getOrifices() {
		return orifices;
	}
	public void setOrifices(String orifices) {
		this.orifices = orifices;
	}
	public String getMouthOdour() {
		return mouthOdour;
	}
	public void setMouthOdour(String mouthOdour) {
		this.mouthOdour = mouthOdour;
	}
	public String getIsDecomposition() {
		return isDecomposition;
	}
	public void setIsDecomposition(String isDecomposition) {
		this.isDecomposition = isDecomposition;
	}
	public String getHiddenTimeHour() {
		return hiddenTimeHour;
	}
	public void setHiddenTimeHour(String hiddenTimeHour) {
		this.hiddenTimeHour = hiddenTimeHour;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getIncisionType() {
		return incisionType;
	}
	public void setIncisionType(String incisionType) {
		this.incisionType = incisionType;
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
	public String[] getOpinionCode() {
		return opinionCode;
	}
	public void setOpinionCode(String[] opinionCode) {
		this.opinionCode = opinionCode;
	}
	public String[] getOpinionName() {
		return opinionName;
	}
	public void setOpinionName(String[] opinionName) {
		this.opinionName = opinionName;
	}
	public String[] getOpinionRemarks() {
		return opinionRemarks;
	}
	public void setOpinionRemarks(String[] opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
	public String getExtraOpinionCode() {
		return extraOpinionCode;
	}
	public void setExtraOpinionCode(String extraOpinionCode) {
		this.extraOpinionCode = extraOpinionCode;
	}
	public String getExtraOpinionRemarks() {
		return extraOpinionRemarks;
	}
	public void setExtraOpinionRemarks(String extraOpinionRemarks) {
		this.extraOpinionRemarks = extraOpinionRemarks;
	}
	public String getHiddenExtraOpinionCode() {
		return hiddenExtraOpinionCode;
	}
	public void setHiddenExtraOpinionCode(String hiddenExtraOpinionCode) {
		this.hiddenExtraOpinionCode = hiddenExtraOpinionCode;
	}
	public String getHiddenExtraOpinionName() {
		return hiddenExtraOpinionName;
	}
	public void setHiddenExtraOpinionName(String hiddenExtraOpinionName) {
		this.hiddenExtraOpinionName = hiddenExtraOpinionName;
	}
	
	public void resetOpinion(ActionMapping mapping,HttpServletRequest request)
	{
		this.setExtraOpinionCode("");
		this.setExtraOpinionRemarks("");
	}
	public String getAddOpinionFlag() {
		return addOpinionFlag;
	}
	public void setAddOpinionFlag(String addOpinionFlag) {
		this.addOpinionFlag = addOpinionFlag;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
