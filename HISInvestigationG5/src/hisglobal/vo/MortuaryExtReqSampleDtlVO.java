package hisglobal.vo;

public class MortuaryExtReqSampleDtlVO extends ValueObject
{
	private String postmortemId;
	private String requestId;
	private String srNo;
	private String status;
	private String itemCode;
	private String itemName;
	private String receiveRemarks;
	
	
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getReceiveRemarks() {
		return receiveRemarks;
	}
	public void setReceiveRemarks(String receiveRemarks) {
		this.receiveRemarks = receiveRemarks;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
