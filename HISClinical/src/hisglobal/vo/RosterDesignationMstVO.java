package hisglobal.vo;

public class RosterDesignationMstVO extends ValueObject{

	private String hospitalcode;
	private String serialNo;

	private String rosterTypeId;
	private String designationId;
	
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRosterTypeId() {
		return rosterTypeId;
	}
	public void setRosterTypeId(String rosterTypeId) {
		this.rosterTypeId = rosterTypeId;
	}
	
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
		
}
