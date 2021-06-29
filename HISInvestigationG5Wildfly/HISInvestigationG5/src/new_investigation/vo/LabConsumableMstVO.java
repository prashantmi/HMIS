package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class LabConsumableMstVO extends ValueObject {

	
	private String otherItemID;
	private String itemName;
	private String defaultQuantity;
	private String itemType;
	private String itemIdFromStore;
	private String chk[];
	private String maxKey;
	private String uomCode;
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	public String getOtherItemID() {
		return otherItemID;
	}
	public void setOtherItemID(String otherItemID) {
		this.otherItemID = otherItemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDefaultQuantity() {
		return defaultQuantity;
	}
	public void setDefaultQuantity(String defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemIdFromStore() {
		return itemIdFromStore;
	}
	public void setItemIdFromStore(String itemIdFromStore) {
		this.itemIdFromStore = itemIdFromStore;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String chk[]) {
		this.chk = chk;
	}
}
