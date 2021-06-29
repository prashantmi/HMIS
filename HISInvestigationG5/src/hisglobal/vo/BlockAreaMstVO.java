package hisglobal.vo;

public class BlockAreaMstVO extends ValueObject{

	private String chk;
	private String isActive;
	private String serialNo;

	private String blockId;
	private String areaCode;
	private String areaTypeCode;
	private String workPrefrence;
		
	
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}
	public String getWorkPrefrence() {
		return workPrefrence;
	}
	public void setWorkPrefrence(String workPrefrence) {
		this.workPrefrence = workPrefrence;
	}
		
}
