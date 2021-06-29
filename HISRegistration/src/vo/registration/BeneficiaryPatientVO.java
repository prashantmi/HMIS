package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * PatientVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DB tables. 
 * @author AHIS
 */
public class BeneficiaryPatientVO extends ValueObject
{
	private String patCrNo;
	private String clientCode;
	private String clientName;	
	
	private String cardNo;
	private String creditLetterRefNo;
	private String creditLetterDate;
	
	private String patPrimaryCatCode;	
	private String staffCardNo;
	private String staffCardName;
	private String relationWithStaff;
	private String cardvalidityDate;
	
	private String isDependent;
	private String dependentRelationCode;
	private String dependentRelation;
	private String dependentName;
	
	private String agsDistrictCode;
	private String agsCounterNo;
	private String agsNo;
	
	protected String letterType;
	
	//by Mukund on 26.07.2016
	private String patActualAmount;
	
	public String getPatActualAmount() {
		return patActualAmount;
	}
	public void setPatActualAmount(String patActualAmount) {
		this.patActualAmount = patActualAmount;
	}
	public String getLetterType() {
		return letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	protected String creditLimit;
	
	protected String balCreditLimit;
	
	
	public String getBalCreditLimit() {
		return balCreditLimit;
	}
	public void setBalCreditLimit(String balCreditLimit) {
		this.balCreditLimit = balCreditLimit;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getStaffCardNo() {
		return staffCardNo;
	}
	public void setStaffCardNo(String staffCardNo) {
		this.staffCardNo = staffCardNo;
	}
	public String getStaffCardName() {
		return staffCardName;
	}
	public void setStaffCardName(String staffCardName) {
		this.staffCardName = staffCardName;
	}
	public String getRelationWithStaff() {
		return relationWithStaff;
	}
	public void setRelationWithStaff(String relationWithStaff) {
		this.relationWithStaff = relationWithStaff;
	}
	public String getCardvalidityDate() {
		return cardvalidityDate;
	}
	public void setCardvalidityDate(String cardvalidityDate) {
		this.cardvalidityDate = cardvalidityDate;
	}
	public String getAgsDistrictCode() {
		return agsDistrictCode;
	}
	public void setAgsDistrictCode(String agsDistrictCode) {
		this.agsDistrictCode = agsDistrictCode;
	}
	public String getAgsCounterNo() {
		return agsCounterNo;
	}
	public void setAgsCounterNo(String agsCounterNo) {
		this.agsCounterNo = agsCounterNo;
	}
	public String getAgsNo() {
		return agsNo;
	}
	public void setAgsNo(String agsNo) {
		this.agsNo = agsNo;
	}
	public String getIsDependent() {
		return isDependent;
	}
	public void setIsDependent(String isDependent) {
		this.isDependent = isDependent;
	}
	public String getDependentRelationCode() {
		return dependentRelationCode;
	}
	public void setDependentRelationCode(String dependentRelationCode) {
		this.dependentRelationCode = dependentRelationCode;
	}
	public String getDependentRelation() {
		return dependentRelation;
	}
	public void setDependentRelation(String dependentRelation) {
		this.dependentRelation = dependentRelation;
	}
	public String getDependentName() {
		return dependentName;
	}
	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCreditLetterRefNo() {
		return creditLetterRefNo;
	}
	public void setCreditLetterRefNo(String creditLetterRefNo) {
		this.creditLetterRefNo = creditLetterRefNo;
	}
	public String getCreditLetterDate() {
		return creditLetterDate;
	}
	public void setCreditLetterDate(String creditLetterDate) {
		this.creditLetterDate = creditLetterDate;
	}

	
	}
