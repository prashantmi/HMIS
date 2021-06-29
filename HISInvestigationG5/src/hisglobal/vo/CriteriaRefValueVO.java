package hisglobal.vo;

public class CriteriaRefValueVO extends ValueObject
{
	private String genderCode;
	private String criteriaRefValueSeqNO;
	private String lowAge;
	private String lowAgeUom;
	private String highAge;
	private String highAgeUom;
	private String lowValue;
	private String lowValueUom;
	public String getCriteriaRefValueSeqNO() {
		return criteriaRefValueSeqNO;
	}
	public void setCriteriaRefValueSeqNO(String criteriaRefValueSeqNO) {
		this.criteriaRefValueSeqNO = criteriaRefValueSeqNO;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getHighAge() {
		return highAge;
	}
	public void setHighAge(String highAge) {
		this.highAge = highAge;
	}
	public String getHighAgeUom() {
		return highAgeUom;
	}
	public void setHighAgeUom(String highAgeUom) {
		this.highAgeUom = highAgeUom;
	}
	public String getLowAge() {
		return lowAge;
	}
	public void setLowAge(String lowAge) {
		this.lowAge = lowAge;
	}
	public String getLowAgeUom() {
		return lowAgeUom;
	}
	public void setLowAgeUom(String lowAgeUom) {
		this.lowAgeUom = lowAgeUom;
	}
	public String getLowValue() {
		return lowValue;
	}
	public void setLowValue(String lowValue) {
		this.lowValue = lowValue;
	}
	public String getLowValueUom() {
		return lowValueUom;
	}
	public void setLowValueUom(String lowValueUom) {
		this.lowValueUom = lowValueUom;
	}

}
