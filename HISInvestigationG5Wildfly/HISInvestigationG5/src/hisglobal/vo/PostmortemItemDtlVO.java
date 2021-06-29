package hisglobal.vo;

public class PostmortemItemDtlVO extends ValueObject
{
	private String postmortemId;
	private String srNO;
	private String itemCode;
	private String remarks;
	private String isPreserved;
	private String preservativeId;
	private String status;
	private String itemName;
	private String isPreservedLabel;
	private String preservativeName;
	
	
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSrNO() {
		return srNO;
	}
	public void setSrNO(String srNO) {
		this.srNO = srNO;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsPreserved() {
		return isPreserved;
	}
	public void setIsPreserved(String isPreserved) {
		this.isPreserved = isPreserved;
	}
	public String getPreservativeId() {
		return preservativeId;
	}
	public void setPreservativeId(String preservativeId) {
		this.preservativeId = preservativeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getIsPreservedLabel() {
		return isPreservedLabel;
	}
	public void setIsPreservedLabel(String isPreservedLabel) {
		this.isPreservedLabel = isPreservedLabel;
	}
	public String getPreservativeName() {
		return preservativeName;
	}
	public void setPreservativeName(String preservativeName) {
		this.preservativeName = preservativeName;
	}
	
}
