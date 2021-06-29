package hisglobal.vo;

public class RackShelfMstVO extends ValueObject{

	private String rackName;
	private String rackId;
	private String rackShelfId;
	private String serialNo;
	private String shelfLabel;
	private String shelfStatus;
	private String shelfCapacity;
	private String isValid;
	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getRackShelfId() {
		return rackShelfId;
	}
	public void setRackShelfId(String rackShelfId) {
		this.rackShelfId = rackShelfId;
	}
	public String getShelfLabel() {
		return shelfLabel;
	}
	public void setShelfLabel(String shelfLabel) {
		this.shelfLabel = shelfLabel;
	}
	public String getShelfStatus() {
		return shelfStatus;
	}
	public void setShelfStatus(String shelfStatus) {
		this.shelfStatus = shelfStatus;
	}

	public String getShelfCapacity() {
		return shelfCapacity;
	}
	public void setShelfCapacity(String shelfCapacity) {
		this.shelfCapacity = shelfCapacity;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getRackName() {
		return rackName;
	}
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}
	

		
}
