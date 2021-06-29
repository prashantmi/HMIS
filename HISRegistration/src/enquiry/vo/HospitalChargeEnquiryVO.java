package enquiry.vo;

import hisglobal.vo.ValueObject;

public class HospitalChargeEnquiryVO extends ValueObject {

	private String tariffId;
	private String tariffName;
	private String slNo;
	private String groupId;
	private String groupName;
	private String patCat;
	private String patCatCode;
	private String chargeType;
	private String chargeTypeId;
	private String charge;
	private String ipdChargeTypeId;
	private String ipdChargeType;
	
	public String getTariffId() {
		return tariffId;
	}
	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}
	
	public String getTariffName() {
		return tariffName;
	}
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPatCat() {
		return patCat;
	}
	public void setPatCat(String patCat) {
		this.patCat = patCat;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getPatCatCode() {
		return patCatCode;
	}
	public void setPatCatCode(String patCatCode) {
		this.patCatCode = patCatCode;
	}
	public String getChargeTypeId() {
		return chargeTypeId;
	}
	public void setChargeTypeId(String chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getIpdChargeTypeId() {
		return ipdChargeTypeId;
	}
	public void setIpdChargeTypeId(String ipdChargeTypeId) {
		this.ipdChargeTypeId = ipdChargeTypeId;
	}
	public String getIpdChargeType() {
		return ipdChargeType;
	}
	public void setIpdChargeType(String ipdChargeType) {
		this.ipdChargeType = ipdChargeType;
	}
	
	
	
	
}
