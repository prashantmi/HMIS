package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


public class DeceasedItemDetailFB extends DeceasedTileFB
{
	private String postmortemId;
	private String srNO;
	private String itemCode[];
	private String remarks[];
	private String isPreserved[];
	private String preservativeId[];
	private String hmode;
	private String deceasedNo;
	private String isPreservedValue[];
	
	private String extraItemCode;
	private String extraRemarks;
	private String extraIsPreserved;
	private String extraPreservativeId;
	private String extraIsPreservedValue;
	private String hiddenExtraItemCode;
	private String hiddenExtraItemName;
	private String itemFoundFlag;
	
	public String[] getIsPreservedValue() {
		return isPreservedValue;
	}
	public void setIsPreservedValue(String[] isPreservedValue) {
		this.isPreservedValue = isPreservedValue;
	}
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String[] getItemCode() {
		return itemCode;
	}
	public void setItemCode(String[] itemCode) {
		this.itemCode = itemCode;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String[] getIsPreserved() {
		return isPreserved;
	}
	public void setIsPreserved(String[] isPreserved) {
		this.isPreserved = isPreserved;
	}
	public String[] getPreservativeId() {
		return preservativeId;
	}
	public void setPreservativeId(String[] preservativeId) {
		this.preservativeId = preservativeId;
	}
	public String getExtraRemarks() {
		return extraRemarks;
	}
	public void setExtraRemarks(String extraRemarks) {
		this.extraRemarks = extraRemarks;
	}
	public String getExtraIsPreserved() {
		return extraIsPreserved;
	}
	public void setExtraIsPreserved(String extraIsPreserved) {
		this.extraIsPreserved = extraIsPreserved;
	}
	public String getExtraPreservativeId() {
		return extraPreservativeId;
	}
	public void setExtraPreservativeId(String extraPreservativeId) {
		this.extraPreservativeId = extraPreservativeId;
	}
	public String getExtraIsPreservedValue() {
		return extraIsPreservedValue;
	}
	public void setExtraIsPreservedValue(String extraIsPreservedValue) {
		this.extraIsPreservedValue = extraIsPreservedValue;
	}
	public String getExtraItemCode() {
		return extraItemCode;
	}
	public void setExtraItemCode(String extraItemCode) {
		this.extraItemCode = extraItemCode;
	}
	public String getHiddenExtraItemCode() {
		return hiddenExtraItemCode;
	}
	public void setHiddenExtraItemCode(String hiddenExtraItemCode) {
		this.hiddenExtraItemCode = hiddenExtraItemCode;
	}
	public String getHiddenExtraItemName() {
		return hiddenExtraItemName;
	}
	public void setHiddenExtraItemName(String hiddenExtraItemName) {
		this.hiddenExtraItemName = hiddenExtraItemName;
	}
	public String getItemFoundFlag() {
		return itemFoundFlag;
	}
	public void setItemFoundFlag(String itemFoundFlag) {
		this.itemFoundFlag = itemFoundFlag;
	}
	
	public void resetItem(ActionMapping mapping,HttpServletRequest request)
	{
		this.setExtraItemCode("");
		this.setExtraRemarks("");
		this.setExtraIsPreserved("");
		this.setExtraPreservativeId("");
	}
	
	
}
