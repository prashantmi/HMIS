package hisglobal.vo;

public class GrowthChartVO extends ValueObject
{
	private String ageYears;
	private String height;
	private String weight;
	private String vitalName;
	private String vitalId;
	private String gender;
	private String ageWeeks;
	private String ageMonths;
	private String ageDays;
	private String vitalValue;
	
	
	
	
	public String getVitalValue() {
		return vitalValue;
	}
	public void setVitalValue(String vitalValue) {
		this.vitalValue = vitalValue;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getVitalName() {
		return vitalName;
	}
	public void setVitalName(String vitalName) {
		this.vitalName = vitalName;
	}
	public String getVitalId() {
		return vitalId;
	}
	public void setVitalId(String vitalId) {
		this.vitalId = vitalId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgeYears() {
		return ageYears;
	}
	public void setAgeYears(String ageYears) {
		this.ageYears = ageYears;
	}
	public String getAgeMonths() {
		return ageMonths;
	}
	public void setAgeMonths(String ageMonths) {
		this.ageMonths = ageMonths;
	}
	public String getAgeDays() {
		return ageDays;
	}
	public void setAgeDays(String ageDays) {
		this.ageDays = ageDays;
	}
	public String getAgeWeeks() {
		return ageWeeks;
	}
	public void setAgeWeeks(String ageWeeks) {
		this.ageWeeks = ageWeeks;
	}
	
}