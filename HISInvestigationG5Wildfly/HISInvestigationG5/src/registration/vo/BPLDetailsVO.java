package registration.vo;

import hisglobal.vo.ValueObject;

public class BPLDetailsVO extends ValueObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String familyHeadName="";
	private String patBPLCardNo="";
	private String memberName="";
	private String bplFamilyId="";
	private String patIsUrban="";
	private String patGenderCode="";
	private String patCrNo="";
	private String bplStateId="";
	private String bplRelationCode;
	private String bplMMJRKNo="";
	private String bplDetailsFound="";
		

	
	public String getBplDetailsFound() {
			return bplDetailsFound;
	}
	public void setBplDetailsFound(String bplDetailsFound) {
			this.bplDetailsFound = bplDetailsFound;
	}	
	public String getFamilyHeadName() {
		return familyHeadName;
	}
	public void setFamilyHeadName(String familyHeadName) {
		this.familyHeadName = familyHeadName;
	}
	public String getPatBPLCardNo() {
		return patBPLCardNo;
	}
	public void setPatBPLCardNo(String patBPLCardNo) {
		this.patBPLCardNo = patBPLCardNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBplFamilyId() {
		return bplFamilyId;
	}
	public void setBplFamilyId(String bplFamilyId) {
		this.bplFamilyId = bplFamilyId;
	}
	public String getPatIsUrban() {
		return patIsUrban;
	}
	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getBplStateId() {
		return bplStateId;
	}
	public void setBplStateId(String bplStateId) {
		this.bplStateId = bplStateId;
	}
	public String getBplRelationCode() {
		return bplRelationCode;
	}
	public void setBplRelationCode(String bplRelationCode) {
		this.bplRelationCode = bplRelationCode;
	}
	public String getBplMMJRKNo() {
		return bplMMJRKNo;
	}
	public void setBplMMJRKNo(String bplMMJRKNo) {
		this.bplMMJRKNo = bplMMJRKNo;
	}
	
}