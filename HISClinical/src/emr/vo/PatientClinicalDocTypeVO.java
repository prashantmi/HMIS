/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : OPD IPD
 ## Process/Database Object Name	    : PATIENT CLINICAL DOCUMENTS
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2017 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package emr.vo;

import hisglobal.vo.ValueObject;

public class PatientClinicalDocTypeVO extends ValueObject
{
	private String documentType;
	private String documentName;
	private String documentDesc;
	private String isUnique;
	private String defaultName;
	private String isCdBurn;
	private String isBilling;
	private String generationMode;
	private String documentUsablity;
	private String isDesclaimerRequired;
	private String normalAccessType;
	private String restrictedAccessType;
	private String slNo;
	private String isValid;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String seatID;
	private String entryDate;
	private String isActive;
	private String documentGenerationMode;
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentDesc() {
		return documentDesc;
	}
	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}
	public String getIsUnique() {
		return isUnique;
	}
	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}
	public String getDefaultName() {
		return defaultName;
	}
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}
	
	public String getIsBilling() {
		return isBilling;
	}
	public void setIsBilling(String isBilling) {
		this.isBilling = isBilling;
	}
	public String getGenerationMode() {
		return generationMode;
	}
	public void setGenerationMode(String generationMode) {
		this.generationMode = generationMode;
	}
	public String getDocumentUsablity() {
		return documentUsablity;
	}
	public void setDocumentUsablity(String documentUsablity) {
		this.documentUsablity = documentUsablity;
	}
	public String getIsDesclaimerRequired() {
		return isDesclaimerRequired;
	}
	public void setIsDesclaimerRequired(String isDesclaimerRequired) {
		this.isDesclaimerRequired = isDesclaimerRequired;
	}
	public String getNormalAccessType() {
		return normalAccessType;
	}
	public void setNormalAccessType(String normalAccessType) {
		this.normalAccessType = normalAccessType;
	}
	public String getRestrictedAccessType() {
		return restrictedAccessType;
	}
	public void setRestrictedAccessType(String restrictedAccessType) {
		this.restrictedAccessType = restrictedAccessType;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getIsCdBurn() {
		return isCdBurn;
	}
	public void setIsCdBurn(String isCdBurn) {
		this.isCdBurn = isCdBurn;
	}
	public String getDocumentGenerationMode() {
		return documentGenerationMode;
	}
	public void setDocumentGenerationMode(String documentGenerationMode) {
		this.documentGenerationMode = documentGenerationMode;
	}
	
	
	
}

