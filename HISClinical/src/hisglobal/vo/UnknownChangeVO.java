package hisglobal.vo;

/**
 * UnknownChangeVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DB tables. 
 * @author AHIS
 */
public class UnknownChangeVO extends ValueObject
{

	private String patCrNo;
	private String unknownConversionDate;
	private String empNo;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String systemIPAddress;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patAge;
	private String patDOB;
	private String patGender;
	private String patGenderCode;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String patSecondaryCat;
	private String patSecondaryCatCode;
	private String patGuardianName;
	private String patAddHNo;
	private String patAddStreet;
	private String patAddCity;
	private String patAddState;
	private String patAddStateCode;
	private String patNationalityCode;
	private String patNationality;
	private String verificationDocumentId;

	private String requestBy;
	private String requestRelation;
	private String requestByName;
	private String constableDesig;
	private String constableBadgeNo;
	private String requestByAddress;
	private String requestByContactNo;

	
	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getRequestRelation() {
		return requestRelation;
	}

	public void setRequestRelation(String requestRelation) {
		this.requestRelation = requestRelation;
	}

	public String getRequestByName() {
		return requestByName;
	}

	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}

	public String getConstableDesig() {
		return constableDesig;
	}

	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}

	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}

	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}

	public String getRequestByAddress() {
		return requestByAddress;
	}

	public void setRequestByAddress(String requestByAddress) {
		this.requestByAddress = requestByAddress;
	}

	public String getVerificationDocumentId() {
		return verificationDocumentId;
	}

	public void setVerificationDocumentId(String verificationDocumentId) {
		this.verificationDocumentId = verificationDocumentId;
	}

	/**
	 * Sets systemIPAddress.
	 * @param systemIPAddress
	 */
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	/**
	 * Retrieves systemIPAddress.
	 * @return Value of systemIPAddress.	
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	/**
	 * Sets patSecondaryCatCode.
	 * @param patSecondaryCatCode
	 */
	public void setPatSecondaryCatCode(String patSecondaryCatCode)
	{
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	/**
	 * Retrieves patSecondaryCatCode.
	 * @return Value of patSecondaryCatCode.	
	 */
	public String getPatSecondaryCatCode()
	{
		return patSecondaryCatCode;
	}

	/**
	 * Sets patSecondaryCat.
	 * @param patSecondaryCat
	 */
	public void setPatSecondaryCat(String patSecondaryCat)
	{
		this.patSecondaryCat = patSecondaryCat;
	}

	/**
	 * Retrieves patSecondaryCat.
	 * @return Value of patSecondaryCat.	
	 */
	public String getPatSecondaryCat()
	{
		return patSecondaryCat;
	}

	/**
	 * Sets patPrimaryCatCode.
	 * @param patPrimaryCatCode
	 */
	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	/**
	 * Retrieves patPrimaryCatCode.
	 * @return Value of patPrimaryCatCode.	
	 */
	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	/**
	 * Sets patPrimaryCat.
	 * @param patPrimaryCat
	 */
	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	/**
	 * Retrieves patPrimaryCat.
	 * @return Value of patPrimaryCat.	
	 */
	public String getPatPrimaryCat()
	{
		return patPrimaryCat;
	}

	/**
	 * Sets patMiddleName.
	 * @param patMiddleName
	 */
	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	/**
	 * Retrieves patMiddleName.
	 * @return Value of patMiddleName.	
	 */
	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	/**
	 * Sets patLastName.
	 * @param patLastName
	 */
	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	/**
	 * Retrieves patLastName.
	 * @return Value of patLastName.	
	 */
	public String getPatLastName()
	{
		return patLastName;
	}

	/**
	 * Sets patGuardianName.
	 * @param patGuardianName
	 */
	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	/**
	 * Retrieves patGuardianName.
	 * @return Value of patGuardianName.	
	 */
	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	/**
	 * Sets patGenderCode.
	 * @param patGenderCode
	 */
	public void setPatGenderCode(String patGenderCode)
	{
		this.patGenderCode = patGenderCode;
	}

	/**
	 * Retrieves patGenderCode.
	 * @return Value of patGenderCode.	
	 */
	public String getPatGenderCode()
	{
		return patGenderCode;
	}

	/**
	 * Sets patGender.
	 * @param patGender
	 */
	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	/**
	 * Retrieves patGender.
	 * @return Value of patGender.	
	 */
	public String getPatGender()
	{
		return patGender;
	}

	/**
	 * Sets patFirstName.
	 * @param patFirstName
	 */
	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	/**
	 * Retrieves patFirstName.
	 * @return Value of patFirstName.	
	 */
	public String getPatFirstName()
	{
		return patFirstName;
	}

	/**
	 * Sets patDOB.
	 * @param patDOB
	 */
	public void setPatDOB(String patDOB)
	{
		this.patDOB = patDOB;
	}

	/**
	 * Retrieves patDOB.
	 * @return Value of patDOB.	
	 */
	public String getPatDOB()
	{
		return patDOB;
	}

	/**
	 * Retrieves patAge.
	 * @return Value of patAge.
	 */
	public String getPatAge()
	{
		return patAge;
	}

	/**
	 * Sets patAge.
	 * @param patAge
	 */
	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	/**
	 * Retrieves entryDate.
	 * @return Value of entryDate.	
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves isValid.
	 * @return Value of isValid.	
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isValid.
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves patCrNo.
	 * @return Value of patCrNo.	
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patCrNo.
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/**
	 * Retrieves seatId.
	 * @return Value of seatId.	
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Sets patAddStateCode.
	 * @param patAddStateCode
	 */
	public void setPatAddStateCode(String patAddStateCode)
	{
		this.patAddStateCode = patAddStateCode;
	}

	/** Sets patAddStreet.
	 * @param patAddStreet
	 */
	public void setPatAddStreet(String patAddStreet)
	{
		this.patAddStreet = patAddStreet;
	}

	/**
	 * Sets patAddState.
	 * @param patAddState
	 */
	public void setPatAddState(String patAddState)
	{
		this.patAddState = patAddState;
	}

	/**
	 * Sets patAddHNo.
	 * @param patAddHNo
	 */
	public void setPatAddHNo(String patAddHNo)
	{
		this.patAddHNo = patAddHNo;
	}

	/**
	 * Sets patAddCity.
	 * @param patAddCity
	 */
	public void setPatAddCity(String patAddCity)
	{
		this.patAddCity = patAddCity;
	}

	/**
	 * Retrieves patAddStateCode.
	 * @return Value of patAddStateCode.	
	 */
	public String getPatAddStateCode()
	{
		return patAddStateCode;
	}

	/**
	 * Retrieves patAddStreet.
	 * @return Value of patAddStreet.	
	 */
	public String getPatAddStreet()
	{
		return patAddStreet;
	}

	/**
	 * Retrieves patAddState.
	 * @return Value of patAddState.	
	 */
	public String getPatAddState()
	{
		return patAddState;
	}

	/**
	 * Retrieves patAddHNo.
	 * @return Value of patAddHNo.	
	 */
	public String getPatAddHNo()
	{
		return patAddHNo;
	}

	/**
	 * Retrieves patAddCity.
	 * @return Value of patAddCity.	
	 */
	public String getPatAddCity()
	{
		return patAddCity;
	}

	/**
	 * Retrieves empNo.
	 * @return Value of empNo.	
	 */
	public String getEmpNo()
	{
		return empNo;
	}

	/**
	 * Sets empNo.
	 * @param empNo
	 */
	public void setEmpNo(String empNo)
	{
		this.empNo = empNo;
	}

	/**
	 * Retrieves unknownConversionDate.
	 * @return Value of unknownConversionDate.	
	 */
	public String getUnknownConversionDate()
	{
		return unknownConversionDate;
	}

	/**
	 * Sets unknownConversionDate.
	 * @param unknownConversionDate
	 */
	public void setUnknownConversionDate(String unknownConversionDate)
	{
		this.unknownConversionDate = unknownConversionDate;
	}

	/**
	 * Retrieves patNationality.
	 * @return Value of patNationality.	
	 */
	public String getPatNationality()
	{
		return patNationality;
	}

	/**
	 * Sets patNationality.
	 * @param patNationality
	 */
	public void setPatNationality(String patNationality)
	{
		this.patNationality = patNationality;
	}

	/**
	 * Retrieves patNationalityCode.
	 * @return Value of patNationalityCode.	
	 */
	public String getPatNationalityCode()
	{
		return patNationalityCode;
	}

	/**
	 * Sets patNationalityCode.
	 * @param patNationalityCode
	 */
	public void setPatNationalityCode(String patNationalityCode)
	{
		this.patNationalityCode = patNationalityCode;
	}

	public String getRequestByContactNo() {
		return requestByContactNo;
	}

	public void setRequestByContactNo(String requestByContactNo) {
		this.requestByContactNo = requestByContactNo;
	}

	
	
}
