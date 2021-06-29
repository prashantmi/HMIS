package in.cdacnoida.idgenerator;

public class HealthId {

	private String firstName;
	private String lastName;
	private String fathersName;
	private String mothersName;
	private String gender;
	private String mobileNo;
	private int yearOfBirth;
	private int stateCode;
	private int districtCode;
	
	public HealthId() {
	}
	
	/**
	 * Constructor with fields for generation of Secondary Health ID
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param fathersName Father's Name
	 * @param mothersName Mother's Name
	 * @param gender Gender
	 * @param yearOfBirth Year of Birth
	 * @param stateCode State Code
	 * @param districtCode District Code
	 * @param mobileNo Mobile Number
	 */
	public HealthId(String firstName, String lastName, String fathersName,
			String mothersName, String gender, int yearOfBirth, int stateCode,
			int districtCode, String mobileNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.fathersName = fathersName;
		this.mothersName = mothersName;
		this.gender = gender;
		this.yearOfBirth = yearOfBirth;
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.mobileNo = mobileNo;
	}


	/*
	 * Getters and Setters
	 */
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}


	public String getFathersName() {
		return fathersName;
	}


	public void setFathersName(String fathersName) {
		this.fathersName = fathersName.trim();
	}

	public String getMothersName() {
		return mothersName;
	}


	public void setMothersName(String mothersName) {
		this.mothersName = mothersName.trim();
	}


	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender.trim();
	}



	public int getYearOfBirth() {
		return yearOfBirth;
	}


	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public int getStateCode() {
		return stateCode;
	}


	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}


	public int getDistrictCode() {
		return districtCode;
	}


	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
