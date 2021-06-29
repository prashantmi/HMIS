package hisglobal.vo;

public class SymptomWiseTemplateMappingMasterVO extends ValueObject
{
	private String entryDate;
	private String seatId;
	private String isValid;
	private String allergyTypeCode;
	private String symptomCode;
	private String templateCode;
	private String symptomterm;
	private String symptomcode;


	
	
	
	
	
	
		public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
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

	
	public String getSymptomCode() {
		return symptomCode;
	}

	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}

	public String getAllergyTypeCode() {
		return allergyTypeCode;
	}

	public void setAllergyTypeCode(String allergyTypeCode) {
		this.allergyTypeCode = allergyTypeCode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSymptomterm() {
		return symptomterm;
	}

	public void setSymptomterm(String symptomterm) {
		this.symptomterm = symptomterm;
	}

	public String getSymptomcode() {
		return symptomcode;
	}

	public void setSymptomcode(String symptomcode) {
		this.symptomcode = symptomcode;
	}

}
