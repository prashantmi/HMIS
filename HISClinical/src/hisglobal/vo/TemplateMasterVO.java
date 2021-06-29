package hisglobal.vo;

public class TemplateMasterVO extends ValueObject
{
	private String templateId;
	private String tempSerialNo;
	private String templateName;
	private String templateType;
	private String templateCategory;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String effectiveFrom;
	private String effectiveTo;
	private String hospitalCode;
	private String isDefault;
	private String isLock;
	private String ageRangeId;//added by swati on date:23-08-2019
	private String genderCode;//added by swati on date:23-08-2019
	private String ageRangeName;//added by swati on date:23-08-2019
	private String Gender;//added by swati on date:23-08-2019
	private String strGenderName;//added by swati on date:23-08-2019
	private String strTemplateDispName;//added by swati on date:28-08-2019

	public String getTemplateType()
	{
		return templateType;
	}

	public void setTemplateType(String templateType)
	{
		this.templateType = templateType;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getEffectiveFrom()
	{
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom)
	{
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo()
	{
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo)
	{
		this.effectiveTo = effectiveTo;
	}

	public String getTempSerialNo()
	{
		return tempSerialNo;
	}

	public void setTempSerialNo(String tempSerialNo)
	{
		this.tempSerialNo = tempSerialNo;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getTemplateCategory()
	{
		return templateCategory;
	}

	public void setTemplateCategory(String templateCategory)
	{
		this.templateCategory = templateCategory;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getAgeRangeId() {
		return ageRangeId;
	}

	public void setAgeRangeId(String ageRangeId) {
		this.ageRangeId = ageRangeId;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getAgeRangeName() {
		return ageRangeName;
	}

	public void setAgeRangeName(String ageRangeName) {
		this.ageRangeName = ageRangeName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getStrGenderName() {
		return strGenderName;
	}

	public void setStrGenderName(String strGenderName) {
		this.strGenderName = strGenderName;
	}

	public String getStrTemplateDispName() {
		return strTemplateDispName;
	}

	public void setStrTemplateDispName(String strTemplateDispName) {
		this.strTemplateDispName = strTemplateDispName;
	}

	
}
