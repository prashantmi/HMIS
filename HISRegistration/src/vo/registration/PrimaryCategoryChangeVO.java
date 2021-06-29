package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * Creation Date:- 04-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */

@SuppressWarnings("serial")
public class PrimaryCategoryChangeVO extends ValueObject{

	private String patCrNo;
	private String serialNo;
	private String patPrevPrimaryCat;
	private String patPrevPrimaryCatCode;
	private String patNewPrimaryCat;
	private String patNewPrimaryCatCode;
	private String effectiveFrom;
	private String effectiveTo;
	private String cardNo;
	private String validUpto;
	private String issuingAuthority;
	private String verificationDocumentId;
	private String remarks;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String systemIPAddress;
	
	private String isRenewal;
	private String isIdRequired;
	private String patIdNo;
	private String prevPatIdNo;
	private String prevVerificationDocument;
    private String isAlternateId;
    private String username;
    private String approvedBy;
    private String  newCatapprovedBy;
	private String patFirstName;
	private String clientCode;
	private String clientName;
	

	private String staffCardNo;
	private String staffCardName;
	private String relationWithStaff;
	private String relationNameWithStaff;
	private String cardvalidityDate;
	
	private String agsDistrictCode;
	private String agsCounterNo;
	private String agsNo;
	private String patPrimaryCatGrp; 
	private String patPrimaryCatGrpCode; 
	private String memSlNo;
	/*Start: Surabhi
	 * reason: for adding credit 
	 * date : 29-7-2016*/
	private String agsCreditLimit;
	
	
	public String getAgsCreditLimit() {
		return agsCreditLimit;
	}
	public void setAgsCreditLimit(String agsCreditLimit) {
		this.agsCreditLimit = agsCreditLimit;
	}
	//End
	
	public String getMemSlNo() {
		return memSlNo;
	}
	public void setMemSlNo(String memSlNo) {
		this.memSlNo = memSlNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {	
		this.patCrNo = patCrNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPatPrevPrimaryCat() {
		return patPrevPrimaryCat;
	}
	public void setPatPrevPrimaryCat(String patPrevPrimaryCat) {
		this.patPrevPrimaryCat = patPrevPrimaryCat;
	}
	public String getPatPrevPrimaryCatCode() {
		return patPrevPrimaryCatCode;
	}
	public void setPatPrevPrimaryCatCode(String patPrevPrimaryCatCode) {
		this.patPrevPrimaryCatCode = patPrevPrimaryCatCode;
	}
	public String getPatNewPrimaryCat() {
		return patNewPrimaryCat;
	}
	public void setPatNewPrimaryCat(String patNewPrimaryCat) {
		this.patNewPrimaryCat = patNewPrimaryCat;
	}
	public String getPatNewPrimaryCatCode() {
		return patNewPrimaryCatCode;
	}
	public void setPatNewPrimaryCatCode(String patNewPrimaryCatCode) {
		this.patNewPrimaryCatCode = patNewPrimaryCatCode;
	}
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getValidUpto() {
		return validUpto;
	}
	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}
	public String getIssuingAuthority() {
		return issuingAuthority;
	}
	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}
	public String getVerificationDocumentId() {
		return verificationDocumentId;
	}
	public void setVerificationDocumentId(String verificationDocumentId) {
		this.verificationDocumentId = verificationDocumentId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getSystemIPAddress() {
		return systemIPAddress;
	}
	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}
	public String getIsRenewal() {
		return isRenewal;
	}
	public void setIsRenewal(String isRenewal) {
		this.isRenewal = isRenewal;
	}
	public String getPatIdNo() {
		return patIdNo;
	}
	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}
	public String getIsIdRequired() {
		return isIdRequired;
	}
	public void setIsIdRequired(String isIdRequired) {
		this.isIdRequired = isIdRequired;
	}
	public String getPrevPatIdNo() {
		return prevPatIdNo;
	}
	public void setPrevPatIdNo(String prevPatIdNo) {
		this.prevPatIdNo = prevPatIdNo;
	}
	public String getPrevVerificationDocument() {
		return prevVerificationDocument;
	}
	public void setPrevVerificationDocument(String prevVerificationDocument) {
		this.prevVerificationDocument = prevVerificationDocument;
	}
	public String getIsAlternateId() {
		return isAlternateId;
	}
	public void setIsAlternateId(String isAlternateId) {
		this.isAlternateId = isAlternateId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getNewCatapprovedBy() {
		return newCatapprovedBy;
	}
	public void setNewCatapprovedBy(String newCatapprovedBy) {
		this.newCatapprovedBy = newCatapprovedBy;
	}
	public String getRelationWithStaff() {
		return relationWithStaff;
	}

	public void setRelationWithStaff(String relationWithStaff) {
		this.relationWithStaff = relationWithStaff;
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
	public String getRelationNameWithStaff() {
		return relationNameWithStaff;
	}

	public void setRelationNameWithStaff(String relationNameWithStaff) {
		this.relationNameWithStaff = relationNameWithStaff;
	}
	public String getPatPrimaryCatGrp() {
		return patPrimaryCatGrp;
	}
	public void setPatPrimaryCatGrp(String patPrimaryCatGrp) {
		this.patPrimaryCatGrp = patPrimaryCatGrp;
	}
	public String getPatPrimaryCatGrpCode() {
		return patPrimaryCatGrpCode;
	}

	public void setPatPrimaryCatGrpCode(String patPrimaryCatGrpCode) {
		this.patPrimaryCatGrpCode = patPrimaryCatGrpCode;
	}
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
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
	public String getCardvalidityDate() {
		return cardvalidityDate;
	}

	public void setCardvalidityDate(String cardvalidityDate) {
		this.cardvalidityDate = cardvalidityDate;
	}
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	
	
}



