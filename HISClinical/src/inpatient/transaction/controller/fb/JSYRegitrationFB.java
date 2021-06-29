package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import registration.controller.fb.CRNoFB;

public class JSYRegitrationFB extends CRNoFB{

//	private String patCrNo;
	private String gravidaNo;
	private String ancNo;
	private String episodeCode;
	private String registrationAge;
	private String visitNo;
	private String liveBirthCount;
	private String broughtByASHA;
	private String healthWorkerID;
	private String healthWorkerName;
	private String healthWorkerCardNo;
	private String healthWorkerAddress;
	private String areaType;
//	private String patCrNoText="";
    private String hmode;
	private String patCaste;
	private String patIsUrban;
	private String patCasteName;
	private String expectedDeliveryDate;
	
	private String patAge;
	private String patPrimaryCategory;
	private String isCasteBound;
	private String patMinAgeJsyRule;
	private String patMaxAgeJsyRule;
	private String patPrimaryCatJsyRule;
	private String liveBirthJsyRule;
	private String castIdjsyRule;
	
	private String patCasteText;
	private String areaTypeId;
	
	private String jsyCategoryCode;
	private String gestationPeriodDays;
	private String gestationPeriod;
	private String registrationUpto;
	private String primaryCategoryName;
	private String casteNameJsyRule;
	private String patSecondaryCat;
	private String patSecondaryCatCode;
	private String treatmentCatValidity;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		this.broughtByASHA="";
		this.healthWorkerID="";
	//	this.setPatCrNo("            ");
	}
	
	/*public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}*/
	public String getGravidaNo() {
		return gravidaNo;
	}
	public void setGravidaNo(String gravidaNo) {
		this.gravidaNo = gravidaNo;
	}
	public String getAncNo() {
		return ancNo;
	}
	public void setAncNo(String ancNo) {
		this.ancNo = ancNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getRegistrationAge() {
		return registrationAge;
	}
	public void setRegistrationAge(String registrationAge) {
		this.registrationAge = registrationAge;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getLiveBirthCount() {
		return liveBirthCount;
	}
	public void setLiveBirthCount(String liveBirthCount) {
		this.liveBirthCount = liveBirthCount;
	}
	public String getBroughtByASHA() {
		return broughtByASHA;
	}
	public void setBroughtByASHA(String broughtByASHA) {
		this.broughtByASHA = broughtByASHA;
	}
	public String getHealthWorkerID() {
		return healthWorkerID;
	}
	public void setHealthWorkerID(String healthWorkerID) {
		this.healthWorkerID = healthWorkerID;
	}
	public String getHealthWorkerName() {
		return healthWorkerName;
	}
	public void setHealthWorkerName(String healthWorkerName) {
		this.healthWorkerName = healthWorkerName;
	}
	public String getHealthWorkerCardNo() {
		return healthWorkerCardNo;
	}
	public void setHealthWorkerCardNo(String healthWorkerCardNo) {
		this.healthWorkerCardNo = healthWorkerCardNo;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getPatCaste() {
		return patCaste;
	}
	public void setPatCaste(String patCaste) {
		this.patCaste = patCaste;
	}
	public String getPatIsUrban() {
		return patIsUrban;
	}
	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}
	public String getPatCasteName() {
		return patCasteName;
	}
	public void setPatCasteName(String patCasteName) {
		this.patCasteName = patCasteName;
	}
	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getPatPrimaryCategory() {
		return patPrimaryCategory;
	}

	public void setPatPrimaryCategory(String patPrimaryCategory) {
		this.patPrimaryCategory = patPrimaryCategory;
	}

	public String getIsCasteBound() {
		return isCasteBound;
	}

	public void setIsCasteBound(String isCasteBound) {
		this.isCasteBound = isCasteBound;
	}

	public String getPatMinAgeJsyRule() {
		return patMinAgeJsyRule;
	}

	public void setPatMinAgeJsyRule(String patMinAgeJsyRule) {
		this.patMinAgeJsyRule = patMinAgeJsyRule;
	}

	public String getPatMaxAgeJsyRule() {
		return patMaxAgeJsyRule;
	}

	public void setPatMaxAgeJsyRule(String patMaxAgeJsyRule) {
		this.patMaxAgeJsyRule = patMaxAgeJsyRule;
	}

	public String getPatPrimaryCatJsyRule() {
		return patPrimaryCatJsyRule;
	}

	public void setPatPrimaryCatJsyRule(String patPrimaryCatJsyRule) {
		this.patPrimaryCatJsyRule = patPrimaryCatJsyRule;
	}

	public String getLiveBirthJsyRule() {
		return liveBirthJsyRule;
	}

	public void setLiveBirthJsyRule(String liveBirthJsyRule) {
		this.liveBirthJsyRule = liveBirthJsyRule;
	}

	public String getCastIdjsyRule() {
		return castIdjsyRule;
	}

	public void setCastIdjsyRule(String castIdjsyRule) {
		this.castIdjsyRule = castIdjsyRule;
	}

	public String getPatCasteText() {
		return patCasteText;
	}

	public void setPatCasteText(String patCasteText) {
		this.patCasteText = patCasteText;
	}

	public String getHealthWorkerAddress() {
		return healthWorkerAddress;
	}

	public void setHealthWorkerAddress(String healthWorkerAddress) {
		this.healthWorkerAddress = healthWorkerAddress;
	}

	public String getAreaTypeId() {
		return areaTypeId;
	}

	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public String getJsyCategoryCode() {
		return jsyCategoryCode;
	}

	public void setJsyCategoryCode(String jsyCategoryCode) {
		this.jsyCategoryCode = jsyCategoryCode;
	}

	public String getGestationPeriodDays() {
		return gestationPeriodDays;
	}

	public void setGestationPeriodDays(String gestationPeriodDays) {
		this.gestationPeriodDays = gestationPeriodDays;
	}

	public String getGestationPeriod() {
		return gestationPeriod;
	}

	public void setGestationPeriod(String gestationPeriod) {
		this.gestationPeriod = gestationPeriod;
	}

	public String getRegistrationUpto() {
		return registrationUpto;
	}

	public void setRegistrationUpto(String registrationUpto) {
		this.registrationUpto = registrationUpto;
	}

	public String getPrimaryCategoryName() {
		return primaryCategoryName;
	}

	public void setPrimaryCategoryName(String primaryCategoryName) {
		this.primaryCategoryName = primaryCategoryName;
	}

	public String getCasteNameJsyRule() {
		return casteNameJsyRule;
	}

	public void setCasteNameJsyRule(String casteNameJsyRule) {
		this.casteNameJsyRule = casteNameJsyRule;
	}

	public String getPatSecondaryCat() {
		return patSecondaryCat;
	}

	public void setPatSecondaryCat(String patSecondaryCat) {
		this.patSecondaryCat = patSecondaryCat;
	}

	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}

	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	public String getTreatmentCatValidity() {
		return treatmentCatValidity;
	}

	public void setTreatmentCatValidity(String treatmentCatValidity) {
		this.treatmentCatValidity = treatmentCatValidity;
	}
	
}
