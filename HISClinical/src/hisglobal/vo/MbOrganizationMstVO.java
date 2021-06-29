package hisglobal.vo;

public class MbOrganizationMstVO extends ValueObject{

	 private String orgID;
	 private String slNo;
	 private String orgType;
	 private String orgTypeID;
	 private String orgName;
	 private String orgAddress;

	 
	 
	 public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getOrgTypeID() {
		return orgTypeID;
	}
	public void setOrgTypeID(String orgTypeID) {
		this.orgTypeID = orgTypeID;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	 
}
