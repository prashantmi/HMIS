package hisglobal.vo;

public class IcdHospitalMasterVO extends ValueObject {

	private String diseaseCode;
	private String hospitalDiseaseCode;
	private String hospitalCode;
	private String isValid;
	private String seatID;
	private String entryDate;

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getHospitalDiseaseCode() {
		return hospitalDiseaseCode;
	}

	public void setHospitalDiseaseCode(String hospitalDiseaseCode) {
		this.hospitalDiseaseCode = hospitalDiseaseCode;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getSeatID() {
		return seatID;
	}

	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

}
